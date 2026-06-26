/**
 * Demo：CommonJS 模块
 * 运行：node commonjs-demo.js
 */

const math = require('./math-utils');
const path = require('path'); // Node 内置模块，直接 require

console.log('===== CommonJS 导入 =====');
console.log('10 + 3 =', math.add(10, 3));
console.log('10 - 3 =', math.sub(10, 3));

console.log('\n===== 内置模块 path =====');
console.log('扩展名:', path.extname(__filename)); // .js
console.log('文件名:', path.basename(__filename));

console.log('\n===== require 特点 =====');
console.log('- 同步加载，执行时立即解析依赖');
console.log('- 可以 require 内置模块、本地文件、npm 包');
console.log('- __dirname 只在 CommonJS 中可用');

console.log('__dirname =', __dirname);
