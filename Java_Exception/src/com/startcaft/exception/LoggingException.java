package com.startcaft.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public class LoggingException extends Throwable {

	public static Logger logger = 
			Logger.getLogger("LoggingException");
	
	public LoggingException(){
		StringWriter sWriter = new StringWriter();
		printStackTrace(new PrintWriter(sWriter));
		logger.severe(sWriter.toString());
	}
}
