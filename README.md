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

#### 方式一：命令行运行

```bash
java -Dspring.ai.mcp.server.stdio=true -Dspring.main.web-application-type=none -jar target/mcp-nullbr-server-0.0.1-SNAPSHOT.jar
```

#### 方式二：通过Cherry Studio接入

1. 打开Cherry Studio的设置，点击"MCP 服务器"
2. 点击"编辑 JSON"，添加以下配置：

```json
{
  "mcpServers": {
    "nullbrServer": {
      "command": "java",
      "args": [
        "-Dspring.ai.mcp.server.stdio=true",
        "-Dspring.main.web-application-type=none",
        "-Dlogging.pattern.console=",
        "-jar",
        "/yourPath/mcp-nullbr-server-0.0.1-SNAPSHOT.jar"
      ],
      "env": {}
    }
  }
}
```

3. 在设置-模型服务里选择一个模型，输入API密钥，开启工具函数调用功能
4. 在输入框下面勾选开启MCP服务
5. 现在可以向AI助手询问影视资源了，例如："搜索电影钢铁侠"

### 代码调用

1. 引入依赖

```xml
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-mcp-client-spring-boot-starter</artifactId>
    <version>1.0.0-M6</version>
</dependency>
```

2. 配置MCP服务器

需要在application.yml中配置MCP服务器的一些参数：

```yaml
spring:
  ai:
    mcp:
      client:
        stdio:
          # 指定MCP服务器配置文件
          servers-configuration: classpath:/mcp-servers-config.json
  mandatory-file-encoding: UTF-8
```

其中mcp-servers-config.json的配置如下：

```json
{
  "mcpServers": {
    "nullbrServer": {
      "command": "java",
      "args": [
        "-Dspring.ai.mcp.server.stdio=true",
        "-Dspring.main.web-application-type=none",
        "-Dlogging.pattern.console=",
        "-jar",
        "/yourPath/mcp-nullbr-server-0.0.1-SNAPSHOT.jar"
      ],
      "env": {}
    }
  }
}
```

3. 初始化聊天客户端

```java
@Bean
public ChatClient initChatClient(ChatClient.Builder chatClientBuilder,
                                ToolCallbackProvider mcpTools) {
    return chatClientBuilder
    .defaultTools(mcpTools)
    .build();
}
```

4. 接口调用

```java
@PostMapping(value = "/ai/answer/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public Flux<String> generateStreamAsString(@RequestBody AskRequest request) {
    Flux<String> content = chatClient.prompt()
            .user(request.getContent())
            .stream()
            .content();
    return content
            .concatWith(Flux.just("[complete]"));
}
```

## 后续计划

- 完善电视剧相关功能
- 添加人物和合集相关功能
- 优化结果格式化和错误处理
- 添加缓存机制提高性能

## 许可证

MIT 