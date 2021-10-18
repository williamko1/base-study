package me.qinzc.designp.iterator;


import java.util.Arrays;
import java.util.StringJoiner;

/**
 * desc : 迭代器模式的实现
 *
 * @author Zane Qin
 * creatTime : 09:47 2021/10/14
 * modifier:
 * modifyTime:
 */
public class IteratorPatternDemo {

    public static void main(String[] args) {
        Student student1 = new Student("小王");
        Student student2 = new Student("小明");
        Student student3 = new Student("小李");

        Student[] students = new Student[3];
        students[0] = student1;
        students[1] = student2;
        students[2] = student3;
        Classroom classroom = new Classroom(3);
        classroom.addStudent(student1);
        classroom.addStudent(student2);
        classroom.addStudent(student3);
        Iterator<Student> iterator = classroom.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            System.out.println(student);

        }
        Arrays.stream(students).iterator();

    }

    // 相当于jdk Iterable 接口 提供一个获取迭代器的接口
    public interface Aggregate {
        public abstract Iterator iterator();
    }

    // 迭代器
    public interface Iterator<T> {
        
        public abstract boolean hasNext();

        public abstract T next();
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

        private int last = 0;

        public Classroom(int size) {
            this.students = new Student[size];
        }

        public Student getStudent(int index) {
            return students[index];
        }

        public void addStudent(Student student) {
            this.students[last] = student;
            last++;
        }

        public int getLength() {
            return last;
        }

        public Iterator iterator() {
            return new ClassroomIterator(this);
        }
    }

    /**
     * 教室迭代器
     */
    private static class ClassroomIterator implements Iterator<Student>{

        private Classroom classroom;

        private int index;

        public ClassroomIterator(Classroom classroom) {
            this.classroom = classroom;
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < classroom.getLength();
        }

        @Override
        public Student next() {
            Student student = classroom.getStudent(index);
            index++;
            return student;
        }
    }

}
