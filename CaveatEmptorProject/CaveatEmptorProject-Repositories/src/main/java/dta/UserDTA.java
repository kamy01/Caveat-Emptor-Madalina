package dta;

import javax.ejb.Remote;

import entities.Users;


@Remote
public interface UserDTA {
	public Users findUserByUsername(String username);

}
