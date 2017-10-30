package com.crossent.monitoring.portal.common.lib.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.joda.time.IllegalInstantException;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.joda.time.chrono.GJChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.Assert;



import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * 날짜 연산에 관련된 유틸리티 메소드를 제공하는 클래스
 * 
 * @author SoYon Lim
 * @author JongHoon Kim
 * @author HyunJung Jeong
 * @author HoYeon Lee
 */
public class DateUtil {

	// ~ Static fields/initializers
	// =============================================
	
	/** Date pattern (default) */
	public static final String DATE_PATTERN_DEFAULT = "yyyyMMdd";

	/** Date pattern */
	public static final String DATE_PATTERN_DASH = "yyyy-MM-dd";

	/** Time pattern */
	public static final String TIME_PATTERN = "HH:mm";
	
	/** Date Time pattern (default ) */
	public static final String DATE_TIME_PATTERN_DEFAULT = "yyyyMMdd HH:mm:ss";

	/** Date Time pattern */
	public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/** Date HMS pattern */
	public static final String DATE_HMS_PATTERN = "yyyyMMddHHmmss";
	
	/** Time stamp pattern (default) */
	public static final String TIMESTAMP_PATTERN_DEFAULT = "yyyyMMdd HH:mm:ss.SSS";	

	/** Time stamp pattern */
	public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
	
	/** Timestamp millisecond pattern */
	public static final String TIMESTAMP_MILLIS_PATTERN = "yyyyMMddHHmmssSSS";
	
	/** Timestamp microsecond pattern */
	public static final String TIMESTAMP_MICRO_PATTERN  = "yyyyMMddHHmmssSSSSSS";
	
	/** year pattern (yyyy) */
	public static final String YEAR_PATTERN = "yyyy";

	/** month pattern (MM) */
	public static final String MONTH_PATTERN = "MM";

	/** day pattern (dd) */
	public static final String DAY_PATTERN = "dd";

	/** date pattern (yyyyMMdd) */
	public static final String DATE_PATTERN = "yyyyMMdd";

	/** hour, minute, second pattern (HHmmss) */
	public static final String TIME_HMS_PATTERN = "HHmmss";
	
	/** hour, minute, second pattern (HH:mm:ss) */
	public static final String TIME_HMS_PATTERN_COLONE = "HH:mm:ss";
	
	public static final String TIME_MINSEC_PATTERN= "mmss";
	
	public static final String TIME_MINSEC_PATTERN_COLONE = "mm:ss";

	public static final String TIME_UTC_TIMESTAMP_PATTERN = "yyyy-MM-dd'T'hh:mm:ss.SSSZ";
		
	/**
	 * last day of each month for a common year (other than a leap year)
	 */
	private static final int[] lastDayOfMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	/**
	 * last day of each month for a leap year
	 */
	private static int[] lastDayOfMonthForLeapYear = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	
	/**
	 * 현재일시를 문자열로 반환
	 * 
	 * @return (yyyyMMdd HH:mm:ss) 타입의 현재 일시
	 */
	public static String getCurrentDateTime() {
		return getCurrentDateTime(DATE_TIME_PATTERN_DEFAULT);
	}

	/**
	 * 현재일시를 입력한 타입의 문자열로 반환
	 * 
	 * <pre>
	 * 입력 : HH:mm
	 * 예제 : DateUtil.getCurrentDateTime(&quot;HH:mm&quot;)
	 * 출력 :  18:24 (예시 - 현재일시 기준임)
	 * </pre>
	 * 
	 * @param pattern
	 *            날짜 타입 
	 * @return 입력한 타입의 현재일시 
	 */
	public static String getCurrentDateTime(String pattern) {
	    ;
		//DateTime dt = new DateTime();
		//DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
	    return dateToString(new Timestamp(System.currentTimeMillis()), pattern);
	}

	/**
	 * 현재 연도와 달의 정보를 반환
	 * 
	 * @return yyyy-MM 타입의 현재 연도와 달
	 */
	public static String getThisMonth() {
		return getCurrentDateTime("yyyy-MM");
	}

	/**
	 * 현재 연도의 정보를 반환
	 * 
	 * @return yyyy 타입의 현재 연도
	 */
	public static String getThisYear() {
		return getCurrentDateTime("yyyy");
	}

	/**
	 * 입력받은 일자의 요일 반환.
	 * 
	 * <pre>
	 * 입력 : 2011-02-04
	 * 예제 : DateUtil.getDayOfWeek(&quot;2011-02-04&quot;)
	 * 출력 : Fri
	 * </pre>
	 * 
	 * @param date yyyy-MM-dd 타입 또는 yyyyMMdd의 입력 문자열
	 * @return 입력받은 일자의 요일 
	 */
	public static String getDayOfWeek(String date) {
		return getDayOfWeek(date, true, LocaleContextHolder.getLocale());
	}
	
	/**
     * 입력받은 일자의 요일 반환.
     * 
     * <pre>
     * 입력 : 2011-02-04
     * 예제 : DateUtil.getDayOfWeek(&quot;2011-02-04&quot;)
     * 출력 : Fri
     * </pre>
     * 
     * @param date java.sql.Date타입 일
     * @return 입력받은 일자의 요일 
     */
    public static String getDayOfWeek(java.sql.Date date) {
        return getDayOfWeek(dateToString(date));
    }
	

	/**
	 * 일자, 요약여부,  로케일 정보를 입력받아 요일 반환
	 * 
	 * <pre>
	 * 입력 : 2011-02-04, true, Locale.US
	 * 예제 : DateUtil.getDayOfWeek(&quot;2011-02-04&quot;, true, Locale.US)
	 * 출력 : Fri
	 * <br>
	 * 입력 : 2011-02-04, false, Locale.US
	 * 예제 : DateUtil.getDayOfWeek(&quot;2011-02-04&quot;, false, Locale.US)
	 * 출력 : Friday
	 * </pre>
	 * 
	 * @param date
	 *            yyyy-MM-dd 타입 또는 yyyyMMdd의 일자 (길이로 구별)
	 * @param abbreviation
	 *           약어여부 : true 이면 shortText타입으로 반환, false이면 text형 으로 반환
	 * @param locale
	 *            locale
	 * @return 해당 요일
	 */
	public static String getDayOfWeek(String date, boolean abbreviation, Locale locale) {		
		DateTimeFormatter fmt = createDateFormatterByInputStringLength(date);		
		
		DateTime dt = parseDateTimeInternal(date, fmt);
		DateTime.Property dayOfWeek = dt.dayOfWeek();	
		
		if (abbreviation){
			return dayOfWeek.getAsShortText(locale);
		}
		else{
			return dayOfWeek.getAsText(locale);
		}
	}
	
	/**
	 * summer time 처리시 오류가 발생하지 않도록 내부적으로 parseLocalDateTime 을 사용한 후 DateTime 객체로 변환하여 반환
	 * @param date
	 * @param fmt
	 * @return
	 */
	private static DateTime parseDateTimeInternal(String date, DateTimeFormatter fmt) {
		try{
			DateTime dateTime = fmt.parseDateTime(date);
			return dateTime;
		// TIME ZONE 이 변경된 경우 localDateTime으로 재시도  	
		}catch(IllegalInstantException ie){
			LocalDateTime localDateTime = fmt.parseLocalDateTime(date);
			return new DateTime(localDateTime.toDate().getTime());	
		}
	}

	/**
	 * 입력받은 두 날짜 사이의 일자 계산 
	 * 
	 * <pre>
	 * 입력 : new GregorianCalendar(2010, 11, 14), new GregorianCalendar(2010, 11, 28)
	 * 예제 : DateUtil.getDays(new GregorianCalendar(2010, 11, 14),	new GregorianCalendar(2010, 11, 28))
	 * 출력 : 14
	 * </pre>
	 * 
	 * @param startDate
	 *            시작일
	 * @param endDate
	 *            종료일
	 * @return 시작일과 종료일 사이의 일자
	 */
	public static int getDays(Calendar startDate, Calendar endDate) {
		long min = getMinutes(startDate, endDate);

		return (int) (min / (24 * 60));
	}

	/**
	 * 입력받은 두 날짜 사이의 일자 계산
	 * 
	 * <pre>
	 * 입력 : 2005-08-15, 2005-09-14
	 * 예제 : DateUtil.getDays(&quot;2005-08-15&quot;, &quot;2005-09-14&quot;)
	 * 출력 : 30
	 * </pre>
	 * 
	 * @param startDate
	 *            (yyyyMMddHHmmss) 시작일
	 * @param endDate
	 *            (yyyyMMddHHmmss) 종료일
	 * @return 시작일과 종료일 사이의 일자
	 */
	public static int getDays(String startDate, String endDate) {
		return getDays(startDate, endDate, DATE_HMS_PATTERN);
	}
	
	   /**
     * 입력받은 두 날짜 사이의 일자 계산
     * 
     * <pre>
     * 입력 : 2005-08-15, 2005-09-14
     * 예제 : DateUtil.getDays(&quot;2005-08-15&quot;, &quot;2005-09-14&quot;)
     * 출력 : 30
     * </pre>
     * 
     * @param startDate
     *            시작일
     * @param endDate
     *            종료일
     * @return 시작일과 종료일 사이의 일자
     */
    public static int getDays(java.sql.Date startDate, java.sql.Date endDate) {
        
        return getDays(dateToString(startDate), dateToString(endDate), DATE_HMS_PATTERN);
    }
	

	/**
	 * 입력받은 두 날짜 사이의 일자 계산
	 * 
	 * <pre>
	 * 입력 : 20101124, 20101230, yyyyMMdd
	 * 예제 : DateUtil.getDays(&quot;20101124&quot;, &quot;20101230&quot;, &quot;yyyyMMdd&quot;)
	 * 출력 : 36
	 * </pre>
	 * 
	 * @param start
	 *            시작일
	 * @param end
	 *            종료일
	 * @param pattern
	 *           입력될 날짜 패턴
	 * @return 시작일과 종료일 사이의 일자
	 * 
	 * 
	 */
	// TODO NOTE THIS 25시 문제로 인해서 시각이 들어오는 경우 
	public static int getDays(String start, String end, String pattern) {
		
		String startDate = start;
		String endDate = end;
		String datePattrn = pattern;
		
		int timeStartPos = StringUtil.indexOfIgnoreCase(pattern, "HH");
		if (timeStartPos != -1){
			datePattrn = pattern.substring(0, timeStartPos);
			startDate = start.substring(0, timeStartPos);
			endDate = endDate.substring(0, timeStartPos);
		}

		DateTimeFormatter fmt = DateTimeFormat.forPattern(datePattrn);
		
		DateTime startDateTime = parseDateTimeInternal(startDate, fmt);
		DateTime endDateTime = parseDateTimeInternal(endDate, fmt);
		Days days = Days.daysBetween(startDateTime.toLocalDate(), endDateTime.toLocalDate());
		return days.getDays();
	}

	/**
	 * 입력받은 두 일자가 같은지 여부 반환
	 * 
	 * <pre>
	 * 입력 : new Date(1292252400000l), 2010-12-14
	 * 예제 : DateUtil.equals(new Date(1292252400000l), &quot;2010-12-14&quot;)
	 * 출력 : true
	 * </pre>
	 * 
	 * @param date1
	 *            비교될 일자
	 * @param date2
	 *           (yyyy-MM-dd)타입 또는(yyyyMMdd)의 비교대상 일자 
	 * @return 두일자가 같으면 true, 다르면 false
	 */
	public static boolean equals(Date date1, String date2) {
		if(date2.length() ==10){
			return equals(date1, date2, DATE_PATTERN_DASH);
		}else{
			return equals(date1, date2, DATE_PATTERN_DEFAULT);	
		}
	}

	/**
	 * 입력받은 두 일자가 같은지 여부 반환
	 * 
	 * <pre>
	 * 입력 : new Date(1292252400000l), 2010/12/14, yyyy/MM/dd
	 * 예제 : DateUtil.equals(new Date(1292252400000l), &quot;2010/12/14&quot;, &quot;yyyy/MM/dd&quot;)
	 * 출력 : true
	 * </pre>
	 * 
	 * @param date1
	 *           Date 타입의 비교될 일자
	 * @param date2
	 *           String 타입의  비교대상 일자
	 * @param date2pattern
	 *            Date타입으로 변환시킬 date2의 날짜 타입
	 * @return 두일자가 같으면 true, 다르면 false
	 */
	public static boolean equals(Date date1, String date2, String date2pattern) {
		
		String date = dateToString(date1, date2pattern);
		return date.equals(date2);
	}
	
	/**
	 * 입력받은 두 일자가 같은지 여부 반환
	 * 
	 * <pre>
	 * 입력 : 20101214, 20101214
	 * 예제 : DateUtil.equals(&quot;20101214&quot;, &quot;20101214&quot;)
	 * 출력 : true
	 * </pre>
	 * 
	 * @param str1
	 *           String 타입(yyyyMMdd)의 비교될 일자
	 * @param str2
	 *           String 타입(yyyyMMdd)의  비교대상 일자 
	 * @return 두일자가 같으면 true, 다르면 false
	 */
	public static boolean equals(String str1, String str2) {
		return str1.equals(str2);
	}

	/**
	 * 입력받은 두 일자가 같은지 여부 반환
	 *
	 * <pre>
	 * 입력 : new Date(1292252400000l), new Date(1292252400000l)
	 * 예제 : DateUtil.equals(new Date(1292252400000l), new Date(1292252400000l))
	 * 출력 : true
	 * </pre>
	 * 
	 * @param date1
	 *            Date 타입의 비교될 일자
	 * @param date2
	 *           Date 타입의  비교대상 일자
	 * @return 두일자가 같으면 true, 다르면 false
	 */
	public static boolean equals(Date date1, Date date2) {
		if (date1.getTime() == date2.getTime()) {
			return true;
		}
		return false;
	}

	/**
	 * 입력받은 두 일자의 배치비교
	 *  
	 * <pre>
	 * 입력 : new Date(1292311593000l), 20101202
	 * 예제 : DateUtil.greaterThan(new Date(1292311593000l),&quot;20101202&quot;)
	 * 출력 : true
	 * </pre>
	 * 
	 * @param date1
	 *            Date 타입의 비교될 일자
	 * @param date2
	 *             (yyyy-MM-dd)타입 또는 (yyyyMMdd)의 String type의 비교대상일자.
	 * @return date1이 date2보다 이후이면 true, date2보다 이전이거나 같으면 false
	 */
	public static boolean greaterThan(Date date1, String date2) {
		if(date2.length() ==10){
			return greaterThan(date1, date2, DATE_PATTERN_DASH);
		}else{
			return greaterThan(date1, date2, DATE_PATTERN_DEFAULT);
		}
	}

	/**
	 * 입력받은 두 일자의 배치비교
	 * 
	 * <pre>
	 * 입력 : new Date(1292311593000l), 20101202, yyyyMMdd
	 * 예제 : DateUtil.greaterThan(new Date(1292311593000l),&quot;20101202&quot;, &quot;yyyyMMdd&quot;)
	 * 출력 : true
	 * </pre>
	 * 
	 * @param date1
	 *             Date 타입의 비교될 일자
	 * @param date2
	 *           String 타입의 비교대상 일자 
	 * @param date2pattern
	 *            date2의 변환된 날짜 패턴
	 * @return date1이 date2보다 이후이면 true, date2보다 이전이거나 같으면 false
	 */
	public static boolean greaterThan(Date date1, String date2, String date2pattern) {
		String date = dateToString(date1, date2pattern);
		return greaterThan(date, date2);
	}
	
	/**
	 * 입력받은 두 일자의 배치비교
	 * 
	 * <pre>
	 * 입력 : 20101206, 20101202
	 * 예제 : DateUtil.greaterThan(&quot;20101206&quot;, &quot;20101202&quot;)
	 * 출력 : true
	 * </pre>
	 * 
	 * @param str1
	 *            String 타입(yyyyMMdd)의 비교될 일자
	 * @param str2
	 *            String 타입(yyyyMMdd)의 비교대상 일자 
	 * @return str1이 str2보다 이후이면 true, str2보다 이전이거나 같으면 false
	
	 */
	public static boolean greaterThan(String str1, String str2) {
		return str1.compareTo(str2) > 0;
		/*Date date1 = stringToDate(str1, DATE_PATTERN_DEFAULT);
		Date date2 = stringToDate(str2, DATE_PATTERN_DEFAULT);
		
		return greaterThan(date1, date2);*/
	}

	/**
	 * 입력받은 두 일자의 배치비교
	 * 
	 * <pre>
	 * 입력 : new Date(1292311593000l), new Date(1292252400000l)
	 * 예제 : DateUtil.greaterThan(new Date(1292311593000l), new Date(1292252400000l))
	 * 출력 : true
	 * </pre>
	 * 
	 * @param date1
	 *            Date 타입의 비교될 일자
	 * @param date2
	 *            Date 타입의 비교대상 일자 
	 * @return date1이 date2보다 이후이면 true, date2보다 이전이거나 같으면 false
	
	 */
	public static boolean greaterThan(Date date1, Date date2) {
		if (date1.getTime() > date2.getTime()) {
			return true;
		}
		return false;
	}

	/**
	 * 입력받은 두 java.sql.Timestamp 비교
	 * 
	 * @param timestamp1
	 *            Timestamp 타입의 비교될 일자
	 * @param timestamp2
	 *             Timestamp 타입의 비교대상 일자
	 * @return timestamp1이 timestamp2보다 이후이면 true, timestamp2보다 이전이거나 같으면 false
	 */
	public static boolean greaterThan(Timestamp timestamp1, Timestamp timestamp2) {
		if (timestamp1.getTime() > timestamp2.getTime()) {
			return true;
		}
		return false;
	}

	/**
	 * 입력받은 java.sql.Timestamp과 문자열과 비교
	 * 
	 * @param timestamp1
	 *          Timestamp 타입의 비교될 일자
	 * @param timestamp2
	 *         (yyyyMMdd HH:mm:ss.SSS)타입의 String 타입의 비교대상 일자 
	 * @return timestamp1이 timestamp2보다 이후이면 true, timestamp2보다 이전이거나 같으면 false
	 */
	public static boolean greaterThan(Timestamp timestamp1, String timestamp2) {
		return greaterThan(timestamp1, timestamp2, TIMESTAMP_PATTERN_DEFAULT);
	}



	/**
	 * 입력받은 날짜에서 intervalDays 만큼 더한 날짜 반환
	 * 
	 * <pre>
	 * 입력 : 20101218, 10
	 * 예제 : DateUtil.getEndDate(&quot;20101218&quot;, 10)
	 * 출력 : 20101228
	 * </pre>
	 * 
	 * @param startDate
	 *           String - (yyyyMMdd)타입의 시작일
	 * @param intervalDays
	 *            더할 interval days
	 * @return String (yyyyMMdd)- 타입의 시작일로부터 intervalDays 이후의 일자
	 */
	public static String getEndDate(String startDate, long intervalDays) {
		if (intervalDays < 0L){
			throw new IllegalArgumentException("The input intervalDays more than zero");
		}
		
		int year = Integer.parseInt(startDate.substring(0, 4));
		int mon = Integer.parseInt(startDate.substring(4, 6));
		int day = Integer.parseInt(startDate.substring(6, 8));
		
		DateTime start = new DateTime(year, mon, day, 0, 0, 0, 0);
		
		long intervalTimeMills = intervalDays * 86400000;
		if (intervalDays > 0L && intervalTimeMills < 0L){
			throw new IllegalArgumentException("intervalTimeMills(intervalDays * 86400000) value exceed Long.MAX_VALUE");
		}
		
		Period period = new Period(intervalTimeMills);
		DateTime end = start.plus(period);
		year = end.getYear();
		mon = end.getMonthOfYear();
		day = end.getDayOfMonth();
		String xMon = "";
		String xDay = "";
		if (mon < 10) {
			xMon = "0" + (new Integer(mon)).toString();
		} else {
			xMon = Integer.toString(mon);
		}
		if (day < 10) {
			xDay = "0" + (new Integer(day)).toString();
		} else { 
			xDay = Integer.toString(day); 
		}
		return (new Integer(year)).toString() +  xMon +  xDay;
	}
	
	/**
     * 입력받은 날짜에서 intervalDays 만큼 더한 날짜 반환
     * 
     * <pre>
     * 입력 : 20101218, 10
     * 예제 : DateUtil.getEndDate(&quot;20101218&quot;, 10)
     * 출력 : 20101228
     * </pre>
     * 
     * @param startDate
     *           java.sql.Date타입의 시작일
     * @param intervalDays
     *            더할 interval days
     * @return java.sql.Date 타입의 시작일로부터 intervalDays 이후의 일자
     */
    public static java.sql.Date getEndDate(java.sql.Date startDate, long intervalDays) {
        return stringToSQLDate(getEndDate(dateToString(startDate), intervalDays));
    }

	/**
	 * 입력받은 일자에 대해서 해당 일만큼 더한 일자 반환. 마이너스 일자는 입력받은 일자보다 이전의 일자로 계산해서 반환.
	 * 
	 * <pre>
	 * 입력 : 2010-12-18, 2
	 * 예제 : DateUtil.addDays(&quot;2010-12-18&quot;, 2)
	 * 출력 : 2010-12-20
	 * </pre>
	 * 
	 * @param date
	 *           String - (yyyy-MM-dd)타입 또는(yyyyMMdd)의 입력일자
	 * @param days
	 *            더하기 위한 일수, 빼기 위한 일수
	 * @return String타입의 계산된 일자
	 */
	public static String addDays(String date, long days) {
		if (days == 0) {
			return date;
		}
		
		DateTimeFormatter fmt = createDateFormatterByInputStringLength(date);
		
		DateTime dt = parseDateTimeInternal(date, fmt);
		DateTime subtracted = dt.withFieldAdded(DurationFieldType.days(), (int)days);
		return fmt.print(subtracted);
	}
	
	/**
     * 입력받은 일자에 대해서 해당 일만큼 더한 일자 반환. 마이너스 일자는 입력받은 일자보다 이전의 일자로 계산해서 반환.
     * 
     * <pre>
     * 입력 : 2010-12-18, 2
     * 예제 : DateUtil.addDays(&quot;2010-12-18&quot;, 2)
     * 출력 : 2010-12-20
     * </pre>
     * 
     * @param date
     *           java.sql.Date타입 또는(yyyyMMdd)의 입력일자
     * @param days
     *            더하기 위한 일수, 빼기 위한 일수
     * @return java.sql.Date타입의 계산된 일자
     */
    public static java.sql.Date addDays(java.sql.Date date, long days){
        return stringToSQLDate(addDays(dateToString(date), days));
    }
	
	private static final DateTimeZone DEFAULT_TIME_ZONE = DateTimeZone.forOffsetHours(9);
	//TODO Java SDK의 Calendar와 호환성을 유지하기 위하여 GJChronology 및 FixedTimeZone 적용함
	//TODO JodaTime의 Default Chronology인 ISOChorology를 사용할 경우 1582년 이전 날짜는 불일치 발생
	private static final Chronology DEFAULT_CHRONOLOGY = GJChronology.getInstance(DEFAULT_TIME_ZONE);

	private static DateTimeFormatter createDateFormatterByInputStringLength(String date) {

		DateTimeFormatter fmt = null; 
		
		if (date.length() == 10){
			fmt = DateTimeFormat.forPattern(DATE_PATTERN_DASH).withChronology(DEFAULT_CHRONOLOGY);
		}else{
			fmt = DateTimeFormat.forPattern(DATE_PATTERN_DEFAULT).withChronology(DEFAULT_CHRONOLOGY);
		}
		
		return fmt;
	}

	/**
	 * 입력받은 일자에 대해서 해당 개월수만큼 더한 일자 반환. 마이너스 개월수는 입력받은 일자보다 이전 일자로 계산해서 반환
	 * 
	 * <pre>
	 * 입력 : 2010-12-18, 2
	 * 예제 : DateUtil.addMonths(&quot;2010-12-18&quot;, 2)
	 * 출력 : 2011-02-18
	 * </pre>
	 * 
	 * @param date
	 *            String - (yyyy-MM-dd)타입 또는 (yyyyMMdd)타입의 입력일자
	 * @param months
	 *            더하기 위한 개월수, 빼기 위한 개월수
	 * @return String타입의 계산된 일자
	 */
	public static String addMonths(String date, long months) {
		if (months == 0) {
			return date;
		}
		
		DateTimeFormatter fmt = createDateFormatterByInputStringLength(date);
		
		DateTime dt = parseDateTimeInternal(date, fmt);
		DateTime subtracted = dt.withFieldAdded(DurationFieldType.months(), (int)months);
		return fmt.print(subtracted);
	}
	
	/**
     * 입력받은 일자에 대해서 해당 개월수만큼 더한 일자 반환. 마이너스 개월수는 입력받은 일자보다 이전 일자로 계산해서 반환
     * 
     * <pre>
     * 입력 : 2010-12-18, 2
     * 예제 : DateUtil.addMonths(&quot;2010-12-18&quot;, 2)
     * 출력 : 2011-02-18
     * </pre>
     * 
     * @param date
     *            java.sql.Date타입의 입력일자
     * @param months
     *            더하기 위한 개월수, 빼기 위한 개월수
     * @return java.sql.Date타입의 계산된 일자
     */
    public static java.sql.Date addMonths(java.sql.Date date, long months) {
        return stringToSQLDate(addMonths(dateToString(date), months));
    }
    

	/**
	 * 입력받은 일자에 대해서 해당 년수만큼 더한 일자 반환. 마이너스 년수는 입력받은 일자보다 이전 일자로 계산해서 반환
	 * 
	 * <pre>
	 * 입력 : 2010-12-18, 2
	 * 예제 : DateUtil.addYears(&quot;2010-12-18&quot;, 2)
	 * 출력 : 2012-12-18
	 * </pre>
	 * 
	 * @param date
	 *           String - (yyyy-MM-dd)타입 또는 (yyyyMMdd)타입의 입력일자
	 * @param years
	 *            더하기 위한 년수, 빼기 위한 년수
	 * @return String타입의 계산된 일자
	 */
	public static String addYears(String date, long years) {
		if (years == 0) {
			return date;
		}
		
		DateTimeFormatter fmt = createDateFormatterByInputStringLength(date);		
		
		DateTime dt = parseDateTimeInternal(date, fmt);
		DateTime subtracted = dt.withFieldAdded(DurationFieldType.years(), (int)years);
		return fmt.print(subtracted);
	}
	
	/**
     * 입력받은 일자에 대해서 해당 년수만큼 더한 일자 반환. 마이너스 년수는 입력받은 일자보다 이전 일자로 계산해서 반환
     * 
     * <pre>
     * 입력 : 2010-12-18, 2
     * 예제 : DateUtil.addYears(&quot;2010-12-18&quot;, 2)
     * 출력 : 2012-12-18
     * </pre>
     * 
     * @param date
     *           java.sql.Date타입의 입력일자
     * @param years
     *            더하기 위한 년수, 빼기 위한 년수
     * @return java.sql.Date타입의 계산된 일자
     */
    public static java.sql.Date addYears(java.sql.Date date, long years) {
        return stringToSQLDate(addYears(dateToString(date), years));
    }

	/**
	 * 입력된 일자에 대해서 년,월,일만큼 가감해서 계산한 일자 반환
	 * 
	 * <pre>
	 * 입력 : 2010-04-18, 4, -3, -5
	 * 예제 : DateUtil.addYearMonthDay(&quot;2010-04-18&quot;, 4, -3, -5)
	 * 출력 : 2014-01-13
	 * </pre>
	 * 
	 * @param date
	 *           String - (yyyy-MM-dd)타입 또는 (yyyyMMdd)타입의 입력일자
	 * @param years
	 *            더하기 또는 빼기 위한 년수
	 * @param months
	 *            더하기 또는 빼기 위한 개월수
	 * @param days
	 *            더하기 또는 빼기 위한 일수
	 * @return String타입의 계산된 일자
	 */
	public static String addYearMonthDay(String date, long years, long months, long days) {
		DateTimeFormatter fmt = createDateFormatterByInputStringLength(date);
		
		DateTime dt = parseDateTimeInternal(date, fmt);

		if (years != 0){
			dt = dt.withFieldAdded(DurationFieldType.years(), (int)years);
		}
		if (months != 0){
			dt = dt.withFieldAdded(DurationFieldType.months(), (int)months);
		}
		if (days != 0){
			dt = dt.withFieldAdded(DurationFieldType.days(), (int)days);
		}	
		return fmt.print(dt);
	}
	
	
	/**
     * 입력된 일자에 대해서 년,월,일만큼 가감해서 계산한 일자 반환
     * 
     * <pre>
     * 입력 : 2010-04-18, 4, -3, -5
     * 예제 : DateUtil.addYearMonthDay(&quot;2010-04-18&quot;, 4, -3, -5)
     * 출력 : 2014-01-13
     * </pre>
     * 
     * @param date
     *           java.sql.Date타입의 입력일자
     * @param years
     *            더하기 또는 빼기 위한 년수
     * @param months
     *            더하기 또는 빼기 위한 개월수
     * @param days
     *            더하기 또는 빼기 위한 일수
     * @return java.sql.Date타입의 계산된 일자
     */
    public static java.sql.Date addYearMonthDay(java.sql.Date date, long years, long months, long days) {
        return stringToSQLDate(addYearMonthDay(dateToString(date), years, months, days));
    }

	
	/**
	 * 입력일 기준 이달 첫번째 일자 반환
	 * 
	 * <pre>
	 * 입력 : 20091023
	 * 예제 : DateUtil.getFirstDateOfMonth(&quot;20091023&quot;)
	 * 출력 : 20091001
	 * </pre>
	 * 
	 * @param date
	 *             String - (yyyy-MM-dd)타입 또는 (yyyyMMdd)타입의 입력일자
	 * @return 입력일 기준 이달 첫번째 일자
	 */
	public static String getFirstDateOfMonth(String date) {
		DateTimeFormatter fmt = createDateFormatterByInputStringLength(date);
		
		DateTime dt = parseDateTimeInternal(date, fmt);
		DateTime dtRet = new DateTime(dt.getYear(), dt.getMonthOfYear(), 1, 0, 0, 0, 0);
		return fmt.print(dtRet);
	}
	
	
	/**
     * 입력일 기준 이달 첫번째 일자 반환
     * 
     * <pre>
     * 입력 : 20091023
     * 예제 : DateUtil.getFirstDateOfMonth(&quot;20091023&quot;)
     * 출력 : 20091001
     * </pre>
     * 
     * @param date
     *             java.sql.Date타입의 입력일자
     * @return java.sql.Date타입의 입력일 기준 이달 첫번째 일자
     */
    public static java.sql.Date getFirstDateOfMonth(java.sql.Date date) {
        return stringToSQLDate(getFirstDateOfMonth(dateToString(date)));
    }

	

	/**
	 * 입력일 기준 이달 마지막 일자 반환
	 * 
	 * <pre>
	 * 입력 : 2010-11-20
	 * 예제 : DateUtil.getLastDateOfMonth(&quot;2010-11-20&quot;)
	 * 출력 : 2010-11-30
	 * </pre>
	 * 
	 * @param date
	 *            String - (yyyy-MM-dd)타입 또는 (yyyyMMdd)의 입력일자
	 * @return 입력일 기준 이달 마지막 일자
	 */
	public static String getLastDateOfMonth(String date) {
		String firstDateOfMonth = getFirstDateOfMonth(date);

		DateTimeFormatter fmt = createDateFormatterByInputStringLength(date);
		
		DateTime dt = parseDateTimeInternal(firstDateOfMonth, fmt);
		dt = dt.plusMonths(1).minusDays(1);
		return fmt.print(dt);
	}
	
	   /**
     * 입력일 기준 이달 마지막 일자 반환
     * 
     * <pre>
     * 입력 : 2010-11-20
     * 예제 : DateUtil.getLastDateOfMonth(&quot;2010-11-20&quot;)
     * 출력 : 2010-11-30
     * </pre>
     * 
     * @param date
     *            java.sql.Date타입의 입력일자
     * @return java.sql.Date타입의 입력일 기준 이달 마지막 일자
     */
    public static java.sql.Date getLastDateOfMonth(java.sql.Date date) {
        return stringToSQLDate(getLastDateOfMonth(dateToString(date)));
    }

	
	/**
	 * 입력일 기준 전달의 첫번째 일자 반환
	 * 
	 * <pre>
	 * 입력 : 20101120
	 * 예제 : DateUtil.getFirstDateOfPrevMonth(&quot;20101120&quot;)
	 * 출력 : 20101001
	 * </pre>
	 * 
	 * @param date
	 *             String - (yyyy-MM-dd)타입 또는 (yyyyMMdd)의 입력일자
	 * @return 입력일 기준 전달의 첫번째 일자
	 */
	public static String getFirstDateOfPrevMonth(String date) {
		String firstDateOfMonth = getFirstDateOfMonth(date);

		DateTimeFormatter fmt = createDateFormatterByInputStringLength(date);
		
		DateTime dt = parseDateTimeInternal(firstDateOfMonth, fmt);
		dt = dt.minusMonths(1);
		return fmt.print(dt);
	}
	
	 /**
     * 입력일 기준 전달의 첫번째 일자 반환
     * 
     * <pre>
     * 입력 : 20101120
     * 예제 : DateUtil.getFirstDateOfPrevMonth(&quot;20101120&quot;)
     * 출력 : 20101001
     * </pre>
     * 
     * @param date
     *             java.sql.Date타입의 입력일자
     * @return java.sql.Date타입의 입력일 기준 전달의 첫번째 일자
     */
    public static java.sql.Date getFirstDateOfPrevMonth(java.sql.Date date) {
        return stringToSQLDate(getFirstDateOfPrevMonth(dateToString(date)));

    }

	/**
	 * 입력일 기준 전달의 마지막 일자 반환
	 * 
	 * <pre>
	 * 입력 : 20101120
	 * 예제 : DateUtil.getLastDateOfPrevMonth(&quot;20101120&quot;)
	 * 출력 : 20101031
	 * </pre>
	 * 
	 * @param date
	 *            String - (yyyy-MM-dd)타입 또는 (yyyyMMdd)의 입력일자
	 * @return 입력일 기준 전달의 마지막 일자 
	 */
	public static String getLastDateOfPrevMonth(String date) {
		String firstDateOfMonth = getFirstDateOfMonth(date);

		DateTimeFormatter fmt = createDateFormatterByInputStringLength(date);
		
		DateTime dt = parseDateTimeInternal(firstDateOfMonth, fmt);
		dt = dt.minusDays(1);
		return fmt.print(dt);
	}
	
	   /**
     * 입력일 기준 전달의 마지막 일자 반환
     * 
     * <pre>
     * 입력 : 20101120
     * 예제 : DateUtil.getLastDateOfPrevMonth(&quot;20101120&quot;)
     * 출력 : 20101031
     * </pre>
     * 
     * @param date
     *            java.sql.Date타입의 입력일자
     * @return java.sql.Date타입의 입력일 기준 전달의 마지막 일자 
     */
    public static java.sql.Date getLastDateOfPrevMonth(java.sql.Date date) {
        return stringToSQLDate(getLastDateOfPrevMonth(dateToString(date)));
       
    }

	/**
	 * 입력된 일자가 유효한 일자인지 체크
	 * 
	 * <pre>
	 * 입력 : 20110699
	 * 예제 : DateUtil.isDate(&quot;20110699&quot;)
	 * 출력 : false
	 * </pre>
	 * 
	 * @param date
	 *            String - (yyyy-MM-dd)타입 또는 (yyyyMMdd)의 입력일자
	 * 
	 * @return 일자가 유효하면 true 리턴
	 */
	public static boolean isDate(String date) {
		if (date == null) {
			return false;
		}
		
		if(date.length()==10){
			return isDate(date, DATE_PATTERN_DASH);	
		}		
		return isDate(date, DATE_PATTERN_DEFAULT);
	}

	/**
	 * 입력된 일자가 패턴에 맞는 유효한 일자인지 체크
	 * 
	 * <pre>
	 * 입력 : 20101201, yyyyMMdd
	 * 예제 : DateUtil.isDate(&quot;20101201&quot;, &quot;yyyyMMdd&quot;)
	 * 출력 : true
	 * <br>
	 * 입력 : 2010122, yyyyMMdd
	 * 예제 : DateUtil.isDate(&quot;2010122&quot;, &quot;yyyyMMdd&quot;)
	 * 출력 : false
	 * </pre>
	 * 
	 * @param date
	 *            String 타입의 입력 일자
	 * @param pattern
	 *            일자 타입
	 * @return 입력일자가 입력패턴과 일치 하면 true리턴 아니면 false리턴
	 */
	public static boolean isDate(String date, String pattern) {
		return isDateTime(date, pattern);
	}

	private static boolean isDateTime(String dateTime, String pattern) {
		
		int hourPos = StringUtil.indexOfIgnoreCase(pattern, "HH");
		// 시각 패턴이 존재하는 경우 
		if (hourPos != -1){
			if (hourPos != 0){
				String datePattern = pattern.substring(0, hourPos);
				String date = dateTime.substring(0, hourPos);
				if (!isDateTimePart(date, datePattern)){
					return false;
				}
			}
			
			String hour = dateTime.substring(hourPos, hourPos+2);
			
			int hourInt = Integer.parseInt(hour);
			
			if (hourInt < 0 || hourInt > 99){
				return false;
			}
			String remainPattern = pattern.substring(hourPos+2, pattern.length());
			String remainPart = dateTime.substring(hourPos+2, dateTime.length());
			return isDateTimePart(remainPart, remainPattern);
		}else{
			return isDateTimePart(dateTime, pattern);
		}
	}

	private static boolean isDateTimePart(String dateTime, String pattern) {
		try{
			DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
			DateTime dt = parseDateTimeInternal(dateTime, fmt);

			if (!fmt.print(dt).equals(dateTime)) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 입력된 시간이 유효한 시간인지 체크
	 * 
	 * <pre>
	 * 입력 : 13:56
	 * 예제 : DateUtil.isTime(&quot;13:56&quot;)
	 * 출력 : true
	 * </pre>
	 * 
	 * @param date
	 *            String - (HH:mm) 타입의 입력 일자 시간
	 * @return 입력된 시간이 유효하면 true, 아니면 false 리턴
	 */
	public static boolean isTime(String date) {
		return isTime(date, TIME_PATTERN);
	}

	/**
	 * 입력된 시간이 패턴에 맞는 유효한 시간인지 체크
	 * 
	 * <pre>
	 * 입력 : 13:56:24
	 * 예제 : DateUtil.isTime(&quot;13:56:24&quot;, &quot;HH:mm:ss&quot;)
	 * 출력 : true
	 * </pre>
	 * 
	 * @param time
	 *            String 타입의 입력 시각
	 * @param pattern
	 *            시간 패턴
	 * @return 입력된 일자 시간이 입력된 시각 패턴과 일치하면 true, 아니면 false 
	 */
	public static boolean isTime(String time, String pattern) {		
		return isDateTime(time, pattern);
	}

	/**
	 * 문자열을 java.util.Date 타입으로 변경
	 * 
	 * <pre>
	 * 입력 : 2010-12-14
	 * 예제 : DateUtil.stringToDate(&quot;2010-12-14&quot;)
	 * 출력 : Tue Dec 14 00:00:00 KST 2010
	 * </pre>
	 * 
	 * @param date
	 *           String - (yyyy-MM-dd)타입 또는 (yyyyMMdd)의 입력일자
	 * @return java.util.Date으로 변환된 일자
	 */
	public static Date stringToDate(String date) {
		if (date.length()==10){
			return stringToDate(date, DATE_PATTERN_DASH);	
		}
		
		return stringToDate(date, DATE_PATTERN_DEFAULT);
	}

	/**
	 * 특정 날짜 패턴의 문자열을 java.util.Date 타입으로 변경
	 * 
	 * <pre>
	 * 입력 : 2010/12/14, yyyy/MM/dd
	 * 예제 : DateUtil.stringToDate(&quot;2010/12/14&quot;, &quot;yyyy/MM/dd&quot;)
	 * 출력 : Tue Dec 14 00:00:00 KST 2010
	 * </pre>
	 * 
	 * @param date
	 *            String - 타입의 입력일자
	 * @param pattern
	 *            변환된 날짜 패턴
	 * @return java.util.Date으로 변환된 일자
	 */
	public static Date stringToDate(String date, String pattern) {
		DateTimeFormatter fmt = getDateTimeFormatter(pattern);
		return parseDateTimeInternal(date, fmt).toDate();
	}

	private static DateTimeFormatter getDateTimeFormatter(String pattern) {
		return DateTimeFormat.forPattern(pattern).withChronology(DEFAULT_CHRONOLOGY);
	}

	/**
	 * java.util.Date 타입을 문자열로 변환
	 * 
	 * <pre>
	 * 입력 : new Date(1292311593557l)
	 * 예제 : DateUtil.dateToString(new Date(1292311593557l))
	 * 출력 : 20101214
	 * </pre>
	 * 
	 * @param date
	 *            Date타입 입력일자
	 * @return String - (yyyyMMdd)으로 변환된 일자
	 */
	public static String dateToString(Date date) {
		return dateToString(date, DATE_PATTERN_DEFAULT);
	}

	/**
	 * java.util.Date 타입을 입력된 패턴에 맞는 문자열로 변환
	 * 
	 * <pre>
	 * 입력 : new Date(1292311593557l), yyyy-MM-dd
	 * 예제 : DateUtil.dateToString(new Date(1292311593557l), &quot;yyyy-MM-dd&quot;)
	 * 출력 : 2010-12-14
	 * </pre>
	 * 
	 * @param date
	 *            Date타입 입력일자
	 * @param pattern
	 *            날짜 타입
	 * @return String 타입으로 변환된 일자
	 */
	public static String dateToString(Date date, String pattern) {
		DateTimeFormatter fmt = getDateTimeFormatter(pattern);
		return fmt.print(date.getTime());
	}
	
	/**
	 * java.util.Date 타입을 입력된 패턴에 맞는 문자열로 변환 (Locale 지정 가능)
	 * 
	 * <pre>
	 * 입력 : new Date(1292311593557l), MMM dd yyyy, Locale.US
	 * 예제 : DateUtil.dateToString(new Date(1292311593557l), &quot;MMM dd yyyy&quot;, Locale.US)
	 * 출력 : Dec 14 2010
	 * </pre>
	 * 
	 * @param date
	 *            Date타입 입력일자
	 * @param pattern
	 *            날짜 타입
	 * @param locale
	 *            locale
	 * @return String 타입으로 변환된 일자
	 */
	public static String dateToString(Date date, String pattern, Locale locale) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern).withLocale(locale);
		return fmt.print(date.getTime());
	}
	
	/**
	 * 입력된 문자열 yyyyMMdd의 날짜 타입을 yyyy-MM-dd 타입으로 변환한여 반환한다. (반대의 경우도 가능)
	 * 
	 * <pre>
	 * 입력 : 20101214
	 * 예제 : DateUtil.convertDateFormat("20101214")
	 * 출력 : 2010-12-14
	 * <br>
	 * 입력 : 2017-01-28
	 * 예제 : DateUtil.convertDateFormat("2017-01-28")
	 * 출력 : 20170128
	 * </pre>
	 * 
	 * @param date
	 *            String -타입의 입력일자
	 * @return String 타입의 변환된 일자           
	 */
	public static String convertDateFormat(String date) {
		if (ValidationUtil.isEmptyTrimmed(date)){
			return "";
		}
		
		if (date.length() != 8 && date.length() != 10){
			return "";
		}
		
		DateTimeFormatter basefmt; //기본 패턴 
		DateTimeFormatter wantedfmt; //변환할 패턴

		if (date.length()==10){ //yyyy-MM-dd
			basefmt = DateTimeFormat.forPattern(DATE_PATTERN_DASH);
			wantedfmt = DateTimeFormat.forPattern(DATE_PATTERN_DEFAULT);
		}else{
			basefmt = DateTimeFormat.forPattern(DATE_PATTERN_DEFAULT);
			wantedfmt = DateTimeFormat.forPattern(DATE_PATTERN_DASH);
		}	
		
		DateTime dt = parseDateTimeInternal(date, basefmt);
		return wantedfmt.print(dt);
	}
	
	
	/**
	 * HHmmss시간타입을 HH:mm:ss타입으로 변환한여 반환한다. (반대의 경우도 가능)
	 * 
	 * <pre>
	 * 입력 : 150759
	 * 예제 : DateUtil.convertTimeFormat("150759")
	 * 출력 : 15:07:59
	 * <br>
	 * 입력 : 15:07:59
	 * 예제 : DateUtil.convertTimeFormat("15:07:59")
	 * 출력 : 150759
	 * </pre>
	 * 
	 * @param time
	 *            HHmmss형태의 시간문자열
	 * @return HH:mm:ss형태의 시간문자열           
	 */
	public static String convertTimeFormat(String time) {		
		if (ValidationUtil.isEmptyTrimmed(time)){
			return "";
		}
		
		if (time.length() != 6 && time.length() != 8){
			return "";
		}
		
		//시각 
		String hour = time.substring(0, 2);
		
		// HHmmss -> HH:mm:ss
		if (time.length() == 6){
			String minSec = time.substring(2); //mmss
			DateTimeFormatter basefmt = DateTimeFormat.forPattern(TIME_MINSEC_PATTERN);
			DateTimeFormatter wantedfmt = DateTimeFormat.forPattern(TIME_MINSEC_PATTERN_COLONE);
			
			DateTime dt = parseDateTimeInternal(minSec, basefmt);
			
			String convertedMinSec = wantedfmt.print(dt);
			//HH:mm:ss
			return hour + ":" + convertedMinSec;
			
		// HH:mm:ss ->	HHmmss
		}else{
			String minSec = time.substring(3);
			DateTimeFormatter basefmt = DateTimeFormat.forPattern(TIME_MINSEC_PATTERN_COLONE);
			DateTimeFormatter wantedfmt = DateTimeFormat.forPattern(TIME_MINSEC_PATTERN);
			DateTime dt = parseDateTimeInternal(minSec, basefmt);
			
			String convertedMinSec = wantedfmt.print(dt);
			return hour+convertedMinSec;
		}
	}	

	/**
	 * 입력된 문자열의 날짜 타입을 변환한여 반환한다.
	 * 
	 * <pre>
	 * 입력 : 20101214, yyyyMMdd, yyyy-MM-dd
	 * 예제 : DateUtil.convertDateFormat("20101214", "yyyyMMdd", "yyyy-MM-dd")
	 * 출력 : 2010-12-14
	 * <br>
	 * 입력 : 2010.12.14, yyyy.MM.dd, yyyy/MM/dd
	 * 예제 : DateUtil.convertDateFormat("2010.12.14", "yyyy.MM.dd", "yyyy/MM/dd")
	 * 출력 : 2010/12/14
	 * </pre>
	 * 
	 * @param date
	 *            String -타입의 입력일자
	 * @param basePattern
	 *            기존의 날짜 타입
	 * @param wantedPattern
	 *            변환된 날짜 타입
	 * @return String 타입의 변환된 일자           
	 */
	public static String convertDateFormat(String date, String basePattern, String wantedPattern) {
		return convertDateFormat(date, basePattern, wantedPattern, null);
	}
	
	/**
	 * 입력된 문자열의 날짜 타입을 변환한여 반환한다. (Locale 지정 가능)
	 * 
	 * <pre>
	 * 입력 : 2013.08.08, yyyy.MM.dd, MMM dd yyyy
	 * 예제 : DateUtil.convertDateFormat("2013.08.08", "yyyy.MM.dd", "MMM dd yyyy", Locale.US)
	 * 출력 : Aug 08 2013
	 * </pre>
	 * 
	 * @param date
	 *            String -타입의 입력일자
	 * @param basePattern
	 *            기존의 날짜 타입
	 * @param wantedPattern
	 *            변환된 날짜 타입
	 * @param locale
	 *            locale
	 * @return String 타입의 변환된 일자           
	 */
	public static String convertDateFormat(String date, String basePattern, String wantedPattern, Locale locale) {
		if (ValidationUtil.isEmptyTrimmed(date)){
			return "";
		}
		
		DateTimeFormatter basefmt = DateTimeFormat.forPattern(basePattern);
		boolean exceeded24 = false;
		String dateToConvert = date;
		int hourPos = StringUtil.indexOfIgnoreCase(basePattern, "HH");
		
		if (hourPos != -1){
			int hour = Integer.parseInt(date.substring(hourPos, hourPos+2));
			if (hour >= 24){
				exceeded24 = true;
				int remainHour = hour % 24;
				dateToConvert = date.substring(0, hourPos) + StringUtil.leftPad(Integer.toString(remainHour), 2, '0') + date.substring(hourPos+2);
			}
		}
		
		DateTime dt = parseDateTimeInternal(dateToConvert, basefmt);
		DateTimeFormatter wantedfmt; 
		if (locale != null){
			wantedfmt = DateTimeFormat.forPattern(wantedPattern).withLocale(locale);
		}else{
			wantedfmt = DateTimeFormat.forPattern(wantedPattern);
		}
		
		String convertedDate = wantedfmt.print(dt);
		
		if (exceeded24){
			int index = StringUtil.indexOfIgnoreCase(wantedPattern, "HH");
			if (index != -1){
				String originHour = date.substring(hourPos, hourPos+2);
				return convertedDate.substring(0, index) + originHour + convertedDate.substring(index+2); 
			}
		}
		
		return convertedDate;
	}

	/**
	 * 1900~2100 사이의 랜덤한 일자 반환
	 * 
	 * @return Date -(yyyyMMdd) 타입의 1900~2100 사이의 랜덤한 일자
	 */
	public static Date getRandomDate() {
		DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN_DEFAULT);

		Random generator = new Random(System.currentTimeMillis());

		//String pattern = "(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:29|30))|(?:(?:0[13578]|1[02])-31))";
		String pattern = "(?:19|20)[0-9]{2}(?:(?:0[1-9]|1[0-2])(?:0[1-9]|1[0-9]|2[0-8])|(?:(?!02)(?:0[1-9]|1[0-2])(?:29|30))|(?:(?:0[13578]|1[02])-31))";
		String date = "";

		while (!ValidationUtil.isRegexPatternMatch(date, pattern)) {
			String yyyy = StringUtil.leftPad(String.valueOf(generator.nextInt(200) + 1900), 4, '0');
			String mm = StringUtil.leftPad(String.valueOf(generator.nextInt(12)), 2, '0');
			String dd = StringUtil.leftPad(String.valueOf(generator.nextInt(30)), 2, '0');

			//date = yyyy + "-" + mm + "-" + dd;
			date = yyyy + mm + dd;
		}
		DateTime dt = parseDateTimeInternal(date, fmt);

		return dt.toDate();
	}
	
	/**
     * 1900~2100 사이의 랜덤한 일자 반환
     * 
     * @return Date -(yyyyMMdd) 타입의 1900~2100 사이의 랜덤한 일자
     */
    public static java.sql.Date getRandomSqlDate() {
        return new java.sql.Date(getRandomDate().getTime());
    }

	/**
	 * 문자열을 java.sql.Date 타입으로 변환
	 *
	 * @param date
	 *            String -(yyyy-MM-dd)타입 또는 (yyyyMMdd)의 변환된 입력일자
	 * @return java.sql.Date 타입으로 변환된 일자
	 * @throws java.sql.Date 타입으로 변환시 실패하면 Exception처리
	 */
	public static java.sql.Date stringToSQLDate(String date) {
		if(date.length()==10){
			return stringToSQLDate(date, DATE_PATTERN_DASH);	
		}		
		return stringToSQLDate(date, DATE_PATTERN_DEFAULT);
	}

	/**
	 * 패턴에 맞게 들어온 문자열을 java.sql.Date 타입으로 변환
	 * 
	 * @param date
	 *            String - 변환된 입력일자
	 * @param pattern
	 *            변환된 패턴
	 * @return java.sql.Date - 타입로 변환된 일자
	 */
	public static java.sql.Date stringToSQLDate(String date, String pattern) {
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			Date parsed = sdf.parse(date);
			return new java.sql.Date(parsed.getTime());
		}catch(Exception e){
			return null;
		}
	}
	



	/**
	 * 문자열을 java.util.Calendar 타입으로 변환
	 * 
	 * @param date
	 *            String - (yyyyMMddHHmmss) 타입의 입력일자
	 * @return java.util.Calendar - 타입의 변환된 일자
	 */
	public static Calendar stringToCalendar(String date) {
		if ((date == null) || (date.length() < 14)){
			return null;
		}	
		String year = date.substring(0, 4);
		String month = date.substring(4, 6);
		String day = date.substring(6, 8);
		String hour = date.substring(8, 10);
		String minute = date.substring(10, 12);
		String second = date.substring(12, 14);

		return new GregorianCalendar(NumberUtil.stringToInt(year), NumberUtil.stringToInt(month) - 1,
				NumberUtil.stringToInt(day), NumberUtil.stringToInt(hour), NumberUtil.stringToInt(minute),
				NumberUtil.stringToInt(second));
	}

	/**
	 * java.util.Calendar타입의 일자를 문자열로 변환
	 * 
	 * <pre>
	 * 입력 : new GregorianCalendar(2010, 11, 14, 12, 34, 12)
	 * 예제 : DateUtil.calendarToString(new GregorianCalendar(2010, 11, 14, 12, 34, 12))
	 * 출력 : 20101214123412000
	 * </pre>
	 * 
	 * @param calendar
	 *            Calendar - 타입의 입력일자
	 * @return String (yyyyMMddHHmmss) - 타입으로 변환된 입력일자
	 */
	public static String calendarToString(Calendar calendar) {
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1; // Calendar.MONTH ia
		// zero-based (Jan -> 0)
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);

		return (NumberUtil.intToString(year) + NumberUtil.intToString(month) + NumberUtil.intToString(day)
				+ NumberUtil.intToString(hour) + NumberUtil.intToString(minute) + NumberUtil.intToString(second) + "000");
	}

	/**
	 * 입력된 두 일자 사이의 분을 계산
	 * 
	 * <pre>
	 * 입력 : new GregorianCalendar(2010, 11, 14, 12, 34, 12), new GregorianCalendar(2010, 11, 14, 13, 32, 12)
	 * 예제 : DateUtil.getMinutes(new GregorianCalendar(2010, 11, 14, 12, 34, 12), new GregorianCalendar(2010, 11, 14, 13, 32, 12))
	 * 출력 : 58
	 * </pre>
	 * 
	 * @param startDate
	 *            Calendar - 타입의 계산할 일자의 시작일
	 * @param endDate
	 *           Calendar - 타입의 계산할 일자의 종료일
	 * @return 시작일과 종료일 사이의 분
	 */
	public static int getMinutes(Calendar startDate, Calendar endDate) {
		long utc1 = startDate.getTimeInMillis();
		long utc2 = endDate.getTimeInMillis();

		long result = (utc2 - utc1) / (60 * 1000);

		return (int) result;
	}

	/**
	 * 입력된 두 일자 사이의 분을 계산
	 * 
	 * @param start
	 *            String (yyyyMMddHHmmss) - 타입의 계산할 일자의 시작일
	 * @param end
	 *            String (yyyyMMddHHmmss) - 타입의 계산할 일자의 종료일
	 * @return 시작일과 종료일 사이의 분
	 */
	public static int getMinutes(String start, String end) {
		
		String startDate = getCompenstatedDate(start);
		
		String endDate = getCompenstatedDate(end);
		
		Calendar cal1 = stringToCalendar(startDate);
		Calendar cal2 = stringToCalendar(endDate);

		return getMinutes(cal1, cal2);
	}

	/**
	 * 어제 일자 반환
	 * 
	 * @return String (yyyyMMdd) - 타입의 어제 일자 
	 */
	public static String getYesterday() {		
		return getYesterday(DATE_PATTERN_DEFAULT);
	}

	/**
	 * 포맷에 맞는 어제 일자 반환
	 * 
	 * @param pattern
	 *            날짜 패턴
	 * @return String - 타입의 어제 일자
	 */
	public static String getYesterday(String pattern) {
		Calendar cal = getCalendar();
		cal.roll(Calendar.DATE, -1);
		Date date = cal.getTime();
		return dateToString(date, pattern);
	}

	/**
	 * 한국 시간대에 맞는java.util.Calendar 타입의 일자 반환
	 * 
	 * @return java.util.Calendar - 타입의 변환된 일자
	 */
	private static Calendar getCalendar() {
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+09:00"), Locale.KOREA);
		calendar.setTime(new Date(new Timestamp(System.currentTimeMillis()).getTime()));

		return calendar;
	}

	/**
	 * 두 일자 사이의 일자 목록 반환
	 * 
	 * @param startDate
	 *            String (yyyy-MM-dd) 또는 (yyyyMMdd) - 타입의 시작일
	 * @param endDate
	 *            String (yyyy-MM-dd) 또는 (yyyyMMdd) - 타입의 종료일
	 * @return 시작일과 종료일 사이의 일자 목록
	 */
	public static String[] getDates(String startDate, String endDate) {
		if(startDate.length() ==10){
			return getDates(startDate, endDate, DATE_PATTERN_DASH);
		}		
		return getDates(startDate, endDate, DATE_PATTERN_DEFAULT);
	}
	
	
	/**
     * 두 일자 사이의 일자 목록 반환
     * 
     * @param startDate
     *            java.sql.Date타입의 시작일
     * @param endDate
     *            java.sql.Date타입의 종료일
     * @return 시작일과 종료일 사이의 일자 목록
     */
    public static java.sql.Date[] getDates(java.sql.Date startDate, java.sql.Date endDate) {
        String[] strDates = getDates(dateToString(startDate), dateToString(endDate));
        java.sql.Date[] dates = new java.sql.Date[strDates.length]; 
        for(int i=0; i < dates.length; i++){
            dates[i]=stringToSQLDate(strDates[i]);
        }
        return dates;
    }
	

	/**
	 * 두 일자 사이의 일자 목록 반환
	 * 
	 * @param start
	 *            시작일
	 * @param end
	 *            종료일
	 * @param inputPattern
	 *            입력 일자 패턴
	 * @return 시작일과 종료일 사이의 일자 목록
	 */
	public static String[] getDates(String start, String end, String inputPattern) {
		int hourPos = StringUtil.indexOfIgnoreCase(inputPattern, "HH");
		String datePattern = inputPattern;
		String startDate = start;
		String endDate = end;
		
		if (hourPos != -1){
			datePattern = inputPattern.substring(0, hourPos);
			startDate = start.substring(0, hourPos);
			endDate = end.substring(0, hourPos);
		}
		
		List<String> result = new ArrayList<String>();
		result.add(startDate);

		Calendar cal = getCalendar();
		cal.setTime(stringToDate(startDate, datePattern));
		
		String nextDay = dateToString(cal.getTime(), datePattern);

		while (!nextDay.equals(endDate)) {
			cal.add(Calendar.DATE, 1);
			nextDay = dateToString(cal.getTime(), datePattern);
			result.add(nextDay);
		}
		return result.toArray(new String[0]);
	}

	/**
	 * String - (yyyyMMdd) 타입의 현재일자 반환
	 * 
	 * <pre>
	 * 입력 : 없음
	 * 예제 : DateUtil.getCurrentDate()
	 * 출력 : 20130814 (예시 - 현재일시 기준임)
	 * </pre>
	 * 
	 * 
	 * @return String (yyyyMMdd) - 타입의 현재일자
	 */
	public static String getCurrentDate() {
	    
		return getCurrentDate(DATE_PATTERN_DEFAULT);
	}
	


	/**
	 * 현재일자를 입력한 타입의 문자열로 반환
	 * 
	 * <pre>
	 * 입력 : yyyy.MM.dd
	 * 예제 : DateUtil.getCurrentDate("yyyy.MM.dd")
	 * 출력 : 2013.08.14 (예시 - 현재일시 기준임)
	 * </pre>
	 * 
	 * @param pattern
	 *            입력 일자 패턴
	 * @return String (입력 일자 패턴) - 타입의 변환된 일자 
	 */
	public static String getCurrentDate(String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return df.format(timestamp);
	}

	/**
	 * 현재 일자를 java.sql.Date 타입으로 반환
	 * 
	 * @return java.sql.Date -타입의 현재 일자
	 */
	public static java.sql.Date getCurrentSQLDate() {
		return new java.sql.Date(new Timestamp(System.currentTimeMillis()).getTime());
	}

	/**
	 * 현재 시각을 java.sql.Times 타입으로 반환
	 * 
	 * @return java.sql.Times - 타입의 현재 시각
	 */
	public static Time getCurrentSQLTime() {
		return new Time(new Timestamp(System.currentTimeMillis()).getTime());
	}

	/**
	 * 현재 시각을 문자열로 반환
	 * 
	 * @return String - 타입의 현재 시각
	 */
	public static String getCurrentTime() {
		return new Time(new Timestamp(System.currentTimeMillis()).getTime()).toString();
	}

	/**
	 * 현재 시각을 java.sql.Timestamp 타입으로 반환
	 * 
	 * @return java.sql.Timestamp - 타입의 현재 시각
	 */
	public static Timestamp getCurrentSQLTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 현재 Timestamp값을 문자열로 반환
	 * 
	 * @return String - 타입의 현재 Timestamp
	 */
	public static String getCurrentTimestamp() {
		return getCurrentSQLTimestamp().toString();
	}

	/**
	 * 입력한 일자의 연도 값을 변경하여 반환
	 * 
	 * @param date
	 *           java.util.Date - 타입의 입력일자
	 * @param year
	 *            변경할 연도 값
	 * @return java.util.Date - 타입의 변경일자
	 */
	public static Date replaceYear(Date date, long year) {

		Assert.isTrue(("" + year).length() <= 4, year + " must be less than 4-digit number.");

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.YEAR, (int)year);

		return calendar.getTime();
	}
	
	/**
     * 입력한 일자의 연도 값을 변경하여 반환
     * 
     * @param date
     *           java.sql.Date - 타입의 입력일자
     * @param year
     *            변경할 연도 값
     * @return java.sql.Date - 타입의 변경일자
     */
    public static java.sql.Date replaceYear(java.sql.Date date, long year) {

        Assert.isTrue(("" + year).length() <= 4, year + " must be less than 4-digit number.");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, (int)year);

        return new java.sql.Date(calendar.getTime().getTime());
    }

	/**
	 * 입력한 년,월,일,시,분,초의 값을 가지는 Date 객체를 반환
	 * 
	 * @param year
	 *             년도
	 * @param month
	 *             월
	 * @param day
	 *             일
	 * @param hourOfDay
	 *             시
	 * @param minute
	 *             분
	 * @param second
	 *             초
	 * @return  java.util.Date - 타입의 변환된 일자
	 */
	public static Date getDate(long year, long month, long day, long hourOfDay, long minute, long second) {
		Calendar cal = Calendar.getInstance();
		cal.set((int)year, (int)(month - 1), (int)day, (int)hourOfDay, (int)minute, (int)second);
		
		return cal.getTime();
	}

	/**
	 * 입력한 일자의 달의 마지막날을 반환
	 * 
	 * <pre>
	 * 입력 : 2008-02-22
	 * 예제 : DateUtil.getLastDayOfMonth("2008-02-22")
	 * 출력 : 29
	 * </pre>
	 * 
	 * @param date
	 *         String (yyyy-MM-dd)타입 또는 (yyyyMMdd)타입의 입력일자
	 * @return (int) - 타입의 입력일자 달의 마직막 날
	 */
	public static int getLastDayOfMonth(String date) {
		String examYmd = "";
		if(date.length() ==10){
			examYmd = StringUtil.left(date.trim(), 8) + "01";
		}else{
			examYmd = StringUtil.left(date.trim(), 6) + "01";	
		}
		// check if input date is 'yyyy-MM-dd' pattern
		//Assert.isTrue(isDate(examYmd), date + "must be in 'yyyy-MM-dd' pattern.");
		
		// get last day of the month
		return getLastDayOfMonthInt(examYmd);
	}
	
	/**
     * 입력한 일자의 달의 마지막날을 반환
     * 
     * <pre>
     * 입력 : 2008-02-22
     * 예제 : DateUtil.getLastDayOfMonth("2008-02-22")
     * 출력 : 29
     * </pre>
     * 
     * @param date
     *         java.sql.Date타입의 입력일자
     * @return (int) - 타입의 입력일자 달의 마직막 날
     */
    public static int getLastDayOfMonth(java.sql.Date date){
        return getLastDayOfMonth(dateToString(date));
    }
    

	/**
	 * 입력한 일자가 달의 마지막날인지 여부
	 * 
	 * <pre>
	 * 입력 : 2008-02-28
	 * 예제 : DateUtil.isLastDateOfMonth("2008-02-28")
	 * 출력 : false
	 * <br>
	 * 입력 : 2008-02-29
	 * 예제 : DateUtil.isLastDateOfMonth("2008-02-29")
	 * 출력 : true
	 * </pre>
	 * 
	 * @param date
	 *            String (yyyy-MM-dd) 타입 또는 (yyyyMMdd)의 입력일자
	 * @return 입력일자가 마지막날이 맞으면 true
	 */
	public static boolean isLastDateOfMonth(String date) {
		String examYmd = "";
		
		if(date.length() ==10){
			examYmd = StringUtil.left(date.trim(), 8) + "01";
		}else{
			examYmd = StringUtil.left(date.trim(), 6) + "01";	
		}
		
		int inputDay = Integer.parseInt(StringUtil.right(date.trim(), 2));

		int lastDay = getLastDayOfMonthInt(examYmd);

		if (inputDay == lastDay) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
     * 입력한 일자가 달의 마지막날인지 여부
     * 
     * <pre>
     * 입력 : 2008-02-28
     * 예제 : DateUtil.isLastDateOfMonth("2008-02-28")
     * 출력 : false
     * <br>
     * 입력 : 2008-02-29
     * 예제 : DateUtil.isLastDateOfMonth("2008-02-29")
     * 출력 : true
     * </pre>
     * 
     * @param date
     *            java.sql.Date의 입력일자
     * @return 입력일자가 마지막날이 맞으면 true
     */
    public static boolean isLastDateOfMonth(java.sql.Date date){
        return isLastDateOfMonth(dateToString(date));
    }

	private static int getLastDayOfMonthInt(String date) {
		Assert.notNull(date);
		//Assert.isTrue(isDate(date), date + " must be in 'yyyy-MM-dd' pattern");
		int month = 0; 
		
		if(date.length() ==10){
			month = Integer.parseInt(date.substring(5, 7));
		}else{
			month = Integer.parseInt(date.substring(4, 6));	
		}		
		
		int lastDayOfMonthValue = 0;
		if (isLeapYear(date)) {
			lastDayOfMonthValue = lastDayOfMonthForLeapYear[month - 1];
		} else {
			lastDayOfMonthValue = lastDayOfMonth[month - 1];
		}
		return lastDayOfMonthValue;

	}
	
	

	/**
	 * 입력한 일자가 윤년인지 여부
	 * 
	 * <pre>
	 * 입력 : 20080201
	 * 예제 : DateUtil.isLeapYear("20080201")
	 * 출력 : true
	 * </pre>
	 * 
	 * @param date
	 *            String (yyyy-MM-dd)타입 또는 yyyyMMdd의 입력일자
	 * @return 입력 일자가 윤년이면 true
	 */
	public static boolean isLeapYear(String date) {
		Assert.hasLength(date);
		Assert.isTrue(isDate(date), date + " must be in 'yyyy-MM-dd' or 'yyyyMMdd' pattern");
		
		return isLeapYear(Integer.parseInt(date.substring(0, 4)));
	}
	
	
	/**
     * 입력한 일자가 윤년인지 여부
     * 
     * <pre>
     * 입력 : 20080201
     * 예제 : DateUtil.isLeapYear("20080201")
     * 출력 : true
     * </pre>
     * 
     * @param date
     *            java.sql.Date타입의 입력일자
     * @return 입력 일자가 윤년이면 true
     */
    public static boolean isLeapYear(java.sql.Date date) {
        return isLeapYear(dateToString(date));
    }

	/**
	 * 입력한 년도가 윤년인지 여부
	 * 
	 * <pre>
	 * 입력 : 2008
	 * 예제 : DateUtil.isLeapYear(2008)
	 * 출력 : true
	 * </pre>
	 * 
	 * @param year
	 *            int - 타입의 입력 년도
	 * @return 입력 년도가 윤년이면 true
	 */
	public static boolean isLeapYear(long year) {
		Assert.isTrue(year > 0, year + " must be positive.");
		return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) ? true : false;
	}

	/**
	 *  ---------------------------------------------------end of anyframe-core ------------------------------------
	 */
	/**
	 * 입력한 타입으로 현재일시를 반환
	 * @param format
	 * 			입력 날짜 포맷
	 * @return String (입력 날짜 포맷) - 타입의 변환된 일자 
	 */
	public static String getCurrentFormattedDate(String format) {
		return getCurrentFormattedDate(new Timestamp(System.currentTimeMillis()), format);
	}
	
	/**
	 * 입력한 날짜의 타입을 변환한 문자열을 반환
	 * @param date
	 * 		  Date - 타입의 입력일자
	 * @param format
	 * 		    입력 날짜 포맷 
	 * @return
	 *        String - 타입으로 변환된 일자
	 */		   
	public static String getCurrentFormattedDate(Date date, final String format) {
		String ext = "";
		String baseFormat = format;
		if (isContainsNano(format)) {
			baseFormat = format.substring(0, format.length() - 3);
			ext = "000";
		}

		SimpleDateFormat formatter = new SimpleDateFormat(baseFormat);
		return formatter.format(date) + ext;
	}

	private static boolean isContainsNano(String format) {
		return format.endsWith("SSSSSS");
	}
	
	/**
	 * yyyyMMdd 타입의 포맷을 yyyyddd 타입의 Julian Date 로 변환
	 * 
	 * <pre>
	 * 입력 : 20130101
	 * 예제 : DateUtil.dateToJulian("20130101")
	 * 출력 : 2013001
	 * </pre>
	 * 
	 * @param inputDate yyyyMMdd 타입의 입력 날짜 
	 * @return yyyyddd 타입의 Julian Date 
	 */
	public static String dateToJulian(String inputDate){
		
		checkDate(inputDate);

		Calendar cal = new GregorianCalendar(Integer.parseInt(inputDate.substring(0, 4)), Integer.parseInt(inputDate.substring(4, 6)) - 1, Integer.parseInt(inputDate.substring(6, 8)));
		
		StringBuilder sb = new StringBuilder();
		sb.append(inputDate.substring(0, 4));
		sb.append(StringUtil.leftPad(String.valueOf(cal.get(Calendar.DAY_OF_YEAR)), 3, '0'));
		
		return sb.toString();
	}
	
	/**
	 * 입력된 (yyyyddd) 타입의 Julian Date를  yyyy-MM-dd 타입의 날짜로 변환한다.
	 * 
	 * <pre>
	 * 입력 : 2013001
	 * 예제 : DateUtil.julianToDate("2013001")
	 * 출력 : 2013-01-01
	 * </pre>
	 * 
	 * @param inputJulianDate yyyyddd 타입의 Julian Date
	 * @return  String (yyyy-MM-dd) - 타입으로 변환된 일자
	 */
	public static String julianToDate(String inputJulianDate){
		
		checkJulianDate(inputJulianDate);
		
		int days = Integer.parseInt(inputJulianDate.substring(4, 7));
		
		int[] lastDays = isLeapYear(Integer.parseInt(inputJulianDate.substring(0, 4))) ? lastDayOfMonthForLeapYear : lastDayOfMonth; 
		
		int sumDays = 0;
		int currentMonth = 0;
		
		while (sumDays < days){
			sumDays += lastDays[currentMonth];
			currentMonth ++;
		}
		
		sumDays -= lastDays[currentMonth-1]; 
		
		StringBuilder result = new StringBuilder();
		result.append(inputJulianDate.substring(0, 4));
		result.append(DATE_DELIMETER_DASH);
		result.append(StringUtil.leftPad(String.valueOf(currentMonth), 2, '0'));
		result.append(DATE_DELIMETER_DASH);
		result.append(StringUtil.leftPad(String.valueOf(days - sumDays), 2, '0'));
		
		return result.toString(); 
	}
	
	
	private static final String DATE_DELIMETER_DASH = "-";

	/**
	 * yyyyMMdd 타입의 날짜를 입력받아 해당 분기의 첫번째 날짜를 반환
	 *	 
	 *<pre>
	 * 입력 : 20130331
	 * 예제 : DateUtil.getFirstDayOfQuarter("20130331")
	 * 출력 : 20130101
	 * </pre>
	 *
	 * @param inputDate yyyyMMdd 타입의 날짜
	 * @return yyyyMMdd 타입의 분기 첫번째 날짜
	 */
	public static String getFirstDayOfQuarter(String inputDate){		
		
		checkDate(inputDate);
		
		int month = Integer.parseInt(inputDate.substring(4, 6));
		String year = inputDate.substring(0, 4);
		
		StringBuilder result = new StringBuilder();
		
		result.append(year);
		
		switch (month){
		case 1: case 2: case 3: 
			result.append("01").append("01");
			break;
		case 4: case 5: case 6:
			result.append("04").append("01");
			break;
		case 7: case 8: case 9:
			result.append("07").append("01");
			break;
		case 10: case 11: case 12:
			result.append("10").append("01");
			break;
		default:
			throw new IllegalArgumentException("month must between 1 and 12");	
		}
		
		return result.toString();
	}
	
	/**
     * yyyyMMdd 타입의 날짜를 입력받아 해당 분기의 첫번째 날짜를 반환
     *   
     *<pre>
     * 입력 : 20130331
     * 예제 : DateUtil.getFirstDayOfQuarter("20130331")
     * 출력 : 20130101
     * </pre>
     *
     * @param inputDate java.sql.Date타입의 날짜
     * @return java.sql.Date타입의 분기 첫번째 날짜
     */
    public static java.sql.Date getFirstDayOfQuarter(java.sql.Date inputDate){
        return stringToSQLDate(getFirstDayOfQuarter(dateToString(inputDate)));
    }
	
	/**
	 * yyyyMMdd 타입의 날짜를 입력받아 해당 분기의 마지막 날짜를 반환
	 *
	 * <pre>
	 * 입력 : 20130401
	 * 예제 : DateUtil.getLastDayOfQuarter("20130401")
	 * 출력 : 20130630
	 * </pre>
	 *
	 * @param inputDate yyyyMMdd 타입의 날짜
	 * @return yyyyMMdd 타입의 분기 마지막 날짜 
	 */
	public static String getLastDayOfQuarter(String inputDate){
		
		checkDate(inputDate);
		
		int month = Integer.parseInt(inputDate.substring(4, 6));
		String year = inputDate.substring(0, 4);
		
		StringBuilder result = new StringBuilder();
		
		result.append(year);
		
		switch (month){
		case 1: case 2: case 3: 
			result.append("03").append("31");
			break;
		case 4: case 5: case 6:
			result.append("06").append("30");
			break;
		case 7: case 8: case 9:
			result.append("09").append("30");
			break;
		case 10: case 11: case 12:
			result.append("12").append("31");
			break;
		default:
			throw new IllegalArgumentException("month must between 1 and 12");
			
		}
		
		return result.toString();
	}
	
	/**
     * yyyyMMdd 타입의 날짜를 입력받아 해당 분기의 마지막 날짜를 반환
     *
     * <pre>
     * 입력 : 20130401
     * 예제 : DateUtil.getLastDayOfQuarter("20130401")
     * 출력 : 20130630
     * </pre>
     *
     * @param inputDate java.sql.Date타입의 날짜
     * @return java.sql.Date타입의 분기 마지막 날짜 
     */
    public static java.sql.Date getLastDayOfQuarter(java.sql.Date inputDate){
        return stringToSQLDate(getLastDayOfQuarter(dateToString(inputDate)));
    }
	
	
	/**
	 * yyyyMMdd 타입의 날짜를 입력받아 해당 반기의 첫번째 날짜를 반환 
	 *
	 * <pre>
	 * 입력 : 20130531
	 * 예제 : DateUtil.getFirstDayOfHalf("20130531")
	 * 출력 : 20130101
	 * </pre>
	 *
	 * @param inputDate yyyyMMdd 타입의 날자
	 * @return yyyyMMdd 타입의 문자열 
	 */
	public static String getFirstDayOfHalf(String inputDate){
		
		checkDate(inputDate);
		
		int month = Integer.parseInt(inputDate.substring(4, 6));
		String year = inputDate.substring(0, 4);
		
		StringBuilder result = new StringBuilder();
		result.append(year);
		
		if (month >= 1 && month <=6){
			result.append("01").append("01");
		}else if (month >= 7 && month <= 12){
			result.append("07").append("01");
		}
		
		return result.toString();
		
	}
	
	/**
     * yyyyMMdd 타입의 날짜를 입력받아 해당 반기의 첫번째 날짜를 반환 
     *
     * <pre>
     * 입력 : 20130531
     * 예제 : DateUtil.getFirstDayOfHalf("20130531")
     * 출력 : 20130101
     * </pre>
     *
     * @param inputDate java.sql.Date타입의 날자
     * @return java.sql.Date타입의 문자열 
     */
    public static java.sql.Date getFirstDayOfHalf(java.sql.Date inputDate){
        return stringToSQLDate(getFirstDayOfHalf(dateToString(inputDate)));
        
    }
	
	/**
	 * yyyyMMdd 타입의 날짜를 입력받아 해당 반기의 마지막 날짜를 반환 
	 *
	 * <pre>
	 * 입력 : 20120229
	 * 예제 : DateUtil.getLastDayOfHalf("20120229")
	 * 출력 : 20120630
	 * </pre>
	 *
	 * @param inputDate yyyyMMdd 타입의 날자
	 * @return yyyyMMdd 타입의 문자열 
	 */
	public static String getLastDayOfHalf(String inputDate){
		
		checkDate(inputDate);
		
		int month = Integer.parseInt(inputDate.substring(4, 6));
		String year = inputDate.substring(0, 4);
		
		StringBuilder result = new StringBuilder();
		result.append(year);
		
		if (month >= 1 && month <=6){
			result.append("06").append("30");
		}else if (month >= 7 && month <= 12){
			result.append("12").append("31");
		}
		
		return result.toString();
		
	}
	
	/**
     * yyyyMMdd 타입의 날짜를 입력받아 해당 반기의 마지막 날짜를 반환 
     *
     * <pre>
     * 입력 : 20120229
     * 예제 : DateUtil.getLastDayOfHalf("20120229")
     * 출력 : 20120630
     * </pre>
     *
     * @param inputDate java.sql.Date타입의 날자
     * @return java.sql.Date타입의 문자열 
     */
    public static java.sql.Date getLastDayOfHalf(java.sql.Date inputDate){
        return stringToSQLDate(getLastDayOfHalf(dateToString(inputDate)));
    }
	
	private static void checkDate(String inputDate){
		
		int year = Integer.parseInt(inputDate.substring(0, 4));
		int month = Integer.parseInt(inputDate.substring(4, 6));
		int day = Integer.parseInt(inputDate.substring(6, 8));
		
		if (month < 1 || month > 12){
			throw new IllegalArgumentException("the input month value must between 1 and 12");
		}
		
		int[] lastDays = isLeapYear(year) ? lastDayOfMonthForLeapYear : lastDayOfMonth;
		
		if (day < 1 || day > lastDays[month-1]){
			throw new IllegalArgumentException("the input day value must between 1 and " + lastDays[month-1]);
		}
	}
	
	private static void checkJulianDate(String inputJulianDate){
		String year = inputJulianDate.substring(0, 4);
		
		int days = Integer.parseInt(inputJulianDate.substring(4, 7));
		
		if (isLeapYear(Integer.parseInt(year))){
			if (days < 1 || days > 366){
				throw new IllegalArgumentException("Leap Year day value must between 1 and 366 : " + days);
			}
		}else{
			if (days < 1 || days > 365){
				throw new IllegalArgumentException("Non Leap Year day value must between 1 and 365 : " + days);
			}
		}
	}
	
	/**
	 * yyyyMMdd 타입의 두 날짜를 입력받아(시작일, 종료일) 두 일자간의 차이 월,일수를 반환 
	 *
	 *	 AS-IS STD_91_PERIOD_CAL 항목의 KIGAN_MONTH_CAL PROC 에 대응하는 항목
	 * 
	 * @param startDate
	 *            String - 시작일
	 * @param endDate
	 *             String - 종료일
	 * @return String[] 형식으로 반환, String[0]에는 개월수, String[1]에는 일수가 저장
	 */
	public static String[] getMonthDayDiffToString(String startDate, String endDate) {
		return kiganMonthCal(startDate, endDate);
	}
	
	/**
     * yyyyMMdd 타입의 두 날짜를 입력받아(시작일, 종료일) 두 일자간의 차이 월,일수를 반환 
     *
     *   AS-IS STD_91_PERIOD_CAL 항목의 KIGAN_MONTH_CAL PROC 에 대응하는 항목
     * 
     * @param startDate
     *            java.sql.Date - 시작일
     * @param endDate
     *             java.sql.Date - 종료일
     * @return String[] 형식으로 반환, String[0]에는 개월수, String[1]에는 일수가 저장
     */
    public static String[] getMonthDayDiffToString(java.sql.Date startDate, java.sql.Date endDate) {
        return getMonthDayDiffToString(dateToString(startDate), dateToString(endDate));
    }
	
	/**
	 * AS-IS STD_91_PERIOD_CAL 항목의 KIGAN_MONTH_CAL PROC 에 대응하는 항목 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private static String[] kiganMonthCal(String startDate, String endDate){
		checkDate(startDate);
		checkDate(endDate);
		
		int diffMonths, diffDays;
		
		int startYear = Integer.parseInt(startDate.substring(0, 4));
		int startMonth = Integer.parseInt(startDate.substring(4, 6));
		int startDay = Integer.parseInt(startDate.substring(6, 8));
		
		int endYear = Integer.parseInt(endDate.substring(0, 4));
		int endMonth = Integer.parseInt(endDate.substring(4, 6));
		int endDay = Integer.parseInt(endDate.substring(6, 8));
		
		diffMonths = ((endYear-1) * 12 + endMonth) - ((startYear -1)*12 + startMonth);
		
		if (endDay >= startDay){
			diffDays = endDay - startDay;
			List<String> result = new ArrayList<String>();
			result.add(String.valueOf(diffMonths));
			result.add(String.valueOf(diffDays));	
			return result.toArray(new String[0]);
			
		}// 이상없음 확인 814
		
		
		//윤년 endYear로 윤년체크 
		boolean endYearIsLeap = isLeapYear(endYear);
		
		diffMonths --;
		int monthCount = endMonth -1;
		
		if (monthCount == 0){
			monthCount = 12;
		}// 이상없음 확인 850
		
		if (startDay > 29 && (endMonth == 2) && (endDay == 29)){
			diffMonths++;
			diffDays = 0;
			List<String> result = new ArrayList<String>();
			result.add(String.valueOf(diffMonths));
			result.add(String.valueOf(diffDays));	
			return result.toArray(new String[0]);
		}// 이상없음 확인 864
		
		if (endDay > 27  && endMonth == 2  && endYearIsLeap){
			
			
		}else{
			if ((startDay > lastDayOfMonth[endMonth-1]) && (endDay == lastDayOfMonth[endMonth-1]) ){
				diffMonths ++;
				diffDays = 0;
				List<String> result = new ArrayList<String>();
				result.add(String.valueOf(diffMonths));
				result.add(String.valueOf(diffDays));	
				return result.toArray(new String[0]);
			}
		}// 이상없음 확인 886
		
		if (startDay > 29 && monthCount == 2 && endYearIsLeap){
			
			diffDays = endDay;
			
			List<String> result = new ArrayList<String>();
			result.add(String.valueOf(diffMonths));
			result.add(String.valueOf(diffDays));	
			return result.toArray(new String[0]);
			
		}// 이상없음 확인 899
		
		if (startDay > lastDayOfMonth[monthCount-1] ){
			diffDays = endDay;
			List<String> result = new ArrayList<String>();
			result.add(String.valueOf(diffMonths));
			result.add(String.valueOf(diffDays));	
			return result.toArray(new String[0]);
		}// 이상??? 907
		
		if (endDay > 27  && endMonth == 2  && endYearIsLeap){
			
		}else{
			
			if (startDay == 29 && startMonth == 2 && (endDay == lastDayOfMonth[endMonth -1])) {
				diffMonths ++;
				diffDays = 0;
				List<String> result = new ArrayList<String>();
				result.add(String.valueOf(diffMonths));
				result.add(String.valueOf(diffDays));	
				return result.toArray(new String[0]);
			}
		}// 927
		
		
		if (endDay == 29 && endMonth == 2 && startDay == lastDayOfMonth[startMonth -1]){
			diffMonths++;
			diffDays = 0;
			
			List<String> result = new ArrayList<String>();
			result.add(String.valueOf(diffMonths));
			result.add(String.valueOf(diffDays));	
			return result.toArray(new String[0]);
		} // 942
		
		
		if (startDay == 29 && startMonth == 2 && endDay == 29 && endMonth == 2){
			diffMonths++;
			diffDays = 0;
			
			List<String> result = new ArrayList<String>();
			result.add(String.valueOf(diffMonths));
			result.add(String.valueOf(diffDays));	
			return result.toArray(new String[0]);
		} // 955
		
		
		if (endDay > 27 && endMonth == 2 && endYearIsLeap){
			// do nothing.. 
		}else{
			
			if (startDay == lastDayOfMonth[startMonth-1] && endDay == lastDayOfMonth[endMonth-1] ){
				diffMonths ++;
				diffDays = 0;
				
				List<String> result = new ArrayList<String>();
				result.add(String.valueOf(diffMonths));
				result.add(String.valueOf(diffDays));	
				return result.toArray(new String[0]);
			}
		} //975
		
		// 979
		diffDays = endDay + lastDayOfMonth[monthCount -1] - startDay;
		
		if (endYearIsLeap && endMonth == 3){
			diffDays = endDay + 29 - startDay;
		}
		
		if (!endYearIsLeap && endMonth == 3){
			diffDays = endDay + 28 - startDay;
		}
		
		List<String> result = new ArrayList<String>();
		result.add(String.valueOf(diffMonths));
		result.add(String.valueOf(diffDays));	
		return result.toArray(new String[0]);
		
	}
	
	/**
	 * 입력받은 두 날짜(yyyyMMdd) 사이의 일자 계산
	 * 
	 * <pre>
	 * 입력 : 20101101, 20101130
	 * 예제 : DateUtil.getDiffDays("20101101", "20101130")
	 * 출력 : 29
	 * </pre>
	 * 
	 * @param startDate
	 *             (yyyyMMdd) 시작일
	 * @param endDate
	 *             (yyyyMMdd) 종료일
	 * @return 시작일과 종료일 사이의 일자
	 * @see #getDays(String, String, String)
	 */
	public static int getDiffDays(String startDate, String endDate){
		return getDays(startDate, endDate, DATE_PATTERN_DEFAULT);
	}
	
	/**
     * 입력받은 두 날짜(yyyyMMdd) 사이의 일자 계산
     * 
     * <pre>
     * 입력 : 20101101, 20101130
     * 예제 : DateUtil.getDiffDays("20101101", "20101130")
     * 출력 : 29
     * </pre>
     * 
     * @param startDate
     *             java.sql.Date타입의 시작일
     * @param endDate
     *             java.sql.Date타입의 종료일
     * @return 시작일과 종료일 사이의 일자

     */
    public static int getDiffDays(java.sql.Date startDate, java.sql.Date endDate){
        return getDiffDays(dateToString(startDate), dateToString(endDate));
    }
    
	
	/**
	 * 입력받은 날짜가 유효범위 안에 있는지 확인( 11110101 보다 크거나 99991231 보다 작은지를 체크)
	 *
	 * <pre>
	 * 입력 : 11110101
	 * 예제 : DateUtil.checkDateRange("11110101")
	 * 출력 : false
	 * <br>
	 * 입력 : 20101202
	 * 예제 : DateUtil.checkDateRange("20101202")
	 * 출력 : true
	 * </pre>
	 * 
	 * @param date
	 *             yyyyMMdd 형식의 날짜
	 * @return 입력받은 날짜가 유효하면 true, 아니면 false를 리턴
	 */
	public static boolean checkDateRange(String date){
		return checkDateRange(date, true);
	}
	
	/**
	 * 경계값 체크여부를 입력받아 입력받은 날짜가 유효범위 안에 있는지 확인 
	 *
	 * <pre>
	 * 입력 : 11110101
	 * 예제 : DateUtil.checkDateRange("11110101", true)
	 * 출력 : false
	 * <br>
	 * 입력 : 11110101
	 * 예제 : DateUtil.checkDateRange("11110101", false)
	 * 출력 : true
	 * </pre>
	 * 
	 * @param date
	 *             yyyyMMdd 형식의 날짜
	 * @param checkBoundary
	 *             경계값 유효한 날짜로 처리할지 여부 (true 인 경우 11110101, 99991231은 유효하지 않은 범위로 처리, false 인 경우 11110101, 99991231은 유효한 범위로 처리)   
	 * @return 입력받은 날짜가 유효하면 true, 아니면 false를 리턴
	 */
	public static boolean checkDateRange(String date, boolean checkBoundary){
		if (!isDate(date)) {
			return false;
		}
		
		if (checkBoundary){
			if (date.compareTo("11110101") <= 0 || date.compareTo("99991231") >= 0){
				return false;
			}
		}else{
			if (date.compareTo("11110101") < 0 || date.compareTo("99991231") > 0){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 현재 시스템 시각을 HHmmss 타입으로 반환한다.
	 * 
	 * <pre>
	 * 입력 : 없음
	 * 예제 : DateUtil.getCurrentTimeHMS();
	 * 출력 : 194932  (예시 - 현재시간 기준임)
	 * </pre>
	 * 
	 * @return HHmmss 타입의 시각
	 */
	public static String getCurrentTimeHMS(){
		return getCurrentDateTime(TIME_HMS_PATTERN);
	}
	
	/** hour, minute, second, microsecond format (HHmmssSSS) */
	public static final DateTimeFormatter FORMAT_TIME_HMSS = DateTimeFormat.forPattern("HHmmssSSS");
	
	/**
	 * 현재 시스템 시각을 HHmmssSSSSSS 타입으로 반환한다. 
	 * (HH:시, mm:분, ss:초, SSSSSS:마이크로 초)
	 * <p>
	 * <b> 반환된 값은 절대 시간을 나타내는 것이 아니며 마이크로 수준의 해상도만 제공한다.
	 * 따라서 반환된 값을 가지고 시간 순서를 비교해서는 안됨 </b> 
	 * 
	 * <pre>
	 * 입력 : 없음
	 * 예제 : DateUtil.getCurrentTimeMHSMicro();
	 * 출력 : 194932000000  (예시 - 현재시간 기준임)
	 * </pre>
	 * @return HHmmssSSSSSS 타입의 시간 문자열
	 */
	public static String getCurrentTimeMHSMicro() {
		long micro = (System.nanoTime())/1000;
		
		return FORMAT_TIME_HMSS.print(new Timestamp(System.currentTimeMillis()).getTime()) + String.format("%03d", (micro % 1000));
		
	}
	
	/**
	 *
	 * public constants to check weekcode
	 * @category 커스터마이징 요건 
	 */
	public static final String SUNDAY 	= "0";
	public static final String MONDAY 	= "1";
	public static final String TUESDAY 	= "2";
	public static final String WEDNESDAY = "3";
	public static final String THURSDAY = "4";
	public static final String FRIDAY 	= "5";
	public static final String SATURDAY = "6";

	//private static final String DATE_DELIMETER_DASH = "-";
	
	/**
	 * yyyy-MM-dd 타입의 날짜를 입력받아 해당 일자의 요일코드를 반환
	 * 
	 * @category 커스터마이징 요건
	 * 요일코드는 0 : 일요일 1: 월요일: 2: 화요일, .. 6: 토요일
	 * @param inputDate yyyy-MM-dd 타입의 날자
	 * @return String - 타입의 해당일자의 요일 코드
	 */
	public static String getDayOfWeekCode(String inputDate){
		
		if (!isDate(inputDate)){
			return "";
		}
		
		Calendar cal = Calendar.getInstance();
		if(inputDate.length() == 8){
		    cal.set(Integer.parseInt(inputDate.substring(0, 4)) , Integer.parseInt(inputDate.substring(4, 6)) - 1 , Integer.parseInt(inputDate.substring(6, 8)));
		}
		
		if(inputDate.length() == 10){
		    cal.set(Integer.parseInt(inputDate.substring(0, 4)) , Integer.parseInt(inputDate.substring(5, 7)) - 1 , Integer.parseInt(inputDate.substring(8, 10)));
		}
		
	
		return String.valueOf(cal.get(Calendar.DAY_OF_WEEK)-1); // dayOfWeek
		
	}
	
	/**
     * java.sql.Date 타입의 날짜를 입력받아 해당 일자의 요일코드를 반환
     * 
     * @category 커스터마이징 요건
     * 요일코드는 0 : 일요일 1: 월요일: 2: 화요일, .. 6: 토요일
     * @param inputDate java.sql.Date타입의 날자
     * @return String - 타입의 해당일자의 요일 코드
     */
    public static String getDayOfWeekCode(java.sql.Date inputDate){
        return getDayOfWeekCode(dateToString(inputDate));
    }
	
	/**
	 * 기준시간(HHmmss)에 대하여 일정 시간/분/초 이후의 시간을 구한다.
	 * 
	 * <pre>
	 * 입력 : "142600",0,20,0
	 * 예제 : DateUtil.getEndTime("142600",0,20,0);
	 * 출력 : 144600
	 * <br>
	 * 입력 : "220000", 2, 0, 0
	 * 예제 :  DateUtil.getEndTime("220000", 2, 0, 0)
	 * 출력 : 000000
	 * <br>
	 * 입력 : "140800", 2, 20, 60
	 * 예제 :  DateUtil.getEndTime("140800", 2, 20, 60)
	 * 출력 : 162900
	 * <br>
	 * 입력 : "143800", -1, -30, 0
	 * 예제 :  DateUtil.getEndTime("143800", -1, -30, 0)
	 * 출력 : 130800
	 * <br>
	 * 입력 : "144000", -1, -50, 0
	 * 예제 :  DateUtil.getEndTime("144000", -1, -50, 0)
	 * 출력 : 125000
	 * </pre>
	 * 
	 * @param baseTime
	 *             HHmmss 형태의 시간
	 * @param hour
	 *             시
	 * @param min
	 *             분
	 * @param sec
	 *             초
	 * @return HHmmss 형태의 문자열 시간
	 */
	public static String getEndTime(String baseTime, long hour, long min, long sec) {
		Calendar calendar = getCalendar();
		SimpleDateFormat formatter = new SimpleDateFormat(TIME_HMS_PATTERN);		
		
		try {			
			calendar.setTime(formatter.parse(baseTime));
			
			calendar.add(Calendar.HOUR, (int)hour);
			calendar.add(Calendar.MINUTE, (int)min);
			calendar.add(Calendar.SECOND, (int)sec);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		//캘린더객체를 hhmmss형태의 문자열로 반환
		return formatter.format(calendar.getTime());
	}
	
	/**
	 * 입력받은 두 일자의 배치비교
	 *  
	 * <pre>
	 * 입력 : new Date(1292311593000l), 20101202
	 * 예제 : DateUtil.compareBetween(new Date(1292311593000l),&quot;20101202&quot;)
	 * 출력 : 1
	 * </pre>
	 * 
	 * @param date1
	 *            Date 타입의 비교될 일자
	 * @param date2
	 *             (yyyy-MM-dd)타입 또는 (yyyyMMdd)의 String type의 비교대상일자.
	 * @return date1이 date2보다 이후이면 1, 같으면 0, 이전이면 -1
	 */
	public static int compareBetween(Date date1, String date2) {
		if(date2.length() ==10){
			return compareBetween(date1, date2, DATE_PATTERN_DASH);
		}else{
			return compareBetween(date1, date2, DATE_PATTERN_DEFAULT);
		}
	}

	/**
	 * 입력받은 두 일자의 배치비교
	 * 
	 * <pre>
	 * 입력 : new Date(1292311593000l), 20101202, yyyyMMdd
	 * 예제 : DateUtil.compareBetween(new Date(1292311593000l),&quot;20101202&quot;, &quot;yyyyMMdd&quot;)
	 * 출력 : 1
	 * </pre>
	 * 
	 * @param date1
	 *             Date 타입의 비교될 일자
	 * @param date2
	 *           String 타입의 비교대상 일자 
	 * @param date2pattern
	 *            date2의 변환된 날짜 패턴
	 * @return date1이 date2보다 이후이면 1, 같으면 0, 이전이면 -1
	 */
	public static int compareBetween(Date date1, String date2, String date2pattern) {
		String date = dateToString(date1, date2pattern);
		return compareBetween(date, date2);
	}
	
	/**
	 * 입력받은 두 일자의 배치비교
	 * 
	 * <pre>
	 * 입력 : 20101206, 20101202
	 * 예제 : DateUtil.compareBetween(&quot;20101206&quot;, &quot;20101202&quot;)
	 * 출력 : 1
	 * <br>
	 * 입력 : 20101206, 20101206
	 * 예제 : DateUtil.compareBetween(&quot;20101206&quot;, &quot;20101206&quot;)
	 * 출력 : 0
	 * <br>
	 * 입력 : 20101206, 20101210
	 * 예제 : DateUtil.compareBetween(&quot;20101206&quot;, &quot;20101210&quot;)
	 * 출력 : -1
	 * </pre>
	 * 
	 * @param str1
	 *            String 타입(yyyyMMdd)의 비교될 일자
	 * @param str2
	 *            String 타입(yyyyMMdd)의 비교대상 일자 
	 * @return date1이 date2보다 이후이면 1, 같으면 0, 이전이면 -1
	
	 */
	public static int compareBetween(String str1, String str2) {
		
		int compareResult = str1.compareTo(str2); 
		
		if (compareResult > 0){
			return 1;
		}else if (compareResult == 0){
			return 0;
		}else{
			return -1;
		}
	}

	/**
	 * 입력받은 두 일자의 배치비교
	 * 
	 * <pre>
	 * 입력 : new Date(1292311593000l), new Date(1292252400000l)
	 * 예제 : DateUtil.compareBetween(new Date(1292311593000l), new Date(1292252400000l))
	 * 출력 : 1
	 * </pre>
	 * 
	 * @param date1
	 *            Date 타입의 비교될 일자
	 * @param date2
	 *            Date 타입의 비교대상 일자 
	 * @return date1이 date2보다 이후이면 1, 같으면 0, 이전이면 -1
	
	 */
	public static int compareBetween(Date date1, Date date2) {
		if (date1.getTime() > date2.getTime()) {
			return 1;
		}else if (date1.getTime() == date2.getTime()) {
			return 0;
		}else{
			return -1;
		}
	}

	/**
	 * 입력받은 두 java.sql.Timestamp 비교
	 * 
	 * @param timestamp1
	 *            Timestamp 타입의 비교될 일자
	 * @param timestamp2
	 *             Timestamp 타입의 비교대상 일자
	 * @return timestamp1이 timestamp2보다 이후이면 1, 같으면 0, 이전이면 -1
	 */
	public static int compareBetween(Timestamp timestamp1, Timestamp timestamp2) {
		if (timestamp1.getTime() > timestamp2.getTime()) {
			return 1;
		}else if (timestamp1.getTime() == timestamp2.getTime()) {
			return 0;
		}else{
			return -1;
		}
	}

	/**
	 * 입력받은 java.sql.Timestamp과 문자열과 비교
	 * 
	 * @param timestamp1
	 *          Timestamp 타입의 비교될 일자
	 * @param timestamp2
	 *         (yyyyMMdd HH:mm:ss.SSS)타입의 String 타입의 비교대상 일자 
	 * @return timestamp1이 timestamp2보다 이후이면 1, 같으면 0, 이전이면 -1
	 */
	public static int compareBetween(Timestamp timestamp1, String timestamp2) {
		return compareBetween(timestamp1, timestamp2, TIMESTAMP_PATTERN_DEFAULT);
	}

	/**
	 * 사용자로부터 시간,분,초를 입력받아, 현재시스템시간을 기준으로 경과된 날짜를 yyyyMMdd HH:mm:ss 형태로 리턴한다.
	 * 
	 * <pre>
	 * 입력 : 24, 10. 0
	 * 예제 : DateUtil.getEndTimeFromSystdate(24, 10, 0)
	 * 출력 : 20131003 20:14:13 (예시)
	 * </pre>
	 * 
	 * @param hour
	 *            시
	 * @param min
	 *            분
	 * @param sec
	 *            초           
	 * @return yyyyMMdd HH:mm:ss 형태의 문자열 시간
	 */
	public static String getEndTimeFromSystdate(long hour, long min, long sec){
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_HMS_PATTERN); // yyyyMMddHHmmss		
		Calendar calendar = getCalendar();

		try{
			calendar.add(Calendar.HOUR, (int)hour);
			calendar.add(Calendar.MINUTE, (int)min);
			calendar.add(Calendar.SECOND, (int)sec);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return formatter.format(calendar.getTime());
	}
	
	public static String getTimeMsg(String time){
		if(time == null ||time.length() != 6)
			return "";
		
		String hour = time.substring(0, 2);
		String min = time.substring(2, 4);
		String sec = time.substring(4, 6);
		
		return hour + "시 " + min + "분 " + sec + "초";	
	}
	
	/**
	 * 기준년월일과 월일(mmDD)을 입력받아 GMT년월일을 반환한다. (AS-IS: proc GET_GMT_YEAR)
	 * 
	 * <pre>
	 * 입력 : "20130131", "1201"
	 * 예제 : DateUtil.getGMTDate("20130131", "1201")
	 * 출력 : 20121201
	 * <br>
	 * 입력 : "20130131", "1201"
	 * 예제 : DateUtil.getGMTDate("2011201", "0117")
	 * 출력 : 20120117
	 * </pre>
	 * @param baseYmd
	 *           기준년월일(yyyyMMdd형식) 
	 * @param mmdd
	 *           월일
	 * @return yyyyMMdd 형태의 날짜
	 */
	public static String getGMTDate(String baseYmd, String mmdd){
		if (baseYmd == null || mmdd == null) {
			return "";
		}
		
		int year = Integer.parseInt(baseYmd.substring(0, 4));
		
		if("12".equals(mmdd.substring(0, 2)) && "01".equals(baseYmd.substring(4,6))){
			year = year - 1;
		}else{
			if("01".equals(mmdd.substring(0, 2)) && "12".equals(baseYmd.substring(4,6))){
				year = year + 1;
			}
		}
		
		StringBuilder result = new StringBuilder();
		result.append(year).append(mmdd);
		
		return result.toString();
	}
	
	
	public static final String DATE_INIT_VALUE = "11110101";
	public static final String DATE_MAX_VALUE = "99991231";
	
	/**
	 * Value를 받아 기본값 " "이면 일자기본값을 반환한다.  
	 * 업무모듈의 if를 줄이기 위함 
	 * 
	 * @param value 
	 * @return 
	 */
	public static String getDateInitValueIfDefault(String value) {
		if(" ".equals(value)) {
			return DATE_INIT_VALUE;
		} 
		return value;
	}

	public static Date utcTimestampToDate(String timestamp) throws Exception{


		timestamp = timestamp.replaceFirst("Z", "-0000");

		SimpleDateFormat sdf = new SimpleDateFormat(TIME_UTC_TIMESTAMP_PATTERN);



		return sdf.parse(timestamp);



	}
	
	
	/**
	 * yyyymm
	 * @param inputDate
	 * @return
	 */
	private static String getCompenstatedDate(String inputDate){
		
		int hour = Integer.parseInt(inputDate.substring(8, 10));
		
		if (hour >= 24){
			
			int offset = hour / 24;
			int remainHour = hour % 24;
			
			String date = inputDate.substring(0, 8);
			
			String compendatedDate = addDays(date, offset);
			
			return compendatedDate + StringUtil.leftPad(Integer.toString(remainHour), 2, '0') + inputDate.substring(10);  
					
		}else{
			return inputDate;
		}
		
		
	}

    public static void main(String[] args){
	    
	    System.out.println("addDays : " + DateUtil.addDays(new java.sql.Date(System.currentTimeMillis()), 10L));
        System.out.println("addMonths : " + DateUtil.addMonths(new java.sql.Date(System.currentTimeMillis()), 10L));
        System.out.println("addYears : " + DateUtil.addYears(new java.sql.Date(System.currentTimeMillis()), 10L));
        System.out.println("addYearMonthDay : " + DateUtil.addYearMonthDay(new java.sql.Date(System.currentTimeMillis()), 10L, 10L, 10L));
        System.out.println("getCurrentDate : " + DateUtil.getCurrentDate() );
	    System.out.println("getCurrentSQLDate : " + DateUtil.getCurrentSQLDate() );
	    System.out.println("getDates : " + Arrays.toString(DateUtil.getDates(stringToSQLDate("2016-07-06"), stringToSQLDate("2016-08-04"))));
        System.out.println("getDayOfWeek : " + DateUtil.getDayOfWeek(new java.sql.Date(System.currentTimeMillis())));
        System.out.println("getDayOfWeekCode : " + DateUtil.getDayOfWeekCode(new java.sql.Date(System.currentTimeMillis())));
        System.out.println("getDays : " + DateUtil.getDiffDays(new java.sql.Date(System.currentTimeMillis()), stringToSQLDate("2016-08-06")));
        System.out.println("getEndDate : " + DateUtil.getEndDate(new java.sql.Date(System.currentTimeMillis()), 31L));
        System.out.println("getFirstDateOfMonth : " + DateUtil.getFirstDateOfMonth(new java.sql.Date(System.currentTimeMillis())));
        System.out.println("getFirstDateOfPrevMonth : " + DateUtil.getFirstDateOfPrevMonth(new java.sql.Date(System.currentTimeMillis())));
        System.out.println("getFirstDayOfHalf : " + DateUtil.getFirstDayOfHalf(new java.sql.Date(System.currentTimeMillis())));
        System.out.println("getFirstDayOfQuarter : " + DateUtil.getFirstDayOfQuarter(new java.sql.Date(System.currentTimeMillis())));
        System.out.println("getLastDateOfMonth : " + DateUtil.getLastDateOfMonth(new java.sql.Date(System.currentTimeMillis())));
        System.out.println("getLastDateOfPrevMonth : " + DateUtil.getLastDateOfPrevMonth(new java.sql.Date(System.currentTimeMillis())));
        System.out.println("getLastDayOfHalf : " + DateUtil.getLastDayOfHalf(new java.sql.Date(System.currentTimeMillis())));
        System.out.println("getLastDayOfQuarter : " + DateUtil.getLastDayOfQuarter(new java.sql.Date(System.currentTimeMillis())));
        System.out.println("getLastDayOfMonth : " + DateUtil.getLastDayOfMonth(new java.sql.Date(System.currentTimeMillis())));
        System.out.println("getMonthDayDiffToString : " + Arrays.toString(DateUtil.getMonthDayDiffToString(new java.sql.Date(System.currentTimeMillis()), stringToSQLDate("2016-08-30"))));
        System.out.println("getRandomSqlDate : " + DateUtil.getRandomSqlDate());
        System.out.println("isLastDateOfMonth : " + DateUtil.isLastDateOfMonth(stringToSQLDate("2016-08-31")));
        System.out.println("isLeapYear : " + DateUtil.isLeapYear(stringToSQLDate("2016-08-31")));
        System.out.println("replaceYear : " + DateUtil.replaceYear(stringToSQLDate("2015-08-31"), 2016));
        
        
	}
	
	
	
	
}
   