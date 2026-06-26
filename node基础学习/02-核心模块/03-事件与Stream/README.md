# 03 - 事件 EventEmitter 与 Stream

## 核心概念

### EventEmitter — 发布订阅

Node 很多内置模块继承自 `EventEmitter`（如 `http.Server`、Stream）。

```javascript
emitter.on('event', handler);   // 监听
emitter.emit('event', data);    // 触发
emitter.once('event', handler); // 只触发一次
```

> **前端类比**：和 `addEventListener` / 自定义 EventEmitter 一样，只是用于 Node 内部模块协作。

### Stream — 流式处理

适合处理大文件、网络数据，避免一次性读入内存。

| 类型 | 说明 |
|-----|------|
| Readable | 可读流（读文件、HTTP 请求体） |
| Writable | 可写流（写文件、HTTP 响应） |
| Transform | 转换流（压缩、加密） |

```
数据源 → Readable → ... → Writable → 目的地
         （分块传输，内存友好）
```

## 运行

```bash
node event-stream-demo.js
```
