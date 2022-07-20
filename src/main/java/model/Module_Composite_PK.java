package model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class will create a composite primary key for ascciated to each module
 */
@Embeddable

public class Module_Composite_PK implements Serializable {

    /**
     * Constructor will create a composite primary key using student id and module name
     */
    @Column(name = "studID", nullable = false)
    private int studID;

    @Column(name = "moduleName", nullable = false)
    private String moduleName;

    public Module_Composite_PK(int studID, String moduleName) {
        this.studID = studID;
        this.moduleName = moduleName;
    }

    public Module_Composite_PK() {

    }

    public int getStudID() {
        return studID;
    }

    public void setStudID(int studID) {
        this.studID = studID;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

}
