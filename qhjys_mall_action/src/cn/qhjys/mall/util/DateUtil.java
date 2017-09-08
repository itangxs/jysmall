package cn.qhjys.mall.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Date Utility Class This is used to convert Strings to Dates and Timestamps
 * <p>
 * <a href="DateUtil.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Modified by <a href="mailto:dan@getrolling.com">Dan
 *         Kibler </a> to correct time pattern. Minutes should be mm not MM (MM is month).
 * @version $Revision: 1.6 $ $Date: 2009/02/13 05:20:04 $
 */
public class DateUtil
{
    // ~ Static fields/initializers =============================================

    private static Log log = LogFactory.getLog(DateUtil.class);
    private static String defaultDatePattern = "yyyy-MM-dd HH:mm:ss";
    private static String ymdDatePattern = "yyyy-MM-dd";
    private static String timePattern = "HH:mm";

    // ~ Methods ================================================================

    public static Date getOneDay(int i){
    	Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,i);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
        date=calendar.getTime(); 
       return date;
    }
    /**
     * Return default datePattern (yyyy-MM-dd)
     * 
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern()
    {
        Locale locale = LocaleContextHolder.getLocale();
        try
        {
            defaultDatePattern = defaultDatePattern;
        }
        catch (MissingResourceException mse)
        {
            defaultDatePattern = "yyyy-MM-dd HH:mm:ss";
        }

        return defaultDatePattern;
    }

    public static String getDateTimePattern()
    {
        return DateUtil.getDatePattern() + " HH:mm:ss";
    }

    /**
     * ��ȡ��ʽΪ yyyy-MM-dd ���ַ�����
     * 
     * @param aDate
     *            date from database as a string
     * @return formatted string for the ui
     */
    public static final String getDate(Date aDate)
    {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null)
        {
            df = new SimpleDateFormat(getDatePattern());
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * ���ַ�ת��Ϊ��������
     * 
     * @param aMask
     *            the date pattern the string is in
     * @param strDate
     *            a string representation of a date
     * @return a converted Date object
     * @see java.text.SimpleDateFormat
     * @throws ParseException
     */
    public static final Date convertStringToDate(String aMask, String strDate)
    {
        if (null == strDate)
        {
            return null;
        }

        Date date = null;
        SimpleDateFormat df = new SimpleDateFormat(aMask);
        try
        {
            date = df.parse(strDate);
        }
        catch (ParseException pe)
        {
            log.error("ParseException: " + pe);
        }

        return (date);
    }

    /**
     * This method returns the current date time in the format: yyyy-MM-dd HH:MM
     * 
     * @param theTime
     *            the current time
     * @return the current date/time
     */
    public static String getTimeNow(Date theTime)
    {
        return getDateTime(timePattern, theTime);
    }

    /**
     * This method returns the current date in the format: yyyy-MM-dd
     * 
     * @return the current date
     * @throws ParseException
     */
    public static Calendar getToday()
    {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));

        return cal;
    }

    /**
     * This method generates a string representation of a date's date/time in the format you specify on input
     * 
     * @param aMask
     *            the date pattern the string is in
     * @param aDate
     *            a date object
     * @return a formatted string representation of the date
     * @see java.text.SimpleDateFormat
     */
    public static final String getDateTime(String aMask, Date aDate)
    {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null)
        {
            log.error("aDate is null!");
        }
        else
        {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * ���Ĭ�ϵ�dateFormat����������ַ�Ĭ�ϸ�ʽΪ yyyy-MM-dd
     * 
     * @param aDate
     *            A date to convert
     * @return a string representation of the date
     */
    public static final String convertDateToString(Date aDate)
    {
        return getDateTime(getDatePattern(), aDate);
    }
    
    public static final String convertDateToStringFormat(String amk,Date aDate)
    {
        return getDateTime(amk, aDate);
    }

    /**
     * ���Ĭ�ϵ�dateFormat����������ַ�
     * 
     * @param aDate
     *            A date to convert
     * @return a string representation of the date
     */
    public static final String convertDateToString(String pattern, Date aDate)
    {
        return getDateTime(pattern, aDate);
    }

    /**
     * ���ַ�ת��Ϊ�������ͣ�Ĭ�ϸ�ʽΪ yyyy-MM-dd
     * 
     * @param strDate
     *            the date to convert (in format yyyy-MM-dd)
     * @return a date object
     * @throws ParseException
     */
    public static Date convertStringToDate(String strDate)
    {
        Date aDate = null;

        if (log.isDebugEnabled())
        {
            log.debug("converting date with pattern: " + getDatePattern());
        }

        aDate = convertStringToDate(getDatePattern(), strDate);

        return aDate;
    }
    
    /**
     * get yesterday date
     * @param pattern pattern
     * @return
     */
    public static String getYesDateStr(Date date,String pattern){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.DATE,   -1);
    	String yesterday = new SimpleDateFormat( pattern ).format(cal.getTime());
    	return yesterday;
    }
    
    /**
     * get yesterday date
     * @param pattern pattern
     * @return
     */
    public static String getYesDateStr(String pattern){
    	Calendar   cal   =   Calendar.getInstance();
    	cal.add(Calendar.DATE,   -1);
    	String yesterday = new SimpleDateFormat( pattern ).format(cal.getTime());
    	return yesterday;
    }
    
    /**
     * get last month first day
     * @param pattern pattern
     * @return 
     */
	public static String getLastMonthStr(String pattern){
		String str = "";  
	       SimpleDateFormat sdf=new SimpleDateFormat(pattern);      
	  
	       Calendar lastDate = Calendar.getInstance();  
	       lastDate.set(Calendar.DATE,1);//设为当前月的1号  
	       lastDate.add(Calendar.MONTH,-1);//减一个月，变为下月的1号  
	       //lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天  
	         
	       str=sdf.format(lastDate.getTime());  
	       return str;    
	}
	
	
    /**
     * 获取传入时间与当前时间的分钟差
     * @param dateTime yyyy-MM-dd HH:mm:ss
     * @throws ParseException 
     */
    public static int getMinutesFromTimeCompareNow(String dateTimeStr) throws ParseException
    {
        int minutes=0;
       
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTime = sdf.parse(dateTimeStr);
        Date nowTime = new Date();
        double allDistanct=dateTime.getTime()- nowTime.getTime();
        minutes =(int) Math.ceil(allDistanct/1000/60);
        return minutes;
    }
    
    /**
     * 获取当前时间字符串
     * yyyy-MM-dd
     * @throws ParseException 
     */
    public static String getCurrentDayStr() throws ParseException
    {
        Date date = new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    
    
    /**
     * 获取一定范围内时间列表
     * @param isToday
     * @param interval	时间间隔 小时为单位
     * @param beginTime
     * @param endTime
     * @return
     * @throws Exception
     */
    public static List<String> getTimeList(boolean isToday,Integer interval,String beginTime,String endTime)throws Exception {
    	
    	int beginHour = Integer.parseInt(beginTime.split(":")[0]);
    	int endHour =Integer.parseInt( endTime.split(":")[0]);
    	
    	Date nowDate = new Date();
    	int nowHours = nowDate.getHours();
    	int nowMinutes = nowDate.getMinutes();
    	
    	List<String> timeList = new ArrayList<String>();
    	
    	for (int i = beginHour; i < endHour-1; i++) {
    		
    		
    		if(isToday){
    			if(i>nowHours+interval){
    				if(i== beginHour){
    					timeList.add((i+1)+":00");
    				}
    				if(i >beginHour){
    					timeList.add(i+":30");
    					timeList.add((i+1)+":00");
    				}
    			}else if(i== nowHours+interval){
    				if(nowMinutes <=30){
    					timeList.add(i+":30");
    					timeList.add((i+1)+":00");
    				}
    			}
    		}else{
    			if(i== beginHour){
    				timeList.add((i+1)+":00");
    			}
    			if(i >beginHour){
    				timeList.add(i+":30");
    				timeList.add((i+1)+":00");
    			}
    		}
    		
		}
    	return timeList;
    }
    
    
    /**
     *  根据天数  ，获取当前对应日期    str  
     *  返回  yyyy-MM-dd
     */
    public static String getCurrentDateStrAddDay(int addDay) 
    {
        Date newDate = new Date();     
        Calendar c = Calendar.getInstance();
        c.setTime(newDate);
        c.add(Calendar.DATE, addDay);
        
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(c.getTime());
    }
    
    
    
    
    //获取星期天 根据天数    0  指今天   1明天   2.。。。。
    public static String getDateWeekStr(int addDay)
    {
        Date newDate = new Date();     
        Calendar c = Calendar.getInstance();
        c.setTime(newDate);
        c.add(Calendar.DATE, addDay);
        
        SimpleDateFormat sdf= new SimpleDateFormat("EEEE");
        return sdf.format(c.getTime());
    }
    

}
