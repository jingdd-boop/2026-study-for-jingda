# 06 - 认证与文件上传

对应 [Express学习路线.md](../Express学习路线.md) **第 6 步**。

## 核心概念

### JWT 流程

1. `POST /api/login` 校验账号 → 签发 token
2. 前端存储 token，后续请求带 `Authorization: Bearer <token>`
3. `verifyToken` 中间件解析 token，挂载 `req.user`

### multer 文件上传

- `upload.single('file')` 单文件
- 文件信息在 `req.file`

## 运行

```bash
npm install
cp .env.example .env   # 可选
node server.js
```

## 测试

```bash
# 1. 登录获取 token
curl -X POST http://localhost:3005/api/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"123456"}'

# 2. 带 token 访问受保护接口（替换 <TOKEN>）
curl -H "Authorization: Bearer <TOKEN>" http://localhost:3005/api/me

# 3. 上传文件
curl -X POST http://localhost:3005/api/upload \
  -H "Authorization: Bearer <TOKEN>" \
  -F "file=@./sample.txt"
```

演示账号：`admin` / `123456`
