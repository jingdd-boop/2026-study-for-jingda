import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Demo 04：泛型
 * 运行：javac GenericsDemo.java && java GenericsDemo
 */
public class GenericsDemo {

    public static void main(String[] args) {
        System.out.println("===== 泛型类 =====");
        Box<String> strBox = new Box<>();
        strBox.set("Hello");
        System.out.println("String Box: " + strBox.get());

        Box<Integer> intBox = new Box<>();
        intBox.set(42);
        System.out.println("Integer Box: " + intBox.get());

        System.out.println("\n===== 泛型方法 =====");
        List<String> names = Arrays.asList("Alice", "Bob", "Carol");
        System.out.println("第一个: " + getFirst(names));
        System.out.println("最后一个: " + getLast(names));

        System.out.println("\n===== 通配符 ? extends =====");
        List<Integer> ints = Arrays.asList(1, 2, 3);
        List<Double> doubles = Arrays.asList(1.1, 2.2);
        System.out.println("int 总和: " + sumNumbers(ints));
        System.out.println("double 总和: " + sumNumbers(doubles));

        System.out.println("\n===== 通配符 ? super =====");
        List<Object> dest = new ArrayList<>();
        copyNumbers(ints, dest);
        System.out.println("复制到 Object 列表: " + dest);
    }

    static <T> T getFirst(List<T> list) {
        return list.isEmpty() ? null : list.get(0);
    }

    static <T> T getLast(List<T> list) {
        return list.isEmpty() ? null : list.get(list.size() - 1);
    }

    // 上界：只能读 Number，不能 add（除了 null）
    static double sumNumbers(List<? extends Number> list) {
        double sum = 0;
        for (Number n : list) {
            sum += n.doubleValue();
        }
        return sum;
    }

    // 下界：可以 add Integer 及其子类
    static void copyNumbers(List<? extends Number> src, List<? super Integer> dest) {
        for (Number n : src) {
            dest.add(n.intValue());
        }
    }
}

class Box<T> {
    private T value;

    void set(T value) { this.value = value; }
    T get() { return value; }
}
