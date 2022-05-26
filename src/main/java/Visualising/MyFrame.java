package Visualising;

import Elements.Cherry;
import Elements.Ghost;
import Elements.Point;
import Elements.Wall;
import Others.MapDirection;
import Others.Vector2d;
import Others.WorldMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame extends JFrame implements KeyListener {
    public JPanel[][] panels;
    public WorldMap map;

    public JLabel Lpoints;
    public JLabel Llives;

    private int sizeOfElement=40;

    public MyFrame(WorldMap map){

        this.setVisible(true);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLocationRelativeTo(null);
        this.setSize(map.width*this.sizeOfElement+500+16,map.height*this.sizeOfElement+39);
        this.setResizable(false);

        this.map=map;
        this.createJPanels(map);

        this.Lpoints = new JLabel();
        this.Lpoints.setBounds(0,100,500,75);
        this.Lpoints.setFont(new Font("Arial",Font.PLAIN,75));
        this.Lpoints.setText("Points: 0");
        this.add(Lpoints);

        this.Llives = new JLabel();
        this.Llives.setBounds(0,300,500,75);
        this.Llives.setFont(new Font("Arial",Font.PLAIN,75));
        this.Llives.setText("Points: 0");
        this.add(Llives);


        this.addKeyListener(this);
    }

    private void createJPanels(WorldMap map){
        JPanel[][] panels = new JPanel[map.width][map.height];
        for (int i=0; i<map.width; i++) {
            for (int j = 0; j < map.height; j++) {
                JPanel panel = new JPanel();
                panel.setBounds(i*this.sizeOfElement+500,(map.height-j-1)*this.sizeOfElement,this.sizeOfElement,this.sizeOfElement);
                panels[i][j]=panel;
                this.add(panel);
            }
        }
        this.panels=panels;
    }

    public void draw(){
        for (int i=0; i<this.map.height; i++) {
            for (int j = 0; j < this.map.width; j++) {
                if(this.map.objectAt(new Vector2d(i,j)) instanceof Wall){
                    this.panels[i][j].setBackground(MyColor.WALL.toColor());
                } else if (this.map.objectAt(new Vector2d(i,j)) instanceof Point){
                    this.panels[i][j].setBackground(MyColor.POINT.toColor());
                    // this.panels[j][i].paintComponents();
                } else if (this.map.objectAt(new Vector2d(i,j)) instanceof Cherry) {
                    this.panels[i][j].setBackground(MyColor.CHERRY.toColor());
                } else {
                    this.panels[i][j].setBackground(MyColor.NOTHING.toColor());
                }
            }
        }
        Vector2d position=this.map.pacMan.getPosition();
        this.panels[position.x][position.y].setBackground(MyColor.PACMAN.toColor());

        for (Ghost ghost : this.map.ghosts){
            position=ghost.getPosition();
            if (this.map.pacMan.isOP()){
                this.panels[position.x][position.y].setBackground(MyColor.WEAKGHOST.toColor());
            }
            else{
                this.panels[position.x][position.y].setBackground(MyColor.GHOST.toColor());
            }

        }
    }

    public void actualize(){
        this.Lpoints.setText("Points: " + this.map.pacMan.points);
        this.Llives.setText("Lives: " + this.map.pacMan.lives);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // nic
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.map.pacMan.isDead()){
            return; // blokuje gre
        }

        switch (e.getKeyCode()){
            case 37:
                this.map.pacMan.setDirection(MapDirection.WEST); // Left
                break;
            case 38:
                this.map.pacMan.setDirection(MapDirection.NORTH); // Up
                break;
            case 39:
                this.map.pacMan.setDirection(MapDirection.EAST); // Right
                break;
            case 40:
                this.map.pacMan.setDirection(MapDirection.SOUTH); // Down
                break;
            default:
                return;
        }

        /*this.map.run();
        this.draw();
        this.actualize();
        this.map.time=0;*/
    }

    @Override
    public void keyReleased(KeyEvent e) {
    // nic
    }
}
