/**
 * Demo：原生 http 模块
 * 运行：node http-server.js
 * 访问：http://localhost:3000
 */

const http = require('http');
const { URL } = require('url');

const PORT = 3000;

// 模拟内存数据
const todos = [
  { id: 1, title: '学 Node fs', done: true },
  { id: 2, title: '学 Express', done: false },
];

function sendJSON(res, statusCode, data) {
  res.writeHead(statusCode, {
    'Content-Type': 'application/json; charset=utf-8',
    // 允许前端跨域（开发联调必备）
    'Access-Control-Allow-Origin': '*',
  });
  res.end(JSON.stringify(data));
}

const server = http.createServer((req, res) => {
  const url = new URL(req.url, `http://localhost:${PORT}`);
  const pathname = url.pathname;

  console.log(`${req.method} ${pathname}`);

  // 路由分发
  if (req.method === 'GET' && pathname === '/') {
    sendJSON(res, 200, {
      message: 'Node 原生 HTTP 服务',
      routes: ['GET /api/hello', 'GET /api/time', 'GET /api/todos'],
    });
    return;
  }

  if (req.method === 'GET' && pathname === '/api/hello') {
    sendJSON(res, 200, { message: 'Hello from Node http!' });
    return;
  }

  if (req.method === 'GET' && pathname === '/api/time') {
    sendJSON(res, 200, { time: new Date().toISOString() });
    return;
  }

  if (req.method === 'GET' && pathname === '/api/todos') {
    sendJSON(res, 200, { code: 0, data: todos });
    return;
  }

  // 404
  sendJSON(res, 404, { code: 404, message: 'Not Found' });
});

server.listen(PORT, () => {
  console.log(`✅ 服务已启动: http://localhost:${PORT}`);
  console.log('   试试: /api/hello  /api/todos');
  console.log('   Ctrl+C 停止');
});
