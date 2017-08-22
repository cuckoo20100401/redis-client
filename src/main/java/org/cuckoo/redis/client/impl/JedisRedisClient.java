package org.cuckoo.redis.client.impl;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import org.cuckoo.redis.client.RedisClient;
import org.cuckoo.redis.client.model.RedisConfiguration;
import org.cuckoo.redis.client.utils.PropertiesConfigurationParser;
import org.cuckoo.redis.client.utils.SerializationUtils;

public class JedisRedisClient implements RedisClient {
	
	private static final Log logger = LogFactory.getLog(JedisRedisClient.class);

	private RedisConfiguration redis = new RedisConfiguration();
	private JedisPool jedisPool;
	
	public RedisConfiguration getRedis() {
		return redis;
	}
	public void setRedis(RedisConfiguration redis) {
		this.redis = redis;
	}
	
	/**
	 * 配置方式一：在Spring IOC容器中初始化
	 */
	public JedisRedisClient(){
	}
	/**
	 * 配置方式二：通过构造RedisConfiguration使用代码初始化
	 * @param redis
	 */
	public JedisRedisClient(RedisConfiguration redis){
		this.redis = redis;
		this.initJedisPool();
	}
	/**
	 * 配置方式三：通过properties配置文件初始化
	 * @param propertiesPath 示例："classpath:redis.properties"或"D:/redis.properties"
	 */
	public JedisRedisClient(String propertiesPath){
		RedisConfiguration redis = new RedisConfiguration();
		try {
			PropertiesConfigurationParser configuration = new PropertiesConfigurationParser(propertiesPath);
			redis.setHost(configuration.getAsString("redis.host", redis.getHost()));
			redis.setPort(configuration.getAsInt("redis.port", redis.getPort()));
			redis.setPassword(configuration.getAsString("redis.password", redis.getPassword()));
			redis.setDatabase(configuration.getAsInt("redis.database", redis.getDatabase()));
			redis.getPool().setMaxTotal(configuration.getAsInt("redis.pool.maxTotal", redis.getPool().getMaxTotal()));
			redis.getPool().setMaxIdle(configuration.getAsInt("redis.pool.maxIdle", redis.getPool().getMaxIdle()));
			redis.getPool().setMinIdle(configuration.getAsInt("redis.pool.minIdle", redis.getPool().getMinIdle()));
			redis.getPool().setMaxWaitMillis(configuration.getAsInt("redis.pool.maxWaitMillis", redis.getPool().getMaxWaitMillis()));
			redis.getPool().setTimeout(configuration.getAsInt("redis.pool.timeout", redis.getPool().getTimeout()));
			redis.getPool().setTestOnBorrow(configuration.getAsBoolean("redis.pool.testOnBorrow", redis.getPool().getTestOnBorrow()));
			redis.getPool().setTestWhileIdle(configuration.getAsBoolean("redis.pool.testWhileIdle", redis.getPool().getTestWhileIdle()));
			redis.getPool().setTimeBetweenEvictionRunsMillis(configuration.getAsInt("redis.pool.timeBetweenEvictionRunsMillis", redis.getPool().getTimeBetweenEvictionRunsMillis()));
		} catch (Exception e) {
			logger.info("Configuration file ["+propertiesPath+"] loaded failure, now the redis client use the default values");
		}
		this.redis = redis;
		this.initJedisPool();
	}

	/**
	 * 初始化jedisPool
	 */
	public void initJedisPool(){
		logger.info("initialization jedisPool started");
		logger.info(redis.toString());
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(redis.getPool().getMaxTotal());
		poolConfig.setMaxIdle(redis.getPool().getMaxIdle());
		poolConfig.setMinIdle(redis.getPool().getMinIdle());
		poolConfig.setMaxWaitMillis(redis.getPool().getMaxWaitMillis());
		poolConfig.setTestOnBorrow(redis.getPool().getTestOnBorrow());
		poolConfig.setTestWhileIdle(redis.getPool().getTestWhileIdle());
		poolConfig.setTimeBetweenEvictionRunsMillis(redis.getPool().getTimeBetweenEvictionRunsMillis());
		jedisPool = new JedisPool(poolConfig, redis.getHost(), redis.getPort(), redis.getPool().getTimeout(), redis.getPassword(), redis.getDatabase());
		logger.info("initialization jedisPool completed");
	}
	
	/**
	 * 关闭jedisPool
	 */
	public void closeJedisPool(){
		if(this.jedisPool != null && !this.jedisPool.isClosed()){
			logger.info("shutdown jedisPool started");
			logger.info(redis.toString());
			this.jedisPool.close();
			logger.info("shutdown jedisPool completed");
		}
	}
	
	@Override
	public String setObject(String key, Object object) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.set(key.getBytes(), SerializationUtils.serializeToByteArray(object));
		jedis.close();
		return result;
	}

	@Override
	public Object getObject(String key) {
		Jedis jedis = jedisPool.getResource();
		byte[] data = jedis.get(key.getBytes());
		jedis.close();
		if(data == null) return null;
		return SerializationUtils.deserializeFromByteArray(data);
	}

	@Override
	public JedisPool getJedisPool() {
		return jedisPool;
	}

	@Override
	public Jedis getJedis() {
		return jedisPool.getResource();
	}

	@Override
	public String setObject(String key, int seconds, Object object) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.setex(key.getBytes(), seconds, SerializationUtils.serializeToByteArray(object));
		jedis.close();
		return result;
	}

	@Override
	public Long deleteObject(String key) {
		Jedis jedis = jedisPool.getResource();
		return jedis.del(key.getBytes());
	}

	@Override
	public Boolean isObjectExists(String key) {
		Jedis jedis = jedisPool.getResource();
		return jedis.exists(key.getBytes());
	}

	@Override
	public Long remainingSeconds(String key) {
		Jedis jedis = jedisPool.getResource();
		return jedis.ttl(key.getBytes());
	}

	@Override
	public String renameObjectKey(String oldKey, String newKey) {
		Jedis jedis = jedisPool.getResource();
		return jedis.rename(oldKey.getBytes(), newKey.getBytes());
	}

	@Override
	public String[] allObjectKeys(String pattern) {
		Jedis jedis = jedisPool.getResource();
		Set<byte[]> byteKeySet = jedis.keys(pattern.getBytes());
		Object[] byteKeyArray = byteKeySet.toArray();
		String[] keys = new String[byteKeyArray.length];
		for(int i=0;i<byteKeyArray.length;i++){
			byte[] b = (byte[]) byteKeyArray[i];
			keys[i] = new String(b);
		}
		return keys;
	}
}
