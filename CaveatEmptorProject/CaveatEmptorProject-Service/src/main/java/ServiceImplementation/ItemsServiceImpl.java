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
		ItemsDTO itemDto=new ItemsDTO();
		for (Items item : itemsList) {
			itemDto=Transformation.itemEntityToDto(item);
			itemsListDto.add(itemDto);
		}
			return itemsListDto;
		}catch(Exception e){
			Constants.getLogger().log( Level.INFO, "Exception in getItemsToSell method from ItemsServiceImpl" ,e.getMessage());		
			throw new CaveatEmptorException();			
		}
	}
	
	public List<ItemsDTO> getItemsToBuy(Long userId) throws CaveatEmptorException {
		try{
		List<Items> itemsList=itemsRepository.getItemsToBuy(userId);
		List<ItemsDTO> itemsListDto=new ArrayList<>();
		ItemsDTO itemDto;
		for (Items item : itemsList) {
			itemDto=Transformation.itemEntityToDto(item);
			itemsListDto.add(itemDto);
		}
		return itemsListDto;
		}catch(Exception e){
			Constants.getLogger().log( Level.INFO, "Exception in getItemsToBuy method from ItemsServiceImpl" ,e.getMessage());		
			throw new CaveatEmptorException();			
		}
	}
	
	
	
	public boolean updateItem(ItemsDTO itemDto) throws CaveatEmptorException{
		Items item=new Items();
		try {
			item = Transformation.itemDtoToEntity(itemDto);
			return itemsRepository.updateItem(item);
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		
			}
	public boolean insertItem(ItemsDTO itemDto) throws CaveatEmptorException{
		try {
			Items item=new Items();
			item.setItemId(itemDto.getItemId());
			return itemsRepository.insertItem(item);
		}catch(Exception e){
			Constants.getLogger().log( Level.INFO, "Exception in deleteItem method from ItemsServiceImpl" ,e.getMessage());		
			throw new CaveatEmptorException();			
		}
	}
}
