
package hi.vinnsla.audioplayer;

import java.io.File;
import java.net.URL;
//import  javafx.scene.media;
import hi.vidmot.audioplayer.AudioplayerApplication;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/******************************************************************************
 *  Nafn    : Daníel Ágúst Björnsson
 *  T-póstur: dab47@hi.is
 *
 *  Lýsing  : class for keeping each song
 *
 *
 *****************************************************************************/

public class Lag {
    private MediaPlayer lag;
    private String img;
    private int index;
    public Media validLag;
    public String name;
    public String media;
    public String uuid;

    private static final String filename = "src/main/resources/hi/vidmot/audioplayer/";

    public Lag(){

    }
//    public Lag(String folder, String base_name, int col, int row, int index){
//        this.lesaLog(folder, base_name, col, row, index);
//    }


    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public MediaPlayer getLag() {
        return lag;
    }

    public String getNafn() {
        return name;
    }
    public String getMedia() {
        return media;
    }


    public String getMynd() {
        return img;
    }
    /**
     * reads the music from the folders
     * @param folder the folder the music is in
     * @param base_name the name of the file without "_x.mp3"
     * @param lagalistaIndex the colum of the lagalisti
     * @param index the index of the file so there can be more than one song
     */
    public void lesaLog(String folder, String base_name, int lagalistaIndex, int index) {
        Media media = getFile(folder,  base_name,  lagalistaIndex, index);
        validLag = media;
        setIndex(index);
        if (media == null){
            lag =   new MediaPlayer(getFile("Lagalisti","Music",lagalistaIndex));
            return;
        }
        lag = new MediaPlayer(media);
    }
    /**
     * reads the music from the folders
     * @param folder the folder the music is in
     * @param base_name the name of the file without "_x.mp3"
     * @param lagalistaIndex the colum of the lagalisti
     */
    public void lesaLogv2(String folder, String base_name, int lagalistaIndex, int index) {
//        System.out.println(base_name + " this is basename");
        Media media = getFile(folder,  base_name,  lagalistaIndex);
        validLag = media;
        setIndex(index);
//        setIndex(index);
//        if (media == null){
//            lag =   new MediaPlayer(getFile("Lagalisti","Music",lagalistaIndex, 0));
//            return;
//        }
        if (media != null){
            lag = new MediaPlayer(media);
        }

    }

    public void setIndex(int index) {
//        System.out.println(index + " this is index");
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public Media getValidLag() {
        return validLag;
    }

    /**
     * the main music file reader
     * @param path all of the path
     * @return media
     */
    private Media getFile(String path){
//        Media media = new Media("file:///Movies/test.mp3");
//        URL medURL = AudioplayerApplication.class.getResource(path);
        try{
            Media media = new Media(new File(path).toURI().toString());
            return media;
        }catch (MediaException ignore){
            System.err.println("Fann ekki skrána " + path);
            return null;
        }


//        System.out.println(imgURL);
//        if (medURL != null) {
//            return new Media(medURL.toString());
//        } else {
//            System.err.println("Fann ekki skrána " + path + " helllo");
//            return null;
//        }
//        if (!media.e) {
//            return media;// this is we can add and remove img
//        } else {
//            System.err.println("Fann ekki skrána " + Image_management.filepath+path);
//
//            return null;
//        }
//        return media;
    }
    /**
     * reads the music from the folders
     * @param folder the folder the music is in
     * @param base_name the name of the file without "_x.mp3"
     * @param lagalistaIndex the colum of the lagalisti
     * @param index the index of the file so there can be more than one song
     */
    private Media getFile(String folder, String base_name, int lagalistaIndex, int index){
        String path = "media/music/";
        name = base_name +"_"+index + ".mp3";
        return getFile( filename+path + folder  + "_" +  lagalistaIndex + "/" + base_name +"_"+index + ".mp3");
    }
    private Media getFile(String folder, String base_name, int lagalistaIndex){
        String path = "media/music/";
        name = base_name +"_"+index + ".mp3";
//        System.out.println("hello in lag " +filename+path + folder  + "_" +  lagalistaIndex + "/" + base_name  + ".mp3");

        return getFile( filename+path + folder  + "_" +  lagalistaIndex + "/" + base_name  + ".mp3");
    }

    @Override
    public String toString() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String folder, String base_name, int lagalistaIndex, int index) {
        String path = "media/img/";
        this.img = path + folder  + "_" +  lagalistaIndex + "/" + base_name +"_"+index + ".png";
    }
    public void setImg(String folder, String base_name, int lagalistaIndex) {
        String path = "media/img/";
        this.img = path + folder  + "_" +  lagalistaIndex + "/" + base_name  + ".png";
    }


    public void setImg(String img) {
        this.img = img;
    }
    public Duration getLengd(){
        return lag.getTotalDuration();
    }
}