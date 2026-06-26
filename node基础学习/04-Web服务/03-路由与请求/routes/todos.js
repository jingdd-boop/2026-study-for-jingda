/**
 * Todo 路由模块 — 演示 params / query / body
 */
const express = require('express');
const router = express.Router();

let todos = [
  { id: 1, title: '学 params', done: true },
  { id: 2, title: '学 query', done: false },
  { id: 3, title: '学 Router', done: false },
];
let nextId = 4;

function ok(res, data) {
  res.json({ code: 0, data, message: 'success' });
}

function fail(res, status, message) {
  res.status(status).json({ code: status, data: null, message });
}

// GET /api/todos?done=true&keyword=学
router.get('/', (req, res) => {
  const { done, keyword } = req.query;
  let result = [...todos];

  if (done !== undefined) {
    const flag = done === 'true';
    result = result.filter((t) => t.done === flag);
  }
  if (keyword) {
    result = result.filter((t) => t.title.includes(keyword));
  }

  ok(res, { list: result, total: result.length, query: req.query });
});

// GET /api/todos/:id
router.get('/:id', (req, res) => {
  const id = Number(req.params.id);
  const todo = todos.find((t) => t.id === id);
  if (!todo) return fail(res, 404, 'Todo 不存在');
  ok(res, todo);
});

// POST /api/todos
router.post('/', (req, res) => {
  const { title } = req.body;
  if (!title?.trim()) return fail(res, 400, 'title 不能为空');

  const todo = { id: nextId++, title: title.trim(), done: false };
  todos.push(todo);
  res.status(201);
  ok(res, todo);
});

module.exports = router;
