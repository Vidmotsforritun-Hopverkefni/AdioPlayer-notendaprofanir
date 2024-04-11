
/******************************************************************************
 *  Nafn    : Daníel Ágúst Björnsson
 *            Guðni Emil
 *  T-póstur: dab47@hi.is
 *
 *
 *  Lýsing  :
 *
 *
 *****************************************************************************/
package hi.vidmot.audioplayer.controller;

import hi.vidmot.audioplayer.interface_class.ListiControllerInterface;
import hi.vidmot.audioplayer.view.View;
import hi.vidmot.audioplayer.view.ViewSwitcher;
import hi.vinnsla.audioplayer.*;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;

//import static java.sql.DriverManager.println;

public class ListiController implements ListiControllerInterface {
    public ListView<Lag> fxListView;
    public Label img_lag;
    public Label img_lagalisti;

    public Lag curr_lag;

    private final Image_management image_management = new Image_management(2, 2);
    public Button okay_buttun;
    public GridPane grid_buttons;
    public Button pause_button;
    public ProgressBar fxProgressBar;
    private static final String filename = "src/main/resources/hi/vidmot/audioplayer/media/Txt_(names)/Lagalisti/";

    @FXML
    private Menu_lagController menuStyringController;
    Lagalisti lagalisti;
    private ObservableList<String> uuids;

    /**
     * Frumstillir lagalistann og tengir hann við ListView viðmótshlut
     */
    @Override
    public void initialize() {
        System.err.println("initialize ListController");

        menuStyringController.setListiController(this); // connects menucontroller and listontroller

        image_management.add_images_button(grid_buttons,"Main", "button", 25,25);

        setLog();
        Lag lag = fxListView.getSelectionModel().getSelectedItem();
//        System.out.println("lagalisti.getLagalistaIndex(): " +lagalisti.getLagalistaIndex());
        image_management.add_images_label(img_lagalisti, "Heima", "Heima",Lagalistar.index,true,100,100);
//        System.out.println(lagalisti.getLagalistaIndex() + " this is the index");
        this.curr_lag = lag;
//        System.out.println("curr index: "+Lagalistar.index);



//        fxProgressBar.getScene().getWindow().setHeight(450);

    }

    private void bindProgress(MediaPlayer player, ProgressBar bar) {
        var binding =
                Bindings.createDoubleBinding(
                        () -> {
                            var currentTime = player.getCurrentTime();
                            var duration = player.getMedia().getDuration();
                            if (isValidDuration(currentTime) && isValidDuration(duration)) {
                                return currentTime.toMillis() / duration.toMillis();
                            }
                            return ProgressBar.INDETERMINATE_PROGRESS;
                        },
                        player.currentTimeProperty(),
                        player.getMedia().durationProperty());
        bar.progressProperty().bind(binding);
    }

    private boolean isValidDuration(Duration d) {
        return d != null && !d.isIndefinite() && !d.isUnknown();
    }


    private void setLog(){
//        ObservableList<String> lines = FXCollections.observableArrayList();

//        String filenm = "file:src/main/resources/hi/vidmot/audioplayer/media/img/Lagalisti_1_1/fb5ebc26-6607-4735-b63b-de85d605eafd.png";
//        Image image =  new Image(filenm);
//        ImageView imageView = new ImageView(image);
//        img_lag.setGraphic(imageView);
;        ObservableList<Lagalisti> lagalistis = Lagalistar.getLagalista_list();
        Lagalisti lagalisti = lagalistis.get(Lagalistar.index);
        ObservableList<Lag> listi = lagalisti.getListi();
        System.out.println("lagaListi: " + listi);
        this.lagalisti = lagalisti;
        ObservableList<String> names = JsonFile_management.toNameList(filename+"Lagalisti_","log", lagalisti.getLagalistaIndex());
        this.uuids = JsonFile_management.toUuidList(filename+"Lagalisti_","log", lagalisti.getLagalistaIndex());
        AtomicInteger counter = new AtomicInteger();
        listi.forEach((lag) -> {
//            System.out.println(lines.get(0))
            if (names != null){
                try{
                    lag.setName(names.get(counter.get()));
                }catch (IndexOutOfBoundsException e){

                }

            }

            fxListView.getItems().add(lag);
            if(lag.getLag() != null){
                lag.getLag().currentTimeProperty().addListener((observable, old, newValue) ->
                        fxProgressBar.setProgress(newValue.divide(lag.getLengd()).toMillis()));
            }



            fxProgressBar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getButton() == MouseButton.PRIMARY){
                        Bounds b1 = fxProgressBar.getLayoutBounds();
                        double mouseX = event.getSceneX();
                        double percent = (((b1.getMinX() + mouseX ) * 100) / b1.getMaxX());
                        //correcting a percent, i don't know when it need
//                        percent -= 2;
                        fxProgressBar.setProgress((percent) / 100);
                        //do something with progress in percent
//                        lag.getLengd()
//                        lag.getLag().setStartTime(new Duration(lag.getLengd().toMillis()*percent));

//                        double currentPosition = lag.getLag().getCurrentTime().toMillis();
//                        System.out.println(lag.getLag().getStopTime());
//                        lag.getLag().pause();
//                        curr_lag.getLag().pause();
                        if(curr_lag != null){
                            if(curr_lag.getLag() != null){
                                curr_lag.getLag().seek(curr_lag.getLag().getMedia().getDuration().multiply(percent / 100.0) );
                            }

                        }

//                        curr_lag.getLag().play();
//                        curr_lag.getLag().pause();
//                        System.out.println(percent/100 + "%");
//                        System.out.println(lag.getLag().getStopTime().toMillis() + " end");
//                        System.out.println(lag.getLag().getStartTime().toMillis() + " start");
//                        System.out.println(curr_lag.getLag().getCurrentTime().toMillis() + " curr");
//                        System.out.println(lag.getLag().getStopTime().toMillis()*percent/100 + " okay");
                    }
                }
            });
            counter.getAndIncrement();
        });


    }

    /**
     * Bregðast við músaratburði og spila valið lag
     *
     * @param mouseEvent
     */
    @Override
    public void onValidLag(MouseEvent mouseEvent) {
//        System.out.println("clicked on " + );
        setjaPlayer();

    }

    /**
     * Lætur laga lista vita hvert valda lagið er. Uppfærir myndina fyrir lagið.
     */
    @Override
    public void veljaLag() {
//        System.out.println("hello, veljaLag()");
//        System.out.println(curr_lag);
        if(curr_lag != null){
            if (curr_lag.getLag() != null){
                curr_lag.setImg("Lagalisti",uuids.get(curr_lag.getIndex()),  lagalisti.getLagalistaIndex());
                double percent = curr_lag.getLag().getMedia().getDuration().toMillis()/curr_lag.getLag().getStopTime().toMillis();
//            System.out.println(percent);
                image_management.add_images_label(img_lag, curr_lag.getImg(),0,0); // if one is 0 then width and height are not used
            }

        }

    }

    /**
     * Lagið er pásað ef það er í spilun, lagið er spilað ef það er í pásu
     *
     * @param actionEvent ónotað
     */
    @Override
    public void onPlayPause(ActionEvent actionEvent) {
        if(curr_lag != null){
            if(curr_lag.getLag() != null){
                curr_lag.getLag().pause();
            }


        }



    }

    /**
     * Fara aftur í heima view. Ef spilari er til stöðva spilarann
     *
     * @param actionEvent ónotað
     */
    @Override
    public void onHeim(ActionEvent actionEvent) {
        if(curr_lag != null){
            curr_lag.getLag().seek(new Duration(0));
            curr_lag.getLag().pause();
        }

//        fxListView.getItems().clear();
//        ViewSwitcher.setSceneHeight(350); // sets the height of the window
        ViewSwitcher.switchTo_WithSize(View.HEIMA, true, 300, 500);



    }

    /**
     * Spila lagið
     */
    @Override
    public void spilaLag() {
//        curr_lag.getLag().seek(new Duration(0));
//        onPlayPause();
//        System.out.println(curr_lag.getLag().getCurrentTime());
        if(curr_lag != null ){
            if(curr_lag.getLag() == null){
                return;
            }
            curr_lag.getLag().pause();

//            System.out.println(curr_lag.getLag().getStopTime().toMillis());
//            System.out.println(curr_lag.getLag().getCurrentTime().toMillis());
            if (curr_lag.getLag().getStopTime().toMillis() == curr_lag.getLag().getCurrentTime().toMillis()){
                curr_lag.getLag().seek(new Duration(0));
//                System.out.println("hello");
            }
            curr_lag.getLag().play();
        }

    }

    /**
     * Setja mynd með nafni á ImageView
     *
     * @param fxImageView viðmótshluturinn sem á að uppfærast
     * @param nafnMynd    nafn á myndinni
     */
    @Override
    public void setjaMynd(ImageView fxImageView, String nafnMynd) {

    }

    /**
     * Setja upp player fyrir lagið, þ.m.t. at setja handler á hvenær lagið stoppar og tengja
     * lagið við progress bar
     */
    @Override
    public void setjaPlayer() {
        Lag lag = fxListView.getSelectionModel().getSelectedItem();

        if(this.curr_lag != null){
            if(curr_lag.getLag() != null){
                curr_lag.getLag().pause();
            }

//            bindProgress(curr_lag.getLag(), progers);
//            System.out.println("hello");
            naestaLag();


        }

        this.curr_lag = lag;
        veljaLag();




    }

    /**
     * Næsta lag er spilað. Kallað á þessa aðferð þegar fyrra lag á listanum endar
     */
    @Override
    public void naestaLag() {
        fxProgressBar.setProgress(curr_lag.getLag().getTotalDuration().toMillis());
    }
    @FXML
    protected void removeListItem(ActionEvent event) {
        // Fá valinn hlut í ListView
//        String uuid = JsonFile_management.removeJson(filename,"lagalisti", -1, 0);
        Lag selectedLag = fxListView.getSelectionModel().getSelectedItem();
        if (selectedLag != null) {
            String uuid = JsonFile_management.removeJson(filename+"Lagalisti_","log", lagalisti.getLagalistaIndex(), selectedLag.getIndex());
//            String uuid = JsonFile_management.addJson(filename+"Lagalisti_","log", lagalisti.getLagalistaIndex(), songNew.getName());
            // Fjarlægja valinn hlut úr lista
            lagalisti.fjarlaegjaLag(selectedLag);
            // Endurhlaða ListView til að birta nýja lista
            fxListView.getItems().remove(selectedLag);
        }
    }
}
