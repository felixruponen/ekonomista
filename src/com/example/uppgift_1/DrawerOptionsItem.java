package com.example.uppgift_1;

public class DrawerOptionsItem {

	String text;
	int image_id;
	
	public DrawerOptionsItem(int image_resource, String text){
		this.image_id = image_resource;
		this.text = text;
	}
	
	public int getImageId(){
		return image_id;
	}
	
	public String getText(){
		return text;		
	}
	
	
}
