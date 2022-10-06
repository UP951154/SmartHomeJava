package MyFiles;

public class SmartBuilding {

    public SmartPlugSwitch[] smartPlugs;
    private int currentIndex;
    private String message;
    private String[] rooms;
    private int roomIndex;
    private String[] items;


    // Constructor
    public SmartBuilding(int numPlugs, int numRooms){
        smartPlugs = new SmartPlugSwitch[numPlugs];
        rooms = new String[numRooms];
        items = new  String[5];
        setItems();
        currentIndex = 0;
        int roomIndex = 1;
        message = "";

    }

    private void setItems(){
        items[0] = "Lamp";
        items[1] = "Tv";
        items[2] = "Computer";
        items[3] = "Phone recharger";
        items[4] = "Heater";
    }


    public String chooseItem(int value){
        return items[(value-1)];
    }
    public void addItem(String newItem){
        String[] newItems = new String[(items.length+1)];

        for(int i = 0; i < items.length; i++){
            newItems[i] = items[i];
        }

        newItems[items.length] = newItem;

        items = newItems;

    }

    public void addRoom(String newRoom){
        String[] newRooms = new String[(rooms.length+1)];

        for(int i = 0; i < rooms.length; i++){
            newRooms[i] = rooms[i];
        }

        newRooms[rooms.length] = newRoom;

        rooms = newRooms;
    }
    public int chooseRoom(int  value){
        return value;
    }

    //  CONSOLE DISPLAY
        // DISPLAY ROOMS
        public String displayRooms(){
        String s = "";
        int index = 1;
        System.out.println("ROOMS AVAILABLE:");
        for(String room: rooms){
            s += index +" - " + room + "\n";
            index++;
        }
        return s;
    }
        // DISPLAY PLUGS
        public String display() {
        if (!isPopulated()) {
            return "is not fully populated";
        }
        int index = 1;
        String s = "";
        System.out.println("---------------DASHBOARD---------------");
        for (String room : rooms) {
            s += "\nRoom: " + index;

            for(SmartPlugSwitch object : smartPlugs){
                if(object.getLocation()==index){
                    s += object.toString();}

            }
            index++;
        }
        return s;
    }
        // DISPLAY ITEMS
        public String displayItems(){
            String s = "";
            int index = 1;
            System.out.println("\nAVAILABLE DEVICE LIST OPTIONS" +
                    "\nThese are standard devices that can be attached to the smart plug:");
            for(String item : items){
                s += index + " - " + item+"\n";
                index ++;
            }
            return s;
        }
        // DISPLAY OPTIONS
        public String displayOptions(){
            String s = "-------------MENU OPTIONS-------------\n" +
                    "---------please select option:--------"+
                    "\n1 - house level options"+
                    "\n2 - room level options"+
                    "\n3 - plug level options"+
                    "\n4 - system options";
        return s;
        }


    public void populateRooms(int index, String value){

        rooms[index] = value;

    }

    public void addPlug(boolean status, int location, int id,String item){

        SmartPlugSwitch[] newSmartPlugs = new SmartPlugSwitch[(smartPlugs.length+1)];

        for(int i = 0; i < smartPlugs.length; i++){
            newSmartPlugs[i] = smartPlugs[i];
        }

        newSmartPlugs[smartPlugs.length] = new SmartPlugSwitch(status,location,id,item);

        smartPlugs = newSmartPlugs;

        currentIndex++;

    }
    //Index
    public int index(){return currentIndex;}
    // Size
    public int size(){
        return smartPlugs.length;
    }

    public boolean isPopulated(){
        if(currentIndex>=size()){return true;}
        return false;
    }

    // Add status, location, id, item

    public boolean append(boolean status, int location, int id, String item){
        // Check size
        if(isPopulated()){
            message = "exceeded upper bound";
            return false;}
        // Check unique
            // ID UNIQUE
            if(!isIdUnique(id)){
                message = "id already taken";
                return false;}
            // ITEM UNIQUE
            if(!isItemUnique(item, location)){
                message = "item already taken";
                return false;}

        // Append
        SmartPlugSwitch object = new SmartPlugSwitch(status, location, id, item);
        smartPlugs[currentIndex] = object;
        currentIndex++;

        return true;
    }




    // Check is unique

    public boolean isIdUnique(int id){
        for(int i = 0; i < currentIndex; i++){
            if(smartPlugs[i].getId() == id){return false;}
        }
        return true;

    }

    public boolean isItemUnique(String item, int location){
        for(int i = 0; i < currentIndex; i++){
            if(smartPlugs[i].getItem().equals(item) && smartPlugs[i].getLocation() == location){return false;}
        }
        return true;
    }

    public String listLocation(){


        String s = "";
            for (SmartPlugSwitch object : smartPlugs) {
                s += object.getLocation() + "\n";
            }

        return "------Locations------ \n" + s;
    }

    public void toggle(int id, boolean status){
        for(SmartPlugSwitch object : smartPlugs){
            if(object.getId() == id){object.toggle(status);}
        }
    }

    public void setItem(int id, String item){
        for(SmartPlugSwitch object : smartPlugs){
            if(object.getId() == id){object.setItem(item);}
        }
    }

    public void setLocation(int id, int location){
        for(SmartPlugSwitch object : smartPlugs){
            if(object.getId() == id){object.setLocation(location);}
        }
    }

    public String getMessage(){return message;}

    // ROOMS




}
