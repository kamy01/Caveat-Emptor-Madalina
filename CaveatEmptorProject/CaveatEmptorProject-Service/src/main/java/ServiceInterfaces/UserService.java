package ServiceInterfaces;

import javax.ejb.Remote;
import com.dtos.UserDTO;
import exceptions.CaveatEmptorException;

@Remote
public interface UserService {
	public UserDTO getUserByUsername(String username) throws CaveatEmptorException;

	public UserDTO getUserByEmail(String email) throws CaveatEmptorException;

	public String createUser(UserDTO userDto) throws CaveatEmptorException;

	public void insertKeyForUser(UserDTO userDto, String key) throws CaveatEmptorException;

	public boolean enableAndRegisterUser(UserDTO userDto) throws CaveatEmptorException;
	
}
