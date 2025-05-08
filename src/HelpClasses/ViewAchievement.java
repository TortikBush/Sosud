package HelpClasses;

import javax.swing.*;

public class ViewAchievement {
    public static JPanel ViewAchievement(JPanel panelPhoto, String achievementImage) {
        panelPhoto.setBounds(540, 0, 889, 500);
        // 2. Фото с fade-in
        panelPhoto.setOpaque(false);
        ImageIcon icon = new ImageIcon("src/Resource/Story/Achievement/" + achievementImage); // Ваше изображение
        FadeImagePanel fadeImage = new FadeImagePanel(icon.getImage());
        fadeImage.setBounds(0, 0, 889, 500);
        panelPhoto.setLayout(null);
        panelPhoto.add(fadeImage);

// Запуск анимации
        fadeImage.fadeIn(2500); // 1000 мс = 1 секунда
        new Timer(2500, e -> {
            fadeImage.fadeOut(1500, () -> {
                panelPhoto.setVisible(false); // Скрыть панель после анимации
            });
        }).start();
        return panelPhoto;
    }

}
