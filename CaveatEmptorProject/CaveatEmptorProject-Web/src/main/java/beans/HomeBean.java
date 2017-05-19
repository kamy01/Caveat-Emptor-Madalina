package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;

import com.dtos.UserDTO;

import Utils.SessionUtils;

@ManagedBean(name = "homeBean")
public class HomeBean implements Serializable{
	
	private static final long serialVersionUID = -2520409578333653645L;
	HttpSession session = SessionUtils.getSession();	
	
	private UserDTO userDto;
	
	public UserDTO getUserDto() {
		userDto=(UserDTO) session.getAttribute("userDto");
		return userDto;
	}

	
	
	
	
	
	
}
