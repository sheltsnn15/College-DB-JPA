package view;

import controller.Student_Controller;
import javafx.scene.control.TextArea;

public class Student_Info_TextArea extends TextArea {

    public Student_Info_TextArea(Student_Controller studentController) {
        //Student in the Application TextArea Controls
        this.setText("Students in the Application");
        this.setEditable(false);
        //list students objects from text file
        this.setText(studentController.getListStudentToString());
    }
}
