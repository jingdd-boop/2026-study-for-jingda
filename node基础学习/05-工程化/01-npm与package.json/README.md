# 01 - npm 与 package.json

## 核心概念

`package.json` 和前端项目**完全一样**，是你最熟悉的部分。

### 常用字段

```json
{
  "name": "my-app",
  "version": "1.0.0",
  "scripts": {
    "start": "node server.js",
    "dev": "nodemon server.js"
  },
  "dependencies": {
    "express": "^4.21.0"
  },
  "devDependencies": {
    "nodemon": "^3.0.0"
  }
}
```

| 字段 | 说明 |
|-----|------|
| `scripts` | `npm run xxx` 执行的命令 |
| `dependencies` | 生产依赖（部署时需要） |
| `devDependencies` | 开发依赖（仅本地开发） |

### 常用命令

```bash
npm init -y          # 初始化 package.json
npm install express  # 安装生产依赖
npm install -D nodemon  # 安装开发依赖
npm run dev          # 运行 scripts.dev
```

## 本目录说明

本目录的 `package.json` 是**示例文件**，展示字段含义，无需运行。

实际带依赖的 Demo 见 `04-Web服务/02-Express入门/` 和 `06-实践练习/todo-api/`。

## 前端 vs Node

| 前端 | Node 后端 |
|-----|----------|
| `npm run dev` → Vite | `npm run dev` → nodemon + node |
| `dependencies` | 同样含义 |
| `build` → dist/ | 通常不需要打包（或用 tsc 编译 TS） |
