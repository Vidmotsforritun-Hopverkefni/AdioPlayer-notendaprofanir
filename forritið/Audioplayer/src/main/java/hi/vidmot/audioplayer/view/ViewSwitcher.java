package hi.vidmot.audioplayer.view;

import hi.vidmot.audioplayer.AudioplayerApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class ViewSwitcher {

    private static final Map<View, Parent> cache = new HashMap<>();

    private static Scene scene;

    private static Stage stage;
    public static ListView fxListView;

    public static void setScene(Scene scene) {
        ViewSwitcher.scene = scene;
    }
    public static Scene getScene() {
        return scene;
    }

    public static void setStage(Stage stage) {
        ViewSwitcher.stage = stage;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setSceneHeight(double height){
        scene.getWindow().setHeight(height);
    }

    public static void setSceneWidth(double width){
        scene.getWindow().setWidth(width);
    }

    public static void setSceneSize(double width, double height){
        ViewSwitcher.setSceneWidth(width);
        ViewSwitcher.setSceneHeight(height);
    }
    public static void switchTo_WithSize(View view, boolean isCache, double width, double height){
        ViewSwitcher.setSceneSize(width, height);
        ViewSwitcher.switchTo(view, isCache);
    }

    public static void switchTo(View view, boolean isCache) {
        if (scene == null) {
            System.out.println("No scene was set");
            return;
        }

        try {
            Parent root;

            if (cache.containsKey(view) && isCache) {
                System.out.println("Loading from cache");

                root = cache.get(view);
            } else {
                System.out.println("Loading from FXML");

                root = FXMLLoader.load(
                        AudioplayerApplication.class.getResource(view.getFileName())
                );

                cache.put(view, root);
            }

            scene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
