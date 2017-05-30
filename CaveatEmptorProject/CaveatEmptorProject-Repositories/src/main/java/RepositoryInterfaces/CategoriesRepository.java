package RepositoryInterfaces;

import java.util.List;
import javax.ejb.Remote;

import com.dtos.CategoriesDTO;

import entities.Categories;
import exceptions.CaveatEmptorException;

@Remote
public interface CategoriesRepository {
	public List<Categories> getCategories() throws CaveatEmptorException;
	public boolean removeCategory(CategoriesDTO categoryDto) throws CaveatEmptorException;
}
