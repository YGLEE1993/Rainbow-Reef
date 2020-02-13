import javax.swing.*;

public class Launcher {

    //starts game!

    public static void main(String[] args){
        Game game = new Game("Super Rainbow Reef", 640, 480);
        game.start();
    }

}
