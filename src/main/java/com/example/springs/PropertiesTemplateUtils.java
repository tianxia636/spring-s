package com.example.springs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 读取配置文件工具类
 * @author zhaog
 *
 */
public class PropertiesTemplateUtils {
	
	private static final Properties PROPERTIES = new Properties();
	
	private PropertiesTemplateUtils() {}
	
	static {
		InputStream is = null;
		try {
			is = PropertiesTemplateUtils.class.getResourceAsStream("/template.properties");
			PROPERTIES.load(new InputStreamReader(is, "utf-8"));  
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	/**
	 * 根据名字获取值
	 * @param name
	 * @return
	 */
	public static String getValue(String name) {
		return PROPERTIES.getProperty(name)==null?null:PROPERTIES.getProperty(name).trim();
	}
	/**
	 * 根据名字获取值，带默认值
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static String getValue(String name, String defaultValue) {
		return PROPERTIES.getProperty(name, defaultValue);
	}
	/**
	 * 列出所有的key
	 * @return
	 */
	public static List<String> getAllNames() {
		return new ArrayList<String>(PROPERTIES.stringPropertyNames());
	}
	/**
	 * 列出所有的value
	 * @return
	 */
	public static List<String> getAllValues() {
		List<String> values = new ArrayList<String>();
		for(String name : getAllNames()) {
			values.add(PROPERTIES.getProperty(name));
		}
		return values;
	}
	/**
	 * 把配置文件内容写入输出流
	 * @param printStream
	 */
	public static void list(PrintStream printStream) {
		PROPERTIES.list(printStream);
	}
	/**
	 * 把配置文件内容写入输出流
	 * @param printWriter
	 */
	public static void list(PrintWriter printWriter) {
		PROPERTIES.list(printWriter);
	}
	
	
	public static void main(String[] args) {
		String value = PropertiesTemplateUtils.getValue("yqTime");
		System.out.println(value);
	}
}
