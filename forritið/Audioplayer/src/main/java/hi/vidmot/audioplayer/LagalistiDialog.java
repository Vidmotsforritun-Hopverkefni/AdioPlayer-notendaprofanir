package hi.vidmot.audioplayer;

import hi.vinnsla.audioplayer.Lagalisti;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LagalistiDialog extends Dialog<Lagalisti> {
    public LagalistiDialog(Lagalisti lagalisti) {
        // Búum til textabox fyrir notandanafn
        TextField nafnField = new TextField();
        nafnField.setPromptText("Nafn á lagalista");

        // Búum til GridPane og bætum við textaboxinu
        GridPane grid = new GridPane();
        grid.add(nafnField, 0, 0);

        // Setjum viðmótið í dialoginn
        getDialogPane().setContent(grid);

        // Setjum hnappa til staðfestingar og hættu
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Atburðarhandler fyrir hnappana
        setResultConverter(button -> {
            if (button == ButtonType.OK) {
                // Ef notandi ýtir á OK hnappinn, skila nýjum lagalista með nafni sem notandinn sló inn
                return new Lagalisti(1);
            }
            return null; // Ef notandi ýtir á hættu eða loka hnappinn, skila null
        });
    }
}
