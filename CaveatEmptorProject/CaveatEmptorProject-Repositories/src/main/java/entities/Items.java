package entities;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import RepositoryConstants.QueryConstants;

@Entity
@NamedQueries({ @NamedQuery(name = QueryConstants.GET_ITEMS_TO_SELL, query = QueryConstants.GET_ITEMS_TO_SELL_QUERY),
				@NamedQuery(name = QueryConstants.GET_ITEMS_TO_BUY, query = QueryConstants.GET_ITEMS_TO_BUY_QUERY),
				@NamedQuery(name = QueryConstants.GET_MAX_ITEMID, query = QueryConstants.GET_MAX_ITEMID_QUERY)})
public class Items implements Serializable {

	private static final long serialVersionUID = 352144983391426538L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;

	@Column
	private Long userId;
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private String categories;
	@Column
	private Double initialPrice;
	@Column
	private Double bestBid;
	@Column
	private Double yourBid;
	@Column
	private int nrBids;
	@Column
	private Date biddingStartDate;
	@Column
	private Date biddingEndDate;
	@Column
	private String status;
	@Column
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

	
	public Date getBiddingStartDate() {
		return new Date(biddingStartDate.getTime());
		
		
	}

	public void setBiddingStartDate(Date biddingStartDate) {
		this.biddingStartDate = biddingStartDate;
	}


	public Date getBiddingEndDate() {
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
