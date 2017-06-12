package com.dtos;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;

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
	private Date biddingStartDate;
	private Date biddingEndDate;
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
		return formatDouble(initialPrice);
	}
	public void setInitialPrice(Double initialPrice) {
		this.initialPrice = initialPrice;
	}
	public Double getBestBid() throws ParseException {
		return formatDouble(bestBid);
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

	public Date getBiddingStartDate() throws ParseException {
		return new Date(biddingStartDate.getTime());
		
	}
	public void setBiddingStartDate(Date biddingStartDate) {
		this.biddingStartDate = biddingStartDate;
	}
	
	public Date getBiddingEndDate() throws ParseException {
		return new Date(biddingEndDate.getTime());
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
	public Double getYourBid() throws ParseException {
		return formatDouble(yourBid);
	}
	public void setYourBid(Double yourBid) {
		this.yourBid = yourBid;
	}
	public static Double formatDouble(Double value) throws ParseException{
		DecimalFormat df = new DecimalFormat(".##");
		df.setRoundingMode(RoundingMode.DOWN);
		
		if(value!=null){
		if(value % 1 == 0 || (value.toString().contains("0.00") ))
		{
			return value;
		}else{
				String valueString = df.format(value); 
				return (Double) df.parse(valueString);
			}
		}
		else{
			return null;
		}
	}
}
