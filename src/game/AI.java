package game;

public class AI {
    
    public static void makeChoices(TurnSystem turn, int weaponsLength){
        makeWeaponChoices(turn, weaponsLength);
        makeSuperWeaponChoice(turn);
        makeEngineChoice(turn);
    }
        
        
        
        
    public static void makeWeaponChoices(TurnSystem turn, int weaponsLength){
        for(int i = 0; i < weaponsLength; i++){
            try {
                turn.addWeaponChoice(i, "Attack");
            } catch (OverUseException e) {
                // this is if it cant be added
                // nothing will happen
               // System.out.println(e.getMessage());
                
            }
        }
    }

    public static void makeSuperWeaponChoice(TurnSystem turn){
        try {
            turn.addSuperWeaponChoice("Charge");
        } catch (OverChargeException e) {
            turn.addSuperWeaponChoice("Attack");
            System.out.println(e.getMessage());

        } catch (OverUseException e) {
            // this is if it cant be added
            // nothing will happen
            System.out.println(e.getMessage());
        }
    }

    public static void makeEngineChoice(TurnSystem turn){
        String choice;
        int num = Gen.rand.nextInt(1);
        if (num == 1){
            choice = "Evade";
        }
        else{
            choice = "Move";
        }

        try {
            turn.addEngineChoice(choice);
        } catch (OverUseException e) {
            // this is if it cant be added
            // nothing will happen
        }
    }
    
    /* 
    public static void makeSuperEngineChoice(TurnSystem turn){
        String choice;
        int num = Gen.rand.nextInt(1);
        if (num == 1){
            choice = "Evade";
        }
        else{
            choice = "Move";
        }
        try {
            turn.addSuperEngineChoice(choice);;
        } catch (OverUseException e) {
            // this is if it cant be added
            // nothing will happen
        }
    }*/
}
