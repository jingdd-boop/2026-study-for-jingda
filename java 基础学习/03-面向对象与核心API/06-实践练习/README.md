# 06 - 实践练习

三个项目，把阶段 2 的知识点串起来。建议按顺序完成。

## 练习 1：学生管理系统（控制台版）

**文件**：`Student.java`、`StudentManager.java`

**功能**：
- 添加、删除、修改、查询学生
- 按学号查找、列出全部学生
- 使用 `ArrayList` 或 `HashMap` 存储

**涉及知识点**：OOP 封装、集合、Scanner 输入

---

## 练习 2：CSV 文件解析

**文件**：`CSVParser.java`、`sample.csv`

**功能**：
- 读取 CSV 文件，解析为结构化数据
- 统计行数、按列输出
- 使用 NIO.2 + BufferedReader

**涉及知识点**：IO、异常处理、String.split

---

## 练习 3：LRU Cache

**文件**：`LRUCache.java`

**功能**：
- 固定容量缓存，超出时淘汰最久未使用的项
- 支持 `get`、`put` 操作
- 可用 `LinkedHashMap` 实现（accessOrder=true）

**涉及知识点**：泛型、Map、数据结构

---

## 运行方式

```bash
# 学生管理系统
javac Student.java StudentManager.java && java StudentManager

# CSV 解析（需在同目录）
javac CSVParser.java && java CSVParser

# LRU Cache
javac LRUCache.java && java LRUCache
```

## 学习建议

1. **先自己写**：不看参考代码，尝试独立实现
2. **写不出来再看**：理解后再自己重写一遍
3. **扩展功能**：
   - 学生系统：按姓名模糊搜索、成绩排序
   - CSV：支持导出、过滤某列
   - LRU：手写双向链表 + HashMap 版本（进阶）
