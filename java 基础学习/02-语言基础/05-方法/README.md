# 05 - 方法（函数）

## 方法结构

```java
// 访问修饰符  返回类型  方法名(参数列表) { 方法体 }
public static int add(int a, int b) {
    return a + b;
}
```

| 部分 | 说明 |
|-----|------|
| `public` | 公开访问（阶段 2 会细讲修饰符） |
| `static` | 静态方法，可直接通过类名调用 |
| `int` | 返回类型，`void` 表示无返回值 |
| `add` | 方法名 |
| `(int a, int b)` | 参数列表 |

## 方法重载（Overload）

**同名方法，不同参数**——编译器根据参数类型/数量选择调用哪个。

```java
int add(int a, int b) { return a + b; }
double add(double a, double b) { return a + b; }
int add(int a, int b, int c) { return a + b + c; }
```

> 和 JS 不同：JS 没有真正的重载，后定义的函数会覆盖前面的。

## 可变参数

```java
int sum(int... numbers) {
    int total = 0;
    for (int n : numbers) total += n;
    return total;
}
// 调用：sum(1, 2, 3) 或 sum(1, 2, 3, 4, 5)
// 类似 JS 的 rest 参数：function sum(...numbers)
```

## 值传递

Java **只有值传递**：
- 基本类型：传的是值的副本
- 引用类型：传的是引用的副本（指向同一对象，但引用本身不能改）

## 运行

```bash
javac MethodDemo.java
java MethodDemo
```
