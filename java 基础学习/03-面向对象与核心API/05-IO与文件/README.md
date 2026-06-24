# 05 - IO 与文件

## 流分类

| 类型 | 抽象类 | 典型实现 | 用途 |
|-----|-------|---------|------|
| 字节流 | `InputStream` / `OutputStream` | `FileInputStream` | 二进制、图片 |
| 字符流 | `Reader` / `Writer` | `FileReader`、`BufferedReader` | 文本文件 |

> 文本文件优先用**字符流 + 缓冲**（`BufferedReader`），性能更好。

## NIO.2（Java 7+）

推荐用 `Path` 和 `Files` 替代旧的 `File`：

```java
Path path = Paths.get("data.txt");
String content = Files.readString(path);           // Java 11+
Files.writeString(path, "hello");                // Java 11+
List<String> lines = Files.readAllLines(path);
```

## try-with-resources

```java
try (BufferedReader reader = Files.newBufferedReader(path)) {
    String line;
    while ((line = reader.readLine()) != null) {
        System.out.println(line);
    }
}
```

## 常用 Files 方法

| 方法 | 作用 |
|-----|------|
| `Files.exists(path)` | 文件是否存在 |
| `Files.createFile(path)` | 创建文件 |
| `Files.delete(path)` | 删除 |
| `Files.copy(src, dest)` | 复制 |
| `Files.readAllLines(path)` | 读所有行 |

## 运行

```bash
javac IODemo.java
java IODemo
```

运行后会在当前目录生成 `demo-output.txt`，可打开查看。
