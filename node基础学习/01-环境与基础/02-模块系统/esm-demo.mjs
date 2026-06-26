/**
 * Demo：ES Module
 * 运行：node esm-demo.mjs
 *
 * 注意：ESM 文件使用 .mjs 扩展名，或在 package.json 中设置 "type": "module"
 */

import multiply, { add, sub } from './math-utils.mjs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

// ESM 中没有 __dirname，需要这样获取当前文件路径
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

console.log('===== ES Module 导入 =====');
console.log('10 + 3 =', add(10, 3));
console.log('10 - 3 =', sub(10, 3));
console.log('10 * 3 =', multiply(10, 3));

console.log('\n===== ESM 路径处理 =====');
console.log('当前文件:', __filename);
console.log('所在目录:', __dirname);

console.log('\n===== ESM vs CJS =====');
console.log('- import 必须在文件顶部（静态）');
console.log('- 支持 default 导出 + 命名导出');
console.log('- 动态导入：await import("./module.mjs")');

// 动态 import 示例
const dynamic = await import('./math-utils.mjs');
console.log('\n动态 import add(1, 2) =', dynamic.add(1, 2));
