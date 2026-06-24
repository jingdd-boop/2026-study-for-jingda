/**
 * Demo 03：流程控制
 * 运行：javac ControlFlowDemo.java && java ControlFlowDemo
 */
public class ControlFlowDemo {

    public static void main(String[] args) {
        System.out.println("===== if / else =====");

        int score = 85;

        if (score >= 90) {
            System.out.println("优秀");
        } else if (score >= 80) {
            System.out.println("良好");  // 会走这里
        } else if (score >= 60) {
            System.out.println("及格");
        } else {
            System.out.println("不及格");
        }

        System.out.println("\n===== switch（传统写法）=====");

        int day = 3;
        switch (day) {
            case 1:
                System.out.println("周一");
                break;  // 必须 break，否则穿透到 case 2
            case 2:
                System.out.println("周二");
                break;
            case 3:
                System.out.println("周三");
                break;
            default:
                System.out.println("其他");
        }

        System.out.println("\n===== switch 表达式（Java 14+，推荐）=====");

        String dayName = switch (day) {
            case 1 -> "周一";
            case 2 -> "周二";
            case 3 -> "周三";
            case 4, 5 -> "工作日";  // 多个 case 合并
            default -> "周末或未知";
        };
        System.out.println("day=" + day + " → " + dayName);

        System.out.println("\n===== for 循环 =====");

        System.out.print("0~4: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("\n===== while 循环 =====");

        int count = 3;
        System.out.print("倒计时: ");
        while (count > 0) {
            System.out.print(count + " ");
            count--;
        }
        System.out.println("发射!");

        System.out.println("\n===== do-while 循环 =====");

        int num = 0;
        do {
            System.out.println("至少执行一次, num=" + num);
            num++;
        } while (num < 1); // 条件 false，但已经执行过了

        System.out.println("\n===== 增强 for（foreach）=====");

        String[] fruits = {"苹果", "香蕉", "橙子"};
        for (String fruit : fruits) {
            System.out.println("- " + fruit);
        }

        System.out.println("\n===== break 和 continue =====");

        System.out.print("1~10 中跳过 5，到 8 停止: ");
        for (int i = 1; i <= 10; i++) {
            if (i == 5) continue; // 跳过 5
            if (i == 8) break;    // 到 8 停止
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
