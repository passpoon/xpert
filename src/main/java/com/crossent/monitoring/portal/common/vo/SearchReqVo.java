package com.crossent.monitoring.portal.common.vo;

import java.util.HashMap;
import java.util.Map;

public class SearchReqVo {

    private Map<String, String> keywords;

    private String rangeType;

    private String rangeStart;

    private String rangeEnd;

    public SearchReqVo() {
    }


    public Map<String, String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Map<String, String> keywords) {
        this.keywords = keywords;
    }



    public void addKeyword(String field, String keyword) {
        if(this.keywords == null){
            this.keywords = new HashMap<String, String>();
        }
        keywords.put(field, keyword);

    }

    public String getRangeType() {
        return rangeType;
    }

    public void setRangeType(String rangeType) {
        this.rangeType = rangeType;
    }

    public String getRangeStart() {
        return rangeStart;
    }

    public void setRangeStart(String rangeStart) {
        this.rangeStart = rangeStart;
    }

    public String getRangeEnd() {
        return rangeEnd;
    }

    public void setRangeEnd(String rangeEnd) {
        this.rangeEnd = rangeEnd;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SearchReqVo{");
        sb.append("keywords=").append(keywords);
        sb.append(", rangeType='").append(rangeType).append('\'');
        sb.append(", rangeStart='").append(rangeStart).append('\'');
        sb.append(", rangeEnd='").append(rangeEnd).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
