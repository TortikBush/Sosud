import javax.swing.*;
import java.awt.*;
public class Main {
    public static void main(String[] args) {
        new ImageExample();



    }
}
 class ImageExample extends JFrame {

    public ImageExample() {

            // Загружаем изображение
            ImageIcon originalIcon = new ImageIcon("C:\\Sosud\\IMG_2567.PNG");

            // Изменяем размер изображения
            Image scaledImage = originalIcon.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // Создаем JLabel с измененным изображением
            JLabel label = new JLabel(scaledIcon);




        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Full Screen Window");
            frame.add(label);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Максимизируем окно
            frame.setUndecorated(true);  // Убираем границы и заголовок окна
            frame.setVisible(true);
        });




    }
}
