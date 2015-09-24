package cn.wltc.framework.util.properties;

import java.io.File;
import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.util.ResourceUtils;

public class PropertiesUtil {

	// 资源池
	private static Properties props = new Properties();

	//private static Properties singleProps = null;

	/*
	 * 加载classpath目录下的 *.properties资源文件
	 */

	static {
		try {
			File classPath = ResourceUtils.getFile("classpath:");
			//System.out.println(classPath.getName());
			File[] files = classPath.listFiles();

			for (File file : files) {
				//System.out.println(file.getName());
				// 将以.properties结尾的文件加入资源池
				if (file.isFile()
						&& file.getName().lastIndexOf(".properties") > -1) {
					props.load(new FileInputStream(file));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取一个properties文件到全局Properties
	 * @param fileName
	 * @return
	 */
	public static void loadProperties(String fileName) {
		try {
			
			props.load(Thread.currentThread().getClass().getResourceAsStream(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取一个properties文件
	 * @param fileName
	 * @return
	 */
	public static Properties readProperties(String fileName) {
		Properties singleProps = new Properties();
		try {
			singleProps.load(Thread.currentThread().getClass().getResourceAsStream(fileName));
			//File file = ResourceUtils.getFile("classpath:" + fileName);
			//singleProps.load(new FileInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return singleProps;
	}

	/**
	 * 获取某个属性
	 */
	public static String getProperty(String key) {
		return props.getProperty(key);
	}

	/**
	 * 获取所有key值
	 */
	public static Set<Object> getKeys() {
		return props.keySet();
	}

	/**
	 * 获取所有属性，返回一个map,不常用 可以试试props.putAll(t)
	 */
	public static Map getAllProperty() {
		Map map = new HashMap();
		Enumeration enu = props.propertyNames();
		while (enu.hasMoreElements()) {
			String key = (String) enu.nextElement();
			String value = props.getProperty(key);
			map.put(key, value);
		}
		return map;
	}

	/**
	 * 在控制台上打印出所有属性，调试时用。
	 */
	public static void printProperties() {
		props.list(System.out);
	}
	

}