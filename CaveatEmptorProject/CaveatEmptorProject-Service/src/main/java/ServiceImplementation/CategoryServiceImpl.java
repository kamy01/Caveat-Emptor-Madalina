package ServiceImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.dtos.CategoriesDTO;
import RepositoryInterfaces.CategoriesRepository;
import ServiceInterfaces.CategoryService;
import entities.Categories;
import exceptions.CaveatEmptorException;
import utils.Constants;

@Stateless
public class CategoryServiceImpl implements CategoryService{
	
	@EJB
	CategoriesRepository categoriesRepository;
	
	public CategoriesDTO transformCategoryEntityToDto(Categories categories){
		CategoriesDTO categoriesDto=new CategoriesDTO();
		
		categoriesDto.setCategoryId(categories.getCategoryId());
		categoriesDto.setParentId(categories.getParentId());
		categoriesDto.setNameCategory(categories.getNameCategory());
		categoriesDto.setDescription(categories.getDescription());
		return categoriesDto;
	}
	
	@Override
	public List<CategoriesDTO> getCategories() throws CaveatEmptorException {
		CategoriesDTO categoriesDto=new CategoriesDTO();
		List<CategoriesDTO> listCategoriesDto=new ArrayList<>();
		
		try{
		List<Categories> categories=((CategoriesRepository) categoriesRepository).getCategories();
		
		for (Categories categoryService : categories) {
			categoriesDto=transformCategoryEntityToDto(categoryService);
			listCategoriesDto.add(categoriesDto);
		}
		}catch(Exception e){
			Constants.getLogger().log( Level.INFO, "Exception in getCategories method from CategoryServiceImpl" ,e.getMessage());		
			throw new CaveatEmptorException();
		}
		return listCategoriesDto;
	}

}
