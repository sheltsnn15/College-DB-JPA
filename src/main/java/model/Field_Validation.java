package model;
/*
 * @author Shelton Ngwenya, R00203947
 */

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import view.Alert_Boxes;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Field_Validation {

    /**
     * @brief method to check if user input is digit only
     * @param txtField
     * @return boolean
     */
    public static boolean isDigitOnly(TextField txtField) { //Method for integer validation
        try {
            Integer.parseInt(txtField.getText());
            return true;
        } catch (NumberFormatException e) {
            Alert_Boxes.getErrorBox("Check Fields", "Non-Digit Input", "Please Enter Digits");
            return false;
        }
    }

    /**
     * @brief method to check user input is a float data type
     * @param txtField
     * @return boolean
     */
    public static boolean isFloatOnly(TextField txtField) { //Method for float value validation
        try {
            if (moduleMarkValidation(Float.parseFloat(txtField.getText())))
                return true;
        } catch (NumberFormatException e) {
            Alert_Boxes.getErrorBox("Check Fields", "Non-Digit Input", "Please Enter Digits");
        }
        return false;
    }

    /**
     * @brief method to check if student mark entered is within bounds(0 - 100)
     * @param mark
     * @return boolean
     */
    public static boolean moduleMarkValidation(float mark) {//Method to check if module mark <= 100 && module mark >= 0
        BigDecimal b1 = new BigDecimal("1234.233");
        if (!(mark <= 100 && mark >= 0)) {
            Alert_Boxes.getErrorBox("Error Box", "Module Mark Error",
                    "Ensure Module Mark Is Not Greater Than 100" +
                            "\nOr Less Than 0");


            return false;
        }
        return true;
    }

    /**
     * @param txtField
     * @return boolean
     * @brief method to check if text field is empty
     */
    public static boolean txtFisEmpty(TextField txtField) {//Method for text field is empty
        if (txtField.getText().isEmpty()) {
            Alert_Boxes.getErrorBox("Check Fields", "TextFields Empty", "Please Check Fields");
            return true;
        }
        return false;
    }

    /**
     * @param datePicker
     * @return boolean
     * @brief method to check if datepicker is empty
     */
    public static boolean checkDatePickerIsEmpty(DatePicker datePicker) {
        if ((datePicker.getValue() == null)) {
            Alert_Boxes.getErrorBox("Check Fields", "Date Picker Is Empty", "Please Check Fields");
            return true;
        }
        return false;
    }

    /**
     * @param connection
     * @param tableName
     * @return boolean value
     * @brief check if table exists
     */
    public static boolean isTableExist(Connection connection, String tableName) throws SQLException {
        if (connection != null) {
            DatabaseMetaData dbmd = connection.getMetaData();
            //get names of the tables in db
            ResultSet rs = dbmd.getTables(null, null, tableName.toUpperCase(), null);

            //loop through db and compare table names to parameter table names
            if (rs.next())
                //System.out.println("Table " + rs.getString(tableName.toUpperCase()) + "already exists !!");
            return false;
        }
        return true;
    }


}
