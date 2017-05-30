package ServiceInterfaces;

import javax.ejb.Remote;
import com.dtos.UserDTO;
import exceptions.CaveatEmptorException;

@Remote
public interface UserService {
	public UserDTO getUserByUsername(String username) throws CaveatEmptorException;

	public UserDTO getUserByEmail(String email) throws CaveatEmptorException;
	
	public UserDTO getUserByPhoneNumber(Long phoneNumber) throws CaveatEmptorException;

	public String createUser(UserDTO userDto,String repeatPassword) throws CaveatEmptorException;

	public void insertKeyForUser(UserDTO userDto, String key) throws CaveatEmptorException;

	public boolean enableAndRegisterUser(UserDTO userDto) throws CaveatEmptorException;
	
}
