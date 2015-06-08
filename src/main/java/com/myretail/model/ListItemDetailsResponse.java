package com.myretail.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ListItemDetailsResponse {
	
	private List<ItemDetailsResponse> list;

	public List<ItemDetailsResponse> getList() {
		return list;
	}

	public void setList(List<ItemDetailsResponse> itemList) {
		this.list =  itemList;
	}
	
	
}
