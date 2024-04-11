package hi.vidmot.audioplayer;

import hi.vinnsla.audioplayer.Lagalistar;
import hi.vinnsla.audioplayer.NewPlaylist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class addPlaylistDialog extends Dialog<NewPlaylist> {
    private int index;

    private Lagalistar lagalistar;

    private Stage stage;

    private TextField playlistNameField;

    private ImageView imageView;
    @FXML
    private TextField fxName;

    @FXML
    private TextField fxImage;

    private NewPlaylist newPlaylist;





    public addPlaylistDialog(Lagalistar lagalistar, int index, boolean nix, Stage stage) {
        this.lagalistar = lagalistar;
        this.index = index;
        this.stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/add_playlist_dialog.fxml"));
        try {
            DialogPane dialogPane = fxmlLoader.load();
            Scene scene = new Scene(dialogPane);
            stage.setScene(scene);
        } catch (IOException exception) {
            throw new RuntimeException("Unable to load FXML", exception);
        }

        // Initialize UI components
        playlistNameField = (TextField) fxmlLoader.getNamespace().get("playlistNameField");
        imageView = (ImageView) fxmlLoader.getNamespace().get("imageView");

        // Set up the stage properties
        stage.setTitle("Add Playlist");
        stage.initModality(Modality.APPLICATION_MODAL);
        setDialogPane(createPlaylistDialogPane());

        if (!nix) {
            fxName.setText(newPlaylist.getName());
            if (newPlaylist.getName().isEmpty()) {
                fxName.setStyle("-fx-background-color: red");
            }
        }
    }

    private DialogPane createPlaylistDialogPane() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/nyrplaylistdialog-view.fxml"));
            fxmlLoader.setController(this);
            return fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException("Unable to load FXML", exception);
        }
    }



    @FXML
    private void onChooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Playlist Cover Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
        }
    }

    @FXML
    private void onAddPlaylist() {
        String playlistName = playlistNameField.getText().trim();
        // Add playlist logic here...
        stage.close();
    }

}
