package hi.vidmot.audioplayer.controller;

import hi.vidmot.audioplayer.SongNewDialog;
import hi.vidmot.audioplayer.view.View;
import hi.vidmot.audioplayer.view.ViewSwitcher;
import hi.vinnsla.audioplayer.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class AddLagController {


    public ListView fxListView;
    public Label img_lagalisti;

    private Lagalisti lagalisti;

    private ObservableList<String> lagNames = FXCollections.observableArrayList();

    private final Image_management image_management = new Image_management(2, 2);

    private static final String filename = "src/main/resources/hi/vidmot/audioplayer/media/Txt_(names)/Lagalisti/";
    private Button add_button;
    private SongNew songNew_old = new SongNew();

    @FXML
    private Menu_lagController menuStyringController;



    public void initialize(){
//        fxListView.getItems().add(add_addNewSong());
        menuStyringController.setAddLagController(this); // connects menucontroller and listontroller
        menuStyringController.fxNew.setDisable(true);
        menuStyringController.fxDelete.setDisable(true);
        menuStyringController.fxRename.setDisable(true);
        setLog();
        image_management.add_images_label(img_lagalisti, "Heima", "Heima",lagalisti.getLagalistaIndex(),true, 0, 0); // if one is 0 then width and height are not used

//        System.out.println(fxListView.getItems().indexOf(add_button));

    }

    public void setLog(){
//        ObservableList<Lag> listi = lagalisti.getListi();
        this.lagalisti = Lagalistar.getLagalista_list().get(Lagalistar.index);
        ObservableList<Lag> listi = lagalisti.getListi();
//        lagNames = TxtFile_management.getnames(filename+"Lagalisti_", "log", lagalisti.getLagalistaIndex());
        lagNames = JsonFile_management.toNameList(filename+"Lagalisti_","log", lagalisti.getLagalistaIndex());
        AtomicInteger counter = new AtomicInteger();

        listi.forEach((lag) -> {
            fxListView.getItems().add(add_addNewSong_Label(lagNames.get(lag.getIndex())));
            counter.getAndIncrement();
        });
        add_button = add_addNewSong_Button();
//        System.out.println(lagNames.size());
        fxListView.getItems().add(add_button);

    }
    public Button add_addNewSong_Button(){
        Button button = new Button("add new song");
        VBox.setVgrow(button, Priority.ALWAYS);
//        label.setMaxWidth(Re);
//        fxListView.getItems().getLast();
//        fxListView.prefWidthProperty().bind(button.widthProperty());
//        button.prefHeightProperty().bind(fxListView.heightProperty().divide());
//        System.out.println(fxListView.getFixedCellSize() + " size");
//        button.setminHeight(fxListView.getFixedCellSize());
        button.minWidthProperty().bind(ViewSwitcher.getScene().getWindow().widthProperty().subtract(30));

        button.setOnAction(this::onAddLagv1);
        return button;
    }

    /**
     *
     * @param name
     * @return
     */
    public Label add_addNewSong_Label(String name){
        Label label = new Label(name);
        VBox.setVgrow(label, Priority.ALWAYS);
//        label.setMaxWidth(Re);
        fxListView.prefWidthProperty().bind(label.widthProperty());
        return label;
    }
    public void onAddLagv1(ActionEvent event){
//        System.out.println("hello in button");
        fxListView.getItems().remove(add_button);
        int counter = 0;
        boolean iscorr = addNewSong(true);
        while (!iscorr){
            iscorr = addNewSong(iscorr);
            if(counter >100){
                break;
            }
            counter++;
        }

        fxListView.getItems().add(add_button);

    }

    public boolean addNewSong(boolean iscorr){

//        fxListView.getItems().add("hello");
        SongNew songNew = new SongNew();
        SongNewDialog songNewDialog = new SongNewDialog(songNew, songNew_old, lagalisti, lagNames.size(), iscorr);
        Optional<SongNew> result = songNewDialog.showAndWait();
        if (result.isPresent()) {
            songNew = result.get();
//            fxLogin.setText(askrifandi.getNafn());
            songNew_old = songNew;
            if(songNew.imgFilename.isEmpty() || songNew.songFilename.isEmpty() || songNew.getName().isEmpty()){
                System.err.println("not value");
                if(songNew.getName().isEmpty()){
                    songNew_old.NameWrong = true;
                }
                return false;
            }else{ // main if successful
//                String uuid = JsonFile_management.addJson(filename +"Lagalisti_", "log",lagalisti.getLagalistaIndex(), songNew.getName());
//                              JsonFile_management.addJson(filename+"Lagalisti_","log", lagalisti.getLagalistaIndex(), songNew.getName());
//                System.out.println("this: " +JsonFile_management.toNameList(filename+"Lagalisti_","log", lagalisti.getLagalistaIndex()));
//                if(ifNameAvailable(songNew.getName())){
//                    System.err.println("name in use " + songNew.getName());
//                    songNew_old.NameWrong = true;
//                    return false;
//                }
                if (songNew.isSongWrong() || songNew.isImgWrong()){
                    if(songNew.isImgWrong()){
                        String aukaPath = "/media/img/Lagalisti_"+lagalisti.getLagalistaIndex()+"/";
                        System.err.println("img file not found " + aukaPath + songNew.getName()+".png");
                        songNew_old.imgWrong = true;
                    }
                    if(songNew.isSongWrong()){
                        String aukaPath = "/media/music/Lagalisti_"+lagalisti.getLagalistaIndex()+"/";
                        System.err.println("Audio file not found " + aukaPath +songNew.getName()+".mp3");
                        songNew_old.songWrong = true;
                    }
                    return false;
                }else {
                    String aukaPath = "/media/img/Lagalisti_"+lagalisti.getLagalistaIndex()+"/";
                    String uuid = JsonFile_management.addJson(filename+"Lagalisti_","log", lagalisti.getLagalistaIndex(), songNew.getName());
                    File_management.copyFile(aukaPath,uuid+".png",songNew.imgFilename);
                    aukaPath = "/media/music/Lagalisti_"+lagalisti.getLagalistaIndex()+"/";
                    //path + index+"/"+baseName+".json"
                    //filename+"Lagalisti_","log", i
                    //"src/main/resources/hi/vidmot/audioplayer/"
                    //"src/main/resources/hi/vidmot/audioplayer/media/Txt_(names)/Lagalisti/"

                    File_management.copyFile(aukaPath,uuid+".mp3",songNew.songFilename, songNew.getName());

                }
//                if(songNew.song != null){
//                    songNew_old.songWrong = false;
//                }
//                if(songNew.img != null){
//                    songNew_old.imgWrong = false;
//                }






//                TxtFile_management.addname(filename, lagalisti.getLagalistaIndex(), songNew.getName(), lagNames);
//                JsonFile_management.addJson(filename+"Lagalisti_","log", lagalisti.getLagalistaIndex(), songNew.getName());
//                lagNames = TxtFile_management.getnames(filename+"Lagalisti_","log", lagalisti.getLagalistaIndex());
                lagNames = JsonFile_management.toNameList(filename+"Lagalisti_","log", lagalisti.getLagalistaIndex());
                Lagalistar.addLag(lagNames.get(lagNames.size()-1));
//                System.out.println(lagalisti.getListi() + " hér er listinn");
//                System.out.println(lagalisti.getAmount() + " hér er amount");

//                System.out.println(songNew.getName().length() + " is value " + songNew.imgFilename+ ", " + songNew.songFilename+ ", "+ songNew.getName());
                fxListView.getItems().add(songNew.getName());
                return true;

            }



        }
        return true;

    }

    public boolean ifNameAvailable(String name){
//        System.out.println("hello " + name);
//        AtomicBoolean res = new AtomicBoolean(false);
//        lagNames.forEach((name_in)->{
//            if(name_in == name){
//                res.set(true);
//            }
//        });
////        System.out.println("res " + res.get());
//        return res.get();
        return lagNames.contains(name);
    }
    public void onAddLag(MouseEvent mouseEvent) {
    }
//    private void addImage(File file){
//        String aukaPath = "/media/img/Lagalisti_"+lagalisti.getCul_index()+"_"+lagalisti.getRow_index()+"/";
//
//        File_management.copyFile(aukaPath,"Hello"+lagNames.size()+".png",file);
//    }

    public void onDone(){
        ViewSwitcher.switchTo_WithSize(View.LISTI, false, 300, 450);
    }
}
