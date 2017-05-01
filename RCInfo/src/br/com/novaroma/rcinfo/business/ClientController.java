package br.com.novaroma.rcinfo.business;

import java.util.Map;

import br.com.novaroma.rcinfo.dao.IDaoClient;
import br.com.novaroma.rcinfo.dao.DaoClient;
import br.com.novaroma.rcinfo.entities.Client;
import br.com.novaroma.rcinfo.exceptions.EmptyFieldException;
import br.com.novaroma.rcinfo.exceptions.ExistingEntityException;
import br.com.novaroma.rcinfo.exceptions.InvalidAgeException;
import br.com.novaroma.rcinfo.exceptions.InvalidCharactersFieldException;
import br.com.novaroma.rcinfo.exceptions.InvalidEmailException;
import br.com.novaroma.rcinfo.exceptions.NotFoundException;
import br.com.novaroma.rcinfo.facade.IFacade;
import br.com.novaroma.rcinfo.utils.Utils;

public class ClientController implements IFacade {

	private DaoClient irc = new DaoClient();
	private Map<String, Client> mapaClient;

	public Client simpleReadClient(String cpf) throws EmptyFieldException, NotFoundException {
		if (cpf.replaceAll("[_.-]", "").equals("")) {
			throw new EmptyFieldException("Preencha o campo CPF corretamente");
		} else if (!this.getMapaClient().containsKey(cpf)) {
			throw new NotFoundException("Cliente não encontrado");
		}
		return this.getMapaClient().get(cpf);
	}

	public void readSerializedFile() throws Exception {
		this.setMapClient(this.getIrc().readSerializedFile());
	}

	public IDaoClient getIrc() {
		return irc;
	}

	public void setIrc(DaoClient irc) {
		this.irc = irc;
	}

	public Map<String, Client> getMapaClient() {
		return mapaClient;
	}

	public void setMapClient(Map<String, Client> clientMap) {
		this.mapaClient = clientMap;
	}

	@Override
	public boolean create(Object o) throws ExistingEntityException, EmptyFieldException,
			InvalidCharactersFieldException, InvalidAgeException, InvalidEmailException {
		Client client = (Client) o;
		if (checkExistingCpf(client)) {
			throw new ExistingEntityException("CPF já foi cadastrado anteriormente");
		} else if (validateClient(client)) {
			this.getMapaClient().put(client.getCpf(), client);
			this.getIrc().writeSerialized(this.getMapaClient());
			return true;
		}
		return false;

	}

	private boolean validateClient(Client client)
			throws EmptyFieldException, InvalidCharactersFieldException, InvalidAgeException, InvalidEmailException {
		if (checkEmptyFields(client)) {
			throw new EmptyFieldException("Preencha os campos vazios");
		} else if (!client.getName().replaceAll("[0-9]", "").equals(client.getName())) {
			throw new InvalidCharactersFieldException("Não podem haver números no campo Nome");
		} else if (!client.getAdress().getnumber().replaceAll("[0-9]", "").equals("")) {
			throw new InvalidCharactersFieldException("Preencha o campo Número apenas com números");
		} else if (Utils.validateBirthday(client) < 18) {
			throw new InvalidAgeException("Idade não pode ser abaixo de 18 anos");
		} else if (Utils.validateBirthday(client) > 127) {
			throw new InvalidAgeException("Idade não pode ser acima de 127 anos");
		} else if (!validateEmail(client)) {
			throw new InvalidEmailException("O E-mail deve conter o seguinte formato:\nexemplo@exemplo.com");
		}
		return true;
	}

	private boolean checkEmptyFields(Client client) {
		return client.getName().equals("") || client.getBirthday() == null
				|| client.getCpf().replaceAll("[_.-]", "").equals("") || client.getPhoneNumber().equals("")
				|| client.getEmail().equals("") || client.getAdress().getPublicPlace().equals("")
				|| client.getAdress().getCity().equals("") || client.getAdress().getNeighborhood().equals("")
				|| client.getAdress().getCep().equals("") || client.getAdress().getnumber().equals("");
	}

	private boolean validateEmail(Client client) {

		int arrobaIndex = client.getEmail().contains("@") ? client.getEmail().indexOf("@") : 0;
		boolean correctEmail = arrobaIndex > 0 && Character.isLetter(client.getEmail().charAt(0));
		if (correctEmail) {
			correctEmail = false;
			for (int i = arrobaIndex + 1; i < client.getEmail().length(); i++) {
				if (client.getEmail().charAt(i) == '.') {
					correctEmail = true;
				} else if (client.getEmail().charAt(i) == '@') {
					correctEmail = false;
					break;
				}
			}

			if (correctEmail)
				correctEmail = client.getEmail().charAt(client.getEmail().length() - 1) != '.';

			if (!correctEmail)
				return false;

		} else {
			return false;
		}
		return correctEmail;
	}

	private boolean checkExistingCpf(Client client) {
		return this.getMapaClient().containsKey(client.getCpf());
	}

	@Override
	public boolean update(Object o)
			throws EmptyFieldException, InvalidCharactersFieldException, InvalidAgeException, InvalidEmailException {
		Client client = (Client) o;
		if (checkExistingCpf(client) && validateClient(client)) {
			this.getMapaClient().put(client.getCpf(), client);
			this.getIrc().writeSerialized(this.getMapaClient());
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Object o) throws Exception {
		Client client = (Client) o;
		if (this.getMapaClient().containsKey(client.getCpf())) {
			this.getMapaClient().remove(client.getCpf());
			this.getIrc().writeSerialized(this.getMapaClient());
			return true;
		}
		return false;
	}

	public ClientController() {

	}
}