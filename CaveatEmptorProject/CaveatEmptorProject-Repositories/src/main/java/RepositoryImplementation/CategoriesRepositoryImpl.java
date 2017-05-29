package RepositoryImplementation;

import java.util.List;
import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import RepositoryInterfaces.CategoriesRepository;
import entities.Categories;
import exceptions.CaveatEmptorException;
import utils.Constants;
import RepositoryConstants.*;

@Stateless
public class CategoriesRepositoryImpl implements CategoriesRepository{
	
	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;
	
	
	@SuppressWarnings("unchecked")
	public List<Categories> getCategories() throws CaveatEmptorException{
		
		try {
			return (List<Categories>) entityManager.createNamedQuery(QueryConstants.GET_CATEGORIES).getResultList();

		} catch (Exception ex) {
			Constants.getLogger().log( Level.INFO, "Exception in getCategories method from CategoriesRepositoryImpl" ,ex.getMessage());		
			return null;
		}	
	}
}
