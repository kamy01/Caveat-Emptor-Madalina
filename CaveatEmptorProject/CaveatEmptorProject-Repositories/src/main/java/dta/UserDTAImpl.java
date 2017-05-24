package dta;

import java.sql.Timestamp;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.dtos.UserDTO;

import entities.Users;
import exceptions.UsersException;
import utils.Constants;

@Stateless
public class UserDTAImpl implements UserDTA {

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;
	Users user;

	public void init() {
		user = new Users();
	}

	@Override
	public Users findUserByUsername(String username) throws UsersException {
		try {
			return (Users) entityManager.createNamedQuery(Constants.FIND_USER_BY_USERNAME)
					.setParameter("username", username).getSingleResult();

		} catch (Exception ex) {
			return null;

		}
	}

	@Override
	public Users findUserByEmail(String email) throws UsersException {
		try {
			return (Users) entityManager.createNamedQuery(Constants.FIND_USER_BY_EMAIL).setParameter("email", email)
					.getSingleResult();

		} catch (Exception ex) {
			return null;

		}
	}

	@Override
	public String createUser(UserDTO userDto) throws UsersException {
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
	public void insertKeyForUser(UserDTO userDto, String key) throws UsersException {
		user = findUserByUsername(userDto.getUsername());
		if (user != null) {
			user.setActivationKey(key);
			entityManager.persist(user);
		}
	}

	@Override
	public boolean enableAndRegisterUser(UserDTO userDto) throws UsersException {
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