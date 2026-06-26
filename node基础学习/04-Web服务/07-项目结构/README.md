# 07 - 项目结构（分层架构）

对应 [Express学习路线.md](../Express学习路线.md) **第 7 步**。

## 目录结构

```
07-项目结构/
└── src/
    ├── server.js           # 启动入口
    ├── app.js              # 创建 express 实例
    ├── routes/
    │   └── todos.js        # 路由定义
    ├── controllers/
    │   └── todoController.js  # 解析请求、返回响应
    ├── services/
    │   └── todoService.js     # 业务逻辑、数据操作
    ├── middleware/
    │   ├── asyncHandler.js
    │   └── errorHandler.js
    └── utils/
        └── response.js     # 统一响应格式
```

## 职责划分

| 层级 | 职责 |
|-----|------|
| routes | 定义 URL + HTTP 方法，绑定 controller |
| controllers | 取 req 参数，调 service，调 res 返回 |
| services | 业务逻辑，不关心 HTTP |

## 运行

```bash
npm install
npm start
```

端口：3006

## 对比

`06-实践练习/todo-api/server.js` 是单文件版；本 Demo 是**可维护的分层版**，适合真实项目。
