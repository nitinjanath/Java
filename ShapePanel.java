/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apcsa;

/**
 *
 * @author nitin
 */
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapePanel extends JPanel {
    
    private Shape run;

    public ShapePanel() {
        setBackground(Color.WHITE);
        setVisible(true);
        run = new Shape(400, 300, 100, 100, Color.BLUE, 50, 60);
        
    }

    public void update(Graphics window) {
        paint(window);
    }

    /*
*All of your test code should be placed in paint.
     */
    public void paint(Graphics window) {
        window.setColor(Color.WHITE);
        window.fillRect(0, 0, getWidth(), getHeight());
        window.setColor(Color.BLUE);
        window.drawRect(20, 20, getWidth() - 40, getHeight() - 40);
        window.setFont(new Font("TAHOMA", Font.BOLD, 18));
        window.drawString("CREATE YOUR OWN SHAPE!", 40, 40);
        //make a Shape
        run.draw(window);
        //draw the Shape
    }
}
