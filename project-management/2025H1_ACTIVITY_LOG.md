# 2025H1 Activity Log (partyhub)

## Iteration 1 - Engineering Baseline

- 统一异常处理与 API 返回格式
- 新增分页结果模型
- 提升项目可维护性，减少接口返回风格分裂

## Iteration 2 - Business API Expansion

- 新增 `branch/user/message/feedback/post` 五个业务模块 CRUD
- `star` 模块补齐新增、更新、删除与筛选分页
- 保留旧接口兼容，降低前端接入改造成本

## Iteration 3 - Verification & Documentation

- 新增 `WebMvc` 回归测试（支部、评星）
- 新增 API 覆盖文档与差距分析文档
- 增加脚本化冒烟检查入口

## Iteration 4 - Star Reporting & Analytics

- 新增评星填报提交流程接口：`/api/stars/{id}/submit`
- 新增审核打分接口：`/api/stars/{id}/review`，支持总分自动汇总与星级映射
- 新增统计接口：总览、星级分布、流程分布、学院排行、多年趋势
