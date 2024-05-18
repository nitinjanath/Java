package FinalProject;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AlienHorde {
    private List<Alien> aliens;

    public AlienHorde() {
        aliens = new ArrayList<>();
    }

    public void add(Alien al) {
        aliens.add(al);
    }

    public void drawEmAll(Graphics window) {
        for (Alien alien : aliens) {
            alien.draw(window);
        }
    }

    public void moveEmAll() {
        for (Alien alien : aliens) {
            alien.move("DOWN"); // Move down, for example
        }
    }

    public void removeDeadOnes(List<Ammo> shots) {
        Iterator<Alien> alienIterator = aliens.iterator();

        while (alienIterator.hasNext()) {
            Alien alien = alienIterator.next();

            for (Ammo ammo : shots) {
                if (checkCollision(ammo, alien)) {
                    alienIterator.remove(); // Remove alien if it's hit
                    break; // Exit inner loop once an alien is removed
                }
            }
        }
    }

    private boolean checkCollision(MovingThing a, MovingThing b) {
        return a.getX() < b.getX() + b.getWidth() &&
               a.getX() + a.getWidth() > b.getX() &&
               a.getY() < b.getY() + b.getHeight() &&
               a.getY() + a.getHeight() > b.getY();
    }

	public List<Alien> getAliens()
	{
		return aliens;
	}

    public String toString() {
        StringBuilder sb = new StringBuilder("Alien Horde:\n");
        for (Alien alien : aliens) {
            sb.append(alien.toString()).append("\n");
        }
        return sb.toString();
    }
}
