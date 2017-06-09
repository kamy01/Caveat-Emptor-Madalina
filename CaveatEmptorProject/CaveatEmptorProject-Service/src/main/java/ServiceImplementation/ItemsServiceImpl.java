package ServiceImplementation;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import com.dtos.ItemsDTO;

import RepositoryInterfaces.CategoriesRepository;
import RepositoryInterfaces.ItemsRepository;
import ServiceInterfaces.ItemsService;
import entities.Categories;
import entities.Items;
import exceptions.CaveatEmptorException;
import utils.Constants;

@Stateless
@ApplicationScoped
public class ItemsServiceImpl implements ItemsService{
	
	@EJB 
	ItemsRepository itemsRepository;
	
	@EJB 
	CategoriesRepository categoriesRepository;
	
	public List<ItemsDTO> getItemsToSell(Long userId) throws CaveatEmptorException {
		try{
		List<Items> itemsList=itemsRepository.getItemsToSell(userId);
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
	
	public List<String> getCategoriesNames() throws CaveatEmptorException{
		List<Categories> categories= categoriesRepository.getCategories();
		List<String> categoriesNames=new ArrayList<>();

		for (Categories category : categories) {
			if(category.getNameCategory()!=null){
				categoriesNames.add(category.getNameCategory());
			}
		}
		return categoriesNames;
	}
	
	public List<ItemsDTO> getItemsToBuy(Long userId) throws CaveatEmptorException {
		try{
		List<Items> itemsList=itemsRepository.getItemsToBuy(userId);
		List<ItemsDTO> itemsListDto=new ArrayList<>();
		ItemsDTO itemDto;
		for (Items item : itemsList) {
			itemDto=itemEntityToDto(item);
			itemsListDto.add(itemDto);
		}
		return itemsListDto;
		}catch(Exception e){
			Constants.getLogger().log( Level.INFO, "Exception in getItemsToBuy method from ItemsServiceImpl" ,e.getMessage());		
			throw new CaveatEmptorException();			
		}
	}
	
	public ItemsDTO itemEntityToDto(Items item) throws ParseException{
		ItemsDTO itemDto=new ItemsDTO();
		itemDto.setName(item.getName());
		itemDto.setDescription(item.getDescription());
		itemDto.setCategories(item.getCategories());
		itemDto.setInitialPrice(item.getInitialPrice());
		itemDto.setBestBid(item.getBestBid());
		itemDto.setYourBid(item.getYourBid());
		itemDto.setNrBids(item.getNrBids());
		itemDto.setBiddingStartDate(item.getBiddingStartDate());
		itemDto.setBiddingEndDate(item.getBiddingEndDate());
		itemDto.setStatus(item.getStatus());
		itemDto.setWinner(item.getWinner());
		return itemDto;
	}
}
