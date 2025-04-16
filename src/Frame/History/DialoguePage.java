package Frame.History;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static HelpClasses.CustomFont.CustomFont1;

public class DialoguePage extends JPanel {
    private int index = 0;

    public DialoguePage(Map<String, Object> node, StoryManager manager, Map<String, Object> storyData) throws IOException, FontFormatException {
        setLayout(new BorderLayout());

        List<Map<String, String>> lines = (List<Map<String, String>>) node.get("lines");
        JLabel dialogueLabel = new JLabel();
        dialogueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dialogueLabel.setFont(CustomFont1().deriveFont(20f));
        dialogueLabel.setForeground(new Color(254, 222, 143));
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
                    try {
                        manager.showPage(next, storyData);
                    } catch (IOException | FontFormatException ex) {
                        throw new RuntimeException(ex);
                    }
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
