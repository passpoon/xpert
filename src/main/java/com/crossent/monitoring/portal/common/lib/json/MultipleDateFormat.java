package com.crossent.monitoring.portal.common.lib.json;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


@SuppressWarnings("serial")
public class MultipleDateFormat
    extends DateFormat
{
   
    private final static String DATE_FORMAT_STR_FLAT_8 = "yyyyMMdd";
    private final static String DATE_FORMAT_STR_FLAT_10 = "yyyyMMddHH";
    private final static String DATE_FORMAT_STR_FLAT_12 = "yyyyMMddHHmm";
    private final static String DATE_FORMAT_STR_FLAT_14 = "yyyyMMddHHmmss";
    private final static String DATE_FORMAT_STR_FLAT_17 = "yyyyMMddHHmmssSSS";
    private final static String DATE_FORMAT_STR_PLAIN = "yyyy-MM-dd";
    private final static String DATE_FORMAT_STR_PLAIN_S = "yyyy-MM-dd HH:mm:ss";
    private final static String DATE_FORMAT_STR_PLAIN_MS = "yyyy-MM-dd HH:mm:ss.SSS";
    

   
    protected final static String[] ALL_FORMATS = new String[] {
        DATE_FORMAT_STR_FLAT_8,
        DATE_FORMAT_STR_FLAT_10,
        DATE_FORMAT_STR_FLAT_12,
        DATE_FORMAT_STR_FLAT_14,
        DATE_FORMAT_STR_FLAT_17,
        DATE_FORMAT_STR_PLAIN,
        DATE_FORMAT_STR_PLAIN_S,
        DATE_FORMAT_STR_PLAIN_MS
    };


    private final static TimeZone DEFAULT_TIMEZONE;
    static {
        DEFAULT_TIMEZONE = TimeZone.getTimeZone("Asia/Seoul");
    }

    private final static Locale DEFAULT_LOCALE = Locale.KOREA;
    
    
    protected final static DateFormat DATE_FORMAT_FLAT;
    protected final static DateFormat DATE_TIME_FORMAT_PLAIN_MS;

    protected final static DateFormat DATE_FORMAT_PLAIN;


    static {
        DATE_FORMAT_FLAT = new SimpleDateFormat(DATE_FORMAT_STR_FLAT_17, DEFAULT_LOCALE);
        DATE_FORMAT_FLAT.setTimeZone(DEFAULT_TIMEZONE);
        DATE_TIME_FORMAT_PLAIN_MS = new SimpleDateFormat(DATE_FORMAT_STR_PLAIN_MS, DEFAULT_LOCALE);
        DATE_TIME_FORMAT_PLAIN_MS.setTimeZone(DEFAULT_TIMEZONE);
        DATE_FORMAT_PLAIN = new SimpleDateFormat(DATE_FORMAT_STR_PLAIN, DEFAULT_LOCALE);
        DATE_FORMAT_PLAIN.setTimeZone(DEFAULT_TIMEZONE);
    }
    
   
    public final static MultipleDateFormat instance = new MultipleDateFormat();

    protected transient TimeZone _timezone;

    protected final Locale _locale;


    protected Boolean _lenient;
    
    
    protected transient DateFormat _formatFlat;
    protected transient DateFormat _formatPlainSSS;
    protected transient DateFormat _formatPlain;


    public MultipleDateFormat() {
        _locale = DEFAULT_LOCALE;
    }

    

    protected MultipleDateFormat(TimeZone tz, Locale loc, Boolean lenient) {
        _timezone = tz;
        _locale = loc;
        _lenient = lenient;
    }
    
    public static TimeZone getDefaultTimeZone() {
        return DEFAULT_TIMEZONE;
    }
    
 
    public MultipleDateFormat withTimeZone(TimeZone tz) {
        if (tz == null) {
            tz = DEFAULT_TIMEZONE;
        }
        if ((tz == _timezone) || tz.equals(_timezone)) {
            return this;
        }
        return new MultipleDateFormat(tz, _locale, _lenient);
    }

    public MultipleDateFormat withLocale(Locale loc) {
        if (loc.equals(_locale)) {
            return this;
        }
        return new MultipleDateFormat(_timezone, loc, _lenient);
    }
    
   
    public MultipleDateFormat clone() {
        
        return new MultipleDateFormat(_timezone, _locale, _lenient);
    }






    @Override 
    public TimeZone getTimeZone() {
        return _timezone;
    }

    @Override
    public void setTimeZone(TimeZone tz)
    {
      
        if (!tz.equals(_timezone)) {
            _clearFormats();
            _timezone = tz;
        }
    }


    @Override
    public void setLenient(boolean enabled) {
        Boolean newValue = enabled;
        if (_lenient != newValue) {
            _lenient = newValue;
            
            _clearFormats();
        }
    }

    @Override 
    public boolean isLenient() {
        if (_lenient == null) {
            
            return true;
        }
        return _lenient.booleanValue();
    }

 
    @Override
    public Date parse(String dateStr) throws ParseException
    {
        dateStr = dateStr.trim();
        ParsePosition pos = new ParsePosition(0);

        Date dt = null;

        if (looksLikeDateTime(dateStr)) { // also includes "plain"
            dt = parseAsFormat(dateStr, pos, true);
        }
        if (dt != null) {
            return dt;
        }

        StringBuilder sb = new StringBuilder();
        for (String f : ALL_FORMATS) {
            if (sb.length() > 0) {
                sb.append("\", \"");
            } else {
                sb.append('"');
            }
            sb.append(f);
        }
        sb.append('"');
        throw new ParseException
            (String.format("Can not parse date \"%s\": not compatible with any of standard forms (%s)",
                           dateStr, sb.toString()), pos.getErrorIndex());
    }

    @Override
    public Date parse(String dateStr, ParsePosition pos)
    {
        if (looksLikeDateTime(dateStr)) { 
            try {
                return parseAsFormat(dateStr, pos, false);
            } catch (ParseException e) { 
                return null;
            }
        }else{
            return null;
        }
        
    }

 
    
    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo,
            FieldPosition fieldPosition)
    {

        
        if (_formatFlat == null) {
            _formatFlat = _cloneFormat(DATE_FORMAT_FLAT, DATE_FORMAT_STR_FLAT_17,
                    _timezone, _locale, _lenient);
        }
        return _formatFlat.format(date, toAppendTo, fieldPosition);
    }


    
    @Override
    public String toString() {
        String str = "DateFormat "+getClass().getName();
        TimeZone tz = _timezone;
        if (tz != null) {
            str += " (timezone: "+tz+")";
        }
        str += "(locale: "+_locale+")";
        return str;
    }

    @Override 
    public boolean equals(Object o) {
        return (o == this);
    }

    @Override 
    public int hashCode() {
        return System.identityHashCode(this);
    }

 
    protected boolean looksLikeDateTime(String dateStr)
    {
    	 int len = dateStr.length();
         //System.out.println("len : " + len);
         char c = dateStr.charAt(len-1);
        if (dateStr.length() >= 5
            && Character.isDigit(dateStr.charAt(0))
            && Character.isDigit(dateStr.charAt(3))
            && dateStr.charAt(4) == '-'
            ) {
            return true;
        }else if(isDigit(dateStr) && (len == 8 || len == 10 || len == 12 || len == 14 || len == 17)){
        	return true;
        }
        	
        return false;
    }

    protected Date parseAsFormat(String dateStr, ParsePosition pos, boolean throwErrors)
            throws ParseException
    {
        //System.out.println("orig dateStr" + dateStr);
        int len = dateStr.length();
        //System.out.println("len : " + len);
        char c = dateStr.charAt(len-1);
        
        //System.out.println("c : " + c);
        DateFormat df;
        String formatStr;

        // Need to support "plain" date...
        if (len <= 17 && isDigit(dateStr)) {
            
            switch(len){
            case 8:
                dateStr += "000000000";
                break;
            case 10:
                dateStr += "0000000";
                break;
            case 12:
                dateStr += "00000";
                break;    
            case 14:
                dateStr += "000";
                break;
            }
            
            
            df = _formatFlat;
            
            formatStr = DATE_FORMAT_STR_FLAT_17;
            
            //System.out.println("dateStr" + dateStr);
            if (df == null) {
                df = _formatFlat = _cloneFormat(DATE_FORMAT_FLAT, formatStr,
                        _timezone, _locale, _lenient);
            }
        }else if (len <= 10 && Character.isDigit(c)) {
            df = _formatPlain;
            formatStr = DATE_FORMAT_STR_PLAIN;
            if (df == null) {
                df = _formatPlain = _cloneFormat(DATE_FORMAT_PLAIN, formatStr,
                        _timezone, _locale, _lenient);
            }
        } else {
            // Let's see if we have timezone indicator or not...
            dateStr = dateStr.replace('T', ' ');
            
            int iZ = dateStr.indexOf('Z');
            if(iZ > 0){
                dateStr = dateStr.substring(0, iZ);
            }
           // dateStr = dateStr.replaceAll("Z", "");
            
            //System.out.println("dateStr :" + dateStr);
                // If not, plain date. Easiest to just patch 'Z' in the end?
                StringBuilder sb = new StringBuilder(dateStr);
                // And possible also millisecond part if missing
                int timeLen = len - dateStr.lastIndexOf(' ') - 1;
                //System.out.println("timeLen : " + timeLen);
                if (timeLen < 12) { // missing, or partial
                    switch (timeLen) {
                    case 11: sb.append('0');
                        break;
                    case 10: sb.append("00");
                        break;
                    case 9: sb.append("000");
                        break;
                    default:
                        sb.append(".000");
                    }
                }
                //sb.append('Z');
                dateStr = sb.toString();
                
                //System.out.println("to dateStr : " + dateStr);
                df = _formatPlainSSS;
                formatStr = DATE_FORMAT_STR_PLAIN_MS;
                if (df == null) {
                    df = _formatPlainSSS = _cloneFormat(DATE_TIME_FORMAT_PLAIN_MS, formatStr,
                            _timezone, _locale, _lenient);
                }
            
        }
        Date dt = df.parse(dateStr, pos);
        // 22-Dec-2015, tatu: With non-lenient, may get null
        if (dt == null) {
            throw new ParseException
            (String.format("Can not parse date \"%s\": while it seems to fit format '%s', parsing fails (leniency? %s)",
                           dateStr, formatStr, _lenient),
               pos.getErrorIndex());
        }
        return dt;
    }


    private final static boolean hasTimeZone(String str)
    {
        // Only accept "+hh", "+hhmm" and "+hh:mm" (and with minus), so
        int len = str.length();
        if (len >= 6) {
            char c = str.charAt(len-6);
            if (c == '+' || c == '-') return true;
            c = str.charAt(len-5);
            if (c == '+' || c == '-') return true;
            c = str.charAt(len-3);
            if (c == '+' || c == '-') return true;
        }
        return false;
    }

    private final static DateFormat _cloneFormat(DateFormat df, String format,
            TimeZone tz, Locale loc, Boolean lenient)
    {
        if (!loc.equals(DEFAULT_LOCALE)) {
            df = new SimpleDateFormat(format, loc);
            df.setTimeZone((tz == null) ? DEFAULT_TIMEZONE : tz);
        } else {
            df = (DateFormat) df.clone();
            if (tz != null) {
                df.setTimeZone(tz);
            }
        }
        if (lenient != null) {
            df.setLenient(lenient.booleanValue());
        }
        return df;
    }

    protected void _clearFormats() {
        
        _formatFlat = null;
        _formatPlainSSS = null;
        _formatPlain = null;
    }
    
    private static boolean isDigit(String dateStr){
        char[] chars = dateStr.toCharArray();
        for(char c : chars){
            
            if(!Character.isDigit(c)){
                return false;
            }
        }
        
        return true;
    }
    
  
}

