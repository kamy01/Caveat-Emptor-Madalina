package service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.dtos.UserDTO;
import dta.UserDTA;
import entities.Users;
import exceptions.UsersException;

@Stateless
public class UserService implements IUserService {

	@PersistenceContext
	EntityManager entityManager;
	@EJB
	UserDTA userDta;

	UserDTO userDto;

	@Override
	public UserDTO getUserByUsername(String username) throws UsersException {
		Users user = userDta.findUserByUsername(username);

		if (user != null) {
			userDto = new UserDTO();
			userDto.setFirstname(user.getFirstname());
			userDto.setLastname(user.getLastname());
			userDto.setAdmin(user.isAdmin());
			userDto.setEmail(user.getEmail());
			userDto.setPassword(user.getPassword());
			userDto.setPhoneNumber(user.getPhoneNumber());
			userDto.setRanking(user.getRanking());
			userDto.setUsername(user.getUsername());
			userDto.setActivationKey(user.getActivationKey());
			userDto.setDateRegistered(user.getDateRegistered());
			userDto.setEnabled(user.isEnabled());
		}
		return userDto;

	}

	@Override
	public UserDTO getUserByEmail(String email) throws UsersException {
		Users user = userDta.findUserByEmail(email);

		if (user != null) {
			userDto = new UserDTO();
			userDto.setFirstname(user.getFirstname());
			userDto.setLastname(user.getLastname());
			userDto.setAdmin(user.isAdmin());
			userDto.setEmail(user.getEmail());
			userDto.setPassword(user.getPassword());
			userDto.setPhoneNumber(user.getPhoneNumber());
			userDto.setRanking(user.getRanking());
			userDto.setUsername(user.getUsername());
			userDto.setActivationKey(user.getActivationKey());
			userDto.setDateRegistered(user.getDateRegistered());
			userDto.setEnabled(user.isEnabled());
		}
		return userDto;
	}

	@Override
	public String createUser(UserDTO userDto) throws UsersException {
		return userDta.createUser(userDto);
	}

	@Override
	public void insertKeyForUser(UserDTO userDto, String key) throws UsersException {
		userDta.insertKeyForUser(userDto, key);

	}

	@Override
	public boolean enableAndRegisterUser(UserDTO userDto) throws UsersException {
		return userDta.enableAndRegisterUser(userDto);

	}

}
