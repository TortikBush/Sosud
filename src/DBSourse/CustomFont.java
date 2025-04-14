package DBSourse;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CustomFont {


    public static Font CustomFont1() throws IOException, FontFormatException {
        Font customFont1 = Font.createFont(Font.TRUETYPE_FONT, new File("src/resource/fonts/font1.ttf")); // размер и стиль
        GraphicsEnvironment ge1 = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge1.registerFont(customFont1);
        return customFont1;
    }

    public static Font CustomFont2() throws IOException, FontFormatException {
        Font customFont2 = Font.createFont(Font.TRUETYPE_FONT, new File("src/resource/fonts/font2.ttf")); // размер и стиль
        GraphicsEnvironment ge2 = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge2.registerFont(customFont2);
        return customFont2;
    }

}
