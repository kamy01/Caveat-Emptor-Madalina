package RepositoryInterfaces;

import java.util.List;
import javax.ejb.Remote;
import entities.Categories;
import exceptions.CaveatEmptorException;

@Remote
public interface CategoriesRepository {
	public List<Categories> getCategories() throws CaveatEmptorException;
	public List<Categories> getChildren(Categories parent) throws CaveatEmptorException ;
	public Categories getCategoryById(Long categoryId) throws CaveatEmptorException;
	public String insertCategory(Categories category) throws CaveatEmptorException;
	public boolean updateCategory(Categories category) throws CaveatEmptorException;
	public boolean deleteCategory(Categories category) throws CaveatEmptorException;
	public Long getMaxCategoryId() throws CaveatEmptorException;

}
