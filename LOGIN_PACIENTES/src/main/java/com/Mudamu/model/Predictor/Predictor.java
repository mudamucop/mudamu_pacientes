package com.Mudamu.model.Predictor;

import java.util.List;

import com.Mudamu.model.User.User;

public class Predictor {

	User user;

	public User getDeveloper() {
		return user;
	}

	public void setDeveloper(User user) {
		this.user = user;
	}
	
	/*Developer developer;
	Type type;
	Platform platform;
	Double total_sales, na_sales,pal_sales,japan_sales,other_sales,total_budget,music_budget,design_budget,gameplay_budget;
	int year, month ,platformID, typeID;
	List<Type> TypeList;
	List<Game2> gamelist;

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public int getPlatformID() {
		return platformID;
	}

	public void setPlatformID(int platformID) {
		this.platformID = platformID;
	}
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public List<Platform> getPlatformList() {
		return platformList;
	}

	public void setPlatformList(List<Platform> platformList) {
		this.platformList = platformList;
	}
	List<Platform> platformList;
	
	public Predictor() {
		
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

	public Double getTotal_sales() {
		return total_sales;
	}

	public void setTotal_sales(Double total_sales) {
		this.total_sales = total_sales;
	}

	public Double getNa_sales() {
		return na_sales;
	}

	public void setNa_sales(Double na_sales) {
		this.na_sales = na_sales;
	}

	public Double getPal_sales() {
		return pal_sales;
	}

	public void setPal_sales(Double pal_sales) {
		this.pal_sales = pal_sales;
	}

	public Double getJapan_sales() {
		return japan_sales;
	}

	public void setJapan_sales(Double japan_sales) {
		this.japan_sales = japan_sales;
	}

	public Double getOther_sales() {
		return other_sales;
	}

	public void setOther_sales(Double other_sales) {
		this.other_sales = other_sales;
	}

	public Double getTotal_budget() {
		return total_budget;
	}

	public void setTotal_budget(Double total_budget) {
		this.total_budget = total_budget;
	}

	public Double getMusic_budget() {
		return music_budget;
	}

	public void setMusic_budget(Double music_budget) {
		this.music_budget = music_budget;
	}

	public Double getDesign_budget() {
		return design_budget;
	}

	public void setDesign_budget(Double design_budget) {
		this.design_budget = design_budget;
	}

	public Double getGameplay_budget() {
		return gameplay_budget;
	}

	public void setGameplay_budget(Double gameplay_budget) {
		this.gameplay_budget = gameplay_budget;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Type> getTypeList() {
		return TypeList;
	}

	public void setTypeList(List<Type> typeList) {
		TypeList = typeList;
	}
	
	public List<Game2> getGamelist() {
		return gamelist;
	}

	public void setGamelist(List<Game2> gamelist) {
		this.gamelist = gamelist;
	}

	public void sumaSales() {
		this.total_sales = this.japan_sales + this.pal_sales+this.na_sales+this.other_sales;
	}
	public void sumaBudgets() {
		this.total_budget = this.design_budget + this.gameplay_budget + this.music_budget;
	}
	@Override
	public String toString() {
		
		return " Developer:"+ developer+" total_sales:" +total_sales+" na_sales: "+na_sales +" pal_sales: "+ pal_sales+"japan_sales: "+ japan_sales+" other_sales: "+other_sales +" total_budget: "+total_budget +" music_budget: "+ music_budget+" design_budget: "+design_budget +" gameplay_budget: "+ gameplay_budget+"	year: "+year; 
				
	}

	public Platform getPlatform(int platformID2) {
		Platform p = null;
		for(Platform t : this.getPlatformList()) {
			System.out.println("t");
			if(t.getIDplatform()==platformID2) {
				p = t;
			}
		}
		return p;
	}*/
}
