package ch.shaihalimi.human;

import ch.shaihalimi.gui.Simulation;
import java.awt.*;

public abstract class Human  {
    private EntityLocation location;
    private Simulation simulation;
    private boolean healthy;
    private int time;
    private Direction direction;

    public Human(Simulation simulation){
        this.simulation = simulation;
        this.healthy = true;
        this.location = new EntityLocation(this.simulation.getInstance().randomBetween(1000,0),this.simulation.getInstance().randomBetween(1000,0));
    }

    public void test(){
        System.out.println(new EntityLocation(2,2).equals(new EntityLocation(4,4)));
    }

    public boolean isHealthy() {
        return this.healthy;
    }

    public abstract void draw(Graphics2D g);

    public void addLocation(int x,int y){
        this.move(this.getX()+x,this.getY()+y);
    }

    //TODO: https://natureofcode.com/book/introduction/
    public void move(int x, int y){
        if (x < 0) x=1000;
        if (y < 0) y=1000;
        if (x > 1000) x=0;
        if (y > 1000) y=0;
        this.location.move(x,y);
        for (Human human : this.simulation.getHumans()) {
            if (this.isHealthy() && human.isHealthy() || this == human) continue;
            if (!human.isHealthy() && !this.isHealthy()) continue;
            if(this.getEntityLocation().equals(human.getEntityLocation())) {
                if (this.isHealthy()) this.setHealthy(false);
                else human.setHealthy(false);
            }
        }
    }

    public void move(Direction direction,int time){
        if (this.time==0) {
            this.move(this.getX() + direction.getX(), this.getY() + direction.getY());
            this.time=time;
            this.direction = direction;
            return;
        }
        this.time--;
        this.move(this.getX() + this.direction.getX(), this.getY() + this.direction.getY());

    }


    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
        if (!this.healthy) {
            this.simulation.newInfection(this);
            System.out.println(true);
        }
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

    public void addLocation(int x,int y){
        this.x+=x;
        this.y+=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(EntityLocation location){
        return location.getX() >= this.x &&
                location.getX() <= this.x+10 &&
                location.getY() >= this.y &&
                location.getY() <= this.y+10;
    }
}
