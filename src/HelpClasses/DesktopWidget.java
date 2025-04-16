package HelpClasses;

import javax.swing.*;

public class DesktopWidget {
    private static JFrame frame = new JFrame("Виджет");
    public static void Desktop() {
        // Создаём фрейм (окошко)

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 100);
        frame.setAlwaysOnTop(true); // Всегда поверх окон
        frame.setUndecorated(true); // Без рамок, как настоящий виджет
        frame.setLocationRelativeTo(null); // Центр экрана

        // Добавим простую надпись
        JLabel label = new JLabel("Привет с виджета!", SwingConstants.CENTER);
        frame.add(label);

        // Показываем
        frame.setVisible(true);
    }
    public static void Close() {
        frame.setVisible(false);
    }
}
