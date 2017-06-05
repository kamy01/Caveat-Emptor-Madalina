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
	
	CategoriesDTO categoriesDto;
	
	public CategoriesDTO EntityToDto(Categories categories){
		categoriesDto=new CategoriesDTO();		
		categoriesDto.setCategoryId(categories.getCategoryId());
		categoriesDto.setParentId(categories.getParentId());
		categoriesDto.setNameCategory(categories.getNameCategory());
		categoriesDto.setDescription(categories.getDescription());
		return categoriesDto;
	}
	public Categories DtoToEntity(CategoriesDTO categoriesDto){
		Categories categories=new Categories();		
		categories.setCategoryId(categoriesDto.getCategoryId());
		categories.setParentId(categoriesDto.getParentId());
		categories.setNameCategory(categoriesDto.getNameCategory());
		categories.setDescription(categoriesDto.getDescription());
		return categories;
	}
	
	@Override
	public List<CategoriesDTO> getCategories() throws CaveatEmptorException {
		CategoriesDTO categoriesDto=new CategoriesDTO();
		List<CategoriesDTO> listCategoriesDto=new ArrayList<>();	
		try{
		List<Categories> categories=((CategoriesRepository) categoriesRepository).getCategories();
		for (Categories categoryService : categories) {
			categoriesDto=EntityToDto(categoryService);
			listCategoriesDto.add(categoriesDto);
		}
		}catch(Exception e){
			Constants.getLogger().log( Level.INFO, "Exception in getCategories method from CategoryServiceImpl" ,e.getMessage());		
			throw new CaveatEmptorException();
		}
		return listCategoriesDto;
	}
	
	@Override
	public List<CategoriesDTO> getChildrenForParent(CategoriesDTO parent) throws CaveatEmptorException {
		try{
			Categories parentCategory = DtoToEntity(parent);		
			List<Categories> categories = categoriesRepository.getChildren(parentCategory);	
			List<CategoriesDTO> categoriesDto = new ArrayList<CategoriesDTO>();
			for (Categories category : categories) {
				categoriesDto.add(EntityToDto(category));
			}
			return categoriesDto;	
			 }
		catch(Exception e){
		Constants.getLogger().log( Level.INFO, "Exception in getChildrenForParent method from CategoryServiceImpl" ,e.getMessage());		
		throw new CaveatEmptorException();		
		}
	}
	
	@Override
	public String insertCategory(CategoriesDTO categoriesDto) throws CaveatEmptorException {
		try{
			String insertMessage=categoriesRepository.insertCategory(DtoToEntity(categoriesDto));
			return insertMessage;	
			 }
		catch(Exception e){
		Constants.getLogger().log( Level.INFO, "Exception in insertCategory method from CategoryServiceImpl" ,e.getMessage());		
		throw new CaveatEmptorException();		
		}
		
		
	}
	
	public CategoriesDTO getRootCategory() throws CaveatEmptorException {
		
		try{
			Categories category = categoriesRepository.getCategoryById((long) 0);
			return EntityToDto(category);
		}catch(Exception e){
		Constants.getLogger().log( Level.INFO, "Exception in getRootCategory method from CategoryServiceImpl" ,e.getMessage());		
		throw new CaveatEmptorException();			
	}		
		
	}

	@Override
	public CategoriesDTO getCategoryById(Long categoryId) throws CaveatEmptorException {
		
		try{
			Categories category=categoriesRepository.getCategoryById(categoryId);
			 return EntityToDto(category);	
			 }
		catch(Exception e){
		Constants.getLogger().log( Level.INFO, "Exception in getCategoryById method from CategoryServiceImpl" ,e.getMessage());		
		throw new CaveatEmptorException();			
	}
		
	}
	@Override
	public boolean updateCategory(CategoriesDTO categoriesDto) throws CaveatEmptorException {
		try{
			return categoriesRepository.updateCategory(DtoToEntity(categoriesDto));	
	}catch(Exception e){
		Constants.getLogger().log( Level.INFO, "Exception in updateCategory method from CategoryServiceImpl" ,e.getMessage());		
		throw new CaveatEmptorException();			
	}
		
	}
	@Override
	public boolean deleteCategory(CategoriesDTO categoriesDto) throws CaveatEmptorException {
		try{
			Categories category = DtoToEntity(categoriesDto);
			List<Categories> children=categoriesRepository.getChildren(category);
			Long parentId=categoriesDto.getParentId();
			
			for (Categories child : children) {
				child.setParentId(parentId);
				categoriesRepository.updateCategory(child);
			}
			return categoriesRepository.deleteCategory(category);		
	}catch(Exception e){
		Constants.getLogger().log( Level.INFO, "Exception in deleteCategory method from CategoryServiceImpl" ,e.getMessage());		
		throw new CaveatEmptorException();			
	}
		
	}
	
	public Long getMaxCategoryId() throws CaveatEmptorException{
		try{
			return categoriesRepository.getMaxCategoryId();
	}catch(Exception e){
		Constants.getLogger().log( Level.INFO, "Exception in getMaxCategoryId method from CategoryServiceImpl" ,e.getMessage());		
		throw new CaveatEmptorException();			
	}
	
	}

}
