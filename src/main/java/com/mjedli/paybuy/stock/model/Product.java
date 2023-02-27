package com.mjedli.paybuy.stock.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

@Document
public class Product {

    @Transient
    public static final String SEQUENCE_NAME = "stock_sequence";
	
	@Id
	@NonNull
	private long id;
	
	private String name = "";
	private String idProvider = "";
	private String amount = "";
	  
	private String buyingPrice = "";
	private String sellPrice = "";
	private String tva = "";
	private String profit = "";
	
	public String getTva() {
		return tva;
	}

	public void setTva(String tva) {
		this.tva = tva;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdProvider() {
		return idProvider;
	}

	public void setIdProvider(String idProvider) {
		this.idProvider = idProvider;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(String buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public String getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}
}
