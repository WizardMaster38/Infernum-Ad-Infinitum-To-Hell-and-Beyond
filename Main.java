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

        for (int i = 0; i < 4; i++) {
            String string = roomFirst[i].concat(roomSecond[i]);
            string = string.concat("\n");
            newRoom = newRoom.concat(string);
        }        

        return(newRoom);
    }

    static boolean validateRow(int[] rowOne, int[] rowTwo) {
        boolean validRow = true;
        // self-row validation first
        for (int one = 0; one < rowTwo.length - 1; one++) {
            if (validRow == false) {
                break;
            }
            int firstRoom = rowTwo[one];
            int secondRoom = rowTwo[one + 1];
            if (firstRoom == 0 || firstRoom == 1 || firstRoom == 2 || firstRoom == 4 || firstRoom == 5 || firstRoom == 7 || firstRoom == 8 || firstRoom == 11 || firstRoom == 15) {
                if (secondRoom == 1 || secondRoom == 2 || secondRoom == 3 || secondRoom == 5 || secondRoom == 7 || secondRoom == 9 || secondRoom == 10 || secondRoom == 14) {
                    validRow = true;
                } else {
                    validRow = false;
                }
            } else {
                if (secondRoom == 1 || secondRoom == 2 || secondRoom == 3 || secondRoom == 5 || secondRoom == 7 || secondRoom == 9 || secondRoom == 10 || secondRoom == 14) {
                    validRow = false;
                }
            }
        }
        if (rowOne.equals(rowTwo)) {
            return(validRow);
        }
        for (int one = 0; one < rowOne.length; one++) {
            if (validRow == false) {
                break;
            }
            int roomAbove = rowOne[one], roomBelow = rowTwo[one];
            if (roomAbove == 1 || roomAbove == 3 || roomAbove == 4 || roomAbove == 5 || roomAbove == 6 || roomAbove == 8 || roomAbove == 9 || roomAbove == 13) {
                if (roomBelow == 2 || roomBelow == 3 || roomBelow == 4 || roomBelow == 5 || roomBelow == 6 || roomBelow == 10 || roomBelow == 11 || roomBelow == 16) {
                    validRow = true;
                } else {
                    validRow = false;
                }
            } else {
                if (roomBelow == 2 || roomBelow == 3 || roomBelow == 4 || roomBelow == 5 || roomBelow == 6 || roomBelow == 10 || roomBelow == 11 || roomBelow == 16) {
                    validRow = false;
                }
            }
        }
        return(validRow);
    }

    static String generateMap(int Width, int Height, int Floors) {
        String spawn = "_________ \n|       |_\n|        _\n|_______| ";
        String tInter1 = " ________ \n_|C    C|_\n___ XX ___\n   |  |   ", tInter2 = " __|  |__ \n_|     C|_\n_   XX   _\n |______| ", tInter3 = " __|  |___\n_|  XX  C|\n___    __|\n   |  |   ", tInter4 = "___|  |__ \n|C  XX  |_\n|__    ___\n   |  |   ";
        String plusInter = " __|  |__ \n_|C    C|_\n___ XX ___\n   |  |   ";
        String iInter1 = "___|  |___\n|   XX  C|\n|__    __|\n   |  |   ", iInter2 = " ________ \n_|     C|_\n_   XX   _\n |______| ";
        String lInter1 = "___|  |__ \n|       |_\n|C  XX   _\n|_______| ", lInter2 = " __|  |___\n_|       |\n_   XX  C|\n |_______|", lInter3 = " _________\n_|  XX  C|\n___    __|\n   |  |   ", lInter4 = "_________ \n|C  XX  |_\n|__    ___\n   |  |   ";
        String boss1 = "__________\n|   CC   |\n|__ X* __|\n   |  |   ", boss2 = " _________\n_|      C|\n_   X*  C|\n |_______|", boss3 = "_________ \n|C      |_\n|C  X*   _\n|_______| ", boss4 = "___|  |___\n|C      C|\n|   X*   |\n|________|";
        String empty = "          \n          \n          \n          ";
        int[][] roomGenerator = new int[Height][Width];
        int bossRooms = 0;
        String[] rooms = {spawn, tInter1, tInter2, tInter3, tInter4, plusInter, iInter1, iInter2, lInter4, lInter3, lInter2, lInter1, empty, boss1, boss2, boss3, boss4};
        String fullMap = "";

        for (int h = 0; h < Height; h++) {
            for (int w = 0; w < Width; w++) {
                int newInt = rand.nextInt(16) + 1;
                if (h == 0 && w == 0) {
                    newInt = 0;
                }
                if (h == Height - 1 && w == Width - 1) {
                    if (bossRooms == 0) {
                        newInt = 14;
                    }
                }
                if (h == 0) {
                    if (newInt == 2 || newInt == 3 || newInt == 4 || newInt == 5 || newInt == 6 || newInt == 10 || newInt == 11 || newInt == 16) {
                        w--;
                        if (bossRooms >= 1 && newInt == 16) {
                            bossRooms = 0;
                        }
                        continue;
                    }
                }
                if (w == 0) {
                    if (newInt == 1 || newInt == 2 || newInt == 3 || newInt == 5 || newInt == 7 || newInt == 9 || newInt == 10 || newInt == 14) {
                        w--;
                        if (bossRooms >= 1 && newInt == 14) {
                            bossRooms = 0;
                        }
                        continue;
                    }
                }
                if (h == Height - 1) {
                    if (newInt == 1 || newInt == 3 || newInt == 4 || newInt == 5 || newInt == 6 || newInt == 8 || newInt == 9 || newInt == 13) {
                        w--;
                        if (bossRooms >= 1 && newInt == 13) {
                            bossRooms = 0;
                        }
                        continue;
                    }
                }
                if (w == Width - 1) {
                    if (newInt == 1 || newInt == 2 || newInt == 4 || newInt == 5 || newInt == 7 || newInt == 8 || newInt == 11 || newInt == 15) {
                        w--;
                        if (bossRooms >= 1 && newInt == 15) {
                            bossRooms = 0;
                        }
                        continue;
                    }
                }
                if (newInt >= 13) {
                    if (bossRooms == 0) {
                        bossRooms++;
                    } else {
                        newInt = rand.nextInt(12) + 1;
                    }
                }
                if (h == 0) {
                    if (newInt == 2 || newInt == 3 || newInt == 4 || newInt == 5 || newInt == 6 || newInt == 10 || newInt == 11 || newInt == 16) {
                        w--;
                        if (bossRooms >= 1 && newInt == 16) {
                            bossRooms = 0;
                        }
                        continue;
                    }
                }
                if (h == 0 && w == 1 && newInt == 14) {
                    w--;
                    bossRooms--;
                    continue;
                }
                if (w == 0) {
                    if (newInt == 1 || newInt == 2 || newInt == 3 || newInt == 5 || newInt == 7 || newInt == 9 || newInt == 10 || newInt == 14) {
                        w--;
                        if (bossRooms >= 1 && newInt == 14) {
                            bossRooms = 0;
                        }
                        continue;
                    }
                }
                if (h == Height - 1) {
                    if (newInt == 1 || newInt == 3 || newInt == 4 || newInt == 5 || newInt == 6 || newInt == 8 || newInt == 9 || newInt == 13) {
                        w--;
                        if (bossRooms >= 1 && newInt == 13) {
                            bossRooms = 0;
                        }
                        continue;
                    }
                }
                if (w == Width - 1) {
                    if (newInt == 1 || newInt == 2 || newInt == 4 || newInt == 5 || newInt == 7 || newInt == 8 || newInt == 11 || newInt == 15) {
                        w--;
                        if (bossRooms >= 1 && newInt == 15) {
                            bossRooms = 0;
                        }
                        continue;
                    }
                }
                roomGenerator[h][w] = newInt;
            }
            if (h == 0) {
                boolean validRow = validateRow(roomGenerator[h], roomGenerator[h]);
                //System.out.println(validRow);
                if (!(validRow)) {
                    h--;
                    continue;
                }
            } else {
                boolean validRow = validateRow(roomGenerator[h - 1], roomGenerator[h]);
                //System.out.println(validRow);
                if (!(validRow)) {
                    h--;
                    continue;
                }
            }
        }
        String fullRowOne = "", fullRowTwo = "", fullRowThree = "", fullRowFour = "", fullRowFive = "";
        for (int i = 0; i < roomGenerator.length; i++) {
            for (int j = 0; j < roomGenerator[0].length; j++) {
                if (j == 0) {
                    continue;
                }
                if (i == 0) {
                    if (j == 1) {
                        fullRowOne = addTwoRooms(rooms[roomGenerator[i][j - 1]], rooms[roomGenerator[i][j]]);
                    } else {
                        fullRowOne = addTwoRooms(fullRowOne, rooms[roomGenerator[i][j]]);
                    }
                } else if (i == 1) {
                    if (j == 1) {
                        fullRowTwo = addTwoRooms(rooms[roomGenerator[i][j - 1]], rooms[roomGenerator[i][j]]);
                    } else {
                        fullRowTwo = addTwoRooms(fullRowTwo, rooms[roomGenerator[i][j]]);
                    }
                } else if (i == 2) {
                    if (j == 1) {
                        fullRowThree = addTwoRooms(rooms[roomGenerator[i][j - 1]], rooms[roomGenerator[i][j]]);
                    } else {
                        fullRowThree = addTwoRooms(fullRowThree, rooms[roomGenerator[i][j]]);
                    }
                } else if (i == 3) {
                    if (j == 1) {
                        fullRowFour = addTwoRooms(rooms[roomGenerator[i][j - 1]], rooms[roomGenerator[i][j]]);
                    } else {
                        fullRowFour = addTwoRooms(fullRowFour, rooms[roomGenerator[i][j]]);
                    }
                } else if (i == 4) {
                    if (j == 1) {
                        fullRowFive = addTwoRooms(rooms[roomGenerator[i][j - 1]], rooms[roomGenerator[i][j]]);
                    } else {
                        fullRowFive = addTwoRooms(fullRowFive, rooms[roomGenerator[i][j]]);
                    }
                } 
            }
        }
        fullMap = fullRowOne + fullRowTwo + fullRowThree + fullRowFour + fullRowFive;
        return(fullMap);
    }    

    static void startNewGame(boolean newGame) {
        Player player;
        if (newGame) {
            player = createNewPlayer(newGame);
        } else {
            player = new Player();
        }
        System.out.println("How many rooms high would you like your map to be? (Maximum 5)");
        int height = input.nextInt();
        if (height > 5) {
            height = 5;
        }
        System.out.println("How many rooms wide would you like your map to be? (Maximum 5)");
        int width = input.nextInt();
        if (width > 5) {
            width = 5;
        }
        System.out.println("How many floors would you like your map to be? (Maximum 1)");
        int floors = input.nextInt();
        if (floors > 1) {
            floors = 1;
        }
        String chosenMap = "";
        String[] maps = {generateMap(height, width, floors), generateMap(height, width, floors), generateMap(height, width, floors), generateMap(height, width, floors), generateMap(height, width, floors)};
        for (String map: maps) {
            if (chosenMap != "") {
                break;
            }
            System.out.println("Would you like this map?");
            System.out.println(map);
            String doYouWantIt = input.nextLine();
            if (doYouWantIt.toLowerCase() == "yes") {
                chosenMap = map;
            } else if (doYouWantIt.toLowerCase() == "no") {
                continue;
            } else {
                System.out.println("Invalid input. Please try again.");
                mainMenu();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Please wait, we are initializing everything!");
        /*String[] initializingValuesText = {"Initializing.. ", "Setting up map.. ", "Accessing databases.. ", "Fixing quantum particles.. ", "Solving for y.. ", "Hacking into myself.. ", "Breaking everything.. "};
        for (int i = 1; i < 20; i = i) {
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