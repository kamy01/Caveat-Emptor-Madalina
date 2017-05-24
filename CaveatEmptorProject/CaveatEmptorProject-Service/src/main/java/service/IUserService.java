package service;

import java.sql.Timestamp;

import javax.ejb.Remote;
import com.dtos.UserDTO;
import exceptions.UsersException;

@Remote
public interface IUserService {
	public UserDTO getUserByUsername(String username) throws UsersException;

	public UserDTO getUserByEmail(String email) throws UsersException;

	public String createUser(UserDTO userDto) throws UsersException;

	public void insertKeyForUser(UserDTO userDto, String key) throws UsersException;

	public boolean enableAndRegisterUser(UserDTO userDto) throws UsersException;
	
}
