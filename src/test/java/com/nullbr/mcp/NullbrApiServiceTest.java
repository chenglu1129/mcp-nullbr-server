package com.nullbr.mcp;

import com.nullbr.mcp.service.NullbrApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NullbrApiServiceTest {

    @Autowired
    private NullbrApiService nullbrApiService;
    
    @Test
    public void testSearchMedia() {
        String result = nullbrApiService.searchMedia("钢铁侠", 1);
        System.out.println("搜索结果：");
        System.out.println(result);
    }
    
    @Test
    public void testGetList() {
        // 使用一个有效的列表ID，例如热门电影列表
        String result = nullbrApiService.getList(1, 1);
        System.out.println("列表信息：");
        System.out.println(result);
    }
    
    @Test
    public void testGetMovieInfo() {
        String result = nullbrApiService.getMovieInfo(1726); // 钢铁侠的TMDB ID
        System.out.println("电影信息：");
        System.out.println(result);
    }
    
    @Test
    public void testGetTVInfo() {
        String result = nullbrApiService.getTVInfo(1399); // 权力的游戏的TMDB ID
        System.out.println("电视剧信息：");
        System.out.println(result);
    }
    
    @Test
    public void testGetTVSeasonInfo() {
        String result = nullbrApiService.getTVSeasonInfo(1399, 1); // 权力的游戏第一季
        System.out.println("电视剧季度信息：");
        System.out.println(result);
    }
    
    @Test
    public void testGetTVEpisodeInfo() {
        String result = nullbrApiService.getTVEpisodeInfo(1399, 1, 1); // 权力的游戏第一季第一集
        System.out.println("电视剧集信息：");
        System.out.println(result);
    }
    
    @Test
    public void testGetPersonInfo() {
        String result = nullbrApiService.getPersonInfo(3223, 1); // 小罗伯特·唐尼的TMDB ID
        System.out.println("人物信息：");
        System.out.println(result);
    }
    
    @Test
    public void testGetCollectionInfo() {
        String result = nullbrApiService.getCollectionInfo(86311); // 漫威电影宇宙合集的TMDB ID
        System.out.println("合集信息：");
        System.out.println(result);
    }
    
    @Test
    public void testGetMovieResources() {
        String result = nullbrApiService.getMovieResources(1726, "magnet"); // 钢铁侠的磁力资源
        System.out.println("电影磁力资源：");
        System.out.println(result);
    }
    
    @Test
    public void testGetMovieResourcesVideo() {
        String result = nullbrApiService.getMovieResources(1726, "video"); // 钢铁侠的在线播放资源
        System.out.println("电影在线播放资源：");
        System.out.println(result);
    }
    
    @Test
    public void testGetTVResources() {
        String result = nullbrApiService.getTVResources(1399); // 权力的游戏的网盘资源
        System.out.println("电视剧网盘资源：");
        System.out.println(result);
    }
    
    @Test
    public void testGetTVSeasonResources() {
        String result = nullbrApiService.getTVSeasonResources(1399, 1, "magnet"); // 权力的游戏第一季的磁力资源
        System.out.println("电视剧季度磁力资源：");
        System.out.println(result);
    }
    
    @Test
    public void testGetTVEpisodeResources() {
        String result = nullbrApiService.getTVEpisodeResources(1399, 1, 1, "magnet"); // 权力的游戏第一季第一集的磁力资源
        System.out.println("电视剧集磁力资源：");
        System.out.println(result);
    }
    
    @Test
    public void testGetTVEpisodeResourcesVideo() {
        String result = nullbrApiService.getTVEpisodeResources(1399, 1, 1, "video"); // 权力的游戏第一季第一集的在线播放资源
        System.out.println("电视剧集在线播放资源：");
        System.out.println(result);
    }
    
    @Test
    public void testGetPersonResources() {
        String result = nullbrApiService.getPersonResources(3223); // 小罗伯特·唐尼的网盘资源
        System.out.println("人物网盘资源：");
        System.out.println(result);
    }
    
    @Test
    public void testGetCollectionResources() {
        String result = nullbrApiService.getCollectionResources(86311); // 漫威电影宇宙合集的网盘资源
        System.out.println("合集网盘资源：");
        System.out.println(result);
    }
}