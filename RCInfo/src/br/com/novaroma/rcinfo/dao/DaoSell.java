package br.com.novaroma.rcinfo.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import br.com.novaroma.rcinfo.entities.Sell;

public class DaoSell implements IDaoSell {
	@SuppressWarnings("unchecked")
	public Map<String, Sell> readSerializedFile() throws Exception {

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("vendas.txt"));

		Map<String, Sell> arrayListMain = (Map<String, Sell>) ois.readObject();

		ois.close();

		return arrayListMain;
	}

	public void writeSerialized(Map<String, Sell> map) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("vendas.txt"));
		oos.writeObject(map);
		oos.flush();
		oos.close();
	}

}
