package cn.wltc.core.logging;

import org.apache.log4j.lf5.LogLevel;
import org.slf4j.Logger;

public interface SMLogger extends Logger{

	public void log(EventData eventData);
	public void log(LogLevel logLevel, EventData eventData);

}
