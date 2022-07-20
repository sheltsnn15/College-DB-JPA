package model;
/**
 * Shelton Ngwenya, R00203947
 */

import jakarta.persistence.*;
import view.Alert_Boxes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

@Entity
@Table(name = "Student")
public class Student implements Serializable, Comparable<Student>, Comparator<Student> {

    private static final long serialVersionUUID = 1L;
    @Id
    @Column(name = "sID", unique = true, nullable = false)
    private int sID;

    @OneToMany(mappedBy = "student", targetEntity = Module.class, cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "studentID", referencedColumnName = "sID")
    private ArrayList<Module> modulesArrayList;


    @Column(name = "sName", nullable = false)
    private Name sName;

    @Column(name = "sDOB", nullable = false)
    private LocalDate sDOB;

    public Student(Name sName, int sID, LocalDate sDOB) {
        this.sName = sName;
        this.sID = sID;
        this.sDOB = sDOB;
        this.modulesArrayList = new ArrayList<>();
    }

    public Student() {
    }

    /**
     * Student methods
     */

    public Name getsName() {
        return sName;
    }

    public void setsName(Name sName) {
        this.sName = sName;
    }

    public int getsID() {
        return sID;
    }

    public void setsID(int sID) {
        this.sID = sID;
    }

    public LocalDate getsDOB() {
        return sDOB;
    }

    public void setsDOB(LocalDate sDOB) {
        this.sDOB = sDOB;
    }


    @Override
    public String toString() {
        return "Student ID= " + sID +
                ", Student Name= '" + sName + '\'' +
                ", Student DOB= '" + sDOB + '\'' + '\n';
    }



    public int compare (Student s1, Student s2) {
        Integer id1 = s1.getsID();
        Integer id2 = s2.getsID();
        return id1.compareTo(id2); // integer compareTo()
    }

    @Override
    public int compareTo(Student o) {
        return 0;
    }


    /**
     * Student methods end
     */


    /**
     * Module Arr methods
     */

    public void sortBymGrade() {
        Module moduleGradeComparator = new Module();
        Collections.sort(modulesArrayList, moduleGradeComparator);
    }

    public void addModules(Module module) {//method to add modules to module list
        this.modulesArrayList.add(module);
    }

    public void getAllModules() {
        for (Module module : modulesArrayList) {
            System.out.println(module.toString()+"\n");
        }
    }

    public Module getModule(int i) {//get module at index i
        if ((i > -1) && (i < getSize()))//check if i is in bounds
            return modulesArrayList.get(i);
        else
            Alert_Boxes.getErrorBox("Error", "Module", "Module doesn't exist");
        return null;
    }

    public ArrayList<Module> getModuleList() {//method to get module lost
        return modulesArrayList;
    }

    public boolean checkModuleExists(String modName) {//method to if module has already been added to module list
        for (int i = 0; i < modulesArrayList.size(); i++)
            return Objects.equals(getModule(i).getModuleName(), modName);
        return false;
    }

    public int getSize() {//method to get module list size
        return modulesArrayList.size();
    }

}