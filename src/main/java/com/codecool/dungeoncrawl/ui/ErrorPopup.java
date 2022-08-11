package com.codecool.dungeoncrawl.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorPopup extends MyPopup {
    Button OKButton;
    Label messageLabel;

    public ErrorPopup(Stage parentWindow) {
        super(parentWindow, "Error");

        OKButton = new Button("OK");
        OKButton.setOnAction(event -> window.close());
        messageLabel = new Label("");

        mainContainer.getChildren().add(messageLabel);
        mainContainer.getChildren().add(OKButton);
    }

    public void show(String message) {
        messageLabel.setText(message);
        super.show();
    }

    @Override
    public String getInput() {
        return null;
    }
}
