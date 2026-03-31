# partyhub

🔥 A Spring Boot party-building management project based on MyBatis, MySQL, and integrated static frontend assets.  
🚀 Built for branch star-rating data, backend APIs, and structured business database resources.  
⭐ Provides a practical foundation for organization management, data modeling, and template-based admin pages.

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

## 9. 设计与实现思路

`partyhub` 的实现思路偏向“先数据、再接口、后扩展”：

1. 先通过 `djxt.sql` 梳理党支部、评星批次、星级结果等业务对象及其关系；
2. 再补后端启动骨架、Mapper 扫描和基础查询接口，让关键数据可以直接访问；
3. 同时保留静态前端页面与资源，作为后续逐步接入完整业务流程的承载层；
4. 最后再考虑登录鉴权、角色权限和完整 CRUD 的补齐。

这种方式更适合业务模型相对复杂、但系统还处于逐步建设阶段的项目形态。

## 10. 关键难点与优化方向

### 10.1 关键难点

该项目的难点主要集中在业务建模和实现边界上：

- 党建评星定级类系统表结构较多，且存在批次、年度、党支部、评星结果等多层关系；
- 当前仓库的数据资源比后端实现更完整，容易出现“模型较完整、接口逐步补齐”的状态；
- 需要在“优先跑通核心查询”和“后续能扩展成完整业务系统”之间做好平衡。

### 10.2 当前处理方式

当前版本主要采用以下方式推进：

- 将数据库脚本作为业务建模依据，明确核心实体和主线关系；
- 后端优先实现高价值查询接口，例如评星列表和详情，先打通关键数据链路；
- 静态前端资源以内置页面形式保留，确保仓库已经具备页面结构和后续扩展基础。

### 10.3 后续优化方向

如果继续完善，可以优先推进：

- 登录鉴权、角色权限和操作审计；
- 评星定级相关的完整录入、审核、统计和导出流程；
- 前后端彻底拆分，统一工程结构；
- 数据迁移脚本和初始化说明，提升部署与演示效率。

## 12.1 贡献建议

欢迎通过 Issue / PR 提交：

- 后端业务接口补齐
- 前端页面模块化改造
- 数据库迁移与初始化脚本优化
- 运行文档与部署说明完善

## 12.2 许可说明

本仓库采用 MIT License，详见 [LICENSE](/Users/liuzhuoran/Documents/Playground/readme-batch/partyhub/LICENSE)。
