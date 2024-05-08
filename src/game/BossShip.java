package game;

public class BossShip extends Ship{

    private SuperHull superHull;
    private SuperEngine superEngine;

    BossShip(boolean player, int level){
        super(player, level);
        this.setHull(null);
        this.setEngine(null);

        SuperHull superHull = Gen.generateSuperHull(level, player);
        SuperEngine superEngine = Gen.generateSuperEngine(level);
        this.setSuperHull(superHull);
        this.setSuperEngine(superEngine);
        

    }

    public void setSuperHull(SuperHull hull){
        this.superHull = hull;
    }

    public SuperHull getSuperHull(){
        return this.superHull;
    }
    
    public void setSuperEngine(SuperEngine engine){
        this.superEngine = engine;
    }

    public SuperEngine getSuperEngine(){
        return this.superEngine;
    }

    // methods for the ship's Hull
    @Override
    public void takeDamage(int damage, String type){
        // calculate the damage taken by using the total damage and armor
        if(!this.evade(type) || ! this.move(type)){ // check if the attack has been dodged
            this.superHull.takeDamage(damage);
        }
        

    }

    @Override
    public void repair(int value){
        this.superHull.addCurrentHealth(value);
    }

    @Override
    public int getCurrentHealth(){
        return this.superHull.getCurrentHealth();
    }

    // methods for combat

    @Override
    public boolean evade(String type){ // return true if dodged
        int randNum = Gen.rand.nextInt(100) + 1;

        if (type.equals("Projectile")){
            if (randNum <= this.getSuperEngine().getEvade()){
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

    @Override
    public boolean move(String type){ // return true if dodged
        int randNum = Gen.rand.nextInt(100) + 1;
        if (type.equals("Beam")){
            if (randNum <= this.getSuperEngine().getSpeed()){
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

    @Override
    public boolean isLiving(){
        return this.getSuperHull().isLiving();
    }
}