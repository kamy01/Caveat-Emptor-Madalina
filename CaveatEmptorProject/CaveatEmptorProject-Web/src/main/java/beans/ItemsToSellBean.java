package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.dtos.ItemsDTO;

import ServiceInterfaces.ItemsService;
import exceptions.CaveatEmptorException;

@ManagedBean(name = "itemsToSell")
@ViewScoped
public class ItemsToSellBean  implements Serializable{
	private static final long serialVersionUID = -5604086696813201615L;
	
	
	
	private List<ItemsDTO> itemsListDto;
	
	@EJB
	ItemsService itemsService;
	
	@PostConstruct
	public void init() throws CaveatEmptorException {
		itemsListDto = new ArrayList<>();	
		//itemsListDto=itemsService.getItemsToSell("sell");
	}


	public List<ItemsDTO> getItemsListDto() {
		return itemsListDto;
	}
	public void setItemsListDto(List<ItemsDTO> itemsListDto) {
		try {
			itemsListDto=itemsService.getItemsToSell("sell");
		} catch (CaveatEmptorException e) {
			e.printStackTrace();
		}
	}

	
}
