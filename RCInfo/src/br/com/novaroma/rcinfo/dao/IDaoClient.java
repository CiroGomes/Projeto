package br.com.novaroma.rcinfo.dao;

import java.util.Map;

import br.com.novaroma.rcinfo.entities.Client;

public interface IDaoClient {

	void writeSerialized(Map<String, Client> map);

	Map<String, Client> readSerializedFile() throws Exception;

}
