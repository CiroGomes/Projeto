package br.com.novaroma.rcinfo.apresentation;

import br.com.novaroma.rcinfo.facade.Facade;

public class Apresentation {

	public static void main(String[] args) {
		Facade facade = new Facade();

		try {
			facade.readSerializedFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		new LoginScreen(facade);

	}

}