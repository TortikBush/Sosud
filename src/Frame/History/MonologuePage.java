package Frame.History;

import HelpClasses.CustomFont;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

import Frame.Main;

import static Frame.Main.parButton;
import static Frame.History.CreateButtonMenuOnHistory.CreateButton;
import static HelpClasses.CashedResource.*;

public class MonologuePage extends JPanel {

    public MonologuePage(Map<String, Object> node, StoryManager manager, Map<String, Object> data) throws IOException, FontFormatException {
        setLayout(null);
        setPreferredSize(new Dimension(1920, 1080));

        Image image = null;
        switch (node.get("image").toString()) {
            case "Пифон":
                image = cashedMonologPifon;
                break;
            case "Анте":
                image = cashedMonologAnte;
                break;
            case "Ламия":
                image = cashedMonologLamiya;
                break;
            case "Ехидна":
                image = cashedMonologEhidna;
                break;
            case "MainBackground":
                image = cachedMainBackground.getImage();
        }

        // === Фоновая панель ===
        Image finalImage = image;
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Сначала вызов super
                g.drawImage(finalImage, 0, 0, this); // Потом отрисовка изображения
            }
        };

        if (!Objects.equals(node.get("achievement").toString(), "")) {
            JPanel panelPhoto = new JPanel();
            HelpClasses.ViewAchievement.ViewAchievement(panelPhoto, node.get("achievement").toString());
            backgroundPanel.add(panelPhoto, Integer.valueOf(1));
        }

        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, 1920, 1080);
        add(backgroundPanel);

        // === Верхние кнопки ===
        JPanel topButtons = CreateButton(new JPanel());
        backgroundPanel.add(topButtons);

        // === Панель текста монолога ===
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.setBackground(new Color(0, 0, 0, 180));
        textPanel.setBounds(540, 830, 1000, 160);
        textPanel.setOpaque(false);

        JLabel textLabel = new JLabel("<html><div style='text-align: center;'>" + node.get("text").toString() + "</div></html>");
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setFont(CustomFont.CustomFont2().deriveFont(27f));
        textLabel.setForeground(new Color(254, 222, 143));

        textPanel.add(textLabel, BorderLayout.CENTER);

        backgroundPanel.add(textPanel);

        // === Кнопка "вперёд" справа снизу ===
        JButton nextButton = new JButton();
        nextButton.setBounds(1750, 810, 140, 80);
        parButton(nextButton);
        nextButton.setFocusable(false);
        nextButton.setOpaque(false);
        nextButton.setContentAreaFilled(true);
        nextButton.setBorderPainted(false);
        nextButton.getModel().addChangeListener(e -> {
            ButtonModel model = nextButton.getModel();
            if (model.isPressed()) {
                nextButton.setOpaque(true);
                nextButton.setBackground(new Color(255, 200, 100)); // Цвет при нажатии
            } else if (model.isRollover()) {
                nextButton.setOpaque(false);
            } else {
                nextButton.setOpaque(false);
            }
        });

        nextButton.addActionListener(e -> {
            if (node.get("next").toString().equals("Home")) {
                try {
                    new Main();
                } catch (IOException | SQLException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                topFrame.dispose();
                return;
            }
            String next = node.get("next").toString();
            try {
                manager.showPage(next, data);
            } catch (IOException | FontFormatException ex) {
                throw new RuntimeException(ex);
            }
        });

        backgroundPanel.add(nextButton);
    }
}