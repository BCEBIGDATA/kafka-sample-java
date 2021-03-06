# 百度消息服务Java样例

百度消息服务是分布式、高可扩展、高通量的托管消息队列服务。完全兼容开源Kafka。本样例展示如何使用Kafka原生客户端访问百度消息服务。

## 如何运行

### 运行环境

- [Oracle JDK 7 or JDK 8](http://www.oracle.com/technetwork/java/)，最低为Java 1.7.0-b147。
- 最新的稳定版[Apache Maven](http://maven.apache.org/)
- [Kafka 0.10.1.x客户端](http://kafka.apache.org/)

### 准备主题和SSL证书

准备工作的细节请参考[BCE官网帮助文档](https://cloud.baidu.com/doc/Kafka/QuickGuide.html)

1. 在管理控制台中创建好主题。
2. 在管理控制台中下载您的kafka-key.zip，包含Java程序使用的jks证书文件和Kafka客户端配置文件。对C++、Python等Kafka客户端，请使用pem证书文件。
3. 用上一步的文件替换样例代码中的`client.keystore.jks`、`client.truststore.jks`以及`src/main/resources/client.properties`。

### 运行样例代码

Windows下Maven环境请执行：

    run.bat your_topic_name

Linux下Maven环境请执行：

    sh run.sh your_topic_name

IDE环境执行的话，需要设置命令行参数`your_topic_name`。

## 参考链接

- [百度消息服务产品介绍](https://cloud.baidu.com/product/kafka.html)
- [Kafka](http://kafka.apache.org/)

