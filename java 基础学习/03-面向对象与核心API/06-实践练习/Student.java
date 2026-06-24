/**
 * 学生实体类
 */
public class Student {
    private String id;
    private String name;
    private int age;
    private double score;

    public Student(String id, String name, int age, double score) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getScore() { return score; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setScore(double score) { this.score = score; }

    @Override
    public String toString() {
        return String.format("学号:%s 姓名:%s 年龄:%d 成绩:%.1f", id, name, age, score);
    }
}
