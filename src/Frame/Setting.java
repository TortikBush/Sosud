package Frame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import static Frame.Main.cachedMainBackground;

public class Setting {
    public Setting() throws IOException, SQLException, ClassNotFoundException {
        // Создание фрейма
        JFrame frameSetting = new JFrame();
        frameSetting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSetting.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panelBlockSettings = new JPanel();
        panelBlockSettings.setLayout(new BoxLayout(panelBlockSettings, BoxLayout.Y_AXIS));


        JCheckBox checkbox = new JCheckBox("Music");
        checkbox.setFont(new Font("Arial", Font.BOLD, 24));
        checkbox.setPreferredSize(new Dimension(250, 50));
        checkbox.setMargin(new Insets(10, 10, 10, 10));
        checkbox.setSize(100,100);
        frameSetting.add(checkbox);

        JLabel label = new JLabel(cachedMainBackground);
        frameSetting.add(label);
        frameSetting.setUndecorated(true);
        frameSetting.setVisible(true);
    }
}
