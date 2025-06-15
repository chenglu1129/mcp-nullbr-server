# NullBR API 文档

## 基础信息

- 基础URL: https://api.nullbr.eu.org
- 鉴权方式: 
  - 元数据接口: 需要 X-APP-ID 请求头
  - 资源接口: 需要 X-APP-ID 和 X-API-KEY 请求头
  - X-APP-ID = pUNPgvXND
  - X-API-KEY = Ea8F3jR76suCbOqQpi5GAVEhcpbhiI94
## 接口概览

### 元数据接口 (META)
| 接口名称 | 请求方式 | 接口路径 | 描述 |
|---------|--------|---------|------|
| 影视搜索 | GET | /search | 关键字搜索，支持电影、剧集、合集、人物 |
| 获取列表 | GET | /list/{listid} | 获取影视列表详细信息 |
| 获取电影信息 | GET | /movie/{tmdbid} | 获取电影详细数据 |
| 获取剧集信息 | GET | /tv/{tmdbid} | 获取剧集详细数据 |
| 获取剧集单季信息 | GET | /tv/{tmdbid}/season/{season_number} | 获取剧集单季详细数据 |
| 获取剧集单集信息 | GET | /tv/{tmdbid}/season/{season_number}/episode/{episode_number} | 获取剧集单集详细数据 |
| 获取人物信息 | GET | /person/{tmdbid} | 获取人物详细数据 |
| 获取电影合集信息 | GET | /collection/{tmdbid} | 获取电影合集详细数据 |

### 资源接口 (RES)
| 接口名称 | 请求方式 | 接口路径 | 描述 |
|---------|--------|---------|------|
| 获取电影网盘资源 | GET | /movie/{tmdbid}/115 | 获取电影网盘资源 |
| 获取电影磁力资源 | GET | /movie/{tmdbid}/magnet | 获取电影磁力资源 |
| 获取电影ed2k资源 | GET | /movie/{tmdbid}/ed2k | 获取电影ed2k资源 |
| 获取电影m3u8资源 | GET | /movie/{tmdbid}/video | 获取电影m3u8资源 |
| 获取剧集网盘资源 | GET | /tv/{tmdbid}/115 | 获取剧集网盘资源 |
| 获取剧集季磁力资源 | GET | /tv/{tmdbid}/season/{season_number}/magnet | 获取剧集季磁力资源 |
| 获取剧集单集磁力资源 | GET | /tv/{tmdbid}/season/{season_number}/episode/{episode_number}/magnet | 获取剧集单集磁力资源 |
| 获取剧集单集ed2k资源 | GET | /tv/{tmdbid}/season/{season_number}/episode/{episode_number}/ed2k | 获取剧集单集ed2k资源 |
| 获取剧集单集m3u8资源 | GET | /tv/{tmdbid}/season/{season_number}/episode/{episode_number}/video | 获取剧集单集m3u8资源 |
| 获取人物网盘资源 | GET | /person/{tmdbid}/115 | 获取人物网盘合集资源 |
| 获取电影合集网盘资源 | GET | /collection/{tmdbid}/115 | 获取电影合集网盘资源 |

## 详细接口说明

### 1. 影视搜索 (/search)

**请求参数:**
- query (string, 必填): 查询关键字
- page (int, 可选): 页码，默认1

**响应示例:**
```json
{
  "page": 1,
  "total_pages": 1,
  "total_results": 24,
  "items": [
    {
      "media_type": "collection|movie|tv|person",
      "tmdbid": 1726,
      "poster": "/jOCNOwURzzkOBCpmJKg4Fay4F0L.jpg",
      "title": "钢铁侠",
      "overview": "电影简介...",
      "vote_average": 7.648,
      "release_date": "2008-04-30",
      "115-flg": 1,
      "magnet-flg": 1,
      "video-flg": 1,
      "ed2k-flg": 1
    }
  ]
}
```

### 2. 获取列表 (/list/{listid})

**请求参数:**
- listid (int, 必填): 列表ID
- page (int, 可选): 页码，默认1

**响应示例:**
```json
{
  "id": 20260771,
  "name": "北美情色电影",
  "description": "列表描述...",
  "updated_dt": "2025-03-24 08:57:08",
  "page": 1,
  "total_page": 19,
  "total_items": 554,
  "items": [
    {
      "rank": 1,
      "media_type": "tv|movie",
      "tmdbid": 1399,
      "poster": "/wr3myG67UJC6SGJaRYFwIYIm0Uu.jpg",
      "title": "权力的游戏",
      "overview": "剧集简介...",
      "vote_average": 8.5,
      "release_date": "2011-04-17",
      "115-flg": 1,
      "magnet-flg": 1,
      "video-flg": 1,
      "ed2k-flg": 1
    }
  ]
}
```

### 3. 获取电影信息 (/movie/{tmdbid})

**请求参数:**
- tmdbid (int, 必填): 电影TMDB ID

**响应示例:**
```json
{
  "id": 78,
  "poster": "/l8WEtBkq6h8i3Pqnu7hpRqU0Uko.jpg",
  "title": "银翼杀手",
  "overview": "电影简介...",
  "vote": 7.938,
  "release_date": "1982-06-25",
  "115-flg": 1,
  "magnet-flg": 1,
  "ed2k-flg": 1,
  "video-flg": 1
}
```

### 4. 获取电影资源

#### 4.1 网盘资源 (/movie/{tmdbid}/115)

**响应示例:**
```json
{
  "115": [
    {
      "title": "银翼杀手 (1982)",
      "size": "83.03 GB",
      "share_link": "https://115.com/s/swzxgu436gr?password=9527&#"
    }
  ],
  "id": 78,
  "page": 1,
  "total_page": 2,
  "media_type": "movie"
}
```

#### 4.2 磁力资源 (/movie/{tmdbid}/magnet)

**响应示例:**
```json
{
  "id": 78,
  "media_type": "movie",
  "magnet": [
    {
      "name": "银翼杀手[简繁英字幕].Blade.Runner.1982.2160p.UHD.BluRay.Remux.HEVC.HDR.TrueHD.7.1.Atmos",
      "size": "44.69 GB",
      "magnet": "magnet:?xt=urn:btih:8e1a89ec0df4267dcc42a70b58d43693f1bb774e",
      "resolution": "2160p",
      "source": "Ultra HD Blu-ray",
      "quality": ["Remux", "HDR10", "HD"],
      "zh_sub": 1
    }
  ]
}
```

#### 4.3 ed2k资源 (/movie/{tmdbid}/ed2k)

**响应格式与磁力资源类似**

#### 4.4 m3u8资源 (/movie/{tmdbid}/video)

**响应示例:**
```json
{
  "id": 78,
  "media_type": "movie",
  "video": [
    {
      "name": "1",
      "type": "m3u8",
      "link": "https://m3u8.heimuertv.com/play/82d683b472b04e0292b41d05a739f4e1.m3u8"
    }
  ]
}
```

### 5. 获取剧集信息 (/tv/{tmdbid})

**请求参数:**
- tmdbid (int, 必填): 剧集TMDB ID

**响应示例:**
```json
{
  "id": 1396,
  "poster": "/bSldznJEjkeojc5dzjLvlukqVEU.jpg",
  "title": "绝命毒师",
  "overview": "剧集简介...",
  "vote": 8.9,
  "release_date": "2008-01-20",
  "number_of_seasons": 5,
  "115-flg": 1,
  "magnet-flg": 1,
  "ed2k-flg": 1, 
  "video-flg": 1
}
```

### 6. 获取剧集单季信息 (/tv/{tmdbid}/season/{season_number})

**请求参数:**
- tmdbid (int, 必填): 剧集TMDB ID
- season_number (int, 必填): 季数

**响应示例:**
```json
{
  "tv_show_id": 1396,
  "season_number": 1,
  "name": "第 1 季",
  "overview": "季简介...",
  "air_date": "2008-01-20",
  "poseter": "/91EIXdWxmp2mHNRsSuDW3WyDJLj.jpg",
  "episode_count": 7,
  "vote_average": 8.3,
  "magnet-flg": 1
}
```

### 7. 获取剧集单集信息 (/tv/{tmdbid}/season/{season_number}/episode/{episode_number})

**请求参数:**
- tmdbid (int, 必填): 剧集TMDB ID
- season_number (int, 必填): 季数
- episode_number (int, 必填): 集数

**响应示例:**
```json
{
  "tv_show_id": 1396,
  "season_number": 1,
  "episode_number": 3,
  "episode_type": "standard",
  "name": "袋在河里",
  "overview": "集简介...",
  "air_date": "2008-02-10",
  "vote_average": 8.327,
  "poseter": "/dLgiPZCVamFcaa7Gaqudrldj15h.jpg",
  "runtime": 49,
  "magnet-flg": 1,
  "ed2k-flg": 1
}
```

### 8. 获取人物信息 (/person/{tmdbid})

**请求参数:**
- tmdbid (int, 必填): 人物TMDB ID
- page (int, 可选): 页码，默认1

**响应示例:**
```json
{
  "id": 57607,
  "name": "周星驰",
  "poster": "/j5DWs54BGZfp92G43c9OyHPvkNG.jpg",
  "overview": "人物简介...",
  "115-flg": 1,
  "page": 1,
  "total_page": 3,
  "total_items": 70,
  "items": [
    {
      "media_type": "tv",
      "tmdbid": 109723,
      "poster": "/5MNCqzpcEbZiNbYEt5MlKGx5dpw.jpg",
      "title": "香城浪子",
      "overview": "剧集简介...",
      "vote_average": 0.0,
      "release_date": "1982-09-27",
      "115-flg": 1,
      "magnet-flg": 0,
      "video-flg": 1,
      "ed2k-flg": 0
    }
  ]
}
```

### 9. 获取电影合集信息 (/collection/{tmdbid})

**请求参数:**
- tmdbid (int, 必填): 合集TMDB ID

**响应示例:**
```json
{
  "id": 295,
  "poster": "/rohE3fl6f6CCQoP2P5TNxdr56ZE.jpg",
  "title": "加勒比海盗（系列）",
  "overview": "合集简介...",
  "115-flg": 1,
  "items": [
    {
      "media_type": "movie",
      "tmdbid": 22,
      "poster": "/h9fxh0rCIB5AviPgltp8JgeVSHz.jpg",
      "title": "加勒比海盗：黑珍珠号的诅咒",
      "overview": "电影简介...",
      "vote_average": 7.8,
      "release_date": "2003-07-09",
      "115-flg": 1,
      "magnet-flg": 1,
      "video-flg": 1,
      "ed2k-flg": 1
    }
  ]
}
```

## 资源标识说明

各种资源标识的含义:
- 115-flg: 1表示有网盘资源，0表示无网盘资源
- magnet-flg: 1表示有磁力资源，0表示无磁力资源
- ed2k-flg: 1表示有ed2k资源，0表示无ed2k资源
- video-flg: 1表示有m3u8资源，0表示无m3u8资源

## 剧集资源特别说明

对于剧集资源:
- 115网盘资源在整个剧集层级提供
- magnet资源在季和单集层级提供
- ed2k和video资源仅在单集层级提供

## 精选影视列表

系统提供了大量精选影视列表，可通过`/list/{listid}`接口获取，包括:
- 不同年代最受欢迎的电影和电视节目
- 特定类型的电影合集(如科幻、恐怖、动作等)
- 各种评分榜单

完整列表ID请参考原始文档。 