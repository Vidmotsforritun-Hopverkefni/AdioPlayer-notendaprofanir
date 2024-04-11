package hi.vidmot.audioplayer.view;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public enum View {
    HEIMA("fxml/heima-view.fxml"),
    LISTI("fxml/listi-view.fxml"),
    ADDSONG("fxml/add_song-view.fxml"),
    EDITSONG("fxml/edit_song-view.fxml"),
    DELETESONG("fxml/delete-Lag-view.fxml"),
    ADDPLAYLIST("fxml/add-playlist-view.fxml");

    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
