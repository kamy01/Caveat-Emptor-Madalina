package service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.dtos.UserDTO;

import exceptions.UsersException;
import utils.Constants;
import utils.SessionUtils;

public class SendEmailService {

	public static String sendEmail(UserDTO userDto) throws UsersException {
		try {
			Properties properties = getProperties();
			Session session = SessionUtils.createSession(properties);
			String key = UUID.randomUUID().toString();
			Message message = buildMessage(session, properties, userDto, key);
			Transport.send(message);
			return key;

		} catch (MessagingException | IOException e) {
			throw new UsersException("Exception in sendEmail method from Service");
		}
	}

	private static Message buildMessage(Session session, Properties properties, UserDTO userDto, String key)
			throws UsersException {
		String link = Constants.MAIL_REGISTRATION_SITE_LINK + "?username="+userDto.getUsername()+"&key=" + key;

		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(properties.getProperty("mail.login.username")));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userDto.getEmail()));
			message.setSubject("Account activation");
			message.setText("Hello " + userDto.getFirstname() + " " + userDto.getLastname() + ", "
					+ System.lineSeparator() + "Please confirm the following link " + System.lineSeparator() + link);
		} catch (MessagingException e) {
			throw new UsersException("Exception in buildMessage method from Service");
		}
		return message;

	}

	private static Properties getProperties() throws IOException {
		Properties properties = new Properties();
		String filename = "config.properties";

		try (InputStream input = SendEmailService.class.getClassLoader().getResourceAsStream(filename)) {
			properties.load(input);
		}
		return properties;
	}
}
