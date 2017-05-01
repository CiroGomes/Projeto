package br.com.novaroma.rcinfo.facade;

import br.com.novaroma.rcinfo.exceptions.EmptyFieldException;
import br.com.novaroma.rcinfo.exceptions.ExistingEntityException;
import br.com.novaroma.rcinfo.exceptions.InvalidAgeException;
import br.com.novaroma.rcinfo.exceptions.InvalidCharactersFieldException;
import br.com.novaroma.rcinfo.exceptions.InvalidEmailException;

public interface IFacade {
	
	boolean create(Object o) throws ExistingEntityException, EmptyFieldException, InvalidCharactersFieldException,
			InvalidAgeException, InvalidEmailException, Exception;

	boolean update(Object o) throws InvalidCharactersFieldException, EmptyFieldException, InvalidAgeException,
			InvalidEmailException, Exception;

	boolean delete(Object o) throws Exception;

}