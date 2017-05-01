package br.com.novaroma.rcinfo.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import br.com.novaroma.rcinfo.dao.DaoSell;
import br.com.novaroma.rcinfo.entities.Sell;
import br.com.novaroma.rcinfo.exceptions.EmptyFieldException;
import br.com.novaroma.rcinfo.exceptions.ExistingEntityException;
import br.com.novaroma.rcinfo.exceptions.InvalidCharactersFieldException;
import br.com.novaroma.rcinfo.facade.IFacade;

public class SellController implements IFacade {
	private DaoSell repSell = new DaoSell();
	private Map<String, Sell> mapaSell;

	public void readSerializedFile() throws Exception {
		this.setMapaSell(this.getRepSell().readSerializedFile());
	}

	public Sell simpleReadSell(String protocol) throws EmptyFieldException, InvalidCharactersFieldException {
		if (protocol.equals("")) {
			throw new EmptyFieldException("Preencha o campo Protocolo");
		} else if (!protocol.replaceAll("[0-9]", "").equals("")) {
			throw new InvalidCharactersFieldException("Campo Protocolo deve conter apenas números");
		}
		return this.getMapaSell().containsKey(protocol) ? this.getMapaSell().get(protocol) : null;
	}

	public String[][] advancedRead(Calendar data, String cpf) throws EmptyFieldException {
		String[][] advancedMatriz = null;

		if (data == null) {
			throw new EmptyFieldException("Campo data está vazio");
		} else if (cpf.replaceAll("[_.-]", "").equals("")) {
			throw new EmptyFieldException("Campo cpf está vazio");
		} else {
			final ArrayList<Sell> arrayListSell = new ArrayList<>();
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat dfWithHour = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			for (Sell sell : getMapaSell().values()) {
				if (df.format(sell.getDataVenda().getTime()).equals(df.format(data.getTime())) && sell.getCpfCliente().equals(cpf)) {
					arrayListSell.add(sell);
				}
			}
			if (arrayListSell.size() > 0) {
				advancedMatriz = new String[arrayListSell.size()][4];
				for (int i = 0; i < advancedMatriz.length; i++) {
					advancedMatriz[i][0] = arrayListSell.get(i).getProtocolo();
					advancedMatriz[i][1] = arrayListSell.get(i).getCpfCliente();
					advancedMatriz[i][2] = dfWithHour.format(arrayListSell.get(i).getDataVenda().getTime());
					advancedMatriz[i][3] = arrayListSell.get(i).getTotal();
				}
			} else {
				throw new EmptyFieldException("Nenhuma venda registrada com estes dados");
			}
		}
		return advancedMatriz;
	}

	@Override
	public boolean create(Object o) throws Exception {
		Sell sell = (Sell) o;
		if (sell.getProdutos().length == 0) {
			throw new EmptyFieldException("Adicione algum produto");
		} else if (this.getMapaSell().containsKey(sell.getProtocolo())) {
			throw new ExistingEntityException("Protocolo já existe");
		} else {
			this.getMapaSell().put(sell.getProtocolo(), sell);
			this.getRepSell().writeSerialized(this.getMapaSell());
			return true;
		}
	}

	@Override
	public boolean update(Object o) throws Exception {
		Sell sell = (Sell) o;
		if (this.getMapaSell().containsKey(sell.getProtocolo())) {
			this.getMapaSell().put(sell.getProtocolo(), sell);
			this.getRepSell().writeSerialized(this.getMapaSell());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(Object o) throws Exception {
		Sell product = (Sell) o;
		if (this.getMapaSell().containsKey(product.getProtocolo())) {
			this.getMapaSell().remove(product.getProtocolo());
			this.getRepSell().writeSerialized(this.getMapaSell());
			return true;
		} else {
			System.out.println("Deu flase 11111");
			return false;
		}
	}

	public DaoSell getRepSell() {
		return repSell;
	}

	public void setRepSell(DaoSell repSell) {
		this.repSell = repSell;
	}

	public Map<String, Sell> getMapaSell() {
		return mapaSell;
	}

	public void setMapaSell(Map<String, Sell> productMap) {
		this.mapaSell = productMap;
	}

}
