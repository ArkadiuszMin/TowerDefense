package src.main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

//here we will draw things
public class GameScreen extends JPanel {

    private Random random;
    private BufferedImage img;

    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    public GameScreen(BufferedImage img){
        this.img = img;

        loadObject();

        this.random = new Random();
    }

    private void loadObject() {
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                this.sprites.add(this.img.getSubimage(j*32, i*32, 32, 32));
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(this.sprites.get(19), 0, 0, null);


    }

    private Color getRandomColor(){
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return new Color(red, green, blue);
    }

}
