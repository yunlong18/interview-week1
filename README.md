# Week 1

## Day 1: 用`Spring Initializr`创建 Spring Boot 项目

1. 访问 [Spring Initializr](https://start.spring.io/)
2. 选择项目元数据
   - Project: Maven Project
   - Language: Java
   - Spring Boot: 选择最新版本
   - Project Metadata: 填写 Group 和 Artifact 信息
3. 选择依赖项
   - 在"Dependencies"部分添加所需的依赖项，例如：
     - Spring Web
     - Spring Data JPA
     - H2 Database
4. 生成项目
   - 点击"Generate"按钮下载项目压缩包
5. 解压并导入 IDE
   - 使用 IDE（如 IntelliJ IDEA 或 Eclipse）导入解压后的项目
6. 运行项目

   ```bash
   # 在项目根目录下运行以下命令启动 Spring Boot 应用：
   $ ./mvnw spring-boot:run
   ```

7. git 初始化

   ```bash
   # 初始化 git 仓库
   $ git init
   $ git add .
   $ git commit -m "init: spring boot skeleton"
   ```

## Day 2: 创建 RESTful API

### CURL 常用参数

- `-X` 或 `--request`：指定请求方法（GET、POST、PUT、DELETE 等）。
- `-H` 或 `--header`：添加请求头信息。
- `-d` 或 `--data`：发送请求体数据，通常用于 POST 或 PUT 请求。
- `-i`：显示响应头信息。
- `-v`：显示详细的请求和响应信息，便于调试。

### 测试接口

#### 获取订单信息

```bash
curl -i -H "Accept: application/json" "http://localhost:8080/api/orders/1"
```

#### 创建新订单

```bash
curl -X POST -H "Content-Type: application/json" -d '{"product":"Sample Product","quantity":2}' http://localhost:8080/api/orders
```

#### 注册新用户

```bash
# 正常请求
curl -X POST -H "Content-Type: application/json" -d '{"username":"testuser","password":"testpass"}' http://localhost:8080/api/users/register
# 异常请求
curl -X POST -H "Content-Type: application/json" -d '{"username":"","password":""}' http://localhost:8080/api/users/register
```

#### 登录

```bash
# 正常请求
curl -X POST -H "Content-Type: application/json" -d '{"username":"testuser","password":"testpass"}' http://localhost:8080/api/users/login
# 用户名不存在
curl -X POST -H "Content-Type: application/json" -d '{"username":"testusera","password":"testpass"}' http://localhost:8080/api/users/login
# 密码错误
curl -X POST -H "Content-Type: application/json" -d '{"username":"testuser","password":"wrongpass"}' http://localhost:8080/api/users/login
```
