package hi.vidmot.audioplayer;

import hi.vidmot.audioplayer.controller.PlayerController;
import hi.vidmot.audioplayer.view.ViewSwitcher;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class AudioplayerApplication extends Application {
    public ListView fxListView;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AudioplayerApplication.class.getResource("fxml/heima-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 400); //300, 500
        ViewSwitcher.setScene(scene);
        ViewSwitcher.setStage(stage);
        PlayerController sc = fxmlLoader.getController();
        sc.setScene(scene);
        stage.setTitle("Audioplayer");
        stage.getIcons().add(new Image(AudioplayerApplication.class.getResourceAsStream("media/img/Heima/music2.png")));

        ListView fxListView  = new ListView<String>();
        // Fill Data inside ListView
        ObservableList<String> list = FXCollections.observableArrayList();
        for (int i = 0; i < 100; i++) {
            list.add("Value " + i);
        }
        fxListView.setItems(list);
//        stage.setOnShowing(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent event) {
//                System.out.println("hello is shown");
//            }
//        });
//        scene = new Scene(fxListView, 400, 400);
//        sc.fxListView.setItems(list);
//        sc.fxlistview2 = fxListView;
        ViewSwitcher.fxListView = fxListView;
        stage.setScene(scene);

        stage.show();
        bind_sroll();
//        System.out.println("outside");
        ScrollBar node = (ScrollBar)ViewSwitcher.fxListView.lookup(".scroll-bar");
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
    private void bind_sroll(){

    }

    public static void main(String[] args) {
        launch();
    }
}