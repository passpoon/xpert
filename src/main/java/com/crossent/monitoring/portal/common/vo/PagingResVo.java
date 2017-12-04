package com.crossent.monitoring.portal.common.vo;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PagingResVo<T> {



    /**
     * 현제 페이지
     */
    int number;

    /**
     * 페이지 크기
     */
    int size;

    /**
     * 전체 페이지 수
     */
    int totalPages;

    /**
     * 현제 페이지 데이터수
     */
    int numberOfElements;

    /**
     * 전체 데이터 수
     */
    long totalElements;

    /**
     * 첫페이지 여부
     */
    boolean isFirstPage;

    /**
     * 마지막 페이지 여부
     */
    boolean isLastPage;

    /**
     * 다음 페이지가 존재하는지 여부
     */
    boolean hasNextPage;


    List<String> titles;


    List<T> list;


    public PagingResVo() {
        super();
    }


    public PagingResVo(Page page, boolean listCopyYn) {
        this.number = page.getNumber();
        this.size = page.getSize();
        this.totalPages= page.getTotalPages();
        this.numberOfElements = page.getNumberOfElements();
        this.totalElements = page.getTotalElements();
        this.isFirstPage = page.isFirst();
        this.isLastPage = page.isLast();
        this.hasNextPage = page.hasNext();

        if(listCopyYn) {

            this.list = page.getContent();
        }
    }

    public PagingResVo(int number, int size, int totalElements){
        this.number = number;
        this.size = size;
        this.totalElements = totalElements;

        int devi = size==0?0:totalElements/size;
        int remain = size==0?0:totalElements%size;

        if(remain == 0){
            totalPages = devi;
        }else{
            totalPages = devi + 1;
        }


        if(number == 0){
            isFirstPage = true;
        }else{
            isFirstPage = false;
        }

        if((number+1) ==  totalPages){
            isLastPage = true;
        }else{
            isLastPage = false;
        }

        if(isLastPage && remain != 0){
            numberOfElements = remain;

        }else{
            numberOfElements = size;

        }

        if(isLastPage){
            hasNextPage = false;
        }else{
            hasNextPage = true;
        }
    }


    public PagingResVo(int number, int size, int totalPages, int numberOfElements, long totalElements, boolean isFirstPage, boolean isLastPage, boolean hasNextPage) {
        this.number = number;
        this.size = size;
        this.totalPages = totalPages;
        this.numberOfElements = numberOfElements;
        this.totalElements = totalElements;
        this.isFirstPage = isFirstPage;
        this.isLastPage = isLastPage;
        this.hasNextPage = hasNextPage;
    }

    public PagingResVo(int number, int size, int totalPages, int numberOfElements, long totalElements, boolean isFirstPage, boolean isLastPage, boolean hasNextPage, List<T> list) {
        this.number = number;
        this.size = size;
        this.totalPages = totalPages;
        this.numberOfElements = numberOfElements;
        this.totalElements = totalElements;
        this.isFirstPage = isFirstPage;
        this.isLastPage = isLastPage;
        this.hasNextPage = hasNextPage;
        this.list = list;
    }




    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void addListItem(T item){
        if(list == null){
            list = new ArrayList<T>();

        }

        list.add(item);
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public void addTile(String title){
        if(titles == null){
            titles = new ArrayList<String>();

        }
        titles.add(title);

    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PagingResVo{");
        sb.append("number=").append(number);
        sb.append(", size=").append(size);
        sb.append(", totalPages=").append(totalPages);
        sb.append(", numberOfElements=").append(numberOfElements);
        sb.append(", totalElements=").append(totalElements);
        sb.append(", isFirstPage=").append(isFirstPage);
        sb.append(", isLastPage=").append(isLastPage);
        sb.append(", hasNextPage=").append(hasNextPage);
        sb.append(", titles=").append(titles);
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}
