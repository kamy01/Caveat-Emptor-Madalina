package beans;

import java.io.Serializable;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import com.dtos.UserDTO;
import ServiceInterfaces.UserService;
import exceptions.CaveatEmptorException;
import utils.FacesMessagesUtil;
import utils.LoggerUtils;
import utils.SessionUtils;

@ManagedBean(name = "userlogin")
@SessionScoped
public class UserLoginBean implements Serializable {

	private static final long serialVersionUID = 951727265860837267L;
	private String username;
	private String password;
	private UserDTO userDto;


	@EJB
	UserService userService;

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

	
	public UserDTO getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDTO userDto) {
		this.userDto = userDto;
	}

	public void login() throws CaveatEmptorException {
		try {
			userDto = userService.getUserByUsername(username);
		} catch (CaveatEmptorException e) {
			LoggerUtils.getLogger().log( Level.INFO, "Exception in login method from UserLoginBean" ,e.getMessage());	
		}
		catch (Exception e) {
			FacesMessagesUtil.redirectPage("error.xhtml");
		}
		if (userDto != null && password.equals(userDto.getPassword())) {
			try {
				HttpSession session = SessionUtils.getSession();
				session.setAttribute("userDto", userDto);
				if(userDto.isEnabled()){
					FacesMessagesUtil.redirectPage("templates/content.xhtml?userId="+userDto.getUserId());
				}
				else{
					FacesMessagesUtil.message_info("Account not activated! ", "Please confirm your email to activate this account!");
				}
			} catch (CaveatEmptorException e) {
				LoggerUtils.getLogger().log( Level.INFO, "Exception in login method from UserLoginBean" ,e.getMessage());	
			}
			catch (Exception e) {
				FacesMessagesUtil.redirectPage("error.xhtml");
			}

		} else {
			FacesMessagesUtil.message_info("Invalid Login!", "Please Try Again!");
		}
	}
}