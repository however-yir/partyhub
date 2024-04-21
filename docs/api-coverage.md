# partyhub API 覆盖清单

## 已覆盖模块

- `GET /api/branches` 党支部分页查询（关键词、学院、状态）
- `GET /api/branches/{id}` 党支部详情
- `POST /api/branches` 新增党支部
- `PUT /api/branches/{id}` 更新党支部
- `DELETE /api/branches/{id}` 删除党支部

- `GET /api/users` 用户分页查询（关键词、支部、状态）
- `GET /api/users/{id}` 用户详情
- `POST /api/users` 新增用户（含账号唯一校验）
- `PUT /api/users/{id}` 更新用户
- `DELETE /api/users/{id}` 用户逻辑删除

- `GET /api/messages` 通知分页查询
- `GET /api/messages/{id}` 通知详情
- `POST /api/messages` 新增通知
- `PUT /api/messages/{id}` 更新通知
- `DELETE /api/messages/{id}` 删除通知

- `GET /api/feedbacks` 反馈分页查询（支持按通知过滤）
- `GET /api/feedbacks/{id}` 反馈详情
- `POST /api/feedbacks` 新增反馈
- `PUT /api/feedbacks/{id}` 更新反馈
- `DELETE /api/feedbacks/{id}` 删除反馈

- `GET /api/posts` 岗位分页查询
- `GET /api/posts/{id}` 岗位详情
- `POST /api/posts` 新增岗位
- `PUT /api/posts/{id}` 更新岗位
- `DELETE /api/posts/{id}` 删除岗位

- `GET /api/stars` 评星记录分页查询（年份、流程、关键词）
- `GET /api/stars/{id}` 评星详情
- `POST /api/stars` 新增评星记录
- `PUT /api/stars/{id}` 更新评星记录
- `PUT /api/stars/{id}/submit` 支部填报提交（流转到专家评分）
- `PUT /api/stars/{id}/review` 专家/管理员审核打分（自动汇总总分与星级）
- `DELETE /api/stars/{id}` 删除评星记录

- `GET /api/stars/stats/overview` 年度总览（填报数、完成数、均分、星级数量）
- `GET /api/stars/stats/rating-distribution` 星级分布统计
- `GET /api/stars/stats/process-distribution` 流程节点分布统计
- `GET /api/stars/stats/dept-ranking` 学院排行（记录数、均分、完成率）
- `GET /api/stars/stats/trend` 多年份趋势统计

## 兼容旧接口

- `GET /star`
- `GET /star/{id}`

## 统一返回

所有 `/api/**` 接口返回结构：

```json
{
  "code": "OK",
  "message": "success",
  "data": {},
  "timestamp": "2026-04-10T16:20:00"
}
```
