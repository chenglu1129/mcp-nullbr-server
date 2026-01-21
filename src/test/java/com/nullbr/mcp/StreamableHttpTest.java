package com.nullbr.mcp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StreamableHttpTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMcpEndpointExists() throws Exception {
        // 验证MCP端点是否已暴露并接受连接
        // Streamable HTTP 通常支持 GET (用于 SSE 连接) 和 POST (用于发送消息)
        
        // 测试 SSE 连接端点
        mockMvc.perform(get("/mcp")
                .accept(MediaType.TEXT_EVENT_STREAM))
                .andExpect(status().isOk());
                // 注意：在没有真实客户端连接和握手的情况下，可能只会返回 200 OK 并保持连接
    }
}
