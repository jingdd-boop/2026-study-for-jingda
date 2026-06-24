import java.io.IOException;
import java.nio.file.*;   // NIO.2：Path、Paths、Files
import java.util.*;

/**
 * 练习 2：CSV 文件解析
 *
 * 功能：读取 sample.csv，解析表头与数据行，按 city 列统计人数
 * 知识点：NIO.2 读文件、String.split、Map.merge、lambda
 *
 * 运行：javac CSVParser.java && java CSVParser
 * 注意：需在 sample.csv 同目录下执行
 */
public class CSVParser {

    public static void main(String[] args) {
        // Paths.get 构造文件路径；相对路径相对于当前工作目录
        Path path = Paths.get("sample.csv");

        try {
            // Files.readAllLines 一次性读入全部行，返回 List<String>
            List<String> lines = Files.readAllLines(path);
            if (lines.isEmpty()) {
                System.out.println("文件为空");
                return;
            }

            // 第 0 行是表头，按逗号拆成列名数组
            String[] headers = parseLine(lines.get(0));
            System.out.println("===== CSV 解析 =====");
            System.out.println("表头: " + Arrays.toString(headers));
            System.out.println("数据行数: " + (lines.size() - 1));  // 减去表头行

            // 从第 1 行起遍历数据行，格式化输出「列名=值」
            System.out.println("\n===== 数据明细 =====");
            for (int i = 1; i < lines.size(); i++) {
                String[] fields = parseLine(lines.get(i));
                if (fields.length == 0) continue;  // 跳过空行
                System.out.printf("  第 %d 行: %s%n", i, formatRow(headers, fields));
            }

            // 用 HashMap 统计每个 city 出现次数
            System.out.println("\n===== 按城市统计 =====");
            Map<String, Long> cityCount = new HashMap<>();
            int cityIndex = indexOf(headers, "city");  // 找到 city 列的下标
            for (int i = 1; i < lines.size(); i++) {
                String[] fields = parseLine(lines.get(i));
                if (fields.length > cityIndex) {
                    String city = fields[cityIndex];
                    // merge：key 不存在则放入 1，已存在则用 Long::sum 累加
                    cityCount.merge(city, 1L, Long::sum);
                }
            }
            // forEach + lambda：遍历 Map 并打印；单行 lambda 末尾不要加分号
            cityCount.forEach((city, count) ->
                System.out.println("  " + city + ": " + count + " 人")
            );

        } catch (IOException e) {
            // 文件不存在、无权限等 IO 异常在此捕获
            System.err.println("读取失败: " + e.getMessage());
        }
    }

    /**
     * 解析一行 CSV：去首尾空白后按逗号分割
     * 简化版实现，未处理字段内含逗号的情况（如 "北京,朝阳"）
     */
    static String[] parseLine(String line) {
        return line.trim().split(",");
    }

    /**
     * 在表头数组中查找列名对应的下标，找不到返回 -1
     */
    static int indexOf(String[] headers, String name) {
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].equals(name)) return i;
        }
        return -1;
    }

    /**
     * 将一行数据格式化为「name=张三, age=25, city=北京」便于阅读
     */
    static String formatRow(String[] headers, String[] fields) {
        StringBuilder sb = new StringBuilder();
        // Math.min 防止列数不一致时越界
        for (int i = 0; i < Math.min(headers.length, fields.length); i++) {
            if (i > 0) sb.append(", ");
            sb.append(headers[i]).append("=").append(fields[i]);
        }
        return sb.toString();
    }
}
