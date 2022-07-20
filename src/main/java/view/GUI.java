package view;
/**
 * Shelton Ngwenya, R00203947
 */

import controller.Student_Controller;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application {

    Student_Controller studentController = new Student_Controller();
    static String image = "src/main/java/miscellaneous/background.png";
    static String cssPath = "src/main/java/miscellaneous/style.css";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


        primaryStage.setTitle("MTU Student Record System");
        BorderPane bpRoot = new BorderPane();

        //Vbox to host welcome text and arrow image
        VBox vbTop = new VBox(8);

        Text text = new Text("Welcome");
        Font fontHeader = Font.font(Font.getFamilies().get(25), FontWeight.BOLD, FontPosture.REGULAR, 50);
        //Setting font to the text
        text.setFont(fontHeader);

        Text text2 = new Text("Click Links For Various Functions");
        Font fontH2 = Font.font(Font.getFamilies().get(25), FontWeight.BOLD, FontPosture.REGULAR, 30);
        text2.setFont(fontH2);

        //ImageView imageView = new ImageView(image);
        //imageView.setFitHeight(55);
        //imageView.setFitWidth(90);

        //Creating a TabPane
        TabPane tabPane = new TabPane();

        vbTop.getChildren().addAll(text, text2);
        vbTop.setAlignment(Pos.CENTER);
        vbTop.setPadding(new Insets(15, 15, 15, 15));
        bpRoot.setTop(vbTop);

        //HBox to host tab pane
        HBox hbTabPane = new HBox(5);
        hbTabPane.setAlignment(Pos.CENTER);
        hbTabPane.setPadding(new Insets(15, 15, 15, 15));
        hbTabPane.getChildren().addAll(tabPane);
        bpRoot.setCenter(hbTabPane);

        //Adding tabs to the tab pane
        tabPane.getTabs().add(new Tab1(studentController));
        tabPane.getTabs().add(new Tab2(studentController));
        tabPane.getTabs().add(new Tab3(studentController));

        Scene sceneRoot = new Scene(bpRoot, 549, 600);
        //sceneRoot.getStylesheets().add(cssPath);
        primaryStage.setScene(sceneRoot);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}