/**
 * 实践练习：Todo REST API
 * 运行：npm install && npm start
 *
 * API:
 *   GET    /api/todos       列表
 *   POST   /api/todos       创建 { title }
 *   PUT    /api/todos/:id   更新 { title?, done? }
 *   DELETE /api/todos/:id   删除
 */

const express = require('express');
const fs = require('fs/promises'); // Node 内置模块，Promise 版文件读写
const path = require('path');

const app = express();
const PORT = process.env.PORT || 4000; // 支持通过环境变量改端口
const DATA_FILE = path.join(__dirname, 'data', 'todos.json'); // 持久化存储路径

// ========== 中间件 ==========

// 解析 JSON 请求体（POST / PUT 需要）
app.use(express.json());

// CORS：允许浏览器跨域访问（前端联调时使用）
app.use((req, res, next) => {
  res.setHeader('Access-Control-Allow-Origin', '*');
  res.setHeader('Access-Control-Allow-Methods', 'GET,POST,PUT,DELETE,OPTIONS');
  res.setHeader('Access-Control-Allow-Headers', 'Content-Type');
  if (req.method === 'OPTIONS') return res.sendStatus(204); // 预检请求直接返回
  next();
});

// ========== 工具函数 ==========

// 统一成功响应格式
function ok(res, data) {
  res.json({ code: 0, data, message: 'success' });
}

// 统一失败响应格式
function fail(res, status, message) {
  res.status(status).json({ code: status, data: null, message });
}

// 从 JSON 文件读取 todo 列表；文件不存在时返回初始空数据
async function loadTodos() {
  try {
    const raw = await fs.readFile(DATA_FILE, 'utf-8');
    return JSON.parse(raw);
  } catch (err) {
    if (err.code === 'ENOENT') {
      // 首次运行，data/todos.json 尚未创建
      return { todos: [], nextId: 1 };
    }
    throw err;
  }
}

// 将 todo 列表写回 JSON 文件（自动创建 data 目录）
async function saveTodos(store) {
  await fs.mkdir(path.dirname(DATA_FILE), { recursive: true });
  await fs.writeFile(DATA_FILE, JSON.stringify(store, null, 2), 'utf-8');
}

// ========== 路由 ==========

// 健康检查 / 服务信息
app.get('/', (req, res) => {
  ok(res, { name: 'Todo API', port: PORT });
});

// 获取全部 todo
app.get('/api/todos', async (req, res) => {
  const store = await loadTodos();
  ok(res, store.todos);
});

// 创建 todo，请求体：{ title: string }
app.post('/api/todos', async (req, res) => {
  const { title } = req.body;
  if (!title?.trim()) return fail(res, 400, 'title 不能为空');

  const store = await loadTodos();
  const todo = { id: store.nextId++, title: title.trim(), done: false };
  store.todos.push(todo);
  await saveTodos(store);
  ok(res, todo);
});

// 更新 todo，请求体：{ title?: string, done?: boolean }
app.put('/api/todos/:id', async (req, res) => {
  const id = Number(req.params.id);
  const store = await loadTodos();
  const todo = store.todos.find((t) => t.id === id);
  if (!todo) return fail(res, 404, 'Todo 不存在');

  if (req.body.title !== undefined) todo.title = String(req.body.title).trim();
  if (typeof req.body.done === 'boolean') todo.done = req.body.done;

  await saveTodos(store);
  ok(res, todo);
});

// 删除 todo
app.delete('/api/todos/:id', async (req, res) => {
  const id = Number(req.params.id);
  const store = await loadTodos();
  const index = store.todos.findIndex((t) => t.id === id);
  if (index === -1) return fail(res, 404, 'Todo 不存在');

  const [removed] = store.todos.splice(index, 1);
  await saveTodos(store);
  ok(res, removed);
});

// ========== 启动服务 ==========

app.listen(PORT, () => {
  console.log(`✅ Todo API: http://localhost:${PORT}`);
  console.log(`   数据文件: ${DATA_FILE}`);
});
