package org.example;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private static Map<Integer, Student> directory = new HashMap<>();

    private int pid;
    private String first;
    private String last;

    private Student(int pid, String first, String last) {
        this.pid = pid;
        this.first = first;
        this.last = last;
    }

    public static Student create(int pid, String first, String last) {
        if (!directory.containsKey(pid)) {
            directory.put(pid, new Student(pid, first, last));
        }
        return directory.get(pid);
    }

    public static void main(String[] args) {
        Student s1 = Student.create(1, "Leon", "Aaron");
        Student s2 = Student.create(2, "an", "ds");
        Student s3 = Student.create(1, "sd", "sdf");
    }
}
