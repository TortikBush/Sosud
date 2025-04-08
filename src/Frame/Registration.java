package Frame;
import DBSourse.JDBCPosgreSQLConnection;
import DBSourse.UsersTable;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import static Frame.Main.MainFrain;
import static Frame.Main.parButton;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class Registration {
    public static void RegistrationFraim() throws IOException, SQLException, ClassNotFoundException {
        JFrame frame = new JFrame();

        JPanel panelBlock = new JPanel();
        ImageIcon icon = new ImageIcon(new File("src/resource/forestMenu.png").getAbsolutePath());
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

        JTextArea Field3 = new JTextArea();
        Field3.setSize(300, 30);
        Field3.setOpaque(false);
        Field3.setForeground(Color.RED);

        JButton btnRegistration = new JButton("Регистрация");
        btnRegistration.setSize(100, 30);
        btnRegistration.setForeground(Color.WHITE);
        btnRegistration.setOpaque(false);
        parButton(btnRegistration);

        btnRegistration.addActionListener(e -> {
            Field3.setText("");
            if (Field1.getText().isEmpty() || Field2.getText().isEmpty()) {
                Field3.setText("Вы не заполнили все поля");
                return;
            }

            try {
                Connection connection = JDBCPosgreSQLConnection.OpenConnection();
                String sql1 = "Select id from users where login = ? LIMIT 1;";
                PreparedStatement stmt = connection.prepareStatement(sql1);
                stmt.setString(1, Field1.getText());
                ResultSet rs = stmt.executeQuery();

                UsersTable UsersTable = new UsersTable();
                while (rs.next()) {
                    UsersTable.setId(rs.getInt("Id"));
                }
                if (UsersTable.getId() == 0) {
                    String sql2 = "INSERT INTO users (login, password, idrole) VALUES (?, ?, 2)";
                    PreparedStatement add = connection.prepareStatement(sql2);
                    add.setString(1, Field1.getText());
                    add.setString(2, Field2.getText());
                    int resultUpdate = add.executeUpdate();
                    if (resultUpdate == 1) {
                        Users users = new Users();
                        users.UserName = Field1.getText();
                        MainFrain();
                        try {
                            Thread.sleep(600);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                        frame.dispose();
                    }
                    else {
                        Field3.setText("Неизвестная ошибка, попробуйте еще раз");
                    }
                }
                else {
                    Field3.setText("Такой пользователь существует");
                }

            } catch (SQLException ex) {
                try {
                    throw new SQLException(ex);
                } catch (SQLException exc) {
                    throw new RuntimeException(exc);
                }
            } catch (ClassNotFoundException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        panelBlock.setSize(300, 130);
        panelBlock.setOpaque(false);
        panelBlock.add(Field1);
        centerLocation = CenterLocationObject(frame);
        panelBlock.setLocation(centerLocation[0] - 50, centerLocation[1]);
        panelBlock.add(Field2);
        panelBlock.add(btnRegistration);
        panelBlock.add(Field3);

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
