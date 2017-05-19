package dta;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dtos.UserDTO;

import Constants.Constants;
import entities.Users;
import exceptions.CaveatEmptorException;

@Stateless
public class UserDTAImpl implements UserDTA {

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager entityManager;

	@Override
	public Users findUserByUsername(String username) throws CaveatEmptorException {
		Users user = new Users();
		try {
			 user = (Users) entityManager.createNamedQuery(Constants.FIND_USER_BY_USERNAME)
					.setParameter("username", username).getSingleResult();
			 
				 return user;
		} catch (Exception ex) {
					throw new CaveatEmptorException(
							"Exception into findUserByUsername method for username= " + username +"-> "+ ex.getMessage());
		}
	}
	
	@Override
	public Users findUserByEmail(String email) throws CaveatEmptorException {
		Users user = new Users();
		try {
			 user = (Users) entityManager.createNamedQuery(Constants.FIND_USER_BY_EMAIL)
					.setParameter("email", email).getSingleResult();
			return user;

		} catch (Exception ex) {
					throw new CaveatEmptorException(
							"Exception into findUserByUsername method for username= " + email +"-> "+ ex.getMessage());
		}
	}
	
	public boolean existUser(String option,String value) throws CaveatEmptorException {
		
		Users user=new Users();
		
		if(option.equals("username")){
			user=findUserByUsername(value);
			if(user!=null)
				return true;
		}
		if(option.equals("email")){
			user=findUserByEmail(value);
			if(user!=null)
				return true;
		}
		return false;
	}
	
	@Override
	public String createUser(UserDTO userDto) throws CaveatEmptorException
	{
		Users user=new Users();
		
		user.setFirstname(userDto.getFirstname());
		user.setLastname(userDto.getLastname());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setPhoneNumber(userDto.getPhoneNumber());
		user.setUsername(userDto.getUsername());
		
		boolean usernameExist=false;
		boolean emailExist=false;
		
		
		try{
			usernameExist=existUser("username",userDto.getUsername());
		}catch(CaveatEmptorException ex){
			ex.getMessage();
		}

		
		try{
			emailExist=existUser("email",userDto.getEmail());
		}catch(CaveatEmptorException ex){
			ex.getMessage();
		}
		
		if(emailExist)
			return "emailExist";
		else if(usernameExist)
			return "usernameExist";
		else{
			entityManager.persist(user);
			return "userCreated";
		}
		
	}

}