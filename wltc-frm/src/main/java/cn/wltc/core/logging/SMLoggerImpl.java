package cn.wltc.core.logging;

import org.apache.log4j.lf5.LogLevel;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

public class SMLoggerImpl implements SMLogger {
	final static private String FQCN = SMLoggerImpl.class.getName();
	final private Logger logger;

	public SMLoggerImpl(Logger logger) {
		this.logger = logger; 
	}

	protected Logger getLogger()
	{
		return logger;
	}
	

	public String getName() {
		return logger.getName();
	}

	public boolean isTraceEnabled()
	{
		return logger.isTraceEnabled();
	}


	public void trace(String msg)
	{
        if (!logger.isTraceEnabled())
            return;

        if (logger instanceof LocationAwareLogger)
            ((LocationAwareLogger) logger)
                    .log(null, FQCN, LocationAwareLogger.TRACE_INT, msg, null, null);
        else
        	logger.trace(msg);
	}


	public void trace(String format, Object arg)
	{
        if (!logger.isTraceEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arg).getMessage();
            ((LocationAwareLogger) logger)
                    .log(null, FQCN, LocationAwareLogger.TRACE_INT, formattedMessage, new Object[] {arg}, null);
        }
        else
        	logger.trace(format, arg);
	}

	public void trace(String format, Object arg1, Object arg2)
	{
        if (!logger.isTraceEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arg1, arg2).getMessage();
            ((LocationAwareLogger) logger)
                    .log(null, FQCN, LocationAwareLogger.TRACE_INT, formattedMessage, new Object[] {arg1, arg2}, null);
        }
        else
        	logger.trace(format, arg1, arg2);
	}

	public void trace(String msg, Throwable t)
	{
        if (!logger.isTraceEnabled())
            return;

        if (logger instanceof LocationAwareLogger)
            ((LocationAwareLogger) logger)
                    .log(null, FQCN, LocationAwareLogger.TRACE_INT, msg, null, t);
        else
        	logger.trace(msg, t);
	}

	public boolean isTraceEnabled(Marker marker)
	{
		return logger.isTraceEnabled();
	}

	public void trace(Marker marker, String msg)
	{
        if (!logger.isTraceEnabled())
            return;

        if (logger instanceof LocationAwareLogger)
            ((LocationAwareLogger) logger)
                    .log(marker, FQCN, LocationAwareLogger.TRACE_INT, msg, null, null);
        else
        	logger.trace(marker, msg);
	}

	public void trace(Marker marker, String format, Object arg)
	{
        if (!logger.isTraceEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arg).getMessage();
            ((LocationAwareLogger) logger)
                    .log(marker, FQCN, LocationAwareLogger.TRACE_INT, formattedMessage, new Object[] {arg}, null);
        }
        else
    		logger.trace(marker, format, arg);
	}

	public void trace(Marker marker, String format, Object arg1, Object arg2)
	{
        if (!logger.isTraceEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arg1, arg2).getMessage();
            ((LocationAwareLogger) logger)
                    .log(marker, FQCN, LocationAwareLogger.TRACE_INT, formattedMessage, new Object[] {arg1, arg2}, null);
        }
        else
    		logger.trace(marker, format, arg1, arg2);
	}

	public void trace(Marker marker, String format, Object... arguments)
	{
        if (!logger.isTraceEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arguments).getMessage();
            ((LocationAwareLogger) logger)
                    .log(marker, FQCN, LocationAwareLogger.TRACE_INT, formattedMessage, new Object[] {arguments}, null);
        }
        else
        	logger.trace(marker, format, arguments);
	}

	public void trace(Marker marker, String msg, Throwable t)
	{
        if (!logger.isTraceEnabled())
            return;

        if (logger instanceof LocationAwareLogger)
            ((LocationAwareLogger) logger)
                    .log(marker, FQCN, LocationAwareLogger.TRACE_INT, msg, null, t);
        else
        	logger.trace(marker, msg, t);
	}


	public boolean isDebugEnabled()
	{
		return logger.isDebugEnabled();
	}

	public void debug(String msg)
	{
        if (!logger.isDebugEnabled())
            return;

        if (logger instanceof LocationAwareLogger)
            ((LocationAwareLogger) logger)
                    .log(null, FQCN, LocationAwareLogger.DEBUG_INT, msg, null, null);
        else
        	logger.debug(msg);
	}


	public void debug(String format, Object arg)
	{
        if (!logger.isDebugEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arg).getMessage();
            ((LocationAwareLogger) logger)
                    .log(null, FQCN, LocationAwareLogger.DEBUG_INT, formattedMessage, new Object[] {arg}, null);
        }
        else
        	logger.debug(format, arg);
	}

	public void debug(String format, Object arg1, Object arg2)
	{
        if (!logger.isDebugEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arg1, arg2).getMessage();
            ((LocationAwareLogger) logger)
                    .log(null, FQCN, LocationAwareLogger.DEBUG_INT, formattedMessage, new Object[] {arg1, arg2}, null);
        }
        else
        	logger.debug(format, arg1, arg2);
	}

	public void debug(String format, Object... arguments)
	{
        if (!logger.isDebugEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arguments).getMessage();
            ((LocationAwareLogger) logger)
                    .log(null, FQCN, LocationAwareLogger.DEBUG_INT, formattedMessage, arguments, null);
        }
        else
        	logger.debug(format, arguments);
	}

	public void debug(String msg, Throwable t)
	{
        if (!logger.isDebugEnabled())
            return;

        if (logger instanceof LocationAwareLogger)
            ((LocationAwareLogger) logger)
                    .log(null, FQCN, LocationAwareLogger.DEBUG_INT, msg, null, t);
        else
        	logger.debug(msg, t);
	}

	public boolean isDebugEnabled(Marker marker)
	{
		return logger.isDebugEnabled();
	}

	public void debug(Marker marker, String msg)
	{
        if (!logger.isDebugEnabled())
            return;

        if (logger instanceof LocationAwareLogger)
            ((LocationAwareLogger) logger)
                    .log(marker, FQCN, LocationAwareLogger.DEBUG_INT, msg, null, null);
        else
        	logger.debug(marker, msg);
	}

	public void debug(Marker marker, String format, Object arg)
	{
        if (!logger.isDebugEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arg).getMessage();
            ((LocationAwareLogger) logger)
                    .log(marker, FQCN, LocationAwareLogger.DEBUG_INT, formattedMessage, new Object[] {arg}, null);
        }
        else
    		logger.debug(marker, format, arg);
	}

	public void debug(Marker marker, String format, Object arg1, Object arg2)
	{
        if (!logger.isDebugEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arg1, arg2).getMessage();
            ((LocationAwareLogger) logger)
                    .log(marker, FQCN, LocationAwareLogger.DEBUG_INT, formattedMessage, new Object[] {arg1, arg2}, null);
        }
        else
    		logger.debug(marker, format, arg1, arg2);
	}

	public void debug(Marker marker, String format, Object... arguments)
	{
        if (!logger.isDebugEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arguments).getMessage();
            ((LocationAwareLogger) logger)
                    .log(marker, FQCN, LocationAwareLogger.DEBUG_INT, formattedMessage, new Object[] {arguments}, null);
        }
        else
        	logger.debug(marker, format, arguments);
	}

	public void debug(Marker marker, String msg, Throwable t)
	{
        if (!logger.isDebugEnabled())
            return;

        if (logger instanceof LocationAwareLogger)
            ((LocationAwareLogger) logger)
                    .log(marker, FQCN, LocationAwareLogger.DEBUG_INT, msg, null, t);
        else
        	logger.debug(marker, msg, t);
	}


	public boolean isInfoEnabled()
	{
		return logger.isInfoEnabled();
	}

	public void info(String msg)
	{
        if (!logger.isInfoEnabled())
            return;

        if (logger instanceof LocationAwareLogger)
            ((LocationAwareLogger) logger)
                    .log(null, FQCN, LocationAwareLogger.INFO_INT, msg, null, null);
        else
        	logger.info(msg);
	}


	public void info(String format, Object arg)
	{
        if (!logger.isInfoEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arg).getMessage();
            ((LocationAwareLogger) logger)
                    .log(null, FQCN, LocationAwareLogger.INFO_INT, formattedMessage, new Object[] {arg}, null);
        }
        else
        	logger.info(format, arg);
	}

	public void info(String format, Object arg1, Object arg2)
	{
        if (!logger.isInfoEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arg1, arg2).getMessage();
            ((LocationAwareLogger) logger)
                    .log(null, FQCN, LocationAwareLogger.INFO_INT, formattedMessage, new Object[] {arg1, arg2}, null);
        }
        else
        	logger.info(format, arg1, arg2);
	}

	public void info(String format, Object... arguments)
	{
        if (!logger.isInfoEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arguments).getMessage();
            ((LocationAwareLogger) logger)
                    .log(null, FQCN, LocationAwareLogger.INFO_INT, formattedMessage, arguments, null);
        }
        else
        	logger.info(format, arguments);
	}

	public void info(String msg, Throwable t)
	{
        if (!logger.isInfoEnabled())
            return;

        if (logger instanceof LocationAwareLogger)
            ((LocationAwareLogger) logger)
                    .log(null, FQCN, LocationAwareLogger.INFO_INT, msg, null, t);
        else
        	logger.info(msg, t);
	}

	public boolean isInfoEnabled(Marker marker)
	{
		return logger.isInfoEnabled();
	}

	public void info(Marker marker, String msg)
	{
        if (!logger.isInfoEnabled())
            return;

        if (logger instanceof LocationAwareLogger)
            ((LocationAwareLogger) logger)
                    .log(marker, FQCN, LocationAwareLogger.INFO_INT, msg, null, null);
        else
        	logger.info(marker, msg);
	}

	public void info(Marker marker, String format, Object arg)
	{
        if (!logger.isInfoEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arg).getMessage();
            ((LocationAwareLogger) logger)
                    .log(marker, FQCN, LocationAwareLogger.INFO_INT, formattedMessage, new Object[] {arg}, null);
        }
        else
    		logger.info(marker, format, arg);
	}

	public void info(Marker marker, String format, Object arg1, Object arg2)
	{
        if (!logger.isInfoEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arg1, arg2).getMessage();
            ((LocationAwareLogger) logger)
                    .log(marker, FQCN, LocationAwareLogger.INFO_INT, formattedMessage, new Object[] {arg1, arg2}, null);
        }
        else
    		logger.info(marker, format, arg1, arg2);
	}

	public void info(Marker marker, String format, Object... arguments)
	{
        if (!logger.isInfoEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arguments).getMessage();
            ((LocationAwareLogger) logger)
                    .log(marker, FQCN, LocationAwareLogger.INFO_INT, formattedMessage, new Object[] {arguments}, null);
        }
        else
        	logger.info(marker, format, arguments);
	}

	public void info(Marker marker, String msg, Throwable t)
	{
        if (!logger.isInfoEnabled())
            return;

        if (logger instanceof LocationAwareLogger)
            ((LocationAwareLogger) logger)
                    .log(marker, FQCN, LocationAwareLogger.INFO_INT, msg, null, t);
        else
        	logger.info(marker, msg, t);
	}


	public boolean isWarnEnabled()
	{
		return logger.isWarnEnabled();
	}

	public void warn(String msg)
	{
        if (!logger.isWarnEnabled())
            return;

        if (logger instanceof LocationAwareLogger)
            ((LocationAwareLogger) logger)
                    .log(null, FQCN, LocationAwareLogger.WARN_INT, msg, null, null);
        else
        	logger.warn(msg);
	}


	public void warn(String format, Object arg)
	{
        if (!logger.isWarnEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arg).getMessage();
            ((LocationAwareLogger) logger)
                    .log(null, FQCN, LocationAwareLogger.WARN_INT, formattedMessage, new Object[] {arg}, null);
        }
        else
        	logger.warn(format, arg);
	}

	public void warn(String format, Object arg1, Object arg2)
	{
        if (!logger.isWarnEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arg1, arg2).getMessage();
            ((LocationAwareLogger) logger)
                    .log(null, FQCN, LocationAwareLogger.WARN_INT, formattedMessage, new Object[] {arg1, arg2}, null);
        }
        else
        	logger.warn(format, arg1, arg2);
	}

	public void warn(String format, Object... arguments)
	{
        if (!logger.isWarnEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arguments).getMessage();
            ((LocationAwareLogger) logger)
                    .log(null, FQCN, LocationAwareLogger.WARN_INT, formattedMessage, arguments, null);
        }
        else
        	logger.warn(format, arguments);
	}

	public void warn(String msg, Throwable t)
	{
        if (!logger.isWarnEnabled())
            return;

        if (logger instanceof LocationAwareLogger)
            ((LocationAwareLogger) logger)
                    .log(null, FQCN, LocationAwareLogger.WARN_INT, msg, null, t);
        else
        	logger.warn(msg, t);
	}

	public boolean isWarnEnabled(Marker marker)
	{
		return logger.isWarnEnabled();
	}

	public void warn(Marker marker, String msg)
	{
        if (!logger.isWarnEnabled())
            return;

        if (logger instanceof LocationAwareLogger)
            ((LocationAwareLogger) logger)
                    .log(marker, FQCN, LocationAwareLogger.WARN_INT, msg, null, null);
        else
        	logger.warn(marker, msg);
	}

	public void warn(Marker marker, String format, Object arg)
	{
        if (!logger.isWarnEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arg).getMessage();
            ((LocationAwareLogger) logger)
                    .log(marker, FQCN, LocationAwareLogger.WARN_INT, formattedMessage, new Object[] {arg}, null);
        }
        else
    		logger.warn(marker, format, arg);
	}

	public void warn(Marker marker, String format, Object arg1, Object arg2)
	{
        if (!logger.isWarnEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arg1, arg2).getMessage();
            ((LocationAwareLogger) logger)
                    .log(marker, FQCN, LocationAwareLogger.WARN_INT, formattedMessage, new Object[] {arg1, arg2}, null);
        }
        else
    		logger.warn(marker, format, arg1, arg2);
	}

	public void warn(Marker marker, String msg, Throwable t)
	{
        if (!logger.isWarnEnabled())
            return;

        if (logger instanceof LocationAwareLogger)
            ((LocationAwareLogger) logger)
                    .log(marker, FQCN, LocationAwareLogger.WARN_INT, msg, null, t);
        else
        	logger.warn(marker, msg, t);
	}

	public boolean isErrorEnabled()
	{
		return logger.isErrorEnabled();
	}

	public void error(String msg)
	{
        if (!logger.isErrorEnabled())
            return;

        if (logger instanceof LocationAwareLogger)
            ((LocationAwareLogger) logger)
                    .log(null, FQCN, LocationAwareLogger.ERROR_INT, msg, null, null);
        else
        	logger.error(msg);
	}

	public void error(String format, Object arg)
	{
        if (!logger.isErrorEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arg).getMessage();
            ((LocationAwareLogger) logger)
                    .log(null, FQCN, LocationAwareLogger.ERROR_INT, formattedMessage, new Object[] {arg}, null);
        }
        else
        	logger.error(format, arg);
	}

	public void error(String format, Object arg1, Object arg2)
	{
        if (!logger.isErrorEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arg1, arg2).getMessage();
            ((LocationAwareLogger) logger)
                    .log(null, FQCN, LocationAwareLogger.ERROR_INT, formattedMessage, new Object[] {arg1, arg2}, null);
        }
        else
        	logger.error(format, arg1, arg2);
	}

	public void error(String msg, Throwable t)
	{
        if (!logger.isErrorEnabled())
            return;

        if (logger instanceof LocationAwareLogger)
            ((LocationAwareLogger) logger)
                    .log(null, FQCN, LocationAwareLogger.ERROR_INT, msg, null, t);
        else
        	logger.error(msg, t);
	}

	public boolean isErrorEnabled(Marker marker)
	{
		return logger.isErrorEnabled();
	}

	public void error(Marker marker, String msg)
	{
        if (!logger.isErrorEnabled())
            return;

        if (logger instanceof LocationAwareLogger)
            ((LocationAwareLogger) logger)
                    .log(marker, FQCN, LocationAwareLogger.ERROR_INT, msg, null, null);
        else
        	logger.error(marker, msg);
	}

	public void error(Marker marker, String format, Object arg)
	{
        if (!logger.isErrorEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arg).getMessage();
            ((LocationAwareLogger) logger)
                    .log(marker, FQCN, LocationAwareLogger.ERROR_INT, formattedMessage, new Object[] {arg}, null);
        }
        else
    		logger.error(marker, format, arg);
	}

	public void error(Marker marker, String format, Object arg1, Object arg2)
	{
        if (!logger.isErrorEnabled())
            return;

        if (logger instanceof LocationAwareLogger) {
        	String formattedMessage = MessageFormatter.format(format, arg1, arg2).getMessage();
            ((LocationAwareLogger) logger)
                    .log(marker, FQCN, LocationAwareLogger.ERROR_INT, formattedMessage, new Object[] {arg1, arg2}, null);
        }
        else
    		logger.error(marker, format, arg1, arg2);
	}

	public void error(Marker marker, String msg, Throwable t)
	{
        if (!logger.isErrorEnabled())
            return;

        if (logger instanceof LocationAwareLogger)
            ((LocationAwareLogger) logger)
                    .log(marker, FQCN, LocationAwareLogger.ERROR_INT, msg, null, t);
        else
        	logger.error(marker, msg, t);
	}


	public void log(EventData eventData) {
		EventLogger.logEvent(this, eventData);
		
	}
	
	public void log(LogLevel logLevel, EventData eventData)
	{
		
		EventLogger.logEvent(logLevel, this, eventData);
	}

	@Override
	public void error(String arg0, Object[] arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(Marker arg0, String arg1, Object[] arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warn(Marker arg0, String arg1, Object[] arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trace(String arg0, Object[] arg1) {
		// TODO Auto-generated method stub
		
	}
}
