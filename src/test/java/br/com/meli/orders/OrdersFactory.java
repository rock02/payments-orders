package br.com.meli.orders;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class OrdersFactory implements OrdersStrategy {

	public String get() {
		return random();
	}

	private String random() {

		Random r = new Random();
		
		List<String> orders = Arrays.asList("4114988927", "4114988960", "4114999549");
				

		return orders.get(r.nextInt(orders.size()));
	}

}
