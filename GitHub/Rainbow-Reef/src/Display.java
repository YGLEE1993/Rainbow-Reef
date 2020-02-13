import javax.swing.*;
import java.awt.*;

public class Display {

    private JFrame frame; //window
    private Canvas canvas; //paints
    private String title;
    private int width, height; //pixels

    //Constructor
    public Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        createDisplay();
    }

    //create this method just to not shove everything in the constructor
    private void createDisplay(){

        //init JFrame
        frame = new JFrame(title);
        frame.setSize(width,height);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // making sure it closes down properly
        frame.setResizable(false);//making it not resizable
        frame.setLocationRelativeTo(null);// appear in the middle of the screen
        frame.setVisible(true);// visible

        //init Canvas
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false); //let JFrame focus only

        frame.add(canvas);
        frame.pack(); //adjust to show the canvas better

    }

    //Canvas & JFrame is private so we use getter so that other class can access
    public Canvas getCanvas(){

        return canvas;
    }

    public JFrame getFrame(){

        return frame;
    }

}
