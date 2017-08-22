package org.cuckoo.redis.client;

import org.cuckoo.redis.client.impl.JedisRedisClient;

public class Main_RedisClient {

	public static void main(String[] args) {

		RedisClient redisClient = new JedisRedisClient("classpath:redis.properties");
		
		//存储单个对象（永久保存）
		/*String result = redisClient.setObject("person", new Person("赵涛", 22));
		System.out.println("result:"+result); //保存成功返回"OK"
		Object object = redisClient.getObject("person");
		if(object != null){
			Person person = (Person) object;
			System.out.println("name:"+person.getName()+", age:"+person.getAge());
		}*/
		
		//存储集合（永久保存）
		/*List<Person> list = new ArrayList<Person>();
		list.add(new Person("李自成", 33));
		list.add(new Person("张学良", 28));
		list.add(new Person("黄宇宙", 23));
		String result = redisClient.setObject("personList", list);
		System.out.println("result:"+result); //保存成功返回"OK"
		Object object = redisClient.getObject("personList");
		if(object != null){
			List<Person> persons = (List<Person>) object;
			for(Person person: persons){
				System.out.println("list:"+person.getName()+" ,"+person.getAge());
			}
		}*/
		
		//存储对象（有效时间内保存）
		/*String result = redisClient.setObject("person", 5, new Person("赵涛", 28));
		System.out.println("result:"+result); //保存成功返回"OK"
		Object object = redisClient.getObject("person");
		if(object != null){
			Person person = (Person) object;
			System.out.println("name:"+person.getName()+", age:"+person.getAge());
		}*/
		
		//获取无对应key的数据，返回null
		/*Object obj = redisClient.getObject("person2");
		System.out.println("obj="+obj);*/
		
		/*System.out.println("是否存在："+redisClient.isObjectExists("personList"));
		System.out.println("剩余的有效时间："+redisClient.remainingSeconds("personList"));
		System.out.println("重命名："+redisClient.renameObjectKey("personList", "persons"));
		System.out.println("删除操作："+redisClient.deleteObject("name"));*/
		
		String[] keys = redisClient.allObjectKeys("*");
		System.out.println("key总数："+keys.length);
		for(String key: keys){
			System.out.println("key: "+key);
		}
	}
}
