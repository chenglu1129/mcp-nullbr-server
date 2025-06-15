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
     * 获取列表
     * @param listId 列表ID
     * @param page 页码
     * @return 列表详情
     */
    @Tool(description = "getList")
    String getList(int listId, int page);
    
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
    @Tool(description = "getTVSeasonInfo")
    String getTVSeasonInfo(int tmdbId, int seasonNumber);
    
    /**
     * 获取电视剧集详情
     * @param tmdbId 电视剧TMDB ID
     * @param seasonNumber 季号
     * @param episodeNumber 集号
     * @return 集详情
     */
    @Tool(description = "getTVEpisodeInfo")
    String getTVEpisodeInfo(int tmdbId, int seasonNumber, int episodeNumber);
    
    /**
     * 获取人物信息
     * @param tmdbId 人物TMDB ID
     * @param page 页码
     * @return 人物详情
     */
    @Tool(description = "getPersonInfo")
    String getPersonInfo(int tmdbId, int page);
    
    /**
     * 获取电影合集信息
     * @param tmdbId 合集TMDB ID
     * @return 合集详情
     */
    @Tool(description = "getCollectionInfo")
    String getCollectionInfo(int tmdbId);
    
    /**
     * 获取电影资源
     * @param tmdbId 电影TMDB ID
     * @param resourceType 资源类型 (115, magnet, ed2k, video)
     * @return 资源信息
     */
    @Tool(description = "getMovieResources")
    String getMovieResources(int tmdbId, String resourceType);
    
    /**
     * 获取电视剧网盘资源
     * @param tmdbId 电视剧TMDB ID
     * @return 资源信息
     */
    @Tool(description = "getTVResources")
    String getTVResources(int tmdbId);
    
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
    
    /**
     * 获取人物网盘资源
     * @param tmdbId 人物TMDB ID
     * @return 资源信息
     */
    @Tool(description = "getPersonResources")
    String getPersonResources(int tmdbId);
    
    /**
     * 获取电影合集网盘资源
     * @param tmdbId 合集TMDB ID
     * @return 资源信息
     */
    @Tool(description = "getCollectionResources")
    String getCollectionResources(int tmdbId);
} 