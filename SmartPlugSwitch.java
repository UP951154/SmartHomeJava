package MyFiles;

public class SmartPlugSwitch {
    private boolean status;
    private int location;
    private int id;
    private String item;

    // Constructor
    public SmartPlugSwitch(boolean status, int location, int id, String item) {
        this.status = status;
        this.location = location;
        this.id = id;
        this.item = item;
    }

    // Get/Set

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    // toString

    public String onOFF(boolean status){
        if(!status){return "OFF";}
        return "ON";
    }

    public void toggle(boolean status){
        setStatus(status);
    }


    @Override
    public String toString() {
        return "\nSmartPlug | attached to: " + item + "|room: " +location+" |ID: " +id+" |status: "+  onOFF(status) +" | ";
    }
}
