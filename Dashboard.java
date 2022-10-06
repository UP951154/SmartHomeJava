package MyFiles;

public class Dashboard {

    public static void main(String[] args) {
	    ConsoleHelper console = new ConsoleHelper();


	    int numPlugs = console.getInt("How many plugs do you want to place in this property?");

	    int numRooms = console.getInt("How many rooms are there in this property?");


	    SmartBuilding smartHome = new SmartBuilding(numPlugs, numRooms);



//	    // Populate

        console.populateRooms(smartHome,numRooms);
        console.populateSmartBuilding(smartHome);

        // Loop
        while(true) {
            console.out(smartHome.display());
            console.menuOptions(smartHome);

        }







    }
}
