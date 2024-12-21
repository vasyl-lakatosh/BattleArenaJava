package com.battlearena;
import java.util.Random;
import java.util.Scanner;

public class Game {

   public static void main(String[] args) {

       Scanner scanner = new Scanner(System.in);

       System.out.println("Welcome to Battle Arena!");
       System.out.println("Choose your character:");
       System.out.println("1. Warrior");
       System.out.println("2. Mage");
       System.out.println("3. Archer");

       int choice = scanner.nextInt();

       Character player;

       switch (choice) {
           case 1:
               player = new Warrior("Player Warrior");
               break;
           case 2:
               player = new Mage("Player Mage");
               break;
           case 3:
               player = new Archer("Player Archer");
               break;
           default:
               throw new IllegalArgumentException("Invalid choice");
       }

       Character enemy = new Warrior("Enemy Warrior");

       System.out.println("An enemy has appeared!");

       BattleArena arena = new BattleArena();
       arena.startBattle(player, enemy);
   }
}

abstract class Character {
	public String name;
	private int health;
	public int maxAttackPower;
	public int minAttackPower;
	
	public Character(String name, int maxAttackPower, int minAttackPower) {
		this.name = name;
		this.maxAttackPower = maxAttackPower;
		this.minAttackPower = minAttackPower;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int newValue) {
		if (newValue > 100) {
			health = 100;
		}
		else if (newValue <= 0) {
			health = 0;
		} else {
			health = newValue;
		}
	}
	
	public boolean isAlive() {
		return health > 0;
	}
	
	public void displayStats() {
		System.out.println(name + " Stats:\n" + "Health: " + health + "\nAttack Power Range: " + maxAttackPower + " - " + minAttackPower);
	}
	
	public abstract void attack(Character target);
}

class Warrior extends Character {

	public Warrior(String name) {
		super(name, 40, 4);
	}
	
	@Override
	public void attack(Character target) {
		
        Random random = new Random();
        int attackPower = random.nextInt(maxAttackPower - minAttackPower + 1) + minAttackPower;

        target.setHealth(target.getHealth() - attackPower);
	}
}