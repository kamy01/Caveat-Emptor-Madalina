package ServiceImplementation;

import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.dtos.UserDTO;
import exceptions.CaveatEmptorException;
import utils.LoggerUtils;
import utils.SessionUtils;

public class SendEmailService {
	public static final String MAIL_REGISTRATION_SITE_LINK="http://localhost:8080//CaveatEmptorProject-Web/activate.xhtml";	

	public static String sendEmail(UserDTO userDto) throws CaveatEmptorException {
		try {
			Properties properties = getProperties();
			Session session = SessionUtils.createSession(properties);
			String key = UUID.randomUUID().toString();
			Message message = buildMessage(session, properties, userDto, key);
			Transport.send(message);
			return key;

		} catch (MessagingException e) {
			LoggerUtils.getLogger().log( Level.INFO, "Exception in sendEmail method from SendEmailService" ,e.getMessage());		
			throw new CaveatEmptorException();
		}
	}

	private static Message buildMessage(Session session, Properties properties, UserDTO userDto, String key)
			throws CaveatEmptorException {
		String link =MAIL_REGISTRATION_SITE_LINK + "?username="+userDto.getUsername()+"&key=" + key;

		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(properties.getProperty("mail.login.username")));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userDto.getEmail()));
			message.setSubject("Account activation");
			message.setText("Hello " + userDto.getFirstname() + " " + userDto.getLastname() + ", "
					+ System.lineSeparator() + "Please confirm the following link " + System.lineSeparator() + link);
		} catch (MessagingException e) {
			LoggerUtils.getLogger().log( Level.INFO, "Exception in buildMessage method from SendEmailService" ,e.getMessage());		
			throw new CaveatEmptorException();
		}
		return message;

	}

	private static Properties getProperties() throws CaveatEmptorException {
		Properties properties = new Properties();
		String filename = "config.properties";

		try (InputStream input = SendEmailService.class.getClassLoader().getResourceAsStream(filename)) {
			properties.load(input);
		}catch (Exception e) {
			LoggerUtils.getLogger().log( Level.INFO, "Exception in getProperties method from SendEmailService" ,e.getMessage());		
			throw new CaveatEmptorException();
		}
		return properties;
	}
}
