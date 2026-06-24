/**
 * Demo 06：String 与包装类
 * 运行：javac StringAndWrapperDemo.java && java StringAndWrapperDemo
 */
public class StringAndWrapperDemo {

    public static void main(String[] args) {
        System.out.println("===== String 基础 =====");

        String greeting = "Hello";
        String name = "Java";

        // 拼接
        String full = greeting + ", " + name + "!";
        System.out.println(full);

        // 长度（注意是方法 length()，不是属性）
        System.out.println("长度: " + full.length());

        System.out.println("\n===== == vs equals（重要！）=====");

        String s1 = "hello";
        String s2 = "hello";
        String s3 = new String("hello");

        System.out.println("s1 == s2: " + (s1 == s2));       // true（字符串常量池）
        System.out.println("s1 == s3: " + (s1 == s3));       // false（不同对象）
        System.out.println("s1.equals(s3): " + s1.equals(s3)); // true（内容相同）

        // 结论：比较内容永远用 .equals()

        System.out.println("\n===== 常用 String 方法 =====");

        String text = "  Hello, Java World  ";

        System.out.println("trim(): [" + text.trim() + "]");
        System.out.println("toUpperCase(): " + text.trim().toUpperCase());
        System.out.println("substring(2, 7): " + text.trim().substring(2, 7));
        System.out.println("indexOf(\"Java\"): " + text.indexOf("Java"));
        System.out.println("contains(\"World\"): " + text.contains("World"));
        System.out.println("replace(\"Java\", \"Spring\"): "
                + text.trim().replace("Java", "Spring"));

        // split 分割
        String csv = "apple,banana,orange";
        String[] fruits = csv.split(",");
        System.out.print("split: ");
        for (String f : fruits) {
            System.out.print(f + " ");
        }
        System.out.println();

        System.out.println("\n===== StringBuilder（高效拼接）=====");

        // 大量拼接用 StringBuilder，不要用 +
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            sb.append(i);
            if (i < 5) sb.append(", ");
        }
        System.out.println("StringBuilder: " + sb.toString());

        System.out.println("\n===== 包装类 & 自动装箱拆箱 =====");

        // 基本类型
        int primitive = 42;

        // 自动装箱：int → Integer
        Integer wrapper = primitive;
        System.out.println("Integer 对象: " + wrapper);

        // 自动拆箱：Integer → int
        int back = wrapper;
        System.out.println("拆箱回 int: " + back);

        // 包装类常用方法
        String numStr = "123";
        int parsed = Integer.parseInt(numStr);  // 类似 JS 的 parseInt
        System.out.println("parseInt(\"123\") = " + parsed);

        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
        System.out.println("Integer.MIN_VALUE = " + Integer.MIN_VALUE);

        // 注意：包装类比较也用 equals，不要用 ==
        Integer a = 128;
        Integer b = 128;
        System.out.println("Integer 128 == 128: " + (a == b));         // false（超出缓存范围）
        System.out.println("Integer 128 equals 128: " + a.equals(b));  // true
    }
}
