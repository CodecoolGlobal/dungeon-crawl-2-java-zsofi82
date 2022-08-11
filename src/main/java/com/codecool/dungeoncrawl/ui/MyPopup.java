package com.codecool.dungeoncrawl.ui;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class MyPopup {
    protected Stage parentWindow, window;
    protected VBox mainContainer;

    protected MyPopup(Stage parentWindow, String title) {
        this.parentWindow = parentWindow;
        window = new Stage();
        window.initOwner(parentWindow);
        window.setTitle(title);
        window.initModality(Modality.APPLICATION_MODAL);
        mainContainer = new VBox();
    }

    public void show() {
        Scene scene = new Scene(mainContainer);
        window.setScene(scene);

        window.showAndWait();
    }

    public abstract String getInput();
}
