package br.com.novaroma.rcinfo.dao;

import java.util.Map;

import br.com.novaroma.rcinfo.entities.Employee;

public interface IDaoEmployee {
	
	void writeSerialized(Map<String, Employee> map,  Map<Integer, String[]> arrayLogin) throws Exception;
	
	Map<String, Employee> readSerializedEmployee() throws Exception;
	Map<Integer, String[]> readSerializedLogin() throws Exception;

}
