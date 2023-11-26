
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
    }
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
       @Override
       public String enemy(){
           return "enemy";
       }
   @Override
   public int attack(){
       return 10;
   }
 }
 
   
   
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
     }
}
      
  }
    
}
