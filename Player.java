import java.io.*;

public class Player{
    private Integer health, ammo, damage, score, coins;
    private static final String FILE_PATH = "game_data.txt";

    public Player() {
        health = 3;
        ammo = 0;
        damage = 1;
        score = 0;
        coins = loadCoins();
    }

    //Save and Load methods took inspiration from Stack Overflow
    public void saveCoins() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(Integer.toString(coins));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int loadCoins() {
        int coins = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = reader.readLine();
            if (line != null) {
                coins = Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return coins;
    }

    public void reset() {
        health = 3;
        ammo = 0;
        damage = 1;
        score = 0;
        coins = loadCoins();
    }

    //Moves
    public void reload() {
        ammo++;
    }
    public Integer attack() {
        return damage;
    }
    public String defend() {
        return "defend";
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
    public String getScore() {
        return score.toString();
    }
    public int getCoins() {
        return coins;
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
    public void setScore() {
        score = coins * 5;
    }
    public void setCoins(int n) {
        coins = n;
    }
    public void addCoins(int n) {
        coins += n;
    }
    public void subCoins(int n) {
        coins -= n;
    }

}
