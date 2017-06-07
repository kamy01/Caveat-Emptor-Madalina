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
public class CategoriesRepositoryImpl implements CategoriesRepository {

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Categories> getCategories() throws CaveatEmptorException {

		try {
			return (List<Categories>) entityManager.createNamedQuery(QueryConstants.GET_CATEGORIES).getResultList();

		} catch (Exception ex) {
			Constants.getLogger().log(Level.INFO, "Exception in getCategories method from CategoriesRepositoryImpl",ex.getMessage());
			return null;
		}
	}

	@Override
	public Categories getCategoryById(Long categoryId) throws CaveatEmptorException {
		try {
			return (Categories) entityManager.createNamedQuery(QueryConstants.GET_CATEGORY_BY_ID)
					.setParameter("categoryId", categoryId).getSingleResult();
		} catch (Exception e) {
			Constants.getLogger().log(Level.INFO, "Exception in getCategoryById method from CategoriesRepositoryImpl",e.getMessage());
			return null;
		}
	}

	@Override
	public String insertCategory(Categories category) throws CaveatEmptorException {
		try {
			List<Categories> listCategoris = getCategories();
			for (Categories child : listCategoris) {
				if ((getCategoryById(child.getCategoryId()).getCategoryId()) == category.getCategoryId() || ((child.getNameCategory().equals(category.getNameCategory()) && (category.getNameCategory() != null)))) {
					return "categoryExist";
				}
			}
			if ( !category.getNameCategory().isEmpty() ){
				if (category.getParentId() == null) {
					category.setParentId(1L);
				}
				if (category.getCategoryId().equals(category.getParentId())) {
					return "error";
				}

				entityManager.merge(category);
				return "categoryCreated";
			} else {
				return "nullValues";
			}
		} catch (Exception e) {
			Constants.getLogger().log(Level.INFO, "Exception in insertCategory method from CategoriesRepositoryImpl",e.getMessage());
			throw new CaveatEmptorException();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categories> getChildren(Categories parent) throws CaveatEmptorException {

		try {
			return (List<Categories>) entityManager.createNamedQuery(QueryConstants.GET_CHILDREN_FOR_CATEGORY)
					.setParameter("categoryId", parent.getCategoryId()).getResultList();

		} catch (Exception e) {
			Constants.getLogger().log(Level.INFO, "Exception in insertCategory method from CategoriesRepositoryImpl",
					e.getMessage());
			throw new CaveatEmptorException();
		}
	}

	@Override
	public String updateCategory(Categories category) throws CaveatEmptorException {
		try {
			Categories categ = getCategoryById(category.getCategoryId());
			if (categ == null) {
				return "categoryNotExist";
			} else {
				entityManager.merge(category);
				return "categoryUpdated";
				}

		} catch (Exception e) {
			Constants.getLogger().log(Level.INFO, "Exception in updateCategory method from CategoriesRepositoryImpl",
					e.getMessage());
			throw new CaveatEmptorException();
		}
	}

	@Override
	public boolean deleteCategory(Categories category) throws CaveatEmptorException {
		try {
			Categories categ = getCategoryById(category.getCategoryId());
			if (categ == null) {
				return false;
			} else {
				entityManager.remove(categ);
				return true;
			}

		} catch (Exception e) {
			Constants.getLogger().log(Level.INFO, "Exception in deleteCategory method from CategoriesRepositoryImpl",
					e.getMessage());
			throw new CaveatEmptorException();
		}
	}

	public Long getMaxCategoryId() throws CaveatEmptorException {
		try {
			return (Long) entityManager.createNamedQuery(QueryConstants.GET_MAX_CATEGORY_ID).getSingleResult();
		} catch (Exception e) {
			Constants.getLogger().log(Level.INFO, "Exception in getMaxCategoryId method from CategoriesRepositoryImpl",
					e.getMessage());
			throw new CaveatEmptorException();
		}
	}

}
