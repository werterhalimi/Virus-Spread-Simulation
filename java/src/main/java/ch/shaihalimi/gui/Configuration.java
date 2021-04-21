package ch.shaihalimi.gui;

import ch.shaihalimi.Main;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Configuration extends JFrame {
    private JPanel panel;
    private Main main;
    private int student,staff,infect;

    public Configuration(final Main main) throws HeadlessException {
        //Init main
        this.main = main;
        //Init frame
        this.setTitle("Configuration");
        this.setSize(400,800);
        this.setVisible(true);
        this.panel = new JPanel();

        this.student=50;
        final JSlider studentSlider = new JSlider(1,100,this.student);
        final JLabel studentLabel = new JLabel("Nombre d'eleves: " + studentSlider.getValue());

        studentSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                studentLabel.setText("Nombre d'eleves: " + studentSlider.getValue());
                student=studentSlider.getValue();
            }
        });
        this.panel.add(studentSlider);
        this.panel.add(studentLabel);
        this.staff=10;
        final JSlider profSlider = new JSlider(1,20,this.staff);
        final JLabel profLabel = new JLabel("Nombre de profs: " + profSlider.getValue());

        profSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                profLabel.setText("Nombre de profs: " + profSlider.getValue());
                staff=profSlider.getValue();

            }
        });

        this.panel.add(profSlider);
        this.panel.add(profLabel);

        this.infect=1;
        final JSlider infectSlider = new JSlider(1,20,this.infect);
        final JLabel infectLabel = new JLabel("Nombre de cas zero: " + infectSlider.getValue());

        infectSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                infectLabel.setText("Nombre de cas zero: " + infectSlider.getValue());
                infect=infectSlider.getValue();

            }
        });


        this.panel.add(infectSlider);
        this.panel.add(infectLabel);

        JButton startButton = new JButton("Start simulation");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
        this.panel.add(startButton,BOTTOM_ALIGNMENT);
        this.setContentPane(this.panel);
    }

    public void start(){
        this.main.start();
    }

    public int getInfect() {
        return infect;
    }

    public int getHumansNumber(){
        return this.getStaff()+this.getStudent();
    }

    public int getStudent() {
        return student;
    }

    public int getStaff() {
        return staff;
    }
}
