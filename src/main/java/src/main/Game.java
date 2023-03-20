package src.main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Game extends JFrame {
    private GameScreen gameScreen;

    private BufferedImage img;
    public Game(){

        importImg();

        setSize(640, 640); //Setting the size of window
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.gameScreen = new GameScreen(this.img);
        add(gameScreen);
    }

    private void importImg() {

        InputStream is = getClass().getResourceAsStream("/spriteatlas.png");

        try{
            this.img = ImageIO.read(is);
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args){
        Game game = new Game();
    }
}
