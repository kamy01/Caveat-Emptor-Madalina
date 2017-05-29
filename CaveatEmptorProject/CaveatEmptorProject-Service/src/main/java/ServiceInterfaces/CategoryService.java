package ServiceInterfaces;

import java.util.List;

import javax.ejb.Remote;

import com.dtos.CategoriesDTO;

import exceptions.CaveatEmptorException;

@Remote
public interface CategoryService {
	public List<CategoriesDTO> getCategories() throws CaveatEmptorException;
}
