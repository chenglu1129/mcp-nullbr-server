package com.nullbr.mcp.config;

import com.nullbr.mcp.service.CMSService;
import com.nullbr.mcp.service.NullbrApiService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolCallbackProviderRegister {

    @Bean
    public ToolCallbackProvider nullbrTools(NullbrApiService nUllbrApiService) {
        return MethodToolCallbackProvider.builder().toolObjects(nUllbrApiService).build();
    }

    @Bean
    public ToolCallbackProvider cmsTools(CMSService cmsService) {
        return MethodToolCallbackProvider.builder().toolObjects(cmsService).build();
    }
}
