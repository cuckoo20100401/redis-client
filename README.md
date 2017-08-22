# redis-client

A sample redis java client.

## Configuration

#### maven pom.xml
```xml
<!-- jedis -->
<dependency>
	<groupId>redis.clients</groupId>
	<artifactId>jedis</artifactId>
	<version>2.8.0</version>
</dependency>
<!-- log -->
<dependency>
	<groupId>log4j</groupId>
	<artifactId>log4j</artifactId>
	<version>1.2.17</version>
</dependency>
<dependency>
	<groupId>commons-logging</groupId>
	<artifactId>commons-logging</artifactId>
	<version>1.2</version>
</dependency>
```

#### spring applicationContext.xml

```xml
<!-- 装载配置文件 -->
<bean id="configurator" class="org.cuckoo.spring.webmvc.utils.Configurator">
	<property name="ignoreResourceNotFound" value="true"/>
	<property name="locations">
		<list>
			<value>classpath:redis.properties</value>
		</list>
	</property>
</bean>
<!-- 配置Redis客户端 -->
<bean id="redisClient" class="com.cuckoo.redis.client.impl.JedisRedisClient" init-method="initJedisPool" destroy-method="closeJedisPool">
	<property name="redis.host" value="${redis.host}"/>
	<property name="redis.port" value="${redis.port}"/>
	<property name="redis.password" value="${redis.password}"/>
	<property name="redis.database" value="${redis.database}"/>
	<property name="redis.pool.maxTotal" value="${redis.pool.maxTotal}"/>
	<property name="redis.pool.maxIdle" value="${redis.pool.maxIdle}"/>
	<property name="redis.pool.minIdle" value="${redis.pool.minIdle}"/>
	<property name="redis.pool.maxWaitMillis" value="${redis.pool.maxWaitMillis}"/>
	<property name="redis.pool.timeout" value="${redis.pool.timeout}"/>
	<property name="redis.pool.testOnBorrow" value="${redis.pool.testOnBorrow}"/>
	<property name="redis.pool.testWhileIdle" value="${redis.pool.testWhileIdle}"/>
	<property name="redis.pool.timeBetweenEvictionRunsMillis" value="${redis.pool.timeBetweenEvictionRunsMillis}"/>
</bean>
```
注：
- redisClient可通过注解@Autowired装配到需要的类
- 以上配置属性也可以任意省略，被省略的属性将会使用默认值初始化。默认密码是null，其它默认值可查看RedisConfiguration.java获知
- redis.properties内容参考类路径下面的对应文件即可
- 使用中记得引入log4j.properties
- 普通项目的用法请参考测试类