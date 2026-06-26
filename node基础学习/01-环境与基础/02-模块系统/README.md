# 02 - 模块系统

## 核心概念

Node 有两种模块规范，**同一个文件不能混用**（除非特殊配置）。

### CommonJS（CJS）— 默认

```javascript
// 导出
module.exports = { add };
exports.sub = sub;  // 简写

// 导入
const math = require('./math-utils');
```

- 文件扩展名：`.js`（默认 `"type": "commonjs"`）
- 同步加载
- 有 `__dirname`、`__filename`

### ES Module（ESM）

```javascript
// 导出
export function add(a, b) { return a + b; }

// 导入
import { add } from './math-utils.mjs';
```

- 文件扩展名：`.mjs`，或在 `package.json` 中设置 `"type": "module"`
- 静态分析，支持 tree-shaking
- 无 `__dirname`，用 `import.meta.url` 替代

## 与前端对比

| 场景 | 前端 | Node |
|-----|------|------|
| 开发时 import | Vite 即时编译 | 需 `"type": "module"` 或 `.mjs` |
| 默认模块系统 | ESM | CommonJS |
| 动态 import | `import()` | `import()` 同样支持 |

## 运行

```bash
# CommonJS
node commonjs-demo.js

# ES Module（注意 .mjs 扩展名）
node esm-demo.mjs
```

## 选择建议

- 学习 Demo、老项目：CommonJS 更常见
- 新项目、和前端统一：优先 ESM + TypeScript
