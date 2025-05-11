package HelpClasses;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CashedResource {
    public static void CashedResource() {}
    //Кеширование Диалогов
    public static Image cashedDialogPifonAnte;

    static {
        try {
            Image backgroundImg = ImageIO.read(new File("src/resource/story/Dialog/Пифон+Анте.png"))
                    .getScaledInstance(1920, 1080, Image.SCALE_FAST);
            cashedDialogPifonAnte = backgroundImg;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Image cashedDialogPifonEhi;

    static {
        try {
            Image backgroundImg = ImageIO.read(new File("src/resource/story/Dialog/Пифон+Ехи.png"))
                    .getScaledInstance(1920, 1080, Image.SCALE_FAST);
            cashedDialogPifonEhi = backgroundImg;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Image getCashedDialogPifonEhiRead;

    static {
        try {
            Image backgroundImg = ImageIO.read(new File("src/resource/story/Dialog/Пифон+ЕхиЧитает.png"))
                    .getScaledInstance(1920, 1080, Image.SCALE_FAST);
            getCashedDialogPifonEhiRead = backgroundImg;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Image cashedDialogPifonLamiya;

    static {
        try {
            Image backgroundImg = ImageIO.read(new File("src/resource/story/Dialog/Пифон+Ламия.png"))
                    .getScaledInstance(1920, 1080, Image.SCALE_FAST);
            cashedDialogPifonLamiya = backgroundImg;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Image cashedDialogPifonOnFlashLightAnte;

    static {
        try {
            Image backgroundImg = ImageIO.read(new File("src/resource/story/Dialog/ПифонСФонарем+Анте.png"))
                    .getScaledInstance(1920, 1080, Image.SCALE_FAST);
            cashedDialogPifonOnFlashLightAnte = backgroundImg;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Кеширование Выборов
    public static Image cashedChoicePifon;

    static {
        try {
            Image backgroundImg = ImageIO.read(new File("src/resource/story/Choice/ПифонВыбор.png"))
                    .getScaledInstance(1920, 1080, Image.SCALE_FAST);
            cashedChoicePifon = backgroundImg;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Image cashedChoicePifonOnFlashLight;

    static {
        try {
            Image backgroundImg = ImageIO.read(new File("src/resource/story/Choice/ПифонСФонаремВыбор.png"))
                    .getScaledInstance(1920, 1080, Image.SCALE_FAST);
            cashedChoicePifonOnFlashLight = backgroundImg;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ImageIcon cachedChoiceFirst;

    static {
        try {
            String imagePath = "src/resource/story/Choice/First.png";
            Image backgroundImg = ImageIO.read(new File(imagePath))
                    .getScaledInstance(1920, 1080, Image.SCALE_FAST);
            cachedChoiceFirst = new ImageIcon(backgroundImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Кеширование Монологов

    public static Image cashedMonologLamiya;

    static {
        try {
            Image backgroundImg = ImageIO.read(new File("src/resource/story/Monolog/Ламия.png"))
                    .getScaledInstance(1920, 1080, Image.SCALE_FAST);
            cashedMonologLamiya = backgroundImg;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Image cashedMonologEhidna;

    static {
        try {
            Image backgroundImg = ImageIO.read(new File("src/resource/story/Monolog/Ехидна.png"))
                    .getScaledInstance(1920, 1080, Image.SCALE_FAST);
            cashedMonologEhidna = backgroundImg;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Image cashedMonologAnte;

    static {
        try {
            Image backgroundImg = ImageIO.read(new File("src/resource/story/Monolog/Анте.png"))
                    .getScaledInstance(1920, 1080, Image.SCALE_FAST);
            cashedMonologAnte = backgroundImg;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Image cashedMonologPifon;

    static {
        try {
            Image backgroundImg = ImageIO.read(new File("src/resource/story/Monolog/Пифон.png"))
                    .getScaledInstance(1920, 1080, Image.SCALE_FAST);
            cashedMonologPifon = backgroundImg;
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public static ImageIcon cachedManuForest;

    static {
        try {
            ImageIcon icon = new ImageIcon(new File("src/resource/MenuForest.png").getAbsolutePath());

            //картинка настройки
            Image scaledImage = icon.getImage().getScaledInstance(1920, 1080, Image.SCALE_FAST);
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
