package hi.vidmot.audioplayer.controller;

import hi.vidmot.audioplayer.view.View;
import hi.vidmot.audioplayer.view.ViewSwitcher;
import javafx.event.ActionEvent;

public class MenuController {
    public ListiController listiController;
    /**
     * connects menucontroller and listiController
     * @param list
     */
    public void setListiController(ListiController list) {
        this.listiController = list;
    }

    public void onRnameNDelete(ActionEvent actionEvent) {
    }

    public void onQuit(ActionEvent actionEvent) {
    }

    public void onNew(ActionEvent actionEvent) {
        ViewSwitcher.switchTo_WithSize(View.ADDSONG, false, 300, 450);
    }

    public void onAbout(ActionEvent actionEvent) {
    }

    public void onDeleteLag(ActionEvent actionEvent) {
        ViewSwitcher.switchTo_WithSize(View.DELETESONG, false, 300, 450);
    }

    public void onAddPlaylist(ActionEvent actionEvent){
        ViewSwitcher.switchTo_WithSize(View.ADDPLAYLIST,false, 300, 450);
    }

}
