package service;

import javax.ejb.Remote;

import com.dtos.UserDTO;


@Remote
public interface IUserService {
	public UserDTO getUserByUsername(String username);
}
