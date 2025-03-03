import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.StringWriter;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        JFrame frameMain = new JFrame();
        JPanel panelMainButton = new JPanel();


        //Главная картика
        ImageIcon originalIcon = new ImageIcon("C:\\Sosud\\forestMenu.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);


        //Кнопка Играть
        ImageIcon imageOk = new ImageIcon("C:\\Sosud\\Untitled-14.png");
        JButton btnStartGame = new JButton(imageOk);

        btnStartGame.setSize(100, 50);
        parButton(btnStartGame);
        //обработчик кнопки старта
        btnStartGame.addActionListener(e -> {
            JFrame frame2 = new JFrame();
            frame2.show();
            frameMain.dispose();

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
        panelMainButton.setSize(600, 450);
        panelMainButton.setOpaque(false);
        panelMainButton.setLayout(new GridLayout(0, 1, 5, 8));
        panelMainButton.add(btnStartGame);
        panelMainButton.add(btnSave);
        panelMainButton.add(btnSettings);
        panelMainButton.add(btnExit);
        panelMainButton.setLocation(1000, 600);

        //Работа с фреймом(Работа со стеной)

        frameMain.add(panelMainButton);
        frameMain.add(label);
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMain.setExtendedState(JFrame.MAXIMIZED_BOTH); // Максимизируем окно
        frameMain.setUndecorated(true);  // Убираем границы и заголовок окна
        frameMain.show();

    }

    public static JButton parButton(JButton button) {
        button.setBackground(Color.black);
        button.setOpaque(false);
        button.setFocusable(false);
        button.setBorderPainted(false);
        return button;
    }
}

