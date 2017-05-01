package br.com.novaroma.rcinfo.entities;

import java.io.Serializable;
import java.util.Calendar;

public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String cpf;
	private Calendar birthday;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Calendar getBirthday() {
		return birthday;
	}

	public void setBirthday(Calendar birthday) {
		this.birthday = birthday;
	}




	@Override
	public String toString() {
		return "Person [name=" + name + ", cpf=" + cpf + ", birthday=" + birthday + "]";
	}

	public Person() {
	}

	public Person(String name, String cpf, Calendar birthday) {
		this.name = name;
		this.cpf = cpf;
		this.birthday = birthday;
	}
}