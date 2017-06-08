package entities;

import java.io.Serializable;
import java.sql.Timestamp;
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
				@NamedQuery(name = QueryConstants.GET_ITEMS_TO_BUY, query = QueryConstants.GET_ITEMS_TO_BUY_QUERY) })
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
	private double initialPrice;
	@Column
	private double bestBid;
	@Column
	private Double yourBid;
	@Column
	private int nrBids;
	@Column
	private Timestamp biddingStartDate;
	@Column
	private Timestamp biddingEndDate;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}