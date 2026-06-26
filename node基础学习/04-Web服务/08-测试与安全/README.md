# 08 - 测试与安全

对应 [Express学习路线.md](../Express学习路线.md) **第 8 步**。

## 核心概念

### 安全中间件

- `helmet`：设置安全 HTTP 响应头
- `morgan`：请求日志

### API 测试

- `supertest`：不发真实网络请求，直接测 Express app
- Node 内置 `node:test` 运行测试，无需 Jest 配置

## 运行服务

```bash
npm install
npm start
```

端口：3007

## 运行测试

```bash
npm test
```

## 测试文件

`tests/todos.test.js` — 覆盖 GET / 和 Todo CRUD 基本场景。
