package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.EditableValueHolder;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.dtos.ItemsDTO;
import ServiceInterfaces.ItemsService;
import exceptions.CaveatEmptorException;
import utils.ItemsOption;


@ManagedBean(name = "itemsBean")
@ViewScoped
public class ItemsBean  implements Serializable{
	private static final long serialVersionUID = -5604086696813201615L;
	
	private Long userId;
	private List<ItemsDTO> itemsListDto;
	private ItemsDTO itemDto;
	private Long currentRow;
	private String optionDropDown;
	private Map<String, String> dropDownItems;
	private boolean renderedMyBid;
	private boolean renderedEditButton=true;
	
			
	@EJB
	ItemsService itemsService;
	
	@Inject
	@ManagedProperty("#{userlogin}")
	private UserLoginBean user;
	private Map<String, String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap(); 
	String userIdParameter = params.get("userId");
	
	@ManagedProperty("#{treeBean}")
	TreeBean tree;
	
	@PostConstruct
	public void init() throws CaveatEmptorException {
		renderedEditButton=true;
		itemsListDto = new ArrayList<>();	
		itemDto=new ItemsDTO();
		dropDownItems=new HashMap<>();
		optionDropDown="sell";
		renderedMyBid=false;
		itemsListDto=itemsService.getItemsToSell(Long.parseLong(userIdParameter));
//		for (ItemsDTO item : itemsListDto) {
//			if(item.getStatus().equals("closed")){
//				renderedEditButton=false;
//			}
//		}
	}

	
	
	public boolean isRenderedEditButton() {
		return renderedEditButton;
	}
	public void setRenderedEditButton(boolean renderedEditButton) {
		this.renderedEditButton = renderedEditButton;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getOptionDropDown() {
		return optionDropDown;
	}
	public void setOptionDropDown(String optionDropDown) {
		this.optionDropDown = optionDropDown;
	}
	public ItemsDTO getItemDto() {
		return itemDto;
	}
	public void setItemDto(ItemsDTO itemDto) {
		this.itemDto = itemDto;
	}
	public Long getCurrentRow() {
		return currentRow;
	}
	public void setCurrentRow(Long currentRow) {
		this.currentRow = currentRow;
	}
	public UserLoginBean getUser() {
		return user;
	}
	public void setUser(UserLoginBean user) {
		this.user = user;
	}
	public TreeBean getTree() {
		return tree;
	}
	public void setTree(TreeBean tree) {
		this.tree = tree;
	}
	public List<ItemsDTO> getItemsListDto() {
		return itemsListDto;
	}
	public void setItemsListDto(List<ItemsDTO> itemsListDto) {
		this.itemsListDto=itemsListDto;
	}
	
	public Map<String, String> getDropDownItems() {
		return dropDownItems;
	}
	public void setDropDownItems(Map<String, String> dropDownItems) {
		this.dropDownItems = dropDownItems;
	}
	
	public boolean isRenderedMyBid() {
		return renderedMyBid;
	}
	public void setRenderedMyBid(boolean renderedMyBid) {
		this.renderedMyBid = renderedMyBid;
	}



	public void onDropDownChange() throws CaveatEmptorException {
		if (optionDropDown.toLowerCase().equals(ItemsOption.SELL.getValue())) {
			renderedMyBid=false;
			itemsListDto=itemsService.getItemsToSell(Long.parseLong(userIdParameter));

		} else if (optionDropDown.toLowerCase().equals(ItemsOption.BUY.getValue())) {
			renderedMyBid=true;
			itemsListDto=itemsService.getItemsToBuy(Long.parseLong(userIdParameter));
		}
		else{
			itemsListDto = new ArrayList<>();	
		}
	}
	
	 public void onRowEdit(RowEditEvent event) {
		 	itemDto=(ItemsDTO) event.getObject();
	    }
	     
	    public void onRowCancel(RowEditEvent event) {
	     
	    }

}
