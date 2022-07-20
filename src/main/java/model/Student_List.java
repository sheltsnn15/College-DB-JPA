package model;
/**
 * Shelton Ngwenya, R00203947
 */

import view.Alert_Boxes;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student_List implements Serializable {
    private List<Student> studentArrayList;

    public Student_List() {
        studentArrayList = new ArrayList<>();
    }

    /**
     * method to add student object tyo arraylist
     * @param student
     */
    public void addStudent(Student student) {//method to add student obj to arraylist
        this.studentArrayList.add(student);
        Alert_Boxes.getInformationBox("New Student", "Successful", "Student has been added");
    }

    /**
     * method to remove student object from arraylist
     * @param sID
     */
    public void removeStudent(int sID) {
        if (checkStudentExists(sID)) {
            for (int i = 0; i < studentArrayList.size(); i++) {
                if (getStudent(i).getsID() == sID)
                    studentArrayList.remove(i);
            }
            Alert_Boxes.getInformationBox("Remove Student", "Successful", "Student Has Been Removed");
        } else
            Alert_Boxes.getErrorBox("Remove Student", "Unsuccessful", "Student Doesn't Exists");
    }

    /**
     * toString method of arraylist students
     * @return String
     */
    public String listStudents() {
        if (studentArrayList.isEmpty()) {
            Alert_Boxes.getErrorBox("Error", "List Student", "There Arent Any Students In Memory");
            return null;

        } else {
            for (Student student : studentArrayList) {
                return student.toString() + "\n";
            }
        }
        return null;
    }

    /**
     * method to get student object from arraylist by index
     * @param i
     * @return Student
     */
    public Student getStudent(int i) {
        if ((i > -1) && (i < getSize()))
            // check that the index is valid
            return studentArrayList.get(i);
        else
            Alert_Boxes.getErrorBox("Error", "Student Delete", "StudentID doesn't exist");
        return null;
    }

    /**
     * method to get student arraylist
     * @return List<Student>
     */
    public List<Student> getList() {
        return studentArrayList;
    }

    /**
     * method to check if student object exists
     * @param sID
     * @return boolean
     */
    public boolean checkStudentExists(int sID) {
        return getStudentByID(sID) != null;
    }

    /**
     * method to get student object by student id in student arraylist
     * @param sID
     * @return Student
     */
    public Student getStudentByID(int sID) {//get student by student id
        for (int i = 0; i < studentArrayList.size(); i++)
            if (getStudent(i).getsID() == sID) {
                return getStudent(i);
            }

        return null;
    }

    /**
     * method to sort student objects in arraylist by student id
     */
    public void sortBysID() {
        Student studentIdComparator = new Student();
        Collections.sort(studentArrayList, studentIdComparator);
    }

    /**
     * method to get student arraylist size
     * @return int
     */
    public int getSize() {
        return studentArrayList.size();
    }

    /**
     * method to set student arraylist
     * @param studentArrayList
     */
    public void setStudentArrayList(ArrayList<Student> studentArrayList) {
        this.studentArrayList = studentArrayList;
    }
}
