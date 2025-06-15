package com.nullbr.mcp.tools;

import com.nullbr.mcp.service.NullbrApiService;
import org.springframework.ai.tool.annotation.Tool;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.stereotype.Component;

/**
 * 电影工具
 */
@Component
public class MovieTool {
    
    private final NullbrApiService nullbrApiService;
    
    public MovieTool(NullbrApiService nullbrApiService) {
        this.nullbrApiService = nullbrApiService;
    }
    
    /**
     * 获取电影详情
     * @param tmdbId 电影TMDB ID
     * @return 电影详情
     */
    @Tool(description = "getMovieInfo")
    public String getMovieInfo(
            @Parameter(description = "电影的TMDB ID") int tmdbId) {
        return nullbrApiService.getMovieInfo(tmdbId);
    }
    
    /**
     * 获取电影资源
     * @param tmdbId 电影TMDB ID
     * @param resourceType 资源类型
     * @return 资源信息
     */
    @Tool(description = "getMovieResources")
    public String getMovieResources(
            @Parameter(description = "电影的TMDB ID") int tmdbId,
            @Parameter(description = "资源类型，可选值：115(网盘)、magnet(磁力)、ed2k(电驴)、video(在线播放)") String resourceType) {
        return nullbrApiService.getMovieResources(tmdbId, resourceType);
    }
} 