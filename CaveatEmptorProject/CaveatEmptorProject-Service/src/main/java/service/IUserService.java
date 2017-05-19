package service;

import javax.ejb.Remote;
import com.dtos.UserDTO;
import exceptions.CaveatEmptorException;

@Remote
public interface IUserService {
	public UserDTO getUserByUsername(String username) throws CaveatEmptorException;

	public UserDTO getUserByEmail(String email) throws CaveatEmptorException;

	public String createUser(UserDTO userDto) throws CaveatEmptorException;
}
