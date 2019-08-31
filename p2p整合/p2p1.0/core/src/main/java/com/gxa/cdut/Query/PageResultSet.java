package com.gxa.cdut.Query;

import com.gxa.cdut.domain.UserInfo;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.List;

@Alias("PageResultSet")
public class PageResultSet {

    private List listData;
    private Integer totalCount;
    private Integer currentPage;
    private Integer pageSize;
    private Integer totalPage;

    public PageResultSet(List listData,Integer totalCount,Integer currentPage,Integer pageSize){
        this.listData = listData;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;

        this.totalPage = totalCount % pageSize ==0 ? totalCount/pageSize : totalCount/pageSize +1;

    }

    public static PageResultSet empty(){
        return new PageResultSet(new ArrayList(),0,1,5);
    }

    public List getListData() {
        return listData;
    }

    public void setListData(List listData) {
        this.listData = listData;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalCount == 0 ? 1 : totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
