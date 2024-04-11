/******************************************************************************
 *  Nafn    : Daníel Ágúst Björnsson
 *            Guðni Emil
 *  T-póstur: dab47@hi.is
 *
 *
 *  Lýsing  : not in use
 *
 *
 *****************************************************************************/
package hi.vidmot.audioplayer;

import hi.vinnsla.audioplayer.Image_management;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.List;

public class AudioplayerController {
    public ImageView image_11;
    public Button button_11;
    @FXML
    public GridPane grid_home;
    private final int Grid_row_size = 2;//height
    private final int Grid_col_size = 2; // width

//    Stage stage = (Stage) grid_home.getScene().getWindow();

    public void initialize(){
//
//        col.prefWidth(50);
        grid_home.setAlignment(Pos.CENTER);

        Image_management image_management = new Image_management(Grid_col_size, Grid_row_size);
        image_management.add_images_button(grid_home, "Heima","Heima" , 0, 0); // if one is 0 then width and height are not used
//        add_images(grid_home);
//        System.out.println(grid_home.getColumnConstraints().getFirst().getMaxWidth());


    }



    @FXML
    protected void onHelloButtonClick(ActionEvent event) {
//        System.out.println("hello");
//        welcomeText.setText("Welcome to JavaFX Application!");
//        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//        Window theStage = source.getScene().getWindow();

    }



    public ColumnConstraints getcolByRowColumnIndex (final int column, GridPane gridPane) {
        ColumnConstraints result = null;
        ObservableList<ColumnConstraints> colums = gridPane.getColumnConstraints();
        int counter = 0;
        for (ColumnConstraints colum : colums) {
            if(counter == column) {
                result = colum;
                break;
            }
        }


        return result;
    }


}