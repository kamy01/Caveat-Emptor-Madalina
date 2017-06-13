package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.dtos.ItemsDTO;
import ServiceInterfaces.ItemsService;
import exceptions.CaveatEmptorException;
import utils.FacesMessagesUtil;
import utils.ItemsOption;
import utils.LoggerUtils;


@ManagedBean(name = "itemsBean")
@ViewScoped
public class ItemsBean  implements Serializable{
	private static final long serialVersionUID = -5604086696813201615L;
	
	private Long userId;
	private List<ItemsDTO> itemsListDto;
	ItemsDTO itemDto;
	private Long currentRow;
	private String optionDropDown;
	private Map<String, String> dropDownItems;
	private boolean renderedMyBid;
	private boolean renderedEditButton;
	RequestContext context;
			
	@EJB
	ItemsService itemsService;
	
	List<String> category;
	
	private Map<String, String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap(); 
	private final String userIdParameter = params.get("userId");
	
	@ManagedProperty("#{contentCategory}")
	private ContentCategoryBean contentCategory;
	
public void initDto(){
	itemDto=new ItemsDTO();
	itemDto.setName("");
	itemDto.setBiddingStartDate(new Date());
	itemDto.setBiddingEndDate(new Date());
	itemDto.setInitialPrice(0D);
	itemDto.setBestBid(null);
	itemDto.setCategories(" ");
	itemDto.setNrBids(0);
	itemDto.setUserId(Long.parseLong(userIdParameter));
}
	@PostConstruct
	public void init() throws CaveatEmptorException {
		
		context= RequestContext.getCurrentInstance();
		category=new ArrayList<>();
		category=itemsService.getCategoriesNames();
		initDto();
		renderedEditButton=true;
		itemsListDto = new ArrayList<>();	
		dropDownItems=new HashMap<>();
		optionDropDown="sell";
		renderedMyBid=false;
		contentCategory.getCategoryTree().init();
		itemsListDto=itemsService.getItemsToSell(Long.parseLong(userIdParameter));
		for (ItemsDTO item : itemsListDto) {
			if(item.getStatus().equals("closed")){
				item.setRenderedEdit(false);
			}
			else{
				item.setRenderedEdit(true);
			}
		}
	}

	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
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
	public Long getCurrentRow() {
		return currentRow;
	}
	public void setCurrentRow(Long currentRow) {
		this.currentRow = currentRow;
	}
	
	public ContentCategoryBean getContentCategory() {
		return contentCategory;
	}


	public void setContentCategory(ContentCategoryBean contentCategory) {
		this.contentCategory = contentCategory;
	}


	public List<ItemsDTO> getItemsListDto() {
		return itemsListDto;
	}
	public void setItemsListDto(List<ItemsDTO> itemsListDto) {
		this.itemsListDto=itemsListDto;
	}
	
	
	public ItemsDTO getItemDto() {
		return itemDto;
	}


	public void setItemDto(ItemsDTO itemDto) {
		this.itemDto = itemDto;
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
			init();

		} else if (optionDropDown.toLowerCase().equals(ItemsOption.BUY.getValue())) {
			initDto();
			renderedMyBid=true;
			optionDropDown="buy";
			context= RequestContext.getCurrentInstance();
			category=new ArrayList<>();
			category=itemsService.getCategoriesNames();
			renderedEditButton=true;
			itemsListDto = new ArrayList<>();	
			dropDownItems=new HashMap<>();
			itemsListDto=itemsService.getItemsToBuy(Long.parseLong(userIdParameter));
			for (ItemsDTO item : itemsListDto) {
				if(item.getStatus().equals("closed")){
					item.setRenderedEdit(false);
				}
				else{
					item.setRenderedEdit(true);
				}
			}
		}
		else{
			itemsListDto = new ArrayList<>();	
		}
	}
	
	
	 public void onRowEdit(RowEditEvent event) {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgVarEdit').show();");
		itemDto =(ItemsDTO) event.getObject();
		itemDto.setUserId(Long.parseLong(userIdParameter));
	 }
	 public void onRowCancel(RowEditEvent event) {
	 }

	 public void updateItem() throws CaveatEmptorException{
		 try{
			 	 itemsService.updateItem(itemDto);
				 FacesMessagesUtil.message_info("Item "+itemDto.getName()+" was edited!", "");
		 }catch(Exception e){
			 	FacesMessagesUtil.message_info("Item "+itemDto.getName()+" wasn't edited!Try again!", "");
			 	LoggerUtils.getLogger().log( Level.INFO, "Exception in updateItem method from ItemsBean" ,e.getMessage());				
			}
	 }

	 public void insert() throws CaveatEmptorException{
		 try{
			 Long maxItemId = itemsService.getMaxItemId();
			 itemDto.setUserId(Long.parseLong(userIdParameter));
			 itemDto.setItemId(maxItemId+1);
			 itemDto.setStatus("open");
			 
			 itemsService.insertItem(itemDto);
			 RequestContext.getCurrentInstance().update(":tabView:frmDropDown:frmContentItems:dataTableItems");
			    
			 FacesMessagesUtil.message_info("Item "+itemDto.getName()+" was inserted!", "");
			 }catch(Exception e){
				 FacesMessagesUtil.message_info("Item "+itemDto.getName()+" wasn't inserted!Try again!", "");
				 LoggerUtils.getLogger().log( Level.INFO, "Exception in insertItem method from ItemsBean" ,e.getMessage());				
				}
	 }
	 public void insertItem() throws CaveatEmptorException{
		 RequestContext context = RequestContext.getCurrentInstance();
		 context.execute("PF('itemDialog').show();");
		
	 }
}
