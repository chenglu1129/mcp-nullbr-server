package com.nullbr.mcp;

import com.nullbr.mcp.service.NullbrApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StdioFunctionTest {

    @Autowired
    private NullbrApiService nullbrApiService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void testSearchMedia_OutputFormat() {
        // 模拟API响应
        String mockResponse = "{\"items\": [{\"title\": \"Test Movie\", \"media_type\": \"movie\", \"tmdbid\": 123, \"release_date\": \"2023-01-01\"}]}";
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));

        String result = nullbrApiService.searchMedia("test", 1);

        // 验证输出包含关键信息
        assertTrue(result.contains("Test Movie"));
        assertTrue(result.contains("movie"));
        assertTrue(result.contains("123"));
    }

    @Test
    public void testGetMovieInfo_ErrorHandling() {
        // 模拟API错误
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenThrow(new RuntimeException("API Error"));

        String result = nullbrApiService.getMovieInfo(123);

        // 验证错误处理
        assertTrue(result.contains("获取电影详情出错"));
        assertTrue(result.contains("API Error"));
    }

    @Test
    public void testInputValidation() {
        // 测试空输入或其他边界情况（取决于Service层的实现，这里假设Service层会传递给API）
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(new ResponseEntity<>("{}", HttpStatus.OK));
        
        // 只是验证调用不会抛出未捕获的异常
        String result = nullbrApiService.searchMedia("", 1);
        assertTrue(result != null);
    }
}
