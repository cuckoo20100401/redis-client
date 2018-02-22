package org.cuckoo.redis.client.model;

/**
 * Redis基本配置类
 */
public class RedisConfiguration {

	private String host = "localhost";
	private Integer port = 6379;
	private String password = null;
	private Integer database = 0;
	private RedisPoolConfiguration pool = new RedisPoolConfiguration();
	
	public RedisConfiguration(){
	}
	public RedisConfiguration(String host, Integer port, String password, Integer database) {
		this.host = host;
		this.port = port;
		this.password = password;
		this.database = database;
	}
	public RedisConfiguration(String host, Integer port, String password, Integer database, RedisPoolConfiguration pool) {
		this.host = host;
		this.port = port;
		this.password = password;
		this.database = database;
		this.pool = pool;
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getDatabase() {
		return database;
	}
	public void setDatabase(Integer database) {
		this.database = database;
	}
	public RedisPoolConfiguration getPool() {
		return pool;
	}
	public void setPool(RedisPoolConfiguration pool) {
		this.pool = pool;
	}
	@Override
	public String toString() {
		return "RedisConfiguration [host=" + host + ", port=" + port
				+ ", password=" + password + ", database=" + database
				+ ", pool=" + pool + "]";
	}
}
