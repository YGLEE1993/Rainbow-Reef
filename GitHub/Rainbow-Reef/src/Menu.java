import gfx.Assets;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Menu implements MouseListener {

    private Game game;
    public Rectangle playButton = new Rectangle(210,150,200,64);
    public Rectangle helpButton = new Rectangle(210,250,200,64);
    public Rectangle quitButton = new Rectangle(210,350,200,64);
    public Rectangle backButton = new Rectangle(210,350,200,64);

    public Menu(Game game) {

        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //get mouse location
        int mouseX = e.getX();
        int mouseY = e.getY();

        //MENU
        if(game.state == Game.STATE.MENU){

            //PLAT BUTTON CLICKED
            if(mouseOver(mouseX, mouseY,210,150,200,64)){
                game.state = Game.STATE.GAME;
            }

            //HELP BUTTON CLICKED
            if(mouseOver(mouseX, mouseY,210,250,200,64)){
                game.state = Game.STATE.HELP;
            }

            //QUIT BUTTON CLICKED
            if(mouseOver(mouseX, mouseY,210,350,200,64)){
                System.exit(1) ;
            }
        }

        //BACK BUTTON FOR HELP CLICKED
        if(game.state == Game.STATE.HELP){
            if(mouseOver(mouseX, mouseY,210,350,200,64)){
                game.state = Game.STATE.MENU;
                return;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private boolean mouseOver(int mouseX, int mouseY, int x, int y, int width, int height){
       //if the xtop left is less than top right then
        // if top mid y is greater than bottom mid y

        if(mouseX > x && mouseX < x + width ){
            if(mouseY > y && mouseY < y + height){
                return true;
            }else
                return false;
        }else
            return false;
    }

    public void tick(){

    }


    public void render(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        if(game.state == Game.STATE.MENU){

            Font fnt = new Font("arial", 1, 50); //for h1
            Font fnt2 = new Font("arial", 1, 30); //for body
            //TITLE
            g.setFont(fnt);
            g.drawString("RAINBOW REEF", 130,100);

            //Draw Buttons
            g.setFont(fnt2);

            g.drawString("PLAY", 270, 190);
            g2d.draw(playButton);

            g.drawString("HELP", 270, 290);
            g2d.draw(helpButton);

            g.drawString("QUIT", 270, 390);
            g2d.draw(quitButton);

        }else if(game.state == Game.STATE.HELP){

            Font fnt = new Font("arial", 1, 50); //for h1
            Font fnt2 = new Font("arial", 1, 30); //for body

            //HELP BODY
            g.setFont(fnt2);
            g.drawImage(Assets.helpBackground, 0, 0, null);

            //HELP TITLE
            g.setFont(fnt);
            g.drawString("HELP", 260,80);

            //BACK BUTTON FOR HELP
            g.setFont(fnt2);
            g.drawString("BACK", 260, 390);
            g2d.draw(backButton);

        }
    }
}
