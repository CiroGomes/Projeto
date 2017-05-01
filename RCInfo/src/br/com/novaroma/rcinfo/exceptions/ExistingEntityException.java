package br.com.novaroma.rcinfo.exceptions;

@SuppressWarnings("serial")
public class ExistingEntityException extends Exception {
	public ExistingEntityException(String message) {
		super(message);
	}
}
