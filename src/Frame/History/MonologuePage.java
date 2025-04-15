package Frame.History;
// MonologuePage.java

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MonologuePage extends JPanel {
    public MonologuePage(Map<String, Object> node, StoryManager manager, Map<String, Object> storyData) {
        setLayout(new BorderLayout());

        JLabel textLabel = new JLabel("<html><div style='padding:10px;'>" + node.get("text").toString() + "</div></html>");
        add(textLabel, BorderLayout.CENTER);

        JButton nextButton = new JButton("Далее");
        nextButton.addActionListener(e -> {
            String next = (String) node.get("next");
            if (next != null) {
                manager.showPage(next, storyData);
            }
        });
        add(nextButton, BorderLayout.SOUTH);
    }
}
