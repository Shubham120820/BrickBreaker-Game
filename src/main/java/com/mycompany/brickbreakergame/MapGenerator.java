/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickbreakergame;

/**
 *
 * @author Shubham
 */
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

public class MapGenerator {
    public int map[][];
    int BrickWidth;
    int BrickHeight;
    public MapGenerator(int row, int col){
        map = new int [row][col];
        for (int[] map1 : map) {
            for (int j = 0; j<map[0].length; j++) {
                //initially set the value to 1
                map1[j] = 1;
            }
        }
        BrickWidth = 540/col;
        BrickHeight = 150/row;
    }
    public void draw(Graphics2D gs){
        for(int i=0; i<map.length; i++){
            for(int j=0; j<map[0].length; j++){
                if(map[i][j]>0){
                    gs.setColor(Color.blue);
                    gs.fillRect(j* BrickWidth + 80, i* BrickHeight + 50, BrickWidth, BrickHeight);
                    
                    
                    gs.setStroke(new BasicStroke(3));
                    gs.setColor(Color.black);
                    gs.drawRect(j* BrickWidth + 80, i* BrickHeight + 50, BrickWidth, BrickHeight);
                    
                }
            }
        }
    }
    public void setBricksValue(int value, int row, int col){
        map[row][col] = value;
    }
    
    
}
