package com.nullbr.mcp;

import com.nullbr.mcp.service.NullbrApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NullbrMcpServerApplication {

    private static final Logger logger = LoggerFactory.getLogger(NullbrMcpServerApplication.class);

    public static void main(String[] args) {
        logger.info("正在启动NullBR MCP服务器...");
        ConfigurableApplicationContext context = SpringApplication.run(NullbrMcpServerApplication.class, args);
        logger.info("NullBR MCP服务器启动成功");
    }
    

    @Bean
    public ToolCallbackProvider serverTools(NullbrApiService nullbrApiService) {
        logger.info("注册NullBR API服务工具");
        return MethodToolCallbackProvider.builder()
                .toolObjects(nullbrApiService)
                .build();
    }
} 