# 01 - 面向对象（OOP）

## 核心概念

| 概念 | 说明 | 前端类比 |
|-----|------|---------|
| 类与对象 | 类是蓝图，对象是实例 | React Class 组件 vs `<Component />` 实例 |
| 封装 | private 字段 + getter/setter | 模块内部 state，对外暴露 props/方法 |
| 继承 | `extends`，Java 单继承 | `class Dog extends Animal` |
| 多态 | 父类引用指向子类对象 | 接口类型接收不同实现 |
| 抽象类 | `abstract`，可有具体方法 | 抽象基类，部分实现 |
| 接口 | `interface`，可多实现 | TypeScript `interface` |

## 构造方法与 this / super

```java
class Person {
    String name;
    int age;

    Person(String name) {
        this(name, 0);  // 调用本类其他构造器
    }

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Student extends Person {
    String school;

    Student(String name, int age, String school) {
        super(name, age);  // 调用父类构造器，必须第一行
        this.school = school;
    }
}
```

## 访问修饰符

| 修饰符 | 同类 | 同包 | 子类 | 任意 |
|-------|-----|-----|-----|-----|
| `private` | ✓ | ✗ | ✗ | ✗ |
| 默认（无修饰符） | ✓ | ✓ | ✗ | ✗ |
| `protected` | ✓ | ✓ | ✓ | ✗ |
| `public` | ✓ | ✓ | ✓ | ✓ |

## static 静态成员

- 属于**类**而非实例，通过 `类名.成员` 访问
- 静态方法中不能直接使用 `this`（没有实例）
- 常见用途：工具方法、常量、单例模式

## 抽象类 vs 接口

| | 抽象类 | 接口 |
|--|-------|-----|
| 关键字 | `abstract class` | `interface` |
| 继承/实现 | 单继承 | 多实现 |
| 成员 | 可以有字段、构造器、具体方法 | Java 8+ 可有 `default` 方法 |
| 使用场景 | 有公共实现的基类 | 定义能力/契约 |

> **选型建议**：能接口就接口；需要共享状态或构造逻辑时用抽象类。

## 运行

```bash
javac OOPDemo.java
java OOPDemo
```
