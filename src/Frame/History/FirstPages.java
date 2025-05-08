package Frame.History;

import HelpClasses.FadeImagePanel;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;
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

        JLabel background = new JLabel(new ImageIcon(cachedChoiceFirst));
        background.setBounds(0, 0, 1920, 1080);
        // слой 0 — фон
        layeredPane.add(background, Integer.valueOf(0));
        MusicEnable("src/resource/story/music_bg.wav");

        JPanel panelPhoto = new JPanel();
        panelPhoto.setBounds(540, 0, 889, 500);
        // 2. Фото с fade-in
        panelPhoto.setOpaque(false);
        ImageIcon icon = new ImageIcon("src/Resource/Story/Достижение.png"); // Ваше изображение
        FadeImagePanel fadeImage = new FadeImagePanel(icon.getImage());
        fadeImage.setBounds(0, 0, 889, 500);
        panelPhoto.setLayout(null);
        panelPhoto.add(fadeImage);
        layeredPane.add(panelPhoto, Integer.valueOf(1));

// Запуск анимации
        fadeImage.fadeIn(1500); // 1000 мс = 1 секунда


        new Timer(2500, e -> {
            fadeImage.fadeOut(1500, () -> {
                panelPhoto.setVisible(false); // Скрыть панель после анимации
            });
        }).start();


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


