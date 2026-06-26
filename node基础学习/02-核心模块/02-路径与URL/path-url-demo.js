/**
 * Demo：path 与 URL
 * 运行：node path-url-demo.js
 */

const path = require('path');
const { URL, URLSearchParams } = require('url');

console.log('===== path 模块 =====');

// join：智能拼接，自动处理分隔符
console.log('join:', path.join('/users', 'wang', 'docs', 'readme.md'));
console.log('join (..):', path.join('/users', 'wang', '..', 'docs')); // 会解析 ..

// resolve：基于当前工作目录解析绝对路径
console.log('resolve:', path.resolve('02-核心模块', '02-路径与URL', 'path-url-demo.js'));
console.log('cwd:', process.cwd());

// 解析文件路径组成部分
const filePath = '/home/user/project/src/app.js';
console.log('extname:', path.extname(filePath));       // .js
console.log('basename:', path.basename(filePath));     // app.js
console.log('dirname:', path.dirname(filePath));       // /home/user/project/src

console.log('\n===== __dirname 实际应用 =====');
console.log('当前 Demo 目录:', __dirname);
console.log('上级目录:', path.dirname(__dirname));

console.log('\n===== URL 模块 =====');

// 模拟 Express 收到的请求 URL
const requestUrl = 'http://localhost:3000/api/todos?status=done&page=2&page=1';
const parsed = new URL(requestUrl);

console.log('protocol:', parsed.protocol);   // http:
console.log('host:', parsed.host);           // localhost:3000
console.log('pathname:', parsed.pathname);   // /api/todos
console.log('search:', parsed.search);       // ?status=done&page=2&page=1

console.log('\n查询参数:');
console.log('status =', parsed.searchParams.get('status')); // done
console.log('page =', parsed.searchParams.get('page'));     // 2（第一个 page）

// 遍历所有参数
for (const [key, value] of parsed.searchParams) {
  console.log(`  ${key}: ${value}`);
}

console.log('\n===== 前端类比 =====');
console.log('new URL()        ≈  浏览器 location / new URL()');
console.log('searchParams     ≈  URLSearchParams / useSearchParams()');
console.log('path.join        ≈  没有直接对应，Node 特有');
