package FinalProject;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bullets {
    private List<Ammo> ammo;

    public Bullets() {
        ammo = new ArrayList<>();
    }

    public void add(Ammo newAmmo) {
        ammo.add(newAmmo);
    }

    public void drawEmAll(Graphics window) {
        for (Ammo a : ammo) {
            a.draw(window);
        }
    }

    public void moveEmAll() {
        Iterator<Ammo> it = ammo.iterator();
        while (it.hasNext()) {
            Ammo a = it.next();
            a.move("UP"); // Move upward
            if (a.getY() < 0) { // If off-screen
                it.remove(); // Remove from list
            }
        }
    }

    public void cleanEmUp() {
        Iterator<Ammo> it = ammo.iterator();
        while (it.hasNext()) {
            Ammo a = it.next();
            if (a.getY() < 0) { // If off-screen
                it.remove(); // Remove from list
            }
        }
    }

    public List<Ammo> getList() {
        return ammo;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Bullets:\n");
        for (Ammo a : ammo) {
            sb.append(a.toString()).append("\n");
        }
        return sb.toString();
    }
}
