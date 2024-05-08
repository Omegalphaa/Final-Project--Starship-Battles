package game;

public class SuperHull extends Hull{
    private int maxShield, currentShield, cooldownTime, currentCooldown;

    SuperHull(int health, int armor, int shield){ 
        super(health, armor);
        this.setMaxShield(shield);
        this.setCurrentShield(shield);
        this.cooldownTime = 1; // TODO change after testing 
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

    public void setMaxShield(int shield){
        this.maxShield = shield;
    }

    public int getMaxShield(){
        return this.maxShield;
    }

    public void removeCurrentShield(int damage){
        this.currentShield -= damage;
        if (this.getCurrentShield() < 0){
            this.setCurrentShield(0);
        }
    }

    public void setCurrentShield(int shield){
        this.currentShield = shield;
    }

    public int getCurrentShield(){
        return this.currentShield;
    }

    @Override
    public void takeDamage(int damage){
        int postShieldDamage, mitigatedDamage; // simple armor reduction calculation

        postShieldDamage = damage - this.getCurrentShield();
        //System.err.println("damage " + damage);
        //System.err.println("current shield " + this.getCurrentShield());
        //System.err.println("post shield damage " + postShieldDamage);
        
        if (postShieldDamage < 0){ // this happens when the shield doesn't get destroyed
            this.removeCurrentShield(damage);
            
        }
        else if(postShieldDamage == 0){ // this happens when the shield matches the damage
            this.removeCurrentShield(damage);
            this.setCurrentCooldown(this.getCooldownTime());
        }
        else{
            this.removeCurrentShield(this.getMaxShield());
            mitigatedDamage = (int)(damage *(1-(this.getArmor() *.01)));
            this.removeCurrentHealth(mitigatedDamage);
        }
    }

    public void nextTurn(){
        if (this.currentCooldown == 0){
            this.setCurrentShield(this.getMaxShield());
        }
        
        this.currentCooldown -= 1;
        if (this.currentCooldown < 0){
            this.currentCooldown = 0;
            //this.setCurrentShield(this.getMaxShield());
        }
    }
}
