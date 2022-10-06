package MyFiles;

import java.util.Scanner;

public class ConsoleHelper {
    public void out(String prompt){System.out.println(prompt);}

    public int getInt(String prompt){
        Scanner in = new Scanner(System.in);
        int i;
        out(prompt);
        try{
            i = in.nextInt();
        } catch (Exception e){
            out("Enter valid int.");
            return getInt(prompt);
        }
        return i;
    }

    public boolean getBoolean(String prompt){
        Scanner in = new Scanner(System.in);
        boolean i;
        out(prompt);
        try {
            i = in.nextBoolean();
        } catch (Exception e){
            out("Enter valid boolean.");
            return getBoolean(prompt);
        }
        return i;
    }

    public String getString(String prompt){
        Scanner in = new Scanner(System.in);
        String i;
        out(prompt);
        try {
            i = in.nextLine();
        } catch (Exception e){
            out("Enter valid string");
            return getString(prompt);
        }
        return i;
    }

    public double getDouble(String prompt){
        Scanner in = new Scanner(System.in);
        double i;
        out(prompt);
        try {
            i = in.nextDouble();
        } catch (Exception e){
            out("Enter valid double");
            return getDouble(prompt);
        }
        return i;

    }

    private boolean convertStatus(int value){
        if(value == 2){return false;}

        return true;
    }

    public void  pressEnter(){
        Scanner in = new Scanner(System.in);
        out("Press enter to continue");
        in.nextLine();
    }

    public void menuOptions(SmartBuilding smartHome){

        out(smartHome.displayOptions());

        int optionLevel = getInt("");
        switch(optionLevel){

            // house level options
            case 1:
                int value = getInt("HOUSE LEVEL OPTIONS\n" +
                        "1 - Switch all plugs off\n" +
                        "2 - Switch all plugs on\n" +
                        "Select an option\n");

                if(value == 1){
                    for(int i = 0; i<= smartHome.size();i++){smartHome.toggle(i,false);}
                }
                if(value == 2){
                    for(int i = 0; i<= smartHome.size();i++){smartHome.toggle(i,true);
                    }
                }
                break;
            // room level options
                case 2:
                int room = getInt(smartHome.displayRooms()+
                        "Please select room (integer only)\n");
                // display all plugs in room
                for(SmartPlugSwitch object : smartHome.smartPlugs){
                    if(room == object.getLocation()){
                        System.out.println(object+ "\n-------------------------");}}
                int value1 = getInt("ROOM LEVEL OPTIONS" +
                        "\n1 - Switch all plugs off in room" +
                        "\n2 - Switch all plugs on in room" +
                        "\n3 - Select a plug in the room and toggle its on/off status");
                if(value1 == 1){
                    for(SmartPlugSwitch object : smartHome.smartPlugs){
                        if(room == object.getLocation()){
                            smartHome.toggle(object.getId(), false);
                        }
                    }
                }
                if(value1 == 2){
                    for(SmartPlugSwitch object : smartHome.smartPlugs){
                        if(room == object.getLocation()) {
                            smartHome.toggle(object.getId(), true);
                        }
                    }
                }
                if(value1 == 3){
                    int askID = getInt("What ID you want to makes changes?");
                    int intStatus = getInt("Enter status of plug \n1 - On\n2 - Off");
                    boolean askStatus = convertStatus(intStatus);
                    for(SmartPlugSwitch object : smartHome.smartPlugs){
                        if(room == object.getLocation()){
                            smartHome.toggle(askID, askStatus);
                        }
                    }
                }
                break;
            // plug level options
                case 3:
                out(smartHome.display());
                int plugSelect = getInt("\nPlease select plug:");
                int value2 = getInt("PLUG LEVEL OPTIONS" +
                        "\n1 - Switch plug off" +
                        "\n2 - Switch plug on" +
                        "\n3 - Change attached device" +
                        "\n4 - Move plug to different room");
                if(value2 == 1){
                    for(SmartPlugSwitch object : smartHome.smartPlugs){
                        if(plugSelect == object.getId()){
                            smartHome.toggle(object.getId(), false);
                        }
                    }
                }
                if(value2 == 2){
                        for(SmartPlugSwitch object : smartHome.smartPlugs){
                            if(plugSelect == object.getId()){
                                smartHome.toggle(object.getId(), true);
                            }
                        }
                }
                if(value2 == 3){
                    int intItem = getInt(smartHome.displayItems()+
                            "\nEnter device to attach to smart plug");
                    ;
                    for(SmartPlugSwitch object : smartHome.smartPlugs){
                        if(plugSelect == object.getId()){
                            smartHome.setItem(object.getId(),smartHome.chooseItem(intItem));
                        }
                    }
                }
                if(value2== 4){
                    //Set new location
                    out("-------------ROOMS AVAILABLE-------------\n" + smartHome.displayRooms());
                    int location = getInt("Using the above list, please select the new room for this plug");
                    for(SmartPlugSwitch object : smartHome.smartPlugs){
                        if(plugSelect == object.getId()){
                            smartHome.setLocation(object.getId(), location);
                        }
                    }
                }
            break;
            // system level options
            case 4:
                int value3 = getInt("SYSTEM LEVEL OPTIONS" +
                        "\n1 - Add smart plug" +
                        "\n2 - Add device" +
                        "\n3 - Add room");
                if(value3==1){

                    out(smartHome.displayRooms());
                    //Set new location
                    int location = getInt("Using the above list, please select the new room for this plug");
                    // Set new item
                    int intItem = getInt(smartHome.displayItems()+"\nEnter device to attach to smart plug");
                    // set new id
                    int id = (smartHome.size()+1);
                    // add new plug
                    smartHome.addPlug(false,smartHome.chooseRoom(location),id,smartHome.chooseItem(intItem));
                    out("new plug added.");
                }
                if(value3==2){
                        String newItem = getString("Enter new item name:");
                        smartHome.addItem(newItem);
                }
                if(value3==3){
                        String newRoom = getString("Enter new room name:");
                        smartHome.addRoom(newRoom);
                }
                break;
            default:
                out("Please enter a valid option");
        }
    }

    public void populateSmartBuilding(SmartBuilding smartHome){
        // populate while smartHome is not full
        while(!smartHome.isPopulated()) {
            out("-------------ENTER PLUG INFORMATION BELOW-------------");
            // get location
            out(smartHome.displayRooms());
            int location = getInt("Using the above list, please select the room for this plug (integer only).");
            // get status
            boolean status = false;
            // get id
            int id = smartHome.index() + 1;
            // get item
            int intItem = getInt(smartHome.displayItems() + "\nUsing the above list, please select the device to attach to the smart plug (integer only)\n");
            // Pass the data into Smart building
            boolean success = smartHome.append(status, location, id, smartHome.chooseItem(intItem));
            if (!success) {
                out(smartHome.getMessage());
            }
        }
    }

    public void populateRooms(SmartBuilding smartHome, int numRooms){
        for(int i=0; i<numRooms;i++){
            String value = getString("Enter room "+ (i+1) +" name");
            smartHome.populateRooms(i,value);}
    }

}
