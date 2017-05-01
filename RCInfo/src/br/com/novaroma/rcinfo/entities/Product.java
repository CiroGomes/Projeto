package br.com.novaroma.rcinfo.entities;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private String type;
	private String code;
	private String model;
	private String manufacturer;
	private String price;
	private String amount;


	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public void setManufacturer(String brand) {
		this.manufacturer = brand;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPrice() {
		return this.price;
	}

	@Override
	public String toString() {
		return "Product [type=" + type + ", code=" + code + ", model=" + model + ", brand=" + manufacturer + ", price="
				+ price + ", amount=" + amount + "]";
	}

	public Product() {
	}

	public Product(String type, String code, String model, String manufacturer, String price, String amount) {
		this.type = type;
		this.code = code;
		this.model = model;
		this.manufacturer = manufacturer;
		this.price = price;
		this.amount = amount;
	}
}