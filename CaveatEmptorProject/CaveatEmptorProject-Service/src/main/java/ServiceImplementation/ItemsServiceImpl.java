package ServiceImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;

import com.dtos.ItemsDTO;

import RepositoryInterfaces.ItemsRepository;
import ServiceInterfaces.ItemsService;
import entities.Items;
import exceptions.CaveatEmptorException;
import utils.Constants;

@Stateless
@ApplicationScoped
public class ItemsServiceImpl implements ItemsService{
	
	@EJB 
	ItemsRepository itemsRepository;
	
	public List<ItemsDTO> getItemsToSell(String itemOption) throws CaveatEmptorException {
		try{
		List<Items> itemsList=itemsRepository.getItemsToSell(itemOption);
		List<ItemsDTO> itemsListDto=new ArrayList<>();
		ItemsDTO itemDto;
		for (Items item : itemsList) {
			itemDto=itemEntityToDto(item);
			itemsListDto.add(itemDto);
		}
		return itemsListDto;
		}catch(Exception e){
			Constants.getLogger().log( Level.INFO, "Exception in getItemsToSell method from ItemsServiceImpl" ,e.getMessage());		
			throw new CaveatEmptorException();			
		}
	}
	
	public ItemsDTO itemEntityToDto(Items item){
		ItemsDTO itemDto=new ItemsDTO();
		itemDto.setName(item.getName());
		itemDto.setDescription(item.getDescription());
		itemDto.setCategories(item.getCategories());
		itemDto.setInitialPrice(item.getInitialPrice());
		itemDto.setBestBid(item.getBestBid());
		itemDto.setNrBids(item.getNrBids());
		itemDto.setBiddingStartDate(item.getBiddingStartDate());
		itemDto.setBiddingEndDate(item.getBiddingEndDate());
		itemDto.setStatus(item.getStatus());
		itemDto.setWinner(item.getWinner());
		itemDto.setItemOption(item.getItemOption());
		return itemDto;
	}
}
