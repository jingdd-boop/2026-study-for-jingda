# 04 - 数组

## 数组基础

数组是**固定长度**的同类型元素集合。

```java
// 声明 + 初始化
int[] nums = {1, 2, 3, 4, 5};

// 先声明长度，再赋值
int[] arr = new int[3];
arr[0] = 10;
arr[1] = 20;
arr[2] = 30;

// 获取长度
int len = nums.length;  // 注意：是 .length 不是 .length()
```

## 多维数组

```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6}
};
// matrix[0][1] = 2
```

## 遍历方式

```java
// 方式 1：普通 for
for (int i = 0; i < arr.length; i++) { ... }

// 方式 2：增强 for
for (int n : arr) { ... }
```

## 与 JavaScript 对比

| 特性 | JavaScript | Java |
|-----|-----------|------|
| 创建 | `[1, 2, 3]` | `int[] arr = {1, 2, 3}` |
| 长度 | `arr.length` | `arr.length`（一样） |
| 动态扩容 | `push()` 可以 | **不行**，长度固定 |
| 类型 | 任意混合 | 必须同一类型 |

> 需要动态长度列表？阶段 2 会学 `ArrayList`。

## 运行

```bash
javac ArrayDemo.java
java ArrayDemo
```
