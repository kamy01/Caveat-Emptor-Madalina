package beans;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.TreeNode;

import com.dtos.CategoriesDTO;
import com.dtos.ItemsDTO;

import ServiceImplementation.Transformation;
import ServiceInterfaces.ItemsService;
import exceptions.CaveatEmptorException;
import utils.Constants;
import utils.FacesMessagesUtil;
import utils.ItemsOption;


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
	List<CategoriesDTO> categoriesList;
	CategoriesDTO categoryDto;
			
	@EJB
	ItemsService itemsService;
	
	
	private Map<String, String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap(); 
	public final String userIdParameter = params.get("userId");
	
	@ManagedProperty("#{contentCategory}")
	private ContentCategoryBean contentCategory;
	
	@PostConstruct
	public void init() throws CaveatEmptorException {
		context= RequestContext.getCurrentInstance();
		categoryDto=new CategoriesDTO();
		itemDto=new ItemsDTO();
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

	public List<CategoriesDTO> getCategoriesList() {
		return categoriesList;
	}


	public void setCategoriesList(List<CategoriesDTO> categoriesList) {
		this.categoriesList = categoriesList;
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
			renderedMyBid=true;
			optionDropDown="buy";
			context= RequestContext.getCurrentInstance();
			itemDto=new ItemsDTO();
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
		context.execute("PF('dlgChooseCategory').show()");
		 itemDto =(ItemsDTO) event.getObject();
		if(contentCategory.getCategoriesDto().getNameCategory()!=itemDto.getCategories()){
			itemDto.setCategories(contentCategory.getCategoriesDto().getNameCategory());
		}
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
				Constants.getLogger().log( Level.INFO, "Exception in updateItem method from ItemsBean" ,e.getMessage());				
			}
	 }
	 public void delete(ItemsDTO item) throws ParseException{
		 
	
		 itemDto= Transformation.populateItemDto(item);
		 RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgVarDelete').show();");
	 }
	 public void insertItem() throws CaveatEmptorException{
		 try{
		 itemsService.insertItem(itemDto);
		 FacesMessagesUtil.message_info("Item "+itemDto.getName()+" was deleted!", "");
		 }catch(Exception e){
			 FacesMessagesUtil.message_info("Item "+itemDto.getName()+" wasn't deleted!Try again!", "");
				Constants.getLogger().log( Level.INFO, "Exception in deleteItem method from ItemsBean" ,e.getMessage());				
			}
	 }
	 
	 public void editCategory(){
		
		 FacesMessagesUtil.message_info("Category "+contentCategory.getCategoriesDto().getNameCategory(), "");

	 }
	 
	 public void changeCategory(){
		 RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgChooseCategory').show();");
	 }
	 
	
}
