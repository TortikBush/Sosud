package Frame.History;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Random;

import Frame.Setting;
import Frame.Main;
import Frame.Saves;
import Frame.Achievement;
import HelpClasses.ClickArrow;
import HelpClasses.Users;

import static Frame.Main.parButton;
import static Frame.Saves.createNewSave;

public class CreateButtonMenuOnHistory extends Component {
    public static JPanel CreateButton(JPanel topButtons, Map<String, Object> node) {
        Random random = new Random();
        int randomNumber = random.nextInt(9999999);
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

        buttonView(btnSetting);
        btnSetting.addActionListener(e -> {

            ClickArrow.playCachedMP3();
            try {
                new Setting();
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(topButtons);
                Users.SavePoint = node.get("point").toString();
                topFrame.dispose();
            } catch (SQLException | IOException | ClassNotFoundException | FontFormatException ex) {
                throw new RuntimeException(ex);
            }
        });

        buttonView(btnSaving);
        btnSaving.addActionListener(e -> {
            ClickArrow.playCachedMP3();
            try {
                new Saves();
            } catch (IOException | FontFormatException ex) {
                throw new RuntimeException(ex);
            }
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(topButtons);
            Users.SavePoint = node.get("point").toString();
            topFrame.dispose();
        });

        buttonView(btnAchievements);
        btnAchievements.addActionListener(e -> {
            ClickArrow.playCachedMP3();
            try {
                new Achievement();
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(topButtons);
                Users.SavePoint = node.get("point").toString();
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
                createNewSave("Сохранение " + randomNumber,node.get("point").toString());
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

    private static void buttonView(JButton btnSaving) {
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
    }
}
