# 04 - 中间件深入

对应 [Express学习路线.md](../Express学习路线.md) **第 3 步**。

## 核心概念

中间件函数签名：`(req, res, next) => {}`

```
请求 → 日志 → 计时 → 鉴权 → 路由 → 响应
         ↓      ↓      ↓
       next() next() next()
```

| 类型 | 示例 |
|-----|------|
| 应用级 | `app.use(logger)` 全局生效 |
| 路由级 | `app.use('/api', auth)` 仅 /api 下 |
| 单路由链 | `app.get('/x', mw1, mw2, handler)` |
| 错误处理 | `(err, req, res, next) => {}` 四个参数 |

## 运行

```bash
npm install
node server.js
```

观察终端输出，理解中间件执行顺序。

## 测试

```bash
# 公开接口（无需 token）
curl http://localhost:3003/public

# 需鉴权（Header 带 demo-token）
curl http://localhost:3003/api/profile
curl -H "Authorization: Bearer demo-token" http://localhost:3003/api/profile
```
