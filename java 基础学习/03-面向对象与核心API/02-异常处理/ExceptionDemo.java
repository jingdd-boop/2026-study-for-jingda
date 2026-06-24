import java.io.FileReader;
import java.io.IOException;

/**
 * Demo 02：异常处理
 * 运行：javac ExceptionDemo.java && java ExceptionDemo
 */
public class ExceptionDemo {

    public static void main(String[] args) {
        System.out.println("===== try-catch =====");
        try {
            int result = divide(10, 0);
            System.out.println("结果: " + result);
        } catch (ArithmeticException e) {
            System.out.println("捕获算术异常: " + e.getMessage());
        }

        System.out.println("\n===== 多 catch =====");
        try {
            String s = null;
            System.out.println(s.length());
        } catch (NullPointerException e) {
            System.out.println("空指针: " + e.getClass().getSimpleName());
        }

        System.out.println("\n===== finally =====");
        try {
            System.out.println("try 块执行");
            throw new RuntimeException("模拟异常");
        } catch (RuntimeException e) {
            System.out.println("catch: " + e.getMessage());
        } finally {
            System.out.println("finally 总会执行");
        }

        System.out.println("\n===== 自定义异常 =====");
        try {
            validateAge(-1);
        } catch (InvalidAgeException e) {
            System.out.println("业务异常: " + e.getMessage());
        }

        System.out.println("\n===== 受检异常 throws =====");
        try {
            readFileContent("不存在的文件.txt");
        } catch (IOException e) {
            System.out.println("IO 异常（受检）: " + e.getMessage());
        }

        System.out.println("\n===== try-with-resources =====");
        demoTryWithResources();
    }

    static int divide(int a, int b) {
        return a / b;  // b=0 时抛 ArithmeticException（非受检）
    }

    static void validateAge(int age) {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException("年龄无效: " + age);
        }
        System.out.println("年龄合法: " + age);
    }

    static void readFileContent(String path) throws IOException {
        // 受检异常必须 throws 或在内部 try-catch
        try (FileReader reader = new FileReader(path)) {
            // 读取文件...
        }
    }

    static void demoTryWithResources() {
        try (AutoCloseResource resource = new AutoCloseResource()) {
            resource.doWork();
        }
        System.out.println("资源已自动关闭");
    }
}

class InvalidAgeException extends RuntimeException {
    InvalidAgeException(String message) {
        super(message);
    }
}

class AutoCloseResource implements AutoCloseable {
    void doWork() {
        System.out.println("资源工作中...");
    }

    @Override
    public void close() {
        System.out.println("AutoCloseable.close() 被调用");
    }
}
