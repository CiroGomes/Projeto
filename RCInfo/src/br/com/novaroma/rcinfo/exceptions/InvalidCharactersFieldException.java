package br.com.novaroma.rcinfo.exceptions;

@SuppressWarnings("serial")
public class InvalidCharactersFieldException extends Exception{
	public InvalidCharactersFieldException(String message){
		super(message);
	}
}
