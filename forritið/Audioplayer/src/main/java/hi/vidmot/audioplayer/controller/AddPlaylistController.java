package hi.vidmot.audioplayer.controller;

import hi.vidmot.audioplayer.addPlaylistDialog;
import hi.vidmot.audioplayer.view.View;
import hi.vidmot.audioplayer.view.ViewSwitcher;
import hi.vinnsla.audioplayer.Lagalisti;
import hi.vinnsla.audioplayer.NewPlaylist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

public class AddPlaylistController {

    private Lagalisti lagalisti;

    @FXML
    private VBox buttonContainer;


    @FXML
    private TextField playlistNameField;

    @FXML
    private ListView playlistListView;

    @FXML
    private Button add_button;


    public void initialize() {
        add_button = createAddButton();
        buttonContainer.getChildren().add(add_button);
        playlistListView.getItems().add(add_button);
    }

    private Button createAddButton() {
        Button button = new Button("Add playlist");
        button.setOnAction(this::onAddPlaylist);
        VBox.setVgrow(button, Priority.ALWAYS);
        button.minWidthProperty().bind(buttonContainer.widthProperty().subtract(30));
        return button;
    }

    @FXML
    private void addPlaylist(ActionEvent actionEvent) {
        String newPlaylistName = playlistNameField.getText().trim();
        if (!newPlaylistName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Confirm Playlist Addition");
            alert.setContentText("Are you sure you want to add the playlist: " + newPlaylistName + "?");

            alert.showAndWait().ifPresent(response -> {
                if (response == javafx.scene.control.ButtonType.OK) {
                    playlistListView.getItems().add(newPlaylistName);
                    playlistNameField.clear();
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid playlist name!");
            alert.showAndWait();
        }
    }

    @FXML
    private void onAddPlaylist(ActionEvent actionEvent) {
        // Create a TextInputDialog to prompt the user for the playlist name
        TextInputDialog dialog = new TextInputDialog("New Playlist");
        dialog.setTitle("New Playlist");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter playlist name:");


        dialog.initOwner((Stage) playlistListView.getScene().getWindow());
        dialog.initModality(Modality.WINDOW_MODAL);
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            playlistListView.getItems().remove(add_button);
            playlistListView.getItems().add(name);
            playlistListView.getItems().add(add_button);
        });

    }

    @FXML
    private void onDeletePlaylist(ActionEvent actionEvent) {
        // Check if a playlist is selected
        int selectedIndex = playlistListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1 && !(playlistListView.getItems().get(selectedIndex) instanceof Button)) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Playlist");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete the selected playlist?");

            // Show the dialog and wait for the user's response
            alert.initOwner((Stage) playlistListView.getScene().getWindow());
            alert.initModality(Modality.WINDOW_MODAL);
            Optional<ButtonType> result = alert.showAndWait();

            // If the user confirms, delete the playlist
            result.ifPresent(buttonType -> {
                if (buttonType == ButtonType.OK) {

                    playlistListView.getItems().remove(selectedIndex);
                }
            });
        } else {
            // If no playlist is selected, show a warning
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please select a playlist to delete.");
            alert.showAndWait();
        }
    }
    @FXML
    private void onDone() {
        ViewSwitcher.switchTo_WithSize(View.HEIMA, false, 300, 450);
    }
}
