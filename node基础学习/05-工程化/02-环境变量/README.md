# 02 - 环境变量

## 核心概念

环境变量用于区分**开发 / 测试 / 生产**配置，避免把密钥写进代码。

### 读取方式

```javascript
process.env.PORT        // 系统或 .env 注入
process.env.NODE_ENV    // 常见：development / production
```

### dotenv

开发时用 `.env` 文件管理变量，**不要提交到 Git**。

```bash
# .env
PORT=4000
API_KEY=your-secret-key
```

```javascript
require('dotenv').config();
console.log(process.env.PORT);
```

## 运行

```bash
npm install
node env-demo.js

# 也可以命令行临时注入
PORT=5000 node env-demo.js
```

## 安全提醒

- `.env` 已在 `.gitignore` 中忽略
- 生产环境用云平台的环境变量配置，不用 .env 文件
