package dta;

import javax.ejb.Remote;

import com.dtos.UserDTO;

import entities.Users;
import exceptions.CaveatEmptorException;

@Remote
public interface UserDTA {
	public Users findUserByUsername(String username) throws CaveatEmptorException;
	public Users findUserByEmail(String email) throws CaveatEmptorException;
	public String createUser(UserDTO userDto) throws CaveatEmptorException;


}
