package ch.shaihalimi;


import ch.shaihalimi.gui.Configuration;
import ch.shaihalimi.gui.Simulation;
import ch.shaihalimi.human.Direction;

import java.util.Random;

public class Main {
    private static Main instance;
    public static void main(String[] args) {

        instance = new Main();
    }

    private Configuration config;
    private Simulation simulation;
    private Random random;

    public Main() {
        this.config = new Configuration(this);
        this.random=new Random();
    }

    public void start() {
        this.simulation = new Simulation(this,this.config);
    }

    public int randomBetween(int max,int min){
        return this.getRandom().nextInt(max-min) + min;
    }

    public Random getRandom() {
        return random;
    }
}
