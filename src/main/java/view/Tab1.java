package view;
/**
 * Shelton Ngwenya, R00203947
 */


import controller.Student_Controller;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import model.Field_Validation;

import java.text.ParseException;

public class Tab1 extends Tab {

    private static TextField txtName;
    private static TextField txtSID;
    private static DatePicker dpDOB;

    /**
     * Tab 1 class which contains adding student details controls
     * @param studentController
     */
    public Tab1(Student_Controller studentController) {
        //first tab
        setClosable(false);
        setText("Add/Edit Student");

        studentController.loadStudents();

        Student_Info_TextArea studentInfoTextArea = new Student_Info_TextArea(studentController);
        System.out.println(studentController.getStudentList().getSize());
        //studentInfoTextArea.setText(studentController.getListStudentToString());

        //VBox to host Student Info Labels
        VboxLayout vbLabels = new VboxLayout(28);
        vbLabels.getChildren().addAll(new Label("Enter Name"), new Label("Enter Student ID"), new Label("Enter Date of Birth"));

        //Student Info Text fields
        txtName = new TextField();
        txtName.setPromptText("e.g First, Middle, Last Name");
        txtSID = new TextField();
        txtSID.setPromptText("e.g 1(Digit Only)");
        dpDOB = new DatePicker();
        dpDOB.setPromptText("e.g dd-MM-yyyy");
        dpDOB.setEditable(false);

        //VBox to host Student Info text fields
        VboxLayout vbTxtFields = new VboxLayout(15);
        vbTxtFields.setAlignment(Pos.CENTER_RIGHT);
        vbTxtFields.getChildren().addAll(txtName, txtSID, dpDOB);

        HBoxLayout hbLblsTxtBoxes = new HBoxLayout(35);
        hbLblsTxtBoxes.getChildren().addAll(vbLabels, vbTxtFields);

        //Add Remove List Students Action Controls
        Button btnAdd = new Button("Add");
        btnAdd.setOnAction(event -> {
            if (!(Field_Validation.txtFisEmpty(txtName) || Field_Validation.checkDatePickerIsEmpty(dpDOB) || Field_Validation.txtFisEmpty(txtSID))) {
                if (Field_Validation.isDigitOnly(txtSID)) {
                    String sName = txtName.getText();
                    String sDOB = dpDOB.getValue().toString(), sID = txtSID.getText();
                    studentController.addStudentToList(sName, sID, sDOB);
                }
            }
        });

        Button btnRemove = new Button("Remove");
        btnRemove.setOnAction(event -> {
            if (Alert_Boxes.getConfirmationBox("Confirmation Box", "Remove Student", "Are You Sure").get() == ButtonType.OK) {
                if ((!Field_Validation.txtFisEmpty(txtSID)) && Field_Validation.isDigitOnly(txtSID)) {
                    String sID = txtSID.getText();
                    studentController.removeStudentFromList(sID);
                }
            }
        });

        Button btnList = new Button("List");
        btnList.setOnAction(event -> {
            try {
                String allStudents = studentController.getListStudentToString();
                if (allStudents.equals("Students In The Application")) {
                    Alert_Boxes.getErrorBox("Error", "List Error", "Currently, There Aren't Any Students In Application Memory");
                }
                studentInfoTextArea.setText(allStudents);
            } catch (StringIndexOutOfBoundsException e) {
                Alert_Boxes.getErrorBox("Error", "List Students", "There are currently no students in the database");
            }
        });

        Button btnEdit = new Button("Edit");
        btnEdit.setOnAction(event -> {
            if (!(Field_Validation.txtFisEmpty(txtName) || Field_Validation.checkDatePickerIsEmpty(dpDOB) || Field_Validation.txtFisEmpty(txtSID))) {
                if (Field_Validation.isDigitOnly(txtSID)) {
                    String sName = txtName.getText(), sDOB = dpDOB.getValue().toString(), sID = txtSID.getText();
                    studentController.editStudentToList(sName, sID, sDOB);
                }
            }
        });

        HBoxLayout hbARL = new HBoxLayout(15);
        hbARL.getChildren().addAll(btnAdd, btnRemove, btnList, btnEdit);


        HBoxLayout hbtxtA = new HBoxLayout(15);
        hbtxtA.getChildren().addAll(studentInfoTextArea);

        //Help Load Save Exit Students Action Controls
        Button btnHelp = new Button("Help");
        btnHelp.setOnAction(event -> Alert_Boxes.getInformationBox("Help Box", "Not Sure What To Do?",
                "ADD/EDIT STUDENT.\n" +
                        "1. Enter:" +
                        "\n    *student name (firstname, middlename, lastname (add commas between them for separation), " +
                        "\n    *student ID(only digital numbers), " +
                        "\n    *and Date Of Birth (Use Date Picker).\n" +
                        "2. Click Add or Edit button to submit these student details.\n" +
                        "\n" +
                        "REMOVE STUDENT.\n" +
                        "1. Enter student ID.\n" +
                        "2. Click Remove button to remove that student details.\n" +
                        "\n" +
                        "LIST STUDENTS\n" +
                        "1. Click List button to list student details.\n" +
                        "\n" +
                        "LOAD STUDENTS\n" +
                        "1. Click Load button to get student details from text file.\n" +
                        "\n" +
                        "SAVE STUDENTS\n" +
                        "1. Click Save button to save student details to text file.\n" +
                        "\n" +
                        "EXIT PROGRAM\n" +
                        "1. Click Exit button to exit program.\n"));

        Button btnLoad = new Button("Load");
        btnLoad.setOnAction(event -> {
            studentController.loadStudents();
            studentInfoTextArea.setText(studentController.getListStudentToString());
        });

        Button btnExit = new Button("Exit");
        btnExit.setOnAction(event -> {

            if (Alert_Boxes.getConfirmationBox("Confirmation Box", "Exit Program", "Are You Sure You Want To Exit?").get() == ButtonType.OK) {
                System.exit(0);
            }
        });


        //HBox to host Help Load Save Exit Students Action Controls
        HBoxLayout hbNSBtns = new HBoxLayout();
        hbNSBtns.getChildren().addAll(btnHelp, btnLoad, btnExit);

        VboxLayout vbNewStudent = new VboxLayout();
        vbNewStudent.getChildren().addAll(hbLblsTxtBoxes, hbARL, hbtxtA, hbNSBtns);
        setContent(vbNewStudent);
    }

    public static void clearFields() {//Method to clear Student Info text fields
        txtName.clear();
        dpDOB.setValue(null);
        txtSID.clear();
    }
}
