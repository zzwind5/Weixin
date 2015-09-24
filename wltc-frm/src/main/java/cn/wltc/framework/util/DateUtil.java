package cn.wltc.framework.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
 
/**
 * @author liuyang
 */
public final class DateUtil {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DateUtil.class);

    /**
     * ���ڸ�ʽ������
     */
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    private static DateFormat dateFormat_yy = new SimpleDateFormat("yy-MM-dd");

    private static DateFormat dateFormat_input = new SimpleDateFormat("yyyyMMdd");
    /**
     * ����ʱ���ʽ������
     */
    private static DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static DateFormat dateTimeFormatS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static DateFormat dateTimeFormat_input = new SimpleDateFormat("yyyyMMddHHmm");
    /**
     * ʱ���ʽ������
     */
    private static DateFormat timeFormat = new SimpleDateFormat("HH:mm");

	/**
     * ��ȡʱ���ʽ������ "yyyy-MM-dd"
     * @return
     */
    public static final DateFormat getDateFormat(){
        return dateFormat;
    }

    /**
     * ��ȡʱ���ʽ������ "yy-MM-dd"
     * @return
     */
    public static DateFormat getDateFormat_yy() {
		return dateFormat_yy;
	}

	/**
     * ���������ʽ����"yyyyMMdd"
     * @return
     */
    public static final DateFormat getDateFormat_input(){
        return dateFormat_input;
    }

    /**
     * ��ȡʱ�����ڸ�ʽ������ "yyyy-MM-dd HH:mm"
     * @return
     */
    public static final DateFormat getDateTimeFormat(){
        return dateTimeFormat;
    }

    /**
     * ��ȡ��ǰʱ���ʱ�����
     * @return
     */
    public static final Date nowDate(){
        return new Date();
    }
    /**
     * ϵͳ��Сʱ��
     * @return
     */
    public static final Date minDate() {
        return dateBegin(getDate(1900,1,1));
    }
    /**
     * ϵͳ���ʱ��
     * @return
     */
    public static final Date maxDate() {
        return dateEnd(getDate(2079,1,1));
    }
    /**
     * ��ȡָ��ʱ�����
     * @param date
     * @return
     */
    public static final int year(Date date){
    	if(date==null)return 0;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }
    /**
     * ��ȡָ��ʱ�����
     * @param date
     * @return
     */
    public static final int month(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH)+1;
    }
    /**
     * ��ȡָ��ʱ�����
     * @param date
     * @return
     */
    public static final int day(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }
    /**
     * ��ȡһ��ʱ�����
     * @param year ��ʽΪ��2004
     * @param month ��1��ʼ
     * @param date ��1��ʼ
     * @return
     */
    public static final Date getDate(int year,int month,int date){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, date);
        return calendar.getTime();
    }

    /**
     * ��ȡһ��ʱ�����
     * @param year ��ʽΪ��2004
     * @param month ��1��ʼ
     * @param date ��1��ʼ
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static final Date getDateTime(int year, int month, int date, int hour, int minute,
            int second){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, date,hour,minute,second);
        return calendar.getTime();
    }

    /**
     * ��һ����֪ʱ��Ļ�������ָ����ʱ��,�����ʾ����
     * @param oleDate
     * @param year
     * @param month
     * @param date
     * @return
     */
    public static final Date addDate(Date oldDate, int year, int month, int date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DATE, date);
        return calendar.getTime();
    }



    public static int constDateSub=-36500;


    /**
     * ��������ʱ����������
     * @param a
     * @param b
     * @return
     */
    public static final int dateSub(Date a,Date b) {
    	if (a==null||b==null) {
			return constDateSub;
		}


        int date = (int)(a.getTime()/(24*60*60*1000) - b.getTime()/(24*60*60*1000));
        return  date;
    }

    public static final int dateSubAddOne(Date a,Date b) {
        int date = (int)(a.getTime()/(24*60*60*1000) - b.getTime()/(24*60*60*1000));
        return date<=0?1:date+1;
    }

    public static final boolean isBetweenDateS(Date beginDate,Date nowDate,Date endDate){
    	if(beginDate!=null&&nowDate!=null&&endDate!=null){
    		if((beginDate.getTime()/(24*60*60*1000))<=(nowDate.getTime()/(24*60*60*1000))&&(nowDate.getTime()/(24*60*60*1000))<=(endDate.getTime()/(24*60*60*1000))){
    			return true;
    		}
    	}else
    	if(beginDate!=null&&nowDate!=null&&endDate==null){
    		if((beginDate.getTime()/(24*60*60*1000))<=(nowDate.getTime()/(24*60*60*1000))){
    			return true;
    		}
    	}else
    	if(beginDate==null&&nowDate!=null&&endDate!=null){
    		if((nowDate.getTime()/(24*60*60*1000))<=(endDate.getTime()/(24*60*60*1000))){
    			return true;
    		}
    	}
    	return false;
    }

    /**
     * ��������ʱ�������ٷ���
     * @param a
     * @param b
     * @return
     */
    public static final int subSecond(Date a,Date b) {
        return (int)(a.getTime()/(1000) - b.getTime()/(1000));
    }
    public static final int subSecond(String str,Date b){
        Date a = null;
        try {
            a = timeFormat.parse(str);
        } catch (ParseException e) {

            return 0;
        }
        return (int)((a.getTime()%(24*60*60*1000))/1000-(b.getTime()%(24*60*60*1000))/1000);
    }
    /**
     * һ��Ŀ�ʼʱ��
     * @param date
     * @return
     */
    public static final Date dateBegin(Date date) {
        if(date == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        dateBegin(calendar);
        return calendar.getTime();
    }
    /**
     * һ��Ľ���ʱ��
     * @param date
     * @return
     */
    public static final Date dateEnd(Date date) {
        if(date == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        dateEnd(calendar);
        return calendar.getTime();
    }
   
    /**
     * һ��Ľ���ʱ��
     * @param calendar
     * @return
     */
    public static final Calendar dateEnd(Calendar calendar) {
        if(calendar == null)
            return null;
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }
    /**
     * һ��Ŀ�ʼʱ��
     * @param calendar
     * @return
     */
    public static final Calendar dateBegin(Calendar calendar) {
        if(calendar == null)
            return null;
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }
    /**
     * һ�µĿ�ʼʱ��
     * @param date
     * @return
     */
    public static final Date monthBegin(Date date) {
        if(date == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DATE, day);
        dateBegin(calendar);
        return calendar.getTime();
    }
    /**
     * һ�µļ���ʱ��
     * @param date
     * @return
     */
    public static final Date monthEnd(Date date) {
        if(date == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DATE, day);
        dateEnd(calendar);
        return calendar.getTime();
    }
    /**
     * һ��Ŀ�ʼʱ��
     * @param date
     * @return
     */
    public static final Date yearBegin(Date date) {
        if(date == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.getActualMinimum(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DATE, month);
        dateBegin(calendar);
        return calendar.getTime();
    	//return  parseDate(formatDate(date).substring(0,4)+"-01-01");
    }
    
    /**
     * һ��Ľ���ʱ��
     * @param date
     * @return
     */
    public static final Date yearEnd(Date date) {
        if(date == null)
            return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DATE, day);
        dateEnd(calendar);
        return calendar.getTime();
    	//return  parseDate(formatDate(date).substring(0,4)+"-12-31");
    }
    /**
     * ���ַ�ת��Ϊdate
     * Ĭ�ϸ�ʽΪ "yyyy-MM-dd"
     * @param source
     * @return
     */
    public static final Date parseDate(String source){
        if(source==null || source.trim().length()==0)
            return null;
        if(source.trim().length()==8)
        try {
			Date returnDate = dateFormat_input.parse(source);
            return returnDate;
        } catch (ParseException e) {
			logger.error("DateUtil parseDate error", e);
            return null;
        }

        try {
			Date returnDate = dateFormat.parse(source);
            return returnDate;
        } catch (ParseException e) {
			logger.error("DateUtil parseDate error", e);
            return null;
        }
    }
    

    /**
     * ���ַ�ת��Ϊdate
     * Ĭ�ϸ�ʽΪ "yyyy-MM-dd HH:mm"
     * @param source
     * @return
     */
    public static final Date parseDateTime(String source){
        if(source==null || source.length()==0)
            return null;
        try {
            return dateTimeFormat.parse(source);
        } catch (ParseException e) {
        	logger.error("DateUtil parseDate error", e);
            return null;
        }
    }
    
    /**
     * ���ַ�ת��Ϊdate
     * Ĭ�ϸ�ʽΪ "yyyy-MM-dd HH:mm:ss"
     * @param source
     * @return
     */
    public static final Date parseDateTimes(String source){
        if(source==null ||source.equals("") || source.length()==0)
            return null;
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(source);
        } catch (ParseException e) {
        	logger.error("DateUtil parseDate error", e);
        }
        return null;
    }
    /**
     * ��ʽ�������ֻ����ʱ��
     * Ĭ�ϸ�ʽΪ "yyyy-MM-dd"
     * @param date
     * @return
     */
    public static String formatDate(Date date){
        if(date == null)return "";
        return dateFormat.format(date);
    }
    /**
     * ��ʽ�������ֻ����ʱ��
     * Ĭ�ϸ�ʽΪ "yy-MM-dd"
     * @param date
     * @return
     */
    public static String formatDateYY(Date date){
    	if(date == null)return "";
    	return dateFormat_yy.format(date);
    }
    
    public static String format(Date date,String pattern){
    	if(date == null)return "";
    	return DateFormatUtils.format(date, pattern);
    }
    /**
     * ��ʽ�������ʾ����д��ʱ�� yyyyMMdd
     * @param date
     * @return
     */
    public static String formatDate_input(Date date){
        if(date == null)return "";
        return dateFormat_input.format(date);
    }

    /**
     * ��ʽ�����
     * Ĭ�ϸ�ʽΪ "yyyy-MM-dd HH:mm"
     * @param date
     * @return
     */
    public static String formatDateTime(Date date){
        if(date == null)return "";
        return dateTimeFormat.format(date);
    }
    
    /**
     * ��ʽ�����
     * Ĭ�ϸ�ʽΪ "yyyy-MM-dd HH:mm:ss"
     * @param date
     * @return
     */
    public static String formatDateTimeS(Date date){
        if(date == null)return "";
        return dateTimeFormatS.format(date);
    }

    /**
     * ��ʽ�����
     * Ĭ�ϸ�ʽΪ "yyyy-MM-dd HH:mm"
     * @param date
     * @return
     */
    public static String getDateTime(Date date){
        if(date == null)return "";
        return dateTimeFormat.format(date).substring(5,10).replaceAll("_", "/");
    }
    
    public static String formatDateTime_input(Date date){
        if(date == null)return "";
        return dateTimeFormat_input.format(date);
    }
    /**
     * �ж��Ƿ�������
     * @param yearInt
     * @return
     */
    public static boolean isLeapYear(int yearInt){
    	boolean flag = false;
		if(((yearInt%4==0)&&(yearInt%100!=0))||((yearInt%4==0)&&(yearInt%400==0))){
			return true;
		}
    	return flag;
    }

	/**
	 * ��ָ����ʱ���������������ʾΪ��
	 * @param days
	 * @return
	 */
	public static Date addDays(Date date,int days){
		Date newdate=new Date();
		long newtimems=date.getTime()+((long)days*24*60*60*1000);
		newdate.setTime(newtimems);
		return newdate;
	}


    /**
     * ��������жϽ��� ���� ǰ��3��ʱ��Σ�����Ƿ���String����
     * @param date
     * @return
     */
    public static String cnDate(Date date){
    	if(date ==null){
    		return "";
    	}
    	Date newdate=new Date();
    	Long newTimes = newdate.getTime();
    	Long cdTimes = date.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    	String dateStr1 = DateUtil.formatDate(DateUtil.addDays(newdate, -1))+ " 23:59:59";
    	String dateStr2 = DateUtil.formatDate(DateUtil.addDays(newdate, -2))+ " 23:59:59";
    	String dateStr3 = DateUtil.formatDate(DateUtil.addDays(newdate, -3))+ " 23:59:59";
    	Date date1 = DateUtil.parseDateTimes(dateStr1);
    	Date date2 = DateUtil.parseDateTimes(dateStr2);
    	Date date3 = DateUtil.parseDateTimes(dateStr3);
    	if(newTimes >= cdTimes && cdTimes>date1.getTime() ){
    		return "���� "+sdf.format(date);
    	}else if(cdTimes<date1.getTime() && cdTimes>date2.getTime()){
    		return "���� "+sdf.format(date);
    	}else if(cdTimes<date2.getTime() && cdTimes>date3.getTime()){
    		return "ǰ�� "+sdf.format(date);
    	}else
    	
    	return DateUtil.formatDateTime(date);
    }


    /**
     * �ж�8λ�����ڵ��ַ��Ƿ�Ϊ��ȷ�������ַ�
     * @param dateString
     * @return ������ȷ�������ַ���true
     */
    public static boolean isErrorFormatDateString(String dateString){
    	boolean flag =false;
    	String yearString = "";
    	String monthString = "";
    	String dayString = "";
    	if(dateString.length()==8){
    		yearString = dateString.substring(0,4);
        	monthString = dateString.substring(4,6);
        	dayString = dateString.substring(6,8);
    	}else{
    		return true;
    	}
    	int yearInt = Integer.parseInt(yearString);
    	int monthInt = Integer.parseInt(monthString);
    	int dayInt = Integer.parseInt(dayString);
    	if(DateUtil.year(DateUtil.nowDate())!=yearInt){
    		return true;
    	}
    	if(monthInt>0&&monthInt<12){
    		switch (monthInt) {
			case 1:
				if(dayInt>31||dayInt<1)flag=true;
				break;
			case 2:
				if(isLeapYear(yearInt)){
					if(dayInt>29||dayInt<1)flag=true;
				}else{
					if(dayInt>28||dayInt<1)flag=true;
				}
				break;
			case 3:
				if(dayInt>31||dayInt<1)flag=true;
				break;
			case 4:
				if(dayInt>30||dayInt<1)flag=true;
				break;
			case 5:
				if(dayInt>31||dayInt<1)flag=true;
				break;
			case 6:
				if(dayInt>30||dayInt<1)flag=true;
				break;
			case 7:
				if(dayInt>31||dayInt<1)flag=true;
				break;
			case 8:
				if(dayInt>31||dayInt<1)flag=true;
				break;
			case 9:
				if(dayInt>30||dayInt<1)flag=true;
				break;
			case 10:
				if(dayInt>31||dayInt<1)flag=true;
				break;
			case 11:
				if(dayInt>30||dayInt<1)flag=true;
				break;
			case 12:
				if(dayInt>31||dayInt<1)flag=true;
				break;

			default:break;
			}
    	}else{
    		flag=true;
    	}
    	return flag;
    }
    
    /**
     * ����Double��������scaleλС��
     * @param v1
     * @param v2
     * @param scale
     * @return Double
     */
    public static Double div(Double v1,Double v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
            "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * ����double scaleλС��
     * @param v
     * @param scale
     * @return
     */
    public static double round(double v,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    
    public static String round(String str){
    	if(str!=null && str.indexOf(".")!=-1){
        	int i = str.indexOf(".");
        	if(str.length()-i>2){
        		return str.substring(0, i+3);
        	}else if(str.length()-i>1){
        		str = str +"0";
        	}
    	}else if(str==null){
    		return "0.00";
    	}else{
    		str = str +".00";
    	}
    	return str;
    }

	/**
	 * ���������Ӧ�ķ������ʱ��
	 * @param type ת�����ͣ�1/���� 2/ʱ�� ���磺1/20��20�� ��2/1Сʱ/25����
	 * @param s ��
	 * @return times
	 * @throws CrmException 
	 */
	public static String getStrTime(int s,String type) throws Exception{
		String times = "";
		int sec = 1;//second
		int min = 60*sec;//minute
		int hh = 60*min;//hour
		
		//ʱ���֡���
		int hour = 0;
		int minute = 0;
		int second = 0;
		
		//��λ��
		String strH="";
		String strM="";
		String strS="";
		
		if(type == null){
			throw new Exception("�����ת������Ϊ��");
		}
		else if(type.equals("1")){
			minute = s/min;
			second = s-(minute*min);
			strM = minute<10 ? "0"+minute : ""+minute;
			strS = second<10 ? "0"+second : ""+second;
			times = strM+"��"+strS+"��";
		}
		else if(type.equals("2")){
			hour = s/hh;
			minute = (s-hour*hh)/min;
			strH = hour<10 ? "0"+hour : ""+hour;
			strM = minute<10 ? "0"+minute : ""+minute;
			times = strH+"ʱ"+strM+"��";
		}
		return times;
	}
	
	/**
     * ��һ����֪ʱ��Ļ�������ָ����ʱ��,�����ʾ����
     * @param oleDate
     * @param year
     * @param month
     * @param date
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static final Date addDate(Date oldDate, int year, int month, int date,int hour,int minute,int second){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DATE, date);
        calendar.add(Calendar.HOUR_OF_DAY,hour);
        calendar.add(Calendar.MINUTE,minute);
        calendar.add(Calendar.SECOND,second);
        return calendar.getTime();
    }
    
    /**
     * ���ء�yyyy-MM����ʽ��String����
     * @param d ��yyyy-MM��
     * @return
     */
    public static String toDateStr(java.util.Date d){
		if (d == null) {
			return "";
		} else {
			return (new SimpleDateFormat("yyyy-MM")).format(d);
		}
	}
    
    public static String formatDate(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }
    
    public static String answer(String answer,Byte type){
    	String result="";
    	if(type != null && answer != null){ //&& type == 3 ������������
    		result = answer.replace("***"," ; ");
    	}else{
    		result = answer;
    	}
    	return result;
    }
    
    public static String formatDate4Long(Long timeMillis){
        if(timeMillis == null)return "";
        return dateFormat.format(new Date(timeMillis));
    }
    
    public static String formatCurrentDate(){
        return dateFormat.format(new Date());
    }
    
    
    /**
     * 比较两个日期相差的 天、时、分、秒
     * @param begin
     * @param end
     * @return
     */
    public static String compareTimeDiff(Date begin , Date end){
    	
    		if (begin == null || end == null){
    			return "";
    		}
    	
    	    long between =  (end.getTime() - begin.getTime());// 得到两者的毫秒数
    	    
    	    long day = between / (24 * 60 * 60 * 1000);
    	    long hour = (between / (60 * 60 * 1000) - day * 24);
    	    long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
    	    long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
    	    
    	    StringBuilder sb = new StringBuilder();
    	    
    	    if (day > 0){
    	    	sb.append(day);
    	    	sb.append("天");
    	    }
    	    
    	    if (hour > 0){
    	    	sb.append(hour);
    	    	sb.append("小时");
    	    }
    	    
    	    if (min > 0){
    	    	sb.append(min);
    	    	sb.append("分");
    	    }
    	    
    	    if (s > 0){
    	    	sb.append(s);
    	    	sb.append("秒");
    	    }
    	    
    	    if (sb.length() == 0){
    	    	sb.append("0秒");
    	    }
    	    
    	    return sb.toString();
    }
    
    public static boolean isnew (Date baseDate , int dates){
        
        if (baseDate == null){
            return false;
        }
        
       return baseDate.getTime() > System.currentTimeMillis() - dates * 24 * 3600000;
        
    }
   
}

