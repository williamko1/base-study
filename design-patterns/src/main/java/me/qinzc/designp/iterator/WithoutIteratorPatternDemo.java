package me.qinzc.designp.iterator;

import java.util.StringJoiner;

/**
 * desc : 不用迭代器模式的实现
 *
 * 如果不用设计模式，直接去遍历一个类中的集合
 * 一旦这个勒种对集合的使用改版了，比如从数组-》map，还有别的可能
 * 如果说代码和业务逻辑很复杂，同时集合类的实现和遍历代码的实现是2个人开发的
 * 简单来说，这种代码不可扩展
 * @author Zane Qin
 * creatTime : 09:38 2021/10/14
 * modifier:
 * modifyTime:
 */
public class WithoutIteratorPatternDemo {

    public static void main(String[] args) {
        Student student1 = new Student("小王");
        Student student2 = new Student("小明");
        Student student3 = new Student("小李");

        Student[] students = new Student[3];
        students[0] = student1;
        students[1] = student2;
        students[2] = student3;
        Classroom classroom = new Classroom();
        classroom.setStudents(students);
        for (Student student : classroom.getStudents()) {
            System.out.println(student);
        }

    }

    private static class Student {
        private String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Student setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
                    .add("name='" + name + "'")
                    .toString();
        }
    }
    private static class Classroom {
        private Student[] students;

        public Student[] getStudents() {
            return students;
        }

        public Classroom setStudents(Student[] students) {
            this.students = students;
            return this;
        }
    }
}

