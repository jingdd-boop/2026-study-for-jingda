/**
 * Demo 01：面向对象
 * 运行：javac OOPDemo.java && java OOPDemo
 */
public class OOPDemo {

    public static void main(String[] args) {
        System.out.println("===== 封装 =====");
        Person person = new Person("张三", 25);  // 构造器创建对象
        person.setAge(26);  // 封装：通过 setter 修改 private 字段，内部可校验
        System.out.println(person.getInfo());

        System.out.println("\n===== 继承与多态 =====");
        Animal dog = new Dog("旺财");
        Animal cat = new Cat("咪咪");
        dog.speak();  // 多态：运行时绑定子类实现
        cat.speak();

        System.out.println("\n===== 抽象类 =====");
        Shape circle = new Circle(3);
        Shape rect = new Rectangle(4, 5);
        System.out.printf("圆面积: %.2f%n", circle.area());
        System.out.printf("矩形面积: %.2f%n", rect.area());

        System.out.println("\n===== 接口 =====");
        Flyable bird = new Bird("小黄");
        Swimmable duck = new Duck("唐老鸭");
        bird.fly();
        duck.swim();
        // Duck 同时实现 Flyable 和 Swimmable
        ((Flyable) duck).fly();

        System.out.println("\n===== static =====");
        System.out.println("计数: " + Person.getCount());
    }
}

// --- 封装 ---
// Person 类：演示「封装」—— 字段 private，对外通过方法访问
class Person {
    // 实例字段：每个 Person 对象各有一份
    private String name;   // private：类外不能直接访问，只能走 getter/setter
    private int age;

    // 静态字段：属于类本身，所有 Person 对象共享这一份
    private static int count = 0;

    // 构造方法：new Person("张三", 25) 时自动调用，用来初始化对象
    Person(String name, int age) {
        this.name = name;  // this.name 指当前对象的字段；参数 name 是传入的值
        this.age = age;
        count++;           // 每创建一个 Person，计数 +1
    }

    // getter：读取 private 字段（只读访问）
    String getName() { return name; }
    int getAge() { return age; }

    // setter：修改 private 字段，可在方法内做校验（封装的好处）
    void setAge(int age) {
        if (age > 0) this.age = age;  // 参数 age 与字段 this.age 同名，必须用 this 区分
    }

    // 实例方法：把多个字段拼成可读字符串
    String getInfo() {
        return name + ", " + age + " 岁";
    }

    // 静态方法：通过 Person.getCount() 调用，不需要创建对象
    static int getCount() { return count; }
}

// --- 继承与多态 ---
abstract class Animal {
    protected String name;

    Animal(String name) { this.name = name; }

    abstract void speak();
}

class Dog extends Animal {
    Dog(String name) { super(name); }

    @Override
    void speak() {
        System.out.println(name + ": 汪汪!");
    }
}

class Cat extends Animal {
    Cat(String name) { super(name); }

    @Override
    void speak() {
        System.out.println(name + ": 喵喵!");
    }
}

// --- 抽象类 ---
abstract class Shape {
    abstract double area();
}

class Circle extends Shape {
    private final double radius;

    Circle(double radius) { this.radius = radius; }

    @Override
    double area() { return Math.PI * radius * radius; }
}

class Rectangle extends Shape {
    private final double width, height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    double area() { return width * height; }
}

// --- 接口 ---
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

// 实现接口 Flyable：承诺提供 fly() 方法（类只能单继承，但可实现多个接口）
class Bird implements Flyable {
    private final String name;  // final：构造后不可再改
    Bird(String name) { this.name = name; }

    @Override  // 实现接口中声明的方法，必须加 public（接口方法默认 public）
    public void fly() {
        System.out.println(name + " 在飞翔");
    }
}

// 同时实现两个接口：既能飞又能游（Java 支持多接口实现）
class Duck implements Flyable, Swimmable {
    private final String name;
    Duck(String name) { this.name = name; }

    @Override
    public void fly() {
        System.out.println(name + " 扑腾着飞");
    }

    @Override
    public void swim() {
        System.out.println(name + " 在游泳");
    }
}
