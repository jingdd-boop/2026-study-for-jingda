# 01 - Node 入门

## 核心概念

### Node.js 是什么？

Node.js 是基于 **Chrome V8 引擎** 的 JavaScript **运行时**，让 JS 可以在服务器、命令行等环境运行。

```
浏览器 JS = V8 + DOM + Web API
Node.js   = V8 + libuv + Node 内置模块（fs、http…）
```

### 和浏览器的核心差异

| 能力 | 浏览器 | Node.js |
|-----|--------|---------|
| 操作 DOM | ✅ | ❌ |
| 读写本地文件 | ❌ | ✅ `fs` |
| 创建 HTTP 服务 | ❌ | ✅ `http` |
| 全局对象 | `window` | `globalThis` |
| 模块系统 | bundler 处理 | 原生 CJS / ESM |

### process 对象

Node 进程相关的全局对象，常用属性：

- `process.version` — Node 版本
- `process.platform` — 操作系统
- `process.cwd()` — 当前工作目录
- `process.argv` — 命令行参数

## 运行

```bash
node hello-world.js
node node-vs-browser.js
node hello-world.js arg1 arg2   # 体验 process.argv
```
