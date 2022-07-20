package model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 * Shelton Ngwenya, R00203947
 */


@Entity
@Table(name = "Module")
@NamedQuery(name = "Module.all_honours_modules", query = "SELECT m " +
        "FROM Module m JOIN m.student s " +
        "WHERE s.sID = :studentID AND m.moduleGrade >= 70 AND m.moduleName IS NOT NULL ")
@NamedQuery(name = "Module.all_modules", query = "SELECT m " +
        "FROM Module m JOIN m.student s " +
        "WHERE s.sID = :studentID AND m.moduleName IS NOT NULL")
public class Module implements Serializable, Comparable<Module>, Comparator<Module> {


    @EmbeddedId
    private Module_Composite_PK module_composite_pk;

    @MapsId("moduleName")
    @JoinColumn(name = "moduleName")
    private String moduleName;
    @Column(name = "moduleGrade")
    private float moduleGrade;
    @ManyToOne
    @MapsId("studID")
    @JoinColumn(name = "studID")
    private Student student;

    public Module(Module_Composite_PK module_composite_pk, String moduleName, float moduleGrade) {
        this.module_composite_pk = module_composite_pk;
        this.moduleName = moduleName;
        this.moduleGrade = moduleGrade;
    }

    public Module() {
    }

    /**
     * method to get module composite primary key
     * @return Module_Composite_PK
     */
    public Module_Composite_PK getModule_composite_pk() {
        return module_composite_pk;
    }

    /**
     * method to set module composite primary key object
     * @param module_composite_pk
     */
    public void setModule_composite_pk(Module_Composite_PK module_composite_pk) {
        this.module_composite_pk = module_composite_pk;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public float getModuleGrade() {
        return moduleGrade;
    }

    public void setModuleGrade(float moduleGrade) {
        this.moduleGrade = moduleGrade;
    }

    @Override
    public String toString() {
        return "Module Name= '" + moduleName + '\'' +
                ", Module Grade= '" + moduleGrade + '\'' + '\n';
    }

    /**
     * method to compare 2 module objects
     * @param m1 the first object to be compared.
     * @param m2 the second object to be compared.
     * @return
     */
    public int compare(Module m1, Module m2) {
        Float id1 = m1.getModuleGrade();
        Float id2 = m2.getModuleGrade();
        return id1.compareTo(id2); // integer compareTo()
    }

    /**
     * method to compare a module object
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Module o) {
        return 0;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }



}
