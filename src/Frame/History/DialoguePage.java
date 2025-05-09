package Frame.History;

import HelpClasses.CustomFont;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;
import Frame.Main;

import static Frame.History.CreateButtonMenuOnHistory.CreateButton;
import static Frame.Main.parButton;
import static HelpClasses.CashedResource.*;

public class DialoguePage extends JPanel {

    public DialoguePage(Map<String, Object> node, StoryManager manager, Map<String, Object> data) throws IOException, FontFormatException {
        setLayout(null);
        setPreferredSize(new Dimension(1920, 1080));

        // === Фон ===
        Image background = null;
        switch (node.get("image").toString()) {
            case "Пифон+Ламия":
                background = cashedDialogPifonLamiya;
                break;
            case "Пифон+Ехи":
                background = cashedDialogPifonEhi;
                break;
            case "Пифон+Анте":
                background = cashedDialogPifonAnte;
                break;
            case "Пифон+ЕхиЧитает":
                background = getCashedDialogPifonEhiRead;
                break;
            case "ПифонСФонарем+Анте":
                background = cashedDialogPifonOnFlashLightAnte;
                break;
        }

        Image finalBackground = background;
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(finalBackground, 0, 0, 1920, 1080, this);
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, 1920, 1080);
        add(backgroundPanel);

        // === Верхние кнопки ===
        JPanel topButtons = CreateButton(new JPanel());
        backgroundPanel.add(topButtons);

        // === Панель диалога ===
        JPanel dialogPanel = new JPanel(null);
        dialogPanel.setBounds(200, 870, 1520, 170);
        dialogPanel.setOpaque(false);

        if (!Objects.equals(node.get("achievement").toString(), "")) {
            JPanel panelPhoto = new JPanel();
            HelpClasses.ViewAchievement.ViewAchievement(panelPhoto, node.get("achievement").toString());
            backgroundPanel.add(panelPhoto, Integer.valueOf(1));
        }
        JLabel leftText = new JLabel();
        JLabel rightText = new JLabel();


        leftText.setFont(CustomFont.CustomFont2().deriveFont(25f));
        leftText.setForeground(new Color(254, 222, 143));
        leftText.setBounds(60, 50, 700, 200);
        leftText.setVerticalAlignment(SwingConstants.TOP);
        leftText.setHorizontalAlignment(SwingConstants.LEFT);


        rightText.setFont(CustomFont.CustomFont2().deriveFont(25f));
        rightText.setForeground(new Color(254, 222, 143));
        rightText.setBounds(700, 0, 700, 200);
        rightText.setVerticalAlignment(SwingConstants.TOP);
        rightText.setHorizontalAlignment(SwingConstants.RIGHT);

        String who = node.get("who").toString();
        String text = node.get("text").toString();

        if ("left".equalsIgnoreCase(who)) {
            leftText.setText("<html><div style='text-align: center;'>" + text + "</div></html>");
        } else {
            rightText.setText("<html><div style='text-align: center;'>" + text + "</div></html>");
        }

        dialogPanel.add(leftText);
        dialogPanel.add(rightText);
        backgroundPanel.add(dialogPanel);

        // === Кнопка "далее" ===
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