package game;

public class Ship {
    private boolean player;
    private SuperWeapon superweapon;
    private Weapon weapons[] = new Weapon[0];
    private int weaponStorage, currentWeaponStorage;
    

    private Hull hull;
    private Engine engine;
    

    Ship(boolean player, int level){
        if (player){
            Hull startingHull = null;
            Engine startingEngine = null;
            this.setHull(startingHull);
            this.setEngine(startingEngine);// override for BossShip
            this.setWeaponStorage(20);
        }
        else{
            for (int i = 0; i < 10; i++){ // this is becuase the variable isnt getting assigned when i call Super() but it works this way
                this.increaseWeaponStorage(50);
            }
            Hull startingHull = Gen.generateHull(level, player);
            Engine startingEngine = Gen.generateEngine(level);
            if (level / 5 == 0){
                this.addWeapon(Gen.generateWeapon(level));
                this.addWeapon(Gen.generateWeapon(level));
            }
            for (int i = 0; i < (level / 5) * 2; i++){
                this.addWeapon(Gen.generateWeapon(level));
            }
            this.setHull(startingHull);
            this.setEngine(startingEngine);// override for BossShip
        }
        this.player = player;
        
        this.currentWeaponStorage = 0;
    }

    // sets for the ship parts and misc
    public boolean getPlayer(){
        return this.player;
    }

    public void setHull(Hull hull){
        this.hull = hull;
    }

    public Hull getHull(){
        return this.hull;
    }

    public void setEngine(Engine engine){
        this.engine = engine;
    }

    public Engine getEngine(){
        return this.engine;
    }

    public void setSuperWeapon(SuperWeapon weapon){
        this.superweapon = weapon;
    }

    public SuperWeapon getSuperWeapon(){
        return this.superweapon;
    }

    public void setWeaponStorage(int storage){
         this.weaponStorage = storage;
    }
    
    public void increaseWeaponStorage(int amount){
        this.weaponStorage += amount;
    }

    public int getWeaponStorage(){
        return this.weaponStorage;
    }

    public int getCurrentWeaponStorage(){
        return this.currentWeaponStorage;
    }
    
    public void setCurrentWeaponStorage(int value){
        this.currentWeaponStorage = value;
    }

    // methods for ship's weapons

    public void addWeapon(Weapon weapon){
        // add to the array weapons
        // if the weapon can't be added to the array, return false otherwise return true
        
        int totalSize = weapon.getSize() + this.getCurrentWeaponStorage();
        int maxWeapons = 30;
    
        
        if (this.getWeapons().length == maxWeapons){
            throw new WeaponStorageException("You can hold no more weapons");
        }
        if (totalSize > this.getWeaponStorage()){
            throw new WeaponStorageException("Weapon Size Capacity Full");
        }

        int wLength = this.weapons.length;
        Weapon temparray[] = new Weapon[wLength + 1];
        for (int i = 0; i < wLength; i++){
            temparray[i] = this.weapons[i];
        }  
        this.setCurrentWeaponStorage(totalSize);
        temparray[wLength] = weapon;
        this.weapons = temparray;
    }

    public void removeWeapon(int index){

        int wLength = this.weapons.length;
        Weapon temparray[] = new Weapon[wLength - 1];
        for (int i = 0, j = 0; i < wLength; i++){
            if (i != index){
                temparray[j] = this.weapons[i];
                j++;
            }
        }
        this.weapons = temparray;
    }

    public Weapon[] getWeapons(){
        return this.weapons;
    }

    public Weapon getWeapon(int index){
        return this.weapons[index];
    }

    // methods for the ship's Hull

    public void takeDamage(int damage, String type){
        if(!this.evade(type) || ! this.move(type)){ // check if the attack has been dodged
            this.hull.takeDamage(damage);
        }
        
    }

    public void repair(int value){
        this.hull.addCurrentHealth(value);
    }

    public int getCurrentHealth(){
        return this.hull.getCurrentHealth();
    }

    // combat related things

    public void weaponAttack(Ship defender, Weapon weapon){
        // find all of the weapons that are ready to attack on the attacking ship 
        defender.takeDamage(weapon.getDamage(), weapon.getType());
    }

    public void bossWeaponAttack(BossShip defender, Weapon weapon){
        defender.takeDamage(weapon.getDamage(), weapon.getType());
    }

    public void superWeaponAttack(Ship defender, SuperWeapon weapon){
        defender.takeDamage(weapon.getDamage(), weapon.getType());
    }

    public void bossSuperWeaponAttack(BossShip defender, Weapon weapon){
        defender.takeDamage(weapon.getDamage(), weapon.getType());
    }

    public boolean evade(String type){ // return true if dodged
        int randNum = Gen.rand.nextInt(99) + 1;
        if(!this.getEngine().getEvadeState()){
            return false;
        }
        if (type.equals("Projectile")){
            if (randNum >= this.getEngine().getEvade()){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    public boolean move(String type){ // return true if dodged
        int randNum = Gen.rand.nextInt(99) + 1;
        if(!this.getEngine().getEvadeState()){
            return false;
        }
        if (type.equals("Beam")){
            if (randNum >= this.getEngine().getSpeed()){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    } 

    public boolean isLiving(){
        return this.getHull().isLiving();
    }
}