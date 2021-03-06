package beans;

import java.io.Serializable;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import com.dtos.UserDTO;
import ServiceImplementation.SendEmailService;
import ServiceInterfaces.UserService;
import exceptions.CaveatEmptorException;
import utils.FacesMessagesUtil;
import utils.LoggerUtils;
import utils.SessionUtils;

@ManagedBean(name = "registerBean")
@SessionScoped
public class UserRegisterBean implements Serializable {

	private static final long serialVersionUID = 1283704450092269442L;


	private UserDTO userDto;
	private String repeatPassword;

	@EJB
	UserService userService;

	HttpSession session ;

	@PostConstruct
	public void init() {
		session = SessionUtils.getSession();
		userDto = new UserDTO();
	}

	public UserDTO getUserDto() {
		return userDto;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public void getNewUser() {
		userDto.setFirstname(getUserDto().getFirstname());
		userDto.setLastname(getUserDto().getLastname());
		userDto.setEmail(getUserDto().getEmail());
		userDto.setUsername(getUserDto().getUsername());
		userDto.setPhoneNumber(getUserDto().getPhoneNumber());
		userDto.setPassword(getUserDto().getPassword());
	}
	
	


	public void register() throws CaveatEmptorException {
		try {
			
			getNewUser();
			String newUser = userService.createUser(userDto,getRepeatPassword());
		
			switch (newUser) {
			case "emailExist":
				FacesMessagesUtil.message_info("Cannot register! Email already exist!", "");
				break;
			case "usernameExist":
				FacesMessagesUtil.message_info("Cannot register! Username already exist!", "");
				break;
			case "passwordDifferent":
				FacesMessagesUtil.message_info("Cannot register! Password doesn't match!", "");
				break;
			case "phoneNumberExist":
				FacesMessagesUtil.message_info("Cannot register! Phone Number already exist!", "");
				break;

			default:
					String key = SendEmailService.sendEmail(userDto);
					if (key != null) {
						userService.insertKeyForUser(userDto, key);								
						RequestContext context = RequestContext.getCurrentInstance();
						context.execute("PF('myDialogVar').show();");
					}
					session.setAttribute("userDto", userDto);
				break;
			}
			
		
		
		} catch (CaveatEmptorException e) {
			LoggerUtils.getLogger().log( Level.INFO, "Exception in register method from UserRegisterBean" ,e.getMessage());	
		}
		catch (Exception e) {
			FacesMessagesUtil.redirectPage("error.xhtml");
		}
	}
	
	public void cancelRegister() throws CaveatEmptorException {
		try {
			FacesMessagesUtil.redirectPage("index.xhtml");
		} catch (CaveatEmptorException e) {
			LoggerUtils.getLogger().log( Level.INFO, "Exception in cancelRegister method from UserRegisterBean" ,e.getMessage());	
		}
		catch (Exception e) {
			FacesMessagesUtil.redirectPage("error.xhtml");
		}
		
		
	}
	public void redirect() throws CaveatEmptorException {
		try {
			FacesMessagesUtil.redirectPage("index.xhtml");
		} catch (CaveatEmptorException e) {
			LoggerUtils.getLogger().log( Level.INFO, "Exception in redirect method from UserRegisterBean" ,e.getMessage());	
		}
		catch (Exception e) {
			FacesMessagesUtil.redirectPage("error.xhtml");
		}
	}
}
