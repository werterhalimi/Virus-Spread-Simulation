package ch.shaihalimi.human;

import ch.shaihalimi.gui.Simulation;
import java.awt.*;

public abstract class Human  {
    private EntityLocation location;
    private Simulation simulation;
    private boolean healthy;

    public Human(Simulation simulation){
        this.simulation = simulation;
        this.healthy = true;
        this.location = new EntityLocation(this.simulation.getInstance().randomBetween(1000,0),this.simulation.getInstance().randomBetween(1000,0));
    }

    public boolean isHealthy() {
        return this.healthy;
    }

    public abstract void draw(Graphics2D g);



    //TODO: https://natureofcode.com/book/introduction/
    public void move(int x, int y){
        this.location.move(x,y);
        for (Human human : this.simulation.getHumans()) {
            if(this.getEntityLocation().equals(human.getEntityLocation()))
                this.setHealthy(human.isHealthy());
        }
    }

    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
    }

    public EntityLocation getEntityLocation() {
        return location;
    }

    public int getX() {
        return this.location.getX();
    }

    public int getY() {
        return this.location.getY();
    }
}
class EntityLocation{
    private int x,y;

    public EntityLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void move(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(EntityLocation location){
        return location.getX() <= this.x &&
                x <= location.getX()+10 &&
                location.getY() <= this.y &&
                y <= location.getY()+10;
    }
}
