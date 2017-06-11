package RepositoryImplementation;

import java.util.List;
import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import RepositoryConstants.QueryConstants;
import RepositoryInterfaces.ItemsRepository;
import entities.Items;
import exceptions.CaveatEmptorException;
import utils.Constants;

@Stateless
public class ItemsRepositoryImpl implements ItemsRepository{

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Items> getItemsToSell(Long userId) throws CaveatEmptorException {
		try {
				return (List<Items>) entityManager.createNamedQuery(QueryConstants.GET_ITEMS_TO_SELL)
						.setParameter("userId", userId).getResultList();
		} catch (Exception ex) {
			Constants.getLogger().log(Level.INFO, "Exception in getItemsToSell method from ItemsRepositoryImpl",ex.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Items> getItemsToBuy(Long userId) throws CaveatEmptorException {
		try {
				return (List<Items>) entityManager.createNamedQuery(QueryConstants.GET_ITEMS_TO_BUY)
						.setParameter("userId", userId).getResultList();
		} catch (Exception ex) {
			Constants.getLogger().log(Level.INFO, "Exception in getItemsToBuy method from ItemsRepositoryImpl",ex.getMessage());
			return null;
		}
	}
	
	public boolean updateItem(Items item) throws CaveatEmptorException{
		return entityManager.merge(item) != null;
	}
	public boolean deleteItem(Items item) throws CaveatEmptorException{
		if(item.getItemId()!=null){
			entityManager.remove(item);
			return true;
		}
		return false;
	}
}
