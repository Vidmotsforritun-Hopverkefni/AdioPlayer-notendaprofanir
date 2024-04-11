package hi.vinnsla.audioplayer;

import hi.vidmot.audioplayer.controller.PlayerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/******************************************************************************
 *  Nafn    : Daníel Ágúst Björnsson
 *  T-póstur: dab47@hi.is
 *
 *  Lýsing  : class for Lagalistar
 *  holds all of th lagalisti
 *
 *
 *****************************************************************************/
import java.io.IOException;

public class Lagalistar {

    private static ObservableList<Lagalisti> lagalista_list = FXCollections.observableArrayList();

    public static int index = 0;

    public static int row_index;

    public static int cul_index;

    public static final String SKRA_FANNST_EKKI = "skrá fannst ekki ";

    public static final String SUFFIX = ".dat";

    public static final String NAFN = "listi";

    private static final Lagalisti[] listar = new Lagalisti [2];
    private static final String filename = "src/main/resources/hi/vidmot/audioplayer/media/Txt_(names)/Lagalisti/";




    /**
     * amount is for the amount songs in this class
     */
    private static int amount = PlayerController.Grid_col_size+PlayerController.Grid_row_size;

//    public static int[] amount_list = {2,2,2,1};
    public static ObservableList<Integer> amount_list = FXCollections.observableArrayList();

    /**
     * sets the index of the chosen lagalisti
     * @param index
     */
    public static void setIndex(int index){
        Lagalistar.index = index;
    }





//    public ObservableList<Integer> setAmount(){
//        ObservableList<Integer> amount_list_in = FXCollections.observableArrayList();
//
//        return amount_list_in;
//    }
    /**
     * set the row and colum indexs
     * @param cul_index
     * @param row_index
     */
    public static void set_CulAndRow_index(int cul_index, int row_index){
        Lagalistar.cul_index = cul_index;
        Lagalistar.row_index = row_index;
    }

    /**
     * sets the amount
     * @param amount
     */
    public static void setAmount(int amount) {
        Lagalistar.amount = amount;
    }

    public Lagalistar(ObservableList<Integer> list){
        int[] amount_listx = {2,2,2,1};
//        amount_list.addAll(2, 5, 1, 1);
        Lagalistar.amount_list = list;
        Lagalistar.setAmount(Lagalistar.amount_list.size());
//        amount_list = new int[]{2, 5, 1, 1}; // the amount of music in each lagalisti
        lagalista_list = setLaglistar();

    }
    public Lagalistar(ObservableList<Integer> list,ObservableList<String> hello){
        int[] amount_listx = {2,2,2,1};
//        amount_list.addAll(2, 5, 1, 1);
        Lagalistar.amount_list = list;
        Lagalistar.setAmount(Lagalistar.amount_list.size());
//        amount_list = new int[]{2, 5, 1, 1}; // the amount of music in each lagalisti
//        System.out.println(names + " these are the names");
        lagalista_list = setLaglistar(hello);

    }

    /**
     * increments amount_list at the given index by amount
     * @param index
     */
    public static void incrementAmount_list(int index, int amount){
        amount_list.set(index, amount_list.get(index)+amount);
    }
    /**
     * increments amount_list at the given index by 1
     * @param index
     */
    public static void incrementAmount_list(int index){
        amount_list.set(index, amount_list.get(index)+1);
    }

    /**
     * decrements amount_list at the given index by amount
     * @param index
     */
    public static void decrementAmount_list(int index, int amount){
        amount_list.set(index, amount_list.get(index)-amount);
    }
    /**
     * decrements amount_list at the given index by amount
     * @param index
     */
    public static void decrementAmount_list(int index){
        amount_list.set(index, amount_list.get(index)-1);
    }

    /**
     * adds amount at the end of amount_list
     * @param amount
     */
    public static void addAmount(int amount){
        amount_list.add(amount);
    }

    /**
     * removes the item from amount_list at give index
     * @param index
     */
    public static void removeAmount(int index){
        amount_list.remove(index);
    }

    /**
     * gets lagalisti
     * @return
     */
    public static ObservableList<Lagalisti> getLagalista_list() {
        return lagalista_list;
    }

    /**
     * makes one lagalisti
     * not in use
     * @return
     */
    private static Lagalisti makeLagalisti(int index){
//        if(index == 2){
////            Lagalistar.incrementAmount_list(2);
//        }
//        String filename = "file:src/main/resources/hi/vidmot/audioplayer/media/img/Heima/";
        Lagalisti temp = new Lagalisti(amount_list.get(index));
        temp.setImg("media/img/Heima/Heima_"+index+".png");
        temp.setName("Heima_"+index);
        temp.setLagalistaIndex(index);
        temp.setLog();


//        temp.set_CulAndRow_index(cul, row);
//        System.out.println((temp));
        return temp;
    }
    private static Lagalisti makeLagalisti(ObservableList<String> lines, int index){
//        if(index == 2){
////            Lagalistar.incrementAmount_list(2);
//        }
//        String filename = "file:src/main/resources/hi/vidmot/audioplayer/media/img/Heima/";
        Lagalisti temp = new Lagalisti(amount_list.get(index));
        temp.setImg("media/img/Heima/Heima_"+index+".png");
        temp.setName("Heima_"+index);
        temp.setLagalistaIndex(index);
        temp.setLog(lines);


//        temp.set_CulAndRow_index(cul, row);
//        System.out.println((temp));
        return temp;
    }
    private static Lagalisti makeLagalisti2(ObservableList<LagData> lagData_list, int index){
//        if(index == 2){
////            Lagalistar.incrementAmount_list(2);
//        }
//        String filename = "file:src/main/resources/hi/vidmot/audioplayer/media/img/Heima/";
        Lagalisti temp = new Lagalisti(amount_list.get(index));
        temp.setImg("media/img/Heima/Heima_"+index+".png");
        temp.setName("Heima_"+index);
        temp.setLagalistaIndex(index);
        temp.setLog2(lagData_list);


//        temp.set_CulAndRow_index(cul, row);
//        System.out.println((temp));
        return temp;
    }

    /**
     * calls addlag() adn updates the amount
     */
    public static void addLag(){
        int new_amount = lagalista_list.get(index).addlag();
        amount_list.set(index, new_amount);
        amount = new_amount;
    }
    /**
     * calls addlag() adn updates the amount
     */
    public static void addLag(String name){
        int new_amount = lagalista_list.get(index).addlagv2(name);
        amount_list.set(index, new_amount);
        amount = new_amount;
    }

    /**
     * not in use
     * @return
     */
    protected static ObservableList<Lagalisti> setLaglistar(){
        ObservableList<Lagalisti> lagalistiTemp = FXCollections.observableArrayList();
//        System.out.println(amount);
        for (int i = 0; i < amount; i++) {
            lagalistiTemp.add(Lagalistar.makeLagalisti(i));

        }
//        System.out.println(Arrays.toString(lagalistiTemp));

        return lagalistiTemp;
    }
    /**
     * makes all of the lagalisti
     * @return
     */
    protected static ObservableList<Lagalisti> setLaglistar(ObservableList<String> lines){
        ObservableList<Lagalisti> lagalistiTemp = FXCollections.observableArrayList();
//        System.out.println(amount);
        for (int i = 0; i < amount; i++) {
            String baseName = "log";
            int index = i;
            String path = filename+"Lagalisti_";
            ObservableList<String> names = JsonFile_management.toNameList(filename+"Lagalisti_","log", i);
            ObservableList<LagData> lagData_list = JsonFile_management.lesaJson(filename+"Lagalisti_","log", i);
//            ObservableList
//            JsonFile_management.onVista(names, path + index+"/"+baseName + ".json" );
            lagalistiTemp.add(Lagalistar.makeLagalisti2(lagData_list,i));

        }
//        System.out.println(Arrays.toString(lagalistiTemp));

        return lagalistiTemp;
    }


    public static Lagalisti getNuverandi() {
        return listar[index];
    }
    public static void buaTilNyrLagalisti(Lagalisti newList) {
        // Finnur næsta lausa sæti í listanum
        int emptyIndex = -1;
        for (int i = 0; i < listar.length; i++) {
            if (listar[i] == null) {
                emptyIndex = i;
                break;
            }
        }
        // Ef engin lausu sæti eru í listanum, hætti fallið
        if (emptyIndex == -1) {
            System.out.println("Engin lausu sæti í lagalista listanum.");
            return;
        }
        // Setja nýja lagalistan í næsta lausa sæti
        listar[emptyIndex] = newList;
        // Vista lagalistan í skrá
        try {
            newList.vistaLagalisti(NAFN + (emptyIndex + 1) + SUFFIX);
        } catch (Exception e) {
            System.out.println("Gat ekki vistað nýja lagalista í skrá.");
            e.printStackTrace();
        }
    }


}
