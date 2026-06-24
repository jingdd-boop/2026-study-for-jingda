# Java 基础学习

前端转 Java 全栈的学习仓库。详细路线见 [java 基础学习/学习路线.md](./java%20基础学习/学习路线.md)。

---

## 为什么运行 Java 要先 `javac` 再 `java`？

初学时几乎每个 Demo 都要敲这两步：

```bash
javac OOPDemo.java   # ① 编译
java OOPDemo         # ② 运行
```

这不是多余操作，而是 **Java 作为编译型语言** 的标准流程。

### 一句话总结

| 命令 | 角色 | 输入 | 输出 |
|------|------|------|------|
| `javac` | **编译器**（Compiler） | `.java` 源码（人类可读） | `.class` 字节码（JVM 可读） |
| `java` | **运行时**（Runtime / JVM） | `.class` 字节码 | 程序执行结果 |

**先编译、再运行**——因为 JVM 不能直接执行 `.java` 源码，只能执行编译后的字节码。

### 完整流程

```
  你写的代码                    编译                    运行
┌─────────────┐    javac     ┌─────────────┐    java    ┌─────────────┐
│ OOPDemo.java │  ────────►  │ OOPDemo.class│  ────────► │    JVM      │
│  (源码)      │             │  (字节码)     │            │  执行 main  │
└─────────────┘             └─────────────┘            └─────────────┘
                                   │
                                   ▼
                            还会生成其他 .class
                            （每个类/接口一个文件）
```

以 `OOPDemo.java` 为例，文件里定义了 `Person`、`Dog`、`Animal` 等多个类，`javac` 会为 **每一个类/接口** 各生成一个 `.class` 文件。运行 `java OOPDemo` 时，JVM 从 `OOPDemo.class` 的 `main` 方法入口开始，按需加载其余 `.class`。

### JDK、JRE、JVM 的关系

| 组件 | 包含什么 | 类比（前端） |
|------|---------|-------------|
| **JVM** | 只负责运行字节码 | 浏览器 / Node.js 运行时 |
| **JRE** | JVM + 核心类库 | Node.js（能跑，不能编译 TS） |
| **JDK** | JRE + **javac** 等开发工具 | Node.js + TypeScript 编译器 |

终端里 `java -version` 和 `javac -version` 都能输出版本号，说明装的是 **JDK**（开发工具包），而不只是 JRE。

### 和前端怎么类比？

| Java | 前端 |
|------|------|
| `.java` 源码 | `.ts` / `.jsx` 源码 |
| `javac` 编译 | `tsc` / Babel / Webpack 构建 |
| `.class` 字节码 | 构建产物（`.js`） |
| `java` 启动 JVM | `node app.js` 运行 |
| `main` 方法 | 程序入口 / `index.js` |

JavaScript 在 Node 或浏览器里通常是 **解释执行**（或 JIT 即时编译），改完代码直接跑；Java 则需要 **先把源码编译成字节码**，这一步目前无法跳过（除非用 IDE / 构建工具帮你自动做）。

### 什么时候不用手动敲 `javac`？

| 场景 | 谁帮你编译 |
|------|-----------|
| IntelliJ IDEA / Cursor 点「运行」 | IDE 在后台自动调用 `javac` |
| Maven 项目执行 `mvn compile` | Maven 调用编译器 |
| Gradle 项目执行 `./gradlew build` | Gradle 调用编译器 |

手动 `javac` + `java` 主要用于 **学习阶段的小 Demo**，帮助理解编译和运行是两回事。

### 常用写法

**源码目录保持干净**——编译产物输出到 `out/`：

```bash
mkdir -p out
javac -d out OOPDemo.java
java -cp out OOPDemo
```

**修改代码后需要重新编译**。只改 `.java` 不重新 `javac`，运行的仍是旧的 `.class`，可能看不到最新改动，甚至报 `ClassNotFoundException`。

**`.class` 文件不要提交 Git**。仓库根目录 `.gitignore` 已忽略 `*.class` 和 `out/`。

### 常见报错对照

| 报错 | 原因 |
|------|------|
| `javac: command not found` | 未安装 JDK，或 PATH 未配置 |
| `Error: Could not find or load main class OOPDemo` | 未编译、类名拼错，或不在 `-cp` 指定目录下 |
| `java: 找不到符号` | 源码有语法/引用错误，编译失败 |
| 改了代码但输出没变 | 忘记重新 `javac` |

---

## 目录结构

```
java 基础学习/
├── 学习路线.md              # 全栈学习总路线
├── 02-语言基础/             # 阶段 1：语法、数组、方法…
└── 03-面向对象与核心API/    # 阶段 2：OOP、集合、泛型、IO…
```
