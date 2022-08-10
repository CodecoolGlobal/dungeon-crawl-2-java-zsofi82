package com.codecool.dungeoncrawl;

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

public class SavePopup {
    Stage parentWindow, window;
    Button saveButton, cancelButton;
    TextField usernameInput;
    Label inputLabel;
    HBox buttonBox, inputBox;
    VBox mainContainer;

    public SavePopup(Stage parentWindow) {
        this.parentWindow = parentWindow;
        window = new Stage();
        window.initOwner(parentWindow);
        window.setTitle("Save");
        window.initModality(Modality.APPLICATION_MODAL);

        saveButton = new Button("Save");
        cancelButton = new Button("Cancel");
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

        mainContainer = new VBox(inputBox, buttonBox);
    }

    public void show() {
        Scene scene = new Scene(mainContainer);
        window.setScene(scene);

        window.showAndWait();
    }

    public String getInput() {
        return usernameInput.getText();
    }
}