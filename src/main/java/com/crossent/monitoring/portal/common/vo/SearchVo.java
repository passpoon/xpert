package com.crossent.monitoring.portal.common.vo;


import com.crossent.monitoring.portal.common.lib.util.DateUtil;
import com.crossent.monitoring.portal.common.lib.util.StringUtil;

import java.util.*;

public class SearchVo {

    public static final String DATE_FORMAT =DateUtil.DATE_HMS_PATTERN;



    private static final long MS_1s = 1000L;
    private static final long MS_1m = MS_1s*60;
    private static final long MS_1h = MS_1m*60;
    private static final long MS_1d = MS_1h*24;
    boolean haveKeyworkd = false;
    boolean haveRange = false;

    Map<String, String> keywords;

    List<String> keys;

    RangeType rangeType;
    long rangeTerm;
    String startDttm;
    String endDttm;
//
//    public SearchVo(){
//        super();
//
//    }

    public SearchVo(SearchReqVo searchReqVo){
        super();
       // this();
        keys = new ArrayList<String>();
        keywords = new HashMap<String, String>();

        Map<String, String> kws = searchReqVo.getKeywords();

        if( kws != null) {
            Iterator<String> keySet = kws.keySet().iterator();
            while (keySet.hasNext()) {
                String key = keySet.next();
                String val = kws.get(key);

                if(StringUtil.isNotEmpty(key) && StringUtil.isNotEmpty(val)){
                    haveKeyworkd = true;
                    if(!keys.contains(key)){
                        keys.add(key);
                    }
                    keywords.put(key, val);
                }
            }
        }

        String strRangeType = searchReqVo.getRangeType();
        rangeType = RangeType.forRangeType(strRangeType);
        if(rangeType == null){
            haveRange = false;

        }else{

            switch(rangeType){
                case MANUAL:{
                    startDttm = searchReqVo.getRangeStart();
                    endDttm = searchReqVo.getRangeEnd();
                    if(StringUtil.isEmpty(startDttm)){
                        startDttm = DateUtil.dateToString(new Date(System.currentTimeMillis() - MS_1d), DATE_FORMAT);
                    }else if(StringUtil.isEmpty(endDttm)){
                        endDttm =  DateUtil.getCurrentDate(DATE_FORMAT);
                    }
                }
                break;

                case MIN:{
                    rangeTerm = Long.parseLong(strRangeType.substring(0, strRangeType.length() -1));
                    startDttm = DateUtil.dateToString(new Date(System.currentTimeMillis() - rangeTerm * MS_1m), DATE_FORMAT);
                    endDttm = DateUtil.getCurrentDate(DATE_FORMAT);
                }
                break;
                case HOUR:{
                    rangeTerm = Long.parseLong(strRangeType.substring(0, strRangeType.length() -1));
                    startDttm = DateUtil.dateToString(new Date(System.currentTimeMillis() - rangeTerm * MS_1h), DATE_FORMAT);
                    endDttm = DateUtil.getCurrentDate(DATE_FORMAT);
                }
                break;
                case DAY:{
                    rangeTerm = Long.parseLong(strRangeType.substring(0, strRangeType.length() -1));
                    startDttm = DateUtil.addDaysForDateHms(DateUtil.getCurrentDate(DATE_FORMAT), rangeTerm);
                    endDttm = DateUtil.getCurrentDate(DATE_FORMAT);

                }
                break;
                case MONTH:{
                    rangeTerm = Long.parseLong(strRangeType.substring(0, strRangeType.length() -1));
                    startDttm = DateUtil.addMonthsForDateHms(DateUtil.getCurrentDate(DATE_FORMAT), rangeTerm);
                    endDttm = DateUtil.getCurrentDate(DATE_FORMAT);
                }
                break;
                case YEAR:{
                    rangeTerm = Long.parseLong(strRangeType.substring(0, strRangeType.length() -1));
                    startDttm = DateUtil.addYearsForDateHms(DateUtil.getCurrentDate(DATE_FORMAT), rangeTerm);
                    endDttm = DateUtil.getCurrentDate(DATE_FORMAT);
                }
                break;

            }

            if(StringUtil.isNotEmpty(startDttm)&& StringUtil.isNotEmpty(endDttm)){
                haveRange = true;
            }

        }

    }



    enum RangeType{
        MIN("m"), HOUR("h"), DAY("d"), WEEK("w"), MONTH("M"), YEAR("y"), MANUAL("manual");

        String flag;

        private RangeType(String flag){
            this.flag = flag;
        }

        public String getFlag(){
            return flag;
        }


        public static RangeType forRangeType(String rangeType){
            if("manual".equals(rangeType)){
                return RangeType.MANUAL;
            }else {
                if( rangeType != null && rangeType.length() > 1){
                    String flag = rangeType.substring(rangeType.length()-1);
                    switch (flag){
                        case "m":
                            return RangeType.MIN;
                        case "h":
                            return RangeType.HOUR;
                        case "d":
                            return RangeType.DAY;
                        case "w":
                            return RangeType.WEEK;
                        case "M":
                            return RangeType.MONTH;
                        case "y":
                            return RangeType.YEAR;
                        default:
                            return null;

                    }
                }else{
                    return null;
                }
            }

        }





    }


    public static String getDateFormat() {
        return DATE_FORMAT;
    }



    public boolean isHaveKeyworkd() {
        return haveKeyworkd;
    }

    public void setHaveKeyworkd(boolean haveKeyworkd) {
        this.haveKeyworkd = haveKeyworkd;
    }

    public boolean isHaveRange() {
        return haveRange;
    }

    public void setHaveRange(boolean haveRange) {
        this.haveRange = haveRange;
    }

    public Map<String, String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Map<String, String> keywords) {
        this.keywords = keywords;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public RangeType getRangeType() {
        return rangeType;
    }

    public void setRangeType(RangeType rangeType) {
        this.rangeType = rangeType;
    }

    public long getRangeTerm() {
        return rangeTerm;
    }

    public void setRangeTerm(long rangeTerm) {
        this.rangeTerm = rangeTerm;
    }

    public String getStartDttm() {
        return startDttm;
    }

    public String getStartDttm(String format) {
        return DateUtil.convertDateFormat(startDttm, DATE_FORMAT, format);
    }

    public void setStartDttm(String startDttm) {
        this.startDttm = startDttm;
    }

    public String getEndDttm() {
        return endDttm;
    }

    public String getEndDttm(String format) {
        return DateUtil.convertDateFormat(endDttm, DATE_FORMAT, format);

    }

    public void setEndDttm(String endDttm) {

        this.endDttm = endDttm;
    }


    public String getKeyword(String key){

        return keywords.get(key);
    }

    public void setKeyword(String key, String value){
        if(keywords == null){
            keywords = new HashMap<String, String>();
        }
        keywords.put(key, value);

    }

}
