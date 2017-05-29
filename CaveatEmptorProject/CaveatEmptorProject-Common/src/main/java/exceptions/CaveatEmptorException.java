package exceptions;

public class CaveatEmptorException extends Exception{

	private static final long serialVersionUID = 6844703856998242174L;
	public CaveatEmptorException(){}

	public CaveatEmptorException(String message){
		super(message);
	}

	

}
