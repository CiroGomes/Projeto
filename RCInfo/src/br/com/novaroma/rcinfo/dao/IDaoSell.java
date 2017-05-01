package br.com.novaroma.rcinfo.dao;

import java.util.Map;

import br.com.novaroma.rcinfo.entities.Sell;

public interface IDaoSell {
	public Map<String, Sell> readSerializedFile() throws Exception;

	public void writeSerialized(Map<String, Sell> map) throws Exception;
}
