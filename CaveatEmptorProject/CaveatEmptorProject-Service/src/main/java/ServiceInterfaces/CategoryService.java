package ServiceInterfaces;

import java.util.List;
import javax.ejb.Remote;
import com.dtos.CategoriesDTO;
import exceptions.CaveatEmptorException;

@Remote
public interface CategoryService {
	public List<CategoriesDTO> getCategories() throws CaveatEmptorException;
	public String insertCategory(CategoriesDTO categoriesDto) throws CaveatEmptorException;
	public List<CategoriesDTO> getChildrenForParent(CategoriesDTO parent) throws CaveatEmptorException;
	public CategoriesDTO getCategoryById(Long categoryId) throws CaveatEmptorException;
	public boolean updateCategory(CategoriesDTO categoriesDto) throws CaveatEmptorException;
	public boolean deleteCategory(CategoriesDTO categoriesDto) throws CaveatEmptorException;
	public CategoriesDTO getRootCategory() throws CaveatEmptorException;
	public Long getMaxCategoryId() throws CaveatEmptorException;
}
