package com.codecool.dungeoncrawl.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ExportPopup extends CancelablePopup {
    Button exportButton, cancelButton;
    TextField usernameInput;
    Label inputLabel;
    HBox buttonBox, inputBox;

    public ExportPopup(Stage parentWindow) {
        super(parentWindow, "Export");

        exportButton = new Button("Export");
        exportButton.setOnAction(event -> close(false));
        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> close(true));
        usernameInput = new TextField();
        inputLabel = new Label("File name:");
        inputLabel.setLabelFor(usernameInput);

        inputBox = new HBox(inputLabel, usernameInput);
        inputBox.setSpacing(10);
        inputBox.setPadding(new Insets(10));
        inputBox.setAlignment(Pos.CENTER_LEFT);

        buttonBox = new HBox(exportButton, cancelButton);
        buttonBox.setSpacing(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setPadding(new Insets(0, 10, 10, 10));

        mainContainer.getChildren().add(inputBox);
        mainContainer.getChildren().add(buttonBox);
    }

    @Override
    public String getInput() {
        return null;
    }
}
