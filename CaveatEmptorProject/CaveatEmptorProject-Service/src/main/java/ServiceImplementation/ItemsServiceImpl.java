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
import utils.LoggerUtils;

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
			LoggerUtils.getLogger().log( Level.INFO, "Exception in getItemsToSell method from ItemsServiceImpl" ,e.getMessage());		
			throw new CaveatEmptorException();			
		}
	}
	public List<String> getCategoriesNames() throws CaveatEmptorException{
		List<Categories> categories= categoriesRepository.getCategories();
		List<String> categoriesNames=new ArrayList<>();

		for (Categories category : categories) {
			if(!category.getNameCategory().isEmpty()){
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
			itemDto=Transformation.itemEntityToDto(item);
			itemsListDto.add(itemDto);
		}
		return itemsListDto;
		}catch(Exception e){
			LoggerUtils.getLogger().log( Level.INFO, "Exception in getItemsToBuy method from ItemsServiceImpl" ,e.getMessage());		
			throw new CaveatEmptorException();			
		}
	}
	
	
	
	public boolean updateItem(ItemsDTO itemDto) throws CaveatEmptorException{
		try {
			return itemsRepository.updateItem(Transformation.itemDtoToEntity(itemDto));
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		
			}
	public boolean insertItem(ItemsDTO itemDto) throws CaveatEmptorException{
		try {
			

			return itemsRepository.insertItem(Transformation.itemDtoToEntity(itemDto));
		}catch(Exception e){
			LoggerUtils.getLogger().log( Level.INFO, "Exception in deleteItem method from ItemsServiceImpl" ,e.getMessage());		
			throw new CaveatEmptorException();			
		}
	}
	
	public Long getMaxItemId() throws CaveatEmptorException{
		try{
			return itemsRepository.getMaxItemId();
	}catch(Exception e){
		LoggerUtils.getLogger().log( Level.INFO, "Exception in getMaxItemId method from ItemsServiceImpl" ,e.getMessage());		
		throw new CaveatEmptorException();			
	}
}
}
