package br.com.novaroma.rcinfo.entities;

import java.io.Serializable;
import java.util.Calendar;

public class Employee extends Person implements Serializable {
	private static final long serialVersionUID = 1L;

	private String code;
	private int office;

	public int getOffice() {
		return office;
	}

	public void setOffice(int office) {
		this.office = office;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public Employee() {

	}

	@Override
	public String toString() {
		return "[code=" + code + ", type=" + office + "]";
	}

	public Employee(int office, String code, String name, String cpf, Calendar birthday) {
		super(name, cpf, birthday);
		this.office = office;
		this.code = code;
	}

}
