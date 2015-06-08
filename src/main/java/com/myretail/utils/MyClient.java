package com.myretail.utils;

import java.util.List;

import javax.ws.rs.core.MediaType;


import com.myretail.model.ErrorResponse;
import com.myretail.model.ItemDetailsResponse;
import com.myretail.model.ListItemDetailsResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class MyClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String uri = "http://localhost:8080/myretail/rest/catalog/Toys";
		
		//set id as 1 for OK response
		try{
			Client client = Client.create();
			WebResource r=client.resource(uri);
			ClientResponse response = r.type(MediaType.APPLICATION_XML).get(ClientResponse.class);
			System.out.println(response.getStatus());
			if(response.getStatus() == 200){
				System.out.println("fkdhfjd");
				ListItemDetailsResponse arrItemList = response.getEntity(ListItemDetailsResponse.class);
				List<ItemDetailsResponse> itemList = arrItemList.getList();
				for(ItemDetailsResponse item:itemList){
					System.out.println(item.getId() + "::"+item.getName());
				}
			}else{
				ErrorResponse exc = response.getEntity(ErrorResponse.class);
				System.out.println(exc.getErrorCode());
				System.out.println(exc.getErrorId());
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
