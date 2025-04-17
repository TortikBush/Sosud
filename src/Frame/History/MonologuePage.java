package Frame.History;

import HelpClasses.CustomFont;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class MonologuePage extends JPanel {
    public MonologuePage(Map<String, Object> node, StoryManager manager, Map<String, Object> storyData) throws IOException, FontFormatException {
        setLayout(new BorderLayout());

        JLabel textLabel = new JLabel("<html><div style='padding:10px;'>" + node.get("text").toString() + "</div></html>");
        textLabel.setFont(CustomFont.CustomFont1().deriveFont(20f));
        textLabel.setForeground(Color.RED);

        add(textLabel, BorderLayout.CENTER);
        JButton nextButton = new JButton("Далее");
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

        add(nextButton, BorderLayout.SOUTH);
    }
}
