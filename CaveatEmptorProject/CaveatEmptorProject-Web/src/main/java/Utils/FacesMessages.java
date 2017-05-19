package Utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesMessages {

	public void message_info(String title,String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, title, message));
    }
}
