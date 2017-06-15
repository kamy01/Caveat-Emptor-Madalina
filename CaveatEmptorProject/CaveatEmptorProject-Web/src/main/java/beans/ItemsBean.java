package beans;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import com.dtos.CategoriesDTO;
import com.dtos.ItemsDTO;

import ServiceImplementation.Transformation;
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
	ItemsDTO itemDto,itemToUpdate;
	private String optionDropDown;
	private String statusChange;
	private boolean renderedMyBid;
	private boolean renderedEditButton;
	
	private boolean renderedInsertButton;
	private String nameInsertButton;

			
	@EJB
	ItemsService itemsService;
	
	List<String> category;
	
	private Map<String, String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap(); 
	private final String userIdParameter = params.get("userId");
	
	@ManagedProperty("#{contentCategory}")
	private ContentCategoryBean contentCategory;

	RequestContext context ;
	
public void initDto(){
	renderedInsertButton=true;
	nameInsertButton="Insert";
	itemDto=new ItemsDTO();
	itemDto.setBiddingStartDate(new Date());
	itemDto.setBiddingEndDate(new Date());
	itemDto.setInitialPrice(0D);
	itemDto.setBestBid(null);
	itemDto.setWinner("");
	itemDto.setNrBids(0);
	itemDto.setUserId(Long.parseLong(userIdParameter));
}


	@PostConstruct
	public void init() throws CaveatEmptorException {
		itemDto=new ItemsDTO();
		context = RequestContext.getCurrentInstance();
		category=new ArrayList<>();
		category=itemsService.getCategoriesNames();
		initDto();
		renderedEditButton=true;
		itemsListDto = new ArrayList<>();	
		optionDropDown="sell";
		renderedMyBid=false;
		contentCategory.getCategoryTree().init();
		itemsListDto=itemsService.getItemsToSell(Long.parseLong(userIdParameter));
		for (ItemsDTO item : itemsListDto) {
			statusChange=item.getStatus();
			if(item.getStatus().equals("closed") || item.getStatus().equals("not yet open")){
				item.setRenderedEdit(false);
			}
			else{
				item.setRenderedEdit(true);
				//item.setStatus(statusChange);
				//itemDto=item;
			}
		}
	}
	
	public String getStatusChange() {
		return statusChange;
	}
	public void setStatusChange(String statusChange) {
		this.statusChange = statusChange;
	}
	public boolean isRenderedInsertButton() {
		return renderedInsertButton;
	}
	public void setRenderedInsertButton(boolean renderedInsertButton) {
		this.renderedInsertButton = renderedInsertButton;
	}
	public String getNameInsertButton() {
		return nameInsertButton;
	}
	public void setNameInsertButton(String nameInsertButton) {
		this.nameInsertButton = nameInsertButton;
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

	public boolean isRenderedMyBid() {
		return renderedMyBid;
	}
	public void setRenderedMyBid(boolean renderedMyBid) {
		this.renderedMyBid = renderedMyBid;
	}

	public void onStatusChange(){
		switch(statusChange.toLowerCase()){
		case "open":statusChange="open"; break;
		case "closed":statusChange="closed"; break;
		case "abandoned":statusChange="abandoned"; break;
		}
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
			itemsListDto=itemsService.getItemsToBuy(Long.parseLong(userIdParameter));
		}
		else{
			itemsListDto = new ArrayList<>();	
		}
	}
	
//	public void hideDialog(){
//		RequestContext context = RequestContext.getCurrentInstance();
//		context.execute("PF('dialogValidateDates').hide();");
//	}
	 public void onRowEdit(RowEditEvent event) throws ParseException, CaveatEmptorException {
		RequestContext context = RequestContext.getCurrentInstance();
		itemToUpdate=new ItemsDTO();
		itemDto =(ItemsDTO) event.getObject();
		itemDto.setUserId(Long.parseLong(userIdParameter));
		itemDto.setStatus(statusChange);
		itemToUpdate=Transformation.populateItemDto(itemDto);
		 if(itemToUpdate.getBiddingStartDate().getTime() > itemToUpdate.getBiddingEndDate().getTime()){
			 FacesMessagesUtil.message_info("Closing date must be bigger than opening date!", "");
			 init();
			// context.execute("PF('dialogValidateDates').show();");
			// context.execute("PF('dlgVarEdit').hide();");
			// context.update("tabView:form:dataTableItems:dialogEdit");
		}else{
			context.execute("PF('dlgVarEdit').show();");
		}

	 }
	 public void onRowCancel(RowEditEvent event) {
	 }
	 
	 public void cancelUpdateItem() throws CaveatEmptorException{
		 init();
	 }

	 public void updateItem() throws CaveatEmptorException{
		 try{
			 	 itemsService.updateItem(itemToUpdate);
				 FacesMessagesUtil.message_info("Item "+itemToUpdate.getName()+" was edited!", "");
				 itemDto=Transformation.populateItemDto(itemToUpdate);
			
			 init();
		 }catch(Exception e){
			 	FacesMessagesUtil.message_info("Item "+itemToUpdate.getName()+" wasn't edited!Try again!", "");
			 	init();
			 	LoggerUtils.getLogger().log( Level.INFO, "Exception in updateItem method from ItemsBean" ,e.getMessage());				
			}
	 }
	 
	 public void cancelInsert()throws CaveatEmptorException{
		 initDto();
	 }

	 public void insert() throws CaveatEmptorException{
		 try{
			 Long maxItemId = itemsService.getMaxItemId();
			 itemDto.setUserId(Long.parseLong(userIdParameter));
			 itemDto.setItemId(maxItemId+1);
			 itemDto.setStatus("open");

			 if(!contentCategory.IsAnyNodeSelected()){
				 FacesMessagesUtil.message_error("Select a category!", "");
			 }else{
			 CategoriesDTO category=(CategoriesDTO) contentCategory.getCategoryTree().getSelectedNode().getData();
			 
			 itemDto.setCategories(category.getNameCategory());
			 
			 if(itemDto.getBiddingEndDate().getTime() < itemDto.getBiddingStartDate().getTime()){
				 FacesMessagesUtil.message_error("Item "+itemDto.getName()+" wasn't inserted!Closing date must be bigger than opening date", "");
			 }
			 else if(itemDto.getName().isEmpty() || itemDto.getName()==null)
			 {
				 FacesMessagesUtil.message_error("Item wasn't inserted!Column name is required", "");

			 }
			 else if(itemDto.getDescription().isEmpty() || itemDto.getDescription()==null)
			 {
				 FacesMessagesUtil.message_error("Item wasn't inserted!Column description is required", "");

			 }
			 else if(itemDto.getInitialPrice()==null || itemDto.getInitialPrice().equals(""))
			 {
				 FacesMessagesUtil.message_error("Column Initial price is required", "");

			 }
			 else{
			 nameInsertButton="Save";
			 itemsService.insertItem(itemDto);
			 RequestContext context = RequestContext.getCurrentInstance();
			 context.execute("PF('itemDialog').hide();");
			 FacesMessagesUtil.message_info("Item "+itemDto.getName()+" was inserted!", "");
			 init();
			 initDto();
			 }
			    
			 }
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
