package com.Mudamu.controller;

import com.Mudamu.model.User.User;
import com.Mudamu.service.Login.LoginService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

@Controller
public class PacienteController {

	@Autowired
	LoginService userService;

	final static String EXCHANGE_NAME = "amq.topic";
	ConnectionFactory factory;

	String message = null;

	public PacienteController() {
		factory = new ConnectionFactory();
		factory.setHost("mudaworkers.duckdns.org");
		factory.setUsername("admin");
		factory.setPassword("password");
	}

	@GetMapping("/pacPage")
	public String userForm(Model model) throws Exception {
		String url = "index";

		// Logueado
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.loadUserByUsername(((UserDetails) principal).getUsername());

		model.addAttribute("citas", "active");
		model.addAttribute("citas", userService.getCitas(user));

		return url;
	}

	@PostMapping("/cancelarCita")
	public String cancelarCita(Model model, @RequestBody String data) throws Exception {

		// Logueado
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.loadUserByUsername(((UserDetails) principal).getUsername());

		userService.cancelarCita(Integer.parseInt(data.split("=")[1]));

		Channel channel = null;
		try (Connection connection = factory.newConnection()) {
			channel = connection.createChannel();
			channel.exchangeDeclare(EXCHANGE_NAME, "topic", true);

			channel.basicPublish(EXCHANGE_NAME, "admin", null,
					(user.getpacienteID() + " ha cancelado la cita").getBytes());

			channel.close();

		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}

		return "redirect:/pacPage";
	}
}
