/**
 * Demo：JWT 认证 + multer 文件上传
 * 运行：npm install && node server.js
 * 端口：3005
 */

require('dotenv').config();

const express = require('express');
const jwt = require('jsonwebtoken');
const multer = require('multer');
const path = require('path');
const fs = require('fs');
const verifyToken = require('./middleware/auth');

const app = express();
const PORT = process.env.PORT || 3005;
const JWT_SECRET = process.env.JWT_SECRET || 'demo-jwt-secret-change-in-production';

const UPLOAD_DIR = path.join(__dirname, 'uploads');
fs.mkdirSync(UPLOAD_DIR, { recursive: true });

// 模拟用户表
const USERS = [{ id: 1, username: 'admin', password: '123456', name: '管理员' }];

const storage = multer.diskStorage({
  destination: UPLOAD_DIR,
  filename: (req, file, cb) => {
    const unique = `${Date.now()}-${file.originalname}`;
    cb(null, unique);
  },
});
const upload = multer({
  storage,
  limits: { fileSize: 1024 * 1024 }, // 1MB
});

app.use(express.json());

app.get('/', (req, res) => {
  res.json({
    name: '认证与上传 Demo',
    login: 'POST /api/login { username, password }',
    demoAccount: { username: 'admin', password: '123456' },
  });
});

// 登录签发 JWT
app.post('/api/login', (req, res) => {
  const { username, password } = req.body;
  const user = USERS.find((u) => u.username === username && u.password === password);

  if (!user) {
    return res.status(401).json({ code: 401, message: '用户名或密码错误' });
  }

  const token = jwt.sign({ userId: user.id, username: user.username }, JWT_SECRET, {
    expiresIn: '2h',
  });

  res.json({
    code: 0,
    data: { token, user: { id: user.id, username: user.username, name: user.name } },
  });
});

// 受保护：当前用户信息
app.get('/api/me', verifyToken, (req, res) => {
  const user = USERS.find((u) => u.id === req.user.userId);
  res.json({ code: 0, data: user });
});

// 受保护：文件上传
app.post('/api/upload', verifyToken, upload.single('file'), (req, res) => {
  if (!req.file) {
    return res.status(400).json({ code: 400, message: '请用 form-data 字段名 file 上传' });
  }

  res.status(201).json({
    code: 0,
    data: {
      originalName: req.file.originalname,
      filename: req.file.filename,
      size: req.file.size,
      path: req.file.path,
      uploadedBy: req.user.username,
    },
  });
});

// 列出已上传文件（受保护）
app.get('/api/uploads', verifyToken, (req, res) => {
  const files = fs.readdirSync(UPLOAD_DIR);
  res.json({ code: 0, data: files });
});

app.listen(PORT, () => {
  console.log(`✅ 认证上传 Demo: http://localhost:${PORT}`);
  console.log('   账号: admin / 123456');
});
