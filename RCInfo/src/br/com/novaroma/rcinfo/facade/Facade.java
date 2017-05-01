package br.com.novaroma.rcinfo.facade;

import java.util.Calendar;
import java.util.Map;

import br.com.novaroma.rcinfo.business.ClientController;
import br.com.novaroma.rcinfo.business.EmployeeController;
import br.com.novaroma.rcinfo.business.ProductController;
import br.com.novaroma.rcinfo.business.SellController;
import br.com.novaroma.rcinfo.entities.Client;
import br.com.novaroma.rcinfo.entities.Employee;
import br.com.novaroma.rcinfo.entities.Product;
import br.com.novaroma.rcinfo.entities.Sell;
import br.com.novaroma.rcinfo.exceptions.EmptyFieldException;
import br.com.novaroma.rcinfo.exceptions.ExistingEntityException;
import br.com.novaroma.rcinfo.exceptions.InvalidAgeException;
import br.com.novaroma.rcinfo.exceptions.InvalidCharactersFieldException;
import br.com.novaroma.rcinfo.exceptions.InvalidEmailException;
import br.com.novaroma.rcinfo.exceptions.InvalidLoginException;
import br.com.novaroma.rcinfo.exceptions.NotFoundException;;

public class Facade implements IFacade {

	private EmployeeController employeeController = new EmployeeController();

	private ProductController productController = new ProductController();

	private ClientController clientController = new ClientController();

	private SellController sellController = new SellController();

	public void readSerializedFile() throws Exception {
		this.getProductController().readSerializedFile();
		this.getEmployeeController().readSerializedFile();
		this.getClientController().readSerializedFile();
		this.getSellController().readSerializedFile();
	}

	public boolean autenticarLogin(int loginComboBox, String code, String password)
			throws InvalidLoginException, EmptyFieldException, InvalidCharactersFieldException {
		return this.getEmployeeController().autenticarLogin(loginComboBox, code, password);
	}

	public SellController getSellController() {
		return sellController;
	}

	public void setSellController(SellController sellController) {
		this.sellController = sellController;
	}

	public EmployeeController getEmployeeController() {
		return employeeController;
	}

	public void setEmployeeController(EmployeeController sellerController) {
		this.employeeController = sellerController;
	}

	public ProductController getProductController() {
		return productController;
	}

	public void setProductController(ProductController stockController) {
		this.productController = stockController;
	}

	public ClientController getClientController() {
		return clientController;
	}

	public void setClientController(ClientController clientController) {
		this.clientController = clientController;
	}

	public Map<Integer, String[]> getArrayListLogin() {
		return this.getEmployeeController().getArrayListLogin();
	}

	public Map<String, Employee> getMapEmployee() {
		return this.getEmployeeController().getMapaEmployee();
	}

	public Map<String, Client> getMapClient() {
		return this.getClientController().getMapaClient();
	}

	public Map<String, Product> getMapProduct() {
		return this.getProductController().getMapaProduct();
	}

	public Map<String, Sell> getMapSell() {
		return this.getSellController().getMapaSell();
	}

	@Override
	public boolean create(Object o) throws ExistingEntityException, EmptyFieldException, InvalidCharactersFieldException, InvalidAgeException, InvalidEmailException, Exception {
		return o instanceof Product && this.getProductController().create(o)
				|| o instanceof Client && this.getClientController().create(o)
				|| o instanceof Sell && this.getSellController().create(o);
	}

	public boolean createEmployee(Employee o, String password) throws NumberFormatException, ExistingEntityException,
			EmptyFieldException, InvalidCharactersFieldException, InvalidAgeException, Exception {
		return this.getEmployeeController().createEmployee(o, password);
	}

	public String[][] advancedRead(Calendar data, String cpf) throws EmptyFieldException {
		return getSellController().advancedRead(data, cpf);
	}

	public Client simpleReadClient(String cpf) throws EmptyFieldException, NotFoundException {
		return this.getClientController().simpleReadClient(cpf);
	}

	public Employee simpleReadEmployee(String code) throws EmptyFieldException, InvalidCharactersFieldException, NotFoundException {
		return this.getEmployeeController().simpleReadEmployee(code);
	}

	public Product simpleReadProduct(String code) throws EmptyFieldException, InvalidCharactersFieldException {
		return this.getProductController().simpleReadProduct(code);
	}

	public Sell simpleReadSell(String protocolo) throws EmptyFieldException, InvalidCharactersFieldException {
		return this.getSellController().simpleReadSell(protocolo);
	}

	public boolean updateEmployee(Employee employee, String password) throws Exception {
		return this.getEmployeeController().updateEmployee(employee, password);
	}

	@Override
	public boolean update(Object o) throws InvalidCharactersFieldException, EmptyFieldException, InvalidAgeException,
			InvalidEmailException, Exception {
		return o instanceof Product && this.getProductController().update(o)
				|| o instanceof Client && this.getClientController().update(o)
				|| o instanceof Employee && this.getEmployeeController().update(o)
				|| o instanceof Sell && this.getSellController().update(o);
	}

	@Override
	public boolean delete(Object o) throws Exception {
		return o instanceof Employee && this.getEmployeeController().delete(o)
				|| o instanceof Client && this.getClientController().delete(o)
				|| o instanceof Product && this.getProductController().delete(o)
				|| o instanceof Sell && this.getSellController().delete(o);
	}
}