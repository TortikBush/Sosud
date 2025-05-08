package Frame.History;

import HelpClasses.CustomFont;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static Frame.History.CreateButtonMenuOnHistory.CreateButton;
import static HelpClasses.CashedResource.cashedChoicePifon;
import static HelpClasses.CashedResource.cashedChoicePifonOnFlashLight;

public class ChoicePage extends JPanel {

    public ChoicePage(Map<String, Object> node, StoryManager manager, Map<String, Object> data) throws IOException, FontFormatException {
        setLayout(null);
        setPreferredSize(new Dimension(1920, 1080));

        // ==== Фон через кастомную панель ====
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                switch (node.get("image").toString()) {
                    case "Пифон":
                        g.drawImage(cashedChoicePifon, 0, 0, null);

                    case "ПифонСФонаремВыбор":
                        g.drawImage(cashedChoicePifonOnFlashLight, 0, 0, null);
                }
            }
        };

        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, 1920, 1080);
        add(backgroundPanel);

        if (!Objects.equals(node.get("achievement").toString(), "")) {
            JPanel panelPhoto = new JPanel();
            HelpClasses.ViewAchievement.ViewAchievement(panelPhoto, node.get("achievement").toString());
            backgroundPanel.add(panelPhoto, Integer.valueOf(1));
        }

        // ==== Верхние кнопки ====
        JPanel topButtons = new JPanel();
        topButtons = CreateButton(topButtons);

        backgroundPanel.add(topButtons);

        // ==== Панель выбора ====
        JPanel choicePanel = new JPanel();
        choicePanel.setLayout(new BoxLayout(choicePanel, BoxLayout.Y_AXIS));
        choicePanel.setBackground(new Color(0, 0, 0, 180));
        choicePanel.setBounds(620, 800, 1020, 280);
        choicePanel.setOpaque(false);
        choicePanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // ==== Заголовок ====
        JLabel questionLabel = new JLabel(node.get("text").toString());
        questionLabel.setForeground(Color.red);
        questionLabel.setFont(new Font("Serif", Font.BOLD, 40));
        questionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        choicePanel.add(questionLabel);
        choicePanel.add(Box.createVerticalStrut(20));

        // ==== Кнопки выбора ====
        List<Map<String, Object>> options = (List<Map<String, Object>>) node.get("options");
        for (Map<String, Object> choice : options) {
            String text = choice.get("text").toString();
            String next = choice.get("next").toString();

            JButton choiceButton = new JButton(text);
            choiceButton.setBackground(Color.black);
            choiceButton.setOpaque(false);
            choiceButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            choiceButton.setFont(CustomFont.CustomFont2().deriveFont(25f));
            choiceButton.setForeground(new Color(254, 222, 143));
            choiceButton.setMaximumSize(new Dimension(1000, 50));
            choiceButton.setFocusable(false);
            choiceButton.setBorderPainted(false);
            choiceButton.getModel().addChangeListener(e -> {
                ButtonModel model = choiceButton.getModel();
                if (model.isPressed()) {
                    choiceButton.setBackground(new Color(255, 200, 100));
                } else if (model.isRollover()) {
                    choiceButton.setBackground(new Color(255, 230, 160));
                } else {
                    choiceButton.setBackground(new Color(254, 222, 143));
                }
            });

            choiceButton.addActionListener(e -> {
                try {
                    manager.showPage(next, data);
                } catch (IOException | FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
            });

            choicePanel.add(choiceButton);
            choicePanel.add(Box.createVerticalStrut(10));
        }

        backgroundPanel.add(choicePanel);
    }
}