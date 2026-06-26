/**
 * Demo：路由与请求对象
 * 运行：npm install && node server.js
 * 端口：3002
 */

const express = require('express');
const todoRoutes = require('./routes/todos');
const userRoutes = require('./routes/users');

const app = express();
const PORT = 3002;

app.use(express.json());

app.get('/', (req, res) => {
  res.json({
    name: '路由与请求 Demo',
    routes: [
      'GET  /api/todos?done=false&keyword=学',
      'GET  /api/todos/:id',
      'POST /api/todos',
      'GET  /api/users?role=admin',
      'GET  /api/users/:id',
    ],
  });
});

// 模块化挂载：URL 前缀 + Router
app.use('/api/todos', todoRoutes);
app.use('/api/users', userRoutes);

// 404 放最后
app.use((req, res) => {
  res.status(404).json({ code: 404, message: `未找到: ${req.method} ${req.url}` });
});

app.listen(PORT, () => {
  console.log(`✅ 路由 Demo: http://localhost:${PORT}`);
});
