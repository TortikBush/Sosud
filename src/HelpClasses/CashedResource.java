package HelpClasses;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CashedResource {
    public static void CashedResource() {
    }

    public static Image cashedChoice;

    static {
        try {

            Image backgroundImg = ImageIO.read(new File("src/resource/story/выбор.png"))
                    .getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
            cashedChoice = backgroundImg;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Image cashedMonolog;

    static {
        try {
            String imagePath = "src/resource/story/Пифон.png";

            Image backgroundImg = ImageIO.read(new File(imagePath))
                    .getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
            cashedMonolog = backgroundImg;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Image cachedFirst;

    static {
        try {
            String imagePath = "src/resource/story/First.png";
            Image backgroundImg = ImageIO.read(new File(imagePath))
                    .getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
            cachedFirst = backgroundImg;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ImageIcon cachedManuForest;

    static {
        try {
            ImageIcon icon = new ImageIcon(new File("src/resource/MenuForest.png").getAbsolutePath());

            //картинка настройки
            Image scaledImage = icon.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            cachedManuForest = new ImageIcon(scaledIcon.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ImageIcon cachedMainBackground;

    static {
        try {
            BufferedImage img = ImageIO.read(new File("src/resource/MainBackground.png"));
            Image scaled = img.getScaledInstance(1920, 1080, Image.SCALE_FAST); // SCALE_FAST быстрее
            cachedMainBackground = new ImageIcon(scaled);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ImageIcon cachedRegistrationFon;

    static {
        try {
            BufferedImage img = ImageIO.read(new File("src/resource/RegistrationFon.png"));
            Image scaled = img.getScaledInstance(1920, 1080, Image.SCALE_FAST); // SCALE_FAST быстрее
            cachedRegistrationFon = new ImageIcon(scaled);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ImageIcon cachedSingInFon;

    static {
        try {
            BufferedImage img = ImageIO.read(new File("src/resource/SignIn.png"));
            Image scaled = img.getScaledInstance(1920, 1080, Image.SCALE_FAST); // SCALE_FAST быстрее
            cachedSingInFon = new ImageIcon(scaled);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ImageIcon cachedSettingFon;

    static {
        try {
            BufferedImage img = ImageIO.read(new File("src/resource/SettingFon.png"));
            Image scaled = img.getScaledInstance(1920, 1090, Image.SCALE_FAST);
            cachedSettingFon = new ImageIcon(scaled);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
