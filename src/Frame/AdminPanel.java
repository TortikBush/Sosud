package Frame;

import DBSourse.JDBCPostgreSQLConnection;
import HelpClasses.CustomFont;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.*;

import static HelpClasses.CashedResource.cachedMainBackground;

public class AdminPanel extends JFrame {
    Connection connection = JDBCPostgreSQLConnection.OpenConnection();

    JTable table;
    DefaultTableModel model;
    public static boolean isOpen = false;

    public AdminPanel() throws SQLException, ClassNotFoundException, IOException, FontFormatException {
        isOpen = true;

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                isOpen = false;
            }
        });

        setTitle("Панель администратора — Пользователи");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Фоновая панель
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(cachedMainBackground.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // Таблица и модель
        model = new DefaultTableModel(new String[]{"ID", "Login", "Password", "Role Name"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);

        // Прозрачность таблицы
        table.setOpaque(false);
        ((DefaultTableCellRenderer) table.getDefaultRenderer(Object.class)).setOpaque(false);

        // Шрифт и цвет
        table.setFont(CustomFont.CustomFont1().deriveFont(15f));
        table.setForeground(new Color(254, 222, 143));
        table.setRowHeight(28);

        // Заголовок таблицы
        JTableHeader header = table.getTableHeader();
        header.setFont(CustomFont.CustomFont1().deriveFont(Font.BOLD, 17f));
        header.setForeground(Color.WHITE);
        header.setBackground(new Color(0, 0, 0, 150)); // прозрачный чёрный
        header.setOpaque(false);

        // Прокрутка
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        backgroundPanel.add(scrollPane, BorderLayout.CENTER);
        setContentPane(backgroundPanel);

        // Загрузка пользователей
        loadUsers();

        setVisible(true);
    }

    private void loadUsers() throws SQLException {
        String query = "SELECT users.id, users.login, users.password, role.name " +
                "FROM users JOIN role ON users.idrole = role.id ORDER BY users.id";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String roleName = rs.getString("name");

                model.addRow(new Object[]{(Object) id, login, password, roleName});
            }
        }
    }
}
