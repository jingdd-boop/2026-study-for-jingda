import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/**
 * 练习 2：CSV 文件解析
 * 运行：javac CSVParser.java && java CSVParser
 */
public class CSVParser {

    public static void main(String[] args) {
        Path path = Paths.get("sample.csv");

        try {
            List<String> lines = Files.readAllLines(path);
            if (lines.isEmpty()) {
                System.out.println("文件为空");
                return;
            }

            String[] headers = parseLine(lines.get(0));
            System.out.println("===== CSV 解析 =====");
            System.out.println("表头: " + Arrays.toString(headers));
            System.out.println("数据行数: " + (lines.size() - 1));

            System.out.println("\n===== 数据明细 =====");
            for (int i = 1; i < lines.size(); i++) {
                String[] fields = parseLine(lines.get(i));
                if (fields.length == 0) continue;
                System.out.printf("  第 %d 行: %s%n", i, formatRow(headers, fields));
            }

            System.out.println("\n===== 按城市统计 =====");
            Map<String, Long> cityCount = new HashMap<>();
            int cityIndex = indexOf(headers, "city");
            for (int i = 1; i < lines.size(); i++) {
                String[] fields = parseLine(lines.get(i));
                if (fields.length > cityIndex) {
                    String city = fields[cityIndex];
                    cityCount.merge(city, 1L, Long::sum);
                }
            }
            cityCount.forEach((city, count) ->
                System.out.println("  " + city + ": " + count + " 人");

        } catch (IOException e) {
            System.err.println("读取失败: " + e.getMessage());
        }
    }

    static String[] parseLine(String line) {
        return line.trim().split(",");
    }

    static int indexOf(String[] headers, String name) {
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].equals(name)) return i;
        }
        return -1;
    }

    static String formatRow(String[] headers, String[] fields) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.min(headers.length, fields.length); i++) {
            if (i > 0) sb.append(", ");
            sb.append(headers[i]).append("=").append(fields[i]);
        }
        return sb.toString();
    }
}
