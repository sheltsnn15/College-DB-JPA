package view;
/**
 * Shelton Ngwenya, R00203947
 */

import controller.Student_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Field_Validation;
import model.Module;
import model.Student;

public class Tab2 extends Tab {

    private ListView<Student> listView;
    private TextField txtModName, txtModGrade;
    private Student studentSel;

    private ObservableList<Student> studs = FXCollections.observableArrayList();
    private ObservableList<Module> mods = FXCollections.observableArrayList();

    /**
     * Tab 2 class which contains adding student modules details controls
     * @param studentController
     */
    public Tab2(Student_Controller studentController) {//Constructor to create Tab 2 with Record Completed Student Modules UI's
        //second tab
        setText("Record Completed Student Modules");
        setClosable(false);

        //VBox with info text
        VboxLayout vbText = new VboxLayout(8);
        vbText.setAlignment(Pos.CENTER);
        Text text2 = new Text("Click Student To Add Modules");
        Font fontH2 = Font.font(Font.getFamilies().get(25), FontWeight.BOLD, FontPosture.REGULAR, 20);
        text2.setFont(fontH2);

        //Arrow image
        //ImageView imageView = new ImageView(GUI.image);
        //imageView.setFitHeight(25);
        //imageView.setFitWidth(50);

        //vbText.getChildren().addAll(text2, imageView);
        vbText.getChildren().addAll(text2);

        //list view to view student list
        listView = new ListView<>();

        listView.setItems(Students_FX_Collections.getListViewStudents(studentController));
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //HBox for action buttons
        HBoxLayout hbBtns = new HBoxLayout();

        Button btnExit = new Button("Exit");
        btnExit.setOnAction(event -> {

            if (Alert_Boxes.getConfirmationBox("Confirmation Box", "Exit Program", "Are You Sure You Want To Exit?").get() == ButtonType.OK) {
                System.exit(0);
            }
        });

        Button btnAddModule = new Button("Add Module");
        btnAddModule.setOnAction(event -> {
            addbtnClicked();
            if (studentSel != null) {
                getAddStudentModuleBox(studentController);
            } else {
                Alert_Boxes.getErrorBox("Error Box", "Add Student Module Error", "Select Student Before Adding A Module");
            }
        });

        Button btnRefresh = new Button("Refresh");
        btnRefresh.setOnAction(e -> {
            try {
                Students_FX_Collections.getListViewStudents(studentController).clear();
                listView.setItems(Students_FX_Collections.getListViewStudents(studentController));
            } catch (Exception ex) {
                Alert_Boxes.getErrorBox("Error", "List Students", "There are currently no students in the database");
            }
        });

        Button btnHelp = new Button("Help");
        btnHelp.setOnAction(e -> {
            Alert_Boxes.getInformationBox("Information Box", "Not Sure What To Do?",
                    "ADD MODULE TO STUDENT\n" +
                            "\n" +
                            "1. Click to select from listview\n" +
                            "\n" +
                            "2. Click Add Module Button\n" +
                            "\n" +
                            "\n" +
                            "EXIT PROGRAM\n" +
                            "\n" +
                            "1. Click Exit button to exit program.\n" +
                            "\n" +
                            "\n" +
                            "SAVE STUDENTS WITH COMPLETED MODULES\n" +
                            "\n" +
                            "1. Click Save button to save student details with completed modules to text file.\n" +
                            "\n" +
                            "\n" +
                            "REFRESH STUDENT LIST\n" +
                            "\n" +
                            "1. Click Refresh button to list new students\n");
        });

        hbBtns.getChildren().addAll(btnHelp, btnRefresh, btnAddModule, btnExit);

        VboxLayout vbRCSM = new VboxLayout();
        vbRCSM.getChildren().addAll(vbText, listView, hbBtns);

        //Setting the content
        setContent(vbRCSM);
    }

    public void getAddStudentModuleBox(Student_Controller studentController) {//Method with UI's to add new student module
        Stage stage = new Stage();
        stage.setTitle("Record Module Details");
        stage.setResizable(false);

        //Vbox for labels
        VBox vbLbls = new VBox(28);
        vbLbls.getChildren().addAll(new Label("Module Name"), new Label("Module Grade"));

        //Vbox to text fields
        VBox vbTxtFields = new VBox(15);
        vbTxtFields.setAlignment(Pos.CENTER_RIGHT);

        txtModName = new TextField();
        txtModName.setPromptText("Psychology");
        txtModGrade = new TextField();
        txtModGrade.setPromptText("99.99");
        vbTxtFields.getChildren().addAll(txtModName, txtModGrade);

        HBoxLayout hbTwoVBs = new HBoxLayout(35);
        hbTwoVBs.getChildren().addAll(vbLbls, vbTxtFields);

        //HBox to host action buttons
        HBoxLayout hbBtns = new HBoxLayout();
        Button btnHelp = new Button("Help");
        btnHelp.setOnAction(e -> {
            Alert_Boxes.getInformationBox("Help Box", "Not Sure What To Do?",
                    "ADD NEW MODULE\n" +
                            "\n" +
                            "1. Enter module name\n" +
                            "\n" +
                            "2. Enter module grade\n" +
                            "\n" +
                            "3. Click Submit Button\n" +
                            "\n" +
                            "\n" +
                            "GO BACK\n" +
                            "\n" +
                            "1. Click Go Back button to exit");
        });
        Button btnGoBack = new Button("Go Back");
        btnGoBack.setOnAction(e -> {
            stage.close();
        });
        Button btnSubmit = new Button("Submit");
        btnSubmit.setOnAction(event -> {
            if (!(Field_Validation.txtFisEmpty(txtModName) || Field_Validation.txtFisEmpty(txtModGrade))) {
                if (Field_Validation.isFloatOnly(txtModGrade)) {
                    studentController.addStudentModule(studentController.getStudentList().getStudentByID(studentSel.getsID()), txtModName.getText(), txtModGrade.getText());
                    clearFields();
                }
            }
        });

        hbBtns.getChildren().addAll(btnHelp, btnGoBack, btnSubmit);

        //Root VBox
        VboxLayout vbAddStudentModule = new VboxLayout();
        vbAddStudentModule.getChildren().addAll(hbTwoVBs, hbBtns);

        Scene scene = new Scene(vbAddStudentModule);
        scene.getStylesheets().add(GUI.cssPath);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    private void clearFields() {//method to clear text fields
        txtModName.clear();
        txtModGrade.clear();
    }

    private void addbtnClicked() {//method to get selected student from listview
        StringBuilder message = new StringBuilder();
        ObservableList<Student> student = listView.getSelectionModel().getSelectedItems();
        System.out.println("\nSelected Student: ");
        for (Student stud : student) {
            message.append(stud.toString());
            studentSel = stud;
        }
        System.out.println(message);
    }


}
