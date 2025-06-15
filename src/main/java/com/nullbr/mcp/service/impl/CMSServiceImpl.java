package com.nullbr.mcp.service.impl;

import com.nullbr.mcp.service.CMSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * CMS服务类，用于与CMS系统交互
 */
@Service
//@RequiredArgsConstructor
//@Slf4j
public class CMSServiceImpl implements CMSService {

    private static final Logger logger = LoggerFactory.getLogger(CMSServiceImpl.class);

    @Value("${cms.base-url}")
    private String baseUrl;

    @Value("${cms.username}")
    private String username;

    @Value("${cms.password}")
    private String password;

    private final RestTemplate restTemplate;
    public CMSServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    private String token;
    private long tokenExpiry;

    /**
     * 登录CMS系统获取token
     *
     * @return 登录响应数据
     */
    private Map<String, Object> login() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("username", username);
            requestBody.put("password", password);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(
                    baseUrl + "/api/auth/login",
                    request,
                    Map.class
            );

            Map<String, Object> responseBody = response.getBody();

            if (responseBody != null && responseBody.get("code").equals(200) && responseBody.containsKey("data")) {
                return (Map<String, Object>) responseBody.get("data");
            }

            throw new RuntimeException("登录失败: " + responseBody);

        } catch (Exception e) {
            logger.error("登录CMS系统失败", e);
            throw new RuntimeException("登录CMS系统失败", e);
        }
    }

    /**
     * 添加115分享链接到CMS系统进行转存
     *
     * @param url 115分享链接
     * @return API响应数据
     */
    @Override
    @Tool(description = "将获取的115分享链接添加到CMS系统进行转存")
    public Map<String, Object> addShareDown(String url) {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("url不能为空");
        }

        ensureValidToken();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);

            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("url", url);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(
                    baseUrl + "/api/cloud/add_share_down",
                    request,
                    Map.class
            );

            return response.getBody();

        } catch (Exception e) {
            // 如果是401错误，可能是token过期，尝试重新获取token
            logger.error("添加分享链接失败", e);
            throw new RuntimeException("添加分享链接失败", e);
        }
    }

    /**
     * 确保有效的token，如果token过期或不存在则重新获取
     */
    private void ensureValidToken() {
        long currentTime = System.currentTimeMillis() / 1000;

        // 如果token不存在或距离过期时间不到24小时，重新获取token
        if (token == null || currentTime >= (tokenExpiry - 60*60*24)) {
            Map<String, Object> loginData = login();
            token = (String) loginData.get("token");

            // 从token中提取过期时间，如果无法提取则设置为24小时后
            try {
                // 实际项目中应该解析JWT token获取过期时间
                // 这里简化处理，设置为24小时后过期
                tokenExpiry = currentTime + 86400;
            } catch (Exception e) {
                logger.warn("解析token过期时间失败", e);
                tokenExpiry = currentTime + 86400;
            }
        }
    }
}
