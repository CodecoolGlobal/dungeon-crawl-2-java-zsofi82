package com.codecool.dungeoncrawl.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorPopup extends MyPopup {
    Button OKButton;
    Label messageLabel;

    public ErrorPopup(Stage parentWindow) {
        super(parentWindow);
        window.setTitle("Error");

        OKButton = new Button("OK");
        OKButton.setOnAction(event -> window.close());
        messageLabel = new Label("");

        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPadding(new Insets(10));
        mainContainer.setSpacing(5);
        mainContainer.getChildren().add(messageLabel);
        mainContainer.getChildren().add(OKButton);
    }

    public void show(String message) {
        messageLabel.setText(message);
        super.show();
    }

}
