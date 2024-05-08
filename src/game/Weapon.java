package game;

public class Weapon {
    private int damage;
    private int size;
    private int cooldownTime; // cooldown time in turns
    private int currentCooldown;
    private String type; // attack type, affects how to dodge
    private boolean armed; // weapons readyness.

    Weapon(int size, int cooldown, int damage, String type){
        this.setSize(size);
        this.setCooldownTime(cooldown);
        this.setCurrentCooldown(0);
        this.setDamage(damage);
        this.setArmed(true);
        this.setType(type);
    }

    public void setDamage(int damage){
        this.damage = damage;
    }

    public int getDamage(){
        return this.damage;
    }

    public void setArmed(boolean armed){
        this.armed = armed;
    }

    public boolean getArmed(){
        return this.armed;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
    
    public void setCooldownTime(int cooldown){
        this.cooldownTime = cooldown;
    }

    public int getCooldownTime(){
        return this.cooldownTime;
    }

    public void setCurrentCooldown(int cooldown){
        this.currentCooldown = cooldown;
    }

    public int getCurrentCooldown(){
        return this.currentCooldown;
    }

    public void setSize(int size){
        this.size = size;
    }

    public int getSize(){
        return this.size;
    }

    public void use(){
        this.setCurrentCooldown(this.getCooldownTime());
        this.setArmed(false);
    }

    public void nextTurn(){
        this.currentCooldown -= 1;
        if (this.currentCooldown <= 0){
            this.currentCooldown = 0;
            this.setArmed(true);
        }
    }
}
