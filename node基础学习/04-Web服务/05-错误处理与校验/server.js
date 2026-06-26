/**
 * Demo：错误处理与参数校验
 * 运行：npm install && node server.js
 * 端口：3004
 */

const express = require('express');
const { body, param, validationResult } = require('express-validator');
const asyncHandler = require('./middleware/asyncHandler');
const errorHandler = require('./middleware/errorHandler');

const app = express();
const PORT = 3004;

let todos = [];
let nextId = 1;

app.use(express.json());

function ok(res, data, status = 200) {
  res.status(status).json({ code: 0, data, message: 'success' });
}

// 校验结果统一处理
function validate(req, res, next) {
  const errors = validationResult(req);
  if (!errors.isEmpty()) {
    return res.status(400).json({
      code: 400,
      message: '参数校验失败',
      errors: errors.array(),
    });
  }
  next();
}

app.get('/', (req, res) => {
  ok(res, { name: '错误处理与校验 Demo' });
});

// 带 express-validator 的创建接口
app.post(
  '/api/todos',
  body('title').trim().notEmpty().withMessage('title 不能为空').isLength({ max: 50 }).withMessage('title 最多 50 字'),
  validate,
  (req, res) => {
    const todo = { id: nextId++, title: req.body.title, done: false };
    todos.push(todo);
    ok(res, todo, 201);
  }
);

// async 路由 + 路径参数校验
app.get(
  '/api/todos/:id',
  param('id').isInt({ min: 1 }).withMessage('id 必须是正整数'),
  validate,
  asyncHandler(async (req, res) => {
    const todo = todos.find((t) => t.id === Number(req.params.id));
    if (!todo) {
      const err = new Error('Todo 不存在');
      err.status = 404;
      throw err;
    }
    ok(res, todo);
  })
);

// 模拟异步服务崩溃 — 由 asyncHandler 捕获
app.get(
  '/api/boom',
  asyncHandler(async () => {
    await Promise.resolve();
    throw new Error('模拟数据库连接失败');
  })
);

// 404
app.use((req, res) => {
  res.status(404).json({ code: 404, message: 'Not Found' });
});

// 全局错误处理（必须 4 个参数，放最后）
app.use(errorHandler);

app.listen(PORT, () => {
  console.log(`✅ 校验 Demo: http://localhost:${PORT}`);
});
