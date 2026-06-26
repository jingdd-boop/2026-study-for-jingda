# Node 基础学习

前端工程师系统学习 Node.js 的仓库。详细路线见 [学习路线.md](./学习路线.md)。

---

## 为什么前端学 Node 上手更快？

你已经会 JavaScript——Node 只是把 JS 从浏览器搬到了服务端。语法几乎一样，差异主要在 **运行环境** 和 **可用 API**：

| 浏览器 | Node.js |
|--------|---------|
| `window` | `globalThis` / `global` |
| `document` / DOM | 无 DOM，用 `fs` / `http` 等模块 |
| `fetch` | `fetch`（Node 18+ 内置）或 `http` 模块 |
| ES Module / bundler | 原生支持 CommonJS + ESM |
| Vite dev server | `node app.js` 直接运行 |

**一句话**：Node = JavaScript 运行时 + 丰富的内置模块 + npm 生态。

---

## 环境要求

```bash
# 验证安装（建议 Node 18+ 或 20 LTS）
node -v
npm -v
```

推荐用 [nvm](https://github.com/nvm-sh/nvm) 管理 Node 版本：

```bash
nvm install 20
nvm use 20
```

---

## 如何运行 Demo

### 无需安装的 Demo（01~03 大部分）

进入对应目录，直接运行：

```bash
cd "01-环境与基础/01-Node入门"
node hello-world.js
```

### 需要依赖的 Demo（Express 等）

先安装依赖，再运行：

```bash
cd "04-Web服务/02-Express入门"
npm install
node express-demo.js
```

---

## 目录结构

```
node基础学习/
├── 学习路线.md              # 完整学习路线
├── 01-环境与基础/           # Node 入门、模块系统
├── 02-核心模块/             # fs、path、events、stream
├── 03-异步编程/             # 回调、Promise、Event Loop
├── 04-Web服务/              # 原生 HTTP、Express 01~08 分步 Demo
├── 05-工程化/               # npm、环境变量、脚本
└── 06-实践练习/             # Todo API、文件 CLI
```

---

## 学习建议

1. **按编号顺序学**：后面的 Demo 会用到前面的概念。
2. **先读 README 再跑代码**：每个子目录都有概念说明和前端类比。
3. **动手改代码**：改路由、改文件名、加一行 `console.log`，观察输出变化。
4. **对比浏览器**：每学一个 API，想想在浏览器里对应什么。
