package ch.shaihalimi.human;

import ch.shaihalimi.gui.Simulation;
import java.awt.*;

public abstract class Human  {
    private EntityLocation location;
    private Simulation simulation;
    private int age;
    private boolean healthy,dead;
    private int time;
    private Direction direction;
    //TODO fleche de direction
    public Human(Simulation simulation){
        this.simulation = simulation;
        this.healthy = true;
        this.dead = false;
        if (this instanceof Student)
            this.age = this.simulation.getInstance().randomBetween(19,15);
        else
            this.age = this.simulation.getInstance().randomBetween(70,05);
        int x = this.simulation.getInstance().randomBetween(1910,141);
        int y = this.simulation.getInstance().randomBetween(865,79);
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
        if (x < 0 || y < 0 || x > 1918 || y > 1078){
            this.direction=this.direction.getOpposite();
            return;
        }
        if (this.direction != null && !this.simulation.isWalkable(x,y)){
            this.direction=this.direction.getOpposite();
            return;
        }
        if (this.simulation.getField().getRGB(x,y)==-9433843)x=(x>1000)?x-953:x+953;
        this.location.move(x,y);
        for (Human human : this.simulation.getHumans()) {
            if(human.isDead()) continue;
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
            //SOURCE: https://www.trt.net.tr/francais/photogallery/infographie/taux-de-mortalite-du-coronavirus-selon-l-age
            float chance = this.simulation.getInstance().getRandom().nextFloat();
            if(this.age >= 70 && chance <= 0.08) {
                this.die();
                return;
            }
            if(this.age >= 60 && chance <= 0.036) {
                this.die();
                return;
            }
            if(this.age >= 50 && chance <= 0.013) {
                this.die();
                return;
            }
            if(this.age >= 40 && chance <= 0.04) {
                this.die();
                return;
            }
            if(this.age >= 10 && chance <= 0.02) {
                this.die();
                return;
            }
        }
    }

    public boolean isDead() {

        return dead;
    }

    public void die(){
        System.out.println("died");
        this.dead=true;
        this.simulation.kill(this);
        return;
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
