package com.scorer.client.values;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageBean {

    private String token;
    private String desc;
    private Map<String, Object> searchs = new HashMap<>();
    private List<HashMap<String, String>> sort;
    private Long total;
    private List<?> rows;

    private Integer limit = 1000;
    private Integer paging = 1;

    public int getPageSize() {
        return limit;
    }

    public int getStartIndex() {
        return (paging - 1) * limit;
    }

    public List<HashMap<String, String>> getSort() {
        return sort;
    }

    public void setSort(List<HashMap<String, String>> sort) {
        this.sort = sort;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Map<String, Object> getSearchs() {
        return searchs;
    }

    public void setSearchs(Map<String, Object> searchs) {
        this.searchs = searchs;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPaging() {
        return paging;
    }

    public void setPaging(Integer paging) {
        this.paging = paging;
    }

    public static int getMaxPage(Integer count, int size) {
        return (int) Math.ceil(((double)count/(double)size));
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTotal() {
        return total;
    }

}
