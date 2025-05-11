package Frame.History;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

/**
 * A panel that displays different hero states/images that the user can cycle through by clicking.
 * After viewing all states, a "Next" button appears to proceed in the story.
 */
public class HeroViewPage extends JPanel {
    /** Label to display the current hero image */
    private JLabel heroLabel;
    /** Array of different hero state images */
    private ImageIcon[] heroStates;
    /** Index of the currently displayed hero state */
    private int currentIndex = 0;

    /**
     * Constructor for creating a hero view page
     * 
     * @param node The node containing information about the current page (next page, etc.)
     * @param manager The StoryManager instance to handle navigation between pages
     * @param data Additional data needed for the story progression
     */
    public HeroViewPage(Map<String, Object> node, StoryManager manager, Map<String, Object> data) {
        setLayout(null);
        setPreferredSize(new Dimension(1920, 1080));

        // === Загрузка изображений ===
        heroStates = new ImageIcon[]{
                new ImageIcon("src/resource/Story/HeroView/BG-02(Без кнопки выбрать).png"),
                new ImageIcon("src/resource/Story/HeroView/Дух.png"),
                new ImageIcon("src/resource/Story/HeroView/Тень.png"),
                new ImageIcon("src/resource/Story/HeroView/Проводник.png"),
                new ImageIcon("src/resource/Story/HeroView/Тело.png")
        };

        // Масштабируем под Full HD
        for (int i = 0; i < heroStates.length; i++) {
            Image img = heroStates[i].getImage().getScaledInstance(1920, 1080, Image.SCALE_FAST);
            heroStates[i] = new ImageIcon(img);
        }

        // === Создаем слоистую панель ===
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1920, 1080);

        // === Метка с изображением героя ===
        heroLabel = new JLabel(heroStates[currentIndex]);
        heroLabel.setBounds(0, 0, 1920, 1080);
        layeredPane.add(heroLabel, Integer.valueOf(0)); // нижний слой

        // === Кнопка "Далее" ===
        JButton nextButton = new JButton("Далее");
        nextButton.setBounds(1750, 950, 120, 60);
        nextButton.setForeground(new Color(254, 222, 143));
        nextButton.setFont(new Font("Serif", Font.BOLD, 26));
        nextButton.setContentAreaFilled(false);
        nextButton.setBorderPainted(false);
        nextButton.setVisible(false); // изначально скрыта

        nextButton.addActionListener(e -> {
            try {
                String next = node.get("next").toString();
                manager.showPage(next, data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        layeredPane.add(nextButton, Integer.valueOf(1)); // верхний слой

        // === Обработка кликов ===
        heroLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentIndex = (currentIndex + 1) % heroStates.length;
                heroLabel.setIcon(heroStates[currentIndex]);
                if (currentIndex == heroStates.length - 1) {
                    nextButton.setVisible(true);
                }
            }
        });

        add(layeredPane);
    }
}
