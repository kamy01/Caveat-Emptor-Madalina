package RepositoryImplementation;

import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.dtos.UserDTO;

import RepositoryInterfaces.UserRepository;
import entities.Users;
import exceptions.CaveatEmptorException;
import utils.Constants;
import RepositoryConstants.*;

@Stateless
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;
	Users user;

	public void init() {
		user = new Users();
	}

	@Override
	public Users findUserByUsername(String username) throws CaveatEmptorException {
		try {
			return (Users) entityManager.createNamedQuery(QueryConstants.FIND_USER_BY_USERNAME)
					.setParameter("username", username).getSingleResult();
		} catch (Exception ex) {
			Constants.getLogger().log( Level.INFO, "Exception in findUserByUsername method from UserRepositoryImpl" ,ex.getMessage());		
			return null;
		}
	}

	@Override
	public Users findUserByEmail(String email) throws CaveatEmptorException {
		try {
			return (Users) entityManager.createNamedQuery(QueryConstants.FIND_USER_BY_EMAIL).setParameter("email", email)
					.getSingleResult();

		} catch (Exception ex) {
			Constants.getLogger().log( Level.INFO, "Exception in findUserByEmail method from UserRepositoryImpl" ,ex.getMessage());		
			return null;

		}
	}

	@Override
	public String createUser(UserDTO userDto) throws CaveatEmptorException {
		Date date = new Date();

		user = new Users();
		user.setFirstname(userDto.getFirstname());
		user.setLastname(userDto.getLastname());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setPhoneNumber(userDto.getPhoneNumber());
		user.setUsername(userDto.getUsername());
		user.setDateRegistered(new Timestamp(date.getTime()));

		if (findUserByUsername(userDto.getUsername()) != null) {
			return "usernameExist";
		} else if (findUserByEmail(userDto.getUsername()) != null) {
			return "emailExist";
		} else {
			entityManager.persist(user);
			return "userCreated";
		}
	}

	@Override
	public void insertKeyForUser(UserDTO userDto, String key) throws CaveatEmptorException {
		user = findUserByUsername(userDto.getUsername());
		if (user != null) {
			user.setActivationKey(key);
			entityManager.persist(user);
		}
	}

	@Override
	public boolean enableAndRegisterUser(UserDTO userDto) throws CaveatEmptorException {
		user = findUserByUsername(userDto.getUsername());
		if(!user.isEnabled()){
			user.setEnabled(true);
			user.setActivationKey(null);
			entityManager.persist(user);
			return true;
		}
			return false;
	}
}