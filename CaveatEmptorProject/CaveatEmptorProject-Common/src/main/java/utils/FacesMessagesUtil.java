package utils;

import java.io.IOException;
import java.util.logging.Level;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import exceptions.CaveatEmptorException;

public class FacesMessagesUtil {

	public static void redirectPage(String page) throws  CaveatEmptorException{
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try{

			 String username = (String) context.getSessionMap().get("username");

			    if (username == null) {
			    	context.redirect(page);
			        return;
			    }

		}catch(IOException ex){
			Constants.getLogger().log( Level.INFO, "Redirect page exception" ,ex.getMessage());		
			throw new CaveatEmptorException();
		}
	}
	
	
	public static void message_info(String title,String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title, message));
    }
	
	public static void message_error(String title,String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, title, message));
    }
	
}
