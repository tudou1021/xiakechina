package org.xiakechina.course.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
public class DateUtils {
	 /**
	  * 锟斤拷指锟斤拷锟街凤拷转锟斤拷锟斤拷锟斤拷锟斤拷
	  * 
	  * @param date
	  *            String 锟斤拷锟斤拷锟街凤拷
	  * @param datePattern
	  *            String 锟斤拷锟节革拷式
	  * @return Date
	  */
	 public static java.util.Date getFormatDate(String date, String datePattern) {
	  SimpleDateFormat sd = new SimpleDateFormat(datePattern);
	  return sd.parse(date, new java.text.ParsePosition(0));
	 }
	 
	 /**
	  * 锟斤拷指锟斤拷锟斤拷锟节讹拷锟斤拷转锟斤拷锟缴革拷式锟斤拷锟街凤拷
	  * 
	  * @param date
	  *            Date XML锟斤拷锟节讹拷锟斤拷
	  * @param datePattern
	  *            String 锟斤拷锟节革拷式
	  * @return String
	  */
	 public static String getFormattedString(Date date, String datePattern) {
	  SimpleDateFormat sd = new SimpleDateFormat(datePattern);
	  return sd.format(date);
	 }
	 /**
	  * 锟斤拷指锟斤拷XML锟斤拷锟节讹拷锟斤拷转锟斤拷锟缴革拷式锟斤拷锟街凤拷
	  * 
	  * @param xmlDate
	  *            Date XML锟斤拷锟节讹拷锟斤拷
	  * @param datePattern
	  *            String 锟斤拷锟节革拷式
	  * @return String
	  */
	 public static String getFormattedString(XMLGregorianCalendar xmlDate,
	   String datePattern) {
	  SimpleDateFormat sd = new SimpleDateFormat(datePattern);
	  Calendar calendar = xmlDate.toGregorianCalendar();
	  return sd.format(calendar.getTime());
	 }
	 /**
	  * 锟斤拷指锟斤拷XML锟斤拷锟节讹拷锟斤拷转锟斤拷锟斤拷锟斤拷锟节讹拷锟斤拷
	  * 
	  * @param xmlDate
	  *            Date XML锟斤拷锟节讹拷锟斤拷
	  * @param datePattern
	  *            String 锟斤拷锟节革拷式
	  * @return Date
	  */
	 public static Date xmlGregorianCalendar2Date(XMLGregorianCalendar xmlDate) {
	  return xmlDate.toGregorianCalendar().getTime();
	 }
	 public static String getThisYear() {
	  // 锟斤拷玫锟角帮拷锟斤拷锟�
	  Calendar cldCurrent = Calendar.getInstance();
	  // 锟斤拷锟斤拷锟斤拷锟斤拷锟�
	  String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
	  return strYear;
	 }
	 public static XMLGregorianCalendar convert2XMLCalendar(Calendar calendar) {
	  try {
	   DatatypeFactory dtf = DatatypeFactory.newInstance();   
	   return dtf.newXMLGregorianCalendar(
	     calendar.get(Calendar.YEAR),
	     calendar.get(Calendar.MONTH)+1,
	     calendar.get(Calendar.DAY_OF_MONTH),
	     calendar.get(Calendar.HOUR),
	     calendar.get(Calendar.MINUTE),
	     calendar.get(Calendar.SECOND),
	     calendar.get(Calendar.MILLISECOND),
	     calendar.get(Calendar.ZONE_OFFSET)/(1000*60));
	    
	  } catch (DatatypeConfigurationException e) {
	   e.printStackTrace();
	   return null;
	  }
	 }
	 // 锟斤拷取锟斤拷锟斤拷时锟斤拷
	 public static java.sql.Timestamp getNowTime(String dateformat) {
	  Date now = new Date();
	  SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 锟斤拷锟皆凤拷锟斤拷锟斤拷薷锟斤拷锟斤拷诟锟绞�
	  String dateString = dateFormat.format(now);
	  SimpleDateFormat sd = new SimpleDateFormat(dateformat);
	  Date dateFormt = sd.parse(dateString, new java.text.ParsePosition(0));
	  java.sql.Timestamp dateTime = new java.sql.Timestamp(dateFormt
	    .getTime());
	  return dateTime;
	  // return hehe;
	 }
	 // 锟斤拷取指锟斤拷时锟斤拷
	 public static java.sql.Timestamp getNowNewTime(String date,String dateformat){    
	        //Date   now   =   new   Date();       
	        SimpleDateFormat   dateFormat   =   new   SimpleDateFormat(dateformat);//锟斤拷锟皆凤拷锟斤拷锟斤拷薷锟斤拷锟斤拷诟锟绞�  
	        dateFormat.parse(date, new java.text.ParsePosition(0));
	        
	      //  String  dateString= dateFormat.format(date); 
	       Date dateFormt= dateFormat.parse(date, new java.text.ParsePosition(0));
	       java.sql.Timestamp dateTime = new java.sql.Timestamp(dateFormt.getTime());
	     
	       
	       return dateTime;
	       // return hehe;    
	    }
	 /**
	  * @param 锟斤拷锟斤拷yyyy-MM-dd'T'hh:mm:ss.SSS锟斤拷式锟斤拷时锟斤拷转锟斤拷.
	  * @return
	  */
	 public static String getTFormatString(String tdate) {
	  SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
	  String str ="";
	  try {
	   java.util.Date date = format1.parse(tdate);
	   SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    str = format2.format(date);
	  } catch (ParseException e) {
	   e.printStackTrace();
	  }
	  return str;
	 }
	 
	 public static void main(String[] args) {
	  SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
	  String date = "20110202";
	  System.out.println(sd.parse(date, new java.text.ParsePosition(0)));
	  System.out.println(getBefore2HourDate());
	 }
	 //锟斤拷取锟斤拷前时锟斤拷前2锟斤拷小时锟斤拷时锟戒。
	 public static String getBefore2HourDate(){
	  SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");  
	   Calendar c = Calendar.getInstance();    
	   c.add(Calendar.HOUR_OF_DAY, -2); // 目前锟絩锟絞锟斤拷3小锟絩    
	  return df.format(c.getTime());
	  
	 }
	 
	 /**
	  * 
	  * @param time1   锟斤拷前时锟斤拷  
	  * @param time2  锟饺斤拷时锟斤拷 
	  * @return  锟斤拷锟絫ime1锟斤拷time2锟斤拷gap锟斤拷锟接ｏ拷锟津返伙拷true;
	  */
	 public static boolean compareDateTime(Date time1, Date time2, int gap) {
	  return time1.getTime() - time2.getTime() > gap * 60 * 1000;
	 }
	 
	 public static Date StringToDate(String dateStr,String formatStr){
			DateFormat dd=new SimpleDateFormat(formatStr);
			Date date=null;
			try {
				date = dd.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}
	

	public static Date strToDate(String strDate) {
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  ParsePosition pos = new ParsePosition(0);
	  Date strtodate = formatter.parse(strDate, pos);
	  return strtodate;
	 }
}