package view;
/**
 * Shelton Ngwenya, R00203947
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class VboxLayout extends VBox {
    public VboxLayout() {//main btn layout
        this.setAlignment(Pos.CENTER);
        this.setSpacing(15);
        this.setPadding(new Insets(25, 25, 25, 25));
    }

    public VboxLayout(int spacing) {//other layout
        this.setSpacing(spacing);
    }

}
