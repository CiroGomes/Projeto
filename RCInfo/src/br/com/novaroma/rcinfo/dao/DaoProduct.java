package br.com.novaroma.rcinfo.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import br.com.novaroma.rcinfo.entities.Product;

public class DaoProduct implements IDaoProduct {

	@SuppressWarnings("unchecked")
	public Map<String, Product> readSerializedFile() throws Exception {

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("produtos.txt"));

		Map<String, Product> arrayListMain = (Map<String, Product>) ois.readObject();

		ois.close();

		return arrayListMain;
	}

	public void writeSerialized(Map<String, Product> map) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("produtos.txt"));
		oos.writeObject(map);
		oos.flush();
		oos.close();
	}

}