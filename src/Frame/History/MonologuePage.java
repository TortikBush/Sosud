package Frame.History;

import HelpClasses.CustomFont;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import Frame.Main;

import static Frame.Main.parButton;
import static Frame.History.CreateButtonMenuOnHistory.CreateButton;
import static HelpClasses.CashedResource.cashedMonolog;

public class MonologuePage extends JPanel {

    public MonologuePage(Map<String, Object> node, StoryManager manager, Map<String, Object> data) throws IOException, FontFormatException {
        setLayout(null);
        setPreferredSize(new Dimension(1920, 1080));

        // === Фоновая панель ===
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(cashedMonolog, 0, 0, null);
            }
        };
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
        textPanel.setBounds(550, 840, 1000, 160);
        textPanel.setOpaque(false);

        JLabel textLabel = new JLabel("<html><div style='text-align: center;'>" + node.get("text").toString() + "</div></html>");
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setFont(CustomFont.CustomFont1().deriveFont(26f));
        textLabel.setForeground(Color.WHITE);

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
            if(node.get("next").toString().equals("Home")) {
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