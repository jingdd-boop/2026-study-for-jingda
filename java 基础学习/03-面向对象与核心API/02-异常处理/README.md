# 02 - 异常处理

## 异常体系

```
Throwable
├── Error          ← JVM 级错误，一般不捕获（如 OutOfMemoryError）
└── Exception
    ├── RuntimeException（非受检异常）  ← NullPointerException、IllegalArgumentException
    └── 其他 Exception（受检异常）      ← IOException、SQLException，必须处理或声明 throws
```

## try-catch-finally

```java
try {
    // 可能抛异常的代码
} catch (IOException e) {
    // 处理特定异常
} catch (Exception e) {
    // 处理其他异常
} finally {
    // 无论是否异常都会执行（资源清理）
}
```

## try-with-resources（推荐）

自动关闭实现了 `AutoCloseable` 的资源：

```java
try (Scanner scanner = new Scanner(System.in)) {
    // 使用 scanner
}  // 自动 close，即使发生异常
```

## 受检 vs 非受检

| 类型 | 是否必须处理 | 典型场景 |
|-----|------------|---------|
| 受检异常 | 必须 `try-catch` 或 `throws` | 文件 IO、网络 |
| 非受检异常 | 可选处理 | 编程错误、参数非法 |

## 自定义异常

```java
class BusinessException extends RuntimeException {
    BusinessException(String message) {
        super(message);
    }
}
```

## 最佳实践

1. **不要吞异常**：空的 `catch {}` 会隐藏问题
2. **捕获具体类型**：先 catch 子类，再 catch 父类
3. **记录日志**：至少 `e.printStackTrace()` 或使用日志框架
4. **用异常表达错误**：参数校验失败抛 `IllegalArgumentException`

## 运行

```bash
javac ExceptionDemo.java
java ExceptionDemo
```
