import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;


import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class frame2 {
    public static void WorkFrame() {
        JFrame frame = new JFrame();

        JPanel panelBlockTxt = new JPanel();
        ImageIcon originalIcon1 = new ImageIcon("C:\\Sosud\\forestMenu.jpg");
        Image scaledImage1 = originalIcon1.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage1);
        JLabel label = new JLabel(scaledIcon2);

        JTextField Field1 = new JTextField(15);
        Field1.setSize(100, 500);
        Field1.setBackground(Color.black);
        Field1.setOpaque(false);
        Field1.setDisabledTextColor(Color.RED);


        JTextField Field2 = new JTextField(25);
        Field2.setSize(300, 500);
        Field2.setBackground(Color.black);
        Field2.setOpaque(false);


        panelBlockTxt.setSize(300, 100);

        panelBlockTxt.setOpaque(false);
        panelBlockTxt.add(Field1);
        panelBlockTxt.add(Field2);
        panelBlockTxt.setLocation(700, 500);

        frame.add(panelBlockTxt);
        frame.add(label);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        frame.show();
    }


}
