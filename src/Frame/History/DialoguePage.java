package Frame.History;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class DialoguePage extends JPanel {
    private int index = 0;

    public DialoguePage(Map<String, Object> node, StoryManager manager, Map<String, Object> storyData) {
        setLayout(new BorderLayout());

        List<Map<String, String>> lines = (List<Map<String, String>>) node.get("lines");
        JLabel dialogueLabel = new JLabel();
        dialogueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(dialogueLabel, BorderLayout.CENTER);

        JButton nextButton = new JButton("Далее");
        add(nextButton, BorderLayout.SOUTH);

        nextButton.addActionListener(e -> {
            if (index < lines.size()) {
                Map<String, String> line = lines.get(index++);
                dialogueLabel.setText("<html><b>" + line.get("name") + ":</b> " + line.get("text") + "</html>");
            } else {
                String next = (String) node.get("next");
                if (next != null) {
                    manager.showPage(next, storyData);
                }
            }
        });

        // Показать первую строку
        if (!lines.isEmpty()) {
            Map<String, String> line = lines.get(index++);
            dialogueLabel.setText("<html><b>" + line.get("name") + ":</b> " + line.get("text") + "</html>");
        }
    }
}
