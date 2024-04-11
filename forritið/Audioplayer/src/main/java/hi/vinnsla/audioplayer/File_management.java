package hi.vinnsla.audioplayer;

import com.mpatric.mp3agic.*;
import com.mpatric.mp3agic.Mp3File;
import hi.vidmot.audioplayer.view.ViewSwitcher;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class File_management {

    public static String filepath = "src/main/resources/hi/vidmot/audioplayer/"; // the path of the
    private static final String RETAG_EXTENSION = ".retag";
    private static final String BACKUP_EXTENSION = ".bak";
    protected static String filename = null;

    /**
     * opens file explorer for the user to find the file
     * @return
     */
    public static File getFileAudio(){
        FileChooser fileChooser = createFileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile =  fileChooser.showOpenDialog(ViewSwitcher.getStage());
        if (selectedFile != null) {
//            stage.display(selectedFile);
            System.out.println(selectedFile);
//            Image image = Image.fromPlatformImage(selectedFile);
            return selectedFile;
        }else{
            System.err.println("Fann ekki skrána");
            return null;
        }


    }
    public static FileChooser createFileChooser() {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        return chooser ;
    }
    public static File getFileImg(){
        FileChooser fileChooser = createFileChooser();
        fileChooser.setTitle("Open Resource File");
//        fileChooser.setInitialDirectory();
        System.out.println(fileChooser.getInitialDirectory() + " InitialDirectory");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile =  fileChooser.showOpenDialog(ViewSwitcher.getStage());
        if (selectedFile != null) {
//            stage.display(selectedFile);
            System.out.println(selectedFile);
//            Image image = Image.fromPlatformImage(selectedFile);
            return selectedFile;
        }else{
            System.err.println("Fann ekki skrána");
            return null;
        }


    }

    /**
     * copyes file from one spot to another using File class at one end
     * @param path
     * @param newName
     * @param file
     * @throws IOException
     */
    public static void copyFile(String path, String newName,File file){
//        Image image = new Image("file:"+ String.valueOf(selectedFile.getAbsoluteFile()));
//        fxImaageView.setImage(image);
        // path verður að enda á /
        try {
            Path targetDirPath = Paths.get(filepath+path+newName);
            Files.copy(file.toPath(), targetDirPath);
        }catch (IOException ingnore){
            System.err.println(filepath+path+newName + " not found");
        }

    }

    /**
     * copyes file from one spot to another using paths at both end
     * @param path
     * @param newName
     * @param oldPath
     * @throws IOException
     */
    public static boolean copyFile(String path, String newName,String oldPath) {
//        Image image = new Image("file:"+ String.valueOf(selectedFile.getAbsoluteFile()));
//        fxImaageView.setImage(image);
        // path verður að enda á /
//        Mp3File mp3file = null;
        try {
            Path targetDirPath = Paths.get(filepath + path + newName);
            Path oldDirPath = Paths.get(oldPath);
            File file = new File(oldPath);
//            file.set
            Files.copy(oldDirPath, targetDirPath);
//            Mp3File mp3file = new Mp3File(oldDirPath);
//            System.out.println("mp3 " + mp3file);
//            setMetadata(mp3file);
//            saveMetadata(mp3file);
            filename = targetDirPath.toString();
//            retag(mp3file, path);
//            setMetadata(mp3file, path, )


            return true;
        } catch (IOException ignore) {
            System.err.println("IOException copyFile");
            return false;
        }

    }
    public static boolean copyFile(String path, String newName,String oldPath, String name) {
//        Image image = new Image("file:"+ String.valueOf(selectedFile.getAbsoluteFile()));
//        fxImaageView.setImage(image);
        // path verður að enda á /
//        Mp3File mp3file = null;
        try {
            Path targetDirPath = Paths.get(filepath + path + newName);
            Path oldDirPath = Paths.get(oldPath);
            File file = new File(oldPath);
//            file.set
//            Files.copy(oldDirPath, targetDirPath);
            System.out.println(oldDirPath + " file path");
            System.out.println(file.exists() + " hello");
            Mp3File mp3file = new Mp3File(file);
            System.out.println("mp3 " + mp3file);
//            setMetadata(mp3file);

            filename = targetDirPath.toString();
            saveMetadata(mp3file,name);
//            retag(mp3file, path);
//            setMetadata(mp3file, path, name);


            return true;
        } catch (IOException ignore) {
            System.err.println("IOException copyFile");
            return false;
        } catch (InvalidDataException e) {
            System.out.println(filepath + path + newName + " not found");
//            System.err.println("did not save " + mp3file.getFilename() + " InvalidDataException");
            throw new RuntimeException(e);
        } catch (UnsupportedTagException e) {
            System.out.println(filepath + path + newName + " not found");
//            System.err.println("did not save " + mp3file.getFilename() + " UnsupportedTagException");
            throw new RuntimeException(e);
        } catch (NotSupportedException e) {
//            System.err.println("did not save " + mp3file.getFilename() + " NotSupportedException");
            throw new RuntimeException(e);
        }

    }
    private static void retag(Mp3File mp3File, String path) throws IOException, NotSupportedException {
//        setMetadata(mp3File);
        saveMetadata(mp3File, path);
//        renameFiles();
//		mp3file.se
    }
    protected static void renameFiles() {
        File originalFile = new File(filename);
        File backupFile = new File(filename + BACKUP_EXTENSION);
        File retaggedFile = new File(filename + RETAG_EXTENSION);
        if (backupFile.exists()) {
            backupFile.delete();
        }
        originalFile.renameTo(backupFile);
        retaggedFile.renameTo(originalFile);
    }

    private void deleteFile(String filename) {
        File file = new File(filename);
        file.delete();
    }

    public static Mp3File setMetadata(Mp3File mp3file){
        ID3Wrapper newId3Wrapper = new ID3Wrapper(new ID3v1Tag(), new ID3v23Tag());
        newId3Wrapper.setTitle(trimField("oldId3Wrapper.getTitle()"));
        mp3file.setId3v1Tag(newId3Wrapper.getId3v1Tag());
//        mp3file.setId3v2Tag(newId3Wrapper.getId3v2Tag());
        return mp3file;

    }
    public static void saveMetadata(Mp3File mp3file, String title) throws IOException, NotSupportedException {
        System.err.println(mp3file.getFilename() + " filename");
        ID3v1 id3v1Tag;
        if (mp3file.hasId3v1Tag()) {
            id3v1Tag =  mp3file.getId3v1Tag();
        } else {
            id3v1Tag = new ID3v1Tag();
            mp3file.setId3v1Tag(id3v1Tag);
        }
        id3v1Tag.setTitle(title);

//        mp3file.save("MyMp3File.mp3");
        System.out.println(filename);
        mp3file.save(filename);
    }
    private static String trimField(String value) {
        if (value == null) return null;
        return value.trim();
    }

    /**
     * adds file after path
     * @param path path from
     *             src/main/resources/hi/vidmot/audioplayer/
     * @param newName the name of the file that it becomes
     * @param oldPath if null use open file in file explorer
     * @throws IOException
     */
    public static String addFileAudio(String path, String newName, String oldPath) {
        if (oldPath == null){
            File file = File_management.getFileAudio();
            if (file != null){
                File_management.copyFile(path, newName, file);
                return path+newName;
            }
        }else {

            File_management.copyFile(path, newName,oldPath);
            return path+newName;
        }
        return null;

    }

    /**
     * adds file after path
     * @param path path from
     *             src/main/resources/hi/vidmot/audioplayer/
     * @param newName the name of the file that it becomes
     * @param oldPath if null use open file in file explorer
     * @throws IOException
     */
    public static String addFileImg(String path, String newName, String oldPath) {
        if (oldPath == null){
            File file = File_management.getFileImg();
            if (file != null){
                File_management.copyFile(path, newName, file);
                return path+newName;
            }
        }else {

            File_management.copyFile(path, newName,oldPath);
            return path+newName;
        }
        return null;

    }
}
