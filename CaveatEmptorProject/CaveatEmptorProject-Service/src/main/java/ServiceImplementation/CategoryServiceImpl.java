package ServiceImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;
import java.util.logging.Level;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;
	
	public CategoriesDTO transformCategoryEntityToDto(Categories categories){
		CategoriesDTO categoriesDto=new CategoriesDTO();
		
		categoriesDto.setCategoryId(categories.getCategoryId());
		categoriesDto.setParentId(categories.getParentId());
		categoriesDto.setNameCategory(categories.getNameCategory());
		categoriesDto.setDescription(categories.getDescription());
		return categoriesDto;
	}
	
	public Categories transformCategoryDtoToEntity(CategoriesDTO categoriesDto){
		Categories categories=new Categories();
		
		categories.setCategoryId(categoriesDto.getCategoryId());
		categories.setParentId(categoriesDto.getParentId());
		categories.setNameCategory(categoriesDto.getNameCategory());
		categories.setDescription(categoriesDto.getDescription());
		return categories;
	}
	
	@Override
	public List<CategoriesDTO> getCategories() throws CaveatEmptorException {
		ArrayList<Categories> categories = (ArrayList<Categories>)categoriesRepository.getCategories();

		ArrayList<CategoriesDTO> categoriesDto = new ArrayList<>();

		for (Categories category : categories) {
			CategoriesDTO categ=transformCategoryEntityToDto(category);
			categoriesDto.add(categ);
		}
		return (List<CategoriesDTO>)categoriesDto;
	}
	
	public void insertCategory(CategoriesDTO categoryDto) throws CaveatEmptorException {
		Categories category = transformCategoryDtoToEntity(categoryDto);
		entityManager.persist(category);

	}
	
	public boolean removeCategory(CategoriesDTO categoryDto) throws CaveatEmptorException{
		try{
			return categoriesRepository.removeCategory(categoryDto);
		}catch(Exception e){
			Constants.getLogger().log( Level.INFO, "Exception in removeCategory method from CategoryServiceImpl" ,e.getMessage());		
			throw new CaveatEmptorException();
		}
	}

}
