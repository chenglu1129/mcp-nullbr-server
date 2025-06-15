package com.nullbr.mcp.tools;

import com.nullbr.mcp.service.NullbrApiService;
import org.springframework.ai.tool.annotation.Tool;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.stereotype.Component;

/**
 * 影视搜索工具
 */
@Component
public class SearchTool {
    
    private final NullbrApiService nullbrApiService;
    
    public SearchTool(NullbrApiService nullbrApiService) {
        this.nullbrApiService = nullbrApiService;
    }
    
    /**
     * 搜索影视资源
     * @param query 搜索关键词
     * @param page 页码，默认为1
     * @return 搜索结果
     */
    @Tool(description = "search")
    public String search(
            @Parameter(description = "搜索关键词") String query,
            @Parameter(description = "页码，默认为1") Integer page) {
        
        int pageNum = (page == null || page < 1) ? 1 : page;
        return nullbrApiService.searchMedia(query, pageNum);
    }
} 