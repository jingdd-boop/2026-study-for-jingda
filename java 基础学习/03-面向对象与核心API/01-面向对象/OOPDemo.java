/**
 * Demo 01：面向对象
 * 运行：javac OOPDemo.java && java OOPDemo
 */
public class OOPDemo {

    public static void main(String[] args) {
        System.out.println("===== 封装 =====");
        Person person = new Person("张三", 25);
        person.setAge(26);
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
class Person {
    private String name;
    private int age;
    private static int count = 0;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
        count++;
    }

    String getName() { return name; }
    int getAge() { return age; }

    void setAge(int age) {
        if (age > 0) this.age = age;
    }

    String getInfo() {
        return name + ", " + age + " 岁";
    }

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

class Bird implements Flyable {
    private final String name;
    Bird(String name) { this.name = name; }

    @Override
    public void fly() {
        System.out.println(name + " 在飞翔");
    }
}

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
