package br.com.meli.orders;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

public class OrdersService {
	
	public Response getBy(String order_id) {
		

		Response response = given().config(RestAssured.config().sslConfig(SSLConfig.sslConfig().allowAllHostnames())).log().all()
				.get(String.format("/orders/%s", order_id));
		
		response.then().log().all();
		
		return response;
	}

}
