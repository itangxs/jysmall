package cn.qhjys.mall.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * 类名称:DateTimeUtil
 * 类描述: 时间日期帮助类
 * 创建人:JiangXiaoPing
 * 创建时间:2015年5月26日上午11:17:26
 * 修改人
 * 修改时间:
 * 修改备注:
 */
public class DateTimeUtil {

	/**
	 * 
	 * getStartTime
	 * 			天的开始时间
	 * @return
	 */
	public  static Date getStartTime(Date date){  
        Calendar todayStart = Calendar.getInstance();  
        todayStart.setTime(date);
        todayStart.set(Calendar.HOUR, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);  
        todayStart.set(Calendar.MILLISECOND, 0);  
        return todayStart.getTime();
       /* return todayStart.getTime().getTime();  */
    }  
    
	/**
	 * 
	 * getEndTime
	 * 		天得结束时间
	 * @param date
	 * @return
	 */
	public static Date getEndTime(Date date){  
        Calendar todayEnd = Calendar.getInstance();  
        todayEnd.setTime(date);
        todayEnd.set(Calendar.HOUR, 23);  
        todayEnd.set(Calendar.MINUTE, 59);  
        todayEnd.set(Calendar.SECOND, 59);  
        todayEnd.set(Calendar.MILLISECOND, 999);
        return  todayEnd.getTime();
        /*return todayEnd.getTime().getTime();  */
    }  
}
