import java.awt.*;
import java.awt.image.BufferedImage;

public class Block {
    public static int width = 40, height = 20;
    public int x, y;
    protected BufferedImage img;

    public Block(BufferedImage img, int x, int y) {
        this.x = x;
        this.y = y;
        this.img = img;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    public boolean collide(Rectangle rectangle){
        return getBounds().intersects(rectangle);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
