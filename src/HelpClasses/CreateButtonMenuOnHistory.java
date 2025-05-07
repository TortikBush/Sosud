package HelpClasses;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import Frame.Setting;
import Frame.Main;
import Frame.Achievement;
import static Frame.Main.parButton;

public class CreateButtonMenuOnHistory extends Component {
    public static JPanel CreateButton(JPanel topButtons) {
        topButtons.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 10));
        topButtons.setOpaque(false);
        topButtons.setBounds(1468, 20, 400, 73);

        JButton btnSetting = new JButton();
        JButton btnSaving = new JButton();
        JButton btnAchievements = new JButton();
        JButton btnHome = new JButton();

        parButton(btnSetting);
        parButton(btnSaving);
        parButton(btnAchievements);
        parButton(btnHome);

        btnSetting.setPreferredSize(new Dimension(53, 55));
        btnSaving.setPreferredSize(new Dimension(50, 47));
        btnAchievements.setPreferredSize(new Dimension(53, 55));
        btnHome.setPreferredSize(new Dimension(74, 73));

        btnSetting.setFocusable(false);
        btnSetting.setOpaque(false);
        btnSetting.setFocusPainted(true);
        btnSetting.setContentAreaFilled(false);
        btnSetting.setBorderPainted(false);
        btnSetting.getModel().addChangeListener(e -> {
            ButtonModel model = btnSetting.getModel();
            if (model.isPressed()) {
                btnSetting.setOpaque(true);
                btnSetting.setBackground(new Color(255, 200, 100)); // Цвет при нажатии
            } else if (model.isRollover()) {
                btnSetting.setOpaque(false);

            } else {
                btnSetting.setOpaque(false);

            }
        });
        btnSetting.addActionListener(e -> {
            ClickArrow.playCachedMP3();
            try {
                new Setting();
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(topButtons);
                topFrame.dispose();
            } catch (SQLException | IOException | ClassNotFoundException | FontFormatException ex) {
                throw new RuntimeException(ex);
            }

        });

        btnSaving.setFocusable(false);
        btnSaving.setOpaque(false);
        btnSaving.setFocusPainted(true);
        btnSaving.setContentAreaFilled(false);
        btnSaving.setBorderPainted(false);
        btnSaving.getModel().addChangeListener(e -> {
            ButtonModel model = btnSaving.getModel();
            if (model.isPressed()) {
                btnSaving.setOpaque(true);
                btnSaving.setBackground(new Color(255, 200, 100)); // Цвет при нажатии
            } else if (model.isRollover()) {
                btnSaving.setOpaque(false);

            } else {
                btnSaving.setOpaque(false);
            }
        });

        btnAchievements.setFocusable(false);
        btnAchievements.setOpaque(false);
        btnAchievements.setFocusPainted(true);
        btnAchievements.setContentAreaFilled(false);
        btnAchievements.setBorderPainted(false);
        btnAchievements.getModel().addChangeListener(e -> {
            ButtonModel model = btnAchievements.getModel();
            if (model.isPressed()) {
                btnAchievements.setOpaque(true);
                btnAchievements.setBackground(new Color(255, 200, 100)); // Цвет при нажатии
            } else if (model.isRollover()) {
                btnAchievements.setOpaque(false);

            } else {
                btnAchievements.setOpaque(false);

            }
        });
        btnAchievements.addActionListener(e -> {
            ClickArrow.playCachedMP3();
            try {
                new Achievement();
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(topButtons);
                topFrame.dispose();
            } catch (SQLException | IOException | ClassNotFoundException | FontFormatException ex) {
                throw new RuntimeException(ex);
            }

        });

        btnHome.setFocusable(false);
        btnHome.setOpaque(false);
        btnHome.setContentAreaFilled(true);
        btnHome.setBorderPainted(false);

// Визуальное выделение при наведении и нажатии
        btnHome.getModel().addChangeListener(e -> {
            ButtonModel model = btnHome.getModel();
            if (model.isPressed()) {
                btnHome.setOpaque(true);
                btnHome.setBackground(new Color(255, 200, 100)); // Цвет при нажатии
            } else if (model.isRollover()) {
                btnHome.setOpaque(false);

            } else {
                btnHome.setOpaque(false);

            }
        });

// Обработка действия
        btnHome.addActionListener(e -> {
            ClickArrow.playCachedMP3();
            try {
                new Main();
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(btnHome);
                topFrame.dispose();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        topButtons.add(btnSetting);
        topButtons.add(Box.createHorizontalStrut(1));
        topButtons.add(btnSaving);
        topButtons.add(Box.createHorizontalStrut(0));
        topButtons.add(btnAchievements);
        topButtons.add(Box.createHorizontalStrut(2));
        topButtons.add(btnHome);
        return topButtons;
    }
}
