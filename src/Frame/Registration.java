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
        var ref = new Object() {
            int RegSign = 0;
        };

        JFrame frame = new JFrame();
        JPanel panelBlock = new JPanel();
        ImageIcon icon = new ImageIcon(new File("src/resource/MainBackground.png").getAbsolutePath());
        Image scaledImage1 = icon.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage1);
        JLabel label = new JLabel(scaledIcon2);
        int[] centerLocation = CenterLocationObject(frame);
        label.setBounds(centerLocation[0], centerLocation[1], 1920, 1080);

        JTextField LogginField1 = new JTextField(15);
        LogginField1.setSize(100, 500);
        LogginField1.setBackground(Color.black);
        LogginField1.setForeground(Color.WHITE);
        LogginField1.setOpaque(false);
        LogginField1.setDisabledTextColor(Color.RED);


        JTextField PasswordField2 = new JTextField(25);
        PasswordField2.setSize(300, 500);
        PasswordField2.setBackground(Color.black);
        PasswordField2.setForeground(Color.WHITE);

        PasswordField2.setOpaque(false);

        JTextArea Field3 = new JTextArea();
        Field3.setSize(300, 30);
        Field3.setOpaque(false);
        Field3.setForeground(Color.RED);

        JButton btnRegistration = new JButton("Регистрация");
        btnRegistration.setSize(100, 30);
        btnRegistration.setForeground(Color.WHITE);
        btnRegistration.setOpaque(false);
        parButton(btnRegistration);

        JPanel panelsignIn = new JPanel();
        panelsignIn.setBackground(Color.black);
        panelsignIn.setSize(250, 50);
        panelsignIn.setLocation(centerLocation[0] - 50, centerLocation[1] + 100);


        JTextArea Account = new JTextArea();
        Account.setSize(300, 30);
        Account.setOpaque(false);
        Account.setForeground(Color.WHITE);
        Account.setOpaque(false);
        Account.setBackground(Color.black);
        Account.setText("У вас есть аккаунт?");

        JButton signInBtn = new JButton();
        signInBtn.setSize(100, 30);
        signInBtn.setForeground(Color.WHITE);
        signInBtn.setOpaque(false);
        signInBtn.setText("Войти");
        signInBtn.setBackground(Color.black);
        signInBtn.addActionListener(e -> {
            if (ref.RegSign == 0) {
                btnRegistration.setText("Войти");
                Account.setText("У вас нет аккаунта? ");
                signInBtn.setText("Зарегистрироваться");
                ref.RegSign = 1;
            } else if (ref.RegSign == 1) {
                Account.setText("У вас есть аккаунт? ");
                signInBtn.setText("Войти");
                btnRegistration.setText("Зарегистрироваться");
                ref.RegSign = 0;
            }
        });

        panelsignIn.add(Account);
        panelsignIn.add(signInBtn);

        btnRegistration.addActionListener(e -> {
            if (ref.RegSign == 1) {
                Field3.setText("");
                if (LogginField1.getText().isEmpty() || PasswordField2.getText().isEmpty()) {
                    Field3.setText("Вы не заполнили все поля");
                    return;
                }
                try {
                    Connection connection = JDBCPosgreSQLConnection.OpenConnection();
                    String sql1 = "Select id, login from users where login = ? and password = ? LIMIT 1;";
                    PreparedStatement stmt = connection.prepareStatement(sql1);
                    stmt.setString(1, LogginField1.getText());
                    stmt.setString(2, PasswordField2.getText());
                    ResultSet rs = stmt.executeQuery();
                    Users user = new Users();
                    while (!rs.next()) {
                        if (!rs.next()) {
                            Field3.setText("Такого пользователя нет!");
                            break;
                        } else {
                            user.UserName = rs.getString("login");
                            MainFrain();
                            try {
                                Thread.sleep(600);
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
                            frame.dispose();
                            break;
                        }
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            } else {
                Field3.setText("");
                if (LogginField1.getText().isEmpty() || PasswordField2.getText().isEmpty()) {
                    Field3.setText("Вы не заполнили все поля");
                    return;
                }
                try {
                    Connection connection = JDBCPosgreSQLConnection.OpenConnection();
                    String sql1 = "Select id from users where login = ? LIMIT 1;";
                    PreparedStatement stmt = connection.prepareStatement(sql1);
                    stmt.setString(1, LogginField1.getText());
                    ResultSet rs = stmt.executeQuery();

                    UsersTable UsersTable = new UsersTable();
                    while (rs.next()) {
                        UsersTable.setId(rs.getInt("Id"));
                    }
                    if (UsersTable.getId() == 0) {
                        String sql2 = "INSERT INTO users (login, password, idrole) VALUES (?, ?, 2)";
                        PreparedStatement add = connection.prepareStatement(sql2);
                        add.setString(1, LogginField1.getText());
                        add.setString(2, PasswordField2.getText());
                        int resultUpdate = add.executeUpdate();
                        if (resultUpdate == 1) {
                            Users users = new Users();
                            users.UserName = LogginField1.getText();
                            MainFrain();
                            try {
                                Thread.sleep(600);
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
                            frame.dispose();
                        } else {
                            Field3.setText("Неизвестная ошибка, попробуйте еще раз");
                        }
                    } else {
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
            }


        });

        panelBlock.setSize(300, 130);
        panelBlock.setOpaque(false);
        panelBlock.add(LogginField1);
        centerLocation = CenterLocationObject(frame);
        panelBlock.setLocation(centerLocation[0] - 50, centerLocation[1]);
        panelBlock.add(PasswordField2);
        panelBlock.add(btnRegistration);
        panelBlock.add(Field3);

        frame.add(panelBlock);
        frame.add(panelsignIn);
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
