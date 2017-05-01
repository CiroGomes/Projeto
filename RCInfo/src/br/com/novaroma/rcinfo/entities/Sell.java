package br.com.novaroma.rcinfo.entities;

import java.io.Serializable;
import java.util.Calendar;

public class Sell implements Serializable {
	private static final long serialVersionUID = 1L;
	private String protocol;
	private String[][] products;
	private String clientCpf;
	private Calendar sellDate;
	private String total;
	private String subtotal;
 public Sell(){
	 
 }
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	
	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public Sell(String protocol, String[][] products, String clientCpf, Calendar sellDate, String subtotal, String total) {
		this.protocol = protocol;
		this.products = products;
		this.clientCpf = clientCpf;
		this.sellDate = sellDate;
		this.subtotal = subtotal;
		this.total = total;
	}

	public Calendar getDataVenda() {
		return sellDate;
	}

	public void setDataVenda(Calendar sellDate) {
		this.sellDate = sellDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getProtocolo() {
		return protocol;
	}

	public void setProtocolo(String protocol) {
		this.protocol = protocol;
	}

	public String[][] getProdutos() {
		return products;
	}

	public void setProdutos(String[][] products) {
		this.products = products;
	}

	public String getCpfCliente() {
		return clientCpf;
	}

	public void setCpfCliente(String clientCpf) {
		this.clientCpf = clientCpf;
	}

}
