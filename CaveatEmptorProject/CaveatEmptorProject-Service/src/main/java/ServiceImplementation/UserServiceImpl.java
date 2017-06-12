package ServiceImplementation;

import java.util.logging.Level;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.dtos.UserDTO;
import RepositoryInterfaces.UserRepository;
import ServiceInterfaces.UserService;
import entities.Users;
import exceptions.CaveatEmptorException;
import utils.LoggerUtils;

@Stateless
public class UserServiceImpl implements UserService {

	@EJB
	UserRepository userReposity;

	UserDTO userDto;

	
	
	@Override
	public UserDTO getUserByUsername(String username) throws CaveatEmptorException {
		
		try{
			Users user = userReposity.findUserByUsername(username);
			return Transformation.transformUserEntityToDto(user);
			
		}catch(Exception e){
			LoggerUtils.getLogger().log( Level.INFO, "Exception in getUserByUsername method from UserServiceImpl" ,e.getMessage());		
			throw new CaveatEmptorException();			
		}
	}

	@Override
	public UserDTO getUserByEmail(String email) throws CaveatEmptorException {
		try{
			Users user = userReposity.findUserByEmail(email);	
			return Transformation.transformUserEntityToDto(user);	
		}catch(Exception e){
			LoggerUtils.getLogger().log( Level.INFO, "Exception in getUserByEmail method from UserServiceImpl" ,e.getMessage());		
			throw new CaveatEmptorException();			
		}
	}
	
	@Override
	public UserDTO getUserByPhoneNumber(Long phoneNumber) throws CaveatEmptorException {
		try{
			Users user = userReposity.findUserByPhoneNumber(phoneNumber);
			return Transformation.transformUserEntityToDto(user);	
		}catch(Exception e){
			LoggerUtils.getLogger().log( Level.INFO, "Exception in getUserByPhoneNumber method from UserServiceImpl" ,e.getMessage());		
			throw new CaveatEmptorException();			
		}
	}

	@Override
	public String createUser(UserDTO userDto,String repeatPassword) throws CaveatEmptorException {
		try{
			return userReposity.createUser(userDto,repeatPassword);
		}catch(Exception e){
			LoggerUtils.getLogger().log( Level.INFO, "Exception in createUser method from UserServiceImpl" ,e.getMessage());		
			throw new CaveatEmptorException();			
		}
	}

	@Override
	public void insertKeyForUser(UserDTO userDto, String key) throws CaveatEmptorException {
		try{
			userReposity.insertKeyForUser(userDto, key);
		}catch(Exception e){
			LoggerUtils.getLogger().log( Level.INFO, "Exception in insertKeyForUser method from UserServiceImpl" ,e.getMessage());		
			throw new CaveatEmptorException();			
		}
	}

	@Override
	public boolean enableAndRegisterUser(UserDTO userDto) throws CaveatEmptorException {
		try{
			return userReposity.enableAndRegisterUser(userDto);
		}catch(Exception e){
			LoggerUtils.getLogger().log( Level.INFO, "Exception in enableAndRegisterUser method from UserServiceImpl" ,e.getMessage());		
			throw new CaveatEmptorException();			
		}

	}

}
