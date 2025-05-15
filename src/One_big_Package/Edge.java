package One_big_Package;

public class Edge {
    private String id;
    private String sorse;
    private String receiver;

    Edge(String sourse, String receiver){
        this.sorse = sourse;
        this.receiver = receiver;
        id = sourse + receiver;
    }

    public String getId() {
        return id;
    }

    public String getSource() {
        return sorse;
    }

    public String getReceiver() {
        return receiver;
    }
}
