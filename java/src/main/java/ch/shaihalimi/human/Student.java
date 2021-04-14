package ch.shaihalimi.human;

import ch.shaihalimi.gui.Simulation;

import java.awt.*;

public class Student extends Human{

    public Student(Simulation simulation) {
        super(simulation);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor((this.isHealthy()) ? Color.GREEN : Color.RED);
        g.fillOval(this.getX(),this.getY(),10,10);
        g.setColor(Color.WHITE);
        g.drawOval(this.getX(),this.getY(),10,10);
    }


}
