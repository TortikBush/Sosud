package Frame.History;

import HelpClasses.CustomFont;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static Frame.History.CreateButtonMenuOnHistory.CreateButton;
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
        JButton nextButton = new JButton(">>>");
        nextButton.setBounds(1750, 900, 100, 50);
        nextButton.setFont(CustomFont.CustomFont1().deriveFont(20f));
        nextButton.setFocusPainted(false);
        nextButton.setContentAreaFilled(false);
        nextButton.setBorderPainted(false);
        nextButton.setForeground(new Color(254, 222, 143));

        nextButton.addActionListener(e -> {
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