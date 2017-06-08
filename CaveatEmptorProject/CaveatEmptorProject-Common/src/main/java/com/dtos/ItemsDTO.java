package com.dtos;

import java.io.Serializable;
import java.sql.Timestamp;
public class ItemsDTO implements Serializable{

	private static final long serialVersionUID = 352144983391426538L;
	private Long itemId;
	private Long userId;
	private String name;
	private String description;
	private String categories;
	private double initialPrice;
	private double bestBid;
	private Double yourBid;
	private int nrBids;
	private Timestamp biddingStartDate;
	private Timestamp biddingEndDate;
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
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public Timestamp getBiddingStartDate() {
		return new Timestamp(biddingStartDate.getTime());
	}
	public void setBiddingStartDate(Timestamp biddingStartDate) {
		this.biddingStartDate = biddingStartDate;
	}
	public Timestamp getBiddingEndDate() {
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
	public Double getYourBid() {
		return yourBid;
	}
	public void setYourBid(Double yourBid) {
		this.yourBid = yourBid;
	}

}
