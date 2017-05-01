package br.com.novaroma.rcinfo.business;

import java.util.Map;

import br.com.novaroma.rcinfo.dao.IDaoEmployee;
import br.com.novaroma.rcinfo.dao.DaoEmployee;
import br.com.novaroma.rcinfo.entities.Employee;
import br.com.novaroma.rcinfo.exceptions.EmptyFieldException;
import br.com.novaroma.rcinfo.exceptions.ExistingEntityException;
import br.com.novaroma.rcinfo.exceptions.InvalidAgeException;
import br.com.novaroma.rcinfo.exceptions.InvalidLoginException;
import br.com.novaroma.rcinfo.exceptions.NotFoundException;
import br.com.novaroma.rcinfo.facade.IFacade;
import br.com.novaroma.rcinfo.utils.Utils;
import br.com.novaroma.rcinfo.exceptions.InvalidCharactersFieldException;

public class EmployeeController implements IFacade {

	private DaoEmployee ire = new DaoEmployee();
	private Map<Integer, String[]> arrayListLogin;
	private Map<String, Employee> mapaEmployee;

	public Employee simpleReadEmployee(String code) throws EmptyFieldException, InvalidCharactersFieldException, NotFoundException {
		if (code.equals("")) {
			throw new EmptyFieldException("Preencha o campo Código");
		} else if (!code.replaceAll("[0-9]", "").equals("")) {
			throw new InvalidCharactersFieldException("Campo código deve conter apenas números");
		} else if(!this.getMapaEmployee().containsKey(code)){
			throw new NotFoundException("Funcionário não encontrado");
		}
		return this.getMapaEmployee().get(code);
	}
	
	public void readSerializedFile() throws Exception {
		this.setMapaEmployee(this.getIre().readSerializedEmployee());
		this.setArrayListLogin(this.getIre().readSerializedLogin());
	}

	public boolean autenticarLogin(int comboBoxLogin, String code, String password)
			throws EmptyFieldException, InvalidLoginException, InvalidCharactersFieldException {
		boolean logged = false;

		if (code.equals("") && password.equals("")) {
			throw new EmptyFieldException("Os campos Código e Senha estão vazios");
		} else if (code.equals("")) {
			throw new EmptyFieldException("Campo Código está vazio");
		} else if (password.equals("")) {
			throw new EmptyFieldException("Campo Senha está vazio");
		} else if (!code.replaceAll("[0-9]", "").equals("")) {
			throw new InvalidCharactersFieldException("Devem conter apenas números no campo Código");
		} else
			try {
				logged = this.getArrayListLogin().containsKey(Integer.parseInt(code))
						&& this.getArrayListLogin().get(Integer.parseInt(code))[2]
								.equals(Utils.criptografarSenha(password))
						&& this.getArrayListLogin().get(Integer.parseInt(code))[0]
								.equals(String.valueOf(comboBoxLogin));
			} catch (Exception e) {
				e.printStackTrace();
			}
		if (!logged) {
			throw new InvalidLoginException("Código ou senha inválidos");
		}
		return logged;
	}

	public Map<Integer, String[]> getArrayListLogin() {
		return arrayListLogin;
	}

	public void setArrayListLogin(Map<Integer, String[]> arrayListLogin) {
		this.arrayListLogin = arrayListLogin;
	}

	public IDaoEmployee getIre() {
		return ire;
	}

	public void setIre(DaoEmployee ire) {
		this.ire = ire;
	}

	public Map<String, Employee> getMapaEmployee() {
		return mapaEmployee;
	}

	public void setMapaEmployee(Map<String, Employee> mapaEmployee) {
		this.mapaEmployee = mapaEmployee;
	}

	@Override
	public boolean create(Object o)
			throws ExistingEntityException, EmptyFieldException, InvalidCharactersFieldException, InvalidAgeException {
		Employee employee = (Employee) o;
		if (checkExistingCode(employee)) {
			throw new ExistingEntityException("Código já cadastrado");
		} else if (validateEmployee(employee)) {
			this.getMapaEmployee().put(employee.getCode(), employee);
			return true;
		}
		return false;
	}

	private boolean checkExistingCode(Employee employee) {
		return this.getMapaEmployee().containsKey(employee.getCode());
	}

	private boolean validateEmployee(Employee employee)
			throws EmptyFieldException, InvalidCharactersFieldException, InvalidAgeException {
		if (checkEmptyFields(employee)) {
			throw new EmptyFieldException("Preencha os campos vazios");
		} else if (!employee.getName().replaceAll("[0-9]", "").equals(employee.getName())) {
			throw new InvalidCharactersFieldException("Não podem haver números no campo Nome");
		} else if (Utils.validateBirthday(employee) < 18) {
			throw new InvalidAgeException("Idade não pode ser abaixo de 18 anos");
		} else if (Utils.validateBirthday(employee) > 65) {
			throw new InvalidAgeException("Idade não pode ser acima de 65 anos");
		}
		return true;
	}

	private boolean checkEmptyFields(Employee employee) {
		return employee.getBirthday() == null || employee.getCode().equals("")
				|| employee.getCpf().replaceAll("[_.-]", "").equals("") || employee.getName().equals("");
	}

	public boolean createEmployee(Employee employee, String password)
			throws NumberFormatException, ExistingEntityException, EmptyFieldException, InvalidCharactersFieldException,
			InvalidAgeException, Exception {
		if (this.create(employee)) {
			this.getArrayListLogin().put(Integer.parseInt(employee.getCode()), new String[] {
					String.valueOf(employee.getOffice()), employee.getCode(), Utils.criptografarSenha(password) });
			this.getIre().writeSerialized(this.getMapaEmployee(), this.getArrayListLogin());
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Object o) throws EmptyFieldException, InvalidCharactersFieldException, InvalidAgeException {
		Employee employee = (Employee) o;
		if (checkExistingCode(employee) && validateEmployee(employee)) {
			this.getMapaEmployee().put(employee.getCode(), employee);
			return true;
		}
		return false;
	}

	public boolean updateEmployee(Employee employee, String password) throws NumberFormatException, EmptyFieldException,
			InvalidCharactersFieldException, InvalidAgeException, Exception {
		boolean teste = false;
		if (this.update(employee)) {
			this.getArrayListLogin().put(Integer.parseInt(employee.getCode()), new String[] {
					String.valueOf(employee.getOffice()), employee.getCode(), Utils.criptografarSenha(password) });
			this.getIre().writeSerialized(this.getMapaEmployee(), this.getArrayListLogin());
			teste = true;
		}
		System.out.println(teste ? "pegou" : "Falhou");
		return teste;
	}

	@Override
	public boolean delete(Object o) throws Exception {
		Employee employee = (Employee) o;

		if (checkExistingCode(employee)) {
			this.getMapaEmployee().remove(employee.getCode());

			this.getArrayListLogin().remove(Integer.parseInt(employee.getCode()));

			this.getIre().writeSerialized(this.getMapaEmployee(), this.getArrayListLogin());
			return true;
		}
		return false;
	}
}