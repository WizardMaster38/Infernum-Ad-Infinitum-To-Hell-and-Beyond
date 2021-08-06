import java.util.Random;
import java.util.HashMap;
import java.util.Scanner;

abstract class Enemy {
    Random rand = new Random();
    double health;
    int level;
    String nameOfEnemy;
    String[] names = {"Bob", "Jeff", "Jess", "Alyssa", "Chad"};
    abstract void intilizeValues(); 
}

class Player {
    Random rand = new Random();
    public double health;
    public int level;
    public int ammo;
    public String nameOfPlayer;
}

class Slime extends Enemy {
    void intilizeValues() {
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
        intilizeValues();
    }
}

class Main {
    static Random rand = new Random();
    static Scanner input = new Scanner(System.in);
    static HashMap<String, Slime> slimes = new HashMap<>();
    static HashMap<String, Double> slimeStatAverages = new HashMap<>();

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
    
    static String mainMenu() {
        String mainMenu = "----- MAIN MENU -----\nWhat would you like to do?\n------------\nStart Game\nLoad Game\nSettings\nExit"; 
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

    public static void main(String[] args) {
        String action = mainMenu();
        System.out.println(action);
    }
}