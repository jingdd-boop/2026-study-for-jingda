# 03 - 路由与请求对象

对应 [Express学习路线.md](../Express学习路线.md) **第 2 步**。

## 核心概念

| 来源 | 属性 | 示例 |
|-----|------|------|
| URL 路径 | `req.params` | `/users/:id` → `{ id: '1' }` |
| 查询字符串 | `req.query` | `?page=1` → `{ page: '1' }` |
| 请求体 | `req.body` | 需 `express.json()` |
| 请求头 | `req.headers` | `authorization` |

`express.Router()` 用于按业务拆分路由模块，类似前端 React Router 子路由。

## 目录说明

```
03-路由与请求/
├── server.js          # 入口，挂载路由
└── routes/
    ├── todos.js       # /api/todos
    └── users.js       # /api/users
```

## 运行

```bash
npm install
node server.js
```

## 测试接口

```bash
# 查询参数过滤
curl "http://localhost:3002/api/todos?done=false"

# 路径参数
curl http://localhost:3002/api/todos/1
curl http://localhost:3002/api/users/2

# 创建
curl -X POST http://localhost:3002/api/todos \
  -H "Content-Type: application/json" \
  -d '{"title":"学 Router"}'
```
