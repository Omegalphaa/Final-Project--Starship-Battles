package game;

public class SuperEngine extends Engine{
    private int efficiency;
    
    SuperEngine(int dodge, int speed, int cooldown, int efficiency){
        super(dodge, speed, cooldown);
        this.efficiency = efficiency;
    }

    public int getEfficiency(){
        return this.efficiency;
    }
}
