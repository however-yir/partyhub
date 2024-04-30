# partyhub 党建管理系统

> 基于 Spring Boot 的党支部评星定级与党建事务管理后端服务

[![CI](https://github.com/however-yir/partyhub/actions/workflows/ci.yml/badge.svg)](https://github.com/however-yir/partyhub/actions/workflows/ci.yml)
[![Java Version](https://img.shields.io/badge/Java-17-blue)](https://adoptium.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-green)](https://spring.io/projects/spring-boot)

## 🎯 项目定位

`partyhub` 聚焦党支部评星定级管理场景，解决线下填报效率低、评分口径不统一、年度统计分析滞后的问题。  
系统支持支部信息管理、党员与岗位管理、评星流程流转、通知反馈与统计分析，服务于学院/组织层级的党建数据治理。

## ⚡ 核心能力

| 模块 | 功能说明 | 技术亮点 |
|------|----------|----------|
| 支部管理 | 党支部信息维护、分页检索、状态管理 | RESTful CRUD + 统一响应模型 |
| 用户管理 | 党员账号、组织归属、岗位信息维护 | Spring Security + JWT 鉴权 |
| 评星定级 | 评星填报、提交流程、审核与自动评级 | 评分模型（基础分+加分-减分+评议项） |
| 统计分析 | 年度总览、星级分布、流程分布、学院排行、趋势 | Redis 缓存热点统计接口 |
| 通知反馈 | 消息发布与反馈信息管理 | 分层架构 + 全局异常治理 |

## 🏗️ 技术架构

- Spring Boot 3.2
- Spring Security + JWT
- MyBatis-Plus 3.5
- Flyway
- MySQL 8.x
- Redis

## 🚀 快速开始

### 环境要求

- JDK 17+
- Maven 3.9+
- MySQL 8.x
- Redis 7.x（用于统计缓存）

### 启动步骤

```bash
# 1) 准备环境变量
cp .env.example .env
set -a && source .env && set +a

# 2) 启动依赖（可选：本地已有 MySQL / Redis 可跳过）
docker compose -f docker-compose.dev.yml up -d

# 3) 初始化数据库（两种方式任选其一）
# 方式A：导入历史数据脚本
mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS djxt DEFAULT CHARSET utf8mb4;"
mysql -u root -p djxt < djxt.sql

# 方式B：空库交给 Flyway 自动建表（推荐工程化流程）
mysql -u root -p -e "CREATE DATABASE IF NOT EXISTS djxt DEFAULT CHARSET utf8mb4;"

# 4) 启动服务
mvn spring-boot:run
```

默认地址：`http://localhost:8080`

## 🔐 认证接口

- `POST /api/auth/login`：用户名密码登录，返回 JWT
- `GET /api/auth/me`：获取当前登录用户信息

## 📐 核心 API

| 模块 | 接口前缀 | 说明 |
|------|----------|------|
| 支部 | `/api/branches` | 支部 CRUD 与分页 |
| 用户 | `/api/users` | 党员信息管理 |
| 评星 | `/api/stars` | 评星定级流程与记录 |
| 统计 | `/api/stars/stats/*` | 年度分析、分布与排名 |
| 通知 | `/api/messages` | 消息管理 |
| 反馈 | `/api/feedbacks` | 意见反馈 |
| 岗位 | `/api/posts` | 岗位信息管理 |

兼容历史接口：

- `GET /star`
- `GET /star/{id}`

## 🧪 测试

```bash
# 单元测试
mvn test

# 冒烟测试
bash scripts/evaluation/api_smoke_check.sh http://localhost:8080
```

## 📄 License

MIT License
