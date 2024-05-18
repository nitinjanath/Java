package FinalProject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class OuterSpace extends Canvas implements KeyListener, Runnable {
    private Ship ship;
	private AlienHorde alienHorde;
    private Bullets bullets;
    private boolean[] keys;
    private BufferedImage back;
	private boolean gameOver;
	private int moveCounter;
	private boolean gameWon;

    public OuterSpace() {
        setBackground(Color.black);
        keys = new boolean[5]; // Tracks key presses
        bullets = new Bullets(); // Manages all active ammo
        alienHorde = initializeAlienHorde();
		gameWon = false;
		gameOver = false;
		moveCounter = 0;

        ship = new Ship(400, 520, 50, 50, 5);

        this.addKeyListener(this);
        new Thread(this).start(); // Start the game loop

        setVisible(true);
    }
	private AlienHorde initializeAlienHorde() {
        AlienHorde horde = new AlienHorde(); // Example size of 10
        int initialX = 50; // Starting x position
        int initialY = 50; // Starting y position
        int spacingX = 70; // Horizontal spacing between aliens
        int spacingY = 50; // Vertical spacing between rows
        int aliensPerRow = 10; // Number of aliens per row
        int rows = 3; // Number of rows

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < aliensPerRow; c++) {
                int x = initialX + (c * spacingX);
                int y = initialY + (r * spacingY);
                horde.add(new Alien(x, y, 50, 50, 1)); // Add aliens with specific positions
            }
        }

        return horde;
    }

    public void update(Graphics window) {
        paint(window);
    }

    public void paint(Graphics window) {
		if (gameOver) {
            showGameOverScreen(window);
            return; // Stop game logic if game is over
        }
		if (gameWon) { // If the game is won
            showGameWonScreen(window);
            return;
        }
        Graphics2D twoDGraph = (Graphics2D) window;

        if (back == null) {
            back = (BufferedImage) createImage(getWidth(), getHeight());
        }

        Graphics graphToBack = back.createGraphics();

        graphToBack.setColor(Color.BLACK);
        graphToBack.fillRect(0, 0, 800, 600); 
		if (keys[0] && ship.getX() > 0) { 
            ship.move("LEFT");
        }
        if (keys[1] && ship.getX() < 750) { 
            ship.move("RIGHT");
        }
        if (keys[2] && ship.getY() < 550) { 
            ship.move("DOWN");
        }
        if (keys[3] && ship.getY() > 0) { 
            ship.move("UP");
        }
		
		ArrayList<Ammo> toRemoveBullets = new ArrayList<>();
		ArrayList<Alien> toRemoveAliens = new ArrayList<>();
        ship.draw(graphToBack);
		bullets.drawEmAll(graphToBack); // Draw all bullets
        bullets.moveEmAll();
		for (Ammo bullet : bullets.getList()) {
            for (Alien alien : alienHorde.getAliens()) {
                if (checkCollision(bullet, alien)) {
                    toRemoveBullets.add(bullet); // Remove bullet on hit
                    toRemoveAliens.add(alien); // Remove alien on hit
                    break; // Only one bullet per alien
                }
            }
        }

        bullets.getList().removeAll(toRemoveBullets);
		alienHorde.getAliens().removeAll(toRemoveAliens);

        alienHorde.removeDeadOnes(bullets.getList()); 

        alienHorde.drawEmAll(graphToBack); 
		moveCounter++;
		if (moveCounter % 20 == 0) { // Adjust frequency of movement
			alienHorde.moveEmAll(); // Move aliens downward
		}
		for (Alien alien : alienHorde.getAliens()) {
                    if (alien.getY() >= 540)
                    {
                        gameOver = true;
                        break;
                    }
            if (checkCollision(ship, alien)) {
                gameOver = true; // Game over if ship collides with an alien
                break; // No need to check further
            }
        }
		alienHorde.removeDeadOnes(bullets.getList()); // Remove aliens hit by bullets

        if (!gameOver) { // If game is still on
            alienHorde.drawEmAll(graphToBack); // Draw remaining aliens
        }
        
        if (alienHorde.getAliens().isEmpty())
        {
            showGameWonScreen(graphToBack);
        }
		
		


        twoDGraph.drawImage(back, null, 0, 0); // Render the back buffer
    }
	private boolean checkCollision(MovingThing a, MovingThing b) {
        return a.getX() < b.getX() + b.getWidth() &&
               a.getX() + a.getWidth() > b.getX() &&
               a.getY() < b.getY() + b.getHeight() &&
               a.getY() + b.getHeight() > b.getY();
    }
	private void showGameOverScreen(Graphics window) {
        Graphics2D twoDGraph = (Graphics2D) window;
        if (back == null) {
            back = (BufferedImage) createImage(getWidth(), getHeight());
        }

        Graphics graphToBack = back.createGraphics();
        graphToBack.fillRect(0, 0, 800, 600); // Clear background

        graphToBack.setColor(Color.RED);
        graphToBack.drawString("Game Over", 350, 300); // Display game-over message

        twoDGraph.drawImage(back, null, 0, 0); // Render the back buffer
    }
	private void showGameWonScreen(Graphics window) {
        Graphics2D twoDGraph = (Graphics2D) window;
        if (back == null) {
            back = (BufferedImage) createImage(getWidth(), getHeight());
        }

        Graphics graphToBack = back.createGraphics();
        graphToBack.fillRect(0, 0, 800, 600); // Clear background

        graphToBack.setColor(Color.GREEN);
        graphToBack.drawString("You Win!", 350, 300); // Display win message

        twoDGraph.drawImage(back, null, 0, 0); // Render the back buffer
	}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keys[0] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keys[1] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keys[2] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[3] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[4] = true;
            // Fire ammo from the ship
            Ammo newAmmo = new Ammo(ship.getX() + ship.getWidth() / 2, ship.getY(), 10); // Center-top of ship
            bullets.add(newAmmo);
        }
        repaint();
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keys[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keys[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keys[2] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[3] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[4] = false;
        }
        repaint();
    }

    public void keyTyped(KeyEvent e) {
        // Not needed for this implementation
    }

    public void run() {
        try {
            while (true) {
                Thread.sleep(5); // Slow down the game loop
                repaint(); // Refresh the screen
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
