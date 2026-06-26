import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 练习 3：LRU Cache（最近最少使用缓存）
 *
 * 功能：固定容量缓存，超出时自动淘汰「最久未使用」的条目
 * 操作：get / put 均继承自 LinkedHashMap，无需重写
 *
 * 实现思路：
 * - LinkedHashMap 在 accessOrder=true 时，按访问顺序维护链表
 * - 头部 = 最久未用，尾部 = 最近使用
 * - 重写 removeEldestEntry，在插入新项后判断是否超出容量并删除头部
 *
 * 知识点：泛型、继承、LinkedHashMap、LRU 淘汰策略
 *
 * 运行：javac LRUCache.java && java LRUCache
 */
// 泛型 K=键类型，V=值类型；继承 LinkedHashMap 复用其 get/put 与顺序维护逻辑
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    /** 缓存最大容量，超出时触发淘汰 */
    private final int capacity;

    /**
     * 创建 LRU 缓存
     *
     * @param capacity 最大条目数，必须 > 0
     */
    public LRUCache(int capacity) {
        // super 参数：初始容量、负载因子 0.75f、accessOrder=true（按访问顺序排序）
        // accessOrder=true 时，get/put 会把条目移到链表尾部，头部为最久未用
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    /**
     * 插入新条目后由 LinkedHashMap 回调：返回 true 则删除最久未用的 eldest 条目
     * eldest 即链表头部（accessOrder 模式下最久未被 get/put 访问的键）
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // size() 在 put 之后已包含新条目，故用 > 而非 >=
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCache<String, Integer> cache = new LRUCache<>(3);

        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);
        System.out.println("初始: " + cache);  // {a=1, b=2, c=3}

        cache.get("a");       // 访问 a → a 移到尾部，b 变为最久未用
        cache.put("d", 4);    // 容量满，淘汰 b
        System.out.println("put d 后: " + cache);  // {c=3, a=1, d=4}

        cache.put("e", 5);    // 淘汰 c（a 刚被访问过，c 为最久未用）
        System.out.println("put e 后: " + cache);  // {a=1, d=4, e=5}

        System.out.println("get('a'): " + cache.get("a"));
        System.out.println("最终: " + cache);
    }
}
