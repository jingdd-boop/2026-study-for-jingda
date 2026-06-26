/**
 * Demo：中间件深入
 * 运行：npm install && node server.js
 * 端口：3003
 */

const express = require('express');
const app = express();
const PORT = 3003;

// ========== 1. 应用级：请求日志 ==========
app.use((req, res, next) => {
  console.log(`→ [日志] ${req.method} ${req.url}`);
  next();
});

// ========== 2. 应用级：请求耗时（在响应结束时统计）==========
app.use((req, res, next) => {
  const start = Date.now();
  res.on('finish', () => {
    console.log(`← [耗时] ${req.method} ${req.url} ${res.statusCode} ${Date.now() - start}ms`);
  });
  next();
});

// ========== 3. 给 req 挂载公共数据 ==========
app.use((req, res, next) => {
  req.requestTime = new Date().toISOString();
  next();
});

app.use(express.json());

// ========== 4. 路由级鉴权中间件 ==========
function requireAuth(req, res, next) {
  const auth = req.headers.authorization;
  const token = auth?.startsWith('Bearer ') ? auth.slice(7) : null;

  if (token !== 'demo-token') {
    console.log('  ✗ [鉴权] 未通过');
    return res.status(401).json({ code: 401, message: '需要 Authorization: Bearer demo-token' });
  }

  req.user = { id: 1, name: '演示用户' };
  console.log('  ✓ [鉴权] 通过', req.user.name);
  next();
}

// ========== 5. 单路由多中间件链 ==========
function checkContentType(req, res, next) {
  if (req.method === 'POST' && !req.is('application/json')) {
    return res.status(415).json({ code: 415, message: '需要 Content-Type: application/json' });
  }
  next();
}

// 公开路由
app.get('/public', (req, res) => {
  res.json({ message: '无需登录', time: req.requestTime });
});

// 受保护路由（路由级中间件）
app.get('/api/profile', requireAuth, (req, res) => {
  res.json({ code: 0, data: { user: req.user, time: req.requestTime } });
});

// 多中间件链：鉴权 + 类型检查 + 处理
app.post('/api/notes', requireAuth, checkContentType, (req, res) => {
  res.status(201).json({
    code: 0,
    data: { id: Date.now(), author: req.user.name, text: req.body.text },
  });
});

// ========== 6. 演示 next('route') 跳过当前路由（了解）==========
app.get('/skip-demo', (req, res, next) => {
  if (req.query.skip === '1') {
    return next('route'); // 跳到下一个匹配的 /skip-demo
  }
  res.json({ branch: 'default' });
});
app.get('/skip-demo', (req, res) => {
  res.json({ branch: 'skipped-to-here' });
});

// 404
app.use((req, res) => {
  res.status(404).json({ code: 404, message: 'Not Found' });
});

app.listen(PORT, () => {
  console.log(`✅ 中间件 Demo: http://localhost:${PORT}`);
  console.log('   带 token: curl -H "Authorization: Bearer demo-token" http://localhost:3003/api/profile');
});
