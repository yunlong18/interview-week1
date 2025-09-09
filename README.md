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
