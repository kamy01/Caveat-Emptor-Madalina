package beans;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.dtos.UserDTO;

import Utils.SessionUtils;
import exceptions.CaveatEmptorException;
import service.IUserService;

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

	public void login() throws CaveatEmptorException {

		try {
			userDto = userService.getUserByUsername(username);
		} catch (CaveatEmptorException e) {
			e.getMessage();
		}

		if (userDto != null && password.equals(userDto.getPassword())) {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

			try {
				HttpSession session = SessionUtils.getSession();
				session.setAttribute("userDto", userDto);
				context.redirect("home.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid Login!", "Please Try Again!"));
		}
	}
}