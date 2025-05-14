package One_big_Package;

public class Egede {
    private String id;
    private String sorse;
    private String receiver;

    Egede (String sourse, String receiver){
        this.sorse = sourse;
        this.receiver = receiver;
        id = sourse + receiver;
    }

    public String getId() {
        return id;
    }

    public String getSorse() {
        return sorse;
    }

    public String getReceiver() {
        return receiver;
    }
}
