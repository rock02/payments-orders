package br.com.meli.shipments;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;

public class ShipmentsService {
	
	public Response getBy(String shipment_id) {
		

		Response response = given().config(RestAssured.config().sslConfig(SSLConfig.sslConfig().allowAllHostnames())).log().all()
				.get(String.format("/shipments/%s", shipment_id));
		
		response.then().log().all();
		
		return response;
	}

}
