package com.nullbr.mcp.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public interface CMSService {
    /**
     * 添加115分享链接到CMS系统进行转存
     *
     * @param url 115分享链接
     * @return API响应数据
     */
    @Tool(description = "将获取的115分享链接添加到CMS系统进行转存")
    public Map<String, Object> addShareDown(String url);
}
