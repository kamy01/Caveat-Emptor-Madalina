package RepositoryConstants;

public class QueryConstants {
	
	public static final String FIND_USER_BY_USERNAME = "Users.findUserByUsername";
	public static final String FIND_USER_BY_USERNAME_QUERY = "SELECT us FROM Users us WHERE us.username = :username";
	
	public static final String FIND_USER_BY_EMAIL = "Users.findUserByEmail";
	public static final String FIND_USER_BY_EMAIL_QUERY = "SELECT us FROM Users us WHERE us.email = :email";
	
	public static final String GET_CATEGORIES = "Categories.getCategories";
	public static final String GET_CATEGORIES_QUERY = "SELECT c FROM Categories c";
	
	public static final String GET_ROOT = "Categories.getRootCategory";
	public static final String GET_ROOT_QUERY = "SELECT categ FROM Categories categ where categ.id_category=0";
	
	public static final String GET_CHILDREN_FOR_CATEGORY = "Categories.getChildrenList";
	public static final String GET_CHILDREN_FOR_CATEGORY_QUERY = "SELECT categ FROM Categories categ where categ.parent_id= :parent_id";
	
}
