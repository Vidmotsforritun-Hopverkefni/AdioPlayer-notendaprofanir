package hi.vidmot.audioplayer.controller;

import hi.vidmot.audioplayer.view.View;
import hi.vidmot.audioplayer.view.ViewSwitcher;
import hi.vinnsla.audioplayer.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class EditSongController {

    public ListView fxListView;
    public Label img_lagalisti;
    public ListView fxListview_trash;
    public HBox fxHBox;
    @FXML
    private Menu_lagController menuStyringController;
    private Lagalisti lagalisti;
    private ObservableList<String> lagNames = FXCollections.observableArrayList();
    private static final String filename1 = "src/main/resources/hi/vidmot/audioplayer/media/Txt_(names)/Lagalisti/";
    private static final String filename2 = "src/main/resources/hi/vidmot/audioplayer/media/";
    private final Image_management image_management = new Image_management(2, 2);
    private static final String filename = "src/main/resources/hi/vidmot/audioplayer/media/Txt_(names)/Lagalisti/";

    public void initialize(){
        menuStyringController.setEditSongController(this); // connects menucontroller and editSongController
        setLog();
        image_management.add_images_label(img_lagalisti, "Heima", "Heima",lagalisti.getLagalistaIndex(),true, 0, 0); // if one is 0 then width and height are not used

    }

    public void setLog(){
//        ObservableList<Lag> listi = lagalisti.getListi();
        this.lagalisti = Lagalistar.getLagalista_list().get(Lagalistar.index);
        ObservableList<Lag> listi = lagalisti.getListi();
        lagNames = JsonFile_management.toNameList(filename1+"Lagalisti_","log", lagalisti.getLagalistaIndex());
        AtomicInteger counter = new AtomicInteger();

//        listi.forEach((lag) -> {
//            HBox hBox = new HBox();
//            Label newSong_label = add_addNewSong_Label(lagNames.get(lag.getIndex()));
//            hBox.getChildren().add(newSong_label);
//            Button button = new Button();
//
//            button.minHeight(20);
//            button.minWidth(20);
//            button.prefHeight(20);
//            button.prefWidth(20);
//            button.maxHeight(20);
//            button.maxWidth(20);
//            button.setPadding(new Insets(0,0,0,0));
//            button.setAlignment(Pos.CENTER);
//            ImageView imageView = new ImageView(Image_management.make_image("Heima","trash",0,20,20));
////            imageView.fitHeightProperty().bind(button.heightProperty());
////            imageView.fitWidthProperty().bind(button.widthProperty());
//            imageView.minHeight(20);
//            imageView.maxHeight(20);
//            imageView.minWidth(20);
//            imageView.maxWidth(20);
//            imageView.prefHeight(20);
//            imageView.prefWidth(20);
//            button.setGraphic(imageView);
//            hBox.getChildren().add(button);
//            int minus = 50;
////            fxListView.prefWidthProperty().bind(hBox.widthProperty());
////            hBox.minWidthProperty().bind(ViewSwitcher.getScene().getWindow().widthProperty().subtract(minus-20));
////            newSong_label.minWidthProperty().bind(ViewSwitcher.getScene().getWindow().widthProperty().subtract(minus+20));
//            HBox.getHgrow(newSong_label);
//            fxListView.getItems().add(lag);
////            fxListview_trash.getItems().add("b");
////            fxListView.onScrollProperty().bind(fxListview_trash.getOnScroll());
//            // Bind the ListView scroll property
//
//            counter.getAndIncrement();
//        });



//        bind_sroll();
//        ViewSwitcher.getStage().
//        add_button = add_addNewSong_Button();
////        System.out.println(lagNames.size());
//        fxListView.getItems().add(add_button);
//        fxListview_trash.scroll
        fxListView.getItems().addAll(listi);

    }

    private void bind_sroll(){
//        System.out.println("hell.o");
//        Node n1 = fxListView.lookup(".scroll-bar");
//        if (n1 instanceof ScrollBar) {
//            System.out.println("hell.o1");
//            final ScrollBar bar1 = (ScrollBar) n1;
//            Node n2 = fxListview_trash.lookup(".scroll-bar");
//            if (n2 instanceof ScrollBar) {
//                System.out.println("hell.o2");
//                final ScrollBar bar2 = (ScrollBar) n2;
//                bar1.valueProperty().bindBidirectional(bar2.valueProperty());
//            }
//        }
        System.out.println("outside");
        ViewSwitcher.getStage().setOnShowing(new EventHandler<WindowEvent>(){
            boolean hasRun = false;

            @Override
            public void handle(WindowEvent arg0) {
                System.out.println("hello vf");
                if(!hasRun){
                    System.out.println(fxListView.lookupAll(".scroll-bar") + " is what");
                    for (Node node: fxListView.lookupAll(".scroll-bar")) {
                        System.out.println("heæl1");
                        if (node instanceof ScrollBar) {
                            System.out.println("heæl");
                            final ScrollBar bar = (ScrollBar) node;
                            bar.valueProperty().addListener(new ChangeListener<Number>() {
                                @Override public void changed(ObservableValue<? extends Number> value, Number oldValue, Number newValue) {
                                    System.out.println(bar.getOrientation() + " " + newValue);
                                }
                            });
                        }
                    }
                    hasRun = true;
                }
            }});
    }

    public Label add_addNewSong_Label(String name){
        Label label = new Label(name);
        VBox.setVgrow(label, Priority.ALWAYS);
//        label.setMaxWidth(Re);
        fxListView.prefWidthProperty().bind(label.widthProperty());
        return label;
    }
    public void onDone(ActionEvent actionEvent) {
        ViewSwitcher.switchTo_WithSize(View.LISTI, false, 300, 500);
    }

    public void onRename(ActionEvent actionEvent) {
        Lag selectedLag = (Lag) fxListView.getSelectionModel().getSelectedItem();
        if (selectedLag != null){
            selectedLag.setName("hell2");
            String uuid = JsonFile_management.replaceJson(filename+"Lagalisti_","log", lagalisti.getLagalistaIndex(), selectedLag.getIndex(), "hello2");
        }
    }

    @FXML
    protected void removeListItem(ActionEvent event) {
        // Fá valinn hlut í ListView
//        String uuid = JsonFile_management.removeJson(filename,"lagalisti", -1, 0);
        Lag selectedLag = (Lag) fxListView.getSelectionModel().getSelectedItem();
        if (selectedLag != null) {
            String uuid = JsonFile_management.removeJson(filename+"Lagalisti_","log", lagalisti.getLagalistaIndex(), selectedLag.getUuid());
//            String uuid = JsonFile_management.addJson(filename+"Lagalisti_","log", lagalisti.getLagalistaIndex(), songNew.getName());
            // Fjarlægja valinn hlut úr lista
            lagalisti.fjarlaegjaLag(selectedLag);
            // Endurhlaða ListView til að birta nýja lista
            fxListView.getItems().remove(selectedLag);
        }
    }
}
