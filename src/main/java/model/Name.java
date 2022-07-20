package model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

public class Name implements Serializable {

    private static final long serialVersionUUID = 1L;

    private String first_name, middle_name, last_name;

    public Name(String fullName) {
        splitFullName(fullName);
    }

    public Name() {
    }

    public void splitFullName(String fname) {
        if (fname.contains(" ")) {
            String[] nameParts = fname.split(" ");
            first_name = nameParts[0].substring(0, 1).toUpperCase();
            middle_name = nameParts[1].substring(0, 1).toUpperCase();
            last_name = nameParts[2].substring(0, 1).toUpperCase();
            System.out.printf("Your initials are: %s %s %s ", first_name, middle_name, last_name);
        }
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return first_name + ' ' +
                middle_name + ' ' +
                last_name + ' ';
    }

}
