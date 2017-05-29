package RepositoryInterfaces;

import java.util.List;
import javax.ejb.Remote;
import entities.Categories;
import exceptions.CaveatEmptorException;

@Remote
public interface CategoriesRepository {
	public List<Categories> getCategories() throws CaveatEmptorException;
}
