package com.demo.kotlinproject.data.api.response.search;

public class RestaurantsItem{
	private Restaurant restaurant;

	public void setRestaurant(Restaurant restaurant){
		this.restaurant = restaurant;
	}

	public com.demo.kotlinproject.data.api.pojo.Restaurant getRestaurant(){
		return restaurant;
	}
}
