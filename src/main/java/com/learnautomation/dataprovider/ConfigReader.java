package com.learnautomation.dataprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	
	public static String getProperty(String key)
	{
		Properties pro=new Properties();
		
		try 
		{
			pro.load(new FileInputStream(new File(System.getProperty("user.dir")+"/Config/Config.properties")));
		} catch (FileNotFoundException e) 
		{
			System.out.println("File not present "+e.getMessage());
		} catch (IOException e) 
		{
			System.out.println("Unable to load the file "+e.getMessage());
		}
		
		String value=pro.getProperty(key);
		
		return value;
	}
	

}
