package com.nullbr.mcp.dto;

/**
 * 资源请求DTO
 */
public class ResourceRequest {
    private int tmdbId;
    private String resourceType;
    private Integer seasonNumber;
    private Integer episodeNumber;
    
    public ResourceRequest() {
    }
    
    public ResourceRequest(int tmdbId, String resourceType) {
        this.tmdbId = tmdbId;
        this.resourceType = resourceType;
    }
    
    public ResourceRequest(int tmdbId, Integer seasonNumber, String resourceType) {
        this.tmdbId = tmdbId;
        this.seasonNumber = seasonNumber;
        this.resourceType = resourceType;
    }
    
    public ResourceRequest(int tmdbId, Integer seasonNumber, Integer episodeNumber, String resourceType) {
        this.tmdbId = tmdbId;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
        this.resourceType = resourceType;
    }
    
    public int getTmdbId() {
        return tmdbId;
    }
    
    public void setTmdbId(int tmdbId) {
        this.tmdbId = tmdbId;
    }
    
    public String getResourceType() {
        return resourceType;
    }
    
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
    
    public Integer getSeasonNumber() {
        return seasonNumber;
    }
    
    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }
    
    public Integer getEpisodeNumber() {
        return episodeNumber;
    }
    
    
    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }
} 