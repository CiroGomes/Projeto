package br.com.novaroma.rcinfo.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Map;

import br.com.novaroma.rcinfo.entities.Client;

public class DaoClient implements IDaoClient {
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Client> readSerializedFile() throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("clientes.txt"));

		Map<String, Client> clientMap = (Map<String, Client>) ois.readObject();

		ois.close();

		return clientMap;
	}

	@Override
	public void writeSerialized(Map<String, Client> map) {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("clientes.txt"));
			oos.writeObject(map);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
