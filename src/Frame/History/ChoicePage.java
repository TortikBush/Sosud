package Frame.History;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static Frame.Main.parButton;

public class ChoicePage extends JPanel {
    public ChoicePage(Map<String, Object> node, StoryManager manager, Map<String, Object> storyData) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1920, 1080));

        // JLayeredPane: фон + интерфейс
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1920, 1080));
        // 1. Фон

        String imagePath = "src/resource/story/" + node.get("image").toString();
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image scaled = originalIcon.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(scaled));
        background.setBounds(0, 0, 1920, 1080);
        layeredPane.add(background, Integer.valueOf(0));  // слой 0 — фон

        // 3. Кнопки выбора
        int buttonY = 100;

        Map<String, String> options = (Map<String, String>) node.get("options");
        for (Map.Entry<String, String> entry : options.entrySet()) {
            JButton button = new JButton();
            button.setBounds(730, buttonY, 480, 840);
            button.setFocusPainted(true);

            button.setContentAreaFilled(false);
            button.setOpaque(false);
            parButton(button);
            button.setFocusable(true);
            button.setBorderPainted(true);
            buttonY += 30;
            button.addActionListener(e -> {
                button.transferFocus();
                try {
                    manager.showPage(entry.getValue(), storyData);
                } catch (IOException | FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
            });
            layeredPane.add(button, Integer.valueOf(1));
        }

        add(layeredPane, BorderLayout.CENTER);
    }
}