package hi.vidmot.audioplayer.controller;

import hi.vidmot.audioplayer.AskrifandiDialog;
import hi.vidmot.audioplayer.interface_class.PlayerControllerInterface;
import hi.vidmot.audioplayer.view.ViewSwitcher;
import hi.vinnsla.audioplayer.*;
import hi.vidmot.audioplayer.view.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayerController implements PlayerControllerInterface {

    public ListView<Lagalisti> fxListView;
    public Label img_lagalisti;
    public Button fxSelect;
    Scene scene;
    public GridPane grid_home;
    public static final int Grid_row_size = 2;//height
    public static final int Grid_col_size = 2; // width
    public Button fxLogin;
    private static final String filename1 = "src/main/resources/hi/vidmot/audioplayer/media/Txt_(names)/Heim/";
    private static final String filename2 = "src/main/resources/hi/vidmot/audioplayer/media/Txt_(names)/Lagalisti/";
    private Lagalisti curr_lagalisti;
    private Image_management image_management;
    @FXML
    private Menu_laglistiController menuStyringController;
    private ObservableList<String> names = FXCollections.observableArrayList();;


    /**
     *
     */
    @Override
    public void initialize() {

//        col.prefWidth(50);
//        grid_home.setAlignment(Pos.CENTER);
        menuStyringController.setPlayerController(this);

        this.image_management = new Image_management(Grid_col_size, Grid_row_size);
//        image_management.add_images_button(grid_home, "Heima","Heima", 0,0 ); // if one is 0 then width and height are not used

//        Lagalistar.setAmount(1);
//        getAmount();

        fxSelect.setDisable(true);
        names = TxtFile_management.getnames(filename1,"lagalisti",-1);
        Lagalistar lagalistar = new Lagalistar(getAmount(),names);
        setLaglista();
//        String uuid = JsonFile_management.addJson(filename+"Lagalisti_","log", lagalisti.getLagalistaIndex(), songNew.getName());
//        vistaJson(temp, path + index+"/"+baseName+".json");
//        String filename = "src/main/resources/hi/vidmot/audioplayer/media/Txt_(names)/Heim/lagalisti.json";
//        addJson(String path, String baseName, int index, String name)
//        nagement.writeJson(names,filename);

    }
    private void setLaglista(){

        ;        ObservableList<Lagalisti> lagalistis = Lagalistar.getLagalista_list();
        ObservableList<String> lines = TxtFile_management.getnames(filename1,"lagalisti",-1);
        String filename = "src/main/resources/hi/vidmot/audioplayer/media/Txt_(names)/Heim";
        ObservableList<String> laglisatNames = JsonFile_management.toNameList(filename,"lagalisti");
        AtomicInteger counter = new AtomicInteger();
//        System.out.println((lines == null) + " is null");
//        ObservableList<LagData> lagDatas = JsonFile_management.lesaJson(filename,"lagalisti", -1);
//        String uuid = JsonFile_management.removeJson(filename,"lagalisti", -1, 0);
        lagalistis.forEach((lagalisti) -> {

            if (true){
                try{
                    lagalisti.setName(laglisatNames.get(counter.get()));
                }catch (IndexOutOfBoundsException e){

                }

            }
            counter.getAndIncrement();

            fxListView.getItems().add(lagalisti);

        });

    }

    public int getAmount_val(){
        ObservableList<String> lines = TxtFile_management.getData(filename1,"Data");

//        names =lines;
        return Integer.parseInt(lines.get(0));
    }
    public ObservableList<Integer> getAmount(){
        int amount = getAmount_val();
        ObservableList<Integer> amount_list = FXCollections.observableArrayList();
        for (int i = 0; i < amount; i++){
            amount_list.add(JsonFile_management.toNameList(filename2+"Lagalisti_","log",i).size());
        }

        return amount_list;
    }

    public void setAmount(int amount){
        TxtFile_management.setData(filename1,"Data", String.valueOf(amount));
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * not in use
     * @param mouseEvent temp
     */
    @Override
    public void onVeljaLista(ActionEvent mouseEvent) { //TODO fix this so it uses listview
        Node node = (Node) mouseEvent.getSource() ;
        int row_index = GridPane.getRowIndex(node);
        int col_index = GridPane.getColumnIndex(node);
//        Lagalistar.index = 2*row_index+col_index;
        Button button = (Button) node;
        ImageView imageView = (ImageView)button.getGraphic();
        Image image =  imageView.getImage();
        System.out.println(image.getUrl() + " Url ello");
        Lagalistar.setIndex((row_index * Grid_row_size) + col_index); // set the index of the lagalisti that is chosen TODO change this to just use the index of the listview
        Lagalistar.set_CulAndRow_index(col_index, row_index);
//        Lagalistar.setAmount(1); // temp
//        ViewSwitcher.setSceneHeight(450);// set the height of the window
        ViewSwitcher.switchTo_WithSize(View.LISTI, false, 300, 500);

    }

    /**
     * Loggar áskrifanda inn
     *
     * @param actionEvent temp
     */
    @Override
    public void onLogin(ActionEvent actionEvent) {
        Askrifandi askrifandi = new Askrifandi(" ");
        AskrifandiDialog askrifandiDialog = new AskrifandiDialog(askrifandi);
        Optional<Askrifandi> result = askrifandiDialog.showAndWait();
        if (result.isPresent()) {
            askrifandi = result.get();
            fxLogin.setText(askrifandi.getNafn());

        }

    }

    public void onValidLaglista(MouseEvent mouseEvent) {
        Lagalisti lagalisti = fxListView.getSelectionModel().getSelectedItem();
        this.curr_lagalisti = lagalisti;
        Lagalistar.setIndex(curr_lagalisti.getLagalistaIndex());
        image_management.add_images_label(img_lagalisti, curr_lagalisti.getImg(),0,0);
        fxSelect.setDisable(false);



    }

    public void onSelect(ActionEvent actionEvent) {


        ViewSwitcher.switchTo_WithSize(View.LISTI, false, 300, 500);
    }
}
