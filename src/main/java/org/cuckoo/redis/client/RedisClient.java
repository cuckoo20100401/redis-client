package org.cuckoo.redis.client;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis客户端接口
 * 注：此接口只针对Object的操作，设置的key都会被转化成字节数组。普通元素操作可以通过getJedis()方法调用原始API实现
 */
public interface RedisClient {
	
	/**
	 * 获取JedisPool，用于自定义代码操作Redis服务器，操作完后记得调用jedisPool.close();
	 * @return
	 */
	public JedisPool getJedisPool();
	
	/**
	 * 获取Jedis，用于自定义代码操作Redis服务器，操作完后记得调用jedis.close();
	 * @return
	 */
	public Jedis getJedis();
	
	/**
	 * 设置对象，永久保存
	 * @param key
	 * @param object
	 * @return 保存成功返回"OK"
	 */
	public String setObject(String key, Object object);
	
	/**
	 * 设置对象，在指定的有效期内有效
	 * @param key
	 * @param seconds
	 * @param object
	 * @return 保存成功返回"OK"
	 */
	public String setObject(String key, int seconds, Object object);
	
	/**
	 * 获取对象，若无对应的key时返回null
	 * @param key
	 * @return
	 */
	public Object getObject(String key);
	
	/**
	 * 删除对象
	 * @param key
	 * @return 删除成功返回1 删除失败（或者不存在）返回 0
	 */
	public Long deleteObject(String key);
	
	/**
	 * 指定的key是否存在
	 * @param key
	 * @return
	 */
	public Boolean isObjectExists(String key);
	
	/**
	 * 获取给定key剩余的有效时间
	 * @param key
	 * @return 如果是-1则表示永远有效，如果是-2则表示指定的key不存在
	 */
	public Long remainingSeconds(String key);
	
	/**
	 * 重命名key操作，如果指定的key不存在，会抛出异常
	 * @param oldKey
	 * @param newKey
	 * @return 操作成功返回"OK"
	 */
	public String renameObjectKey(String oldKey, String newKey);
	
	/**
	 * 根据给定的模板获取所有的key
	 * 示例："*"表示当前库中所有的key；"user*"表示当前库中所有以user开头的key
	 * @param pattern
	 * @return 如果没有key，返回一个长度为0的数组
	 */
	public String[] allObjectKeys(String pattern);
}
