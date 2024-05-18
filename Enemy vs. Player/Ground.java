/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.apcsa;

/**
 *
 * @author nitin
 */
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
public class Ground
{
    private ArrayList<Image> tiles = new ArrayList<Image>();
    public Ground() throws IOException
    {
        Image grass = ImageIO.read(new File("C:\\Users\\nitin\\Documents\\NetBeansProjects\\APCSA\\src\\main\\java\\com\\mycompany\\apcsa\\grass.gif"));
        Image road = ImageIO.read(new File("C:\\Users\\nitin\\Documents\\NetBeansProjects\\APCSA\\src\\main\\java\\com\\mycompany\\apcsa\\road.gif"));
        Image gtoroad = ImageIO.read(new File("C:\\Users\\nitin\\Documents\\NetBeansProjects\\APCSA\\src\\main\\java\\com\\mycompany\\apcsa\\gtoroad.gif"));
        Image rtograss = ImageIO.read(new File("C:\\Users\\nitin\\Documents\\NetBeansProjects\\APCSA\\src\\main\\java\\com\\mycompany\\apcsa\\rtograss.gif"));
        tiles.add(grass);     
        tiles.add(road);      
        tiles.add(gtoroad);   
        tiles.add(rtograss);
        //point the tiles at new array list
        //add in each of the images for the grass.gif, gtoroad.gif, road.gif,rtograss.gif
        //tiles.add( ImageIO.read(new File("grass.gif")) );
    }
        public void drawGround(Graphics2D g)
        {
            int tileSize = 50;
            for (int y = 0; y < 600; y += tileSize) 
            { // Adjusted loop end condition for five rows of tiles
                for (int x = 0; x < 800; x += tileSize) 
                {
                    if (y == 0 || y == tileSize)
                    {
                        g.drawImage(tiles.get(0), x, y, null); // Grass for top two tiles
                    } 
                    else if (y == tileSize * 2) 
                    {
                        g.drawImage(tiles.get(2), x, y, null); // Grass to road for the third tile
                    } 
                    else if (y > tileSize * 2 && y < tileSize * 10) 
                    {
                        g.drawImage(tiles.get(1), x, y, null); // Road for middle two tiles
                    } 
                    else if (y == 500) 
                    {
                        g.drawImage(tiles.get(3), x, y, null); // Road to grass for the second to last tile
                    } 
                    else if (y == 550) 
                    {
                        g.drawImage(tiles.get(0), x, y, null); // Grass for the bottom tile
                    }
                }
            }
        }
    }

