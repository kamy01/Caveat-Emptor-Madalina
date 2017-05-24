package utils;

public final class Constants {
	public static final String FIND_USER_BY_USERNAME = "Users.findUserByUsername";
	public static final String FIND_USER_BY_USERNAME_QUERY = "SELECT us FROM Users us WHERE us.username = :username";
	
	public static final String FIND_USER_BY_EMAIL = "Users.findUserByEmail";
	public static final String FIND_USER_BY_EMAIL_QUERY = "SELECT us FROM Users us WHERE us.email = :email";
	
	public static final String GET_CATEGORIES = "Categories.getCategories";
	public static final String GET_CATEGORIES_QUERY = "SELECT categ FROM Categories categ";

	public static final String MAIL_REGISTRATION_SITE_LINK="http://localhost:8080//CaveatEmptorProject-Web/activate.xhtml";
	
    
}
