import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        //Главная картика
        ImageIcon originalIcon = new ImageIcon("C:\\Sosud\\IMG_2567.PNG");
        Image scaledImage = originalIcon.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);

        //Кнопка Играть
        JButton btnStartGame = new JButton("Начать игру");
        btnStartGame.setSize(100, 50);
        parButton(btnStartGame);
        //обработчик кнопки старта
        btnStartGame.addActionListener(e -> {
            System.exit(0);
        });

        //Кнопка Сохранение
        JButton btnSave = new JButton("Сохранение");
        btnSave.setSize(100, 50);
        parButton(btnSave);
        //обработчик кнопки старта
        btnSave.addActionListener(e -> {
            System.exit(0);
        });

        //Кнопка Настройки
        JButton btnSettings = new JButton("Настройки");
        btnSettings.setSize(100, 50);
        parButton(btnSettings);
        //обработчик кнопки старта
        btnSettings.addActionListener(e -> {
            System.exit(0);
        });

        //Кнопка Выход
        JButton btnExit = new JButton("Выход");
        btnExit.setSize(100, 50);
        parButton(btnExit);
        btnExit.addActionListener(e -> {
            System.exit(0);
        });

        //Работа с панелью(Работа с гвоздями и картиной)
        panel.setSize(110, 100);
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(0, 1, 5, 8));
        panel.add(btnStartGame);
        panel.add(btnSave);
        panel.add(btnSettings);
        panel.add(btnExit);
        panel.setLocation(1300, 750);

        //Работа с фреймом(Работа со стеной)
        frame.setSize(69, 30);
        frame.add(panel);
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Максимизируем окно
        frame.setUndecorated(true);  // Убираем границы и заголовок окна
        frame.show();

    }

    public static JButton parButton(JButton button) {
        button.setBackground(Color.black);
        button.setOpaque(false);
        button.setFocusable(false);
        button.setBorderPainted(false);
        return button;
    }
}

