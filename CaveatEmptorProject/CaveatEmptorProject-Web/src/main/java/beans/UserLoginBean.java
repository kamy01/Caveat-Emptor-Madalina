package beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.dtos.UserDTO;
import service.IUserService;


@ManagedBean(name ="userlogin")
@ApplicationScoped
public class UserLoginBean implements Serializable{

	private static final long serialVersionUID = 951727265860837267L;
	private String username;
	private String password;
	
	UserDTO userDto;
	
	@EJB
	IUserService userService;
	
	@PostConstruct
	public void init(){
		userDto=new UserDTO();
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

	public void login(){
	
		userDto=userService.getUserByUsername(username);
		
			 if (userDto != null && password.equals(userDto.getPassword())) {
				/////
			 }
	
	

}
}