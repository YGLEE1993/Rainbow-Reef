import gfx.Assets;
import input.KeyManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GameWorld {

    public int x,y;
    public boolean didWin = false;
    public KeyManager keyManager = new KeyManager();
    public ArrayList<Block> blocks = new ArrayList<Block>();
    BufferedImage blockImage[] = {Assets.block1, Assets.block2, Assets.block3, Assets.block4, Assets.block5,Assets.block6,Assets.block7, Assets.b8, Assets.b9};

    public GameWorld() {

        makeWorld();
    }

    private void makeWorld(){

        //LEVEL 1
        if(!didWin){
            for(x=280; x < 640; x+=400){
                for(y=0; y<80; y+=80){
                    addBlock(Assets.potal,x,y);
                }
            }

            for(int x = 0; x < 640; x += 40) {
                for (int y = 80; y < 150; y += 20) {
                    Random random = new Random();
                    int blockIndex = random.nextInt(9);
                    addBlock(blockImage[blockIndex], x, y);
                }
            }
        }else if(didWin){ //LEVEL 2
            for(x=100; x < 500; x+=90){
                for(y=0; y<80; y+=80){
                    addBlock(Assets.boss,x,y);
                }
            }
            for(int x = 120; x < 520; x += 40) {
                for (int y = 80; y < 280; y += 20) {
                    Random random = new Random();
                    int blockIndex = random.nextInt(9);
                    addBlock(blockImage[blockIndex], x, y);
                }
            }
        }
    }


    public boolean blockCollision(int x, int y){
        for(Block b: blocks){
            if ((b.getBounds().intersects(new Rectangle(x, y, 35, 35)))) {
                return true;
            }
        }
        return false;
    }


    public void eraseBlocks(int x, int y) {
        ArrayList<Block> toRemove = new ArrayList<Block>();
        for (Block b: blocks) {
            if((b.getBounds()).intersects(new Rectangle(x, y, 35, 35))){
                toRemove.add(b);
            }
        }
        for (Block b : toRemove) {
            removeBlock(b);
        }
    }

    private void paintBlock(Graphics g, Block b) {
        g.drawImage(b.img, b.getX(), b.getY(), null);
    }

    public void paintBlocks(Graphics g) {
        for (Block b : blocks) {
            paintBlock(g, b);
        }
    }

    public void addBlock(BufferedImage i, int x, int y){
        blocks.add(new Block(i, x, y));
    }

    public void removeBlock(Block b) {
        blocks.remove(b);
        if(blocks.isEmpty()){

            didWin = true;

            if(didWin){
                Object[] answers = {"YES", "NO"};
                Object selectedAnswer = JOptionPane.showInputDialog(null, "Level2 ?", "You Won!", JOptionPane.INFORMATION_MESSAGE, null, answers, answers[0]);

                if (selectedAnswer == "YES") {
                    keyManager.startPosition();
                    makeWorld();
                } else {
                    System.exit(0);
                }
            }

        }
    }
}
