package com.codecool.dungeoncrawl.ui;

import javafx.stage.Stage;

public abstract class CancelablePopup extends MyPopup {
    protected boolean canceled;

    protected CancelablePopup(Stage parentWindow, String title) {
        super(parentWindow, title);

        canceled = false;
    }

    @Override
    public void show() {
        canceled = false;
        super.show();
    }

    public boolean isCanceled() {
        return canceled;
    }

    protected void close(boolean wasCanceled) {
        canceled = wasCanceled;
        window.close();
    }
}
