package com.dts.aoc.utility;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
import java.util.logging.Level;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import com.dts.core.db.DbCon;
import com.dts.core.util.DataObject;
import com.dts.core.util.LoggerManager;


public class InstantiationServlet extends HttpServlet 
{	
    public void init(ServletConfig sc)
    {
    	ServletContext ctx = sc.getServletContext();
    	DataObject dobject = new DataObject();
    	InputStream fis = ctx.getResourceAsStream(sc.getInitParameter("config"));
    	Properties props = new Properties();
   	 	try 
   	 	{
			props.load(fis);
		} 
   	 	catch (IOException ioe) {
   	 		ioe.printStackTrace();
			LoggerManager.logger.log(Level.INFO,ioe.toString(),ioe.fillInStackTrace());
		}
    	dobject.setProperties(props);
    	
    	LoggerManager.logger=new LoggerManager().getLogger(props.getProperty("logfile"));
    	LoggerManager.writeLogInfo("Logger Instatiated");
    	
    	DbCon dbcon = new DbCon();
    	Connection connection = dbcon.getConnection();
    	dobject.setConnection(connection);
    	
    	
    }
}
