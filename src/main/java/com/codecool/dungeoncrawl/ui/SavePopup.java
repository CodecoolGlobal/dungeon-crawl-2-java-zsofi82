package com.codecool.dungeoncrawl.ui;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.stage.*;

public class SavePopup extends MyPopup {
    Button saveButton, cancelButton;
    TextField usernameInput;
    Label inputLabel;
    HBox buttonBox, inputBox;
    boolean canceled;

    public SavePopup(Stage parentWindow) {
        super(parentWindow, "Save");

        canceled = false;

        saveButton = new Button("Save");
        saveButton.setOnAction(event -> close(false));
        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> close(true));
        usernameInput = new TextField();
        inputLabel = new Label("User name:");
        inputLabel.setLabelFor(usernameInput);

        inputBox = new HBox(inputLabel, usernameInput);
        inputBox.setSpacing(10);
        inputBox.setPadding(new Insets(10));
        inputBox.setAlignment(Pos.CENTER_LEFT);

        buttonBox = new HBox(saveButton, cancelButton);
        buttonBox.setSpacing(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setPadding(new Insets(0, 10, 10, 10));

        mainContainer.getChildren().add(inputBox);
        mainContainer.getChildren().add(buttonBox);
    }

    @Override
    public void show() {
        super.show();
        canceled = false;
    }

    @Override
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