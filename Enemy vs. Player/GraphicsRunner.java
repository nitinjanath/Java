package com.mycompany.apcsa;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nitin
 */
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicsRunner extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public GraphicsRunner() {
        super("MAKE YOUR OWN SHAPE");
        setSize(WIDTH, HEIGHT);
        getContentPane().add(new ShapePanel());
        getContentPane().add(new MovingShapePanel());
        //getContentPane().add(new MovingShapePanel());
        setVisible(true);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        GraphicsRunner run = new GraphicsRunner();
    }
}
