import java.util.*;

/**
 * Demo 03：集合框架
 * 运行：javac CollectionDemo.java && java CollectionDemo
 */
public class CollectionDemo {

    public static void main(String[] args) {
        demoList();
        demoSet();
        demoMap();
        demoQueue();
        demoIteration();
    }

    static void demoList() {
        System.out.println("===== ArrayList =====");
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        list.add("Go");
        System.out.println("get(0): " + list.get(0));
        System.out.println("size: " + list.size());
        list.remove("Python");
        System.out.println("删除后: " + list);

        System.out.println("\n===== LinkedList =====");
        LinkedList<Integer> linked = new LinkedList<>();
        linked.addFirst(1);
        linked.addLast(2);
        linked.addLast(3);
        System.out.println("首尾增删: " + linked);
        System.out.println("first: " + linked.getFirst());
    }

    static void demoSet() {
        System.out.println("\n===== HashSet（无序去重）=====");
        Set<String> hashSet = new HashSet<>();
        hashSet.add("apple");
        hashSet.add("banana");
        hashSet.add("apple");  // 重复，不会加入
        System.out.println(hashSet);

        System.out.println("\n===== TreeSet（有序）=====");
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(30);
        treeSet.add(10);
        treeSet.add(20);
        System.out.println("自动排序: " + treeSet);
    }

    static void demoMap() {
        System.out.println("\n===== HashMap =====");
        Map<String, Integer> scores = new HashMap<>();
        scores.put("张三", 90);
        scores.put("李四", 85);
        scores.put("王五", 92);
        System.out.println("张三的分数: " + scores.get("张三"));
        System.out.println("containsKey: " + scores.containsKey("李四"));

        System.out.println("\n遍历 Map:");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("\n===== LinkedHashMap（保持插入顺序）=====");
        Map<String, String> ordered = new LinkedHashMap<>();
        ordered.put("first", "1");
        ordered.put("second", "2");
        ordered.put("third", "3");
        System.out.println(ordered);
    }

    static void demoQueue() {
        System.out.println("\n===== PriorityQueue =====");
        Queue<Integer> pq = new PriorityQueue<>();
        pq.offer(30);
        pq.offer(10);
        pq.offer(20);
        System.out.print("按优先级出队: ");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println();
    }

    static void demoIteration() {
        System.out.println("\n===== 遍历方式 =====");
        List<String> fruits = Arrays.asList("苹果", "香蕉", "橙子");

        // for-each
        System.out.print("for-each: ");
        for (String f : fruits) System.out.print(f + " ");
        System.out.println();

        // Iterator
        System.out.print("Iterator: ");
        Iterator<String> it = fruits.iterator();
        while (it.hasNext()) System.out.print(it.next() + " ");
        System.out.println();
    }
}
