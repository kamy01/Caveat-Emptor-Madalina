package dta;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import entities.Users;


@Stateless
public class UserDTAImpl implements UserDTA{

	@PersistenceUnit(unitName="persistenceUnit")
	private EntityManager entityManager;
	
	@Override
	public Users findUserByUsername(String username) {
		Users user=(Users) entityManager.createNamedQuery("User.findUserByUsername").setParameter("username", username).getSingleResult();
		return user;
	}
	
}