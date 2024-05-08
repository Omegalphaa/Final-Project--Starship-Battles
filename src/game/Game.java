package game;

@SuppressWarnings("unused")
public class Game{
    private static int level = 1; // level counter
    /*
    public static void main(String args[]){

        


        
        //System.out.println("THIS WORKS");
        //System.out.println(Gen.testString);

        

        
        Ship ship1 = new Ship(true, 1), ship2 = new Ship(false, 1);
        BossShip superShip1 = new BossShip(true, 1), superShip2 = new BossShip(false, 1);

        Hull hull1 = new Hull(100, 0), hull2 = new Hull(100, 0);
        SuperHull superHull1 = new SuperHull(100, 0, 5), superHull2 = new SuperHull(100, 0, 5);


        Weapon weapon1 = new Weapon(0, 1, 5, "Beam"), weapon2 = new Weapon(0,1, 5, "Beam");
        SuperWeapon sweapon1 = new SuperWeapon(0, 1, 30, 1, "Beam"), sweapon2 = new SuperWeapon(0, 1, 30, 1, "Beam");

        Engine engine1 = new Engine(0, 0, 1), engine2 = new Engine(0, 0, 1);
        SuperEngine superEngine1 = new SuperEngine(0, 0, 1, 0), superEngine2 = new SuperEngine(0, 0, 1, 0);
        
        ship1.setEngine(engine1);
        ship2.setEngine(engine2);
        superShip1.setSuperEngine(superEngine1);
        superShip2.setSuperEngine(superEngine2);
        //ship1.addWeapon(weapon1);
        //ship2.addWeapon(weapon2);
        //superShip1.addWeapon(weapon1);
        //superShip2.addWeapon(weapon2);
        ship1.setHull(hull1);
        ship2.setHull(hull2);
        superShip1.setSuperHull(superHull1);
        superShip2.setSuperHull(superHull2);
        ship1.setSuperWeapon(sweapon1);
        ship2.setSuperWeapon(sweapon2);
        superShip1.setSuperWeapon(sweapon1);
        superShip2.setSuperWeapon(sweapon2);

        TurnSystem tshipship1 = new TurnSystem(ship1, ship2), tshipship2 = new TurnSystem(ship2, ship1);

        TurnSystem tshipboss1 = new TurnSystem(ship1, superShip2), tshipboss2 = new TurnSystem(superShip2, ship1);

        TurnSystem tbossship1 = new TurnSystem(superShip1, ship2), tbossship2 = new TurnSystem(ship2, superShip1);

        TurnSystem tbossboss1 = new TurnSystem(superShip1, superShip2), tbossboss2 = new TurnSystem(superShip2, superShip1);
        


        boolean shipship = true, shipboss = false, bossship = false, bossboss = false;
        
        // pre test
        if(shipship){
            System.err.println("Pretest");
            System.err.println("player ship " + ship1.getCurrentHealth());
            System.err.println("enemy ship " + ship2.getCurrentHealth());
            System.err.println();
        }
        else if(shipboss){
            System.err.println("Pretest");
            System.err.println("player ship " + ship1.getCurrentHealth());
            System.err.println("enemy ship health " + superShip2.getCurrentHealth());
            System.err.println("enemy ship shield " + superShip2.getSuperHull().getCurrentShield());
            System.err.println();
            
        }
        else if(bossship){
            System.err.println("Pretest");
            System.err.println("player ship health " + superShip1.getCurrentHealth());
            System.err.println("player ship shield " + superShip1.getSuperHull().getCurrentShield());
            System.err.println("enemy ship " + ship2.getCurrentHealth());
            
            System.err.println();

        }
        else if(bossboss){
            System.err.println("Pretest");
            System.err.println("player ship health " + superShip1.getCurrentHealth());
            System.err.println("player ship shield " + superShip1.getSuperHull().getCurrentShield());
            System.err.println("enemy ship health " + superShip2.getCurrentHealth());
            System.err.println("enemy ship shield " + superShip2.getSuperHull().getCurrentShield());
            System.err.println();
        }

        ///*
        1 w shoot, sw charge hp 95
        2 w cd, sw shoot hp 65
        3 w shoot, sw cd hp 60
        4 w cd, sw charge hp 60

        1 sw charge hp 100
        2 sw shoot hp 70
        3 sw cd hp 70
        4 sw charge hp 70
        5 sw shoot hp 40



         * / //

        // combat testing
        for (int i = 1; i < 5 + 1; i++){
            if(shipship){
                AI.makeEngineChoice(tshipship1);
                AI.makeEngineChoice(tshipship2);
                AI.makeWeaponChoices(tshipship1, ship1.getWeapons().length);
                AI.makeWeaponChoices(tshipship2, ship2.getWeapons().length);
                AI.makeSuperWeaponChoice(tshipship1);
                AI.makeSuperWeaponChoice(tshipship2);

                tshipship1.advanceEngineTurn();
                tshipship2.advanceEngineTurn();
                tshipship1.advanceSuperWeaponTurn();
                tshipship2.advanceSuperWeaponTurn();
                tshipship1.advanceWeaponTurn();
                tshipship2.advanceWeaponTurn();

                System.err.println("ship vs ship");
                System.err.println("Combat " + i + " Results");
                System.err.println("player ship " + ship1.getCurrentHealth());
                System.err.println("enemy ship " + ship2.getCurrentHealth());
                System.err.println();
                
            }
            else if(shipboss){
                AI.makeEngineChoice(tshipboss1);
                AI.makeEngineChoice(tshipboss2);
                AI.makeWeaponChoices(tshipboss1, ship1.getWeapons().length);
                AI.makeWeaponChoices(tshipboss2, superShip1.getWeapons().length);
                AI.makeSuperWeaponChoice(tshipboss1);
                AI.makeSuperWeaponChoice(tshipboss2);

                tshipboss1.advanceEngineTurn();
                tshipboss2.advanceEngineTurn();
                tshipboss1.advanceSuperWeaponTurn();
                tshipboss2.advanceSuperWeaponTurn();
                tshipboss1.advanceWeaponTurn();
                tshipboss2.advanceWeaponTurn();
                
                System.err.println("ship vs boss");
                System.err.println("Combat " + i + " Results");
                System.err.println("player ship " + ship1.getCurrentHealth());
                System.err.println("enemy ship health " + superShip2.getCurrentHealth());
                System.err.println("enemy ship shield " + superShip2.getSuperHull().getCurrentShield());
                System.err.println();
                
            }
            else if(bossship){
                AI.makeEngineChoice(tbossship1);
                AI.makeEngineChoice(tbossship2);
                AI.makeWeaponChoices(tbossship1, ship1.getWeapons().length);
                AI.makeWeaponChoices(tbossship2, ship2.getWeapons().length);
                AI.makeSuperWeaponChoice(tbossship1);
                AI.makeSuperWeaponChoice(tbossship2);
                tbossship1.advanceEngineTurn();
                tbossship2.advanceEngineTurn();
                tbossship1.advanceSuperWeaponTurn();
                tbossship2.advanceSuperWeaponTurn();
                tbossship1.advanceWeaponTurn();
                tbossship2.advanceWeaponTurn();

                System.err.println("boss vs ship");
                System.err.println("Combat " + i + " Results");
                System.err.println("player ship health " + superShip1.getCurrentHealth());
                System.err.println("player ship shield " + superShip1.getSuperHull().getCurrentShield());
                System.err.println("enemy ship " + ship2.getCurrentHealth());
                
                System.err.println();

            }
            else if(bossboss){
                AI.makeEngineChoice(tbossboss1);
                AI.makeEngineChoice(tbossboss2);
                AI.makeWeaponChoices(tbossboss1, ship1.getWeapons().length);
                AI.makeWeaponChoices(tbossboss2, ship2.getWeapons().length);
                AI.makeSuperWeaponChoice(tbossboss1);
                AI.makeSuperWeaponChoice(tbossboss2);
                tbossboss1.advanceEngineTurn();
                tbossboss2.advanceEngineTurn();
                tbossboss1.advanceSuperWeaponTurn();
                tbossboss2.advanceSuperWeaponTurn();
                tbossboss1.advanceWeaponTurn();
                tbossboss2.advanceWeaponTurn();

                System.err.println("boss vs boss");
                System.err.println("Combat " + i + " Results");
                System.err.println("player ship health " + superShip1.getCurrentHealth());
                System.err.println("player ship shield " + superShip1.getSuperHull().getCurrentShield());
                System.err.println("enemy ship health " + superShip2.getCurrentHealth());
                System.err.println("enemy ship shield " + superShip2.getSuperHull().getCurrentShield());
                System.err.println();
            }
            
        }
        
        
    }   // boss every 5 levels*/
    /*
     * // phase 1 test
        AI.makeEngineChoice(player, ship1.getBoss());
        AI.makeEngineChoice(enemy, superShip1.getBoss());
        AI.makeWeaponChoices(player, ship1.getWeapons().length);
        AI.makeWeaponChoices(enemy, superShip1.getWeapons().length);
        //AI.makeSuperWeaponChoice(player);
        //AI.makeSuperWeaponChoice(enemy);

        player.advanceEngineTurn();
        enemy.advanceEngineTurn();
        //player.advanceSuperWeaponTurn();
        //enemy.advanceSuperWeaponTurn();
        player.advanceWeaponTurn();
        enemy.advanceWeaponTurn();

        System.err.println("Combat 1 Results");
        System.err.println("player ship " + ship1.getCurrentHealth());
        System.err.println("enemy ship health " + superShip1.getCurrentHealth());
        System.err.println("enemy ship shield " + superShip1.getSuperHull().getCurrentShield());


        System.err.println("\n");
        // phase 2 test

        AI.makeEngineChoice(player, ship1.getBoss());
        AI.makeEngineChoice(enemy, superShip1.getBoss());
        AI.makeWeaponChoices(player, ship1.getWeapons().length);
        AI.makeWeaponChoices(enemy, superShip1.getWeapons().length);
        //AI.makeSuperWeaponChoice(player);
        //AI.makeSuperWeaponChoice(enemy);

        player.advanceEngineTurn();
        enemy.advanceEngineTurn();

        //player.advanceSuperWeaponTurn();
        //enemy.advanceSuperWeaponTurn();
        player.advanceWeaponTurn();
        enemy.advanceWeaponTurn();

        System.err.println("Combat 2 Results");
        System.err.println("player ship " + ship1.getCurrentHealth());
        System.err.println("enemy ship " + superShip1.getCurrentHealth());
        System.err.println("enemy ship shield " + superShip1.getSuperHull().getCurrentShield());

        System.err.println("\n");
        // phase 3 test

        AI.makeEngineChoice(player, ship1.getBoss());
        AI.makeEngineChoice(enemy, superShip1.getBoss());
        AI.makeWeaponChoices(player, ship1.getWeapons().length);
        AI.makeWeaponChoices(enemy, superShip1.getWeapons().length);
        //AI.makeSuperWeaponChoice(player);
        //AI.makeSuperWeaponChoice(enemy);

        player.advanceEngineTurn();
        enemy.advanceEngineTurn();

        //player.advanceSuperWeaponTurn();
        //enemy.advanceSuperWeaponTurn();
        

        player.advanceWeaponTurn();
        enemy.advanceWeaponTurn();

        System.err.println("Combat 3 Results");
        System.err.println("player ship " + ship1.getCurrentHealth());
        System.err.println("enemy ship " + superShip1.getCurrentHealth());
        System.err.println("enemy ship shield " + superShip1.getSuperHull().getCurrentShield());

        
        System.err.println("\n");
        // phase 4 test

        AI.makeEngineChoice(player, ship1.getBoss());
        AI.makeEngineChoice(enemy, superShip1.getBoss());
        AI.makeWeaponChoices(player, ship1.getWeapons().length);
        AI.makeWeaponChoices(enemy, superShip1.getWeapons().length);
        //AI.makeSuperWeaponChoice(player);
        //AI.makeSuperWeaponChoice(enemy);

        player.advanceEngineTurn();
        enemy.advanceEngineTurn();

        //player.advanceSuperWeaponTurn();
        //enemy.advanceSuperWeaponTurn();
        player.advanceWeaponTurn();
        enemy.advanceWeaponTurn();

        System.err.println("Combat 4 Results");
        System.err.println("player ship " + ship1.getCurrentHealth());
        System.err.println("enemy ship " + superShip1.getCurrentHealth());
        System.err.println("enemy ship shield " + superShip1.getSuperHull().getCurrentShield());


     */
    /* 
    private static int nextTurn(int currentTurn){
        // call functions that tick up counters for the things that need it.
        // have a way to remember what has taken its turn already.

    }*/
}