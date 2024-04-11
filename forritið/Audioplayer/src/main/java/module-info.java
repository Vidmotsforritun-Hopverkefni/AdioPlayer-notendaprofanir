module hi.vidmot.audioplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.media;
    requires java.desktop;
    requires com.fasterxml.jackson.databind;
    requires mp3agic;


    opens hi.vidmot.audioplayer to javafx.fxml;
    exports hi.vidmot.audioplayer;
    exports hi.vidmot.audioplayer.view;
    opens hi.vidmot.audioplayer.view to javafx.fxml;
    exports hi.vidmot.audioplayer.interface_class;
    opens hi.vidmot.audioplayer.interface_class to javafx.fxml;
    exports hi.vidmot.audioplayer.controller;
    opens hi.vidmot.audioplayer.controller to javafx.fxml;
    exports hi.vinnsla.audioplayer to com.fasterxml.jackson.databind;
}