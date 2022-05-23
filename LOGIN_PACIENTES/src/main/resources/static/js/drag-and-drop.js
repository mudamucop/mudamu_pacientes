document.addEventListener('DOMContentLoaded', (event) => {
    const dropDestiny = document.getElementById('drop-destiny');
    const dropSource = document.getElementById('drop-source');
    var dragSourceElement = null;

    // Get the element that is being dragged
    function handleDragStart(e) {
        this.style.opacity = '0.4';
        if (this.parentElement.id == 'drop-source') {
            console.log('drag from drop-source');
            dropDestiny.classList.add('dropzone');
        } else if (this.parentElement.id == 'drop-destiny') {
            console.log('drag from drop-destiny');
            dropSource.classList.add('dropzone');
        }
        dragSourceElement = this;

        e.dataTransfer.effectAllowed = 'move';
        e.dataTransfer.setData('text/html', this);
    }

    // Enable drop on the element
    function handleDragOver(e) {
        if (e.preventDefault) {
            e.preventDefault();
        }
        e.dataTransfer.dropEffect = 'move';
        return false;
    }

    // Add the class 'over' to the element that is being dragged over
    function handleDragEnter(e) {
        /* if (this.id != 'drop-source' && this.id != 'drop-destiny' && this.parentElement != null) {
             console.log(this);
             if (this.parentElement.id == 'drop-source') {
                 dropDestiny.classList.add('over');
                 console.log("added over to drop-source");
             } else if (this.parentElement.id == 'drop-destiny') {
                 dropSource.classList.add('over');
                 console.log("added over to drop-destiny");
             }
         } else */
        this.classList.add('over');
    }

    // Remove the class 'over' to the element that is not being dragged over anymore
    function handleDragLeave(e) {
        this.classList.remove('over');
        //console.log(this);
    }

    function handleDrop(e) {
        if (e.stopPropagation) {
            e.stopPropagation(); // stops the browser from redirecting.
        }
        if (dropSource.classList.contains('over') || dropDestiny.classList.contains('over')) {
            dragSourceElement.style.opacity = '1';
            let cloneNode = dragSourceElement.cloneNode(true);
            cloneNode.classList.add('sintoma-seleccionado');
            this.appendChild(cloneNode);
            dragSourceElement.remove();
        }
        return false;
    }

    // Remove the class 'over' to the element that is not being dragged anymore
    function handleDragEnd(e) {
        this.style.opacity = '1';
        dropDestiny.classList.remove('dropzone');
        dropSource.classList.remove('dropzone');
    }

    let items = document.querySelectorAll('.drag');
    items.forEach(function (item) {
        //item.addEventListener('dragenter', handleDragEnter, false);

        item.addEventListener('dragstart', handleDragStart, false);
        item.addEventListener('dragend', handleDragEnd, false);
    });

    dropDestiny.addEventListener('dragover', handleDragOver, false);
    dropDestiny.addEventListener('dragenter', handleDragEnter, false);
    dropDestiny.addEventListener('dragleave', handleDragLeave, false);
    dropDestiny.addEventListener('drop', handleDrop, false);

    dropSource.addEventListener('dragover', handleDragOver, false);
    dropSource.addEventListener('dragenter', handleDragEnter, false);
    dropSource.addEventListener('dragleave', handleDragLeave, false);
    dropSource.addEventListener('drop', handleDrop, false);

    /* Get síntomas */

    // Si quieres coger algo más aparte de los IDs, puedes hacerlo aquí
    function getSintomasSeleccionados() {
        let sintomas = [];
        let sintomasSeleccionados = document.getElementsByClassName('sintoma-seleccionado');
        for (let i = 0; i < sintomasSeleccionados.length; i++) {
            let sintoma = [];
            sintoma.push(sintomasSeleccionados[i].id);
            sintoma.push(sintomasSeleccionados[i].innerText);
            sintomas.push(sintoma);
        }
        return sintomas;
    }

    function handleClickPrediccion(e) {
        let sintom = getSintomasSeleccionados();

        console.log(sintom);
        $.ajax({
            url: '/getPrediction',
            type: "POST",
            data: {'sintomas': sintom},
            contentType: "application/json",
            success: function () {
                $("#exampleModal").hide('medium');
                $("#success").modal('show');
                setTimeout(function(){location.href = 'http://localhost:8080/pacPage'; }, 2000);
            },
            error: function () {

            },
        });
    }

    let btnPrediccion = document.getElementById('predecir');
    btnPrediccion.addEventListener('click', handleClickPrediccion, false);
});

