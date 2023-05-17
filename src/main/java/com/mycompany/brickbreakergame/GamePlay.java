/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickbreakergame;

/**
 *
 * @author Shubham
 */
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    private Timer Timer;
    private int delay = 8;
    private int playerX = 310;
    private int BallposX = 120;
    private int BallposY = 350;
    private int BalldirX = -1;
    private int BalldirY = -2;
    private MapGenerator map;
    
    
    
    //initializing the constructor
    public GamePlay(){
        map = new MapGenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        Timer =  new Timer (delay, this);
        Timer.start();
        
    }
    public void paint(Graphics gs){
        gs.setColor(Color.black);
        gs.fillRect(1, 1, 692, 592);
        
        map.draw((Graphics2D) gs);
        
        //boundaries
        gs.setColor(Color.yellow);
        gs.fillRect(0, 0, 3, 592);
        gs.fillRect(0, 0, 692, 3);
        gs.fillRect(691, 0, 3, 592);
        //score
        gs.setColor(Color.white);
        gs.setFont(new Font("serif", Font.BOLD, 25));
        gs.drawString("" + score, 590, 30);
        //slider
        gs.setColor(Color.red);
        gs.fillRect(playerX, 550, 100, 8);
        
        //ball
        gs.setColor(Color.green);
        gs.fillOval(BallposX, BallposY, 20, 20);
        
        if(BallposY > 570){
            play = false;
            BalldirX = 0;
            BalldirY = 0;
            gs.setColor(Color.red);
            gs.setFont(new Font("serif", Font.BOLD, 30));
            gs.drawString("        Game Over: " + score, 190, 300);
            
            gs.setFont(new Font("serif", Font.BOLD, 30));
            gs.drawString("       Press Enter To Restart", 190, 340);
            
        }
            
            if(totalBricks == 0){
                play = false;
                BalldirX = -1;
                BalldirY = -2;
                gs.setColor(Color.red);
                gs.setFont(new Font("serif", Font.BOLD, 30));
                gs.drawString("           Game Over: "+ score, 190, 300);
                
                gs.setFont(new Font("serif", Font.BOLD, 30));
                gs.drawString("           Press Enter To Restart", 190, 340);
                
            }
            
            
          gs.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       Timer.start();
       if(play){
           if(new Rectangle(BallposX, BallposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8)));
           BalldirY = -BalldirY;
       }
       
       A:
       
       for(int i=0; i<map.map.length; i++){
           for(int j=0; j<map.map[0].length; j++){
               if(map.map[i][j]>0){
                   int BrickX = j* map.BrickWidth + 80;
                   int BrickY = i* map.BrickHeight +50;
                   int BrickWidth = map.BrickWidth;
                   int BrickHeight = map.BrickHeight;
                   
                   Rectangle rect = new Rectangle(BrickX, BrickY, BrickWidth, BrickHeight);
                   Rectangle Ballrect = new Rectangle(BallposX, BallposY, 20, 20);
                   Rectangle Brickrect = rect;
                   
                   if(Ballrect.intersects(Brickrect)){
                       map.setBricksValue(0, i, j);
                       totalBricks--;
                       score += 5;
                       if(BallposX + 90 <= Brickrect.x || BallposX + 1 >= Brickrect.x + BrickWidth){
                           BalldirX = -BalldirX;
                       }else{
                           BalldirY = -BalldirY;
                       }
                       break A;
                   }
               }
           }
       }
           BallposX += BalldirX;
           BallposY += BalldirY;
           
           if(BallposX<0){
               BalldirX = -BalldirX;
               
           }
           if(BallposY<0){
               BalldirY = -BalldirY;
               
           }
           if(BallposX>670){
               BalldirX = -BalldirX;
           }
       repaint();
       
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX>=600){
                playerX=600;
            }else{
                moveRight();
            }
            
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX<10){
                playerX=10;
            }else{
                moveLeft();
            }
        }
        
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                BallposX = 120;
                BallposY = 350;
                BalldirX = -1;
                BalldirY = -2;
                score = 0;
                playerX = 310;
                totalBricks = 21;
                map = new MapGenerator(3, 7 );
                repaint();
            }
        }
    }
     public void moveRight(){
        play = true;
        playerX += 20;
    }
    public void moveLeft(){
        play = true;
        playerX -= 20;
    }
    
    
    
}

