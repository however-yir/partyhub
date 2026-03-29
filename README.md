# partyhub

> 党建评星主题的数据与应用示例仓库，包含 Spring Boot 后端接口、静态前端资源以及完整业务数据库脚本（`djxt.sql`）。

## 1. 项目定位

`partyhub` 当前仓库是“后端 API + 静态资源 + 数据模型”混合形态：

- 后端：Spring Boot（可运行）
- 前端：`src/main/resources/static` 内置静态页面与资源
- 数据库：`djxt.sql` 提供较完整的业务表结构与样例数据

## 2. 当前代码已实现内容

- 后端应用启动与 MyBatis Mapper 扫描
- `sys_branch_star` 数据查询接口（列表与详情）
  - `GET /star`
  - `GET /star/{id}`
- 静态资源托管（大量 CSS/JS/图片模板资源）

说明：数据库脚本覆盖的业务范围远大于当前后端接口实现，仓库属于“数据与模板较完整、业务代码仍可继续补齐”的状态。

## 3. 技术栈

- 后端：Spring Boot 2.4.2、MyBatis
- 数据库：MySQL 8.x
- 前端资源：Vue2、ElementUI、Axios（静态集成）
- Java 版本：1.8

## 4. 项目结构

```text
partyhub
├── src/main/java/org/example/djxt/
│   ├── demos/web/StarController.java
│   ├── mapper/
│   ├── service/
│   └── domain/
├── src/main/resources/
│   ├── application.yml
│   └── static/                     # 页面与前端资源
├── djxt.sql                        # 业务数据库脚本
├── pom.xml
└── package.json
```

## 5. 本地运行

### 5.1 环境

- JDK 8
- Maven 3.6+
- MySQL 8.x

### 5.2 初始化数据库

1. 创建数据库：`djxt`
2. 导入 [djxt.sql](/Users/liuzhuoran/Documents/Playground/readme-batch/partyhub/djxt.sql)
3. 修改 [application.yml](/Users/liuzhuoran/Documents/Playground/readme-batch/partyhub/src/main/resources/application.yml) 的数据库账号密码

### 5.3 启动后端

```bash
mvn spring-boot:run
```

默认地址：`http://localhost:8080`

## 6. 示例数据

`djxt.sql` 中包含示例管理员数据（`sys_user`）：

- `admin / 123456`

## 7. 当前注意事项

- 根目录包含 `node_modules`，实际开发建议忽略并通过 `npm install` 生成
- `pom.xml` 中 `spring-boot-maven-plugin` 配置了 `<skip>true</skip>`，如需打包请按发布流程评估调整
- 若要实现完整党建评星业务，需要继续补全控制器、服务和权限链路

## 8. 后续扩展建议

- 按 `djxt.sql` 的业务表逐步补齐 CRUD/API
- 增加登录鉴权、角色权限与操作审计
- 统一前后端工程结构（拆分独立前端项目）

## 12.1 贡献建议

欢迎通过 Issue / PR 提交：

- 后端业务接口补齐
- 前端页面模块化改造
- 数据库迁移与初始化脚本优化
- 运行文档与部署说明完善

## 12.2 许可说明

本仓库采用 MIT License，详见 [LICENSE](/Users/liuzhuoran/Documents/Playground/readme-batch/partyhub/LICENSE)。
