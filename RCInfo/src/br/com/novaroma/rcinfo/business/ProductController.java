package br.com.novaroma.rcinfo.business;

import java.util.Map;

import br.com.novaroma.rcinfo.dao.IDaoProduct;
import br.com.novaroma.rcinfo.dao.DaoProduct;
import br.com.novaroma.rcinfo.entities.Product;
import br.com.novaroma.rcinfo.exceptions.EmptyFieldException;
import br.com.novaroma.rcinfo.exceptions.ExistingEntityException;
import br.com.novaroma.rcinfo.exceptions.InvalidCharactersFieldException;
import br.com.novaroma.rcinfo.facade.IFacade;

public class ProductController implements IFacade {

	private IDaoProduct irp = new DaoProduct();
	private Map<String, Product> productMap;

	public Product simpleReadProduct(String code) throws EmptyFieldException, InvalidCharactersFieldException {
		if (code.equals("")) {
			throw new EmptyFieldException("Preencha o campo Código");
		} else if (!code.replaceAll("[0-9]", "").equals("")) {
			throw new InvalidCharactersFieldException("Campo código deve conter apenas números");
		}
		return this.getMapaProduct().containsKey(code) ? this.getMapaProduct().get(code) : null;
	}
	
	public void readSerializedFile() throws Exception {
		this.setMapaProduct(this.getIrp().readSerializedFile());
	}

	public IDaoProduct getIrp() {
		return irp;
	}

	public void setIrp(IDaoProduct irp) {
		this.irp = irp;
	}

	public Map<String, Product> getMapaProduct() {
		return productMap;
	}

	public void setMapaProduct(Map<String, Product> productMap) {
		this.productMap = productMap;
	}

	@Override
	public boolean create(Object o) throws Exception {
		Product product = (Product) o;
		if (checkExistingProduct(product)) {
			throw new ExistingEntityException("Produto já cadastrado anteriorente");
		} else if (validateProduct(product)) {
			this.getMapaProduct().put(product.getCode(), product);
			this.getIrp().writeSerialized(this.getMapaProduct());
			return true;
		}
		return false;
	}

	private boolean validateProduct(Product product) throws InvalidCharactersFieldException, EmptyFieldException {
		if (product.getType().equalsIgnoreCase("Tipo do produto")) {
			throw new EmptyFieldException("Defina o tipo do produto");
		} else if (checkEmptyFields(product)) {
			throw new EmptyFieldException("Preencha os campos vazios");
		} else if (!product.getPrice().replaceAll("[0-9.]", "").equals("")) {
			throw new InvalidCharactersFieldException("Campo Preço deve conter apenas números");
		} else if (!product.getAmount().replaceAll("[0-9]", "").equals("")) {
			throw new InvalidCharactersFieldException("Campo Quantidade deve conter apenas números");
		} else if(checkExistingProduct(product)){
			
		}
		return true;

	}

	private boolean checkEmptyFields(Product product) {
		return product.getCode().equals("") || product.getManufacturer().equals("") || product.getModel().equals("") || product.getPrice().equals("");
	}

	private boolean checkExistingProduct(Product product) {
		return this.getMapaProduct().containsKey(product.getCode());
	}

	@Override
	public boolean update(Object o) throws InvalidCharactersFieldException, EmptyFieldException, Exception  {
		Product product = (Product) o;
		if (checkExistingProduct(product) && validateProduct(product)) {
			this.getMapaProduct().put(product.getCode(), product);
			this.getIrp().writeSerialized(this.getMapaProduct());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(Object o) throws Exception {
		Product product = (Product) o;
		if (checkExistingProduct(product)) {
			this.getMapaProduct().remove(product.getCode());
			this.getIrp().writeSerialized(this.getMapaProduct());
			return true;
		} else {
			System.out.println("Deu flase 11111");
			return false;
		}
	}

}