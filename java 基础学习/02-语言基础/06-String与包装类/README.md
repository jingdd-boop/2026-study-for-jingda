# 06 - String 与包装类

## String 要点

### 1. 不可变性

String 对象创建后**不能修改**，每次 "修改" 都会创建新对象。

```java
String s = "hello";
s = s + " world";  // 不是改原对象，而是创建了新 String
```

### 2. 比较

```java
String a = "hello";
String b = new String("hello");

a == b;         // false！== 比较的是内存地址
a.equals(b);    // true，equals 比较内容
```

> **永远用 `.equals()` 比较字符串内容**，这是 Java 新手最常踩的坑。

### 3. 常用方法

| 方法 | 作用 | JS 类比 |
|-----|------|---------|
| `length()` | 长度 | `.length` |
| `charAt(i)` | 取字符 | `[i]` |
| `substring(start, end)` | 截取 | `.slice()` |
| `indexOf("x")` | 查找位置 | `.indexOf()` |
| `toUpperCase()` | 转大写 | `.toUpperCase()` |
| `trim()` | 去首尾空格 | `.trim()` |
| `split(",")` | 分割 | `.split()` |
| `isEmpty()` | 是否为空 | `.length === 0` |

### 4. StringBuilder

频繁拼接字符串时用 `StringBuilder`，性能远好于 `+` 拼接。

```java
StringBuilder sb = new StringBuilder();
sb.append("Hello").append(" ").append("World");
String result = sb.toString();
```

## 包装类

基本类型有对应的包装类，用于在需要对象的场景（如集合）：

| 基本类型 | 包装类 |
|---------|--------|
| `int` | `Integer` |
| `double` | `Double` |
| `boolean` | `Boolean` |
| `char` | `Character` |

### 自动装箱 / 拆箱

```java
Integer num = 42;    // 自动装箱：int → Integer
int val = num;       // 自动拆箱：Integer → int
```

## 运行

```bash
javac StringAndWrapperDemo.java
java StringAndWrapperDemo
```
