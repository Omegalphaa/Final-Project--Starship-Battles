package game;

public class TurnSystem {
    private String weaponChoice[], superWeaponChoice, engineChoice;
    private Ship mainShip, defendingShip;
    private int damageDealt = 0;
    private BossShip bossMainShip;
    private boolean mainShipBoss;
    private Weapon weapons[];
    private SuperWeapon superWeapon;

    TurnSystem(Ship mainShip, Ship defendingShip){
        if(mainShip.getEngine() == null){
            this.mainShipBoss = true;
            this.bossMainShip = (BossShip)mainShip;
        }
        else{
            this.mainShipBoss = false;
            this.mainShip = mainShip;
        }
        this.defendingShip = defendingShip;
        this.weapons = mainShip.getWeapons();
        this.superWeapon = mainShip.getSuperWeapon();
        this.weaponChoice = new String[weapons.length];
    }
    
    public void resetAllChoices(){
        for(int i = 0; i < this.weaponChoice.length; i++){
            this.weaponChoice[i] = null;
        }
        this.superWeaponChoice = null;
        this.engineChoice = null;
        this.damageDealt = 0;
    }
    
    
    public int getDamageDealt(){
        return this.damageDealt;
    }
    public void addWeaponChoice(int index, String choice){
        if (!this.weapons[index].getArmed()){
            this.weaponChoice[index] = null;
            throw new OverUseException("Weapon Currenly on cooldown");
        }
        else{
            this.weaponChoice[index] = choice;
        }
    }
    public String getWeaponChoice(int index){
        if(this.weaponChoice[index] == null){
            return "None";
        }
        return this.weaponChoice[index];
    }
    

    public void addSuperWeaponChoice(String choice){
        if(this.superWeapon == null){
            return;
        }
        if(this.superWeapon.getArmed() && choice == "Attack"){
            this.superWeaponChoice = choice;
        }
        else if(this.superWeapon.getChargeReady() && choice == "Charge"){
            if(this.superWeapon.getCurrentCharge() == this.superWeapon.getMaxCharge()){
                throw new OverChargeException("Super Weapon currently at max charge");
            }
            this.superWeaponChoice = choice;
        }
        else{
            this.superWeaponChoice = null;
            throw new OverUseException("SuperWeapon currenly on cooldown");
        }

        /* 
        if (!this.superWeapon.getArmed()){
            this.superWeaponChoice = null;
            throw new OverUseException("SuperWeapon currenly on cooldown");
        }
        else if(this.superWeapon.getCurrentCharge() == this.superWeapon.getMaxCharge() && choice == "Charge"){
            throw new OverChargeException("Super Weapon currently at max charge");
        }
        else{
            this.superWeaponChoice = choice;
        }*/
    }

    public String getSuperWeaponChoice(){
        if(this.superWeaponChoice == null){
            return "None";
        }
        return this.superWeaponChoice;
    }
    
    public void addEngineChoice(String choice){
        if(mainShipBoss){
            if(!this.bossMainShip.getSuperEngine().getArmed()){
                this.engineChoice = null;
                throw new OverUseException("Engine Currently on cooldown");
            }
            else{
                this.engineChoice = choice;
            }

        }
        else{
            if(!this.mainShip.getEngine().getArmed()){
                this.engineChoice = null;
                throw new OverUseException("Engine Currently on cooldown");
            }
            else{
                this.engineChoice = choice;
            }
        }

        
    }
    
    public String getEngineChoice(){
        if(this.engineChoice == null){
            return "None";
        }
        return this.engineChoice;
    }

    @SuppressWarnings("unused") // to remove a unused variable warning for for loop.
    public void advanceWeaponTurn(){
        for(int i = 0; i < this.weaponChoice.length; i++){
            if (this.weaponChoice[i] == null){
                this.weaponChoice[i] = "Pass";
            }
            switch (this.weaponChoice[i]) {
                case "Attack":
                    if(!this.mainShipBoss){
                        this.mainShip.weaponAttack(this.defendingShip, this.weapons[i]);
                        this.weapons[i].use();
                    }
                    else{
                        this.bossMainShip.weaponAttack(this.defendingShip, this.weapons[i]);
                        this.weapons[i].use();
                    }
                    this.damageDealt += this.weapons[i].getDamage();
                    break;
                case "Pass":
                    this.weapons[i].nextTurn();
                    break;
            }
        
            
        }
    }

    public void advanceSuperWeaponTurn(){
        if(this.superWeaponChoice == null){
            this.superWeaponChoice = "Pass";
        }
        if(this.superWeapon == null){
            return;
        }
        if (this.mainShip != null){ // if the main ship is a normal ship
            switch (this.superWeaponChoice) {
                case "Attack":
                    this.mainShip.superWeaponAttack(this.defendingShip, this.superWeapon);
                    this.superWeapon.use();
                    this.damageDealt += this.superWeapon.getDamage();
                    break;
                case "Charge":
                    this.superWeapon.charge();
                case "Pass":
                    this.superWeapon.nextTurn();
                    break;
            }
        }
        else{
            switch (this.superWeaponChoice) {
                case "Attack":
                    this.bossMainShip.superWeaponAttack(this.defendingShip, this.superWeapon);
                    this.superWeapon.use();
                    this.damageDealt += this.superWeapon.getDamage();
                    break;
                case "Charge":
                    this.superWeapon.charge();
                case "Pass":
                    this.superWeapon.nextTurn();
                    break;
            }
        }
        
    }

    public void advanceEngineTurn(){
        if(this.engineChoice == null){
            this.engineChoice = "Pass";
        }
        if(this.mainShipBoss){
            switch (this.engineChoice) {
                case "Evade":
                    this.bossMainShip.getSuperEngine().setEvadeState(true);
                    this.bossMainShip.getSuperEngine().use();
                    break;
                case "Move":
                    this.bossMainShip.getSuperEngine().setMoveState(true);
                    this.bossMainShip.getSuperEngine().use();
                    break;
                case "Pass":
                    this.bossMainShip.getSuperEngine().nextTurn(); // TODO make the engine state reset after nextTurn
                    break;
            }
            if(this.bossMainShip.getSuperHull().getCurrentShield() <= 0){
                // checking to see if the shield has been destroyed
                this.bossMainShip.getSuperHull().nextTurn();
            }
        }
        else{
            switch (this.engineChoice) {
                case "Evade":
                    this.mainShip.getEngine().setEvadeState(true);
                    this.mainShip.getEngine().use();
                    break;
                case "Move":
                    this.mainShip.getEngine().use();
                    break;
                case "Pass":
                    this.mainShip.getEngine().nextTurn();
                    break;
            }
        }
            

            

    }

    public boolean levelEnd(){ // return true if the defending ship has been destroyed  
        if(this.defendingShip.isLiving()){
            return false;
        }
        else{
            return true;
        }
    }
}
