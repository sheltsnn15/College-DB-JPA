package view;
/**
 * @author Shelton Ngwenya, R00203947
 */

import controller.Student_Controller;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Field_Validation;
import model.Module;

public class Tab3 extends Tab {

    private ListView<Module> listView;
    private TextField txtSID;

    /**
     * Tab 3 class which contains viewing student and student module details controls
     * @param studentController
     */
    public Tab3(Student_Controller studentController) {
        //third tab
        setText("View Completed Modules");
        setClosable(false);

        Student_Info_TextArea studentInfoTextArea = new Student_Info_TextArea(studentController);

        VboxLayout hb_sLbl_sTxtF = new VboxLayout(15);
        txtSID = new TextField();
        txtSID.setPromptText("e.g 1(Digit Only)");

        Button btnSubmit = new Button("Submit");
        btnSubmit.setOnAction(event -> {
            if (!Field_Validation.txtFisEmpty(txtSID)) {
                if (Field_Validation.isDigitOnly(txtSID)) {
                    listView.setItems(Students_FX_Collections.getListViewStudentModules(studentController, Integer.parseInt(txtSID.getText())));
                }
            }
        });

        Button btnSortByGrade = new Button("Sort By Grade");
        btnSortByGrade.setOnAction(event -> {
            if (!Field_Validation.txtFisEmpty(txtSID)) {
                listView.setItems(Students_FX_Collections.getSortModulesByGrade(studentController, Integer.parseInt(txtSID.getText())));

            }
        });

        hb_sLbl_sTxtF.getChildren().addAll(new Label("Enter Student ID"), txtSID, btnSubmit, btnSortByGrade);

        Text text2 = new Text("Student Modules");
        Font fontH2 = Font.font(Font.getFamilies().get(25), FontWeight.BOLD, FontPosture.REGULAR, 15);
        text2.setFont(fontH2);
        //list view to view modules list
        VboxLayout hbLV = new VboxLayout(15);
        hbLV.setAlignment(Pos.CENTER);
        listView = new ListView<>();
        listView.setMaxHeight(210);
        hbLV.getChildren().addAll(text2, listView);


        HBoxLayout hbTxtF_LV = new HBoxLayout(15);
        hbTxtF_LV.getChildren().addAll(hb_sLbl_sTxtF, hbLV);

        //Student in the Application TextArea Controls
        studentInfoTextArea.setText("Students In Application");
        HBoxLayout hbtxtA = new HBoxLayout(15);

        Button btnList = new Button("List");
        btnList.setOnAction(event -> {
            try {
                String allStudents = studentController.getListStudentToString();
                System.out.println(allStudents);
                studentInfoTextArea.setText(allStudents);
            } catch (StringIndexOutOfBoundsException e) {
                Alert_Boxes.getErrorBox("Error", "List Students", "There are currently no students in the database");
            }
        });

        hbtxtA.getChildren().addAll(studentInfoTextArea);

        Button btnHelp = new Button("Help");
        btnHelp.setOnAction(event -> Alert_Boxes.getInformationBox("Help Box", "Not Sure What To Do?",
                "VIEW STUDENT MODULES.\n" +
                        "\n" +
                        "1. Enter student ID(only digital numbers).\n" +
                        "\n" +
                        "1.1 Check text area To view saved Student Information.\n" +
                        "\n" +
                        "2. Click Submit button to submit student ID.\n" +
                        "\n" +
                        "2.1 Student information will be displayed in the List View\n" +
                        "\n" +
                        "\n" +
                        "LIST STUDENTS\n" +
                        "\n" +
                        "1. Click List button to list student details.\n" +
                        "\n" +
                        "\n" +
                        " SORT STUDENTS MODULES\n" +
                        "\n" +
                        "1. Click Sort By Grade button to sort students modules grade in ascending order.\n" +
                        "\n" +
                        "\n" +
                        "EXIT PROGRAM\n" +
                        "\n" +
                        "1. Click Exit button to exit program.\n"));

        Button btnExit = new Button("Exit");
        btnExit.setOnAction(event -> {
                System.exit(0);
        });

        HBoxLayout hbBtns2 = new HBoxLayout();
        hbBtns2.getChildren().addAll(btnHelp, btnList, btnExit);

        VboxLayout vbRoot = new VboxLayout();
        vbRoot.getChildren().addAll(hbTxtF_LV, hbtxtA, hbBtns2);

        //Setting the content
        setContent(vbRoot);
    }

    /**
     * @brief clear sID textfield
     */
    private void clearFields() {//method to clear text fields
        txtSID.clear();
    }


}
