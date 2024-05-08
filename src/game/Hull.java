package game;

public class Hull {
    private int maxHealth;
    private int currentHealth;
    private int armor; // 100 armor is 100% damage reduction

    Hull(int health, int armor){
        this.setMaxHealth(health);
        this.setArmor(armor);
    }

    public void setMaxHealth(int health){
        this.maxHealth = health;
        this.currentHealth = health;
    }

    public int getMaxHealth(){
        return this.maxHealth;
    }

    public void setArmor(int value){
        this.armor = value;
    }

    public int getArmor(){
        return this.armor;
    }

    public void addCurrentHealth(int value){
        this.currentHealth += value;
        if (this.currentHealth > this.maxHealth){
            this.currentHealth = this.maxHealth;
        }
    }

    public void removeCurrentHealth(int damage){ // returns true if still alive
        this.currentHealth -= damage;
    }

    public int getCurrentHealth(){
        return this.currentHealth;
    }

    public void takeDamage(int damage){
        int mitigatedDamage = (int)(damage *(1-(this.getArmor() *.01))); // simple armor reduction calculation

        this.removeCurrentHealth(mitigatedDamage);
    }

    public boolean isLiving(){
        if (this.getCurrentHealth() <= 0){
            return false;
        }
        else{
            return true;
        }
    }
}