package br.com.novaroma.rcinfo.entities;

import java.io.Serializable;

public class Adress implements Serializable{
	private static final long serialVersionUID = 1L;
	private String publicPlace;
	private String city;
	private String neighborhood;
	private String number;
	private String cep;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public void setPublicPlace(String publicPlace) {
		this.publicPlace = publicPlace;
	}

	public String getPublicPlace() {
		return this.publicPlace;
	}

	public void setnumber(String number) {
		this.number = number;
	}

	public String getnumber() {
		return this.number;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCep() {
		return this.cep;
	}

	
	
	@Override
	public String toString() {
		return "Adress [publicPlace=" + publicPlace + ", number=" + number + ", cep=" + cep + "]";
	}

	public Adress() {

	}

	public Adress(String publicPlace, String city, String neighborhood, String number, String cep) {
		this.publicPlace = publicPlace;
		this.number = number;
		this.cep = cep;
		this.city = city;
		this.neighborhood = neighborhood;
	}
}
