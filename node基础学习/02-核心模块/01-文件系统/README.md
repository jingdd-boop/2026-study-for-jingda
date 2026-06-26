# 01 - 文件系统 fs

## 核心概念

Node 通过 `fs` 模块操作文件系统——这是浏览器 JS **做不到**的能力。

### API 风格

| 风格 | 示例 | 说明 |
|-----|------|------|
| 回调 | `fs.readFile(path, cb)` | 传统写法，错误优先 `(err, data)` |
| 同步 | `fs.readFileSync(path)` | 阻塞，仅脚本/启动时用 |
| Promise | `fs.promises.readFile(path)` | 推荐，配合 async/await |

### 常用操作

- 读写字文件：`readFile` / `writeFile`
- JSON 配置：`JSON.parse` + `readFile`
- 目录：`mkdir`、`readdir`
- 文件信息：`stat`（是否目录、修改时间）

## 与前端对比

| 前端 | Node fs |
|-----|---------|
| `fetch('/api/data')` | `fs.readFile('./data.json')` |
| `localStorage.setItem` | `fs.writeFile` 持久化到磁盘 |
| File API（用户上传） | 直接读写服务器本地路径 |

## 运行

```bash
node fs-demo.js
```

运行后会在当前目录生成 `demo-output/` 文件夹，可查看生成的文件。
