package com.dtos;

import java.io.Serializable;
import java.util.Date;

public class ItemsDTO implements Serializable{

	private static final long serialVersionUID = 352144983391426538L;
	private Long itemId;
	private String name;
	private String description;
	private String categories;
	private double initialPrice;
	private double bestBid;
	private Double yourBid;
	private int nrBids;
	private Date biddingStartDate;
	private Date biddingEndDate;
	private String status;
	private String winner;
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
	public void setCategories(String categories) {
		this.categories = categories;
	}
	public double getInitialPrice() {
		return initialPrice;
	}
	public void setInitialPrice(double initialPrice) {
		this.initialPrice = initialPrice;
	}
	public double getBestBid() {
		return bestBid;
	}
	public void setBestBid(double bestBid) {
		this.bestBid = bestBid;
	}
	public int getNrBids() {
		return nrBids;
	}
	public void setNrBids(int nrBids) {
		this.nrBids = nrBids;
	}
	public Date getBiddingStartDate() {
		return biddingStartDate;
	}
	public void setBiddingStartDate(Date biddingStartDate) {
		this.biddingStartDate = biddingStartDate;
	}
	public Date getBiddingEndDate() {
		return biddingEndDate;
	}
	public void setBiddingEndDate(Date biddingEndDate) {
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
	public Double getYourBid() {
		return yourBid;
	}
	public void setYourBid(Double yourBid) {
		this.yourBid = yourBid;
	}
	
	
	
	
}
