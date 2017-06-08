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
	public List<Items> getItemsToSell() throws CaveatEmptorException {
		try {
				return (List<Items>) entityManager.createNamedQuery(QueryConstants.GET_ITEMS_TO_SELL).getResultList();

		} catch (Exception ex) {
			Constants.getLogger().log(Level.INFO, "Exception in getItemsToSell method from ItemsRepositoryImpl",ex.getMessage());
			return null;
		}
	}

}
