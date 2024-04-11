package hi.vidmot.audioplayer.controller;

import hi.vidmot.audioplayer.view.View;
import hi.vidmot.audioplayer.view.ViewSwitcher;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;

public class Menu_lagController {
    public ListiController listiController;
    public AddLagController addLagController;
    public EditSongController editSongController;

    public MenuItem fxNew;
    public MenuItem fxRename;
    public MenuItem fxDelete;

    /**
     * connects menucontroller and listiController
     * @param list
     */
    public void setListiController(ListiController list) {
        this.listiController = list;
    }

    public void setAddLagController(AddLagController addLagController) {
        this.addLagController = addLagController;
    }

    public void setEditSongController(EditSongController editSongController) {
        this.editSongController = editSongController;
    }

    public void onRnameNDelete(ActionEvent actionEvent) {
        ViewSwitcher.switchTo_WithSize(View.EDITSONG, false,300, 500);
    }

    public void onQuit(ActionEvent actionEvent) {
    }

    public void onNew(ActionEvent actionEvent) {
        if( listiController.curr_lag != null){
            listiController.curr_lag.getLag().pause();
        }
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
