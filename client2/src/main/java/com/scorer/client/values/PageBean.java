package com.scorer.client.values;

import java.util.List;
import java.util.Map;

public class PageBean {

    private String token;
    private String desc;
    private Map<String, Object> searchs;
    private Map<String, Object> pager;

    private long total;
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
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

    public Map<String, Object> getPager() {
        return pager;
    }

    public void setPager(Map<String, Object> pager) {
        this.pager = pager;
    }

    public int getPageSize() {
        return Integer.parseInt((String)pager.get("ps"));
    }

    public int getStartIndex() {
        int currentPage = Integer.parseInt((String)pager.get("page"));
        int pageSize = getPageSize();
        return (currentPage - 1) * pageSize;
    }

    public static int getMaxPage(Integer count, int size) {
        return (int) Math.ceil(((double)count/(double)size));
    }

}