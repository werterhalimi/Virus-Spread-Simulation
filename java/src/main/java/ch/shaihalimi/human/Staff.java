package ch.shaihalimi.human;

import ch.shaihalimi.gui.Simulation;

import java.awt.*;

public class Staff extends Human {

    public Staff(Simulation simulation) {
        super(simulation);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor((this.isHealthy()) ? Color.GREEN : (this.isDead())?Color.BLACK: Color.RED);
        g.fillRect(this.getX(),this.getY(),10,10);
        g.setColor(Color.WHITE);
        g.drawRect(this.getX(),this.getY(),10,10);
    }
}
