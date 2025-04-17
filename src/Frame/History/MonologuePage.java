package Frame.History;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class MonologuePage extends JPanel {
    public MonologuePage(Map<String, Object> node, StoryManager manager, Map<String, Object> storyData) {
        setLayout(null);

        if (node.containsKey("image")) {
            String imagePath = "src/resource/" + node.get("image").toString();
            ImageIcon originalIcon = new ImageIcon(imagePath);

            Image scaled = originalIcon.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
            JLabel background = new JLabel(new ImageIcon(scaled));
            background.setBounds(0, 0, 1920, 1080);
            add(background);

            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setBounds(0, 0, 1920, 1080);

            JLabel textLabel = new JLabel("<html><div style='color:white; padding:30px; font-size:24px; background-color:rgba(0,0,0,0.5);'>" + node.get("text").toString() + "</div></html>");
            textLabel.setBounds(100, 50, 1720, 200);
            layeredPane.add(textLabel, JLayeredPane.PALETTE_LAYER);

            JButton nextButton = new JButton("Далее");
            nextButton.setFont(new Font("Arial", Font.BOLD, 20));
            nextButton.setBounds(860, 960, 200, 50);
            nextButton.addActionListener(e -> {
                String next = (String) node.get("next");
                if (next != null) {
                    try {
                        manager.showPage(next, storyData);
                    } catch (IOException | FontFormatException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            layeredPane.add(nextButton, JLayeredPane.PALETTE_LAYER);

            add(layeredPane);
        }
    }
}
