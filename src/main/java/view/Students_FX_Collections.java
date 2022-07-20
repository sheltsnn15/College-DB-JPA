package view;

import controller.Student_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Module;
import model.Student;

public class Students_FX_Collections {

    private static ObservableList<Student> studs = FXCollections.observableArrayList();
    private static ObservableList<Module> mods = FXCollections.observableArrayList();
    ;

    /**
     * Get student information and add to list view
     * @param studentController
     * @return
     */
    public static ObservableList<Student> getListViewStudents(Student_Controller studentController) {//method to get all students from arraylist (observable list)
        for (int i = 0; i < studentController.getStudentList().getSize(); i++) {
            studentController.getStudentList().sortBysID();
            studs.add(studentController.getStudentList().getStudent(i));
        }
        return studs;
    }

    /**
     * Get module information and add to list view
     * @param studentController
     * @param sID
     * @return
     */
    public static ObservableList<Module> getListViewStudentModules(Student_Controller studentController, int sID) {//get modules and add to observable list

        if (studentController.getStudentList().checkStudentExists(sID)) {//check if student exists

            if (!studentController.getStudentList().getStudentByID(sID).getModuleList().isEmpty()) {//check if module arr list is empty
                mods.clear();
                for (int i = 0; i < studentController.getStudentList().getStudentByID(sID).getModuleList().size(); i++) {
                    //studentController.getStudentList().getStudentByID(sID).sortBymGrade();
                    mods.add(studentController.getStudentList().getStudentByID(sID).getModule(i));
                }
                //mods.addAll(studentList.getStudentByID(sID).getModuleList());

                studentController.getStudentModulesToString(studentController.getStudentList().getStudentByID(sID));
            } else {
                Alert_Boxes.getErrorBox("Error", "Module", "Student Hasn't Completed Any Modules Yet");
            }
        } else {
            Alert_Boxes.getErrorBox("Error", "Student ID", "Student ID, " + sID + " Doesn't Exist");
        }

        return mods;
    }


    /**
     * Get module information by grade and add to list view
     * @param studentController
     * @param sID
     * @return
     */
    public static ObservableList<Module> getSortModulesByGrade(Student_Controller studentController, int sID) {//get modules and add to observable list

        if (!studentController.getStudentList().getStudentByID(sID).getModuleList().isEmpty()) {//check if module arr list is empty
            mods.clear();
            studentController.getStudentList().getStudentByID(sID).sortBymGrade();
            getListViewStudentModules(studentController, sID);

        } else {
            Alert_Boxes.getErrorBox("Error", "Student ID", "Student ID, " + sID + " Doesn't Exist");
        }

        return mods;
    }
}

