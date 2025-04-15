package Frame.History;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ChoicePage extends JPanel {
    public ChoicePage(Map<String, Object> node, StoryManager manager, Map<String, Object> storyData) {
        setLayout(new BorderLayout());

        JLabel questionLabel = new JLabel(node.get("question").toString(), SwingConstants.CENTER);
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(0, 1));
        Map<String, String> options = (Map<String, String>) node.get("options");

        for (Map.Entry<String, String> entry : options.entrySet()) {
            JButton button = new JButton(entry.getKey());
            button.addActionListener(e -> {
                manager.showPage(entry.getValue(), storyData);
            });
            optionsPanel.add(button);
        }

        add(optionsPanel, BorderLayout.CENTER);
    }
}
