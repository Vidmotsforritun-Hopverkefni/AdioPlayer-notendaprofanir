package hi.vinnsla.audioplayer;
/******************************************************************************
 *  Nafn    : Daníel Ágúst Björnsson
 *  T-póstur: dab47@hi.is
 *
 *  Lýsing  : class for Askrifandi
 *  holds the data for Askrifandi
 *
 *
 *****************************************************************************/
public class Askrifandi {

    public Askrifandi(String nafn){
        this.nafn = nafn;
    }
    public String nafn;

    public void setNafn(String nafn_in){
        nafn = nafn_in;
    }

    public String getNafn(){
        return nafn;
    }
}
