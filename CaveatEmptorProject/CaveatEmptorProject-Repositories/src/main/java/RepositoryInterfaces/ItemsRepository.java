package RepositoryInterfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.Items;
import exceptions.CaveatEmptorException;

@Remote
public interface ItemsRepository {
	public List<Items> getItemsToSell(String itemOption) throws CaveatEmptorException;
}
