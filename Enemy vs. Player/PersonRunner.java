/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apcsa;

/**
 *
 * @author nitin
 */
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class PersonRunner implements Runnable, KeyListener {
public static final int WIDTH = 800;
public static final int HEIGHT = 600;
public static boolean RUNNING = true;
// Person
private Person p;
private JFrame frame;
private Canvas canvas;
private BufferStrategy bufferStrategy;
public PersonRunner() throws IOException
{
frame = new JFrame("Basic Game");
JPanel panel = (JPanel) frame.getContentPane();
panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
panel.setLayout(null);
canvas = new Canvas();
canvas.setBounds(0, 0, WIDTH, HEIGHT);
canvas.setIgnoreRepaint(true);
panel.add(canvas);
// Initialize person
p = new Person(400, 100);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.pack();
frame.setResizable(false);
frame.setVisible(true);
canvas.createBufferStrategy(2);
bufferStrategy = canvas.getBufferStrategy();
canvas.requestFocus();
canvas.addKeyListener(this);
}
// Game loop
public void run()
{
while (RUNNING) {
try {
render();
} catch (IOException e1) {
e1.printStackTrace();
}
try {
Thread.sleep(100);
} catch (InterruptedException e) {
}
}
}
private void render() throws IOException {
Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
g.clearRect(0, 0, WIDTH, HEIGHT);
render(g);
g.dispose();
bufferStrategy.show();
}
void render(Graphics2D g) throws IOException
{
p.drawPerson(g);
}
public void keyTyped(KeyEvent e) {
// not used
}
public void keyReleased(KeyEvent e) {
// not used
}
public void keyPressed(KeyEvent e)
{
if (e.getKeyCode() == e.VK_W) {
p.moveUp();
}
if (e.getKeyCode() == e.VK_S) {
p.moveDown();
}
if (e.getKeyCode() == e.VK_A) {
p.moveLeft();
}
if (e.getKeyCode() == e.VK_D) {
p.moveRight();
}
}
// ******************************************************************
    public static void main(String[] args) throws IOException 
    {
        PersonRunner game = new PersonRunner();
        new Thread(game).start();
    }
}