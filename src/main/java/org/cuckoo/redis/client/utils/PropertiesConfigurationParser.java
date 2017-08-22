package org.cuckoo.redis.client.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * properties配置文件解析器
 * @version 1.0
 */
public class PropertiesConfigurationParser {
	
	private final static Log logger = LogFactory.getLog(PropertiesConfigurationParser.class);

	private Properties prop = new Properties();
	
	/**
	 * 构造函数
	 * @param propertiesPath 1、配置文件在类路径下时，参数示例为："classpath:redis.properties"；2、配置文件不在类路径下时，传递绝对路径
	 */
	public PropertiesConfigurationParser(String propertiesPath) throws IOException,FileNotFoundException {
		InputStream inputStream = null;
		try {
			if(propertiesPath.toLowerCase().startsWith("classpath:")){
				logger.info("Loading properties file from class path resource ["+propertiesPath.split(":")[1]+"]");
				inputStream = getClass().getResourceAsStream("/"+propertiesPath.split(":")[1]);
			}else{
				logger.info("Loading properties file ["+propertiesPath+"]");
				inputStream = new BufferedInputStream(new FileInputStream(propertiesPath));
			}
			prop.load(inputStream);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("The file ["+propertiesPath+"] is not exist");
		} catch (IOException e) {
			throw new IOException();
		} catch (NullPointerException e) {
			throw new FileNotFoundException("The file ["+propertiesPath+"] is not exist");
		}
	}
	
	/**
	 * 获取key的value，若无指定的key时返回null
	 * @param key
	 * @return
	 */
	public String getAsString(String key){
		return prop.getProperty(key);
	}
	
	/**
	 * 获取key的value，若无指定的key时返回defaultValue
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String getAsString(String key, String defaultValue){
		return prop.getProperty(key, defaultValue);
	}
	
	/**
	 * 获取key的value，若无指定的key时返回null
	 * @param key
	 * @return
	 */
	public Integer getAsInt(String key){
		String value = prop.getProperty(key);
		if(value == null || value.trim().length() == 0){
			return null;
		}
		return Integer.valueOf(value);
	}
	
	/**
	 * 获取key的value，若无指定的key时返回defaultValue
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public Integer getAsInt(String key, Integer defaultValue){
		String value = prop.getProperty(key, String.valueOf(defaultValue));
		if(value == null || value.trim().length() == 0){
			return null;
		}
		return Integer.valueOf(value);
	}
	
	/**
	 * 获取key的value，若无指定的key时返回null
	 * @param key
	 * @return
	 */
	public Boolean getAsBoolean(String key){
		String value = prop.getProperty(key);
		if(value == null || value.trim().length() == 0){
			return null;
		}
		return Boolean.valueOf(value);
	}
	
	/**
	 * 获取key的value，若无指定的key时返回defaultValue
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public Boolean getAsBoolean(String key, Boolean defaultValue){
		String value = prop.getProperty(key, String.valueOf(defaultValue));
		if(value == null || value.trim().length() == 0){
			return null;
		}
		return Boolean.valueOf(value);
	}
}
