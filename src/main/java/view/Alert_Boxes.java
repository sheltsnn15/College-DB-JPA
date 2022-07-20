package view;
/**
 * Shelton Ngwenya, R00203947
 */

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Alert_Boxes {
    public static void getInformationBox(String title, String headerTxt, String contentTxt) { //Method what will give you alert box(information)
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerTxt);
        alert.setContentText(contentTxt);
        alert.showAndWait();
    }

    public static void getErrorBox(String title, String headerTxt, String contentTxt) { //Method what will give you alert box(error)
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerTxt);
        alert.setContentText(contentTxt);
        alert.showAndWait();
    }

    public static Optional<ButtonType> getConfirmationBox(String title, String headerTxt, String contentTxt) { //Method what will give you alert box(information)
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerTxt);
        alert.setContentText(contentTxt);
        Optional<ButtonType> action = alert.showAndWait();

        return action;
    }
}
