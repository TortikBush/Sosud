package Frame;
import DBSourse.JDBCPosgreSQLConnection;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import static Frame.Main.parButton;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Registration {
    public static void WorkFrame() throws IOException, SQLException, ClassNotFoundException {
        JFrame frame = new JFrame();

        JPanel panelBlock = new JPanel();
        ImageIcon icon = new ImageIcon(new File("src/Source/forestMenu.png").getAbsolutePath());
        Image scaledImage1 = icon.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage1);
        JLabel label = new JLabel(scaledIcon2);
        int[] centerLocation = CenterLocationObject(frame);
        label.setBounds(centerLocation[0], centerLocation[1], 1920, 1080);

        JTextField Field1 = new JTextField(15);
        Field1.setSize(100, 500);
        Field1.setBackground(Color.black);
        Field1.setForeground(Color.WHITE);
        Field1.setOpaque(false);
        Field1.setDisabledTextColor(Color.RED);

        JTextField Field2 = new JTextField(25);
        Field2.setSize(300, 500);
        Field2.setBackground(Color.black);
        Field2.setForeground(Color.WHITE);
        Field2.setOpaque(false);

        JButton btnRegistration = new JButton("Регистрация");
        btnRegistration.setSize(100, 30);
        btnRegistration.setBackground(Color.black);
        btnRegistration.setForeground(Color.WHITE);
        btnRegistration.setOpaque(false);
        parButton(btnRegistration);

        btnRegistration.addActionListener(e -> {
            try {
                Connection connection = JDBCPosgreSQLConnection.OpenConection();
                String sql = "INSERT INTO users (login, password, idrole) VALUES (?, ?, 2)";
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, Field1.getText());
                stmt.setString(2, Field2.getText());

                ResultSet rs = stmt.executeQuery();
                System.out.println(rs.next());

            } catch (SQLException ex) {
                if (ex.getErrorCode() == 0) {
                    return;
                }
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        });

        panelBlock.setSize(300, 90);
        panelBlock.setOpaque(false);
        panelBlock.add(Field1);
        centerLocation = CenterLocationObject(frame);
        panelBlock.setLocation(centerLocation[0] - 50, centerLocation[1]);
        panelBlock.add(Field2);
        panelBlock.add(btnRegistration);

        frame.add(panelBlock);
        frame.add(label);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        frame.show();
    }


    public static int[] CenterLocationObject(Frame frame) {
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int w = frame.getSize().width;
        int h = frame.getSize().height;
        int x = (screenDimension.width - w) / 2;
        int y = (screenDimension.height - h) / 2;
        int[] centerLocation = new int[2];
        centerLocation[0] = x;
        centerLocation[1] = y;
        return centerLocation;
    }


}
