# 06 - 实践练习

两个小项目，把前面学的知识点串起来。建议按顺序完成。

## 练习 1：Todo REST API

**目录**：`todo-api/`

**功能**：
- 完整 CRUD：增删改查 Todo
- 数据持久化到 JSON 文件（重启不丢失）
- 统一响应格式 + 错误处理
- 支持环境变量配置端口

**涉及知识点**：Express、fs、path、中间件、RESTful

---

## 练习 2：文件 CLI 工具

**目录**：`file-cli/`

**功能**：
- 命令行读写文件
- 子命令：`read`、`write`、`list`

**涉及知识点**：process.argv、fs、path、模块拆分

---

## 运行方式

```bash
# Todo API
cd todo-api
npm install
npm start
# 访问 http://localhost:4000

# 文件 CLI
cd file-cli
node file-cli.js read ./sample.txt
node file-cli.js write ./output.txt "Hello CLI"
node file-cli.js list .
```

## 学习建议

1. **先自己写**：不看代码，尝试独立实现
2. **写不出来再看**：参考提供的实现，理解后再重写一遍
3. **扩展功能**：Todo 加分页、文件 CLI 加 `copy` 子命令
