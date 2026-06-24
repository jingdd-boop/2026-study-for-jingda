import java.io.*;
import java.nio.file.*;
import java.util.List;

/**
 * Demo 05：IO 与文件
 * 运行：javac IODemo.java && java IODemo
 */
public class IODemo {

    private static final String DEMO_FILE = "demo-output.txt";

    public static void main(String[] args) {
        demoNioWriteAndRead();
        demoBufferedReader();
        demoFilesUtility();
        cleanup();
    }

    static void demoNioWriteAndRead() {
        System.out.println("===== NIO.2 读写 =====");
        Path path = Paths.get(DEMO_FILE);

        try {
            Files.writeString(path, "第一行\n第二行\n第三行\n");
            String content = Files.readString(path);
            System.out.println("文件内容:\n" + content);
        } catch (IOException e) {
            System.err.println("IO 错误: " + e.getMessage());
        }
    }

    static void demoBufferedReader() {
        System.out.println("===== BufferedReader 逐行读取 =====");
        Path path = Paths.get(DEMO_FILE);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            int lineNum = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println("  行 " + lineNum++ + ": " + line);
            }
        } catch (IOException e) {
            System.err.println("读取失败: " + e.getMessage());
        }
    }

    static void demoFilesUtility() {
        System.out.println("\n===== Files 工具方法 =====");
        Path path = Paths.get(DEMO_FILE);

        System.out.println("exists: " + Files.exists(path));
        System.out.println("isRegularFile: " + Files.isRegularFile(path));

        try {
            List<String> lines = Files.readAllLines(path);
            System.out.println("总行数: " + lines.size());
            System.out.println("第一行: " + lines.get(0));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    static void cleanup() {
        try {
            Files.deleteIfExists(Paths.get(DEMO_FILE));
            System.out.println("\n已清理临时文件 " + DEMO_FILE);
        } catch (IOException e) {
            System.err.println("清理失败: " + e.getMessage());
        }
    }
}
