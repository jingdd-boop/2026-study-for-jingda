/**
 * 用户路由模块 — 演示 Router 独立挂载
 */
const express = require('express');
const router = express.Router();

const users = [
  { id: 1, name: '张三', role: 'admin' },
  { id: 2, name: '李四', role: 'user' },
];

// GET /api/users
router.get('/', (req, res) => {
  const { role } = req.query;
  const list = role ? users.filter((u) => u.role === role) : users;
  res.json({ code: 0, data: list });
});

// GET /api/users/:id
router.get('/:id', (req, res) => {
  const user = users.find((u) => u.id === Number(req.params.id));
  if (!user) {
    return res.status(404).json({ code: 404, message: '用户不存在' });
  }
  res.json({ code: 0, data: user });
});

module.exports = router;
