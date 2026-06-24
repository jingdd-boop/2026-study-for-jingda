# 03 - 集合框架（重点）

## 体系结构

```
Collection
├── List（有序、可重复）
│   ├── ArrayList    ← 最常用，随机访问 O(1)
│   └── LinkedList   ← 头尾增删 O(1)
├── Set（不重复）
│   ├── HashSet      ← 无序，O(1) 查
│   └── TreeSet      ← 有序（红黑树）
└── Queue
    └── PriorityQueue ← 优先级队列

Map（键值对，不继承 Collection）
├── HashMap          ← 最常用
├── LinkedHashMap    ← 保持插入顺序
└── TreeMap          ← 按键排序
```

> **对比 JS**：`ArrayList` ≈ `Array`，`HashMap` ≈ `Object` / `Map`。

## 常用 API

### List

```java
List<String> list = new ArrayList<>();
list.add("a");
list.get(0);
list.size();
list.remove(0);
```

### Set

```java
Set<Integer> set = new HashSet<>();
set.add(1);
set.contains(1);  // true
```

### Map

```java
Map<String, Integer> map = new HashMap<>();
map.put("age", 25);
map.get("age");
map.containsKey("age");
map.remove("age");

// 遍历
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    entry.getKey();
    entry.getValue();
}
```

## 选型建议

| 场景 | 推荐 |
|-----|------|
| 一般列表 | `ArrayList` |
| 频繁头尾增删 | `LinkedList` |
| 去重 | `HashSet` |
| 需要排序的去重 | `TreeSet` |
| 键值存储 | `HashMap` |
| 需要插入顺序 | `LinkedHashMap` |

## 注意

- 集合存**对象**时用包装类或 String，不要混用基本类型
- 遍历时删除元素用 **Iterator.remove()**，不要在 for-each 里直接 `list.remove(i)`
- `Arrays.asList()` 返回的 List **不能** add/remove

## 运行

```bash
javac CollectionDemo.java
java CollectionDemo
```
