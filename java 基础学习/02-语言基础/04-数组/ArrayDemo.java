/**
 * Demo 04：数组
 * 运行：javac ArrayDemo.java && java ArrayDemo
 */
public class ArrayDemo {

    public static void main(String[] args) {
        System.out.println("===== 一维数组 =====");

        // 方式 1：声明时直接初始化
        int[] scores = {90, 85, 78, 92, 88};

        // 方式 2：指定长度后赋值
        String[] names = new String[3];
        names[0] = "Alice";
        names[1] = "Bob";
        names[2] = "Charlie";

        System.out.println("scores 长度: " + scores.length);
        System.out.println("scores[0] = " + scores[0]);
        System.out.println("names[1] = " + names[1]);

        System.out.println("\n===== 遍历数组 =====");

        // 普通 for
        System.out.print("scores (for): ");
        for (int i = 0; i < scores.length; i++) {
            System.out.print(scores[i] + " ");
        }
        System.out.println();

        // 增强 for
        System.out.print("scores (foreach): ");
        for (int score : scores) {
            System.out.print(score + " ");
        }
        System.out.println();

        System.out.println("\n===== 数组常用操作 =====");

        // 求和
        int sum = 0;
        for (int s : scores) {
            sum += s;
        }
        double avg = (double) sum / scores.length;
        System.out.println("总分: " + sum + ", 平均分: " + avg);

        // 找最大值
        int max = scores[0];
        for (int s : scores) {
            if (s > max) max = s;
        }
        System.out.println("最高分: " + max);

        System.out.println("\n===== 多维数组 =====");

        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("matrix[1][2] = " + matrix[1][2]); // 第2行第3列 = 6

        System.out.println("整个矩阵:");
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + "\t");
            }
            System.out.println();
        }

        System.out.println("\n===== 默认值 =====");

        // 未赋值的 int 数组元素默认是 0
        int[] defaults = new int[3];
        System.out.println("int 默认值: " + defaults[0]); // 0

        // 未赋值的 String 数组元素默认是 null
        String[] strDefaults = new String[2];
        System.out.println("String 默认值: " + strDefaults[0]); // null
    }
}
