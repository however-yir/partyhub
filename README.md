# partyhub

`partyhub` 是一个党建管理系统后端项目，基于 Spring Boot + MyBatis + MySQL，当前形态为：

- 可运行后端服务（统一响应、异常处理、分页）
- 多业务模块 API（支部、用户、通知、反馈、岗位、评星）
- 静态前端资源托管（`src/main/resources/static`）
- 完整业务数据库脚本（`djxt.sql`）

## 技术栈

- Java 8
- Spring Boot 2.4.2
- MyBatis + tk-mapper
- MySQL 8.x

## 项目结构

```text
partyhub
├── src/main/java/org/example/djxt
│   ├── common                 # 统一响应、分页、异常
│   ├── controller             # 新增业务控制器
│   ├── demos/web              # 兼容旧接口与示例控制器
│   ├── domain                 # 业务实体
│   ├── mapper                 # MyBatis Mapper
│   └── service                # 服务层
├── src/main/resources/static  # 静态页面与资源
├── docs/api-coverage.md       # API 覆盖清单
├── evaluation/                # 差距分析与评估记录
├── scripts/evaluation/        # 冒烟测试脚本
├── project-management/        # 迭代活动记录
└── djxt.sql                   # 数据库初始化脚本
```

## 已覆盖 API

详情见：[docs/api-coverage.md](docs/api-coverage.md)

核心接口前缀：

- `/api/branches`
- `/api/users`
- `/api/messages`
- `/api/feedbacks`
- `/api/posts`
- `/api/stars`
- `/api/stars/stats/*`（评星定级统计分析）

兼容旧接口：

- `GET /star`
- `GET /star/{id}`

评星定级重点能力：

- 党支部评星数据填报与草稿更新
- 填报提交流程（支部自评 -> 专家评分）
- 审核打分与总分自动汇总（基础分 + 加分 - 减分 + 评议项）
- 自动星级判定（五星/四星/三星/二星/一星）
- 统计分析（年度总览、星级分布、流程分布、学院排行、多年趋势）

## 本地运行

### 1. 准备环境

- JDK 8
- Maven 3.6+
- MySQL 8.x

### 2. 配置环境变量

```bash
cp .env.example .env
set -a && source .env && set +a
```

### 3. 初始化数据库

```bash
mysql -u root -p -e "create database if not exists djxt default charset utf8mb4"
mysql -u root -p djxt < djxt.sql
```

### 4. 启动服务

```bash
mvn spring-boot:run
```

默认访问：`http://localhost:8080`

### 5. 冒烟检查

```bash
bash scripts/evaluation/api_smoke_check.sh http://localhost:8080
```

## 测试

```bash
mvn test
```

当前已包含基于 `MockMvc` 的控制器测试样例，可直接在无真实数据库环境下执行。

## 迭代记录

- 后端完善差距分析：`evaluation/backend-gap-analysis.md`
- 活动日志：`project-management/2025H1_ACTIVITY_LOG.md`

## License

MIT
