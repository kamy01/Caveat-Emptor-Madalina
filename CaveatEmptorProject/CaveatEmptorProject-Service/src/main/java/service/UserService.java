package service;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dtos.UserDTO;

import dta.UserDTA;
import entities.Users;



@Stateless
public class UserService implements IUserService{
	
	@PersistenceContext
	EntityManager entityManager;
	
	@EJB
	UserDTA userDta;
	
	UserDTO userDto;
	
	
	@Override
	public UserDTO getUserByUsername(String username) {
		Users user=userDta.findUserByUsername(username);
		
		userDto=new UserDTO();
		userDto.setFirstname(user.getFirstname());
		userDto.setLastname(user.getLastname());
		userDto.setAdmin(user.isAdmin());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setPhoneNumber(user.getPhoneNumber());
		userDto.setRanking(user.getRanking());
		userDto.setUsername(user.getUsername());
		
		
		return userDto;
	}
	
	

	
	
	
	

	
	

	
	


	
	
	
	

	
}
