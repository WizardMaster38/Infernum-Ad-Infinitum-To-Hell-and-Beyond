import java.util.Random;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

abstract class Enemy {
    Random rand = new Random();
    double health;
    int level;
    String nameOfEnemy, description;
    String[] names = {"Bob", "Jeff", "Jess", "Alyssa", "Chad"};
    abstract void initializeValues(); 
}

class Player {
    Random rand = new Random();
    public double health;
    public int level;
    public String nameOfPlayer, gender;
    public String[] pronouns = new String[2];
}

class Slime extends Enemy {
    static HashMap<String, Slime> slimes = new HashMap<>();
    static HashMap<String, Double> slimeStatAverages = new HashMap<>();
    void initializeValues() {
        int nameNumber = rand.nextInt(101);
        if (nameNumber >= 0 && nameNumber <= 23) {
            nameOfEnemy = names[0];
        }
        else if (nameNumber >= 24 && nameNumber <= 47) {
            nameOfEnemy = names[1];
        }
        else if (nameNumber >= 48 && nameNumber <= 71) {
            nameOfEnemy = names[2];
        }
        else if (nameNumber >= 72 && nameNumber <= 95) {
            nameOfEnemy = names[3];
        }
        else if (nameNumber >= 96) {
            nameOfEnemy = names[4];
        }  
        level = rand.nextInt(10) + 1;
        health = (rand.nextInt(10) + (10 * (level - 1)) + 1);
        if (nameOfEnemy == "Chad") {
            health = 150;
            level = 15;
        }        
    }
    Slime() {
        initializeValues();
    }
    
    static void addSlime(Slime slimeInstance) {
        try {
            int slimeNumber = slimes.size() + 1;
            String slimeName = "slime" + String.valueOf(slimeNumber);
            slimes.put(slimeName, slimeInstance);
            //System.out.println("successful! " + slimeName + " created!");
        }
        catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    static void generateSlimes(int amount) {
        System.out.println("Generating " + amount + " slimes!");
        for(int i = 1; i <= amount; i++) {
            Slime slime = new Slime();
            addSlime(slime);
        }
        System.out.println(amount + " slimes generated successfully!");
    }

    static void getStats() {
        int Bob = 0; 
        int Jeff = 0; 
        int Jess = 0;
        int Alyssa = 0;
        int Chad = 0;

        for (String key : slimes.keySet()) {
            String nameOfEnemy = slimes.get(key).nameOfEnemy;
            if (nameOfEnemy == "Bob") {
                Bob += 1;
            }
            else if (nameOfEnemy == "Jeff") {
                Jeff += 1;
            }
            else if (nameOfEnemy == "Jess") {
                Jess += 1;
            }
            else if (nameOfEnemy == "Alyssa") {
                Alyssa += 1;
            }
            else if (nameOfEnemy == "Chad") {
                Chad += 1;
            }
            else {
                System.out.println("What?");
            }
        }

        double sizeOfSlimes = (double) slimes.size();
        double BobAverage = ((Bob / sizeOfSlimes) * 100);
        double JeffAverage = ((Jeff / sizeOfSlimes) * 100);
        double JessAverage = ((Jess / sizeOfSlimes) * 100);
        double AlyssaAverage = ((Alyssa / sizeOfSlimes) * 100);
        double ChadAverage = ((Chad / sizeOfSlimes) * 100);

        slimeStatAverages.put("BobAverage", BobAverage);
        slimeStatAverages.put("JeffAverage", JeffAverage);
        slimeStatAverages.put("JessAverage", JessAverage);
        slimeStatAverages.put("AlyssaAverage", AlyssaAverage);
        slimeStatAverages.put("ChadAverage", ChadAverage);

        for (String key : slimeStatAverages.keySet()) {
            double average = slimeStatAverages.get(key);
            System.out.println(key + ": " + average + "%");
        }
    }
    
    static Slime getSlime(int slimeNumber) {
        //System.out.println(slimes.get("slime" + slimeNumber));
        return slimes.get("slime" + slimeNumber);
    }

    static boolean removeSlime(int slimeNumber) {
        Slime removedSlime = slimes.remove("slime" + slimeNumber);
        return(removedSlime instanceof Slime);
    }

}

class FireSlime extends Slime {
    FireSlime() {
        initializeValues();
    }
}

class Main {
    static Random rand = new Random();
    static Scanner input = new Scanner(System.in);
    
    static String mainMenu() {
        String mainMenu = "----- MAIN MENU -----\nWhat would you like to do?\n------------\nStart Game\nLoad Game (Coming soon! (Hopefully))\nSettings\nExit"; 
        System.out.println(mainMenu);
        String nextInput = input.nextLine();
        nextInput = nextInput.toLowerCase();
        switch (nextInput) {
            case "start game":
                return("start game");
            case "load game":
                return("load game");
            case "settings":
                return("settings");
            case "exit":
                System.exit(1);
            default: 
                System.out.println("Invalid input. Please try again.");
                return(mainMenu());
        }
    }

    static void settingsMenu() {
        return; 
    }

    static Player createNewPlayer(boolean newCharacter) {
        if (newCharacter) {
            Player player = new Player();
            System.out.println("What is the name of your new character?");
            player.nameOfPlayer = input.nextLine();
            System.out.println("What is the gender of your new character? (Male, Female, other)");
            player.gender = input.nextLine();
            System.out.println("What are the pronouns of your character? Split them with a space, for example: 'She Her' or 'He Them'.");
            player.pronouns = input.nextLine().split(" ");
            return(player);
        }
        else {
            Player player = new Player();
            return player;
        }
    }

    
    //static void 

    /*
    Room A:
    ___|  |_____ 
    |          | 
   _|   X  X   |_
   ____|  |______
       |  |      
    
    Room B:
    ____|  |_____  
    |           | 
    |     XX    |_
    | C          _
    |___________| 

    Room C:
______________
| C   C   C  |
|     X*     |
|____  ______| 
    |  |      
 
    Room D:

     _____________
     |        C|_|
    _|    X    |_|
    _     X    |_|
     |___________|
    Key:
    X - Enemy
    X* - Boss
    C - Chest
    C* - Fake Chest
    */

    static String addTwoRooms(String firstRoom, String secondRoom) {
        
        String newRoom = "";

        String[] roomFirst = firstRoom.split("\n");
        String[] roomSecond = secondRoom.split("\n"); 

        for (int i = 0; i < 5; i++) {
            String string = roomFirst[i].concat(roomSecond[i]);
            string = string.concat("\n");
            newRoom = newRoom.concat(string);
        }        

        return(newRoom);
    }

    static String generateMap(int Width, int Height, int Floors, String Mode) { 
        String a = System.getenv("A");
        String b = System.getenv("B"); 
        String c = System.getenv("C");
        String d = System.getenv("D");
        String e = System.getenv("E(mpty)");
        String c2 = System.getenv("C2");
        String fullMapOne = "";
        String fullMapTwo = "";
        String fullMapThree = "";
        String fullMapFour = "";
        String fullMapFive = "";
        String fullMap = "";
        String[] allowedRooms = new String[6];
        int[][] RoomGenerator = new int[Height][Width];
        switch(Mode) {
            case "easy":
                allowedRooms[0] = a;
                allowedRooms[1] = b;
                allowedRooms[2] = c;
                allowedRooms[3] = e;
                break;
            case "medium":
                allowedRooms[0] = a;
                allowedRooms[1] = b;
                allowedRooms[2] = c;
                allowedRooms[3] = e;
                allowedRooms[4] = d;
                break;
            case "hard":
                allowedRooms[0] = a;
                allowedRooms[1] = b;
                allowedRooms[2] = c;
                allowedRooms[3] = e;
                allowedRooms[4] = d;
                allowedRooms[5] = c2;
                break;
        }
        int bossRooms = 0;
        for (int i = 0; i < Height; i++) { // X dimension
            for (int j = 0; j < Width; j++) { // Y dimension
                try {
                    RoomGenerator[i][j] = rand.nextInt(6); // generates the numbers
                } catch (Exception error2) {
                    System.out.println("An error occured! Please report this at https://github.com/WizardMaster38/Infernum-Ad-Infinitum-To-Hell-and-Beyond/issues/new (Error: " + error2 + ")");
                    System.exit(1);
                }
                if (RoomGenerator[i][j] == 2 || RoomGenerator[i][j] == 5) {
                    if (bossRooms < 1) {
                        bossRooms++;
                    } else if (bossRooms >= 1) {
                        RoomGenerator[i][j] = 3;
                    }
                }
                if (allowedRooms[RoomGenerator[i][j]] == null) {
                    if (j > 0) {
                        j--;
                    }
                    else if (j == 0 && !(i == 0)) {
                        i--;
                        j = Width;
                    }
                    else if (j == 0 && i == 0) {
                        j--;
                    }
                } else{
                    try {
                        System.out.println("Room " + RoomGenerator[i][j] + "Position: " + i + ", " + j + ".\n" + allowedRooms[RoomGenerator[i][j]]);
                    } catch(Exception error) { // debugging
                        System.out.println(error);
                    }
                    if (j == 1 && i == 0) {
                        fullMapOne = addTwoRooms(allowedRooms[RoomGenerator[i][0]], allowedRooms[RoomGenerator[i][j]]);
                        System.out.println(fullMapOne);
                    } else if (j > 1 && i == 0) {
                        fullMapOne = addTwoRooms(fullMapOne, allowedRooms[RoomGenerator[i][j]]);
                        System.out.println(fullMapOne);
                    } else if (j == 1 && i == 1) {
                        fullMapTwo = addTwoRooms(allowedRooms[RoomGenerator[i][0]], allowedRooms[RoomGenerator[i][j]]);
                        System.out.println(fullMapTwo);
                    } else if (j > 1 && i == 1) {
                        fullMapTwo = addTwoRooms(fullMapTwo, allowedRooms[RoomGenerator[i][j]]);
                        System.out.println(fullMapTwo);
                    } else if (j == 1 && i == 2) {
                        fullMapThree = addTwoRooms(allowedRooms[RoomGenerator[i][0]], allowedRooms[RoomGenerator[i][j]]);
                        System.out.println(fullMapThree);
                    } else if (j > 1 && i == 2) {
                        fullMapThree = addTwoRooms(fullMapThree, allowedRooms[RoomGenerator[i][j]]);
                        System.out.println(fullMapThree);
                    } else if (j == 1 && i == 3) {
                        fullMapFour = addTwoRooms(allowedRooms[RoomGenerator[i][0]], allowedRooms[RoomGenerator[i][j]]);
                        System.out.println(fullMapFour);
                    } else if (j > 1 && i == 3) {
                        fullMapFour = addTwoRooms(fullMapFour, allowedRooms[RoomGenerator[i][j]]);
                        System.out.println(fullMapFour);
                    } else if (j == 1 && i == 4) {
                        fullMapFive = addTwoRooms(allowedRooms[RoomGenerator[i][0]], allowedRooms[RoomGenerator[i][j]]);
                        System.out.println(fullMapFive);
                    } else if (j > 1 && i == 4) {
                        fullMapFive = addTwoRooms(fullMapFive, allowedRooms[RoomGenerator[i][j]]);
                        System.out.println(fullMapFive);
                    }
                }
            }
        }
        fullMap = fullMapOne + fullMapTwo + fullMapThree + fullMapFour + fullMapFive;
        return(fullMap);
    }

    static void startNewGame(boolean newGame) {
        Player player;
        if (newGame) {
            player = createNewPlayer(newGame);
        } else {
            player = new Player();
        }
        
    }

    public static void main(String[] args) {
        /*String a = System.getenv("A");
        String b = System.getenv("B");
        String c = System.getenv("C");
        String d = System.getenv("D");
        String e = System.getenv("E(mpty)");
        String c2 = System.getenv("C2");*/
        System.out.println("Please wait, we are initializing everything!");
        String[] initializingValuesText = {"Initializing.. ", "Setting up map.. ", "Accessing databases.. ", "Fixing quantum particles.. ", "Solving for y.. ", "Hacking into myself.. ", "Breaking everything.. "};
        
        System.out.println("Full map:\n" + generateMap(5, 5, 3, "easy"));
        System.out.println("Full map:\n" + generateMap(5, 5, 3, "medium"));
        System.out.println("Full map:\n" + generateMap(5, 5, 3, "hard"));

        for (int i = 1; i < 20; i = i) {
            int numberOfSeconds = rand.nextInt(4) + 1;
            try {
                Thread.sleep(numberOfSeconds * 1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            i += numberOfSeconds;
            System.out.println(initializingValuesText[rand.nextInt(initializingValuesText.length)] + i * 5 + "%");
        }
        String action = mainMenu();
        //System.out.println(action);
        switch (action) {
            case "start game":
                startNewGame(true);
                break;
            case "load game":
                System.out.println("Function not available yet! Please select another option.");
                System.exit(0);
            case "settings":
                //return("settings");
        }
    }
}