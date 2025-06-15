package com.nullbr.mcp.dto;

/**
 * 搜索请求DTO
 */
public class SearchRequest {
    private String query;
    private int page = 1;
    
    public SearchRequest() {
    }
    
    public SearchRequest(String query, int page) {
        this.query = query;
        this.page = page;
    }
    
    public String getQuery() {
        return query;
    }
    
    public void setQuery(String query) {
        this.query = query;
    }
    
    public int getPage() {
        return page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
} 