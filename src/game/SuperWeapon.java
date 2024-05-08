package game;

public class SuperWeapon extends Weapon{
    private int maxCharge; // charge time in turns
    private int currentCharge; 
    private boolean chargeReady;

    SuperWeapon(int size, int cooldown, int damage, int charge, String type){ // add the inputs;
        super(size, cooldown, damage, type);
        this.maxCharge = charge;
        this.currentCharge = 0;
        this.setArmed(false);
        this.setChargeReady(true);
        this.setSize(0);
    }
    
    public void setChargeReady(boolean state){
        this.chargeReady = state;
    }

    public boolean getChargeReady(){
        return this.chargeReady;
    }

    public int getMaxCharge(){
        return this.maxCharge;
    }

    public int getCurrentCharge(){
        return this.currentCharge;
    }
    
    public void setCurrentCharge(int charge){
        this.currentCharge = charge;
    }

    public void charge(){ 
        this.setCurrentCharge(this.getCurrentCharge() + 1);
        if (this.getCurrentCharge() == this.getMaxCharge()){
            this.setArmed(true);
            this.setChargeReady(false);
        }

    }

    @Override
    public void use(){
        this.setCurrentCooldown(this.getCooldownTime());
        this.setCurrentCharge(0);
        this.setArmed(false);
        this.setChargeReady(false);
    }

    @Override
    public void nextTurn(){
        this.setCurrentCooldown(this.getCurrentCooldown() - 1);
        if (this.getCurrentCooldown() <= 0){
            this.setCurrentCooldown(0);
            this.setArmed(true);
            this.setChargeReady(true);
        }
    }
}