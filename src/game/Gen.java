package game;

import java.util.Random;

public class Gen {
    public static Random rand = new Random();
    public static String testString = "THIS IS IN GEN";

    private static int generateHealth(int level){
        int health, minHealth = 75, realCap;
        double cap = 100;

        if (level == 1){
            cap = 125;
        }  
        else if(level <= 5){
            cap = cap + (level * (cap * .25));
        }
        else{
            cap = cap + (level * (cap * .1));
        }

        cap -= minHealth;

        realCap = (int)cap;
        health = rand.nextInt(realCap) + minHealth + 1;
        return health;
    }

    private static int generateArmor(int level, boolean player){
        int cap, armor, minArmor = 5;
        if (player){
            cap = level * 4 + 4;
            if (cap >= 50){
                cap = 50;
            } 
        }
        else{
            cap = level * 2 + 4;
            if (cap >= 30){
                cap = 30;
            }
        }
        cap -= minArmor;
        armor = rand.nextInt(cap) + minArmor + 1;
        return armor;
    }

    private static int generateShield(int bossnum){
        int shield, realCap, minshield = 50;
        double cap = 100;
        cap = cap + (bossnum * (cap * .25));

        realCap = (int)cap;

        shield = rand.nextInt(realCap) + minshield + 1;

        return shield;        
    }

    private static int generateSpeed(int level){
        int speed, cap, minspeed = 5;
        cap = level * 4 + 4;
            if (cap >= 50){
                cap = 50;
            } 
        speed = rand.nextInt(cap) + minspeed + 1;
        return speed;
    }

    private static int generateEvade(int level){
        int evade, cap, minEvade = 5;
        cap = level * 4 + 4;
            if (cap >= 50){
                cap = 50;
            } 
        evade = rand.nextInt(cap) + minEvade + 1;
        return evade;
    }

    private static int genereateEfficiency(){
        int efficiency, cap = 5;

        efficiency = rand.nextInt(cap + 1);

        return efficiency;
    }

    private static int generateWeaponSize(int level){
        int size, cap;
        cap = level * 2 + 3;

        size = rand.nextInt(cap) + 1;
        return size;
    }

    private static int generateWeaponDamage(int level){
        int damage, cap, minDamage = 30;
        cap = level * 10;
        damage = rand.nextInt(cap) + minDamage + 1;

        return damage;
    }

    private static int generateCooldown(){
        int cooldown, cap = 5;

        cooldown = rand.nextInt(cap) + 1; // TODO fix when playtest happens

        return cooldown;
    }

    private static int generateWeaponcharge(){
        int charge, cap = 10, minCharge = 3;

        charge = rand.nextInt(cap) + minCharge + 1; 
        
        return charge;
    }

    private static String generateWeaponType(){
        String type;
        int num = rand.nextInt(2);
        if (num == 0){
            type = "Projectile";
        }
        else{
            type = "Beam";
        }
        return type;
    }

    public static SuperHull generateSuperHull(int level, boolean player){
        SuperHull hull;
        int bossnum = level / 5;
        if (player){
            hull = new SuperHull(generateHealth(level) * 2, generateArmor(level, player), 0);
        }
        else{
            hull = new SuperHull(generateHealth(level) * 2, generateArmor(level, player) / 2, generateShield(bossnum));
        }
        return hull;
    }

    public static SuperEngine generateSuperEngine(int level){
        SuperEngine engine = new SuperEngine(generateEvade(level) * 2, generateSpeed(level) * 2, generateCooldown(), genereateEfficiency());
        return engine;
    }

    public static SuperWeapon generateSuperWeapon(int level){
        SuperWeapon weapon = new SuperWeapon(generateWeaponSize(level) * 0, generateCooldown(), generateWeaponDamage(level) * 2, generateWeaponcharge(), generateWeaponType());
        return weapon;
    }

    public static Hull generateHull(int level, boolean player){
        Hull hull = new Hull(generateHealth(level), generateArmor(level, player));
        return hull;
    }

    public static Engine generateEngine(int level){
        Engine engine = new Engine(generateEvade(level), generateSpeed(level), generateCooldown());
        return engine;
    }

    public static Weapon generateWeapon(int level){
        Weapon weapon = new Weapon(generateWeaponSize(level), generateCooldown(), generateWeaponDamage(level), generateWeaponType());
        return weapon;
    }
}
