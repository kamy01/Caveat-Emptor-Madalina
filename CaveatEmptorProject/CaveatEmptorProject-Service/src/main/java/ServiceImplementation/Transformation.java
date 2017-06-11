package ServiceImplementation;

import java.text.ParseException;

import com.dtos.CategoriesDTO;
import com.dtos.ItemsDTO;
import com.dtos.UserDTO;

import entities.Categories;
import entities.Items;
import entities.Users;

public class Transformation {
	
public static UserDTO transformUserEntityToDto(Users user){
	UserDTO userDto = new UserDTO();
		if(user!=null){
		userDto.setUserId(user.getUserId());
		userDto.setFirstname(user.getFirstname());
		userDto.setLastname(user.getLastname());
		userDto.setAdmin(user.isAdmin());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setPhoneNumber(user.getPhoneNumber());
		userDto.setRanking(user.getRanking());
		userDto.setUsername(user.getUsername());
		userDto.setActivationKey(user.getActivationKey());
		userDto.setDateRegistered(user.getDateRegistered());
		userDto.setEnabled(user.isEnabled());
		}
		return userDto;
		
	}

	public static CategoriesDTO CategoriesEntityToDto(Categories categories){
		 CategoriesDTO categoriesDto=new CategoriesDTO();		
		categoriesDto.setCategoryId(categories.getCategoryId());
		categoriesDto.setParentId(categories.getParentId());
		categoriesDto.setNameCategory(categories.getNameCategory());
		categoriesDto.setDescription(categories.getDescription());
		return categoriesDto;
	}
	public static Categories CategoriesDtoToEntity(CategoriesDTO categoriesDto){
		Categories categories=new Categories();		
		categories.setCategoryId(categoriesDto.getCategoryId());
		categories.setParentId(categoriesDto.getParentId());
		categories.setNameCategory(categoriesDto.getNameCategory());
		categories.setDescription(categoriesDto.getDescription());
		return categories;
	}
	public static Items itemDtoToEntity(ItemsDTO itemDto) throws ParseException{
		Items item=new Items();
		item.setItemId(itemDto.getItemId());
		item.setUserId(itemDto.getUserId());
		item.setName(itemDto.getName());
		item.setDescription(itemDto.getDescription());
		item.setCategories(itemDto.getCategories());
		item.setInitialPrice(itemDto.getInitialPrice());
		item.setBestBid(itemDto.getBestBid());
		item.setYourBid(itemDto.getYourBid());
		item.setNrBids(itemDto.getNrBids());
		item.setBiddingStartDate(itemDto.getBiddingStartDate());
		item.setBiddingEndDate(itemDto.getBiddingEndDate());
		item.setStatus(itemDto.getStatus());
		item.setWinner(itemDto.getWinner());
		return item;
	}
	public static ItemsDTO populateItemDto(ItemsDTO itemDto) throws ParseException{
		ItemsDTO item=new ItemsDTO();
		item.setItemId(itemDto.getItemId());
		item.setUserId(itemDto.getUserId());
		item.setName(itemDto.getName());
		item.setDescription(itemDto.getDescription());
		item.setCategories(itemDto.getCategories());
		item.setInitialPrice(itemDto.getInitialPrice());
		item.setBestBid(itemDto.getBestBid());
		item.setYourBid(itemDto.getYourBid());
		item.setNrBids(itemDto.getNrBids());
		item.setBiddingStartDate(itemDto.getBiddingStartDate());
		item.setBiddingEndDate(itemDto.getBiddingEndDate());
		item.setStatus(itemDto.getStatus());
		item.setWinner(itemDto.getWinner());
		return item;
	}
	public static ItemsDTO itemEntityToDto(Items item) throws ParseException{
		ItemsDTO itemDto=new ItemsDTO();
		itemDto.setItemId(item.getItemId());
		itemDto.setUserId(item.getUserId());
		itemDto.setName(item.getName());
		itemDto.setDescription(item.getDescription());
		itemDto.setCategories(item.getCategories());
		itemDto.setInitialPrice(item.getInitialPrice());
		itemDto.setBestBid(item.getBestBid());
		itemDto.setYourBid(item.getYourBid());
		itemDto.setNrBids(item.getNrBids());
		itemDto.setBiddingStartDate(item.getBiddingStartDate());
		itemDto.setBiddingEndDate(item.getBiddingEndDate());
		itemDto.setStatus(item.getStatus());
		itemDto.setWinner(item.getWinner());
		return itemDto;
	}
}
