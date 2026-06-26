# 阶段 3：Web 服务 — Demo 目录

从原生 http 到 Express，搭建 REST API。

## Express 专项路线

**完整 8 步学习路线**见 👉 **[Express学习路线.md](./Express学习路线.md)**

## 目录结构

| 编号 | 目录 | 内容 | Express 步骤 | 端口 |
|-----|------|------|-------------|------|
| 01 | [01-原生HTTP](./01-原生HTTP/) | 无框架 HTTP 服务 | 前置 | 3000 |
| 02 | [02-Express入门](./02-Express入门/) | 路由、中间件、CRUD | 第 1、4 步 | 3001 |
| 03 | [03-路由与请求](./03-路由与请求/) | Router、params、query | 第 2 步 | 3002 |
| 04 | [04-中间件深入](./04-中间件深入/) | 鉴权、耗时、中间件链 | 第 3 步 | 3003 |
| 05 | [05-错误处理与校验](./05-错误处理与校验/) | asyncHandler、validator | 第 5 步 | 3004 |
| 06 | [06-认证与文件上传](./06-认证与文件上传/) | JWT、multer | 第 6 步 | 3005 |
| 07 | [07-项目结构](./07-项目结构/) | routes/controllers/services | 第 7 步 | 3006 |
| 08 | [08-测试与安全](./08-测试与安全/) | supertest、helmet、morgan | 第 8 步 | 3007 |

## 建议学习顺序

```
01-原生HTTP → 02-Express入门 → 03~08 按编号学习 → 06-实践练习/todo-api
```

每个需依赖的目录：

```bash
cd "03-路由与请求"    # 换成对应目录
npm install
node server.js       # 或 npm start / npm test
```

> `npm install` 若报 EACCES，见 `02-Express入门/README.md`。

## 对应学习路线

本目录对应 [学习路线.md](../学习路线.md) 中的 **阶段 3：Web 服务 & Express**。
