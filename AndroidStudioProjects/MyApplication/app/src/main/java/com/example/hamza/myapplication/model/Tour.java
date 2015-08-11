package com.example.hamza.myapplication.model;

import java.text.NumberFormat;

public class Tour {
	private int id;
	private String title;
	private String description;
	private double price;
	private String image;
	
	public Tour() {
	}

	public Tour(String title, double price) {
		this.title = title;
		this.price = price;
	}
	
	public long	 getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return title + "\n(" + nf.format(price) + ")";
	}
}
