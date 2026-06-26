/**
 * 工具模块 — CommonJS 导出
 * 被 commonjs-demo.js 引用
 */

function add(a, b) {
  return a + b;
}

function sub(a, b) {
  return a - b;
}

// 方式 1：导出整个对象
module.exports = { add, sub };

// 方式 2（二选一）：逐个挂载
// exports.add = add;
// exports.sub = sub;
