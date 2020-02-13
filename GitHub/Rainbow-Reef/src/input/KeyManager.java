package input;

import gfx.Assets;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager extends JPanel implements KeyListener, ActionListener {

    public int y = 450;
    public boolean running = false;
    private int barX = 440;
    private int ballX = 100;
    private int ballY = 400;
    private int ballXDir = -2; //ball direction
    private int ballYDir = -2;

    public KeyManager(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        tick();
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(Assets.bar, barX, y, null);
        g2.drawImage(Assets.ball, ballX, ballY, null);
    }

    public void moveLeft(){
        running = true;
        barX -= 20;
    }

    public void moveRight(){
        running = true;
        barX += 20;
    }


    public void updateBlockCollision(){
        ballYDir = -ballYDir;
    }

    public void tick(){
        if(running){
            if(new Rectangle(ballX, ballY, 35,35).intersects(new Rectangle(barX, y, 83,30))){
                ballYDir =- ballYDir;
            }
            ballX += ballXDir;
            ballY += ballYDir;

            if(ballX < 0)
                ballXDir =- ballXDir;
            if(ballX > 605)
                ballXDir =- ballXDir;
            if(ballY < 0)
                ballYDir =- ballYDir;
            if(ballY > 450)
                ballYDir =- ballYDir;
        }
    }

    public boolean ballOutOfScope(){
        if(ballY > 450){
                ballX = 100;
                ballY = 400;
                return true;
            }

        return false;
    }
    public void startPosition(){
        ballX = 100;
        ballY = 400;
    }

    @Override //whenever you press
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();
        System.out.println("Pressed");

        if(keyCode == KeyEvent.VK_LEFT){
            moveLeft();
            if(barX <= 1)
                barX = 1;
        }
        if(keyCode == KeyEvent.VK_RIGHT){
            moveRight();
            if(barX >= 557)
                barX = 557;
        }
        if(keyCode == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
    }

    @Override //whenever you release the key
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void setBallX(int ballX) {
        this.ballX = ballX;
    }

    public void setBallY(int ballY) {
        this.ballY = ballY;
    }

    public int getBallX() {
        return ballX;
    }

    public int getBallY() {
        return ballY;
    }
}
