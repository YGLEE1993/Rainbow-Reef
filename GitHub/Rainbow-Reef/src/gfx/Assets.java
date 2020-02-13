package gfx;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Assets {

    private static final int width = 32, height =32;

    public static BufferedImage background;
    public static BufferedImage bar, ball, boss, heart;
    public static BufferedImage block1, block2, block3, block4, block5, block6;
    public static BufferedImage block7, b8, b9;
    public static BufferedImage background2, helpBackground, bar2, ball2, boss2, potal;

    public static void init(){
        //get the spriteSheet sheet.png
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/sheet.png"));//RAINBOW REEF
        SpriteSheet sheet2 = new SpriteSheet(ImageLoader.loadImage("/sheet2.png"));//RICK & MORTY
        SpriteSheet helpSheet = new SpriteSheet(ImageLoader.loadImage("/helpSheet.png"));//help page

        //RAINBOW REEF version
        heart = sheet.crop(608, 0,32,32);
        background2 = sheet.crop(0,80,640,480);

        ball2 = sheet.crop(0, 0, 35, 35);
        bar2 = sheet.crop(398, 0, 80, 30);
        boss2 = sheet2.crop(315, 0, 70, 80);

        block1 = sheet.crop(35, 0, 40, 20); //blue
        block2 = sheet.crop(115, 0, 40, 20); //stone
        block3 = sheet.crop(155, 0, 40, 20); //green
        block4 = sheet.crop(155, 0, 40, 20); //red
        block5 = sheet.crop(195, 0, 40, 20); //blue
        block6 = sheet.crop(235, 0, 40, 20); //yellow
        block7 = sheet.crop(275, 0, 40, 20); //purple


        b8 = sheet.crop(35, 20, 40, 20); //heart
        b9 = sheet.crop(115, 0, 40, 20); //star

        //Rick and Morty version
        background = sheet2.crop(0,80,640,480);
        ball = sheet2.crop(0, 0, 35, 35);
        bar = sheet2.crop(0, 50, 83, 30);
        boss = sheet2.crop(535, 0, 74, 80); //smile rick
        potal = sheet2.crop(480, 0, 60, 80);

        //Help
        helpBackground = helpSheet.crop(0,80,640,480);

    }
}

