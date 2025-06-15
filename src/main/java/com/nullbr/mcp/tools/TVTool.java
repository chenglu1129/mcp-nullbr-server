package com.nullbr.mcp.tools;

import com.nullbr.mcp.service.NullbrApiService;
import org.springframework.ai.tool.annotation.Tool;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.stereotype.Component;

/**
 * 电视剧工具
 */
@Component
public class TVTool {
    
    private final NullbrApiService nullbrApiService;
    
    public TVTool(NullbrApiService nullbrApiService) {
        this.nullbrApiService = nullbrApiService;
    }
    
    /**
     * 获取电视剧详情
     * @param tmdbId 电视剧TMDB ID
     * @return 电视剧详情
     */
    @Tool(description = "getTVInfo")
    public String getTVInfo(
            @Parameter(description = "电视剧的TMDB ID") int tmdbId) {
        return nullbrApiService.getTVInfo(tmdbId);
    }
    
    /**
     * 获取电视剧季详情
     * @param tmdbId 电视剧TMDB ID
     * @param seasonNumber 季号
     * @return 季详情
     */
    @Tool(description = "getTVSeasonInfo")
    public String getTVSeasonInfo(
            @Parameter(description = "电视剧的TMDB ID") int tmdbId,
            @Parameter(description = "季号") int seasonNumber) {
        return nullbrApiService.getTVSeasonInfo(tmdbId, seasonNumber);
    }
    
    /**
     * 获取电视剧集详情
     * @param tmdbId 电视剧TMDB ID
     * @param seasonNumber 季号
     * @param episodeNumber 集号
     * @return 集详情
     */
    @Tool(description = "getTVEpisodeInfo")
    public String getTVEpisodeInfo(
            @Parameter(description = "电视剧的TMDB ID") int tmdbId,
            @Parameter(description = "季号") int seasonNumber,
            @Parameter(description = "集号") int episodeNumber) {
        return nullbrApiService.getTVEpisodeInfo(tmdbId, seasonNumber, episodeNumber);
    }
    
    /**
     * 获取电视剧季资源
     * @param tmdbId 电视剧TMDB ID
     * @param seasonNumber 季号
     * @param resourceType 资源类型
     * @return 资源信息
     */
    @Tool(description = "getTVSeasonResources")
    public String getTVSeasonResources(
            @Parameter(description = "电视剧的TMDB ID") int tmdbId,
            @Parameter(description = "季号") int seasonNumber,
            @Parameter(description = "资源类型，可选值：magnet(磁力)") String resourceType) {
        return nullbrApiService.getTVSeasonResources(tmdbId, seasonNumber, resourceType);
    }
    
    /**
     * 获取电视剧集资源
     * @param tmdbId 电视剧TMDB ID
     * @param seasonNumber 季号
     * @param episodeNumber 集号
     * @param resourceType 资源类型
     * @return 资源信息
     */
    @Tool(description = "getTVEpisodeResources")
    public String getTVEpisodeResources(
            @Parameter(description = "电视剧的TMDB ID") int tmdbId,
            @Parameter(description = "季号") int seasonNumber,
            @Parameter(description = "集号") int episodeNumber,
            @Parameter(description = "资源类型，可选值：magnet(磁力)、ed2k(电驴)、video(在线播放)") String resourceType) {
        return nullbrApiService.getTVEpisodeResources(tmdbId, seasonNumber, episodeNumber, resourceType);
    }
} 