package javaapplication16;
import java.util.*;


public class JavaApplication16 {

    private static final Random rndm = new Random();

    // Player Implementation
    public static class PlayerImpl implements Player {
        private String name;
        private int health;
        private int defense;

        public PlayerImpl(String name, int health, int defense) {
            this.name = name;
            this.health = health;
            this.defense = defense;
        }

        @Override
        public String getName() {
            return name;
        }

        
        @Override
        public int attack() {
            return Math.max(0, rndm.nextInt(11) + 5 - defense);
        }

        @Override
        public int getHealth() {
            return health;
        }

        @Override
        public void reduceHealth(int damage) {
            health -= damage;

            if (health <= 0) {
                System.out.println("Game over");
                System.exit(0);
            }

            int regenerationAmount = (int) (0.10 * health);
            health += regenerationAmount;

            System.out.println("After defeating the enemy, " + getName() + " regenerates " + regenerationAmount + " health.");
            System.out.println("Current Health: " + getHealth());
        }

        @Override
        public void dodge() {
            System.out.println(getName() + " dodges the enemy's attack!");
            if (rndm.nextDouble() < 0.5) {
                System.out.println("Successfully dodged the enemy attack");
            } else {
                System.out.println("Failed to dodge the enemy attack");
            }
        }
    }

    // Gamemode Implementation
    public static class GamemodeImpl implements Gamemode {
        @Override
        public int selectMode() {
            System.out.println("1 - Story");
            System.out.println("2 - Survival");

            Scanner scn = new Scanner(System.in);
            return scn.nextInt();
        }
    }

    // Gameplay Implementation
    public static class GameplayImpl implements Gameplay {
        @Override
        public void startPlay(Player player) {
            System.out.println("Press P to start playing, " + player.getName());
        }
    }

    // Enemy Implementation
    public static class EnemyImpl implements Enemy {
        private int defense;
        private int health;

        @Override
        public String getName() {
            return "enemy";
        }

        @Override
        public int attack() {
            // Randomly choose the damage between 5 and 15, considering enemy defense
            return Math.max(0, rndm.nextInt(11) + 5 - defense);
        }

        @Override
        public int getHealth() {
            return health;
        }

        @Override
        public boolean canDodge() {
            return rndm.nextBoolean();
        }

         public int getDefense() {
        return defense;
    }

        public void decreaseDefense() {
            if (defense > 0) {
                defense--;
            } else {
                health--;
            }
        }
    }

    // StoryMode Implementation
    public static class StoryMode implements Gameplay {
        @Override
        public void startPlay(Player player) {
            System.out.println("Welcome to Story Mode, " + player.getName() + "!");
            System.out.println("Press P To Continue");
            Scanner scn = new Scanner(System.in);
            scn.nextLine();

            Random rndm = new Random();
            List<Enemy> enemies = new ArrayList<>();

            // Generate 5 regular enemies
            for (int i = 0; i < 5; i++) {
                enemies.add(new EnemyImpl());
            }

            // Final boss
           // Final boss - Ashly
                   Enemy ashly = new EnemyImpl() {
                   private int health = 5000; // Set health for Ashly

                            @Override
                    public String getName() {
                               return "Ashly";
               }

    @Override
    public int attack() {
        return 5; // Adjust the attack damage as needed
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public boolean canDodge() {
        return false; // Adjust as needed
    }
};

        String story1 = "You fell asleep at STI building and woke up at 3 am, and you were the only one in the building "
                + "\n You saw Ashly holding Nathan's head."
                + "\n You followed Ashly and investigated. "
                + "\n You see that Ashly has allies, and you are seen as one of Ashly's allies.";
        for (int i = 0; i < story1.length(); i++) {
            System.out.print(story1.charAt(i));
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

            // Encounter regular enemies
            for (Enemy currentEnemy : enemies) {
                System.out.println("You encounter a " + currentEnemy.getName() + "! What will you do?");
                System.out.println("1. Engage in battle\n2. Try to sneak past quietly");
                System.out.print("Enter your choice: ");

                int choice = scn.nextInt();
                scn.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.println("You decided to engage in combat with the " + currentEnemy.getName() + ". ");
                        int damage = currentEnemy.attack();
                        System.out.println("You attack the " + currentEnemy.getName() + " and deal " + damage + " damage. ");

                        // enemy attack
                        int enemydmg = currentEnemy.attack();
                        System.out.println("The " + currentEnemy.getName() + " counterattacks and deals " + enemydmg + " damage. ");

                        // Reduce player health
                        player.reduceHealth(enemydmg);

                        if (player.getHealth() <= 0) {
                            System.out.println("You Died");
                            return;
                        }
                    }

                    case 2 -> {
                        // Option 2
                        System.out.println("You attempt to sneak past the " + currentEnemy.getName() + " quietly.");
                        // 50% chance
                        int sneakchance = rndm.nextInt(2);
                        if (sneakchance == 0) {
                            System.out.println("Success! You manage to avoid the " + currentEnemy.getName() + ". ");
                        } else {
                            System.out.println("Oops! You have been spotted by " + currentEnemy.getName() + ". ");

                            // enemy attacks
                            int senemydmg = currentEnemy.attack();
                            System.out.println("The " + currentEnemy.getName() + " attacks you and deals " + senemydmg + " damage.");

                            // reduce player health
                            player.reduceHealth(senemydmg);

                            if (player.getHealth() <= 0) {
                                System.out.println("You Died");
                                return;
                            }
                        }
                    }

                    default -> {
                        System.out.println("Invalid choice. The " + currentEnemy.getName() + " attacks you.");

                        int defaultEnemyDmg = currentEnemy.attack();
                        player.reduceHealth(defaultEnemyDmg);

                        if (player.getHealth() <= 0) {
                            System.out.println("You Died");
                            return;
                        }
                    }
                }
                System.out.println("Press Enter to continue...");
                scn.nextLine();
            }

            // Final Boss - Ashly
            System.out.println("You Encounter the final Boss Ashly");
            System.out.println("Ashly: Ah, " + player.getName() + ", I didn't expect you to make it this far.");
            System.out.println(player.getName() + " Ashly What did you do why, did you kill nathan");
            System.out.println("Ashly: Nathan? He's of no concern to you now. You should have stayed out of this.");
            System.out.println("");

            // Battle with Ashly
            System.out.println("1. Kill Ashly  \n 2. Attempt to negotiate ");
            System.out.print("Enter your choice: ");
            int choice = scn.nextInt();
            scn.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println(player.getName() + ": I'm not here to talk. Let's finish this!");
                    System.out.println("You decided to engage in combat with the final boss, " + ashly.getName() + ". ");
                    int ashlyDamage = ashly.attack();
                    System.out.println("You attack " + ashly.getName() + " and deal " + ashlyDamage + " damage. ");

                    // Ashly's counterattack
                    int ashlyCounterAttack = ashly.attack();
                    System.out.println(ashly.getName() + " counterattacks and deals " + ashlyCounterAttack + " damage. ");

                    // Reduce player health
                    player.reduceHealth(ashlyCounterAttack);

                    if (player.getHealth() <= 0) {
                        System.out.println("Game over");
                        return;
                    }
                }

                case 2 -> {
                    System.out.println(player.getName() + ": Ashly, there must be another way. We don't have to fight.");
                    System.out.println("You attempt to negotiate with " + ashly.getName() + ". ");
                    if (!ashly.canDodge()) {
                        System.out.println(ashly.getName() + " doesn't seem interested in negotiation and attacks you!");

                        // Ashly's attack
                        int ashlyNegotiationAttack = ashly.attack();
                        System.out.println(ashly.getName() + " attacks you and deals " + ashlyNegotiationAttack + " damage. ");

                        // Reduce player health
                        player.reduceHealth(ashlyNegotiationAttack);

                        if (player.getHealth() <= 0) {
                            System.out.println("Game over");
                            return;
                        }
                    } else {
                        System.out.println(ashly.getName() + " dodges your negotiation attempt and prepares to attack!");
                    }
                }

                default -> {
                    System.out.println("Invalid choice. " + ashly.getName() + " attacks you.");
                    int defaultAshlyAttack = ashly.attack();
                    player.reduceHealth(defaultAshlyAttack);

                    if (player.getHealth() <= 0) {
                        System.out.println("Game over! Your health reached zero.");
                        return;
                    }
                }
            }

            // Continue the story
            System.out.println("You have defeated Ashly, but the mysteries of STI building still linger.");
            System.out.println("As you explore further, you discover a hidden passage.");
        }
    }

    // Main Class
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter your name : ");
        String playerName = scn.nextLine();

        Player player = new PlayerImpl(playerName, 100, 0, 1);
        Gamemode gamemode = new GamemodeImpl();
        int selectMode = gamemode.selectMode();

        Gameplay gameplay = new GameplayImpl();
        gameplay.startPlay(player);

        if (selectMode == 1) {
            StoryMode storyMode = new StoryMode();
            storyMode.startPlay(player);
        } else if (selectMode == 2) {
            System.out.println("Survival mode: " + player.getName() + "!");
            playSurvivalMode(player);
        }
    }

    private static void playSurvivalMode(Player player) {
        Random rndm = new Random();
        Scanner scn = new Scanner(System.in);

        // Survival mode
       System.out.println("Press E to attack, Q to quit, D to dodge");

        while (player.getHealth() > 0) {
            System.out.println("Current Health : " + player.getHealth());
            System.out.println("Encountered an Enemy!");

            // Create a random enemy
            Enemy enemy = new EnemyImpl();
            System.out.println("A " + enemy.getName() + " appears!");
            System.out.println("Press E to attack, Q to quit, D to dodge");

            String input = scn.nextLine();
            if (input.equalsIgnoreCase("E")) {
                // Attack
                int damage = enemy.attack();
                System.out.println("You attack " + enemy.getName() + " and deal " + damage + " damage.");

                // Enemy Attack
                int enemyDmg = enemy.attack();
                System.out.println("The " + enemy.getName() + " attacks you and deals " + enemyDmg + " damage.");
                player.reduceHealth(enemyDmg); // Reduce player health by enemy damage
            } else if (input.equalsIgnoreCase("D")) {
                // Dodge the enemy's attack
                if (enemy.canDodge()) {
                    player.dodge();
                    System.out.println("The " + enemy.getName() + " dodges your attack!");
                    continue;  // Skip player's attack if the enemy dodges
                } else {
                    System.out.println("You attempt to dodge, but the " + enemy.getName() + " attacks you.");
                    int enemyDmg = enemy.attack();
                    System.out.println("The " + enemy.getName() + " deals " + enemyDmg + " damage.");
                    player.reduceHealth(enemyDmg);
                }
            } else if (input.equalsIgnoreCase("Q")) {
                System.out.println("Quitting Survival Mode. Your final health : " + player.getHealth());
                break;
            } else {
                System.out.println("Invalid Input");
                continue;
            }

            // Display enemy damage and defense
            int adjustedEnemyDmg = rndm.nextInt(11) + 5;
            System.out.println("The " + enemy.getName() + " attacks you and deals " + adjustedEnemyDmg + " damage.");

            // Adjust enemy damage based on player's attack
            int enemyDmg = rndm.nextInt(11) + 5;
            System.out.println("The " + enemy.getName() + " attacks you and deals " + enemyDmg + " damage.");

            // Reduce player health
            player.reduceHealth(enemyDmg);

            if (player.getHealth() <= 0) {
                System.out.println("Game Over");
                return;
            }

            // Display adjusted enemy damage and defense
            System.out.println("The " + enemy.getName() + "'s adjusted damage is " + adjustedEnemyDmg);
            System.out.println("The " + enemy.getName() + "'s defense is " + enemy.getDefense());

            System.out.println("Press Enter to continue...");
            scn.nextLine();
        }
        System.out.println("Press E to exit");
    }
}
