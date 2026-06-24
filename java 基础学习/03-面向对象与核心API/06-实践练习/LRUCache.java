import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 练习 3：LRU Cache（最近最少使用缓存）
 * 运行：javac LRUCache.java && java LRUCache
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private final int capacity;

    public LRUCache(int capacity) {
        // accessOrder=true：按访问顺序排序，最久未用的在头部
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCache<String, Integer> cache = new LRUCache<>(3);

        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        System.out.println("初始: " + cache);

        cache.get("a");  // 访问 a，b 变成最久未用
        cache.put("d", 4);  // 容量满，淘汰 b
        System.out.println("put d 后: " + cache);

        cache.put("e", 5);  // 淘汰 c（a 刚被访问过）
        System.out.println("put e 后: " + cache);

        System.out.println("get('a'): " + cache.get("a"));
        System.out.println("最终: " + cache);
    }
}
