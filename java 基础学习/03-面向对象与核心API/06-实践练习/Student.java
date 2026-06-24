/**
 * 学生实体类 —— 练习 1 的数据模型，演示「封装」
 * 运行 StudentManager 时会用到本类
 */
public class Student {

    // 实例字段：private，外部不能直接访问
    private String id;      // 学号（主键，创建后不变）
    private String name;
    private int age;
    private double score;

    /** 构造方法：创建学生对象并初始化字段 */
    public Student(String id, String name, int age, double score) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
    }

    // getter
    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getScore() { return score; }

    // setter（学号无 setter，防止修改唯一标识）
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setScore(double score) { this.score = score; }

    /** 便于 System.out.println(student) 时格式化输出 */
    @Override
    public String toString() {
        return String.format("学号:%s 姓名:%s 年龄:%d 成绩:%.1f", id, name, age, score);
    }
}
