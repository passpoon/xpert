package com.crossent.monitoring.portal.common.vo;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PagingReqVo {
    /**
     * 현제 페이지
     */
    @ApiModelProperty(hidden = true)
    int page;

    /**
     * 페이지 크기
     */
    @ApiModelProperty(hidden = true)
    int pageSize;

    public PagingReqVo() {
    }

    public PagingReqVo(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public PageRequest toPagingRequest(){
        return toPagingRequest(null);

    }

    public PageRequest toPagingRequest(Sort sort){
        PageRequest pageRequest = null;

        if(sort == null ){
            return new PageRequest(page, pageSize);
        } else{
            return new PageRequest(page, pageSize, sort);
        }

    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PagingReqVo{");
        sb.append("page=").append(page);
        sb.append(", pageSize=").append(pageSize);
        sb.append('}');
        return sb.toString();
    }
}
