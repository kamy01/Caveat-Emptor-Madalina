package utils;

import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.servlet.http.HttpSession;
public class SessionUtils {
	
	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}
	
	public static Session createSession(final Properties properties) {

		return Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(properties.getProperty("mail.login.username"),
						properties.getProperty("mail.login.password"));
			}
		});

	}
	
	

}
