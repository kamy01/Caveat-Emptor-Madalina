package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import com.dtos.ItemsDTO;
import ServiceInterfaces.ItemsService;
import exceptions.CaveatEmptorException;
import utils.ItemsOption;

@ManagedBean(name = "itemsToSell")
@ViewScoped
public class ItemsToSellBean  implements Serializable{
	private static final long serialVersionUID = -5604086696813201615L;
	
	
	
	private List<ItemsDTO> itemsListDto;
	private ItemsDTO itemDto;
	private Long currentRow;
	private boolean editedRow;
	
	
	private String optionDropDown;
	
	private Map<String, String> dropDownItems;
	
	@EJB
	ItemsService itemsService;
	
	@Inject
	@ManagedProperty("#{userlogin}")
	private UserLoginBean user;
	
	@ManagedProperty("#{treeBean}")
	TreeBean tree;
	
	@PostConstruct
	public void init() throws CaveatEmptorException {
		itemsListDto = new ArrayList<>();	
		itemDto=new ItemsDTO();
		dropDownItems=new HashMap<>();
		
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
	public boolean isEditedRow() {
		return editedRow;
	}
	public void setEditedRow(boolean editedRow) {
		this.editedRow = editedRow;
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

	public void getItems() throws CaveatEmptorException{
		itemsListDto=itemsService.getItemsToSell();
	}
	
	public Map<String, String> getDropDownItems() {
		return dropDownItems;
	}
	public void setDropDownItems(Map<String, String> dropDownItems) {
		this.dropDownItems = dropDownItems;
	}

	public void initializeDropDown() {

		List<ItemsOption> enumValues =new ArrayList<ItemsOption>(Arrays.asList(ItemsOption.values()));
		dropDownItems = new HashMap<String, String>();
		optionDropDown = enumValues.get(0).getValue();
		dropDownItems.put(optionDropDown, optionDropDown);

	}
	
	public void onDropDownChange() {

		if (optionDropDown.toLowerCase().equals(ItemsOption.SELL.getValue())) {

		//	itemsListDto = itemsService.getItemsByUserId(user.getUser().getId());

			for (ItemsDTO item : itemsListDto) {

				//item.setAvailableStatus(populateItemsSatusList(item));

			}

		} else if (optionDropDown.toLowerCase().equals(ItemsOption.BUY.getValue())) {

			// TODO

		}

	}
}
