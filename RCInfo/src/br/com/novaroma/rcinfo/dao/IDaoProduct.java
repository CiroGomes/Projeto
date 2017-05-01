package br.com.novaroma.rcinfo.dao;

import java.util.Map;

import br.com.novaroma.rcinfo.entities.Product;

public interface IDaoProduct {
	
	
	
	
	public void writeSerialized(Map<String, Product> map) throws Exception;
	
	Map<String, Product> readSerializedFile() throws Exception;



}
