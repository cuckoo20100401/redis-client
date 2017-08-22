package org.cuckoo.redis.client;

import org.cuckoo.redis.client.impl.JedisRedisClient;
import org.cuckoo.redis.client.model.RedisConfiguration;

/**
 * 演示初始化RedisClient的2种方式，第一种方式是在Spring IOC容器中初始化
 */
public class Main_Init_RedisClient {

	public static void main(String[] args) {
		init2();
		init3();
	}

	/**
	 * 方式二，没有设置的参数将会使用默认值
	 */
	public static void init2(){
		RedisConfiguration redis = new RedisConfiguration();
		redis.setHost("127.0.0.1");
		redis.getPool().setMaxWaitMillis(12000);
		redis.getPool().setTimeBetweenEvictionRunsMillis(2000);
		RedisClient redisClient = new JedisRedisClient(redis);
		System.out.println("init2: "+redisClient.getObject("person"));
	}
	
	/**
	 * 方式三，没有设置的参数将会使用默认值
	 */
	public static void init3(){
		RedisClient redisClient = new JedisRedisClient("classpath:redis.properties");
		System.out.println("init3: "+redisClient.getObject("person"));
	}
}
