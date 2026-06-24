# 01 - 数据类型与变量

## 核心概念

Java 是**强类型语言**：每个变量必须先声明类型，再赋值。

### 基本数据类型（8 种）

| 类型 | 大小 | 示例 | 说明 |
|-----|------|------|------|
| `byte` | 1 字节 | `127` | 极小整数，少用 |
| `short` | 2 字节 | `32000` | 小整数，少用 |
| `int` | 4 字节 | `42` | **最常用**的整数 |
| `long` | 8 字节 | `9999999999L` | 大整数，字面量加 `L` |
| `float` | 4 字节 | `3.14f` | 单精度浮点，字面量加 `f` |
| `double` | 8 字节 | `3.14159` | **最常用**的小数 |
| `char` | 2 字节 | `'A'` | 单个字符，单引号 |
| `boolean` | 1 位 | `true` | 只有 true / false |

### 引用类型

- `String`：字符串，双引号 `"hello"`
- 数组、类、接口等都属于引用类型

### 变量 vs 常量

```java
int age = 25;           // 变量，可以重新赋值
final int MAX = 100;    // 常量，赋值后不可改（类似 JS 的 const）
```

## 与 JavaScript 对比

| JavaScript | Java | 区别 |
|-----------|------|------|
| `let x = 1` | `int x = 1` | Java 必须写类型 |
| `const PI = 3.14` | `final double PI = 3.14` | 语义相同 |
| 只有 number | int / long / float / double | Java 整数和小数分开 |
| `"hello"` | `String s = "hello"` | String 是类，不是基本类型 |

## 类型转换

- **自动提升**：`int` → `long` → `double`（小范围到大范围，安全）
- **强制转换**：`(int) 3.14` → `3`（可能丢失精度，需显式写）

## 运行

```bash
javac DataTypesDemo.java
java DataTypesDemo
```
