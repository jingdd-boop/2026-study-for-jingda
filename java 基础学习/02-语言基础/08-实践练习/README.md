# 08 - 实践练习

三个小项目，把阶段 1 的知识点串起来。建议按顺序完成。

## 练习 1：控制台计算器

**文件**：`Calculator.java`

**功能**：
- 支持 `+ - * /` 四则运算
- 输入两个数字和运算符，输出结果
- 除零时给出提示

**涉及知识点**：变量、运算符、if/else、Scanner 输入

---

## 练习 2：猜数字游戏

**文件**：`GuessNumber.java`

**功能**：
- 程序随机生成 1~100 的数字
- 用户反复猜测，提示"太大了"或"太小了"
- 猜对后显示用了几次

**涉及知识点**：Random、while 循环、if/else、Scanner 输入

---

## 练习 3：字符串处理工具

**文件**：`StringUtil.java`

**功能**：
- 反转字符串
- 统计词频（输入一句话，统计每个词出现次数）
- 判断是否为回文

**涉及知识点**：String 方法、数组、循环、方法定义

---

## 运行方式

```bash
# 计算器
javac Calculator.java && java Calculator

# 猜数字
javac GuessNumber.java && java GuessNumber

# 字符串工具
javac StringUtil.java && java StringUtil
```

## 学习建议

1. **先自己写**：不看代码，尝试独立实现
2. **写不出来再看**：参考提供的实现，理解后再自己重写一遍
3. **加功能**：比如计算器支持连续运算、猜数字限制次数等
