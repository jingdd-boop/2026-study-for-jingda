/**
 * 可导出的 app — 供 supertest 测试，也可独立启动
 */
const express = require('express');
const helmet = require('helmet');
const morgan = require('morgan');

let todos = [{ id: 1, title: '写测试', done: false }];
let nextId = 2;

function createApp() {
  const app = express();

  app.use(helmet());
  app.use(morgan('dev'));
  app.use(express.json());

  app.get('/', (req, res) => {
    res.json({ name: '测试与安全 Demo' });
  });

  app.get('/api/todos', (req, res) => {
    res.json({ code: 0, data: todos });
  });

  app.post('/api/todos', (req, res) => {
    const { title } = req.body;
    if (!title?.trim()) {
      return res.status(400).json({ code: 400, message: 'title 不能为空' });
    }
    const todo = { id: nextId++, title: title.trim(), done: false };
    todos.push(todo);
    res.status(201).json({ code: 0, data: todo });
  });

  app.delete('/api/todos/:id', (req, res) => {
    const id = Number(req.params.id);
    const index = todos.findIndex((t) => t.id === id);
    if (index === -1) {
      return res.status(404).json({ code: 404, message: '不存在' });
    }
    const [removed] = todos.splice(index, 1);
    res.json({ code: 0, data: removed });
  });

  return app;
}

// 单例 app 供测试共享（测试间注意数据隔离）
const app = createApp();

module.exports = app;
