package dta;

import javax.ejb.Remote;
import com.dtos.UserDTO;
import entities.Users;
import exceptions.UsersException;

@Remote
public interface UserDTA {
	public Users findUserByUsername(String username) throws UsersException;

	public Users findUserByEmail(String email) throws UsersException;

	public String createUser(UserDTO userDto) throws UsersException;

	public void insertKeyForUser(UserDTO userDto, String key) throws UsersException;

	public boolean enableAndRegisterUser(UserDTO userDto) throws UsersException;

}
