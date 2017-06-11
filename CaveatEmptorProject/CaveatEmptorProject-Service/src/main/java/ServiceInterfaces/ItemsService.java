package ServiceInterfaces;

import java.util.List;

import javax.ejb.Remote;

import com.dtos.ItemsDTO;

import entities.Items;
import exceptions.CaveatEmptorException;

@Remote
public interface ItemsService {
	public List<ItemsDTO> getItemsToSell(Long userId) throws CaveatEmptorException;
	public List<ItemsDTO> getItemsToBuy(Long userId) throws CaveatEmptorException;
	public List<String> getCategoriesNames() throws CaveatEmptorException;
	public boolean updateItem(ItemsDTO itemDto) throws CaveatEmptorException;
	public boolean deleteItem(ItemsDTO itemDto) throws CaveatEmptorException;
}
