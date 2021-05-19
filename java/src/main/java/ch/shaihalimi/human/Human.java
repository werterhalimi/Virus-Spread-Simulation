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
        int x = this.simulation.getInstance().randomBetween(1920,0);
        int y = this.simulation.getInstance().randomBetween(1080,0);
        while (this.simulation.getField().getRGB(x,y) != -4080709){
            x = this.simulation.getInstance().randomBetween(1920,0);
            y = this.simulation.getInstance().randomBetween(1080,0);
        }
        this.location = new EntityLocation(x,y);
    }


    public boolean isHealthy() {
        return this.healthy;
    }

    public abstract void draw(Graphics2D g);

    public void addLocation(int x,int y){
        this.move(this.getX()+x,this.getY()+y);
    }

    //TODO: https://natureofcode.com/book/introduction/
    private void move(int x, int y){
        if (x < 0 || y < 0 || x > 1920 || y > 1080) this.direction=this.direction.getOpposite();
        if (this.direction != null && !this.simulation.isWalkable(x,y)) this.direction=this.direction.getOpposite();
        if (this.simulation.getField().getRGB(x,y)==-9433843)x=(x>1000)?x-953:x+953;
        this.location.move(x,y);
        for (Human human : this.simulation.getHumans()) {
            if(this.getEntityLocation().equals(human.getEntityLocation())) {
//                this.setDirection(human.getDirection());
//                human.setDirection(this.getDirection());
                if (this.isHealthy() && human.isHealthy() || this == human) continue;
                if (!human.isHealthy() && !this.isHealthy()) continue;
                if (this.isHealthy()) this.setHealthy(false);
                else human.setHealthy(false);

            }
        }
    }
    public void setDirection(Direction direction){
        this.direction=direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void move(Direction direction, int time){
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
