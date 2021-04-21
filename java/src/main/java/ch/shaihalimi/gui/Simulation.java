package ch.shaihalimi.gui;

import ch.shaihalimi.Main;
import ch.shaihalimi.human.Direction;
import ch.shaihalimi.human.Human;
import ch.shaihalimi.human.Staff;
import ch.shaihalimi.human.Student;

import javax.swing.*;
import java.awt.*;

public class Simulation extends JFrame {

    private Configuration config;
    private Main instance;
    private Human[] humans;
    private Graphics gr;
    private boolean running;

    public Simulation(Main main,Configuration config)  {
        this.config = config;
        this.instance = main;
        this.setVisible(true);
        this.setSize(1000,1000);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.BLACK);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.init();
        //this.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        g.clearRect(0,0,1000,1000);
        Graphics2D g2d = (Graphics2D) g;
        for (Human human : this.humans){
            human.draw(g2d);
            human.move(Direction.values()[this.getInstance().getRandom().nextInt(7)],this.getInstance().getRandom().nextInt(500));
        }
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.paint(g);
    }

    public void init(){
        this.humans = new Human[this.config.getHumansNumber()];
        for (int i = 0; i < this.config.getStudent(); i++)
            this.humans[i] = new Student(this);
        for (int i = this.config.getStudent(); i < this.config.getHumansNumber(); i++)
            this.humans[i] = new Staff(this);
        humans[58].setHealthy(false);
        Human h = this.humans[58];
        this.humans[50].move(h.getX(),h.getY()+150);
    }

    public void start()  {
        this.running = true;
        int i =0;
        while(isRunning()){
            for (Human human : humans) {
                human.move(human.getX() + this.getInstance().randomBetween(1, -1), human.getY() + this.getInstance().randomBetween(1, -1));
            }
            SwingUtilities.updateComponentTreeUI(this);
            System.out.println(i++);
            try {
                Thread.currentThread().sleep(1000/30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean isRunning() {
        return running;
    }

    public Human[] getHumans() {
        return humans;
    }

    public Main getInstance() {
        return instance;
    }
}

