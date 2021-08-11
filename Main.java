import java.util.Random;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

enum Rarity {
    COMMON, UNCOMMON, RARE, SUPER_RARE, LEGENDARY, MYTHICAL;

    public String getRarity() {
        switch(this) {
            case COMMON:
                return "common";
            case UNCOMMON:
                return "uncommon";
            case RARE:
                return "rare";
            case SUPER_RARE:
                return "super rare";
            case LEGENDARY:
                return "legendary";
            case MYTHICAL:
                return "mythical";
            default: 
                return null;
        }
    }
}

enum Type {
    CONSUMABLE, USABLE, EQUIPPABLE, THROWABLE
}

class Item {
    String name, description;
    int levelNeeded;
    Type type;
    Rarity rarity;
    public void setDetails() {
        return;
    }
}

class WitchsBestFriend extends Item {
    public void setDetails() {
        name = "Witch's Best Friend";
        description = "It is a staff with a obvious phallic shape at the end, it can shoot magic missiles.";
        levelNeeded = 69;
        type = Type.USABLE;
        rarity = Rarity.MYTHICAL;
    }
    WitchsBestFriend() {
        setDetails();
    }
}

class Stick extends Item {
    public void setDetails() {
        name = "Stick";
        description = "A basic stick.";
        levelNeeded = 0;
        type = Type.USABLE;
        rarity = Rarity.COMMON;
    }
    public int attackMin = 1, attackMax = 5;

    Stick() {
        setDetails();
    }
}

class Bokin extends Item {
    public void setDetails() {
        name = "Bokin";
        description = "A wooden training sword.";
        levelNeeded = 1;
        type = Type.USABLE;
        rarity = Rarity.COMMON;
    }
    Bokin() {
        setDetails();
    }
    public int attackMin = 3, attackMax = 8;
}

class Slingshot extends Item {
    public void setDetails() {
        name = "Hunter's Slingshot";
        description = "A slingshot for hunting small game.";
        levelNeeded = 5;
        type = Type.USABLE;
        rarity = Rarity.UNCOMMON;
    }
    Slingshot() {
        setDetails();
    }
    public int attackMin = 11, attackMax = 23;
}

class Pistol extends Item {
    public void setDetails() {
        name = "Old War Flintlock Pistol";
        description = "A restored flintlock pistol from the war. Slow to reload, but she packs a punch!";
        levelNeeded = 20;
        type = Type.USABLE;
        rarity = Rarity.SUPER_RARE;
    }
    Pistol() {
        setDetails();
    }
    public int attackMin = 53, attackMax = 89;
}

class TedBear extends Item {
    public void setDetails() {
        name = "Old Teddy Bear";
        description = "An old, worn out teddy bear. It's warm to the touch.";
        levelNeeded = 25;
        type = Type.CONSUMABLE;
        rarity = Rarity.LEGENDARY;
    }
    TedBear() {
        setDetails();
    }
    public String effect = "+1Resurection";
}

/*class something extends Item {
    public void setDetails() {
        name = "";
        description = "";
        levelNeeded = ;
        type = Type.;
        rarity = Rarity.;
    }
    something() {
        setDetails();
    }
}*/



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
    public int level, experience;
    public String nameOfPlayer, gender;
    public String[] pronouns = new String[2];
    public int defense, attack;
    public Item[] armor = new Item[4];
    public Item[] weapons = new Item[3];
    public ArrayList<Item> inventory = new ArrayList<Item>(); 
    public Item equipedWeapon;
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
        String mainMenu = "-------- MAIN MENU --------\nWhat would you like to do?\n---------------------------\nStart Game\nLoad Game (Coming soon! (Hopefully))\nSettings\nExit"; 
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
            System.out.println("Creating new character... Please wait...");
            Player player = new Player();
            try {
                System.out.println("What is the name of your new character?");
                player.nameOfPlayer = input.nextLine();
                System.out.println("What is the gender of your new character? (Male, Female, other)");
                player.gender = input.nextLine();
                System.out.println("What are the pronouns of your character? Split them with a space, for example: 'She Her' or 'He Them'.");
                player.pronouns = input.nextLine().split(" ");
                player.level = 1;
                player.health = 10;
                player.experience = 0;
                player.defense = 0;
                player.attack = 0;
                Stick starterStick = new Stick();
                player.weapons[0] = starterStick;
                System.out.println("Character successfully created!");
            } catch(Exception error) {
                System.out.println("An error occured! Please report this at https://github.com/WizardMaster38/Infernum-Ad-Infinitum-To-Hell-and-Beyond/issues/new (Error: " + error + ")");
                System.exit(0);
            }
            return(player);
        }
        else {
            Player player = new Player();
            return player;
        }
    }

    static ArrayList<Item> openChest(String type, int amount) {
        ArrayList<Item> returnedItems = new ArrayList<Item>();
        int howManyItems = rand.nextInt(5) + 1;
        //System.out.println(howManyItems);
        double[] chances = new double[6];
        Stick stick = new Stick();
        WitchsBestFriend WitchsBestFriend = new WitchsBestFriend();
        Slingshot Slingshot = new Slingshot();
        Pistol pistol = new Pistol();
        TedBear bear = new TedBear();
        Item[] commonItems = {stick};
        Item[] uncommonItems = {Slingshot};
        Item[] rareItems = {};
        Item[] superRareItems = {pistol};
        Item[] legendaryItems = {bear};
        Item[] mythicalItems = {WitchsBestFriend};
        Item[] itemSet;
        switch(type) {
            case "common":
                chances[0] = 62.5;
                chances[1] = 27.5;
                chances[2] = 7.5;
                chances[3] = 2.5;
                chances[4] = 0;
                chances[5] = 0;
            case "uncommon":
                chances[0] = 50.0;
                chances[1] = 37.5;
                chances[2] = 7.5;
                chances[3] = 4.5;
                chances[4] = 0.5;
                chances[5] = 0.0;
        }    

        for (int i = 0; i <= howManyItems - 1; i++) {
            int randomRarityInt = rand.nextInt(1000) + 1;
            double randomRarityDouble = randomRarityInt / 10.0;
            int randomItemInt;
            try{    
                if (randomRarityDouble <= chances[0]) {
                    itemSet = commonItems;
                } else if (randomRarityDouble > chances[0] && randomRarityDouble <= (chances[1] + chances[0])) {
                    itemSet = uncommonItems;
                } else if (randomRarityDouble > (chances[1] + chances[0]) && randomRarityDouble <= (chances[2] + chances[1] + chances[0])) {
                    itemSet = rareItems;
                } else if (randomRarityDouble > (chances[2] + chances[1] + chances[0]) && randomRarityDouble <= (chances[3] + chances[2] + chances[1] + chances[0])) {
                    itemSet = superRareItems;
                } else if (randomRarityDouble > (chances[3] + chances[2] + chances[1] + chances[0]) && randomRarityDouble <= (chances[4] + chances[3] + chances[2] + chances[1] + chances[0])) {
                    itemSet = legendaryItems;
                } else {
                    itemSet = mythicalItems;
                }
                //System.out.print(itemSet.length);
                randomItemInt = rand.nextInt(itemSet.length);
            } catch(Exception e) {
                itemSet = commonItems;
                randomItemInt = rand.nextInt(itemSet.length);
            }
            returnedItems.add(itemSet[randomItemInt]);
        }
        return(returnedItems);
    }

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

                if (i == 0 && j == 0) {
                    if (RoomGenerator[i][j] == 0 || RoomGenerator[i][j] == 1 || RoomGenerator[i][j] == 4) {
                        j--;
                        continue;
                    }
                } else if (j == 0) {
                    if (RoomGenerator[i][j] == 0 || RoomGenerator[i][j] == 4) {
                        j--;
                        continue;
                    } 
                } else if (i == 0) {
                    if (RoomGenerator[i][j] == 0 || RoomGenerator[i][j] == 1) {
                        j--;
                        continue;
                    }
                } 
                else if (i == Height - 1 && j == Width - 1) {
                    if (RoomGenerator[i][j] == 0 || RoomGenerator[i][j] == 2 || RoomGenerator[i][j] == 1 || RoomGenerator[i][j] == 5) {
                        j--;
                        continue;
                    }
                } else if (j == Width - 1) {
                    if (RoomGenerator[i][j] == 1) {
                        j--;
                        continue;
                    }
                } else if (i == Height - 1) {
                    if (RoomGenerator[i][j] == 0 || RoomGenerator[i][j] == 2 || RoomGenerator[i][j] == 5) {
                        j--;
                        continue;
                    }
                } 
                
                if (allowedRooms[RoomGenerator[i][j]] == null) {
                    if (j > 0) {
                        j--;
                        continue;
                    }
                    else if (j == 0 && !(i == 0)) {
                        i--;
                        j = Width;
                        continue;
                    }
                    else if (j == 0 && i == 0) {
                        j--;
                        continue;
                    }
                } 
                try {
                    //System.out.println("Room " + RoomGenerator[i][j] + "Position: " + i + ", " + j + ".\n" + allowedRooms[RoomGenerator[i][j]]);
                } catch(Exception error) { // debugging
                    System.out.println(error);
                }
                if (j == 1 && i == 0) {
                    fullMapOne = addTwoRooms(allowedRooms[RoomGenerator[i][0]], allowedRooms[RoomGenerator[i][j]]);
                    //System.out.println(fullMapOne);
                } else if (j > 1 && i == 0) {
                    fullMapOne = addTwoRooms(fullMapOne, allowedRooms[RoomGenerator[i][j]]);
                    //System.out.println(fullMapOne);
                } else if (j == 1 && i == 1) {
                    fullMapTwo = addTwoRooms(allowedRooms[RoomGenerator[i][0]], allowedRooms[RoomGenerator[i][j]]);
                    //System.out.println(fullMapTwo);
                } else if (j > 1 && i == 1) {
                    fullMapTwo = addTwoRooms(fullMapTwo, allowedRooms[RoomGenerator[i][j]]);
                    //System.out.println(fullMapTwo);
                } else if (j == 1 && i == 2) {
                    fullMapThree = addTwoRooms(allowedRooms[RoomGenerator[i][0]], allowedRooms[RoomGenerator[i][j]]);
                    //System.out.println(fullMapThree);
                } else if (j > 1 && i == 2) {
                    fullMapThree = addTwoRooms(fullMapThree, allowedRooms[RoomGenerator[i][j]]);
                    //System.out.println(fullMapThree);
                } else if (j == 1 && i == 3) {
                    fullMapFour = addTwoRooms(allowedRooms[RoomGenerator[i][0]], allowedRooms[RoomGenerator[i][j]]);
                    //System.out.println(fullMapFour);
                } else if (j > 1 && i == 3) {
                    fullMapFour = addTwoRooms(fullMapFour, allowedRooms[RoomGenerator[i][j]]);
                    //System.out.println(fullMapFour);
                } else if (j == 1 && i == 4) {
                    fullMapFive = addTwoRooms(allowedRooms[RoomGenerator[i][0]], allowedRooms[RoomGenerator[i][j]]);
                    //System.out.println(fullMapFive);
                } else if (j > 1 && i == 4) {
                    fullMapFive = addTwoRooms(fullMapFive, allowedRooms[RoomGenerator[i][j]]);
                    //System.out.println(fullMapFive);
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
        System.out.println("Please wait, we are initializing everything!");
        String[] initializingValuesText = {"Initializing.. ", "Setting up map.. ", "Accessing databases.. ", "Fixing quantum particles.. ", "Solving for y.. ", "Hacking into myself.. ", "Breaking everything.. "};
        System.out.println(generateMap(5, 5, 1, "easy"));
        System.out.println(generateMap(5, 5, 1, "medium"));
        System.out.println(generateMap(5, 5, 1, "hard"));
        /*for (int i = 1; i < 20; i = i) {
            int numberOfSeconds = rand.nextInt(4) + 1;
            try {
                Thread.sleep(numberOfSeconds * 1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            i += numberOfSeconds;
            if (i > 20) {
                i = 20;
            }
            System.out.println(initializingValuesText[rand.nextInt(initializingValuesText.length)] + i * 5 + "%");
        }*/
        String action = mainMenu();
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