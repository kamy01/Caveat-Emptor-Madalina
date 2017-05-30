package RepositoryInterfaces;

import javax.ejb.Remote;
import com.dtos.UserDTO;
import entities.Users;
import exceptions.CaveatEmptorException;

@Remote
public interface UserRepository {
	public Users findUserByUsername(String username) throws CaveatEmptorException;

	public Users findUserByEmail(String email) throws CaveatEmptorException;
	
	public Users findUserByPhoneNumber(Long phoneNumber) throws CaveatEmptorException;

	public String createUser(UserDTO userDto,String repeatPassword) throws CaveatEmptorException;

	public void insertKeyForUser(UserDTO userDto, String key) throws CaveatEmptorException;

	public boolean enableAndRegisterUser(UserDTO userDto) throws CaveatEmptorException;

}
