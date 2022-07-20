package controller;
/**
 * Shelton Ngwenya, R00203947
 */


import model.*;
import model.Module;
import view.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student_Controller {

    private Student_List studentList;
    private DB_Controller dbController;


    /**
     * Student Controller class constructor
     */
    public Student_Controller() {
        this.studentList = new Student_List();
        this.dbController = new DB_Controller();
    }

    /*
    #################### Student Methods ####################
     */

    /**
     * @param sName
     * @param sID
     * @param sDOB
     * Method to add student
     */
    public void addStudentToList(String sName, String sID, String sDOB) {//Method to ass mew Student Object
        int studentID = Integer.parseInt(sID);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate studentDOB = LocalDate.parse(sDOB, df);
        if (!studentList.checkStudentExists(studentID)) {//check if student exists first before adding
            Name name = new Name(sName);
            Student student = new Student(name, studentID, studentDOB);
            studentList.addStudent(student);
            dbController.add_students_to_db(studentID, name, studentDOB);
            System.out.println("Student Has Been Added");
            Tab1.clearFields();
        } else
            Alert_Boxes.getErrorBox("Error", "New Student", "This Student Already Exists With ID = " + studentID);
    }

    /**
     * @param sID
     * Method to remove student
     */
    public void removeStudentFromList(String sID) {//method to remove student object
        int studentID = Integer.parseInt(sID);
        studentList.removeStudent(studentID);
        dbController.remove_Student_DB(studentID);
        Tab1.clearFields();
    }

    /**
     * @return string
     * Method to String students information
     */
    public String getListStudentToString() {//method to get all students from arraylist (toString)
        StringBuilder allStudents = new StringBuilder("\0");
        if (!studentList.getList().isEmpty()) {
            for (int i = 0; i < studentList.getSize(); i++) {
                studentList.sortBysID();
                allStudents.append(studentList.getStudent(i));
            }
            return allStudents.toString();
        } else {
            Alert_Boxes.getErrorBox("Error", "List Student", "There Aren't Students In The Application ");
            return "Students In The Application";
        }
    }

    /**
     * Method to edit student information
     * @param sName
     * @param sID
     * @param sDOB
     */
    public void editStudentToList(String sName, String sID, String sDOB) {//Method to ass mew Student Object
        int studentID = Integer.parseInt(sID);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate studentDOB = LocalDate.parse(sDOB, df);
        if (studentList.checkStudentExists(studentID)) {//check if student exists first before adding
            studentList.removeStudent(studentID);
            Name name = new Name(sName);
            Student student = new Student(name, studentID, studentDOB);
            studentList.addStudent(student);
            dbController.update_student_details(studentID, name, studentDOB);
            System.out.println("Student Has Been Added");
            Tab1.clearFields();
        } else
            Alert_Boxes.getErrorBox("Error", "New Student", "This Student Already Exists With ID = " + studentID);
    }

    /*
    #################### Student Methods End ####################
     */
//######################################################################################################################
    /*
    #################### Student Modules Methods ####################
     */

    /**
     * @param studentSel
     * @param modName
     * @param modGrade
     * Method to add student modules
     */
    public void addStudentModule(Student studentSel, String modName, String modGrade) {//Add module to module list
        float moduleGrade = Float.parseFloat(modGrade);
        if (!studentSel.checkModuleExists(modName)) {//check if module exists before adding to module list
            //set module composite key (selected student and module name we get from user)
            Module_Composite_PK module_composite_pk = new Module_Composite_PK(studentSel.getsID(), modName);
            Module module = new Module(module_composite_pk, modName, moduleGrade);
            studentSel.addModules(module);
            dbController.add_modules_to_db(module_composite_pk, modName, moduleGrade);
            System.out.println("Module Has Been Added");
            Alert_Boxes.getInformationBox("Information Box", "Module Add Success", "Module Has Been Added");
        } else
            Alert_Boxes.getErrorBox("Error", "New Module", "This Module Already Exists With Name = " + modName);
    }

    /**
     * Method to string student modules
     * @param studentSel
     * @return
     */
    public StringBuilder getStudentModulesToString(Student studentSel) {//get modules in module list and make to String
        StringBuilder allModules = new StringBuilder("\0");
        System.out.println("\tAll Student Modules");
        for (int i = 0; i < studentSel.getModuleList().size(); i++) {
            allModules.append("\t").append(studentSel.getModule(i)).append("\n");
        }
        return allModules;
    }


     /*
    #################### Student Modules Methods End ####################
     */


    public Student_List getStudentList() {
        return studentList;
    }

    public void setStudentList(Student_List studentList) {
        this.studentList = studentList;
    }


    /**
     * Method to load student info from database into the application
     */
    public void loadStudents() {
        ArrayList<Student> listOfStuds = new ArrayList<>();
        listOfStuds.addAll(dbController.get_all_students());
        ArrayList<Module> listOfStudsMods = new ArrayList<>();
        studentList.setStudentArrayList(listOfStuds);

    }
}
