import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws IOException {
        JFrame frameMain = new JFrame();
        JPanel panelMainButton = new JPanel();

        JFrame frame2 = new JFrame();
        JPanel panelMainButton2 = new JPanel();
        JTextField tf1;


//blah-blah
        tf1 = new JTextField();
        tf1.setSize(100, 40);


        //Главная картика
        ImageIcon originalIcon = new ImageIcon("C:\\Sosud\\forestMenu.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);
        //картинка настройки
        ImageIcon originalIcon1 = new ImageIcon("C:\\Sosud\\forestMenu.jpg");
        Image scaledImage1 = originalIcon1.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage1);
        JLabel label1 = new JLabel(scaledIcon2);


        //Кнопка Играть
        JButton btnStartGame = new JButton();
        btnStartGame.setSize(650, 100);
        File imgStartGame = new File("C:\\Sosud\\StartGame1.png");
        Image imgReadStartGame = ImageIO.read(imgStartGame);
        Image newImg = imgReadStartGame.getScaledInstance((int) btnStartGame.getSize().getWidth(), (int) btnStartGame.getSize().getHeight(), java.awt.Image.SCALE_SMOOTH);
        btnStartGame.setIcon(new ImageIcon(newImg));
        parButton(btnStartGame);
        //обработчик кнопки старта
        btnStartGame.addActionListener(e -> {


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
        panelMainButton.setSize(664, 400);
        panelMainButton.setOpaque(false);
        panelMainButton.setLayout(new GridLayout(0, 1, 2, 4));
        panelMainButton.add(btnStartGame);
        panelMainButton.add(btnSave);
        panelMainButton.add(btnSettings);
        panelMainButton.add(btnExit);
        panelMainButton.setLocation(1000, 600);

        panelMainButton2.add(tf1); //добавляем 1-е поле


        //Работа с фреймом(Работа со стеной)

        frameMain.add(panelMainButton);
        frameMain.add(label);
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMain.setExtendedState(JFrame.MAXIMIZED_BOTH); // Максимизируем окно
        frameMain.setUndecorated(true);// Убираем границы и заголовок окна
        frame2.add(panelMainButton2);
        frame2.add(label1);

        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame2.setUndecorated(true);
        frame2.add(tf1);
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

