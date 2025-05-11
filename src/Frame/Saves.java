package Frame;

import DBSourse.JDBCPostgreSQLConnection;
import DBSourse.SaveData;
import Frame.History.NovelFrame;
import HelpClasses.CenterLocation;
import HelpClasses.CustomFont;
import HelpClasses.Users;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static HelpClasses.CashedResource.cachedSaveFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Saves {

    private List<SaveData> saves;
    private JFrame main = new JFrame();
    private JPanel listPanel = new JPanel(null);

    public Saves() throws IOException, FontFormatException {
        saves = loadSavesFromDatabase();

        main.setDefaultCloseOperation(EXIT_ON_CLOSE);
        main.setUndecorated(true);
        main.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // === Слой для компонентов ===
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1920, 1080));

        // === Фон ===
        JLabel background = new JLabel(cachedSaveFrame);
        background.setBounds(0, 0, 1920, 1080);

        // === Кнопки ===
        JButton backButton = new JButton();
        backButton.setBounds(1563, 110, 110, 110);
        layeredPane.add(backButton, Integer.valueOf(1));
        backButton.setBackground(Color.RED);
        backButton.setOpaque(false);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            if (Users.GetSavePoint() != null){
                try {
                    new NovelFrame("src/story.json", Users.GetSavePoint());
                } catch (IOException | FontFormatException ex) {
                    throw new RuntimeException(ex);
                }
                main.dispose();
                return;
            }
            try {
                new Main();
            } catch (IOException | ClassNotFoundException | SQLException ex) {
                throw new RuntimeException(ex);
            }
            main.dispose();
        });


        // === Панель сохранений ===
        listPanel.setOpaque(false);
        listPanel.setBounds(300, 250, 1320, 600);
        updateListPanel();

        layeredPane.add(background, Integer.valueOf(0));
        layeredPane.add(listPanel, Integer.valueOf(1));

        main.setContentPane(layeredPane);
        main.setDefaultCloseOperation(EXIT_ON_CLOSE);
        main.setUndecorated(true);
        main.setExtendedState(JFrame.MAXIMIZED_BOTH);
        main.pack();
        main.setVisible(true);
    }

    private void updateListPanel() throws IOException, FontFormatException {
        saves = loadSavesFromDatabase();
        listPanel.removeAll();
        int y = 0;
        for (int i = 0; i < saves.size(); i++) {
            SaveData save = saves.get(i);
            JPanel saveItem = createSaveItemPanel(save, i);
            saveItem.setBounds(0, y, 1320, 80);
            listPanel.add(saveItem);
            y += 90;
        }
        listPanel.revalidate();
        listPanel.repaint();
    }

    private JPanel createSaveItemPanel(SaveData save, int index) throws IOException, FontFormatException {
        JPanel panel = new JPanel(null);
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(750, 80));

        // Название
        JButton nameButton = new JButton((index + 1) + ". " + save.getTitle());
        nameButton.setFont(CustomFont.CustomFont2().deriveFont(50f));
        nameButton.setHorizontalTextPosition(SwingConstants.CENTER);
        nameButton.setVerticalTextPosition(SwingConstants.CENTER);
        nameButton.setForeground(new Color(254, 222, 143));
        nameButton.setBounds(100, 12, 600, 50);
        nameButton.setBackground(Color.WHITE);
        nameButton.setOpaque(false);
        panel.add(nameButton);
        nameButton.setBorderPainted(false);
        nameButton.addActionListener(e -> {
            try {
                new NovelFrame("src/story.json", save.getSavePoint());
                main.dispose();
            } catch (IOException | FontFormatException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Дата
        JLabel dateLabel = new JLabel(save.getDate().toString());
        dateLabel.setFont(CustomFont.CustomFont2().deriveFont(50f));
        dateLabel.setForeground(new Color(254, 222, 143));
        dateLabel.setBounds(700, 12, 250, 50);
        panel.add(dateLabel);

        // Кнопки
        JButton editButton = createIconButton("write.png");
        JButton deleteButton = createIconButton("Trash.png");
        JButton pinButton = createIconButton("pin01.png");

        editButton.setBounds(1040, 5, 40, 40);
        deleteButton.setBounds(1090, 5, 40, 40);
        pinButton.setBounds(1140, 5, 40, 40);

        panel.add(editButton);
        panel.add(deleteButton);
        panel.add(pinButton);

        // Слушатели
        editButton.addActionListener(e -> {
            editSave(save);
            try {
                updateListPanel();
            } catch (IOException | FontFormatException ex) {
                throw new RuntimeException(ex);
            }
        });

        deleteButton.addActionListener(e -> {
            deleteSave(save);
            try {
                updateListPanel();
            } catch (IOException | FontFormatException ex) {
                throw new RuntimeException(ex);
            }
        });

        pinButton.addActionListener(e -> {
            pinSave(save);
            try {
                updateListPanel();
            } catch (IOException | FontFormatException ex) {
                throw new RuntimeException(ex);
            }
        });

        return panel;
    }

    private JButton createIconButton(String fileName) {
        String resource = ("src/Resource/Save/" + fileName);

        ImageIcon originalIcon = new ImageIcon(resource);
        Image scaled = originalIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaled);

        JButton button = new JButton(icon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        return button;
    }

    private void editSave(SaveData save) {
        String newName = JOptionPane.showInputDialog(main, "Введите новое имя:", save.getTitle());
        if (newName != null && !newName.trim().isEmpty()) {
            save.setTitle(newName);
            updateTitleInDatabase(save.getId(), newName);
        }
    }

    private void deleteSave(SaveData save) {
        int result = JOptionPane.showConfirmDialog(main, "Удалить сохранение?", "Подтверждение", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            markAsDeletedInDatabase(save.getId());
            saves.remove(save);
        }
    }

    private void pinSave(SaveData save) {
        JOptionPane.showMessageDialog(main, "Сохранение закреплено.");
        setPinStatusInDatabase(save.getId(), true);
        save.isDeleted = true;
    }

    // ==== База данных ====

    public List<SaveData> loadSavesFromDatabase() {
        List<SaveData> saves = new ArrayList<>();
        try (Connection connection = JDBCPostgreSQLConnection.OpenConnection()) {
            String sql = "SELECT conservation.id, conservation.title, conservation.date, conservation.ispinning, conservation.isdeleted, conservation.savepoint " +
                    "FROM conservation " +
                    "WHERE conservation.userid = ? AND conservation.isdeleted = FALSE " +
                    "ORDER BY conservation.ispinning DESC, conservation.date DESC";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Users.GetIdUser());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                saves.add(new SaveData(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getDate("date").toLocalDate(),
                        rs.getBoolean("ispinning"),
                        rs.getBoolean("isdeleted"),
                        rs.getString("savepoint")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return saves;
    }

    private void updateTitleInDatabase(int id, String newTitle) {
        try (Connection connection = JDBCPostgreSQLConnection.OpenConnection()) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE conservation SET title = ? WHERE id = ?");
            stmt.setString(1, newTitle);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void markAsDeletedInDatabase(int id) {
        try (Connection connection = JDBCPostgreSQLConnection.OpenConnection()) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE conservation SET isdeleted = TRUE WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setPinStatusInDatabase(int id, boolean pinned) {
        try (Connection connection = JDBCPostgreSQLConnection.OpenConnection()) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE conservation SET ispinning = ? WHERE id = ?");
            stmt.setBoolean(1, pinned);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createNewSave(String title, String savePoint) {
        try (Connection connection = JDBCPostgreSQLConnection.OpenConnection()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO conservation (title, date, ispinning, isdeleted, savepoint, userid) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, title);
            stmt.setDate(2, Date.valueOf(LocalDate.now()));
            stmt.setBoolean(3, false);
            stmt.setBoolean(4, false);
            stmt.setString(5, savePoint);
            stmt.setInt(6, Users.GetIdUser());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
