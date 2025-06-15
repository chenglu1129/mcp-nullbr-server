package com.nullbr.mcp.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public interface CmsAPiService {
    /**
     * 添加115分享链接到CMS系统进行转存
     *
     * @param url 115分享链接
     * @return API响应数据
     */
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
            log.error("添加分享链接失败", e);
            throw new RuntimeException("添加分享链接失败", e);
        }
    }
}
