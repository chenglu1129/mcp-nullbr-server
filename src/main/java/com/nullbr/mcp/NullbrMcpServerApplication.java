package com.nullbr.mcp;

import com.nullbr.mcp.service.NullbrApiService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NullbrMcpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NullbrMcpServerApplication.class, args);
    }
    

    @Bean
    public ToolCallbackProvider serverTools(NullbrApiService nullbrApiService) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(nullbrApiService)
                .build();
    }
} 