# 01 - 回调、Promise 与 async/await

## 核心概念

Node 异步有三种写法，现代项目推荐 **async/await**。

### 1. 回调 Callback（老式）

```javascript
fs.readFile('file.txt', (err, data) => {
  if (err) return console.error(err);
  console.log(data);
});
```

- 错误优先：第一个参数是 `err`
- 多层嵌套 → **回调地狱**

### 2. Promise

```javascript
fs.promises.readFile('file.txt')
  .then(data => console.log(data))
  .catch(err => console.error(err));
```

### 3. async/await（推荐）

```javascript
try {
  const data = await fs.promises.readFile('file.txt', 'utf-8');
  console.log(data);
} catch (err) {
  console.error(err);
}
```

### 并发控制

```javascript
// 全部完成才继续
await Promise.all([task1(), task2()]);

// 不管成功失败都等全部结束
await Promise.allSettled([task1(), task2()]);
```

## 运行

```bash
node async-patterns-demo.js
```
