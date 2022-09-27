package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Student_ListTest {

    private List<Student> studentArrayList;

    public Student_ListTest() {
        studentArrayList = new ArrayList<>();
    }


    @Test
    void addStudent() {
        Name name = new Name("Shelton Nqo Ngwenya");
        LocalDate ld = LocalDate.of(2017, 1, 13);
        Student student = new Student(name, 1, ld);
        studentArrayList.add(student);
        assertEquals(student, studentArrayList.get(0));
    }

    @Test
    void removeStudent() {
        Name name = new Name("Shelton Nqo Ngwenya");
        LocalDate ld = LocalDate.of(2017, 1, 13);
        Student student = new Student(name, 1, ld);
        studentArrayList.add(student);
        studentArrayList.remove(student);
        assertEquals(0, studentArrayList.size());
    }

    @Test
    void getStudent() {
        insert_generic_students();
        Student stud = studentArrayList.get(0);
        assertEquals(1, stud.getsID());

    }



    @Test
    void getStudentByID() {
        insert_generic_students();
        for (Student student : studentArrayList)
            if (student.getsID() == 1) {
                assertEquals(1, student.getsID());
            }
    }

    private void insert_generic_students() {
        Name name = new Name("Shelton Nqo Ngwenya");
        LocalDate ld = LocalDate.of(2017, 1, 13);
        Student student = new Student(name, 4, ld);
        Name name1 = new Name("The Other Ngwenya");
        LocalDate ld1 = LocalDate.of(1998, 1, 13);
        Student student1 = new Student(name, 2, ld);
        studentArrayList.add(student);
        studentArrayList.add(student1);
        System.out.println("Students have been inserted");
    }

    @Test
    void sortBysID() {
        insert_generic_students();
        Student studentIdComparator = new Student();
        studentArrayList.sort(studentIdComparator);
        assertEquals(2, studentArrayList.get(0).getsID());
    }

    @Test
    void getSize() {
        insert_generic_students();
        assertEquals(2, studentArrayList.size());
    }

    @Test
    void setStudentArrayList() {
        ArrayList<Student> studArrayList = new ArrayList<>();
        insert_generic_students();
        studentArrayList = studArrayList;
        assertEquals(0, studentArrayList.size());
    }
}