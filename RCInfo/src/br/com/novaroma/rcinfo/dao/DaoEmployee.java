package br.com.novaroma.rcinfo.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Map;

import br.com.novaroma.rcinfo.entities.Employee;

public class DaoEmployee implements IDaoEmployee {
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	@SuppressWarnings("unchecked")
	public Map<Integer, String[]> readSerializedLogin() throws Exception {

		ObjectInputStream oisLogin = new ObjectInputStream(new FileInputStream("logins.txt"));
		Map<Integer, String[]> arrayListLogin = (Map<Integer, String[]>) oisLogin.readObject();

		oisLogin.close();

		return arrayListLogin;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Employee> readSerializedEmployee() throws Exception {

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("funcionarios.txt"));

		Map<String, Employee> mainMap = (Map<String, Employee>) ois.readObject();

		ois.close();

		return mainMap;

	}

	@Override
	public void writeSerialized(Map<String, Employee> employeeMap, Map<Integer, String[]> loginMap) throws Exception {
		ObjectOutputStream oosFunc = new ObjectOutputStream(new FileOutputStream("funcionarios.txt"));
		oosFunc.writeObject(employeeMap);
		oosFunc.flush();
		oosFunc.close();

		ObjectOutputStream oosLogin = new ObjectOutputStream(new FileOutputStream("logins.txt"));
		oosLogin.writeObject(loginMap);
		oosLogin.flush();
		oosLogin.close();
	}

}