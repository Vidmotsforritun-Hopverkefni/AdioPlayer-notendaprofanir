package hi.vidmot.audioplayer;

import hi.vinnsla.audioplayer.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;

import java.io.File;
import java.io.IOException;

public class SongNewDialog extends Dialog<SongNew> {
    private int index;
    public TextField fxImage;
    public TextField fxAudio;
    private SongNew songNew;
    @FXML
    private Label fxImageVieiw;
    @FXML
    private TextField fxNafn;

    private Lagalisti lagalisti;
    public String filepath = "src/main/resources/hi/vidmot/audioplayer/";

    public static Image_management image_management = new Image_management(2,2);
    public SongNewDialog(SongNew songNew, SongNew songNew_old,Lagalisti lagalisti,int index, boolean iscor) {

        this.songNew = songNew;
        this.lagalisti = lagalisti;
        this.index = index;
        setDialogPane(lesaSongNewDialog());

        if(!iscor){
            fxNafn.setText(songNew_old.getName());
            fxImage.setText(songNew_old.imgFilename);
            fxAudio.setText(songNew_old.songFilename);

            if(songNew_old.getName().isEmpty() || songNew_old.NameWrong){
                fxNafn.setStyle("-fx-border-color: red; -fx-border-width: 1; -fx-border-radius: 2;");
            }
            if(songNew_old.imgFilename.isEmpty() || songNew_old.imgWrong){
                fxImage.setStyle("-fx-border-color: red; -fx-border-width: 1; -fx-border-radius: 2;");
//                fxImage.setBackground(Background.fill(Paint.valueOf("red"))));
            }else{
                Image image = new Image("file:"+ songNew_old.imgFilename, 100, 100, true, true);
                ImageView imageView = new ImageView(image);
                fxImageVieiw.setGraphic(imageView);
            }
//            System.out.println(songNew_old.songFilename.length() + ", " +songNew_old.songFilename);
            if(songNew_old.songFilename.isEmpty() || songNew_old.songWrong){
                fxAudio.setStyle("-fx-border-color: red; -fx-border-width: 1; -fx-border-radius: 2;");
            }
        }
        // sett regla um hvenær í lagi hnappur er virkur
//        iLagiRegla();
        // Búum til hlut af nýjum nafnlausum innri klasa sem útfærir interface
        // Callback fyrir klasana ButtonType og Leikmenn
        // Callback hefur eina aðferð og við endurforritum hana

//        final Button btOk = (Button) getDialogPane().lookupButton(ButtonType.OK);
//        btOk.addEventFilter(
//                ActionEvent.ACTION,
//                event -> {
//                    // Check whether some conditions are fulfilled
//                    event.consume();
//                }
//        );
        setResultConverter(b -> {                                 // b er af taginu ButtonType
            if (b.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                //TODO set all the values (i think it is done)
                songNew.setName(fxNafn.getText());
                songNew.songFilename = fxAudio.getText();
                songNew.imgFilename = fxImage.getText();
                return songNew;

            } else {
                return null;
            }
        });         // endir á d.setResultConverter

    }

    private DialogPane lesaSongNewDialog() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/song_new-view.fxml"));
        try {
            fxmlLoader.setController(this);
            return fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void onImage(ActionEvent actionEvent) {
        File file =  File_management.getFileImg(); //"file:src/main/resources/hi/vidmot/audioplayer/media/"
        if (file != null){
            fxImage.setText(file.getPath());
            songNew.img = file;
            songNew.songFilename = file.getPath();

            Image image = new Image("file:"+ String.valueOf(file.getAbsoluteFile()), 100, 100, true, true);
            ImageView imageView = new ImageView(image);
            fxImageVieiw.setGraphic(imageView);
        }
    }

    public void onAudio(ActionEvent actionEvent) {
        File file =  File_management.getFileAudio(); //"file:src/main/resources/hi/vidmot/audioplayer/media/"
//        String aukaPath = "media/music/Lagalisti_"+lagalisti.getCul_index()+"_"+lagalisti.getRow_index()+"/";
//        System.out.println(filepath+aukaPath+"Music"+index+".mp3");
        if(file != null){
            fxAudio.setText(file.getPath());
            songNew.song = file;
            songNew.songFilename = file.getPath();
        }


//        File_management.copyFile(filepath+aukaPath,"Music"+index+".mp3",file);
//        songNew.lag = new Lag("Lagalisti","Music",lagalisti.getCul_index(), lagalisti.getRow_index(), index);
    }
}
