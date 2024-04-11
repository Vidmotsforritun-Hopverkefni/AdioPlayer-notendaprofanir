package hi.vidmot.audioplayer.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;

public class Menu_laglistiController {
    public MenuItem fxNew;
    public MenuItem fxRename;
    public MenuItem fxDelete;
    public PlayerController playerController;

    public void setPlayerController(PlayerController playerController) {
        this.playerController = playerController;
    }

    public void onNew(ActionEvent actionEvent) {
    }

    public void onQuit(ActionEvent actionEvent) {
    }

    public void onRnameNDelete(ActionEvent actionEvent) {
    }

    public void onAbout(ActionEvent actionEvent) {
    }
}
