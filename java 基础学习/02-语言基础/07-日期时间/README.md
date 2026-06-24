# 07 - 日期时间

## 为什么不用 Date？

旧版 `java.util.Date` 和 `Calendar` API 设计不好，**Java 8 引入了新的日期时间 API**，推荐使用：

| 类 | 用途 | 示例 |
|---|------|------|
| `LocalDate` | 日期（年月日） | `2026-06-24` |
| `LocalTime` | 时间（时分秒） | `14:30:00` |
| `LocalDateTime` | 日期 + 时间 | `2026-06-24T14:30:00` |
| `DateTimeFormatter` | 格式化 / 解析 | `yyyy-MM-dd` |

## 常用操作

```java
// 获取当前日期
LocalDate today = LocalDate.now();

// 创建指定日期
LocalDate birthday = LocalDate.of(2000, 1, 15);

// 日期运算
LocalDate nextWeek = today.plusDays(7);
LocalDate lastMonth = today.minusMonths(1);

// 格式化
DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
String formatted = today.format(fmt);

// 解析
LocalDate parsed = LocalDate.parse("2026-06-24");
```

## 与 JavaScript 对比

| JavaScript | Java |
|-----------|------|
| `new Date()` | `LocalDateTime.now()` |
| `date.getFullYear()` | `date.getYear()` |
| `dayjs().format('YYYY-MM-DD')` | `date.format(formatter)` |
| 月份从 0 开始（坑） | 月份从 1 开始（直观） |

## 运行

```bash
javac DateTimeDemo.java
java DateTimeDemo
```
