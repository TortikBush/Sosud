package Frame;

import DBSourse.JDBCPostgreSQLConnection;
import DBSourse.UsersTable;
import HelpClasses.CustomFont;
import HelpClasses.Users;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import static Frame.Main.*;
import static HelpClasses.CashedResource.cachedRegistrationFon;
import static HelpClasses.CashedResource.cachedSingInFon;
import static HelpClasses.CenterLocation.CenterLocationObject;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class Registration {
    private static final JButton btnRegistration = new JButton();
    private static final JTextArea Field3 = new JTextArea();
    private static final JTextArea Account = new JTextArea();
    private static final JButton signInBtn = new JButton();
    private static final JLabel label = new JLabel(cachedRegistrationFon);

    private static int RegSign = 0;

    public Registration() throws IOException, SQLException, ClassNotFoundException, FontFormatException {
        JFrame frame = new JFrame();
        JPanel panelBlock = new JPanel();
        int[] centerLocation = CenterLocationObject(frame);
        label.setBounds(centerLocation[0], centerLocation[1], 1920, 1080);

        JButton buttonClose = new JButton();
        File imgAchievement = new File("src/resource/CloseImage.png").getAbsoluteFile();
        Image imgReadAchievement = ImageIO.read(imgAchievement);
        buttonClose.setSize(60, 60);
        Image newImg3 = imgReadAchievement.getScaledInstance((int) buttonClose.getSize().getWidth(), (int) buttonClose.getSize().getHeight(), Image.SCALE_SMOOTH);
        buttonClose.setIcon(new ImageIcon(newImg3));
        Main.parButton(buttonClose);
        int[] centerLocation1 = CenterLocationObject(frame);
        buttonClose.setLocation(centerLocation1[0] + 520, centerLocation1[1] - 360);
        buttonClose.addActionListener(e -> {
            try {
                new Main();
                frame.dispose();
            } catch (IOException | SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        JTextField LogginField1 = new JTextField(15);
        LogginField1.setSize(300, 500);
        LogginField1.setEditable(true);
        LogginField1.setCaretColor(new Color(254, 222, 143));
        LogginField1.setBackground(Color.black);
        LogginField1.setForeground(Color.WHITE);
        LogginField1.setOpaque(false);
        LogginField1.setDisabledTextColor(Color.RED);
        LogginField1.setFont(CustomFont.CustomFont1().deriveFont(40f));

        JPanel panelsignIn1 = new JPanel();
        panelsignIn1.setBackground(Color.black);
        panelsignIn1.setOpaque(false);
        panelsignIn1.setSize(240, 100);
        panelsignIn1.setLocation(centerLocation[0] - 300, centerLocation[1] - 45);
        panelsignIn1.setLayout(new BoxLayout(panelsignIn1, BoxLayout.Y_AXIS));

        JTextField Login = new JTextField();
        Login.setSize(300, 30);
        Login.setOpaque(false);
        Login.setText("Логин");
        Login.setEditable(false);
        Login.setBorder(null);
        Login.setFocusable(false);
        Login.setFont(CustomFont.CustomFont1().deriveFont(40f));
        Login.setForeground(new Color(254, 222, 143));

        JTextField Password = new JTextField();
        Password.setSize(300, 30);
        Password.setForeground(Color.WHITE);
        Password.setOpaque(false);
        Password.setBackground(Color.black);
        Password.setEditable(false);
        Password.setBorder(null);
        Password.setFocusable(false);
        Password.setText("Пароль");
        Password.setFont(CustomFont.CustomFont1().deriveFont(40f));
        Password.setForeground(new Color(254, 222, 143));

        JPasswordField  PasswordField2 = new JPasswordField (15);
        PasswordField2.setSize(300, 500);
        PasswordField2.setEchoChar('*');        PasswordField2.setEditable(true);
        PasswordField2.setCaretColor(new Color(254, 222, 143));
        PasswordField2.setBackground(Color.black);
        PasswordField2.setForeground(Color.WHITE);
        PasswordField2.setFont(CustomFont.CustomFont1().deriveFont(40f));
        PasswordField2.setOpaque(false);

        Field3.setSize(300, 30);
        Field3.setEditable(false);
        Field3.setBorder(null);
        Field3.setFocusable(false);
        Field3.setHighlighter(null);
        Field3.setOpaque(false);
        Field3.setForeground(Color.RED);

        btnRegistration.setSize(100, 30);
        btnRegistration.setText("Зарегистрироваться");
        btnRegistration.setForeground(Color.WHITE);
        btnRegistration.setOpaque(false);
        btnRegistration.setFont(CustomFont.CustomFont1().deriveFont(20f));
        btnRegistration.setForeground(new Color(254, 222, 143));
        parButton(btnRegistration);
        btnRegistration.addActionListener(e -> {
            int firstRegistration = 1;
            if (LogginField1.getText().isEmpty() || PasswordField2.getText().isEmpty()) {
                Field3.setText("Вы не заполнили все поля");
                return;
            }
            if (RegSign == 1) {
                Field3.setText("");
                try {
                    Connection connection = JDBCPostgreSQLConnection.OpenConnection();
                    String sql1 = "Select id, login from users where login = ? and password = ? LIMIT 1;";
                    PreparedStatement stmt = connection.prepareStatement(sql1);
                    stmt.setString(1, LogginField1.getText());
                    stmt.setString(2, PasswordField2.getText());
                    ResultSet rs = stmt.executeQuery();
                    if (!rs.next()) {
                        Field3.setText("Такого пользователя нет!");
                    } else {
                        Users.IdUser = rs.getInt("id");
                        Users.UserName = rs.getString("login");
                        if (firstRegistration == 0) {
                            for (int i = 1; i <= 2; i++) {
                                String sql3 = "INSERT INTO user_setting (iduser, idsetting,isactive ) VALUES (?, ?, true)";
                                PreparedStatement stmt1 = connection.prepareStatement(sql3);
                                stmt1.setInt(1, Users.GetIdUser());
                                stmt1.setInt(2, i);
                                stmt1.executeUpdate();
                            }
                        }
                            new Main();
                            try {
                                Thread.sleep(600);
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
                            frame.dispose();
                    }

                } catch (SQLException | ClassNotFoundException | IOException ex) {
                    throw new RuntimeException(ex);
                }

            } else {
                Field3.setText("");
                try {

                    if (LogginField1.getText().length() >= 5 && PasswordField2.getText().length() >= 8) {
                        Connection connection = JDBCPostgreSQLConnection.OpenConnection();
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
                                firstRegistration = 0;
                                SignInRegistration();
                            } else {
                                Field3.setText("Неизвестная ошибка, попробуйте еще раз");
                            }
                        } else {
                            Field3.setText("Такой пользователь c таким логином существует");
                        }
                    } else {
                        Field3.setText("Логин должен состоять из 5 символов, а пароль из 8");
                    }
                } catch (SQLException ex) {
                    try {
                        throw new SQLException(ex);
                    } catch (SQLException exc) {
                        throw new RuntimeException(exc);
                    }
                } catch (ClassNotFoundException | IOException | FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JPanel panelsignIn = new JPanel();
        panelsignIn.setBackground(Color.black);
        panelsignIn.setOpaque(false);
        panelsignIn.setSize(400, 70);
        panelsignIn.setLocation(centerLocation[0] - 200, centerLocation[1] + 170);

        Account.setSize(300, 30);
        Account.setOpaque(false);
        Account.setForeground(Color.WHITE);
        Account.setText("У вас есть аккаунт?");
        Account.setFont(CustomFont.CustomFont1().deriveFont(20f));
        Account.setForeground(new Color(254, 222, 143));

        signInBtn.setSize(100, 30);
        signInBtn.setForeground(Color.WHITE);
        signInBtn.setOpaque(false);
        signInBtn.setText("Войти");
        signInBtn.setFont(CustomFont.CustomFont1().deriveFont(20f));
        signInBtn.setForeground(new Color(254, 222, 143));
        signInBtn.setBackground(Color.black);
        signInBtn.addActionListener(e -> {
            try {
                SignInRegistration();
            } catch (IOException | FontFormatException ex) {
                throw new RuntimeException(ex);
            }
        });

        panelsignIn.add(Account);
        panelsignIn.add(signInBtn);

        panelBlock.setSize(400, 230);
        panelBlock.setOpaque(false);
        panelBlock.add(LogginField1);
        centerLocation = CenterLocationObject(frame);
        panelBlock.setLocation(centerLocation[0] - 100, centerLocation[1] - 45);
        panelBlock.add(PasswordField2);

        panelBlock.add(btnRegistration);
        panelBlock.add(Field3);
        panelsignIn1.add(Login);
        panelsignIn1.add(Password);

        frame.add(panelsignIn1);
        frame.add(buttonClose);
        frame.add(panelBlock);
        frame.add(panelsignIn);
        frame.add(label);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }

    public static void SignInRegistration() throws IOException, FontFormatException {
        if (RegSign == 0) {
            btnRegistration.setText("Войти");
            Field3.setText("");
            Account.setText("У вас нет аккаунта? ");
            signInBtn.setText("Зарегистрироваться");
            label.setIcon(cachedSingInFon);
            RegSign = 1;
        } else if (RegSign == 1) {
            label.setIcon(cachedRegistrationFon);
            Account.setText("У вас есть аккаунт? ");
            signInBtn.setText("Войти");
            btnRegistration.setText("Зарегистрироваться");
            Field3.setText("");
            RegSign = 0;
        }
    }
}
