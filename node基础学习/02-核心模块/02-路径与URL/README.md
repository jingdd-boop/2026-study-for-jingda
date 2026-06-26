# 02 - 路径 path 与 URL

## 核心概念

### path 模块

处理**文件路径**字符串，解决跨平台差异（Windows `\` vs Unix `/`）。

| 方法 | 作用 | 示例 |
|-----|------|------|
| `path.join()` | 拼接路径 | `join('a', 'b', 'c')` → `a/b/c` |
| `path.resolve()` | 解析为绝对路径 | 相对 → 绝对 |
| `path.extname()` | 获取扩展名 | `.js` |
| `path.basename()` | 文件名 | `app.js` |
| `path.dirname()` | 目录名 | `/usr/local` |

### URL 模块

解析 HTTP 请求中的 URL，Express 底层也会用到。

```javascript
const url = new URL('http://localhost:3000/api/users?page=1');
url.pathname  // '/api/users'
url.searchParams.get('page')  // '1'
```

## 运行

```bash
node path-url-demo.js
```
