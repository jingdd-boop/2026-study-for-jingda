# 04 - 泛型

## 为什么需要泛型

没有泛型时，集合存取都是 `Object`，需要强制转型，容易出错：

```java
List list = new ArrayList();
list.add("hello");
Integer n = (Integer) list.get(0);  // 运行时 ClassCastException！
```

泛型在**编译期**做类型检查：

```java
List<String> list = new ArrayList<>();
list.add("hello");
// list.add(123);  // 编译错误
String s = list.get(0);  // 无需强转
```

## 泛型类

```java
class Box<T> {
    private T value;
    void set(T value) { this.value = value; }
    T get() { return value; }
}
```

## 泛型方法

```java
static <T> T getFirst(List<T> list) {
    return list.isEmpty() ? null : list.get(0);
}
```

## 通配符

| 写法 | 含义 | 读/写 |
|-----|------|------|
| `? extends T` | 上界，T 或其子类 | 只能读（Producer） |
| `? super T` | 下界，T 或其父类 | 只能写（Consumer） |
| `?` | 任意类型 | 只能读 Object |

记忆：**PECS** — Producer extends, Consumer super。

## 类型擦除

Java 泛型在运行时会被擦除为原始类型（Raw Type），这是为了兼容旧代码。了解即可，不影响日常使用。

## 运行

```bash
javac GenericsDemo.java
java GenericsDemo
```
