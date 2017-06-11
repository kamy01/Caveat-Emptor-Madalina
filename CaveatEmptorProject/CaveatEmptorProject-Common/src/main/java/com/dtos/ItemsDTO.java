package com.dtos;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import utils.Constants;

public class ItemsDTO implements Serializable{

	private static final long serialVersionUID = 352144983391426538L;
	private Long itemId;
	private Long userId;
	private String name;
	private String description;
	private String categories;
	private Double initialPrice;
	private Double bestBid;
	private Double yourBid;
	private int nrBids;
	//private Date biddingStartDate;
	//private Date biddingEndDate;
	private Timestamp biddingStartDate;
	private Timestamp biddingEndDate;
	private String status;
	private String winner;
	
	private boolean renderedEdit;
	
	
	public boolean isRenderedEdit() {
		return renderedEdit;
	}
	public void setRenderedEdit(boolean renderedEdit) {
		this.renderedEdit = renderedEdit;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategories() {
		return categories;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	public Double getInitialPrice() throws ParseException {
		return Constants.formatDouble(initialPrice);
	}
	public void setInitialPrice(Double initialPrice) {
		this.initialPrice = initialPrice;
	}
	public Double getBestBid() throws ParseException {
		return Constants.formatDouble(bestBid);
	}
	public void setBestBid(Double bestBid) {
		this.bestBid = bestBid;
	}
	public int getNrBids() {
		return nrBids;
	}
	public void setNrBids(int nrBids) {
		this.nrBids = nrBids;
	}

	public Timestamp getBiddingStartDate() throws ParseException {
		return new Timestamp(biddingStartDate.getTime());
		
	}
	public void setBiddingStartDate(Timestamp biddingStartDate) {
		this.biddingStartDate = biddingStartDate;
	}
	
	public Timestamp getBiddingEndDate() throws ParseException {
		return new Timestamp(biddingEndDate.getTime());
	}
	public void setBiddingEndDate(Timestamp biddingEndDate) {
		this.biddingEndDate = biddingEndDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public Double getYourBid() throws ParseException {
		return Constants.formatDouble(yourBid);
	}
	public void setYourBid(Double yourBid) {
		this.yourBid = yourBid;
	}

}
