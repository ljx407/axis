package com.wsdd;

public class OrderService {
	
	public Order returnOrder(Order order) {
		Order newOrder = null ;
		if(order != null) {
			newOrder = new Order();
			if(order.getId() == 1) {
				newOrder.setName("stay foolish");
			} else {
				newOrder.setName("stay hungry");
			}
		}
		System.out.println("Server :" + newOrder.getName());
		return newOrder ;
	}
}
