package beans;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;
import com.dtos.UserDTO;
import exceptions.UsersException;
import service.IUserService;
import service.SendEmailService;
import utils.FacesMessagesUtil;
import utils.SessionUtils;

@ManagedBean(name = "registerBean")
public class UserRegisterBean implements Serializable {

	private static final long serialVersionUID = 1283704450092269442L;

	@Size(min = 3)
	private String firstname;

	@Size(min = 3)
	private String lastname;

	@Size(min = 3)
	private String username;

	@Size(min = 3)
	private String email;

	@Size(min = 10)
	private String phoneNumber;

	@Size(min = 3)
	private String password;

	@Size(min = 3)
	private String repeatPassword;

	private UserDTO userDto;

	@EJB
	IUserService userService;
	
	HttpSession session = SessionUtils.getSession();

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public void register() throws UsersException {
		try {
			userDto = new UserDTO();
			String newUser;

			userDto.setFirstname(firstname);
			userDto.setLastname(lastname);
			userDto.setEmail(email);
			userDto.setUsername(username);
			userDto.setPhoneNumber(phoneNumber);
			userDto.setPassword(password);

			newUser = userService.createUser(userDto);
			if (newUser.equals("emailExist")) {
				FacesMessagesUtil.message_info("Cannot register!", "Email already exist!");
			} else if (newUser.equals("usernameExist")) {
				FacesMessagesUtil.message_info("Cannot register!", "Username already exist!");
			} else {
				String key=SendEmailService.sendEmail(userDto);
					if(key!=null){
						userService.insertKeyForUser(userDto,key);
						FacesMessagesUtil.message_info("An email was sent to your email address", "Please confirm it!");
					}	
					
					session.setAttribute("userDto", userDto);	
			}
		} catch (UsersException e) {
			e.getMessage();
		}
	}
}
