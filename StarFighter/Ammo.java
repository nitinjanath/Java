package FinalProject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;

public class Ammo extends MovingThing {
    private int speed;

    public Ammo(int x, int y, int s) {
        super(x, y, 5, 10); // Define ammo size, e.g., 5x10
        speed = s;
    }

    public void setSpeed(int s) {
        speed = s;
    }

    public int getSpeed() {
        return speed;
    }

    public void move(String direction) {
        if (direction.equals("UP")) {
            setY(getY() - speed);
        }
    }

    public void draw(Graphics window) {
        window.setColor(Color.YELLOW); // For simplicity, let's use a simple rectangle
        window.fillRect(getX(), getY(), getWidth(), getHeight());
    }

    public String toString() {
        return super.toString() + " Speed: " + speed;
    }
}
