# EChat 聊天系统

本项目是一个基于java servlet, websocket, jsp, mysql的web聊天系统。
意在为了用户可以在任何可联网的、有浏览器的设备上聊天。

## 特性
* Easy Chat，简单易用，可在任何联网设备上聊天。
* 功能齐全，用户注册登录、群聊的创建管理。
* 聊天记录全部云端储存，可以随时随地查看。
* 提供桌面客户端和手机客户端，生态完善。

## 环境需求
* JDK 1.8+
* Maven 3.5+
* MySQL 5.7+
* Tomcat 8.5+
* [数据库格式](src/main/java/entity/sql/DatabaseStructure.java)

## 使用方法
1. 配置工件
2. 配置web服务器
3. 确保数据库配置正常
4. 配置Tomcat项目
5. 启动web服务器
6. 打开[http://localhost:8080/EChat_Web_exploded/](http://localhost:8080/EChat_Web_exploded/)
7. 开始聊天吧！