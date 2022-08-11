package com.codecool.dungeoncrawl.ui;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class MyPopup {
    protected Stage parentWindow, window;
    protected VBox mainContainer;
    protected Scene scene;

    protected MyPopup(Stage parentWindow) {
        this.parentWindow = parentWindow;
        window = new Stage();
        window.initOwner(parentWindow);
        window.initModality(Modality.APPLICATION_MODAL);
        mainContainer = new VBox();
        scene = new Scene(mainContainer);
        window.setScene(scene);
    }

    public void show() {
        window.showAndWait();
    }
}
