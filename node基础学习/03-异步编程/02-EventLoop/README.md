# 02 - Event Loop 事件循环

## 核心概念

Node 和浏览器共用同一套 **Event Loop** 模型（基于 V8 + libuv）。

### 执行顺序（简化版）

```
1. 同步代码（Call Stack）
2. 微任务 Microtask：Promise.then、queueMicrotask
3. 宏任务 Macrotask：setTimeout、setImmediate、I/O 回调
```

### 记忆口诀

> **同步 → 微任务 → 宏任务**

同一轮循环中，所有微任务会在下一个宏任务之前全部执行完。

### Node 特有的阶段

Node Event Loop 还有更细的阶段（timers、poll、check…），初学掌握上面的简化版即可应对 90% 面试题。

## 运行

```bash
node event-loop-demo.js
```

**建议**：先自己猜测输出顺序，再运行对照。

## 前端类比

浏览器里 `console.log` → `Promise.then` → `setTimeout` 的顺序题，和 Node **完全一样**。
