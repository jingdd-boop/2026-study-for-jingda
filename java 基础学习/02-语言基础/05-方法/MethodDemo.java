/**
 * Demo 05：方法
 * 运行：javac MethodDemo.java && java MethodDemo
 */
public class MethodDemo {

    public static void main(String[] args) {
        System.out.println("===== 基本方法调用 =====");

        greet("张三");
        int result = add(3, 5);
        System.out.println("3 + 5 = " + result);

        System.out.println("\n===== 方法重载 =====");

        System.out.println("add(1, 2) = " + add(1, 2));
        System.out.println("add(1.5, 2.5) = " + add(1.5, 2.5));
        System.out.println("add(1, 2, 3) = " + add(1, 2, 3));

        System.out.println("\n===== 可变参数 =====");

        System.out.println("sum(1, 2, 3) = " + sum(1, 2, 3));
        System.out.println("sum(10, 20, 30, 40) = " + sum(10, 20, 30, 40));

        System.out.println("\n===== 值传递演示 =====");

        int x = 10;
        changeValue(x);
        System.out.println("基本类型传递后 x 仍然是: " + x); // 10，没变

        int[] arr = {1, 2, 3};
        changeArray(arr);
        System.out.println("引用类型传递后 arr[0] = " + arr[0]); // 100，变了！
        // 原因：引用副本指向同一数组对象，修改对象内容会生效
    }

    // 无返回值的方法
    static void greet(String name) {
        System.out.println("你好, " + name + "!");
    }

    // 有返回值的方法
    static int add(int a, int b) {
        return a + b;
    }

    // 重载 1：两个 int
    static int add(int a, int b, int c) {
        return a + b + c;
    }

    // 重载 2：两个 double
    static double add(double a, double b) {
        return a + b;
    }

    // 可变参数：int... 等价于 int[]，但调用更方便
    static int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) {
            total += n;
        }
        return total;
    }

    // 基本类型值传递：修改副本，不影响原变量
    static void changeValue(int num) {
        num = 999;
    }

    // 引用类型值传递：引用副本指向同一对象，修改对象内容会生效
    static void changeArray(int[] array) {
        array[0] = 100;
    }
}
