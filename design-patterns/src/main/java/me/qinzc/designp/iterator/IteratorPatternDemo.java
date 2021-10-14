package me.qinzc.designp.iterator;


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

    public interface Aggregate {
        public abstract Iterator iterator();
    }

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

        public Student[] getStudents() {
            return students;
        }

        public Classroom setStudents(Student[] students) {
            this.students = students;
            return this;
        }

        public int getLength() {
            return students.length;
        }
    }

    private static class ClassroomIterator implements Iterator<Student>{

        private Classroom classroom;

        private int index;

        public ClassroomIterator(Classroom classroom) {
            this.classroom = classroom;
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < classroom.getLength();
        }

        @Override
        public Student next() {
            Student student = classroom.getStudents()[index];
            index++;
            return student;
        }
    }

}
