package Frame.History;

import HelpClasses.CustomFont;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;
import static Frame.History.CreateButtonMenuOnHistory.CreateButton;
import static HelpClasses.CashedResource.*;

public class DialoguePage extends JPanel {

    public DialoguePage(Map<String, Object> node, StoryManager manager, Map<String, Object> data) throws IOException, FontFormatException {
        setLayout(null);
        setPreferredSize(new Dimension(1920, 1080));

        // === Фон ===
        Image background = null;

        switch (node.get("image").toString()) {
            case "Пифон":
                background = cashedMonologPifon;
                break;
            case "Анте":
                background = cashedMonologAnte;
                break;
            case "Ламия":
                background = cashedMonologLamiya;
                break;
            case "Ехидна":
                background = cashedMonologEhidna;
                break;
            case "MainBackground":
                background = cachedMainBackground.getImage();
        }

        Image finalBackground = background;
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(finalBackground, 0, 0, 1920, 1080, this);
                super.paintComponent(g);
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, 1920, 1080);
        add(backgroundPanel);

        // === Верхние кнопки ===
        JPanel topButtons = CreateButton(new JPanel());
        backgroundPanel.add(topButtons);

        // === Диалоговая панель ===
        JPanel dialogPanel = new JPanel(null);
        dialogPanel.setBounds(200, 800, 1520, 200);
        dialogPanel.setOpaque(false);

        // Левый и правый блок текста
        JLabel leftText = new JLabel();
        JLabel rightText = new JLabel();

        leftText.setFont(CustomFont.CustomFont1().deriveFont(24f));
        leftText.setForeground(Color.WHITE);
        leftText.setBounds(0, 0, 700, 200);
        leftText.setVerticalAlignment(SwingConstants.TOP);

        rightText.setFont(CustomFont.CustomFont1().deriveFont(24f));
        rightText.setForeground(Color.WHITE);
        rightText.setBounds(820, 0, 700, 200);
        rightText.setHorizontalAlignment(SwingConstants.RIGHT);
        rightText.setVerticalAlignment(SwingConstants.TOP);

        String who = node.get("who").toString();
        String text = node.get("text").toString();

        if ("left".equalsIgnoreCase(who)) {
            leftText.setText("<html><div style='padding: 10px;'>" + text + "</div></html>");
        } else {
            rightText.setText("<html><div style='padding: 10px; text-align: right;'>" + text + "</div></html>");
        }

        dialogPanel.add(leftText);
        dialogPanel.add(rightText);
        backgroundPanel.add(dialogPanel);

        // === Кнопка "вперёд" ===
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