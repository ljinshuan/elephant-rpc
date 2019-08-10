# elephant-rpc


+ RPC框架调用的demo 从0开始实现一个RPC框架，了解RPC的基本原理

## 目前实现

+ server client收发请求

+ 服务端服务注册；

+ client发送调用请求->server接收请求->调用真正服务->返回客户端

+ client调用时异步发请求，同步等待调用结果；

+ server端处理服务在Provider线程而不是在IO线程；

+ client、server端的超时处理

+ 序列化统一使用JSON

+ client 通过cglib创建类模拟本地调用；



## todo list

+ 各阶段的超时处理；

+ 错误处理

+ 丰富日志；

+ 支持多种协议；

+ 分布式的服务注册、发现；

+ 心跳、重连；

+ 扩展性；

+ 丰富调用上下文，支持trace；

+ 负载均衡；

+ 支持异步调用；

+ 与Spring集成；



