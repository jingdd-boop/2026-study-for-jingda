/**
 * Demo：Express 入门
 * 运行：npm install && node express-demo.js
 * 访问：http://localhost:3001
 */

const express = require('express');

const app = express();
const PORT = 3001;

// 内存 Todo 数据
let todos = [
  { id: 1, title: '学 Node 模块系统', done: true },
  { id: 2, title: '学 Express', done: false },
];
let nextId = 3;

// ========== 中间件 ==========

// 1. 请求日志
app.use((req, res, next) => {
  console.log(`[${new Date().toLocaleTimeString()}] ${req.method} ${req.url}`);
  next(); // 必须调用 next() 才会进入下一个中间件
});

// 2. 解析 JSON 请求体
app.use(express.json());

// 3. CORS（开发联调）
app.use((req, res, next) => {
  res.setHeader('Access-Control-Allow-Origin', '*');
  res.setHeader('Access-Control-Allow-Methods', 'GET,POST,PUT,DELETE,OPTIONS');
  res.setHeader('Access-Control-Allow-Headers', 'Content-Type');
  if (req.method === 'OPTIONS') {
    return res.sendStatus(204);
  }
  next();
});

// 统一响应格式
function ok(res, data) {
  res.json({ code: 0, data, message: 'success' });
}

function fail(res, status, message) {
  res.status(status).json({ code: status, data: null, message });
}

// ========== 路由 ==========

app.get('/', (req, res) => {
  ok(res, {
    name: 'Express Demo API',
    endpoints: {
      'GET /api/todos': '获取列表',
      'POST /api/todos': '创建 { title }',
      'PUT /api/todos/:id': '更新 { done }',
      'DELETE /api/todos/:id': '删除',
    },
  });
});

// 列表
app.get('/api/todos', (req, res) => {
  ok(res, todos);
});

// 创建
app.post('/api/todos', (req, res) => {
  const { title } = req.body;
  if (!title || !title.trim()) {
    return fail(res, 400, 'title 不能为空');
  }
  const todo = { id: nextId++, title: title.trim(), done: false };
  todos.push(todo);
  ok(res, todo);
});

// 更新
app.put('/api/todos/:id', (req, res) => {
  const id = Number(req.params.id);
  const todo = todos.find((t) => t.id === id);
  if (!todo) return fail(res, 404, 'Todo 不存在');

  if (typeof req.body.done === 'boolean') {
    todo.done = req.body.done;
  }
  ok(res, todo);
});

// 删除
app.delete('/api/todos/:id', (req, res) => {
  const id = Number(req.params.id);
  const index = todos.findIndex((t) => t.id === id);
  if (index === -1) return fail(res, 404, 'Todo 不存在');

  const removed = todos.splice(index, 1)[0];
  ok(res, removed);
});

// 404 兜底
app.use((req, res) => {
  fail(res, 404, `路由不存在: ${req.method} ${req.url}`);
});

// ========== 启动 ==========

app.listen(PORT, () => {
  console.log(`✅ Express 服务: http://localhost:${PORT}`);
  console.log('   GET  /api/todos');
  console.log('   POST /api/todos  body: {"title":"新任务"}');
});
