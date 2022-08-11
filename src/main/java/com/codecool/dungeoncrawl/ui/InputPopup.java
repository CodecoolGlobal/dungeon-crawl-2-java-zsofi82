package com.codecool.dungeoncrawl.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class InputPopup extends MyPopup {
    private boolean canceled;
    private final Button confirmButton, cancelButton;
    private final TextField usernameInput;
    private final Label inputLabel;

    public InputPopup(Stage parentWindow) {
        super(parentWindow);

        confirmButton = new Button();
        confirmButton.setOnAction(event -> close(false));
        cancelButton = new Button();
        cancelButton.setOnAction(event -> close(true));
        usernameInput = new TextField();
        inputLabel = new Label();
        inputLabel.setLabelFor(usernameInput);

        HBox inputBox = new HBox(inputLabel, usernameInput);
        inputBox.setSpacing(10);
        inputBox.setPadding(new Insets(10));
        inputBox.setAlignment(Pos.CENTER_LEFT);

        HBox buttonBox = new HBox(confirmButton, cancelButton);
        buttonBox.setSpacing(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setPadding(new Insets(0, 10, 10, 10));

        mainContainer.getChildren().add(inputBox);
        mainContainer.getChildren().add(buttonBox);

        canceled = false;
    }


    public void show(String title, String confirmLabel, String cancelLabel, String inputLabelText) {
        canceled = false;

        window.setTitle(title);
        confirmButton.setText(confirmLabel);
        cancelButton.setText(cancelLabel);
        inputLabel.setText(inputLabelText);

        super.show();
    }

    public String getInput() {
        return usernameInput.getText();
    }

    public boolean isCanceled() {
        return canceled;
    }

    private void close(boolean wasCanceled) {
        canceled = wasCanceled;
        window.close();
    }
}
