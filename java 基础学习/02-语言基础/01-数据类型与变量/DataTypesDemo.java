/**
 * Demo 01：数据类型与变量
 * 运行：javac DataTypesDemo.java && java DataTypesDemo
 */
public class DataTypesDemo {

    public static void main(String[] args) {
        System.out.println("===== 基本数据类型 =====");

        // 整数类型
        byte smallNum = 127;          // -128 ~ 127
        short mediumNum = 32000;
        int age = 25;                 // 最常用
        long bigNum = 9_999_999_999L; // 大数字用 L 后缀，下划线提高可读性

        // 浮点类型
        float price = 19.99f;         // float 字面量必须加 f
        double pi = 3.141592653589793; // 默认小数是 double

        // 字符和布尔
        char grade = 'A';             // 单引号，只能一个字符
        boolean isActive = true;

        System.out.println("age = " + age);
        System.out.println("price = " + price);
        System.out.println("pi = " + pi);
        System.out.println("grade = " + grade);
        System.out.println("isActive = " + isActive);

        System.out.println("\n===== 引用类型：String =====");

        String name = "张三";          // String 是类，不是基本类型
        String greeting = "Hello, " + name; // 字符串拼接用 +
        System.out.println(greeting);

        System.out.println("\n===== 变量 vs 常量 =====");

        int count = 0;
        count = 10;                   // 变量可以重新赋值
        System.out.println("count = " + count);

        final int MAX_SIZE = 100;     // final = 常量，只能赋一次值
        // MAX_SIZE = 200;            // 编译报错！常量不能修改
        System.out.println("MAX_SIZE = " + MAX_SIZE);

        System.out.println("\n===== 类型转换 =====");

        // 自动提升：int → double，不会丢数据
        int intVal = 10;
        double doubleVal = intVal;
        System.out.println("自动提升: int " + intVal + " → double " + doubleVal);

        // 强制转换：double → int，小数部分被截断
        double d = 9.99;
        int truncated = (int) d;      // 必须写 (int)，结果是 9 不是 10
        System.out.println("强制转换: double " + d + " → int " + truncated);

        System.out.println("\n===== 默认值（成员变量才有，局部变量必须初始化）=====");
        System.out.println("局部变量使用前必须赋值，否则编译报错");
    }
}
