/**
 * Demo 02：Node vs 浏览器环境对比
 * 运行：node node-vs-browser.js
 *
 * 在浏览器 Console 里无法运行此文件（没有 require、process）
 */

console.log('===== 全局对象 =====');

// 浏览器里是 window，Node 里是 globalThis
console.log('globalThis === global?', globalThis === global); // true（Node 环境）

// 浏览器有 window.document，Node 没有
console.log('typeof document:', typeof document); // 'undefined'

console.log('\n===== 内置模块（浏览器需 bundler，Node 原生支持）=====');

const os = require('os');
const path = require('path');

console.log('操作系统:', os.platform(), os.release());
console.log('CPU 核心数:', os.cpus().length);
console.log('当前文件目录:', __dirname);
console.log('当前文件路径:', __filename);
console.log('拼接路径:', path.join(__dirname, 'hello-world.js'));

console.log('\n===== 模块系统 =====');
console.log('本文件使用 CommonJS：require() / module.exports');

console.log('\n===== 前端类比 =====');
console.log('node app.js     ≈  浏览器打开打包后的 index.html');
console.log('npm install     ≈  和前端项目完全一样');
console.log('process.env     ≈  Vite 的 import.meta.env（环境变量）');
