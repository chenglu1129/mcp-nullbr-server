package com.nullbr.mcp.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nullbr.mcp.service.NullbrApiService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class NullbrApiServiceImpl implements NullbrApiService {

    @Value("${nullbr.api.base-url}")
    private String baseUrl;
    
    @Value("${nullbr.api.app-id}")
    private String appId;
    
    @Value("${nullbr.api.api-key}")
    private String apiKey;
    
    private final RestTemplate restTemplate;
    
    public NullbrApiServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    
    @Override
    @Tool(description = "searchMedia")
    public String searchMedia(
            @Parameter(description = "搜索关键词") String query,
            @Parameter(description = "页码，默认为1") int page) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/search")
                .queryParam("query", query)
                .queryParam("page", page);
                
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-APP-ID", appId);
            
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class
            );
            
            return formatSearchResults(response.getBody());
        } catch (Exception e) {
            return "搜索出错: " + e.getMessage();
        }
    }
    
    @Override
    @Tool(description = "getMovieInfo")
    public String getMovieInfo(
            @Parameter(description = "电影的TMDB ID") int tmdbId) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/movie/" + tmdbId);
                
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-APP-ID", appId);
            
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class
            );
            
            return formatMovieInfo(response.getBody());
        } catch (Exception e) {
            return "获取电影信息出错: " + e.getMessage();
        }
    }
    
    @Override
    @Tool(description = "getTVInfo")
    public String getTVInfo(
            @Parameter(description = "电视剧的TMDB ID") int tmdbId) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/tv/" + tmdbId);
                
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-APP-ID", appId);
            
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class
            );
            
            return formatTVInfo(response.getBody());
        } catch (Exception e) {
            return "获取电视剧信息出错: " + e.getMessage();
        }
    }
    
    @Override
    @Tool(description = "getTVSeasonInfo")
    public String getTVSeasonInfo(
            @Parameter(description = "电视剧的TMDB ID") int tmdbId,
            @Parameter(description = "季号") int seasonNumber) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
                baseUrl + "/tv/" + tmdbId + "/season/" + seasonNumber);
                
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-APP-ID", appId);
            
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class
            );
            
            return formatTVSeasonInfo(response.getBody());
        } catch (Exception e) {
            return "获取电视剧季度信息出错: " + e.getMessage();
        }
    }
    
    @Override
    @Tool(description = "getTVEpisodeInfo")
    public String getTVEpisodeInfo(
            @Parameter(description = "电视剧的TMDB ID") int tmdbId,
            @Parameter(description = "季号") int seasonNumber,
            @Parameter(description = "集号") int episodeNumber) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
                baseUrl + "/tv/" + tmdbId + "/season/" + seasonNumber + "/episode/" + episodeNumber);
                
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-APP-ID", appId);
            
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class
            );
            
            return formatTVEpisodeInfo(response.getBody());
        } catch (Exception e) {
            return "获取电视剧集信息出错: " + e.getMessage();
        }
    }
    
    @Override
    @Tool(description = "getMovieResources")
    public String getMovieResources(
            @Parameter(description = "电影的TMDB ID") int tmdbId,
            @Parameter(description = "资源类型，可选值：115(网盘)、magnet(磁力)、ed2k(电驴)、video(在线播放)") String resourceType) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
                baseUrl + "/movie/" + tmdbId + "/" + resourceType);
                
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-APP-ID", appId);
            headers.set("X-API-KEY", apiKey);
            
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class
            );
            
            return formatMovieResources(response.getBody(), resourceType);
        } catch (Exception e) {
            return "获取电影资源出错: " + e.getMessage();
        }
    }
    
    @Override
    @Tool(description = "getTVSeasonResources")
    public String getTVSeasonResources(
            @Parameter(description = "电视剧的TMDB ID") int tmdbId,
            @Parameter(description = "季号") int seasonNumber,
            @Parameter(description = "资源类型，可选值：magnet(磁力)") String resourceType) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
                baseUrl + "/tv/" + tmdbId + "/season/" + seasonNumber + "/" + resourceType);
                
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-APP-ID", appId);
            headers.set("X-API-KEY", apiKey);
            
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class
            );
            
            return formatTVSeasonResources(response.getBody(), resourceType);
        } catch (Exception e) {
            return "获取电视剧季度资源出错: " + e.getMessage();
        }
    }
    
    @Override
    @Tool(description = "getTVEpisodeResources")
    public String getTVEpisodeResources(
            @Parameter(description = "电视剧的TMDB ID") int tmdbId,
            @Parameter(description = "季号") int seasonNumber,
            @Parameter(description = "集号") int episodeNumber,
            @Parameter(description = "资源类型，可选值：magnet(磁力)、ed2k(电驴)、video(在线播放)") String resourceType) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
                baseUrl + "/tv/" + tmdbId + "/season/" + seasonNumber + "/episode/" + episodeNumber + "/" + resourceType);
                
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-APP-ID", appId);
            headers.set("X-API-KEY", apiKey);
            
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class
            );
            
            return formatTVEpisodeResources(response.getBody(), resourceType);
        } catch (Exception e) {
            return "获取电视剧集资源出错: " + e.getMessage();
        }
    }
    
    @Override
    @Tool(description = "getList")
    public String getList(
            @Parameter(description = "列表ID") int listId,
            @Parameter(description = "页码，默认为1") int page) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/list/" + listId)
                .queryParam("page", page);
                
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-APP-ID", appId);
            
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class
            );
            
            return formatListResults(response.getBody());
        } catch (Exception e) {
            return "获取列表出错: " + e.getMessage();
        }
    }

    @Override
    @Tool(description = "getPersonInfo")
    public String getPersonInfo(
            @Parameter(description = "人物的TMDB ID") int tmdbId,
            @Parameter(description = "页码，默认为1") int page) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/person/" + tmdbId)
                .queryParam("page", page);
                
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-APP-ID", appId);
            
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class
            );
            
            return formatPersonInfo(response.getBody());
        } catch (Exception e) {
            return "获取人物信息出错: " + e.getMessage();
        }
    }

    @Override
    @Tool(description = "getCollectionInfo")
    public String getCollectionInfo(
            @Parameter(description = "合集的TMDB ID") int tmdbId) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/collection/" + tmdbId);
                
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-APP-ID", appId);
            
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class
            );
            
            return formatCollectionInfo(response.getBody());
        } catch (Exception e) {
            return "获取合集信息出错: " + e.getMessage();
        }
    }

    @Override
    @Tool(description = "getTVResources")
    public String getTVResources(
            @Parameter(description = "电视剧的TMDB ID") int tmdbId) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
                baseUrl + "/tv/" + tmdbId + "/115");
                
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-APP-ID", appId);
            headers.set("X-API-KEY", apiKey);
            
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class
            );
            
            return formatTVResources(response.getBody());
        } catch (Exception e) {
            return "获取电视剧网盘资源出错: " + e.getMessage();
        }
    }

    @Override
    @Tool(description = "getPersonResources")
    public String getPersonResources(
            @Parameter(description = "人物的TMDB ID") int tmdbId) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
                baseUrl + "/person/" + tmdbId + "/115");
                
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-APP-ID", appId);
            headers.set("X-API-KEY", apiKey);
            
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class
            );
            
            return formatPersonResources(response.getBody());
        } catch (Exception e) {
            return "获取人物网盘资源出错: " + e.getMessage();
        }
    }

    @Override
    @Tool(description = "getCollectionResources")
    public String getCollectionResources(
            @Parameter(description = "合集的TMDB ID") int tmdbId) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
                baseUrl + "/collection/" + tmdbId + "/115");
                
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-APP-ID", appId);
            headers.set("X-API-KEY", apiKey);
            
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class
            );
            
            return formatCollectionResources(response.getBody());
        } catch (Exception e) {
            return "获取合集网盘资源出错: " + e.getMessage();
        }
    }
    
    private String formatSearchResults(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode items = rootNode.get("items");
            
            if (items == null || items.isEmpty()) {
                return "未找到相关结果。";
            }
            
            StringBuilder result = new StringBuilder();
            result.append("找到以下结果:\n\n");
            
            for (JsonNode item : items) {
                String mediaType = item.get("media_type").asText();
                String title = item.get("title").asText();
                int tmdbId = item.get("tmdbid").asInt();
                
                result.append("- ").append(title)
                      .append(" (").append(mediaType).append(", ID: ").append(tmdbId).append(")\n");
                
                if (item.has("overview") && !item.get("overview").isNull()) {
                    String overview = item.get("overview").asText();
                    if (overview.length() > 100) {
                        overview = overview.substring(0, 100) + "...";
                    }
                    result.append("  简介: ").append(overview).append("\n");
                }
                
                result.append("\n");
            }
            
            return result.toString();
        } catch (Exception e) {
            return "解析搜索结果出错: " + e.getMessage();
        }
    }
    
    private String formatMovieInfo(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode movie = objectMapper.readTree(jsonResponse);
            
            StringBuilder result = new StringBuilder();
            result.append("电影信息:\n\n");
            
            result.append("标题: ").append(movie.get("title").asText()).append("\n");
            
            if (movie.has("overview") && !movie.get("overview").isNull()) {
                result.append("简介: ").append(movie.get("overview").asText()).append("\n");
            }
            
            if (movie.has("release_date") && !movie.get("release_date").isNull()) {
                result.append("发布日期: ").append(movie.get("release_date").asText()).append("\n");
            }
            
            if (movie.has("vote") && !movie.get("vote").isNull()) {
                result.append("评分: ").append(movie.get("vote").asText()).append("\n");
            }
            
            return result.toString();
        } catch (Exception e) {
            return "解析电影信息出错: " + e.getMessage();
        }
    }
    
    private String formatTVInfo(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode tv = objectMapper.readTree(jsonResponse);
            
            StringBuilder result = new StringBuilder();
            result.append("电视剧信息:\n\n");
            
            result.append("标题: ").append(tv.get("title").asText()).append("\n");
            
            if (tv.has("overview") && !tv.get("overview").isNull()) {
                result.append("简介: ").append(tv.get("overview").asText()).append("\n");
            }
            
            if (tv.has("release_date") && !tv.get("release_date").isNull()) {
                result.append("首播日期: ").append(tv.get("release_date").asText()).append("\n");
            }
            
            if (tv.has("vote") && !tv.get("vote").isNull()) {
                result.append("评分: ").append(tv.get("vote").asText()).append("\n");
            }
            
            if (tv.has("number_of_seasons") && !tv.get("number_of_seasons").isNull()) {
                result.append("季数: ").append(tv.get("number_of_seasons").asText()).append("\n");
            }
            
            return result.toString();
        } catch (Exception e) {
            return "解析电视剧信息出错: " + e.getMessage();
        }
    }
    
    private String formatMovieResources(String jsonResponse, String resourceType) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            
            StringBuilder result = new StringBuilder();
            result.append("电影资源 (").append(resourceType).append("):\n\n");
            
            if (resourceType.equals("115")) {
                JsonNode resources = rootNode.get("115");
                if (resources != null && !resources.isEmpty()) {
                    for (JsonNode resource : resources) {
                        result.append("标题: ").append(resource.get("title").asText()).append("\n");
                        result.append("大小: ").append(resource.get("size").asText()).append("\n");
                        result.append("链接: ").append(resource.get("share_link").asText()).append("\n\n");
                    }
                } else {
                    result.append("没有找到网盘资源。");
                }
            } else if (resourceType.equals("magnet")) {
                JsonNode resources = rootNode.get("magnet");
                if (resources != null && !resources.isEmpty()) {
                    for (JsonNode resource : resources) {
                        result.append("名称: ").append(resource.get("name").asText()).append("\n");
                        result.append("大小: ").append(resource.get("size").asText()).append("\n");
                        result.append("链接: ").append(resource.get("magnet").asText()).append("\n\n");
                    }
                } else {
                    result.append("没有找到磁力资源。");
                }
            } else {
                // 其他资源类型
                result.append(jsonResponse);
            }
            
            return result.toString();
        } catch (Exception e) {
            return "解析资源信息出错: " + e.getMessage();
        }
    }
    
    private String formatTVSeasonInfo(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode season = objectMapper.readTree(jsonResponse);
            
            StringBuilder result = new StringBuilder();
            result.append("电视剧季度信息:\n\n");
            
            if (season.has("name") && !season.get("name").isNull()) {
                result.append("名称: ").append(season.get("name").asText()).append("\n");
            }
            
            if (season.has("overview") && !season.get("overview").isNull()) {
                result.append("简介: ").append(season.get("overview").asText()).append("\n");
            }
            
            if (season.has("air_date") && !season.get("air_date").isNull()) {
                result.append("首播日期: ").append(season.get("air_date").asText()).append("\n");
            }
            
            if (season.has("episode_count") && !season.get("episode_count").isNull()) {
                result.append("集数: ").append(season.get("episode_count").asText()).append("\n");
            }
            
            if (season.has("vote_average") && !season.get("vote_average").isNull()) {
                result.append("评分: ").append(season.get("vote_average").asText()).append("\n");
            }
            
            return result.toString();
        } catch (Exception e) {
            return "解析电视剧季度信息出错: " + e.getMessage();
        }
    }
    
    private String formatTVEpisodeInfo(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode episode = objectMapper.readTree(jsonResponse);
            
            StringBuilder result = new StringBuilder();
            result.append("电视剧集信息:\n\n");
            
            if (episode.has("name") && !episode.get("name").isNull()) {
                result.append("标题: ").append(episode.get("name").asText()).append("\n");
            }
            
            if (episode.has("overview") && !episode.get("overview").isNull()) {
                result.append("简介: ").append(episode.get("overview").asText()).append("\n");
            }
            
            if (episode.has("air_date") && !episode.get("air_date").isNull()) {
                result.append("播出日期: ").append(episode.get("air_date").asText()).append("\n");
            }
            
            if (episode.has("vote_average") && !episode.get("vote_average").isNull()) {
                result.append("评分: ").append(episode.get("vote_average").asText()).append("\n");
            }
            
            if (episode.has("runtime") && !episode.get("runtime").isNull()) {
                result.append("片长: ").append(episode.get("runtime").asText()).append(" 分钟\n");
            }
            
            return result.toString();
        } catch (Exception e) {
            return "解析电视剧集信息出错: " + e.getMessage();
        }
    }
    
    private String formatTVSeasonResources(String jsonResponse, String resourceType) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            
            StringBuilder result = new StringBuilder();
            result.append("电视剧季度资源 (").append(resourceType).append("):\n\n");
            
            if (resourceType.equals("magnet")) {
                JsonNode resources = rootNode.get("magnet");
                if (resources != null && !resources.isEmpty()) {
                    for (JsonNode resource : resources) {
                        result.append("名称: ").append(resource.get("name").asText()).append("\n");
                        result.append("大小: ").append(resource.get("size").asText()).append("\n");
                        result.append("链接: ").append(resource.get("magnet").asText()).append("\n\n");
                    }
                } else {
                    result.append("没有找到磁力资源。");
                }
            } else {
                // 其他资源类型
                result.append(jsonResponse);
            }
            
            return result.toString();
        } catch (Exception e) {
            return "解析电视剧季度资源信息出错: " + e.getMessage();
        }
    }
    
    private String formatTVEpisodeResources(String jsonResponse, String resourceType) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            
            StringBuilder result = new StringBuilder();
            result.append("电视剧集资源 (").append(resourceType).append("):\n\n");
            
            if (resourceType.equals("magnet")) {
                JsonNode resources = rootNode.get("magnet");
                if (resources != null && !resources.isEmpty()) {
                    for (JsonNode resource : resources) {
                        result.append("名称: ").append(resource.get("name").asText()).append("\n");
                        result.append("大小: ").append(resource.get("size").asText()).append("\n");
                        result.append("链接: ").append(resource.get("magnet").asText()).append("\n\n");
                    }
                } else {
                    result.append("没有找到磁力资源。");
                }
            } else if (resourceType.equals("ed2k")) {
                JsonNode resources = rootNode.get("ed2k");
                if (resources != null && !resources.isEmpty()) {
                    for (JsonNode resource : resources) {
                        result.append("名称: ").append(resource.get("name").asText()).append("\n");
                        result.append("大小: ").append(resource.get("size").asText()).append("\n");
                        result.append("链接: ").append(resource.get("ed2k").asText()).append("\n\n");
                    }
                } else {
                    result.append("没有找到电驴资源。");
                }
            } else if (resourceType.equals("video")) {
                JsonNode resources = rootNode.get("video");
                if (resources != null && !resources.isEmpty()) {
                    for (JsonNode resource : resources) {
                        result.append("名称: ").append(resource.get("name").asText()).append("\n");
                        result.append("类型: ").append(resource.get("type").asText()).append("\n");
                        result.append("链接: ").append(resource.get("link").asText()).append("\n\n");
                    }
                } else {
                    result.append("没有找到在线播放资源。");
                }
            } else {
                // 其他资源类型
                result.append(jsonResponse);
            }
            
            return result.toString();
        } catch (Exception e) {
            return "解析电视剧集资源信息出错: " + e.getMessage();
        }
    }

    private String formatListResults(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            
            StringBuilder result = new StringBuilder();
            result.append("列表信息:\n\n");
            
            if (rootNode.has("name") && !rootNode.get("name").isNull()) {
                result.append("名称: ").append(rootNode.get("name").asText()).append("\n");
            }
            
            if (rootNode.has("description") && !rootNode.get("description").isNull()) {
                result.append("描述: ").append(rootNode.get("description").asText()).append("\n");
            }
            
            if (rootNode.has("total_items") && !rootNode.get("total_items").isNull()) {
                result.append("总项目数: ").append(rootNode.get("total_items").asText()).append("\n");
            }
            
            result.append("\n项目列表:\n\n");
            
            JsonNode items = rootNode.get("items");
            if (items != null && !items.isEmpty()) {
                for (JsonNode item : items) {
                    String title = item.get("title").asText();
                    String mediaType = item.get("media_type").asText();
                    int tmdbId = item.get("tmdbid").asInt();
                    
                    result.append("- ").append(title)
                          .append(" (").append(mediaType).append(", ID: ").append(tmdbId).append(")\n");
                    
                    if (item.has("overview") && !item.get("overview").isNull()) {
                        String overview = item.get("overview").asText();
                        if (overview.length() > 100) {
                            overview = overview.substring(0, 100) + "...";
                        }
                        result.append("  简介: ").append(overview).append("\n");
                    }
                    
                    result.append("\n");
                }
            } else {
                result.append("没有找到项目。");
            }
            
            return result.toString();
        } catch (Exception e) {
            return "解析列表结果出错: " + e.getMessage();
        }
    }

    private String formatPersonInfo(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode person = objectMapper.readTree(jsonResponse);
            
            StringBuilder result = new StringBuilder();
            result.append("人物信息:\n\n");
            
            if (person.has("name") && !person.get("name").isNull()) {
                result.append("姓名: ").append(person.get("name").asText()).append("\n");
            }
            
            if (person.has("overview") && !person.get("overview").isNull()) {
                result.append("简介: ").append(person.get("overview").asText()).append("\n");
            }
            
            result.append("\n作品列表:\n\n");
            
            JsonNode items = person.get("items");
            if (items != null && !items.isEmpty()) {
                for (JsonNode item : items) {
                    String title = item.get("title").asText();
                    String mediaType = item.get("media_type").asText();
                    int tmdbId = item.get("tmdbid").asInt();
                    
                    result.append("- ").append(title)
                          .append(" (").append(mediaType).append(", ID: ").append(tmdbId).append(")\n");
                    
                    if (item.has("release_date") && !item.get("release_date").isNull()) {
                        result.append("  发布日期: ").append(item.get("release_date").asText()).append("\n");
                    }
                    
                    result.append("\n");
                }
            } else {
                result.append("没有找到作品。");
            }
            
            return result.toString();
        } catch (Exception e) {
            return "解析人物信息出错: " + e.getMessage();
        }
    }

    private String formatCollectionInfo(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode collection = objectMapper.readTree(jsonResponse);
            
            StringBuilder result = new StringBuilder();
            result.append("合集信息:\n\n");
            
            if (collection.has("title") && !collection.get("title").isNull()) {
                result.append("标题: ").append(collection.get("title").asText()).append("\n");
            }
            
            if (collection.has("overview") && !collection.get("overview").isNull()) {
                result.append("简介: ").append(collection.get("overview").asText()).append("\n");
            }
            
            result.append("\n电影列表:\n\n");
            
            JsonNode items = collection.get("items");
            if (items != null && !items.isEmpty()) {
                for (JsonNode item : items) {
                    String title = item.get("title").asText();
                    int tmdbId = item.get("tmdbid").asInt();
                    
                    result.append("- ").append(title)
                          .append(" (ID: ").append(tmdbId).append(")\n");
                    
                    if (item.has("release_date") && !item.get("release_date").isNull()) {
                        result.append("  发布日期: ").append(item.get("release_date").asText()).append("\n");
                    }
                    
                    result.append("\n");
                }
            } else {
                result.append("没有找到电影。");
            }
            
            return result.toString();
        } catch (Exception e) {
            return "解析合集信息出错: " + e.getMessage();
        }
    }

    private String formatTVResources(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            
            StringBuilder result = new StringBuilder();
            result.append("电视剧网盘资源:\n\n");
            
            JsonNode resources = rootNode.get("115");
            if (resources != null && !resources.isEmpty()) {
                for (JsonNode resource : resources) {
                    result.append("标题: ").append(resource.get("title").asText()).append("\n");
                    result.append("大小: ").append(resource.get("size").asText()).append("\n");
                    result.append("链接: ").append(resource.get("share_link").asText()).append("\n\n");
                }
            } else {
                result.append("没有找到网盘资源。");
            }
            
            return result.toString();
        } catch (Exception e) {
            return "解析电视剧网盘资源信息出错: " + e.getMessage();
        }
    }

    private String formatPersonResources(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            
            StringBuilder result = new StringBuilder();
            result.append("人物网盘资源:\n\n");
            
            JsonNode resources = rootNode.get("115");
            if (resources != null && !resources.isEmpty()) {
                for (JsonNode resource : resources) {
                    result.append("标题: ").append(resource.get("title").asText()).append("\n");
                    result.append("大小: ").append(resource.get("size").asText()).append("\n");
                    result.append("链接: ").append(resource.get("share_link").asText()).append("\n\n");
                }
            } else {
                result.append("没有找到网盘资源。");
            }
            
            return result.toString();
        } catch (Exception e) {
            return "解析人物网盘资源信息出错: " + e.getMessage();
        }
    }

    private String formatCollectionResources(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            
            StringBuilder result = new StringBuilder();
            result.append("合集网盘资源:\n\n");
            
            JsonNode resources = rootNode.get("115");
            if (resources != null && !resources.isEmpty()) {
                for (JsonNode resource : resources) {
                    result.append("标题: ").append(resource.get("title").asText()).append("\n");
                    result.append("大小: ").append(resource.get("size").asText()).append("\n");
                    result.append("链接: ").append(resource.get("share_link").asText()).append("\n\n");
                }
            } else {
                result.append("没有找到网盘资源。");
            }
            
            return result.toString();
        } catch (Exception e) {
            return "解析合集网盘资源信息出错: " + e.getMessage();
        }
    }
}
