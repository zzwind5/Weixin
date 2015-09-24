package cn.wltc.core.logging;

import org.apache.log4j.lf5.LogLevel;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

class EventLogger {

	  static Marker EVENT_MARKER = MarkerFactory.getMarker("EVENT");

	  private EventLogger() {
	  }

	    public static void logEvent(SMLogger logger, EventData data) {
	        logger.debug(EVENT_MARKER,data.toString());
	    } 
	    public static void logEvent(LogLevel logLevel, SMLogger logger, EventData data) {
	      if(LogLevel.INFO.equals(logLevel)) {
	        logger.info(EVENT_MARKER,data.toString());
	      } else if(LogLevel.DEBUG.equals(logLevel)) {
	        logger.debug(EVENT_MARKER,data.toString());
	      } else if(LogLevel.WARN.equals(logLevel)) {
	        logger.warn(EVENT_MARKER,data.toString());
	      } else if(LogLevel.ERROR.equals(logLevel) || LogLevel.FATAL.equals(logLevel)) {
	        logger.error(EVENT_MARKER,data.toString());
	      }
	    } 

}
