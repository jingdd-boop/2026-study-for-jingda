# 02 - Express 入门

## 核心概念

Express 是最流行的 Node Web 框架，把 http 模块的样板代码封装成简洁 API。

### 核心概念

| 概念 | 说明 | 前端类比 |
|-----|------|---------|
| 路由 | `app.get/post/put/delete` | 后端 API 路由表 |
| 中间件 | `app.use(fn)` 链式处理 | 洋葱模型 / 拦截器 |
| req / res | 请求与响应对象 | fetch 的 request/response |
| express.json() | 解析 JSON body | 自动 `JSON.parse` |

### 中间件执行顺序

```
请求 → 日志中间件 → 解析 body → 路由处理 → 错误处理 → 响应
```

## 运行

```bash
npm install
node express-demo.js
```

> 若 `npm install` 报 `EACCES`（`~/.npm` 权限问题），可先执行永久修复：
> `sudo chown -R $(whoami) ~/.npm`
> 或临时用项目内缓存：`npm install --cache ../../.npm-cache`

访问 http://localhost:3001 ，用 curl 或 Postman 测试 POST：

```bash
curl -X POST http://localhost:3001/api/todos \
  -H "Content-Type: application/json" \
  -d '{"title":"新任务"}'
```

## 和原生 http 对比

| 原生 http | Express |
|----------|---------|
| 手动解析 URL | `req.params`、`req.query` |
| 手动读 body | `express.json()` |
| 手动设置 CORS 头 | `cors` 中间件 |
| 大量 if/else 路由 | 声明式路由 |
