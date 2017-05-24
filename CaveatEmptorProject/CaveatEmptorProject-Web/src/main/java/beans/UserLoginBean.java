package beans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;

import com.dtos.UserDTO;

import exceptions.UsersException;
import service.IUserService;
import utils.FacesMessagesUtil;
import utils.SessionUtils;

@ManagedBean(name = "userlogin")
public class UserLoginBean implements Serializable {

	private static final long serialVersionUID = 951727265860837267L;
	private String username;
	private String password;
	private UserDTO userDto;
	

	@EJB
	IUserService userService;

	@PostConstruct
	public void init() {
		userDto = new UserDTO();
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void login() throws UsersException {
		try {
			userDto = userService.getUserByUsername(username);
		} catch (UsersException e) {
			e.getMessage();
		}
		if (userDto != null && password.equals(userDto.getPassword())) {
			try {
				HttpSession session = SessionUtils.getSession();
				session.setAttribute("userDto", userDto);
				if(userDto.isEnabled()){
					FacesMessagesUtil.redirectPage("home.xhtml");
				}
				else{
					FacesMessagesUtil.message_info("Account not activated! ", "Please confirm your email to activate this account!");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			FacesMessagesUtil.message_info("Invalid Login!", "Please Try Again!");
		}
	}
}