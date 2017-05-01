package br.com.novaroma.rcinfo.exceptions;

@SuppressWarnings("serial")
public class InvalidLoginException extends Exception{
	public InvalidLoginException(String message){
		super(message);
	}
}
