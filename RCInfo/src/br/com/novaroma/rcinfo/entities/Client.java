package br.com.novaroma.rcinfo.entities;

import java.io.Serializable;
import java.util.Calendar;

public class Client extends Person implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String phoneNumber;
	private String email;
	private Adress adress;


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	public Adress getAdress() {
		return this.adress;
	}

	public Client() {
	}


	@Override
	public String toString() {
		return "Client [phoneNumber=" + phoneNumber + ", email=" + email + ", adress=" + adress + "]";
	}

	public Client(String nome, String cpf, Calendar birthday, String phoneNumber, String email, Adress adress) {
		super(nome, cpf, birthday);
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.adress = adress;
	}


}
