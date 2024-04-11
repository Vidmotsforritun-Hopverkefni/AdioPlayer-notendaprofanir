package hi.vinnsla.audioplayer;

import hi.vidmot.audioplayer.AudioplayerApplication;
import hi.vidmot.audioplayer.view.ViewSwitcher;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.Objects;

/******************************************************************************
 *  Nafn    : Daníel Ágúst Björnsson
 *  T-póstur: dab47@hi.is
 *
 *  Lýsing  : class for Image_management
 *
 *
 *****************************************************************************/
public class Image_management {
    private int Grid_col_size = 2; // width
    private int Grid_row_size = 2;//height
    private static String filepath = "file:src/main/resources/hi/vidmot/audioplayer/";

    public Image_management(int grid_col_size, int grid_row_size) {
        Grid_col_size = grid_col_size;
        Grid_row_size = grid_row_size;
    }

    /**
     * modifys the button
     * @param col colum of the button
     * @param row row of the button
     * @param gridPane the gridpane the button is in
     * @param imageView the imageveiw the button receves
     */
    protected void modify_button(int col, int row, GridPane gridPane,ImageView imageView){
        Node col_in = getNodeByRowColumnIndex(col,row, gridPane);
        if (col_in instanceof Button button){
//
            gridPane.getChildren().remove(button);

//            imageView.fitHeightProperty().bind(button.heightProperty());
//            imageView.fitWidthProperty().bind(button.widthProperty());

            imageView.fitHeightProperty().bind(gridPane.heightProperty().divide(Grid_row_size));
            imageView.fitWidthProperty().bind(gridPane.widthProperty().divide(Grid_col_size));

            button.setGraphic(imageView);

            gridPane.add(button, col, row);
        }
    }
    private Button modify_button(Button button, ImageView imageView) {
        imageView.fitHeightProperty().bind(button.heightProperty().divide(Grid_row_size));
        imageView.fitWidthProperty().bind(button.widthProperty().divide(Grid_col_size));

        button.setGraphic(imageView);
        return button;
    }

    /**
     * adds an imageveiw to label
     * @param label label
     * @param imageView
     */
    protected void modify_Label(Label label,ImageView imageView){

        imageView.fitHeightProperty().bind(label.heightProperty());
        imageView.fitWidthProperty().bind(label.widthProperty());

        label.setGraphic(imageView);


    }


    /**
     *  gets the node of the gridpane by the row and colum
     * @param row
     * @param col
     * @param gridPane
     * @return
     */
    private Node getNodeByRowColumnIndex (final int col, final int row, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                result = node;
                break;
            }
        }

        return result;
    }

    /**
     * makes the image
     * @param path the path to the image
     * @param width the width of the image. if it is 0 then it and height are not used
     * @param height the height of the image. if it is 0 then it and width are not used
     * @return an image
     */
    private static Image make_image(String path, double width, double height) {
//        URL imgURL = AudioplayerApplication.class.getResource(path);
//        System.out.println(imgURL);
        Image image = null;
        if (width == 0.0 || height == 0.0){
            image =  new Image(Image_management.filepath+path);
        }else {
            image =  new Image(Image_management.filepath+path, width, height, true, true);
        }

        try{

            if (!image.isError()) {
                 return image;// this is we can add and remove img
            } else {
                System.err.println("Fann ekki skrána " + Image_management.filepath+path);
                // "file:src/main/resources/hi/vidmot/audioplayer/";
                return  new Image(Image_management.filepath+"media/img/Heima/img_not_found.png", width, height, true, true);
//                return null;
            }

//            return new Image(Image_management.filepath+path); // this is we can add and remove img
        }catch (Exception ignored){
            System.err.println("Fann ekki skrána " + Image_management.filepath+path);
            return null;
        }

    }
    /**
     * reads the img from the folders
     * @param folder the folder the img is in
     * @param base_name the name of the file without "_x_x.mp3"
     * @param col the colum of the lagalisti
     * @param row the row of the lagalisti
     * @param width the width of the image. if it is 0 then it and height are not used
     * @param height the height of the image. if it is 0 then it and width are not used
     */
    private static Image make_image(String folder, String base_name, int col, int row, double width, double height) {
        String path = "media/img/";
//        if(Objects.equals(folder, "Main")){
//            System.out.println(path + folder + "/" + base_name + "_" +  row + "_" + col+ ".png");
//        }
        // filename+path + folder + "/" + base_name + "_" +  row + "_" + col+ ".png"
        return make_image(path + folder + "/" + base_name + "_" +  row + "_" + col+ ".png", width, height);
    }
    /**
     * reads the img from the folders <br>
     * file:src/main/resources/hi/vidmot/audioplayer/media/img/+folder+ "/" + base_name + "_" +  index+ ".png"
     * @param folder the folder the img is in
     * @param base_name the name of the file without "_x_x.mp3"
     * @param index the index of the lagalisti
     * @param width the width of the image. if it is 0 then it and height are not used
     * @param height the height of the image. if it is 0 then it and width are not used
     */
    public static Image make_image(String folder, String base_name, int index, double width, double height) {
        String path = "media/img/";
//        if(Objects.equals(folder, "Main")){
//            System.out.println(path + folder + "/" + base_name + "_" +  index+ ".png");
//        }

        return make_image(path + folder + "/" + base_name + "_" +  index+ ".png", width, height);
    }
    /**
     * reads the music from the folders
     * @param folder the folder the img is in
     * @param base_name the name of the file without "_x_x.png"
     * @param index the index of the lagalisti
     * @param width the width of the image. if it is 0 then it and height are not used
     * @param height the height of the image. if it is 0 then it and width are not used
     */
    private static Image make_image_label(String folder, String base_name, int index, double width, double height) {
        String path = "media/img/";
        return make_image(path + folder  + "_" +  index+ "/" + base_name + ".png", width, height);
    }



    /**
     * adds images to all buttons in the gridpane
     * @param gridPane
     * @param folder
     * @param base_name
     * @param width the width of the image. if it is 0 then it and height are not used
     * @param height the height of the image. if it is 0 then it and width are not used
     */
    public void add_images_button(GridPane gridPane, String folder, String base_name, double width, double height){
//        int val = (2*1)+1;
//        System.out.println("Hello: "+ val);
//        System.out.println("media/img/"+"Heima"+"/"+ "Heima" + "_" + 1 +"_"+ 1);
        for (int col = 0; col < Grid_col_size; col++) { //TODO fix this
            for (int row = 0; row < Grid_row_size; row++) {
//                System.out.println(row+ ", " + col);
//                Image image = make_image("media/img/fb5ebc26-6607-4735-b63b-de85d605eafd.png");
                Image image = make_image(folder, base_name, (col * Grid_col_size) + row, width, height);
//                int val =
                ImageView imageView = new ImageView(image);

//                imageView.fitHeightProperty().bind(grid_home.heightProperty().subtract((grid_home.getPrefWidth())).divide(Grid_row_size));
//                imageView.fitWidthProperty().bind(grid_home.widthProperty().subtract(grid_home.getPrefWidth()).divide(Grid_col_size));
                imageView.setPreserveRatio(true);

                modify_button(col,row,gridPane, imageView);
//
            }
        }
    }
    public Button add_image_button(Button button, String folder, String base_name,int index ,double width, double height){
        //image_management.add_images_button(button,"Main", "button", 25,25);
        Image image = make_image(folder, base_name, index, width, height);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        modify_button(button, imageView);
        return button;

    }



    /**
     * main func for adding image to label
     * @param label label itself
     * @param folder the folder the image is in
     * @param base_name the base name of it ($base_name_$row_$col.png)
     * @param index the index
     * @param istop is it the top image or the bottom
     * @param width the width of the image. if it is 0 then it and height are not used
     * @param height the height of the image. if it is 0 then it and width are not used
     */
    public void add_images_label(Label label, String folder, String base_name,int index, boolean istop, double width, double height){
//        System.out.println("media/img/"+"Heima"+"/"+ "Heima" + "_" + 1 +"_"+ 1);
        // folder = Lagalisti.
//        System.out.println("index Label:" + index);
//        System.out.println("index:" + index);
//                Image image;
        if(istop){
            Image image = make_image(folder, base_name, index, width, height);
            ImageView imageView = new ImageView(image);
            imageView.setPreserveRatio(true);

            modify_Label(label, imageView);
            System.out.println(image.getUrl() +" hhhurl");
        }else {
            Image image = make_image_label(folder, base_name, index, width, height);
            ImageView imageView = new ImageView(image);
            imageView.setPreserveRatio(true);

            modify_Label(label, imageView);
            System.out.println(image.getUrl() +" hhhurl");
        }




    }

    /**
     * does the same thing as the other version this func but simpler
     * @param label
     * @param path
     * @param width the width of the image. if it is 0 then it and height are not used
     * @param height the height of the image. if it is 0 then it and width are not used
     */
    public void add_images_label(Label label, String path, double width, double height){
//        System.out.println("media/img/"+"Heima"+"/"+ "Heima" + "_" + 1 +"_"+ 1);
        // folder = Lagalisti

//                Image image;

        Image image = make_image(path, width, height);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);

        modify_Label(label, imageView);




    }

//    public void add_images_main()

    /**
     * not in use
     * @param gridPane
     */
    public void add_imagesv2(GridPane gridPane){
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            Image image = make_image("media/img/fb5ebc26-6607-4735-b63b-de85d605eafd.png", 1,1);
            ImageView imageView = new ImageView(image);
//                imageView.fitHeightProperty().bind(grid_home.heightProperty().subtract((grid_home.getPrefWidth())).divide(Grid_row_size));
//                imageView.fitWidthProperty().bind(grid_home.widthProperty().subtract(grid_home.getPrefWidth()).divide(Grid_col_size));
            imageView.setPreserveRatio(true);
            int col = GridPane.getColumnIndex(node);
            int row = GridPane.getColumnIndex(node);
            modify_buttonv2(col,row,gridPane, imageView, (Button) node);
        }
    }

    private void modify_buttonv2(int col, int row, GridPane gridPane, ImageView imageView, Button button){


        //
        gridPane.getChildren().remove(button);

        //            imageView.fitHeightProperty().bind(button.heightProperty());
        //            imageView.fitWidthProperty().bind(button.widthProperty());
        imageView.fitHeightProperty().bind(gridPane.heightProperty().divide(Grid_row_size));
        imageView.fitWidthProperty().bind(gridPane.widthProperty().divide(Grid_col_size));

        button.setGraphic(imageView);

        gridPane.add(button, col, row);

    }


}


