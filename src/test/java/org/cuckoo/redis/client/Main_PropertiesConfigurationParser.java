package org.cuckoo.redis.client;

import org.cuckoo.redis.client.utils.PropertiesConfigurationParser;

public class Main_PropertiesConfigurationParser {

	public static void main(String[] args) {

		try {
			PropertiesConfigurationParser configuration = new PropertiesConfigurationParser("classpath:redis.properties");
			System.out.println("正常情况："+configuration.getAsInt("redis.port"));
			System.out.println("key不存在的情况："+configuration.getAsInt("redis.port2"));
			System.out.println("key不存在就使用默认值的情况"+configuration.getAsInt("redis.port2", 8080));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
