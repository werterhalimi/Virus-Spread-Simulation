package ch.shaihalimi.gui;

import ch.shaihalimi.Main;
import ch.shaihalimi.human.Direction;
import ch.shaihalimi.human.Human;
import ch.shaihalimi.human.Staff;
import ch.shaihalimi.human.Student;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Simulation extends JFrame {

    private BufferedImage background;
    private Configuration config;
    private Main instance;
    private ArrayList<Human> infected;
    private Human[] humans;
    private boolean running;

    public Simulation(Main main,Configuration config)  {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        try {
            this.background = ImageIO.read(new File("java/src/main/resources/backgroundTM.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.config = config;
        this.instance = main;
        this.infected = new ArrayList<Human>();
        this.setVisible(true);
        this.setSize(1920,1080);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.BLACK);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.init();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.background,0,0,null);
        double inf = this.infected.size();
        g.setColor(Color.white);
        this.drawString(g,"Nombre de cas:" + inf+ "\nTaux d'infection par malade:" + ((inf==1)?0:(inf-1)/inf), 1100, 875);
        for (Human human : this.humans){
            human.draw(g2d);
            human.move(Direction.values()[this.getInstance().getRandom().nextInt(7)],this.getInstance().getRandom().nextInt(200));
        }
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.paint(g);
    }
    private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

    public void init(){
        this.humans = new Human[this.config.getHumansNumber()];
        for (int i = 0; i < this.config.getStudent(); i++)
            this.humans[i] = new Student(this);
        for (int i = this.config.getStudent(); i < this.config.getHumansNumber(); i++)
            this.humans[i] = new Staff(this);
        for (int i = 0; i < this.config.getInfect(); i++)
            this.humans[this.getInstance().getRandom().nextInt(humans.length)].setHealthy(false);

    }



    public boolean isRunning() {
        return running;
    }

    public Human[] getHumans() {
        return humans;
    }

    public void newInfection(Human human){
        this.infected.add(human);
    }

    public Main getInstance() {
        return instance;
    }

    public boolean isWalkable(int x,int y){
        boolean walkable;
        try {
            walkable = this.getField().getRGB(x, y) == -13557214;
        }catch (Exception e){
            walkable = false;
        }
        return !walkable;
    }

    public BufferedImage getField() {
        return background;
    }
}

