package Frame.History;

import HelpClasses.CustomFont;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class ChoicePage extends JPanel {
    public ChoicePage(Map<String, Object> node, StoryManager manager, Map<String, Object> storyData) throws IOException, FontFormatException {
        setLayout(new BorderLayout());

        JLabel questionLabel = new JLabel(node.get("question").toString(), SwingConstants.CENTER);
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(0, 1));
        Map<String, String> options = (Map<String, String>) node.get("options");

        for (Map.Entry<String, String> entry : options.entrySet()) {
            JButton button = new JButton(entry.getKey());
            button.setFont(CustomFont.CustomFont1().deriveFont(20f));
            button.setForeground(new Color(254, 222, 143));
            button.addActionListener(e -> {
                try {
                    manager.showPage(entry.getValue(), storyData);
                } catch (IOException | FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
            });
            optionsPanel.add(button);
        }

        add(optionsPanel, BorderLayout.CENTER);
    }
}
