package ServiceInterfaces;

import java.util.List;

import javax.ejb.Remote;

import com.dtos.ItemsDTO;
import exceptions.CaveatEmptorException;

@Remote
public interface ItemsService {
	public List<ItemsDTO> getItemsToSell() throws CaveatEmptorException;
}
