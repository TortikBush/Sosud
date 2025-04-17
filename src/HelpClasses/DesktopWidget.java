package HelpClasses;

import Frame.Registration;

import javax.swing.*;
import java.awt.geom.RoundRectangle2D;


public class DesktopWidget {
    private static JFrame frame = new JFrame("Виджет");

    public static void Desktop() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true); // Всегда поверх окон
        frame.setUndecorated(true); // Без рамок, как настоящий виджет
        frame.setLocationRelativeTo(null); // Центр экрана

        ImageIcon imageIcon = new ImageIcon("src/resource/Widget.png"); // или "C:/путь/картинка.png"
        JLabel label = new JLabel(imageIcon);
        frame.add(label);
        frame.setShape(new RoundRectangle2D.Double(5, 6, 810, 400, 58, 58));

        int[] centerLocation1 = Registration.CenterLocationObject(frame);
        frame.setLocation(centerLocation1[0] - 360, centerLocation1[1] - 200);
        frame.setSize(820, 400);

        frame.setVisible(true);
    }

    public static void Close() {
        frame.setVisible(false);
    }
}
