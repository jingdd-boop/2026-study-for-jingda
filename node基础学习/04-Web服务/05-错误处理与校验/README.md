# 05 - 错误处理与参数校验

对应 [Express学习路线.md](../Express学习路线.md) **第 5 步**。

## 核心概念

### async 错误捕获

Express 4 不会自动捕获 async 函数里的 throw，需要用 `asyncHandler` 包装。

### 全局错误处理

四个参数的中间件 `(err, req, res, next)`，必须放在所有路由之后。

### express-validator

声明式校验规则，比手写 if 更清晰。

## 运行

```bash
npm install
node server.js
```

## 测试

```bash
# 校验失败（title 为空）
curl -X POST http://localhost:3004/api/todos \
  -H "Content-Type: application/json" \
  -d '{"title":""}'

# 校验成功
curl -X POST http://localhost:3004/api/todos \
  -H "Content-Type: application/json" \
  -d '{"title":"学校验"}'

# 触发异步错误（模拟服务异常）
curl http://localhost:3004/api/boom
```
