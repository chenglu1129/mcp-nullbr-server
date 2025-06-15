package com.nullbr.mcp.service;

import org.springframework.ai.tool.annotation.Tool;

/**
 * NullBR API服务接口，提供与NullBR API交互的方法
 */
public interface NullbrApiService {

    /**
     * 搜索影视资源
     * @param query 搜索关键词
     * @param page 页码
     * @return 搜索结果
     */
    @Tool(description = "searchMedia")
    String searchMedia(String query, int page);
    
    /**
     * 获取电影详情
     * @param tmdbId 电影TMDB ID
     * @return 电影详情
     */
    @Tool(description = "getMovieInfo")
    String getMovieInfo(int tmdbId);
    
    /**
     * 获取电视剧详情
     * @param tmdbId 电视剧TMDB ID
     * @return 电视剧详情
     */
    @Tool(description = "getTVInfo")
    String getTVInfo(int tmdbId);
    
    /**
     * 获取电视剧季详情
     * @param tmdbId 电视剧TMDB ID
     * @param seasonNumber 季号
     * @return 季详情
     */
    String getTVSeasonInfo(int tmdbId, int seasonNumber);
    
    /**
     * 获取电视剧集详情
     * @param tmdbId 电视剧TMDB ID
     * @param seasonNumber 季号
     * @param episodeNumber 集号
     * @return 集详情
     */
    String getTVEpisodeInfo(int tmdbId, int seasonNumber, int episodeNumber);
    
    /**
     * 获取电影资源
     * @param tmdbId 电影TMDB ID
     * @param resourceType 资源类型 (115, magnet, ed2k, video)
     * @return 资源信息
     */
    @Tool(description = "getMovieResources")
    String getMovieResources(int tmdbId, String resourceType);
    
    /**
     * 获取电视剧季资源
     * @param tmdbId 电视剧TMDB ID
     * @param seasonNumber 季号
     * @param resourceType 资源类型 (magnet)
     * @return 资源信息
     */
    @Tool(description = "getTVSeasonResources")
    String getTVSeasonResources(int tmdbId, int seasonNumber, String resourceType);
    
    /**
     * 获取电视剧集资源
     * @param tmdbId 电视剧TMDB ID
     * @param seasonNumber 季号
     * @param episodeNumber 集号
     * @param resourceType 资源类型 (magnet, ed2k, video)
     * @return 资源信息
     */
    @Tool(description = "getTVEpisodeResources")
    String getTVEpisodeResources(int tmdbId, int seasonNumber, int episodeNumber, String resourceType);
} 