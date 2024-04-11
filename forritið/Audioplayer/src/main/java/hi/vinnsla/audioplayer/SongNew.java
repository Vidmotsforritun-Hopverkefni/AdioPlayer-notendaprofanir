package hi.vinnsla.audioplayer;

import java.io.File;

public class SongNew {
    public String songFilename = "";
    public File song;
    public Lag lag;
    public String imgFilename = "";
    public File img;
    public String name = "";

    public boolean NameWrong = false;
    public boolean imgWrong = false;
    public boolean songWrong = false;

    public SongNew(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setImgFilename(String imgFilename) {
        this.imgFilename = imgFilename;
    }

    public boolean isNameWrong() {
        return NameWrong;
    }

    public boolean isImgWrong() {
        return !(new File(imgFilename)).exists();
    }

    public boolean isSongWrong() {
        return !(new File(songFilename)).exists();
    }
}
