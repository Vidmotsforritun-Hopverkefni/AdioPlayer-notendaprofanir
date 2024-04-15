package hi.vinnsla.audioplayer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class JsonFile_management {
    private static final String filename = "src/main/resources/hi/vidmot/audioplayer/media/Txt_(names)/hello.json";


    public static void writeJson(ObservableList<String> names, String path) {
//        welcomeText.setText("Gögn vistuð");
        ObjectMapper objectMapper = new ObjectMapper();
//        Car[] cars = new Car[3];
//        cars[0] = new Car("yellow", "renault");
//        cars[1] = new Car("red", "volvo");
//        cars[2] = new Car("red", "volvo");
        ObservableList<LagData> laga_names = FXCollections.observableArrayList();

        names.forEach((name) ->{
            laga_names.add(new LagData(name, UUID.randomUUID().toString()));
        });
        try {
            File file = new File(path);
            if (file.createNewFile())
                objectMapper.writeValue(file, laga_names);
            else {
                objectMapper.writeValue(file, laga_names);
                System.out.println("skráin er til");
            }
        } catch (IOException ex) {
            System.err.println("did not find " + path);
//            throw new RuntimeException(ex);
        }

    }
    public static void vistaJson(ObservableList<LagData> laga_names, String path) {
//        welcomeText.setText("Gögn vistuð");
        ObjectMapper objectMapper = new ObjectMapper();
//        Car[] cars = new Car[3];
//        cars[0] = new Car("yellow", "renault");
//        cars[1] = new Car("red", "volvo");
//        cars[2] = new Car("red", "volvo");
//        ObservableList<LagData> laga_names = FXCollections.observableArrayList();

//        names.forEach((name) ->{
//            laga_names.add(new LagData(name, UUID.randomUUID().toString()));
//        });
        try {
            File file = new File(path);
            if (file.createNewFile())
                objectMapper.writeValue(file, laga_names);
            else {
                objectMapper.writeValue(file, laga_names);
                System.out.println("skráin er til " + path);
            }
        } catch (IOException ex) {
            System.err.println("did not find " + path);
//            throw new RuntimeException(ex);
        }

    }

    public static ObservableList<LagData> lesaJson(String path,String baseName, int index) {
        ObjectMapper objectMapper = new ObjectMapper();
        if(index >=0){
            try {
                // Gögnin sem mætti breyta t.d.
                List<LagData> listLagData = objectMapper.readValue(
                        new File(path + index+"/"+baseName+".json"),
                        new TypeReference<>() {
                        });
//            System.out.println(listLagData);
                ObservableList<LagData> temp = FXCollections.observableArrayList();
                temp.addAll(listLagData);
                return temp;
            } catch (IOException e) {

                System.out.println ("skrá er ekki til "+path + index+"/"+baseName+".json");
                throw new RuntimeException(e);

            }
        }else {
            try {
                // Gögnin sem mætti breyta t.d.
                List<LagData> listLagData = objectMapper.readValue(
                        new File(path +"/"+baseName+".json"),
                        new TypeReference<>() {
                        });
//            System.out.println(listLagData);
                ObservableList<LagData> temp = FXCollections.observableArrayList();
                temp.addAll(listLagData);
                return temp;
            } catch (IOException e) {

                System.out.println ("skrá er ekki til "+path +"/"+baseName+".json");
                throw new RuntimeException(e);

            }
        }


    }


    public static String addJson(String path, String baseName, int index, String name){
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(path + index+"/"+baseName+".json");
        try {
            // Gögnin sem mætti breyta t.d.
            List<LagData> listLagData = objectMapper.readValue(
                    new File(path + index+"/"+baseName+".json"),
                    new TypeReference<>() {
                    });
//            System.out.println(listLagData);
            ObservableList<LagData> temp = FXCollections.observableArrayList();
            temp.addAll(listLagData);
            LagData lagData = new LagData(name, UUID.randomUUID().toString());
            temp.add(lagData);
            vistaJson(temp, path + index+"/"+baseName+".json");
            return lagData.getUuid();



        } catch (IOException e) {

            System.out.println ("skrá er ekki til "+path + index+"/"+baseName+".json");
            throw new RuntimeException(e);
//            return  null;
//            throw new RuntimeException(e);

        }
    }

    public static LagData getLag(ObservableList<LagData> lagDatalist, String uuid){
        AtomicReference<LagData> res = new AtomicReference<>();
        lagDatalist.forEach(lagData -> {
            if(lagData.getUuid() == uuid){
                res.set(lagData);
            }
        });
        return res.get();
    }
    public static String removeJson(String path, String baseName, int index, String uuid){
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(path +"/"+baseName+".json");
        if(index>=0){
            try {
                // Gögnin sem mætti breyta t.d.
                System.out.println("hellonnjgnfj");
                List<LagData> listLagData = objectMapper.readValue(
                        new File(path + index + "/" + baseName + ".json"),
                        new TypeReference<>() {
                        });
//            System.out.println(listLagData);
                ObservableList<LagData> temp = FXCollections.observableArrayList();
                temp.addAll(listLagData);
//                LagData lagData = new LagData(name, UUID.randomUUID().toString());
                System.out.println("before: " + temp);
//                LagData lagData = temp.get(index2);
                temp.remove(getLag(temp, uuid));
//                temp.re
                System.out.println("after: " + temp);
                vistaJson(temp, path + index + "/" + baseName + ".json");
                return uuid;


            } catch (IOException e) {

                System.out.println("skrá er ekki til " + path + index + "/" + baseName + ".json");
                throw new RuntimeException(e);
//            return  null;
//            throw new RuntimeException(e);

            }
        }else {
            try {
                // Gögnin sem mætti breyta t.d.
//                System.out.println("path " + path);
                List<LagData> listLagData = objectMapper.readValue(
                        new File(path +"/"+baseName+".json"),
                        new TypeReference<>() {
                        });
//            System.out.println(listLagData);
                ObservableList<LagData> temp = FXCollections.observableArrayList();
                temp.addAll(listLagData);
//                LagData lagData = new LagData(name, UUID.randomUUID().toString());
                System.out.println("before: " + temp);
                temp.remove(getLag(temp, uuid));
                System.out.println("after: " + temp);
                vistaJson(temp, path +"/"+baseName+".json");
                return uuid;



            } catch (IOException e) {
                System.out.println("hello");
                System.err.println ("skrá er ekki til "+path + "/"+baseName+".json");
                throw new RuntimeException(e);
//            return  null;
//            throw new RuntimeException(e);

            }
        }
    }
    public static String replaceJson(String path, String baseName, int index, int index2, String name){
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(path +"/"+baseName+".json");
        if(index>=0){
            try {
                // Gögnin sem mætti breyta t.d.
                System.out.println("hellonnjgnfj");
                List<LagData> listLagData = objectMapper.readValue(
                        new File(path + index + "/" + baseName + ".json"),
                        new TypeReference<>() {
                        });
//            System.out.println(listLagData);
                ObservableList<LagData> temp = FXCollections.observableArrayList();
                temp.addAll(listLagData);
//                LagData lagData = new LagData(name, UUID.randomUUID().toString());
                System.out.println("before: " + temp);
                LagData lagData = temp.get(index2);
                lagData.setName(name);
                temp.set(index2, lagData);
//                temp.re
                System.out.println("after: " + temp);
                vistaJson(temp, path + index + "/" + baseName + ".json");
                return lagData.getUuid();


            } catch (IOException e) {

                System.out.println("skrá er ekki til " + path + index + "/" + baseName + ".json");
                throw new RuntimeException(e);
//            return  null;
//            throw new RuntimeException(e);

            }
        }else {
            try {
                // Gögnin sem mætti breyta t.d.
//                System.out.println("path " + path);
                List<LagData> listLagData = objectMapper.readValue(
                        new File(path +"/"+baseName+".json"),
                        new TypeReference<>() {
                        });
//            System.out.println(listLagData);
                ObservableList<LagData> temp = FXCollections.observableArrayList();
                temp.addAll(listLagData);
//                LagData lagData = new LagData(name, UUID.randomUUID().toString());
                System.out.println("before: " + temp);
                LagData lagData = temp.get(index2);
                lagData.setName(name);
                temp.set(index2, lagData);
                System.out.println("after: " + temp);
                vistaJson(temp, path +"/"+baseName+".json");
                return lagData.getUuid();



            } catch (IOException e) {
                System.out.println("hello");
                System.err.println ("skrá er ekki til "+path + "/"+baseName+".json");
                throw new RuntimeException(e);
//            return  null;
//            throw new RuntimeException(e);

            }
        }
    }
    public static ObservableList<String> toNameList(String path,String baseName, int index){
        ObservableList<String> lagNames = FXCollections.observableArrayList();
        ObservableList<LagData> lagData_list = lesaJson( path,baseName,index);
        lagData_list.forEach(((lagData) -> {
            lagNames.add(lagData.getName());
        }));
        return lagNames;
    }
    public static ObservableList<String> toNameList(String path,String baseName){
        ObservableList<String> lagNames = FXCollections.observableArrayList();
        ObservableList<LagData> lagData_list = lesaJson( path,baseName,-1);
        lagData_list.forEach(((lagData) -> {
            lagNames.add(lagData.getName());
        }));
        return lagNames;
    }
    public static ObservableList<String> toUuidList(String path,String baseName, int index){
        ObservableList<String> lagUuids = FXCollections.observableArrayList();
        ObservableList<LagData> lagData_list = lesaJson( path,baseName,index);
        lagData_list.forEach(((lagData) -> {
            lagUuids.add(lagData.getUuid());
        }));
        return lagUuids;
    }
}
