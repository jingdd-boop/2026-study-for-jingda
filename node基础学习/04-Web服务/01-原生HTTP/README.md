# 01 - 原生 HTTP 模块

## 核心概念

Express 等框架底层都是对 Node `http` 模块的封装。先理解原生写法，再学框架会更容易。

### 基本流程

```javascript
const server = http.createServer((req, res) => {
  // req: 请求（method, url, headers）
  // res: 响应（writeHead, end）
});

server.listen(3000);
```

### RESTful 路由（手动实现）

根据 `req.method` + `req.url` 分发到不同处理逻辑——Express 的 `app.get('/api/todos')` 本质上在做这件事。

## 运行

```bash
node http-server.js
```

启动后访问：

- http://localhost:3000/
- http://localhost:3000/api/hello
- http://localhost:3000/api/time

用 `Ctrl + C` 停止服务。

## 前端联调

```javascript
// 浏览器 Console 或前端项目
fetch('http://localhost:3000/api/hello')
  .then(r => r.json())
  .then(console.log);
```
