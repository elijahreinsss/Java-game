
package javaapplication16;
import java.util.*;

public class JavaApplication16 {

  
  

  // Implementation for player
public static class Playerimpl implements Player {
    private String name;
    private int health;

    public Playerimpl(String name, int health) {
        this.name = name;
        this.health = health;
    }

   @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void reduceHealth(int damage) {
        health -= damage;
    if(health<=0) {
      system.out.println("Game Over Beter luck next time!");
      return;
    }
      int regeneration = (int) (0.10 * health);
      health += regeneration;
     system.out.println(After defeating the enemy, " + getName() + " regenerates " + regeneration + " health");
     System.out.println("Current Health: " + getHealth());
      
}

   // Implementation for gamemode
   public static class gamemodeimpl implements Gamemode{
       @Override
       public int selectmode(){
           System.out.println("1 - Story");
           System.out.println("2 - Survival");
  
           Scanner scn = new Scanner(System.in);
           return scn.nextInt();
       }
   }
    // Implementation for gameplay
   public static class gameplayimpl implements Gameplay{
    @Override
        public void startplay(Player player) {
            System.out.println("Press P to start playing, " + player.getName());
        }
    }
   // Implementation of Enemy
 public static class enemyimpl implements  Enemy {
      private int health;

public enemyimpl() {
this.health = 100; // default enemy health
}
   

   @Override
       public String enemy(){
           return "enemy";
       }
   @Override
   public int attack(){
       return 10;
   }
   @Override
   public int getHealth(){
      return health;
   }
 }
 
 public static class StoryMode implements Gameplay{
     @Override
     public void startplay(Player player){
         System.out.println(" Welcome to Story Mode " + player.getName() + " ! ");
         System.out.println("Press P To Continue");
         Scanner scn = new Scanner(System.in);
         scn.nextLine();
         
         
         // Story
        Random rndm = new Random();
        int enemynum = 5;
        Enemy enemy = new enemyimpl();
       List<Enemy> enemies = new ArrayList<>();

       for(int i = 0; i < 5; i++) {
       enemies.add(new enemyimpl());
       }
       // Final Boss
       enemy boss = new enemy(){
         private int health = 150; // boss healt

         @Override
         public String enemy(){
             return "boss";
         }
         @Override
         public int getHealth(){
            return health;
         }
         enemies.add(boss);
         
         System.out.println("You fell asleep at STI building and woke up at 3 am and you were the only one in the building  ");
         System.out.println("");
         System.out.println("");
         System.out.println("and you saw ashly holding nathan's head");
         System.out.println("");
         System.out.println("");
         System.out.println("You followed ashly and investigated");
         System.out.println("");
         System.out.println("");
         System.out.println("You see that ashly has friends and you are seen as one of ashly's friends ");
         System.out.println("");
         System.out.println("");

        // Encounter Regular enemies
         for(int i = 0; i < enemies.size(); i++{
          System.out.println("You encounter a " +  enemy.enemy()  + "! What will you do?  " );
         System.out.println("");
          System.out.println("1. Engage in battle" + "\n2. Try to sneak past quietly");
         System.out.println("");
           System.out.print("Enter your choice: ");
         System.out.println("");
  
         int choice = scn.nextInt();
         scn.nextLine();
           switch(choice){
            // option 1
             case 1:
                 System.out.println("You decided to engage in combat with the " + enemy.enemy() + " . ");
                 int damage = enemy.attack();
                 System.out.println("You attack the " + enemy.enemy() + " and deal " + damage + " damage. ");
                 
                 // enemy attack
                 int enemydmg = enemy.attack();
                 System.out.println(" The " + enemy.enemy() + " counter attacks and deals" + enemydmg + " damage. ");
                 
                 //Reduce player health
                 player.reduceHealth(enemydmg);
                 
                 if(player.getHealth() <= 0) {
                     System.out.println("Game over");
                     return;
                 }
                 break;
                  case 2:
                 // Option 2
                 System.out.println(" You attempt to sneak past the " + enemy.enemy() + " quietly. ");
                 // 50% chance 
                 int sneakchance = rndm.nextInt(2); 
                 if(sneakchance == 0) {
                     System.out.println(" Success! you manage to avoid the " + enemy.enemy() + " . ");
                 }else{
                     System.out.println(" Oops! you have been spotted " + enemy.enemy() + " . ");
                     
                     // enemy attacks
                     int senemydmg = enemy.attack();
                     System.out.println(" The " + enemy.enemy() + " attacks you and deals " + senemydmg + " damage. ");
                     
                     //reduce player health
                     player.reduceHealth(senemydmg);
                     
                       if (player.getHealth() <= 0) {
                         System.out.println("Game over");
                         return;
                       }
                     
                       break;
                   
                    default:
                System.out.println("Invalid choice. The " + enemy.enemy() + " attacks you.");

                int defaultEnemyDmg = enemy.attack();
                player.reduceHealth(defaultEnemyDmg);

                if (player.getHealth() <= 0) {
                    System.out.println("Game over! Your health reached zero.");
                    return;
                }
                break;
        }
    }
     System.out.println("Press Enter to Continue");
         scn.nextLine();
 
}
       // Final Boss Ashly
                    System.out.println("You Encounter the final Boss Ashly");
                    System.out.println(player.getName + "Ashly What did you do why, did you kill nathan");
                    System.out.println("Ashly:");

fgdsdfgfdgfdgdgfgdfghhgfhgfh

        
   
    // main class
   public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter your name : ");
        String playername = scn.nextLine();
        
           // Create player instance
                 Player player = new Playerimpl(playername, 100);
        
        Gamemode gamemode = new gamemodeimpl();
        
         // Create game mode instance
        int selectmode = gamemode.selectmode();
               
        // Select game mode
        Gameplay  gameplay  = new gameplayimpl();
       
        
        
        gameplay.startplay(player);
      
        
        if(selectmode == 1){
            System.out.println("Story mode : " + player.getName() + "!");
        }
        else if(selectmode == 2){
            System.out.println("Survival mode : " + player.getName() + "!");
            playSurvivalmode(player);
        }
        
        gameplay.startplay(player);
    }
   
  private static void playSurvivalmode(Player player){
      Random rndm = new Random();
      Scanner scn = new Scanner(System.in);
      
//Survival mode
while (player.getHealth() > 0){
    System.out.println("Current Health : " + player.getHealth());
    System.out.println("Encountered an Enemy!");
    
    // Create a random enemy
    Enemy enemy = new enemyimpl();
    System.out.println("A " + enemy.enemy() + " appears!");
    System.out.println("Press E to attack, Q to quit");
    
    String input = scn.nextLine();
    if(input.equalsIgnoreCase("E")){
        // Attack
        int damage = enemy.attack();
        System.out.println("You attack " + enemy.enemy() + " and deal " + damage + " damage ");
        
       // Enemy Attack
       int enemydmg = enemy.attack();
       System.out.println("The " + enemy.enemy() + " attacks you and deals " + enemydmg + " damage.");
       
    }else if (input.equalsIgnoreCase("Q")){
        System.out.println("Quiting Survival Mode. Your final health : " + player.getHealth());
        break;
    }else{
        System.out.println("Invalid Input");
        continue;
    }
     int enemydmg = rndm.nextInt(20) + 5;
     System.out.println(" The " + enemy.enemy() + " attacks you and deal " + enemydmg + " damage " );
   
     // Reduce player health
     player.reduceHealth(enemydmg);
     
     if(player.getHealth() <= 0){
         System.out.println("Game Over");
         return;
     }
}
      System.out.println("Press E tp");

      
  }
    
 }
}
