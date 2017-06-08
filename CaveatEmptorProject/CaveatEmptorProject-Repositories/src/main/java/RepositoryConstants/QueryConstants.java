package RepositoryConstants;

public class QueryConstants {
	
	public static final String FIND_USER_BY_USERNAME = "Users.findUserByUsername";
	public static final String FIND_USER_BY_USERNAME_QUERY = "SELECT us FROM Users us WHERE us.username = :username";
	
	public static final String FIND_USER_BY_EMAIL = "Users.findUserByEmail";
	public static final String FIND_USER_BY_EMAIL_QUERY = "SELECT us FROM Users us WHERE us.email = :email";
	
	public static final String FIND_USER_BY_PHONENR = "Users.findUserByPhoneNumber";
	public static final String FIND_USER_BY_PHONENR_QUERY = "SELECT us FROM Users us WHERE us.phoneNumber = :phoneNumber";
	
	public static final String GET_CATEGORIES = "Categories.getCategories";
	public static final String GET_CATEGORIES_QUERY = "SELECT c FROM Categories c";
	
	public static final String GET_CHILDREN_FOR_CATEGORY = "Categories.getChildrenList";
	public static final String GET_CHILDREN_FOR_CATEGORY_QUERY = "SELECT categ FROM Categories categ where categ.parentId= :categoryId";
	
	public static final String GET_CATEGORY_BY_ID = "Categories.getCategoryById";
	public static final String GET_CATEGORY_BY_ID_QUERY = "SELECT categ FROM Categories categ where categ.categoryId=:categoryId";
	
	public static final String GET_MAX_CATEGORY_ID = "Categories.getMaxCategoryId";
	public static final String GET_MAX_CATEGORY_ID_QUERY = "SELECT categ.categoryId FROM Categories categ where categ.categoryId=(SELECT max(categ.categoryId) from Categories)";
	
	public static final String GET_ITEMS_TO_SELL = "Items.getItemsToSell";
	public static final String GET_ITEMS_TO_SELL_QUERY = "SELECT item FROM Items item where item.yourBid is null and item.userId=:userId";
	
	public static final String GET_ITEMS_TO_BUY = "Items.getItemsToBuy";
	public static final String GET_ITEMS_TO_BUY_QUERY = "SELECT item FROM Items item where item.yourBid is not null and item.userId<>:userId";
}
