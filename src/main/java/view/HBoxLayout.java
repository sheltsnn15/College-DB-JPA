package view;
/**
 * Shelton Ngwenya, R00203947
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

/**
 * Sample Template for HBox layout
 */
public class HBoxLayout extends HBox {

    public HBoxLayout() {//layout for buttons
        this.setSpacing(15);
        this.setAlignment(Pos.CENTER_RIGHT);
    }

    public HBoxLayout(int spacing) {//other layout
        this.setSpacing(spacing);
    }
}
