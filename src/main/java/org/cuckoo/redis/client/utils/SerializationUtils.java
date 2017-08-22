package org.cuckoo.redis.client.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 对象序列化工具
 * @version 1.0
 */
public class SerializationUtils {

	/**
	 * 序列化到字节数组
	 * @param object 被序列化对象
	 * @return
	 */
	public static byte[] serializeToByteArray(Object object){
		
		byte[] data = null;
		
		ObjectOutputStream objectOutputStream = null;
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(object);
			data = byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeObjectOutputStream(objectOutputStream);
		}
		return data;
	}
	
	/**
	 * 序列化到文件
	 * @param object 被序列化对象
	 * @param filepath 保存序列化数据的文件
	 */
	public static void serializeToFile(Object object, String filepath){
		ObjectOutputStream objectOutputStream = null;
		try {
			File saveFile = new File(filepath);
			objectOutputStream = new ObjectOutputStream(new FileOutputStream(saveFile));
			objectOutputStream.writeObject(object);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeObjectOutputStream(objectOutputStream);
		}
	}
	
	/**
	 * 反序列化从指定的字节数组
	 * @param data 被反序列化的字节数组
	 * @return
	 */
	public static Object deserializeFromByteArray(byte[] data){
		
		Object object = null;
		
		ObjectInputStream objectInputStream = null;
		try {
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
			objectInputStream = new ObjectInputStream(byteArrayInputStream);
			object = objectInputStream.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeObjectInputStream(objectInputStream);
		}
		return object;
	}
	
	/**
	 * 反序列化从指定的文件
	 * @param filepath 将要读取反序列化数据的文件
	 * @return
	 */
	public static Object deserializeFromFile(String filepath){
		
		Object object = null;
		
		ObjectInputStream objectInputStream = null;
		try {
			File saveFile = new File(filepath);
			objectInputStream = new ObjectInputStream(new FileInputStream(saveFile));
			object = objectInputStream.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			closeObjectInputStream(objectInputStream);
		}
		return object;
	}
	
	/**
	 * 关闭对象输出流
	 * @param objectOutputStream
	 */
	public static void closeObjectOutputStream(ObjectOutputStream objectOutputStream){
		if(objectOutputStream != null){
			try {
				objectOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭对象输入流
	 * @param objectInputStream
	 */
	public static void closeObjectInputStream(ObjectInputStream objectInputStream){
		if(objectInputStream != null){
			try {
				objectInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
