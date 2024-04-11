package hi.vinnsla.audioplayer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class TxtFile_management {

    public static ObservableList<String> getnames(String path,String baseName, int index){
        ObservableList<String> lines = FXCollections.observableArrayList();
        if (index >=0){
            try (BufferedReader reader = new BufferedReader(new FileReader(path + index+"/"+baseName+".txt"))) {

                String line;
                while ((line = reader.readLine()) != null){
//                System.out.println(line);
                    lines.add(line);

                }
            } catch (IOException e) {
                System.err.println("did not find "+path + index+"/"+baseName+".txt");
                return null;
            }
        }else {
            try (BufferedReader reader = new BufferedReader(new FileReader(path + "/"+baseName+".txt"))) {

                String line;
                while ((line = reader.readLine()) != null){
//                System.out.println(line);
                    lines.add(line);

                }
            } catch (IOException e) {
                System.err.println("did not find "+path +"/"+baseName+".txt");
                return null;
            }
        }

        return lines;
    }
    public static ObservableList<String> getData(String path,String base_name){
        ObservableList<String> lines = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(path + base_name+".txt"))) {

            String line;
            while ((line = reader.readLine()) != null){
//                System.out.println(line);
                lines.add(line);

            }
        } catch (IOException e) {
            System.err.println("did not find "+path + base_name+".txt");
            return null;
        }
        return lines;
    }
    public static boolean setnames(String path, int index, String line){
//        ObservableList<String> lines = FXCollections.observableArrayList();
//        PrintWriter fw = null;
////        fw = new PrintWriter("users.txt");

        try ( BufferedWriter writer = new BufferedWriter(new PrintWriter(path + "Lagalisti_"+ index+"/Log.txt"))) {

//            String line;
//            while ((line = reader.readLine()) != null){
//                System.out.println(line);
//                lines.add(line);
//
//            }
            writer.newLine();
            writer.write(line);
        } catch (IOException e) {
            System.err.println("did not find "+path + "Lagalisti_"+ index+"/Log.txt");

            return false;
        }
        return true;
    }

    public static boolean setData(String path, String base_name, String line){
//        ObservableList<String> lines = FXCollections.observableArrayList();
//        PrintWriter fw = null;
////        fw = new PrintWriter("users.txt");

        try ( BufferedWriter writer = new BufferedWriter(new PrintWriter(path + base_name+".txt"))) {

//            String line;
//            while ((line = reader.readLine()) != null){
//                System.out.println(line);
//                lines.add(line);
//
//            }
            writer.newLine();
            writer.write(line);
        } catch (IOException e) {
            System.err.println("did not find "+path + base_name+".txt");

            return false;
        }
        return true;
    }
    public static boolean addname(String path, int index, String line, ObservableList<String> lines){
//        ObservableList<String> lines = FXCollections.observableArrayList();
//        PrintWriter fw = null;
////        fw = new PrintWriter("users.txt");

        try ( BufferedWriter writer = new BufferedWriter(new PrintWriter(path + "Lagalisti_"+ index+"/Log.txt"))) {

//            String line;
//            while ((line = reader.readLine()) != null){
//                System.out.println(line);
//                lines.add(line);
//
//            }

            lines.forEach((line_in) -> {
                try {
                    writer.write(line_in);
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            writer.write(line);
        } catch (IOException e) {
            System.err.println("did not find "+path + "Lagalisti_"+ index+"/Log.txt");

            return false;
        }
        return true;
    }

    public static boolean addnames(String path, int index, ObservableList<String> new_lines, ObservableList<String> org_lines){
//        ObservableList<String> lines = FXCollections.observableArrayList();
//        PrintWriter fw = null;
////        fw = new PrintWriter("users.txt");

        try ( BufferedWriter writer = new BufferedWriter(new PrintWriter(path + "Lagalisti_"+ index+"/Log.txt"))) {

//            String line;
//            while ((line = reader.readLine()) != null){
//                System.out.println(line);
//                lines.add(line);
//
//            }

            org_lines.forEach((line_in) -> {
                try {
                    writer.write(line_in);
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            for (int i = 0; i < new_lines.size(); i++){
                try {
                    writer.write(new_lines.get(i));
                    if(i != new_lines.size()){
                        writer.newLine();
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            System.err.println("did not find "+path + "Lagalisti_"+ index+"/Log.txt");

            return false;
        }
        return true;
    }
    /**
     * used to add a new line to
     * @param path
     * @param base_name
     * @param new_lines
     * @param org_lines
     * @return
     */
    public static boolean addDatas(String path, String base_name, ObservableList<String> new_lines, ObservableList<String> org_lines){


        try ( BufferedWriter writer = new BufferedWriter(new PrintWriter(path + base_name+".txt"))) {


            org_lines.forEach((line_in) -> {
                try {
                    writer.write(line_in);
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
//            new_lines.forEach((line_in) -> {
//                try {
//                    writer.write(line_in);
//                    if (new_lines.indexOf(line_in) != new_lines.size()){
//                        writer.newLine();
//                    }
//
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            });
            for (int i = 0; i < new_lines.size(); i++){
                try {
                    writer.write(new_lines.get(i));
                    if(i != new_lines.size()){
                        writer.newLine();
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            System.err.println("did not find "+path + base_name+".txt");

            return false;
        }
        return true;
    }
    public static boolean addData(String path, String base_name, String line, ObservableList<String> lines){


        try ( BufferedWriter writer = new BufferedWriter(new PrintWriter(path + base_name+".txt"))) {


            lines.forEach((line_in) -> {
                try {
                    writer.write(line_in);
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("did not find "+path + base_name+".txt");

            return false;
        }
        return true;
    }
}
