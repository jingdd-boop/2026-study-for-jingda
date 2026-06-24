/**
 * Demo 02：运算符
 * 运行：javac OperatorsDemo.java && java OperatorsDemo
 */
public class OperatorsDemo {

    public static void main(String[] args) {
        System.out.println("===== 算术运算符 =====");

        int a = 10, b = 3;
        System.out.println("a + b = " + (a + b));  // 13
        System.out.println("a - b = " + (a - b));  // 7
        System.out.println("a * b = " + (a * b));  // 30
        System.out.println("a / b = " + (a / b));  // 3（整数除法，截断小数！）
        System.out.println("a % b = " + (a % b));  // 1（取余）

        // 想要小数结果，至少一个操作数是 double
        System.out.println("10.0 / 3 = " + (10.0 / 3)); // 3.333...

        System.out.println("\n===== 自增自减 =====");

        int i = 5;
        System.out.println("i++ = " + (i++)); // 先输出 5，再变成 6
        System.out.println("现在 i = " + i);  // 6

        int j = 5;
        System.out.println("++j = " + (++j)); // 先变成 6，再输出 6

        System.out.println("\n===== 比较运算符 =====");

        System.out.println("10 > 3  → " + (10 > 3));
        System.out.println("10 == 10 → " + (10 == 10));
        System.out.println("\"abc\" == \"abc\" → " + ("abc" == "abc")); // 字符串比较见 Demo 06

        System.out.println("\n===== 逻辑运算符 =====");

        boolean x = true, y = false;
        System.out.println("x && y = " + (x && y)); // false
        System.out.println("x || y = " + (x || y)); // true
        System.out.println("!x = " + (!x));         // false

        // 短路求值：&& 左边 false 时，右边不会执行
        int n = 0;
        if (n != 0 && 100 / n > 10) { // 不会抛除零异常
            System.out.println("不会执行到这里");
        }
        System.out.println("短路求值避免了除零异常");

        System.out.println("\n===== 赋值运算符 =====");

        int score = 80;
        score += 10;  // 等价于 score = score + 10
        System.out.println("score += 10 → " + score); // 90

        System.out.println("\n===== 三元运算符 =====");

        int examScore = 75;
        String pass = examScore >= 60 ? "及格" : "不及格";
        System.out.println("分数 " + examScore + " → " + pass);

        // 嵌套三元（可读性差，实际项目中少用）
        String level = examScore >= 90 ? "优秀" : examScore >= 60 ? "及格" : "不及格";
        System.out.println("等级 → " + level);
    }
}
