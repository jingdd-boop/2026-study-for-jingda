import java.util.Random;
import java.util.Scanner;

/**
 * 练习 2：猜数字游戏
 * 运行：javac GuessNumber.java && java GuessNumber
 */
public class GuessNumber {

    public static void main(String[] args) {
        Random random = new Random();
        int target = random.nextInt(100) + 1; // 1~100 的随机数

        Scanner scanner = new Scanner(System.in);
        int attempts = 0;
        int maxAttempts = 7;

        System.out.println("===== 猜数字游戏 =====");
        System.out.println("我想了一个 1~100 之间的数字，你有 " + maxAttempts + " 次机会！");

        while (attempts < maxAttempts) {
            System.out.print("第 " + (attempts + 1) + " 次猜测: ");
            int guess = scanner.nextInt();
            attempts++;

            if (guess == target) {
                System.out.println("恭喜！猜对了！用了 " + attempts + " 次。");
                scanner.close();
                return;
            } else if (guess > target) {
                System.out.println("太大了！");
            } else {
                System.out.println("太小了！");
            }

            System.out.println("还剩 " + (maxAttempts - attempts) + " 次机会");
        }

        System.out.println("游戏结束！正确答案是: " + target);
        scanner.close();
    }
}
