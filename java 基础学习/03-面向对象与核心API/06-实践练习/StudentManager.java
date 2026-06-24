import java.util.*;

/**
 * 练习 1：学生管理系统（控制台版）
 * 运行：javac Student.java StudentManager.java && java StudentManager
 */
public class StudentManager {

    private final Map<String, Student> students = new HashMap<>();

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        // 预置示例数据
        manager.add(new Student("001", "张三", 20, 88.5));
        manager.add(new Student("002", "李四", 21, 92.0));

        while (true) {
            printMenu();
            System.out.print("请选择: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    manager.addInteractive(scanner);
                    break;
                case "2":
                    manager.delete(scanner);
                    break;
                case "3":
                    manager.update(scanner);
                    break;
                case "4":
                    manager.find(scanner);
                    break;
                case "5":
                    manager.listAll();
                    break;
                case "0":
                    System.out.println("再见!");
                    scanner.close();
                    return;
                default:
                    System.out.println("无效选项，请重试");
            }
        }
    }

    static void printMenu() {
        System.out.println("\n===== 学生管理系统 =====");
        System.out.println("1. 添加学生");
        System.out.println("2. 删除学生");
        System.out.println("3. 修改学生");
        System.out.println("4. 按学号查询");
        System.out.println("5. 列出全部");
        System.out.println("0. 退出");
    }

    void add(Student student) {
        if (students.containsKey(student.getId())) {
            System.out.println("学号已存在: " + student.getId());
            return;
        }
        students.put(student.getId(), student);
    }

    void addInteractive(Scanner scanner) {
        System.out.print("学号: ");
        String id = scanner.nextLine().trim();
        System.out.print("姓名: ");
        String name = scanner.nextLine().trim();
        System.out.print("年龄: ");
        int age = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("成绩: ");
        double score = Double.parseDouble(scanner.nextLine().trim());
        add(new Student(id, name, age, score));
        System.out.println("添加成功");
    }

    void delete(Scanner scanner) {
        System.out.print("输入要删除的学号: ");
        String id = scanner.nextLine().trim();
        Student removed = students.remove(id);
        if (removed != null) {
            System.out.println("已删除: " + removed.getName());
        } else {
            System.out.println("未找到学号: " + id);
        }
    }

    void update(Scanner scanner) {
        System.out.print("输入要修改的学号: ");
        String id = scanner.nextLine().trim();
        Student student = students.get(id);
        if (student == null) {
            System.out.println("未找到学号: " + id);
            return;
        }
        System.out.print("新姓名 (回车跳过): ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) student.setName(name);
        System.out.print("新年龄 (回车跳过): ");
        String ageStr = scanner.nextLine().trim();
        if (!ageStr.isEmpty()) student.setAge(Integer.parseInt(ageStr));
        System.out.print("新成绩 (回车跳过): ");
        String scoreStr = scanner.nextLine().trim();
        if (!scoreStr.isEmpty()) student.setScore(Double.parseDouble(scoreStr));
        System.out.println("修改成功: " + student);
    }

    void find(Scanner scanner) {
        System.out.print("输入学号: ");
        String id = scanner.nextLine().trim();
        Student student = students.get(id);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("未找到");
        }
    }

    void listAll() {
        if (students.isEmpty()) {
            System.out.println("暂无学生");
            return;
        }
        System.out.println("共 " + students.size() + " 名学生:");
        for (Student s : students.values()) {
            System.out.println("  " + s);
        }
    }
}
