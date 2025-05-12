package Frame.History;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static Frame.Main.MusicEnable;
import static Frame.Main.parButton;
import static HelpClasses.CashedResource.cachedChoiceFirst;

public class FirstPages extends JPanel {
    public FirstPages(Map<String, Object> node, StoryManager manager, Map<String, Object> storyData) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1920, 1080));

        // JLayeredPane: фон + интерфейс
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1920, 1080));
        // 1. Фон

        JLabel background = new JLabel(cachedChoiceFirst);
        background.setBounds(0, 0, 1920, 1080);
        // слой 0 — фон
        layeredPane.add(background, Integer.valueOf(0));
        MusicEnable("src/resource/story/" + node.get("music").toString());
        if (!Objects.equals(node.get("achievement").toString(), "")) {
            JPanel panelPhoto = new JPanel();
            HelpClasses.ViewAchievement.ViewAchievement(panelPhoto, node.get("achievement").toString());
            layeredPane.add(panelPhoto, Integer.valueOf(1));
        }
        // 3. Кнопки выбора
        int buttonY = 100;

        Map<String, String> options = (Map<String, String>) node.get("options");
        for (Map.Entry<String, String> entry : options.entrySet()) {
            JButton button = new JButton();
            button.setBounds(710, buttonY, 500, 900);
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


