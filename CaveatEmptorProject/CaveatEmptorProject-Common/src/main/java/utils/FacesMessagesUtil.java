package utils;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import exceptions.UsersException;

public class FacesMessagesUtil {

	public static void redirectPage(String page) throws IOException, UsersException{
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try{

			 String username = (String) context.getSessionMap().get("username");

			    if (username == null) {
			    	context.redirect(page);
			        return;
			    }

		}catch(IOException ex){
			throw new UsersException("Redirect page exception"+ ex);
		}
	}
	
	
	public static void message_info(String title,String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title, message));
    }
	
}
