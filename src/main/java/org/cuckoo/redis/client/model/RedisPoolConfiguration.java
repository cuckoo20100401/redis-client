package org.cuckoo.redis.client.model;

/**
 * Redis池配置类
 */
public class RedisPoolConfiguration {

	private Integer maxTotal = 10000;
	private Integer maxIdle = 100;
	private Integer minIdle = 10;
	private Integer maxWaitMillis = 15000;
	private Integer timeout = 15000;
	private Boolean testOnBorrow = true;
	private Boolean testWhileIdle = true;
	private Integer timeBetweenEvictionRunsMillis = 60000;
	
	public Integer getMaxTotal() {
		return maxTotal;
	}
	public void setMaxTotal(Integer maxTotal) {
		this.maxTotal = maxTotal;
	}
	public Integer getMaxIdle() {
		return maxIdle;
	}
	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}
	public Integer getMinIdle() {
		return minIdle;
	}
	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}
	public Integer getMaxWaitMillis() {
		return maxWaitMillis;
	}
	public void setMaxWaitMillis(Integer maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}
	public Integer getTimeout() {
		return timeout;
	}
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	public Boolean getTestOnBorrow() {
		return testOnBorrow;
	}
	public void setTestOnBorrow(Boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}
	public Boolean getTestWhileIdle() {
		return testWhileIdle;
	}
	public void setTestWhileIdle(Boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}
	public Integer getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}
	public void setTimeBetweenEvictionRunsMillis(
			Integer timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}
	@Override
	public String toString() {
		return "RedisPoolConfiguration [maxTotal=" + maxTotal + ", maxIdle="
				+ maxIdle + ", minIdle=" + minIdle + ", maxWaitMillis="
				+ maxWaitMillis + ", timeout=" + timeout + ", testOnBorrow="
				+ testOnBorrow + ", testWhileIdle=" + testWhileIdle
				+ ", timeBetweenEvictionRunsMillis="
				+ timeBetweenEvictionRunsMillis + "]";
	}
}
