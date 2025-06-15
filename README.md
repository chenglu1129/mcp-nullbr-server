# NullBR MCP Server

## 简介

NullBR API的题目搜索API现已兼容MCP协议，可以让任意支持MCP协议的智能体助手（如`Claude`、`Cursor`等）快速接入NullBR API，搜索和获取影视资源信息。

依赖`MCP Java SDK`开发，基于Spring AI框架实现。

## 工具列表

#### 影视搜索 `searchMedia`

* 搜索电影、电视剧、人物或合集
* 输入: `query` - 搜索关键词, `page` - 页码(可选)
* 输出: 搜索结果列表

#### 电影详情 `getMovieInfo`

* 获取电影详细信息
* 输入: `tmdbId` - 电影的TMDB ID
* 输出: 电影详情

#### 电影资源 `getMovieResources`

* 获取电影资源，包括网盘、磁力链接、在线播放等
* 输入: `tmdbId` - 电影的TMDB ID, `resourceType` - 资源类型(115/magnet/ed2k/video)
* 输出: 电影资源信息

## 快速开始

使用NullBR MCP Server主要通过`Java SDK`的形式

### 环境要求

- JDK 17+
- Maven 3.6+

### 安装

```bash
git clone https://github.com/yourusername/mcp-nullbr-server
```

### 构建

```bash
cd mcp-nullbr-server
mvn clean package
```

### 使用

#### 命令行运行

```bash
java -Dspring.ai.mcp.server.stdio=true -Dspring.main.web-application-type=none -jar target/mcp-nullbr-server-0.0.1-SNAPSHOT.jar
```

#### 通过Cherry Studio接入

1. 打开Cherry Studio的设置，点击"MCP 服务器"
2. 点击"快速创建"，添加以下配置：
3. 类型选择sse
4. url格式为http://ip:port/sse
5. 在设置-模型服务里选择一个模型，输入API密钥，开启工具函数调用功能
6. 在输入框下面勾选开启MCP服务
7. 现在可以向AI助手询问影视资源了，例如："搜索电影钢铁侠"


## 许可证

MIT 