package cn.qhjys.mall.util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * date工具类
 * @author caowenhua
 */
public class DateUtils {
	 /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_SHORT = "yyyy-MM-dd";
     
    /**
     * 英文全称  如：2010-12-01 23:15:06
     */
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
     
    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
     
    /**
     * 中文简写  如：2010年12月01日
     */
    public static String FORMAT_SHORT_CN = "yyyy年MM月dd";
     
    /**
     * 中文全称  如：2010年12月01日  23时15分06秒
     */
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";
     
    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";
 
	/** 
     * 获取某年第一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static Date getFirstDayOfYear(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }  
      
    /** 
     * 获取某年最后一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static Date getLastDayOfYear(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND,59);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }
    
    /**
     * 获取某个月的第一天
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        int firstDay = cal.getMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH,firstDay);
        cal.set(Calendar.HOUR_OF_DAY,00);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND,0);
       return cal.getTime();
    }
    
    /**
     * 获取某个月的最后一天
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();     
        cal.set(Calendar.YEAR, year);     
        //月份是从0开始的，比如说如果输入5的话，实际上是6月
        cal.set(Calendar.MONTH, month-1);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH,lastDay);
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND,59);
//        new   SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
       return cal.getTime();
    }
    
    /**
     * 获取某一天的第一秒
     * @param date
     * @return
     */
    public static Date getFirstSecondOfDay(Date date) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.set(Calendar.HOUR_OF_DAY,00);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND,0);
        return cal.getTime();
    }
    
    /**
     * 获取某一天的最后一秒
     * @param date
     * @return
     */
    public static Date getLastSecondOfDay(Date date) {
    	Calendar cal = Calendar.getInstance(); 
    	cal.setTime(date);
    	cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND,59);
    	return cal.getTime();
    }
    
    /**
     * 获取指定天数后的日期
     * 
     * @param baseDate
     *        基准日期
     * @param day
     *        后推天数
     * @return 后推后的天数
     */
    public static Date getDateAfter(Date baseDate, int day)
    {
        Calendar now = Calendar.getInstance();
        now.setTime(baseDate);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }
    
    
    
    
    /**
     * 获取时间戳
     */
  public static String getTimeString() {
      SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
      Calendar calendar = Calendar.getInstance();
      return df.format(calendar.getTime());
  }
   
  /**
   * 获取日期年份
   * @param date 日期
   * @return
   */
  public static String getYear(Date date) {
	  SimpleDateFormat df = new SimpleDateFormat(FORMAT_SHORT);
      return df.format(date).substring(0, 4);
  }
  /**
   * 功能描述：返回月
   *
   * @param date
   *            Date 日期
   * @return 返回月份
   */
  public static int getMonth(Date date) {
	  Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      return calendar.get(Calendar.MONTH) + 1;
  }
   
  /**
   * 功能描述：返回日
   *
   * @param date
   *            Date 日期
   * @return 返回日份
   */
  public static int getDay(Date date) {
	  Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      return calendar.get(Calendar.DAY_OF_MONTH);
  }
   
  /**
   * 功能描述：返回小时
   *
   * @param date
   *            日期
   * @return 返回小时
   */
  public static int getHour(Date date) {
	  Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      return calendar.get(Calendar.HOUR_OF_DAY);
  }
   
  /**
   * 功能描述：返回分
   *
   * @param date
   *            日期
   * @return 返回分钟
   */
  public static int getMinute(Date date) {
	  Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      return calendar.get(Calendar.MINUTE);
  }
   
  /**
   * 返回秒钟
   *
   * @param date
   *            Date 日期
   * @return 返回秒钟
   */
  public static int getSecond(Date date) {
	  Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      return calendar.get(Calendar.SECOND);
  }
   
  /**
   * 功能描述：返回毫
   *
   * @param date
   *            日期
   * @return 返回毫
   */
  public static long getMillis(Date date) {
	  Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      return calendar.getTimeInMillis();
  }
    
	/**
	 * 获取今天的23:00:00时间
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date getDateThree(Date date) {
		Calendar c = Calendar.getInstance();  
		c.setTime(date);  
		c.set(Calendar.HOUR_OF_DAY, 23);  
		c.set(Calendar.MINUTE, 00);  
		c.set(Calendar.SECOND, 00);  
		return c.getTime();  
	}
	
	/**
	   * 得到几天前的时间
	   * @param d
	   * @param day
	   * @return
	   */
	  public static Date getDateBefore(Date d,int day){
		   Calendar now =Calendar.getInstance();
		   now.setTime(d);
		   now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
		   now.set(Calendar.HOUR_OF_DAY, 0);  
		   now.set(Calendar.MINUTE, 0);  
		   now.set(Calendar.SECOND, 0);  
		   return now.getTime();
	  }
}
