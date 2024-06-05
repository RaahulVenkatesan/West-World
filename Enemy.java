import java.util.ArrayList;

public class Enemy {
    private int health, ammo, damage;
    private ArrayList<String> powers = new ArrayList<String>();

    //Constructors
    public Enemy(int h, int a, int d) {
        health = h;
        ammo = a;
        damage = d;
    }

    //Methods
    public void reload() {
        ammo++;
    }
    public Integer attack() {
        return damage;
    }
    public String defend() {
        return "defend";
    }
    public Integer moveDecision() {
        int decision = (int) (Math.random()*3); //0 is Attack, 1 is Defend, 2 is Reload
        return decision;
    }

    //Getters
    public int getHealth() {
        return health;
    }
    public int getAmmo() {
        return ammo;
    }
    public int getDamage() {
        return damage;
    }

    //Setters
    public void setHealth(int n) {
        health = n;
    }
    public void setAmmo(int n) {
        ammo = n;
    }
    public void setDamage(int n) {
        damage = n;
    }

}