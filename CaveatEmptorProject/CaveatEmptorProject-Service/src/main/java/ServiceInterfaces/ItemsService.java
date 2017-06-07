package ServiceInterfaces;

import java.util.List;

import javax.ejb.Remote;

import com.dtos.ItemsDTO;

import entities.Items;
import exceptions.CaveatEmptorException;

@Remote
public interface ItemsService {
	public List<ItemsDTO> getItemsToSell(String itemOption) throws CaveatEmptorException;
}
