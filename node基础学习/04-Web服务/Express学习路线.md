# Express 框架学习路线（前端工程师版）

> 面向已掌握 JavaScript 和 HTTP 基础的前端开发者，从零到能独立搭建生产可用的 REST API。
> 建议先完成 `01-原生HTTP`，再按本路线学习 Express。

---

## 一、学习前准备

### 1.1 为什么要学 Express？


| 方式               | 优点             | 缺点                    |
| ---------------- | -------------- | --------------------- |
| 原生 `http`        | 理解底层原理         | 路由、body 解析、错误处理都要手写   |
| **Express**      | 生态成熟、上手快、中间件丰富 | 需理解中间件模型              |
| NestJS / Fastify | 企业级 / 高性能      | 学习曲线更陡，建议 Express 后再学 |


Express 是 Node 后端**事实上的入门标准**：文档多、招聘要求常见、和 Spring Boot 的「快速搭 API」定位类似。

### 1.2 前端经验如何迁移


| 前端概念                         | Express 对应                 |
| ---------------------------- | -------------------------- |
| 路由（React Router）             | `app.get('/users')` 后端路由表  |
| 中间件 / 拦截器（Axios interceptor） | `app.use(middleware)` 洋葱模型 |
| `fetch` 的 request / response | `req` / `res` 对象           |
| 环境变量 `import.meta.env`       | `process.env` + dotenv     |
| 组件拆分                         | 路由 / 控制器 / 服务 分层           |
| 统一错误边界                       | 全局错误处理中间件                  |


### 1.3 环境

```bash
cd "04-Web服务/02-Express入门"
npm install          # 若 EACCES，见该目录 README
node express-demo.js # http://localhost:3001
```

---

## 二、学习路线总览

```
第 1 步：Express 基础          （2~3 天）
    ↓
第 2 步：路由与请求对象         （2~3 天）
    ↓
第 3 步：中间件（核心）         （3~4 天）
    ↓
第 4 步：RESTful API 设计      （2~3 天）
    ↓
第 5 步：错误处理与校验         （2~3 天）
    ↓
第 6 步：认证 & 文件上传        （3~5 天）
    ↓
第 7 步：项目结构与工程化       （1 周）
    ↓
第 8 步：测试、安全与部署       （持续）
```

**预计总时长**：3~~4 周（每天 1~~2 小时），可与主路线「阶段 3~6」并行推进。

---

## 三、各阶段详细内容

### 第 1 步：Express 基础（2~3 天）

**学习目标**：能创建最小 Express 应用，理解它和原生 http 的关系。

#### 核心知识点

```javascript
const express = require('express');
const app = express();

app.get('/', (req, res) => {
  res.json({ message: 'Hello' });
});

app.listen(3000, () => {
  console.log('Server running on port 3000');
});
```


| 概念                          | 说明                                         |
| --------------------------- | ------------------------------------------ |
| `express()`                 | 创建应用实例，底层仍是 http.Server                    |
| `app.listen()`              | 启动服务，等价于 `http.createServer(app).listen()` |
| `app.METHOD(path, handler)` | 注册路由：GET / POST / PUT / DELETE 等           |
| `res.json()`                | 自动设置 Content-Type 并序列化 JSON                |
| `res.status()`              | 设置 HTTP 状态码                                |


#### 与原生 http 对照

```
原生 http                          Express
─────────────────────────────────────────────────
http.createServer((req,res)=>{})  →  express()
手动解析 URL、method              →  app.get('/path', handler)
res.writeHead + res.end(JSON)     →  res.json(data)
大量 if/else 路由                 →  声明式路由注册
```

**实践任务**：

- [ ] 跑通 `02-Express入门/express-demo.js`
- [ ] 新增 `GET /api/health` 健康检查接口
- [ ] 对比 `01-原生HTTP/http-server.js`，列出 Express 帮你省掉的代码

**本仓库 Demo**：`02-Express入门/`

---

### 第 2 步：路由与请求对象（2~3 天）

**学习目标**：熟练处理 URL 参数、查询参数、请求体，会用 Router 拆分模块。

#### 2.1 路由匹配

```javascript
// 路径参数
app.get('/api/users/:id', (req, res) => {
  const id = req.params.id;
});

// 查询参数  GET /api/todos?status=done&page=1
app.get('/api/todos', (req, res) => {
  const { status, page } = req.query;
});
```


| 来源     | 属性            | 示例                             |
| ------ | ------------- | ------------------------------ |
| URL 路径 | `req.params`  | `/users/:id` → `{ id: '1' }`   |
| 查询字符串  | `req.query`   | `?page=1` → `{ page: '1' }`    |
| 请求头    | `req.headers` | `Authorization`、`Content-Type` |
| 请求体    | `req.body`    | 需先 `app.use(express.json())`   |


#### 2.2 express.Router 模块化

```javascript
// routes/todos.js
const router = express.Router();
router.get('/', listTodos);
router.post('/', createTodo);
module.exports = router;

// app.js
app.use('/api/todos', require('./routes/todos'));
```

> **前端类比**：`Router` 像 React Router 的 `<Routes>` 子模块，按业务拆分路由文件。

#### 2.3 路由顺序与匹配规则

- 先注册的路由优先匹配
- `app.use('/api', router)` 会匹配 `/api` 下所有子路径
- 通配与 404 兜底路由要放在**最后**

**实践任务**：

- [ ] 给 Todo API 加 `GET /api/todos/:id` 单条查询
- [ ] 用 `Router` 把路由拆到 `routes/todos.js`
- [ ] 实现按 `?done=true` 过滤 Todo 列表

**本仓库 Demo**：`03-路由与请求/`

---

### 第 3 步：中间件 — Express 核心（3~4 天）

**学习目标**：理解洋葱模型，能写自定义中间件，掌握常用内置中间件。

#### 3.1 什么是中间件？

中间件是一个函数 `(req, res, next) => {}`：

```
请求 → 中间件1 → 中间件2 → 路由处理 → 响应
         ↓          ↓
       next()     next()   不调用 next() 则请求挂起
```

> **前端类比**：类似 Axios 请求/响应拦截器链，或 Koa 的洋葱模型（Express 是线性链）。

#### 3.2 中间件分类


| 类型   | 写法                                 | 用途                |
| ---- | ---------------------------------- | ----------------- |
| 应用级  | `app.use(fn)`                      | 全局日志、CORS、body 解析 |
| 路由级  | `app.use('/api', fn)`              | 仅对某路径生效           |
| 路由处理 | `app.get('/x', fn1, fn2, handler)` | 单路由多步骤            |
| 错误处理 | `(err, req, res, next) => {}`      | **4 个参数**，捕获异常    |


#### 3.3 常用内置 / 官方中间件

```javascript
app.use(express.json());                    // 解析 JSON body
app.use(express.urlencoded({ extended: true })); // 解析表单
app.use(express.static('public'));          // 静态资源（类似 vite public/）
```

#### 3.4 自定义中间件示例

```javascript
// 请求日志
app.use((req, res, next) => {
  console.log(`${req.method} ${req.url}`);
  next();
});

// 简易鉴权
function requireAuth(req, res, next) {
  const token = req.headers.authorization;
  if (!token) return res.status(401).json({ message: '未登录' });
  next();
}

app.get('/api/profile', requireAuth, (req, res) => {
  res.json({ user: 'me' });
});
```

**实践任务**：

- [ ] 实现请求耗时中间件（记录每个接口 ms）
- [ ] 实现 `requireAuth` 占位中间件（先写死 token 校验）
- [ ] 画出 express-demo.js 里中间件的执行顺序图

**本仓库 Demo**：`02-Express入门/express-demo.js`（入门）、`04-中间件深入/`（专题）

---

### 第 4 步：RESTful API 设计（2~3 天）

**学习目标**：按 REST 规范设计接口，统一响应格式，配置 CORS 供前端联调。

#### 4.1 HTTP 方法与语义


| 方法     | 语义   | 示例                    |
| ------ | ---- | --------------------- |
| GET    | 查询   | `GET /api/todos`      |
| POST   | 创建   | `POST /api/todos`     |
| PUT    | 全量更新 | `PUT /api/todos/1`    |
| PATCH  | 部分更新 | `PATCH /api/todos/1`  |
| DELETE | 删除   | `DELETE /api/todos/1` |


#### 4.2 状态码规范


| 状态码 | 场景                |
| --- | ----------------- |
| 200 | 成功（GET、PUT、PATCH） |
| 201 | 创建成功（POST）        |
| 204 | 删除成功，无 body       |
| 400 | 参数错误              |
| 401 | 未认证               |
| 403 | 无权限               |
| 404 | 资源不存在             |
| 500 | 服务器内部错误           |


#### 4.3 统一响应格式（推荐）

```javascript
// 成功
{ "code": 0, "data": { ... }, "message": "success" }

// 失败
{ "code": 400, "data": null, "message": "title 不能为空" }
```

> 作为前端，你一定更喜欢统一格式——现在轮到你来设计了。

#### 4.4 CORS 跨域

```javascript
// 开发环境简单写法
app.use((req, res, next) => {
  res.setHeader('Access-Control-Allow-Origin', '*');
  res.setHeader('Access-Control-Allow-Methods', 'GET,POST,PUT,DELETE,OPTIONS');
  res.setHeader('Access-Control-Allow-Headers', 'Content-Type, Authorization');
  if (req.method === 'OPTIONS') return res.sendStatus(204);
  next();
});

// 生产推荐：cors 包
// npm install cors
// app.use(cors({ origin: 'https://your-frontend.com' }));
```

**实践任务**：

- [ ] 完成 Todo 完整 CRUD（`06-实践练习/todo-api/`）
- [ ] 用 Postman / Apifox 测试全部接口
- [ ] 用前端 `fetch` 或 Axios 联调

**本仓库 Demo**：`02-Express入门/`、`06-实践练习/todo-api/`

---

### 第 5 步：错误处理与参数校验（2~3 天）

**学习目标**：接口健壮，错误不导致进程崩溃，参数有校验。

#### 5.1 异步错误捕获

```javascript
// ❌ async 路由里抛错，Express 4 不会自动捕获
app.get('/api/x', async (req, res) => {
  throw new Error('boom'); // 可能直接导致未处理拒绝
});

// ✅ 用 try/catch 或包装函数
const asyncHandler = (fn) => (req, res, next) => {
  Promise.resolve(fn(req, res, next)).catch(next);
};

app.get('/api/x', asyncHandler(async (req, res) => {
  const data = await someAsync();
  res.json(data);
}));
```

#### 5.2 全局错误处理中间件

```javascript
// 必须放在所有路由之后，且 4 个参数
app.use((err, req, res, next) => {
  console.error(err);
  res.status(err.status || 500).json({
    code: err.status || 500,
    message: err.message || '服务器错误',
  });
});
```

> **Java 类比**：类似 `@ControllerAdvice` 全局异常处理。

#### 5.3 参数校验

```javascript
// 推荐：express-validator 或 zod
const { body, validationResult } = require('express-validator');

app.post('/api/todos',
  body('title').trim().notEmpty().withMessage('title 不能为空'),
  (req, res) => {
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
      return res.status(400).json({ code: 400, message: errors.array() });
    }
    // 创建逻辑...
  }
);
```

**实践任务**：

- [ ] 给 todo-api 加全局错误处理中间件
- [ ] POST 创建 Todo 时用 `express-validator` 校验 title
- [ ] 模拟数据库抛错，确认返回 500 而不是进程退出

**本仓库 Demo**：`05-错误处理与校验/`

---

### 第 6 步：认证与文件上传（3~5 天）

**学习目标**：实现登录态校验和文件上传——两个真实项目高频需求。

#### 6.1 JWT 认证流程

```
1. POST /api/login  → 校验账号密码 → 签发 JWT
2. 前端存 token（localStorage / Cookie）
3. 后续请求 Header: Authorization: Bearer <token>
4. 中间件 verifyToken → 解析 userId → next()
```

```javascript
const jwt = require('jsonwebtoken');

function signToken(user) {
  return jwt.sign({ userId: user.id }, process.env.JWT_SECRET, { expiresIn: '7d' });
}

function verifyToken(req, res, next) {
  const auth = req.headers.authorization;
  const token = auth?.startsWith('Bearer ') ? auth.slice(7) : null;
  if (!token) return res.status(401).json({ message: '未登录' });

  try {
    req.user = jwt.verify(token, process.env.JWT_SECRET);
    next();
  } catch {
    res.status(401).json({ message: 'token 无效或过期' });
  }
}
```

> **前端优势**：你天天处理 token 存取和 401 跳转，后端只是「签发 + 校验」的一方。

#### 6.2 文件上传（multer）

```javascript
const multer = require('multer');
const upload = multer({ dest: 'uploads/' });

app.post('/api/upload', upload.single('file'), (req, res) => {
  res.json({ filename: req.file.filename, size: req.file.size });
});
```

#### 6.3 Cookie Session（了解）

- `express-session` + `cookie-parser`
- 适合传统服务端渲染；前后端分离项目更常用 JWT

**实践任务**：

- [ ] 实现 `POST /api/login` 返回 JWT（用户表可先用内存）
- [ ] 保护 `POST /api/todos` 需要登录才能创建
- [ ] 实现单文件上传接口

**本仓库 Demo**：`06-认证与文件上传/`

---

### 第 7 步：项目结构与工程化（约 1 周）

**学习目标**：从小单文件 demo 升级为可维护的项目结构。

#### 7.1 推荐目录结构

```
todo-api/
├── src/
│   ├── app.js              # 创建 express 实例、挂载中间件
│   ├── server.js           # 启动入口、读 PORT
│   ├── routes/
│   │   ├── index.js
│   │   └── todos.js
│   ├── controllers/
│   │   └── todoController.js
│   ├── services/
│   │   └── todoService.js   # 业务逻辑
│   ├── middleware/
│   │   ├── auth.js
│   │   ├── errorHandler.js
│   │   └── validate.js
│   └── utils/
│       └── response.js      # ok() / fail() 统一响应
├── data/                    # 或对接数据库
├── .env
├── package.json
└── README.md
```

#### 7.2 职责划分


| 层级          | 职责                   | 类比                |
| ----------- | -------------------- | ----------------- |
| routes      | 定义 URL 和 HTTP 方法     | 路由表               |
| controllers | 解析请求、调用 service、返回响应 | 组件事件处理            |
| services    | 业务逻辑、数据库操作           | 自定义 hooks / API 层 |
| middleware  | 横切关注点：鉴权、日志、校验       | 拦截器               |


#### 7.3 开发体验

```json
{
  "scripts": {
    "start": "node src/server.js",
    "dev": "nodemon src/server.js"
  }
}
```

```javascript
// 根据 NODE_ENV 区分配置
const isDev = process.env.NODE_ENV !== 'production';
```

**实践任务**：

- [ ] 把 `todo-api/server.js` 重构为 routes + controllers + services
- [ ] 配置 `npm run dev` + nodemon
- [ ] 用 dotenv 管理 `PORT`、`JWT_SECRET`

**本仓库 Demo**：`07-项目结构/`、`05-工程化/02-环境变量/`

---

### 第 8 步：测试、安全与部署（持续）

**学习目标**：接口可测试、基本安全、可部署上线。

#### 8.1 API 测试（supertest）

```javascript
const request = require('supertest');
const app = require('../src/app');

test('GET /api/todos', async () => {
  const res = await request(app).get('/api/todos');
  expect(res.status).toBe(200);
  expect(res.body.code).toBe(0);
});
```

#### 8.2 安全清单


| 措施       | 包 / 做法                           |
| -------- | -------------------------------- |
| 安全响应头    | `helmet`                         |
| 速率限制     | `express-rate-limit`             |
| 防 SQL 注入 | 使用参数化查询（ORM / PreparedStatement） |
| 敏感配置     | 环境变量，不进 Git                      |
| HTTPS    | 生产环境 Nginx / 云平台终止 SSL           |


#### 8.3 常用生产中间件

```javascript
const helmet = require('helmet');
const morgan = require('morgan');
const rateLimit = require('express-rate-limit');

app.use(helmet());
app.use(morgan('dev')); // 请求日志
app.use(rateLimit({ windowMs: 15 * 60 * 1000, max: 100 }));
```

#### 8.4 部署要点

```bash
# 生产启动
NODE_ENV=production node src/server.js

# 或使用 PM2
pm2 start src/server.js --name todo-api
```

**实践任务**：

- [ ] 用 supertest 为 Todo API 写 2~3 个接口测试
- [ ] 接入 helmet + morgan
- [ ] 部署到 Railway / Render / 自己的服务器

**本仓库 Demo**：`08-测试与安全/`

---

## 四、Express vs 其他框架


| 框架          | 特点                        | 何时学               |
| ----------- | ------------------------- | ----------------- |
| **Express** | 灵活、生态最大、中间件多              | **现在**            |
| Koa         | 洋葱模型更纯粹，需自行选型中间件          | Express 之后        |
| Fastify     | 性能高，Schema 校验内置           | 追求性能时             |
| NestJS      | 装饰器、模块化、类似 Angular/Spring | 企业级 TypeScript 项目 |


---

## 五、Express 常见坑


| 问题                       | 原因                     | 解决                               |
| ------------------------ | ---------------------- | -------------------------------- |
| `req.body` 是 `undefined` | 未使用 `express.json()`   | 在路由前 `app.use(express.json())`   |
| 路由不生效                    | 顺序错了或被前置中间件拦截          | 检查注册顺序，404 放最后                   |
| 异步报错导致崩溃                 | 未 catch / 未交 next(err) | 用 asyncHandler 包装                |
| CORS 预检失败                | 未处理 OPTIONS            | 返回 204 或使用 `cors` 包              |
| 改了代码不生效                  | 未重启服务                  | 开发时用 nodemon                     |
| `npm install` EACCES     | `~/.npm` 权限问题          | `sudo chown -R $(whoami) ~/.npm` |


---

## 六、推荐 npm 包清单


| 用途     | 包名                            |
| ------ | ----------------------------- |
| Web 框架 | `express`                     |
| 跨域     | `cors`                        |
| 环境变量   | `dotenv`                      |
| 热重载    | `nodemon`                     |
| 参数校验   | `express-validator` / `zod`   |
| 认证     | `jsonwebtoken`、`bcryptjs`     |
| 文件上传   | `multer`                      |
| 安全     | `helmet`、`express-rate-limit` |
| 日志     | `morgan`、`winston`            |
| 测试     | `supertest`、`jest` / `vitest` |


---

## 七、里程碑检验


| 里程碑 | 检验标准                             | 预计时间    |
| --- | -------------------------------- | ------- |
| E1  | 独立写出 Express Hello World + 3 个路由 | 第 1 周   |
| E2  | 说清中间件执行顺序，能写自定义中间件               | 第 2 周   |
| E3  | Todo RESTful CRUD + 统一响应 + CORS  | 第 3 周   |
| E4  | JWT 登录保护接口 + 全局错误处理              | 第 4 周   |
| E5  | 项目分层重构 + 部署上线                    | 第 5~6 周 |


---

## 八、与本仓库 Demo 的对应关系

| 学习步骤 | 本仓库路径 | 端口 |
|---------|-----------|------|
| 前置 | `01-原生HTTP/` | 3000 |
| 第 1 步 | `02-Express入门/` | 3001 |
| 第 2 步 | `03-路由与请求/` | 3002 |
| 第 3 步 | `04-中间件深入/` | 3003 |
| 第 4 步 | `02-Express入门/`、`06-实践练习/todo-api/` | 3001 / 4000 |
| 第 5 步 | `05-错误处理与校验/` | 3004 |
| 第 6 步 | `06-认证与文件上传/` | 3005 |
| 第 7 步 | `07-项目结构/` | 3006 |
| 第 8 步 | `08-测试与安全/` | 3007 |

**建议顺序**：按上表从第 1 步学到第 8 步，每步 `npm install` 后运行对应 Demo。

> Express 学的不仅是 API 写法，更是**后端思维**：路由设计、中间件分层、错误边界、安全与部署。这些能力在 NestJS、Spring Boot 里同样适用。

