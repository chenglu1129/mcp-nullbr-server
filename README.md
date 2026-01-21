# NullBR MCP Server

## 简介

NullBR API的题目搜索API现已兼容MCP协议，可以让任意支持MCP协议的智能体助手（如`Claude`、`Cursor`、`Cherry Studio`等）快速接入NullBR API，搜索和获取影视资源信息，并可以转存入CMS中。

本项目依赖 `MCP Java SDK` 开发，基于 Spring AI 框架实现。
支持 **Streamable HTTP (SSE)** 协议，兼容 Cherry Studio 等客户端。

## 工具列表

本项目提供了丰富的影视资源查询与管理工具：

### 搜索与详情
*   **影视搜索 (`searchMedia`)**: 搜索电影、电视剧、人物或合集。
    *   输入: `query` (关键词), `page` (页码, 可选)
*   **获取列表 (`getList`)**: 获取特定列表详情。
    *   输入: `listId`, `page`
*   **获取电影详情 (`getMovieInfo`)**: 获取电影详细元数据。
    *   输入: `tmdbId`
*   **获取电视剧详情 (`getTVInfo`)**: 获取电视剧详细元数据。
    *   输入: `tmdbId`
*   **获取电视剧季详情 (`getTVSeasonInfo`)**: 获取特定季度的详情。
    *   输入: `tmdbId`, `seasonNumber`
*   **获取电视剧集详情 (`getTVEpisodeInfo`)**: 获取特定集数的详情。
    *   输入: `tmdbId`, `seasonNumber`, `episodeNumber`
*   **获取人物信息 (`getPersonInfo`)**: 获取演职人员详情。
    *   输入: `tmdbId`, `page`
*   **获取电影合集信息 (`getCollectionInfo`)**: 获取系列电影合集详情。
    *   输入: `tmdbId`

### 资源获取
*   **获取电影资源 (`getMovieResources`)**: 获取电影的下载/播放资源。
    *   输入: `tmdbId`, `resourceType` (115/magnet/ed2k/video)
*   **获取电视剧资源 (`getTVResources`)**: 获取电视剧网盘资源。
    *   输入: `tmdbId`
*   **获取电视剧季资源 (`getTVSeasonResources`)**: 获取特定季度的资源。
    *   输入: `tmdbId`, `seasonNumber`, `resourceType`
*   **获取电视剧集资源 (`getTVEpisodeResources`)**: 获取特定集数的资源。
    *   输入: `tmdbId`, `seasonNumber`, `episodeNumber`, `resourceType`
*   **获取人物资源 (`getPersonResources`)**: 获取人物相关的网盘资源。
    *   输入: `tmdbId`
*   **获取合集资源 (`getCollectionResources`)**: 获取电影合集的网盘资源。
    *   输入: `tmdbId`

### CMS 管理
*   **添加115分享链接 (`addShareDown`)**: 将获取的115分享链接添加到CMS系统进行转存。
    *   输入: `url`

## 快速开始

### 1. 环境要求

*   JDK 17+
*   Maven 3.6+

### 2. 安装与配置

```bash
git clone https://github.com/yourusername/mcp-nullbr-server
cd mcp-nullbr-server
```

在运行之前，您需要配置 `src/main/resources/application.yml` 文件，填入您的 API 密钥和 CMS 配置：

```yaml
nullbr:
  api:
    base-url: https://api.nullbr.eu.org
    app-id: 您的APP_ID
    api-key: 您的API_KEY

cms:
  base-url: 您的CMS地址
  username: 您的CMS账号
  password: 您的CMS密码
```

### 3. 构建

```bash
mvn clean package
```

### 4. 运行

使用以下命令启动 SSE 服务（默认端口 8080）：

```bash
java -jar target/mcp-nullbr-server-0.0.1-SNAPSHOT.jar
```

### 5. 接入客户端

#### 通过 Cherry Studio 接入 (Stdio 方式 - 推荐本地使用)

1.  确保您已安装 **JDK 17** 或更高版本。
2.  构建项目生成 JAR 包：
    ```bash
    mvn clean package -DskipTests
    ```
3.  打开 Cherry Studio 设置，点击 "MCP 服务器"。
4.  点击 "添加"，配置如下：
    *   **类型**: `Stdio`
    *   **命令**: `java`
    *   **参数**: 
        *   `-Dfile.encoding=UTF-8`
        *   `-jar`
        *   `D:\workspace\mcp_nullbr-server\target\mcp-nullbr-server-0.0.1-SNAPSHOT.jar` (请根据实际路径修改)
5.  保存并启用，确保状态显示为 "已连接"。

#### 通过 Cherry Studio 接入 (SSE 方式)

1.  打开 Cherry Studio 设置，点击 "MCP 服务器"。
2.  点击 "添加"，配置如下：
    *   **类型**: `SSE`
    *   **URL**: `http://localhost:8080/sse` (如果部署在远程，请修改 IP)
3.  保存配置。
4.  在设置 -> 模型服务中，确保已启用 "工具函数调用" (Function Calling)。
5.  在对话界面开启 MCP 服务。
6.  现在您可以直接向 AI 询问，例如：
    *   "搜索电影《黑客帝国》"
    *   "帮我找一下《绝命毒师》第一季的资源"
    *   "把这个115链接转存到我的CMS里"

## 许可证

MIT
