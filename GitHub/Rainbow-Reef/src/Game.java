import gfx.Assets;
import input.KeyManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Game implements Runnable{
    //Runnable allow it to be runnable on  thread


    public int width, height;
    public String title;
    public int x = 0;
    public int fps = 80; //Ticks per sec
    public int trials = 3; // 3 lives count
    public int score = 0;
    ArrayList<BufferedImage> lives = new ArrayList<java.awt.image.BufferedImage>();

    //Display
    private Display display;

    //Thread
    private Thread thread;
    private boolean running = false;

    //States
    public enum STATE{
        MENU,
        HELP,
        GAME
    };
    public STATE state = STATE.MENU;

    //MENU
    private Menu menu;


    //buffer for the canvas and graphic for the painting
    private Graphics g;
    private BufferStrategy bs;

    private KeyManager keyManager;
    private GameWorld gameWorld;
    public JLabel printScore;




    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
    }

    private void init(){
        //only runs once & get every gfx for the game
        //when the launcher calls game.start() it calls -> init() which calls the Display

        Assets.init(); //get all the assets
        gameWorld = new GameWorld();
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        printScore = new JLabel("Score: 0");
        printScore.setVisible(true);

        //init MENU and MouseListener
        menu = new Menu(this);
        display.getCanvas().addMouseListener(menu);

        //instead of doing it here we use Assets class so we don't need to crop out of spriteSheet over and over

    }


    private void updateBallBlockCollision(){
        if(gameWorld.blockCollision(keyManager.getBallX(), keyManager.getBallY())){
            keyManager.updateBlockCollision();
            score++;
            printScore.setText("Score: " + score);
        }
    }

    //LIVES
    private void updateLives(){
        int x = 2;
        int y = 2;
        for(int i = 0; i < trials; i++){
            lives.add(Assets.heart);
            g.drawImage(lives.get(i), x,y,null);
            x +=32;
        }
    }

    public KeyManager getKeyManager(){

        return keyManager;
    }

    //GAME LOOP!
    private void tick(){

        this.display.getFrame().pack();
        this.display.getFrame().setVisible(true);

        if(state == STATE.GAME){
            //update everything for the game
            x += 1;
            this.display.getFrame().getContentPane().add(BorderLayout.NORTH, printScore);
            updateBallBlockCollision();
            gameWorld.eraseBlocks(keyManager.getBallX(), keyManager.getBallY());

        }else if(state == STATE.MENU || state == STATE.HELP){

            menu.tick();

        }

    }


    private void render(){
            //render everything for the game
            bs = display.getCanvas().getBufferStrategy(); // set the canvas using buffer
            if (bs == null) { //checking the buffer
                display.getCanvas().createBufferStrategy(3);
                return;
            }
            g = bs.getDrawGraphics(); //how to draw on the canvas? & graphic allow us to draw
             //clear
             g.clearRect(0,0,width,height);

            if(state == STATE.GAME) {
                //draw
                g.drawImage(Assets.background, 0, 0, null);
                gameWorld.paintBlocks(g);

                updateLives();

                if(keyManager.ballOutOfScope()){
                    trials -= 1;
                    fps += 20; //everytime you die it get's faster
                    if(trials < 0){
                        Object[] answers = {"YES", "NO"};
                        Object selectedAnswer = JOptionPane.showInputDialog(null, "Continue?", "You Died", JOptionPane.INFORMATION_MESSAGE, null, answers, answers[0]);

                        if (selectedAnswer == "YES") {
                            keyManager.setBallX(100);
                            keyManager.setBallY(400);
                            trials = 3;
                        } else {
                            System.exit(0);
                        }
                    }

                }

                keyManager.paintComponent(g);

           }else if(state == STATE.MENU || state == STATE.HELP){

                g.drawImage(Assets.background, 0, 0, null);
                menu.render(g);
            }
            bs.show();
            g.dispose();
    }

    @Override
    public void run() {
        init();
        double timePerTick = 1000000000 / fps; // nano sec
        double delta = 0; //amount of time we had until we have to call tick&render again
        long now; //current time of our computer
        long lastTime = System.nanoTime(); // return currenttime in this computer in nano time
        long timer = 0;
        int ticks = 0;

        while(running){ //while running is true do the game loop(update&render)!
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick; //amount of time pasted / max time allowed
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1){
                tick();
                render();
                ticks++; // we ticked one more time
                delta--;
            }
            if(timer >= 1000000000){
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    //synchronized used for working with thread directly
    public synchronized void start(){
        //check if it's already running then don't do anything
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start(); //it calls the run()
    }

    public synchronized void stop(){
        //check if it's already stopped then don't do anything
        if(!running)
            return;
        running = false;
        try{
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

