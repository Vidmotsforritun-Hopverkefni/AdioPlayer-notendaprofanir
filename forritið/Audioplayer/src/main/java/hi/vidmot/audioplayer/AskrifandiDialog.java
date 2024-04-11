package hi.vidmot.audioplayer;

import hi.vinnsla.audioplayer.Askrifandi;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AskrifandiDialog extends Dialog<Askrifandi> {
    private Askrifandi askrifandi;
    @FXML
    private TextField fxNafn;
    public AskrifandiDialog(Askrifandi askrifandi) {
        this.askrifandi = askrifandi;
        setDialogPane(lesaAskrifandiDialog());
        // sett regla um hvenær í lagi hnappur er virkur
//        iLagiRegla();
        // Búum til hlut af nýjum nafnlausum innri klasa sem útfærir interface
        // Callback fyrir klasana ButtonType og Leikmenn
        // Callback hefur eina aðferð og við endurforritum hana

        setResultConverter(b -> {                                 // b er af taginu ButtonType
            if (b.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                askrifandi.setNafn(fxNafn.getText());
                return askrifandi;
            } else {
                return null;
            }
        });         // endir á d.setResultConverter

    }

    private DialogPane lesaAskrifandiDialog() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/askrifandi-view.fxml"));
        try {
            fxmlLoader.setController(this);
            return fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


}
