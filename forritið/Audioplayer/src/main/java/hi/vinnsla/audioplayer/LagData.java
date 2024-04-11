package hi.vinnsla.audioplayer;

import java.util.UUID;

public class LagData {
    private final UUID uuid_2 = generateUuid();
    private String name;
    private String uuid;


    public UUID generateUuid(){
        return UUID.randomUUID();
    }
    public LagData() {

    }

    public LagData(String n, String u){
        name = n;
        uuid = u;
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String toString() {
        return "Lag{" +
                "name='" + name + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
