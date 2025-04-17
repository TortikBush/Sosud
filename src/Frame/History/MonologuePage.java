package Frame.History;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

import static Frame.Main.parButton;

public class MonologuePage extends JPanel {
    public MonologuePage(Map<String, Object> node, StoryManager manager, Map<String, Object> storyData) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1920, 1080));
        JLabel background = new JLabel();
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1920, 1080));
        if (node.get("image") != null) {
            String imagePath = "src/resource/" + node.get("image").toString();
            ImageIcon originalIcon = new ImageIcon(imagePath);
            Image scaled = originalIcon.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
            background.setIcon(new ImageIcon(scaled));
        }

        background.setBounds(0, 0, 1920, 1080);
        layeredPane.add(background, Integer.valueOf(0));  // слой 0 — фон

        JLabel textLabel = new JLabel("<html><div style='padding:30px; color:white; font-size:28px; background-color: rgba(0,0,0,0.5); border-radius:10px;'>"
                + node.get("text").toString() + "</div></html>");
        textLabel.setBounds(100, 50, 1720, 200);
        layeredPane.add(textLabel, Integer.valueOf(1));

        JButton nextButton = new JButton("Далее");
        nextButton.setFont(new Font("Arial", Font.BOLD, 24));
        nextButton.setBounds(860, 960, 200, 50);
        parButton(nextButton);

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
        layeredPane.add(nextButton, Integer.valueOf(1));

        add(layeredPane, BorderLayout.CENTER);
    }
}
