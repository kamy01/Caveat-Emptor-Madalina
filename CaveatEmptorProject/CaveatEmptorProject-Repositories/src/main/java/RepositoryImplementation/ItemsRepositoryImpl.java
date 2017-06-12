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
import utils.LoggerUtils;

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
			LoggerUtils.getLogger().log(Level.INFO, "Exception in getItemsToSell method from ItemsRepositoryImpl",ex.getMessage());
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
			LoggerUtils.getLogger().log(Level.INFO, "Exception in getItemsToBuy method from ItemsRepositoryImpl",ex.getMessage());
			return null;
		}
	}
	
	public boolean updateItem(Items item) throws CaveatEmptorException{
		return entityManager.merge(item) != null;
	}
	public boolean insertItem(Items item) throws CaveatEmptorException{
		if(item.getItemId()!=null){
			entityManager.merge(item);
			return true;
		}
		return false;
	}
	public Long getMaxItemId() throws CaveatEmptorException {
		try {
			return (Long) entityManager.createNamedQuery(QueryConstants.GET_MAX_ITEMID).getSingleResult();
		} catch (Exception e) {
			LoggerUtils.getLogger().log(Level.INFO, "Exception in getMaxItemId method from ItemsRepositoryImpl",
					e.getMessage());
			throw new CaveatEmptorException();
		}
	}
}
