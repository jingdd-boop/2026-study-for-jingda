import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Demo 07：日期时间（Java 8+ API）
 * 运行：javac DateTimeDemo.java && java DateTimeDemo
 */
public class DateTimeDemo {

    public static void main(String[] args) {
        System.out.println("===== 获取当前时间 =====");

        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.now();

        System.out.println("今天: " + today);
        System.out.println("现在: " + now);
        System.out.println("日期时间: " + dateTime);

        System.out.println("\n===== 创建指定日期 =====");

        LocalDate birthday = LocalDate.of(2000, 1, 15);
        LocalTime meeting = LocalTime.of(14, 30, 0);
        LocalDateTime event = LocalDateTime.of(2026, 6, 24, 14, 30);

        System.out.println("生日: " + birthday);
        System.out.println("会议: " + meeting);
        System.out.println("事件: " + event);

        System.out.println("\n===== 日期运算 =====");

        LocalDate nextWeek = today.plusDays(7);
        LocalDate lastMonth = today.minusMonths(1);
        LocalDate nextYear = today.plusYears(1);

        System.out.println("7 天后: " + nextWeek);
        System.out.println("1 个月前: " + lastMonth);
        System.out.println("1 年后: " + nextYear);

        // 计算两个日期之间的天数
        long daysBetween = ChronoUnit.DAYS.between(birthday, today);
        System.out.println("从出生到今天: " + daysBetween + " 天");

        System.out.println("\n===== 格式化 =====");

        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter fullFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println("日期格式: " + today.format(dateFmt));
        System.out.println("时间格式: " + now.format(timeFmt));
        System.out.println("完整格式: " + dateTime.format(fullFmt));

        System.out.println("\n===== 解析字符串 =====");

        String dateStr = "2026-06-24";
        LocalDate parsed = LocalDate.parse(dateStr); // 默认 ISO 格式
        System.out.println("解析 \"" + dateStr + "\" → " + parsed);

        String customStr = "2026年06月24日";
        LocalDate customParsed = LocalDate.parse(customStr, dateFmt);
        System.out.println("解析 \"" + customStr + "\" → " + customParsed);

        System.out.println("\n===== 获取日期各部分 =====");

        System.out.println("年: " + today.getYear());
        System.out.println("月: " + today.getMonthValue()); // 1~12，不是 0~11
        System.out.println("日: " + today.getDayOfMonth());
        System.out.println("星期: " + today.getDayOfWeek()); // MONDAY, TUESDAY...
    }
}
