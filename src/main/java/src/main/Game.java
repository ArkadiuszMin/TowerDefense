package src.main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Game extends JFrame  implements Runnable{
    private GameScreen gameScreen;
    private BufferedImage img;

    private Thread gameThread;

    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;
    public Game(){
        importImg();
        setSize(640, 640); //Setting the size of window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.gameScreen = new GameScreen(this.img);
        add(gameScreen);
        setVisible(true);
    }

    private void start(){
        gameThread = new Thread(this){}; //constructor needs a run() method, however we already implemented that, so by typing this, we make program look it by himself
        // so basically, we created new thread, gave it the run method, which is responsible for rendering and updating game, and then we started the thread
        // so overall everything works
        gameThread.start();
    }

    @Override
    public void run(){
        double timePerFrames = 1000000000.0/this.FPS_SET;
        double timePerUpdate = 1000000000.0/this.UPS_SET;
        long lastFrame=System.nanoTime();
        long lastUpdate = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastSecond = System.currentTimeMillis();

        //render (repaint)
        while(true){
            if(System.nanoTime() - lastFrame >= timePerFrames){
                lastFrame = System.nanoTime();
                this.gameScreen.repaint();
                frames++;
            }

            if(System.nanoTime() - lastUpdate >= timePerUpdate){
                this.updateGame();
                lastUpdate = System.nanoTime();
                updates++;
            }

            if(System.currentTimeMillis() - lastSecond >= 1000){
                lastSecond = System.currentTimeMillis();
                System.out.println("FPS: " + frames + "|" + "UPS: " + updates);
                frames=0;
                updates=0;
            }
        }
    }
    private void updateGame(){
    }


    public static void main(String[] args){

        Game game = new Game();
        game.start();
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/spriteatlas.png");
        try{
            this.img = ImageIO.read(is);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
