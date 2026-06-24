# 03 - 流程控制

## if / else

和 JavaScript 几乎一样，但条件必须是 `boolean`，不能写 `if (1)` 这种。

```java
if (score >= 90) {
    System.out.println("优秀");
} else if (score >= 60) {
    System.out.println("及格");
} else {
    System.out.println("不及格");
}
```

## switch

Java 12+ 支持 **switch 表达式**（更简洁）：

```java
// 传统 switch
switch (day) {
    case 1: System.out.println("周一"); break;
    case 2: System.out.println("周二"); break;
    default: System.out.println("其他");
}

// 新语法 switch 表达式（Java 14+）
String name = switch (day) {
    case 1 -> "周一";
    case 2 -> "周二";
    default -> "其他";
};
```

> **注意**：传统 switch 每个 case 要加 `break`，否则会继续执行下一个 case（穿透）。

## 循环

| 循环 | 用法 | 场景 |
|-----|------|------|
| `for` | `for (int i = 0; i < 10; i++)` | 已知次数 |
| `while` | `while (condition)` | 先判断再执行 |
| `do-while` | `do { ... } while (condition)` | 至少执行一次 |
| 增强 for | `for (String s : list)` | 遍历数组/集合 |

### break 和 continue

- `break`：跳出当前循环
- `continue`：跳过本次，进入下一次

## 与 JavaScript 对比

| 特性 | JavaScript | Java |
|-----|-----------|------|
| if 条件 | 任何 truthy/falsy 值 | 必须 boolean |
| switch | 有穿透 | 传统 switch 也有穿透 |
| for...of | `for (const x of arr)` | `for (Type x : arr)` |
| forEach | 数组方法 | 增强 for 循环 |

## 运行

```bash
javac ControlFlowDemo.java
java ControlFlowDemo
```
