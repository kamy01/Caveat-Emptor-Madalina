package utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class Constants {

	public static final String MAIL_REGISTRATION_SITE_LINK="http://localhost:8080//CaveatEmptorProject-Web/activate.xhtml";
	
	
	public static Level level=Level.ALL;


	public static Double formatDouble(Double value) throws ParseException{
		DecimalFormat df = new DecimalFormat(".##");
		df.setRoundingMode(RoundingMode.DOWN);
		
		if(value!=null){
		if(value % 1 == 0 || (value.toString().contains("0.00") ))
		{
			return value;
		}else{
				String valueString = df.format(value); 
				return (Double) df.parse(valueString);
			}
		}
		else{
			return null;
		}
	}
	
	 public static final Logger getLogger() {
	        final Throwable t = new Throwable();
	        final StackTraceElement methodCaller = t.getStackTrace()[1];
	        final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(methodCaller.getClassName());
	        logger.setLevel(level);

	        return logger;
	    }
	
	
    
}
