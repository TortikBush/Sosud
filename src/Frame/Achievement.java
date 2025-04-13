package Frame;

import DBSourse.AchievementList;
import DBSourse.JDBCPosgreSQLConnection;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.List;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Achievement {
    public static void Achievement() throws SQLException, ClassNotFoundException {
        var ref = new Object() {
            int indexAchiv = 0;
            int idCharacter = 1;
        };

        JFrame frameAchievement = new JFrame();
        //Главная картика
        ImageIcon icon = new ImageIcon(new File("src/resource/MainBackground.png").getAbsolutePath());

        //картинка настройки
        Image scaledImage = icon.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);
        int[] centerLocation = Registration.CenterLocationObject(frameAchievement);
        label.setBounds(centerLocation[0], centerLocation[1], 1920, 1080);

        JPanel panelBlockAchievement = new JPanel();
        panelBlockAchievement.setSize(900, 250);
        panelBlockAchievement.setBackground(Color.red);
        panelBlockAchievement.setForeground(Color.red);
        panelBlockAchievement.setLayout(new BoxLayout(panelBlockAchievement, BoxLayout.X_AXIS));
        centerLocation = Registration.CenterLocationObject(frameAchievement);
        panelBlockAchievement.setLocation(centerLocation[0] - 670, centerLocation[1] - 100);

        JPanel panelForCharacter = new JPanel();
        panelForCharacter.setSize(500, 150);
        panelForCharacter.setBackground(Color.blue);
        panelForCharacter.setForeground(Color.BLUE);
        panelForCharacter.setLayout(new BoxLayout(panelForCharacter, BoxLayout.X_AXIS));
        panelForCharacter.setLocation(centerLocation[0] - 250, centerLocation[1] - 400);

        JPanel panelForDescription = new JPanel();
        panelForDescription.setSize(300, 100);
        panelForDescription.setBackground(Color.blue);
        panelForDescription.setLayout(new BorderLayout());
        panelForDescription.setForeground(Color.BLUE);
        panelForDescription.setForeground(Color.BLUE);

        JPanel panelGrid = new JPanel(new GridLayout(2, 0));
        panelGrid.setSize(1000, 300);
        centerLocation = Registration.CenterLocationObject(frameAchievement);
        panelGrid.setLocation(centerLocation[0] - 800, centerLocation[1] - 100);


        List<AchievementList> achievementList = SelectAchievementFromDB();

        JTextPane NameAchievement = new JTextPane();
        NameAchievement.setFont(new Font("a", Font.BOLD, 35));
        NameAchievement.setEditable(false);
        NameAchievement.setFocusable(false);
        NameAchievement.setPreferredSize(new Dimension(300, 90));
        NameAchievement.setOpaque(true);
        NameAchievement.setForeground(Color.black);
        NameAchievement.setBackground(Color.red);
        NameAchievement.setAlignmentX(Component.CENTER_ALIGNMENT);
        NameAchievement.setAlignmentY(Component.CENTER_ALIGNMENT);

// Центрирование текста
        StyledDocument docName = NameAchievement.getStyledDocument();
        SimpleAttributeSet centerName = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerName, StyleConstants.ALIGN_CENTER);
        docName.setParagraphAttributes(0, docName.getLength(), centerName, false);

        JTextPane textDescription = new JTextPane();
        textDescription.setPreferredSize(new Dimension(900, 150));
        textDescription.setFont(new Font("a", Font.BOLD, 35));
        textDescription.setEditable(false);
        textDescription.setFocusable(false);
        textDescription.setOpaque(true);
        textDescription.setForeground(Color.black);
        textDescription.setBackground(Color.red);
        textDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        textDescription.setAlignmentY(Component.CENTER_ALIGNMENT);

// Устанавливаем выравнивание по центру
        StyledDocument doc = textDescription.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        JTextPane textCharacter = new JTextPane();
        textCharacter.setPreferredSize(new Dimension(300, 100));
        textCharacter.setFont(new Font("a", Font.BOLD, 35));
        textCharacter.setEditable(false);
        textCharacter.setFocusable(false);
        textCharacter.setOpaque(true);
        textCharacter.setForeground(Color.black);
        textCharacter.setBackground(Color.red);
        textCharacter.setAlignmentX(Component.CENTER_ALIGNMENT);
        textCharacter.setAlignmentY(Component.CENTER_ALIGNMENT);

// Центрирование текста
        StyledDocument docChar = textCharacter.getStyledDocument();
        SimpleAttributeSet centerChar = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerChar, StyleConstants.ALIGN_CENTER);
        docChar.setParagraphAttributes(0, docChar.getLength(), centerChar, false);


        achievementList.stream()
                .filter(achievement -> achievement.getId() == 1) // Применение тернарного оператора
                .forEach(achievement -> {
                    NameAchievement.setText(achievement.getName());
                    textDescription.setText(achievement.getDescription());
                    textCharacter.setText(achievement.getNameCharacter());
                });

        textDescription.setOpaque(true);
        textDescription.setForeground(Color.black);
        textDescription.setBackground(Color.red);
        textDescription.setEditable(false);

        JButton buttonNextNameCharacter = new JButton(">");
        buttonNextNameCharacter.setPreferredSize(new Dimension(70, 50));
        buttonNextNameCharacter.setFont(new Font("asfsafasfasf", Font.BOLD, 30));
        buttonNextNameCharacter.setForeground(Color.red);
        buttonNextNameCharacter.setBackground(Color.black);
        buttonNextNameCharacter.setSize(400, 50);
        buttonNextNameCharacter.setOpaque(true);
        buttonNextNameCharacter.setFocusable(false);

        buttonNextNameCharacter.addActionListener(e -> {
            ref.indexAchiv = 0;
            if (ref.idCharacter != 3) {
                ref.idCharacter += 1;
            }

            achievementList.stream()
                    .filter(achievement -> achievement.getIdCharacter() == ref.idCharacter) // Применение тернарного оператора
                    .forEach(achievement -> {
                        textCharacter.setText(achievement.getNameCharacter());
                    });

            List<AchievementList> achievementList1 = new ArrayList<>();
            achievementList.stream()
                    .filter(achievement -> achievement.getIdCharacter() == ref.idCharacter)// Применение тернарного оператора
                    .forEach(achievement -> {
                        achievementList1.add(achievement);
                    });

                AchievementList previous = achievementList1.get(ref.indexAchiv);
                NameAchievement.setText(previous.getName());
                textDescription.setText(previous.getDescription());
        });


        JButton buttonNextNameAchievement = new JButton(">");
        buttonNextNameAchievement.setPreferredSize(new Dimension(70, 50));
        buttonNextNameAchievement.setFont(new Font("asfsafasfasf", Font.BOLD, 30));
        buttonNextNameAchievement.setForeground(Color.red);
        buttonNextNameAchievement.setBackground(Color.black);
        buttonNextNameAchievement.setSize(400, 50);
        buttonNextNameAchievement.setOpaque(true);
        buttonNextNameAchievement.setFocusable(false);

        buttonNextNameAchievement.addActionListener(e -> {
            List<AchievementList> achievementList1 = new ArrayList<>();
            achievementList.stream()
                    .filter(achievement -> achievement.getIdCharacter() == ref.idCharacter)// Применение тернарного оператора
                    .forEach(achievement -> {
                        achievementList1.add(achievement);
                    });

            if (achievementList1.size() - 1 != ref.indexAchiv) {
                ref.indexAchiv += 1;
                AchievementList previous = achievementList1.get(ref.indexAchiv);
                NameAchievement.setText(previous.getName());
                textDescription.setText(previous.getDescription());
            }
        });

        JButton buttonReversNameAchievement = new JButton("<");
        buttonReversNameAchievement.setPreferredSize(new Dimension(70, 50));
        buttonReversNameAchievement.setFont(new Font("asfsafasfasf", Font.BOLD, 30));
        buttonReversNameAchievement.setForeground(Color.red);
        buttonReversNameAchievement.setBackground(Color.black);
        buttonReversNameAchievement.setSize(400, 50);
        buttonReversNameAchievement.setOpaque(true);
        buttonReversNameAchievement.setFocusable(false);

        buttonReversNameAchievement.addActionListener(e -> {
            List<AchievementList> achievementList1 = new ArrayList<>();
            achievementList.stream()
                    .filter(achievement -> achievement.getIdCharacter() == ref.idCharacter)// Применение тернарного оператора
                    .forEach(achievement -> {
                        achievementList1.add(achievement);
                    });

            if (ref.indexAchiv > 0) {
                ref.indexAchiv -= 1;
                AchievementList previous = achievementList1.get(ref.indexAchiv);
                 NameAchievement.setText(previous.getName());
                 textDescription.setText(previous.getDescription());
            }
        });

        JButton buttonReversNameCharacter = new JButton("<");
        buttonReversNameCharacter.setPreferredSize(new Dimension(70, 50));
        buttonReversNameCharacter.setFont(new Font("asfsafasfasf", Font.BOLD, 30));
        buttonReversNameCharacter.setForeground(Color.red);
        buttonReversNameCharacter.setBackground(Color.black);
        buttonReversNameCharacter.setSize(400, 50);
        buttonReversNameCharacter.setOpaque(true);
        buttonReversNameCharacter.setFocusable(false);

        buttonReversNameCharacter.addActionListener(e -> {
            if (ref.idCharacter != 1) {
                ref.idCharacter -= 1;
            }
            ref.indexAchiv = 0;
            achievementList.stream()
                    .filter(achievement -> achievement.getIdCharacter() == ref.idCharacter) // Применение тернарного оператора
                    .forEach(achievement -> {
                        textCharacter.setText(achievement.getNameCharacter());
                    });

            List<AchievementList> achievementList1 = new ArrayList<>();
            achievementList.stream()
                    .filter(achievement -> achievement.getIdCharacter() == ref.idCharacter)// Применение тернарного оператора
                    .forEach(achievement -> {
                        achievementList1.add(achievement);
                    });
                AchievementList previous = achievementList1.get(ref.indexAchiv);
                NameAchievement.setText(previous.getName());
                textDescription.setText(previous.getDescription());
        });


        panelBlockAchievement.add(buttonReversNameAchievement);
        panelBlockAchievement.add(NameAchievement);
        panelBlockAchievement.add(buttonNextNameAchievement);

        panelForDescription.add(textDescription, BorderLayout.CENTER);
        panelGrid.add(panelBlockAchievement);
        panelGrid.add(panelForDescription);
        panelForCharacter.add(buttonReversNameCharacter);
        panelForCharacter.add(textCharacter);
        panelForCharacter.add(buttonNextNameCharacter);

        frameAchievement.add(panelForCharacter);

        frameAchievement.add(panelGrid);
        frameAchievement.add(label);
        frameAchievement.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frameAchievement.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameAchievement.setUndecorated(true);

        frameAchievement.show();
    }

    public static List<AchievementList> SelectAchievementFromDB() throws SQLException, ClassNotFoundException {
        Connection connection = JDBCPosgreSQLConnection.OpenConnection();
        List<AchievementList> achievementList = new ArrayList<>();
        String sql1 = "Select achievement.id as id, achievement.name AS name, idcharacter, description, character.name as namecharacter" +
                " FROM achievement " +
                "join character on achievement.idcharacter = character.id ";

        PreparedStatement stmt = connection.prepareStatement(sql1);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            AchievementList achievement = new AchievementList();
            achievement.setId(rs.getInt("id"));
            achievement.setName(rs.getString("name"));
            achievement.setIdCharacter(rs.getInt("idcharacter"));
            achievement.setDescription(rs.getString("description"));
            achievement.setNameCharacter(rs.getString("namecharacter"));
            achievementList.add(achievement);
        }

        return achievementList;
    }
}
