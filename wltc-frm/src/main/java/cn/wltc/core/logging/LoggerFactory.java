package cn.wltc.core.logging;

import org.slf4j.Logger;

public class LoggerFactory {
	public static SMLogger getLogger(@SuppressWarnings("rawtypes") Class clazz) {
		Logger logger = org.slf4j.LoggerFactory.getLogger(clazz);
		return new SMLoggerImpl(logger);
	}
	
}
