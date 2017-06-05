package beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import com.dtos.UserDTO;

import ServiceInterfaces.UserService;
import exceptions.CaveatEmptorException;
import utils.Constants;
import utils.FacesMessagesUtil;

@ManagedBean(name ="activationAccount")
@ViewScoped
public class ActivationAccount implements Serializable {

	private static final long serialVersionUID = -921084566822174196L;

	private UserDTO userDto;
	private String usernameURL;
	private Timestamp current_timestamp;
	private Timestamp registeredDate;
	Date date = new Date();
	

	@EJB
	UserService userService;
	
		
	public String getUsernameURL() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("username");		 
	}
	

	public void setUsernameURL(String usernameURL) {
		this.usernameURL = usernameURL;
	}

	public void validateKey() throws CaveatEmptorException {
		
		  FacesContext fc = FacesContext.getCurrentInstance();
	      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	      usernameURL =  params.get("username"); 

		try {
			userDto=userService.getUserByUsername(usernameURL);
			
			current_timestamp = new Timestamp(date.getTime());
			registeredDate=userDto.getDateRegistered();
			long differenceInSeconds=(current_timestamp.getTime()-registeredDate.getTime())/1000;
			
			if (differenceInSeconds > 86400) 
			{
				FacesMessagesUtil.message_info("This link is expired!", "Only 24 hours was valid!");
			}
			else {
				boolean enabled=userService.enableAndRegisterUser(userDto);
				if(enabled){
					FacesMessagesUtil.redirectPage("templates/content.xhtml");
				}
				else{
					FacesMessagesUtil.message_info("Account already enabled!", "");
				}
			}
		} catch (CaveatEmptorException e) {
			Constants.getLogger().log( Level.INFO, "Exception in validateKey method from ActivationAccount" ,e.getMessage());		
		}
		catch (Exception e) {
			FacesMessagesUtil.redirectPage("error.xhtml");
		}
	}
}