package org.aisino.utils.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 日期工具类
 * @author XZY
 *
 * 2015-12-24上午9:23:51
 */
public class DateUtils{
	
	/**
	 * 获得指定格式日期字符串
	 * @描述:
	 * @方法名: getFormatDate
	 * @param format  格式  yyyyMMddHHmmssSSS
	 * @return
	 * @返回类型 String
	 * @创建人 XZY
	 * @创建时间 2016-3-10下午1:42:15
	 * @修改人 XZY
	 * @修改时间 2016-3-10下午1:42:15
	 * @修改备注
	 * @since
	 * @throws
	 */
	public static String getFormatDate(String format){
		if(format != null && !"".equals(format)){
			return new SimpleDateFormat(format).format(new Date());
		}else{
			return null;
		}
	}
	
	/**
	 * 获得当前时间 指定格式字符串   yyyy-MM-dd HH:mm:ss
	 * @return  
	 * 		  返回字符串时间   格式   yyyy-MM-dd HH:mm:ss
	 */
	public static String getFormatDate_DT(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	/**
	 * 获得当前时间 指定格式字符串   yyyy-MM-dd
	 * @return  
	 * 		  返回字符串时间   格式   yyyy-MM-dd
	 */
	public static String getFormatNowDate_D(){
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	
	/**
	 * 获得当前时间 指定格式字符串   yyyy年MM月dd日
	 * @return  
	 * 		  返回字符串时间   格式   yyyy年MM月dd日
	 */
	public static String getFormatNowDate_D2(){
		return new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
	}
	
	
	
	
	
	/**
	 * 将字符串strdate  按指定格式format  转为  日期 Date <br>
	 * 当参数 有为空 时 返回 null
	 * 
	 * @param strdate
	 * 				需要格式化的字符串
	 * @param format
	 * 				格式化  格式  如:yyyy-mm-dd; yyyy-mm-dd hh:mm:ss
	 * @return
	 * 		 返回 格式化后  日期 Date
	 */
	public static Date getFormatStrToDate(String strdate, String format){
		Date d = null;
		try {
			if(!EmptyUtils.isEmpty(strdate) && !EmptyUtils.isEmpty(format)){
				d = new SimpleDateFormat(format).parse(strdate);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return d;
	}
	
	
	
	/**
	 * 将日期 date 按指定格式format  转为 日期 字符串  <br>
	 * 当参数 有为空 时 返回 null
	 * 
	 * @param date
	 * 				需要格式化的日期 Date
	 * @param format
	 * 				格式化  格式  如:yyyy-mm-dd; yyyy-mm-dd hh:mm:ss
	 * @return
	 * 		 返回 格式化后  日期 String
	 */
	public static String getFormatDateToStr(Date date, String format){
		String str = null;
		if(!EmptyUtils.isEmpty(date) && !EmptyUtils.isEmpty(format)){
			str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date).toString();
		}
		return str;
	}
	
	
	
	
	/**
	 * 获得当前时间 下一天 日期时间字符串   格式:yyyy-MM-dd hh:mm:ss <br>
	 * new Date() + 1天  --> String : yyyy-MM-dd hh:mm:ss
	 * @return
	 * 		  返回字符串日期时间   格式yyyy-MM-dd hh:mm:ss
	 */
	public static String getNowNextDate(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, +1);
		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(cal.getTime()).toString();
	}
	
	
	
	/**
	 * 获得当前时间 下一天 日期时间字符串   自定义格式 : format <br>
	 * new Date() + 1天  --> String : format <br>
	 * 参数为空  返回  null
	 * @param format
	 * 			      格式化参数
	 * @return
	 * 		 返回字符串日期时间   格式format
	 */
	public static String getNowNextDate(String format){
		String strnxetdate = null;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, +1);
		if(!EmptyUtils.isEmpty(format)){
			strnxetdate = new SimpleDateFormat(format).format(cal.getTime()).toString();
		}
		return strnxetdate;
	}
	
	
	
	/**
     * 获取 当前日期是 星期几，返回星期字符串<br>
     * 
     * @return 
     * 		 返回星期几字符串
     */
	public static String getWeekOfDate() {
    	String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    		Calendar cal = Calendar.getInstance();
    		cal.setTime(new Date());
    		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
    		if (w < 0){
    			w = 0;
    		}
    		return weekDays[w];
    }
	
	
	 /**
     * 将 指定日期 dt 转换为 星期几，返回星期字符串<br>
     * 
     * @param dt
     * 			需要转换的日期
     * @return 
     * 		 如果参数为空 返回null  否则返回星期几字符串
     */
    public static String getWeekOfDate(Date dt) {
    	String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    	if(!EmptyUtils.isEmpty(dt)){
    		Calendar cal = Calendar.getInstance();
    		cal.setTime(dt);
    		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
    		if (w < 0){
    			w = 0;
    		}
    		return weekDays[w];
    	}
    	return null;
    }
    
    
    
    
    
    /**
     * 将  指定格式日期字符串 yyyy-mm-dd 转为 Map 对象  key：年份，月份，日份
     * Map key：nf ; key：yf ; key：rf
     * 参数为空时 返回 null
     * 
     * @param datestr
     * 				需要转换的日期字符串 yyyy-mm-dd
     * @return
     * 		 如果参数为空或不满足格式要求  返回 null  否则返回Map key：nf(年份) ; key：yf(月份) ; key：rf(日份)
     */
    public static Map<String,String> getDateToYMD(String datestr){
    	Map<String,String> m = null;
    	if(!EmptyUtils.isEmpty(datestr) && datestr.indexOf("-") > -1){
    		m = new HashMap<String,String>();
    		String nf = datestr.substring(0, datestr.indexOf("-"));
    		String yf = datestr.substring(datestr.indexOf("-") + 1, datestr.lastIndexOf("-"));
    		String rf = datestr.substring(datestr.lastIndexOf("-") + 1, datestr.length());
    		m.put("nf", nf);
    		m.put("yf", yf);
    		if(yf.length() == 2 && yf.charAt(0) == '0'){
    			m.put("tyf", yf.substring(1, 2));
    		}
    		m.put("rf", rf);
    	}
    	return m;
    }
}
