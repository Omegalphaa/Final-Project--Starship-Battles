package game;

public class Engine {
    private int evade; // dodge stat for physical projectiles
    private int speed;  // dodge stat for missles
    private int cooldownTime;
    private int currentCooldown;
    private boolean armed, evadeState, moveState;
    // 0 - 100 percentage of dodge


    Engine(int evade, int speed, int cooldownTime){
        this.setSpeed(speed);
        this.setEvade(evade);
        this.setCooldownTime(cooldownTime);
        this.setCurrentCooldown(0);
        this.setArmed(true);
        this.setEvadeState(false);
        this.setMoveState(false);
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public int getSpeed(){
        return this.speed;
    }

    public void setEvade(int evadeChance){
        this.evade = evadeChance;
    }

    public int getEvade(){
        return this.evade;
    }

    public void setEvadeState(boolean state){
        this.evadeState = state;
    }

    public boolean getEvadeState(){
        return this.evadeState;
    }

    public void setMoveState(boolean state){
        this.moveState = state;
    }

    public boolean getMoveState(){
        return this.moveState;
    }

    public void setCooldownTime(int cooldownTime){
        this.cooldownTime = cooldownTime;
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

    public void setArmed(boolean arm){
        this.armed = arm;
    }

    public boolean getArmed(){
        return this.armed;
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
