/**
 * Demo：回调 → Promise → async/await
 * 运行：node async-patterns-demo.js
 */

const fs = require('fs');
const fsp = require('fs/promises');
const path = require('path');

const DEMO_FILE = path.join(__dirname, 'demo-data.txt');

// 模拟异步任务
function delay(ms, value) {
  return new Promise((resolve) => setTimeout(() => resolve(value), ms));
}

function fakeFetchUser(id) {
  return delay(100, { id, name: `用户${id}` });
}

// ========== 1. 回调风格 ==========

function readWithCallback(filePath, callback) {
  fs.readFile(filePath, 'utf-8', callback);
}

console.log('===== 1. 回调 Callback =====');

fs.writeFileSync(DEMO_FILE, 'callback era', 'utf-8');

readWithCallback(DEMO_FILE, (err, data) => {
  if (err) {
    console.error('回调错误:', err.message);
    return;
  }
  console.log('回调读取:', data);
});

// ========== 2. Promise 链 ==========

console.log('\n===== 2. Promise 链 =====');

fsp.readFile(DEMO_FILE, 'utf-8')
  .then((data) => {
    console.log('Promise 读取:', data);
    return data.toUpperCase();
  })
  .then((upper) => {
    console.log('链式转换:', upper);
  })
  .catch((err) => {
    console.error('Promise 错误:', err.message);
  });

// ========== 3. async/await ==========

async function asyncAwaitDemo() {
  console.log('\n===== 3. async/await =====');

  try {
    const data = await fsp.readFile(DEMO_FILE, 'utf-8');
    console.log('await 读取:', data);

    const user = await fakeFetchUser(1);
    console.log('模拟 API 返回:', user);
  } catch (err) {
    console.error('try/catch 捕获:', err.message);
  }
}

// ========== 4. 并发 Promise.all ==========

async function concurrentDemo() {
  console.log('\n===== 4. Promise.all 并发 =====');

  const start = Date.now();

  const [user1, user2, user3] = await Promise.all([
    fakeFetchUser(1),
    fakeFetchUser(2),
    fakeFetchUser(3),
  ]);

  console.log('并发结果:', [user1, user2, user3]);
  console.log(`耗时约 ${Date.now() - start}ms（串行会 ~300ms）`);

  console.log('\n===== 5. Promise.allSettled =====');

  const results = await Promise.allSettled([
    delay(50, '成功'),
    Promise.reject(new Error('失败')),
  ]);

  results.forEach((r, i) => {
    console.log(`任务${i + 1}:`, r.status, r.status === 'fulfilled' ? r.value : r.reason.message);
  });
}

async function main() {
  await new Promise((r) => setTimeout(r, 150)); // 等回调执行
  await asyncAwaitDemo();
  await concurrentDemo();

  // 清理
  await fsp.unlink(DEMO_FILE);
  console.log('\n✅ Demo 完成');
}

main().catch(console.error);
