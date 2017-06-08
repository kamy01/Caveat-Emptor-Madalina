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

	@Override
	public Users findUserByUsername(String username) throws CaveatEmptorException {
		try {
			return (Users) entityManager.createNamedQuery(QueryConstants.FIND_USER_BY_USERNAME)
					.setParameter("username", username).getSingleResult();
		} catch (Exception ex) {
			Constants.getLogger().log(Level.INFO, "Exception in findUserByUsername method from UserRepositoryImpl",
					ex.getMessage());
			return null;
		}
	}

	@Override
	public Users findUserByEmail(String email) throws CaveatEmptorException {
		try {
			return (Users) entityManager.createNamedQuery(QueryConstants.FIND_USER_BY_EMAIL)
					.setParameter("email", email).getSingleResult();

		} catch (Exception ex) {
			Constants.getLogger().log(Level.INFO, "Exception in findUserByEmail method from UserRepositoryImpl",
					ex.getMessage());
			return null;

		}
	}

	@Override
	public Users findUserByPhoneNumber(Long phoneNumber) throws CaveatEmptorException {
		try {
			return (Users) entityManager.createNamedQuery(QueryConstants.FIND_USER_BY_PHONENR)
					.setParameter("phoneNumber", phoneNumber).getSingleResult();

		} catch (Exception ex) {
			Constants.getLogger().log(Level.INFO, "Exception in findUserByPhoneNumber method from UserRepositoryImpl",
					ex.getMessage());
			return null;

		}
	}

	public Users setUser(UserDTO userDto) {
		Date date = new Date();
		Users user = new Users();
		
		user.setFirstname(userDto.getFirstname());
		user.setLastname(userDto.getLastname());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setPhoneNumber(userDto.getPhoneNumber());
		user.setUsername(userDto.getUsername());
		user.setDateRegistered(new Timestamp(date.getTime()));
		return user;
	}

	@Override
	public String createUser(UserDTO userDto, String repeatPassword) throws CaveatEmptorException {
		Users user = new Users();
		user = setUser(userDto);
		if (findUserByUsername(userDto.getUsername()) != null) {
			return "usernameExist";
		} else if (findUserByEmail(userDto.getEmail()) != null) {
			return "emailExist";
		} else if (findUserByPhoneNumber(userDto.getPhoneNumber()) != null) {
			return "phoneNumberExist";
		} else {
			if (user.getPassword().equals(repeatPassword)) {
				entityManager.persist(user);
				return "userCreated";
			} else {
				return "passwordDifferent";
			}
		}
	}

	@Override
	public void insertKeyForUser(UserDTO userDto, String key) throws CaveatEmptorException {
		Users user = new Users();
		user = findUserByUsername(userDto.getUsername());
		if (user != null) {
			user.setActivationKey(key);
			entityManager.persist(user);
		}
	}

	@Override
	public boolean enableAndRegisterUser(UserDTO userDto) throws CaveatEmptorException {
		Users user = new Users();
		user = findUserByUsername(userDto.getUsername());
		if (!user.isEnabled()) {
			user.setEnabled(true);
			user.setActivationKey(null);
			entityManager.persist(user);
			return true;
		}
		return false;
	}
}