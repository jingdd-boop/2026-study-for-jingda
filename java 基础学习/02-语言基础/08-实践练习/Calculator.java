import java.util.Scanner;

/**
 * 练习 1：控制台计算器
 * 运行：javac Calculator.java && java Calculator
 */
public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== 简易计算器 =====");
        System.out.print("输入第一个数字: ");
        double num1 = scanner.nextDouble();

        System.out.print("输入运算符 (+ - * /): ");
        char operator = scanner.next().charAt(0);

        System.out.print("输入第二个数字: ");
        double num2 = scanner.nextDouble();

        double result = calculate(num1, operator, num2);

        if (Double.isNaN(result)) {
            System.out.println("错误：除数不能为 0");
        } else {
            System.out.printf("结果: %.2f %c %.2f = %.2f%n", num1, operator, num2, result);
        }

        scanner.close();
    }

    static double calculate(double a, char op, double b) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) return Double.NaN;
                return a / b;
            default:
                System.out.println("未知运算符: " + op);
                return Double.NaN;
        }
    }
}
