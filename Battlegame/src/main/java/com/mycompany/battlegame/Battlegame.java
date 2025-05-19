package com.mycompany.battlegame;


import java.util.Stack;
import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;


public class Battlegame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        class Character {
    String name;
    int hp;

    public Character(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void heal(int amount) {
        hp += amount;
        System.out.println(name + " healed for " + amount + " HP! Now at " + hp + " HP.");
    }
}

        Character player = new Character("Player 1", 500);
        Stack<Character> enemies = new Stack<>();

       
        HashMap<String, Integer> enemyStats = new HashMap<>();
        enemyStats.put("sven", 500);

        // Add enemies from HashMap to the stack
        for (String name : enemyStats.keySet()) {
            enemies.push(new Character(name, enemyStats.get(name)));
        }

        System.out.println("Game Start! You face 4 enemies.");

        while (player.isAlive() && !enemies.isEmpty()) {
            Character enemy = enemies.peek();

            System.out.println("\nYour HP: " + player.hp);
            System.out.println(enemy.name + " HP: " + enemy.hp);
            System.out.println("Choose action");
            System.out.println("1. Attack  ");
            System.out.println("2. crit damage");
            int choice = scanner.nextInt();

            int damage = 0;

            if (choice == 1) {
                damage = random.nextInt(10) + 5; // 5–14
                System.out.println("You attack and deal " + damage + " damage!");
            } else if (choice == 2) {
                damage = random.nextInt(30) + 10; // 10–39
                System.out.println("You used Critical Skill and dealt " + damage + " damage!");
                
            } else {
                System.out.println("Invalid choice! Turn skipped.");
                continue;
            }

            // passive skill: 25 chance enemy heals
            if (random.nextInt(100) < 25) {
                enemy.heal(damage);
            } else {
                enemy.hp -= damage;
                System.out.println(enemy.name + " takes " + damage + " damage!");
            }

            if (!enemy.isAlive()) {
                System.out.println(enemy.name + " is defeated!");
                enemies.pop();
                continue;
            }

            // Enemy attacks back
            int enemyDamage = random.nextInt(10) + 10;
            player.hp -= enemyDamage;
            System.out.println(enemy.name + " attacks back and deals " + enemyDamage + " damage!");

            if (player.hp <= 0) {
                System.out.println("\nYou were defeated. Game Over.");
                break;
            }
        }

        if (player.isAlive()) {
            System.out.println("\nAll enemies defeated! You win!");
        }

        
    }
}


