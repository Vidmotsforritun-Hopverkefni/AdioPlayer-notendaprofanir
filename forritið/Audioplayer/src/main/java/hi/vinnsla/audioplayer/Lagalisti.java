package hi.vinnsla.audioplayer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/******************************************************************************
 *  Nafn    : Daníel Ágúst Björnsson
 *  T-póstur: dab47@hi.is
 *
 *  Lýsing  : class for lagalist
 *  holds all og lag
 *
 *
 *****************************************************************************/

public class Lagalisti {

    private ObservableList<Lag> listi = FXCollections.observableArrayList();
    private int lagalistaIndex;

    private int row_index;

    private int cul_index;

    private int amount = 2;
    public static boolean is_new = false;
    private String name;
    private String img;

//    public int getCul_index() {
//        return cul_index;
//    }
//
//    public int getRow_index() {
//        return row_index;
//    }

    public Lagalisti(int amount){
//        this.cul_index = cul_index;
//        this.row_index = row_index;
        this.amount = amount;
//        listi = new ObservableList<Lag>();
    }

    /**
     * set the row and colum indexs
     * @param cul_index
     * @param row_index
     */
    public void set_CulAndRow_index(int cul_index, int row_index){
        this.cul_index = cul_index;
        this.row_index = row_index;

    }

    /**
     * set the index <br>
     * index is used what Lagalisti is selected
     * @param lagalistaIndex
     */
    public void setLagalistaIndex(int lagalistaIndex) {
        this.lagalistaIndex = lagalistaIndex;
    }

    /**
     * gets index
     * @return
     */
    public int getLagalistaIndex() {
        return lagalistaIndex;
    }

    /**
     * gets listi
     * @return
     */
    public ObservableList<Lag> getListi() {
        return listi;
    }

    /**
     * sets Listi
     * @param listi
     */
    public void setListi(ObservableList<Lag> listi) {
        this.listi = listi;
    }

    /**
     * sets the amount <br>
     * amount is for amount of songs
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * gets amount
     * @return
     */
    public int getAmount() {
        return amount;
    }

    /**
     * sets the song to the ObservableList
     */
    public void setLog() {
        for (int i = 0; i< amount; i++){
            Lag lag = new Lag();
//            System.out.println(cul_index + "  "+ row_index + " Lagalisti");
            lag.lesaLog("Lagalisti","Music",lagalistaIndex, i);
            listi.add(lag);
        }
//        System.out.println(listi.get(listi.size()-1));
    }
    /**
     * sets the song to the ObservableList
     */
    public void setLog(ObservableList<String> lines) {
        AtomicInteger counter = new AtomicInteger();
        lines.forEach((line) ->{
            Lag lag = new Lag();
//            System.out.println(line);
            lag.lesaLogv2("Lagalisti",line, lagalistaIndex, counter.get());
            listi.add(lag);
            counter.getAndIncrement();

        });
//        for (int i = 0; i< amount; i++){
//            Lag lag = new Lag();
////            System.out.println(cul_index + "  "+ row_index + " Lagalisti");
//            lag.lesaLog("Lagalisti","Music",lagalistaIndex, i);
//            listi.add(lag);
//        }
//        System.out.println(listi.get(listi.size()-1));
    }

    /**
     * sets the song to the ObservableList
     */
    public void setLog2(ObservableList<LagData> lines) {
        AtomicInteger counter = new AtomicInteger();
        lines.forEach((line) ->{
            Lag lag = new Lag();
//            System.out.println(line);
            lag.setUuid(line.getUuid());
            lag.lesaLogv2("Lagalisti",line.getUuid(), lagalistaIndex, counter.get());
            listi.add(lag);
            counter.getAndIncrement();

        });
//        for (int i = 0; i< amount; i++){
//            Lag lag = new Lag();
////            System.out.println(cul_index + "  "+ row_index + " Lagalisti");
//            lag.lesaLog("Lagalisti","Music",lagalistaIndex, i);
//            listi.add(lag);
//        }
//        System.out.println(listi.get(listi.size()-1));
    }
    /**
     * adds a Song
     * @return new amount
     */
    public int addlag(){
        Lag lag = new Lag();
//        System.out.println(cul_index + "  "+ row_index + " Lagalisti");
        lag.lesaLog("Lagalisti","Music",lagalistaIndex, listi.size());
        listi.add(lag);
        return listi.size();
    }
    /**
     * adds a Song
     * @param name
     * @return new amount
     */
    public int addlag(String name){
        Lag lag = new Lag();
//        System.out.println(cul_index + "  "+ row_index + " Lagalisti");
        lag.lesaLog("Lagalisti",name,lagalistaIndex, listi.size());
        listi.add(lag);
        return listi.size();
    }

    /**
     * adds a Song
     * @param uuid
     * @return new amount
     */
    public int addlagv2(String uuid){
        Lag lag = new Lag();
//        System.out.println(cul_index + "  "+ row_index + " Lagalisti");
        lag.lesaLogv2("Lagalisti",uuid,lagalistaIndex, listi.size());
        listi.add(lag);
        return listi.size();
    }

    private Lag getEnd(){
        return listi.get(listi.size()-1);
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    public void vistaLagalisti(String s) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(s))) {
            for (Lag lag : listi) {
                // Skrifa hverja línu í skrá með gögnum úr hvert lag
                writer.write(lag.getNafn() + " " + lag.getMynd() + " " + lag.getMedia() + " " + lag.getLengd());
                writer.newLine(); // Ný lína á eftir hverju lagi
            }
        } catch (IOException e) {
            e.printStackTrace(); // Eða önnur meðhöndlun villu eftir þörfum
        }
    }
    public void fjarlaegjaLag(Lag lag) {
        listi.remove(lag);
    }

}
