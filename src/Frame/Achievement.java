package Frame;

import DBSourse.AchievementList;
import DBSourse.JDBCPosgreSQLConnection;

import javax.swing.*;
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
            int indexAchiv = 1;
            int idCharacter =1;
        };

        JFrame frameAchievement = new JFrame();
        //Главная картика
        ImageIcon icon = new ImageIcon(new File("src/resource/forestMenu.png").getAbsolutePath());

        //картинка настройки
        Image scaledImage = icon.getImage().getScaledInstance(1920, 1080, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(scaledIcon);
        int[] centerLocation = Registration.CenterLocationObject(frameAchievement);
        label.setBounds(centerLocation[0], centerLocation[1], 1920, 1080);

        JPanel panelBlock = new JPanel();
        panelBlock.setSize(1400, 300);
        panelBlock.setBackground(Color.red);
        panelBlock.setForeground(Color.red);
        panelBlock.setLayout(new BoxLayout(panelBlock, BoxLayout.X_AXIS));
        centerLocation = Registration.CenterLocationObject(frameAchievement);
        panelBlock.setLocation(centerLocation[0] - 670, centerLocation[1] - 100);

        JPanel panelForCharacter = new JPanel();
        panelForCharacter.setSize(1400, 300);
        panelForCharacter.setBackground(Color.blue);
        panelForCharacter.setForeground(Color.BLUE);
        panelForCharacter.setLocation(centerLocation[0] -350, centerLocation[1] -500);

        JPanel panelForDescription = new JPanel();
        panelBlock.setSize(1400, 300);
        panelBlock.setBackground(Color.blue);
        panelBlock.setForeground(Color.BLUE);

        JPanel panelGrid = new JPanel(new GridLayout(2, 0));
        panelGrid.setSize(1700, 300);
        centerLocation = Registration.CenterLocationObject(frameAchievement);
        panelGrid.setLocation(centerLocation[0] - 800, centerLocation[1] - 100);


        List<AchievementList> achievementList = SelectAchievementFromDB();


        JTextArea NameAchievement = new JTextArea();
        NameAchievement.setFont(new Font("a", Font.BOLD, 35));
        NameAchievement.setEditable(false);
        NameAchievement.setLineWrap(true);
        NameAchievement.setFocusable(false);
        NameAchievement.setSize(300, 90);
        NameAchievement.setLocation(centerLocation[0] -300, centerLocation[1] + 100);
        NameAchievement.setOpaque(true);
        NameAchievement.setForeground(Color.black);
        NameAchievement.setBackground(Color.red);
        NameAchievement.setEditable(false);
        NameAchievement.setLineWrap(false);
        NameAchievement.setWrapStyleWord(false);
        NameAchievement.setAlignmentX(Component.CENTER_ALIGNMENT);
        NameAchievement.setAlignmentY(Component.CENTER_ALIGNMENT);


        JTextArea textDescription = new JTextArea("test");
        textDescription.setPreferredSize(new Dimension(300, 100));
        textDescription.setFont(new Font("a", Font.BOLD, 35));
        textDescription.setEditable(false);
        textDescription.setLineWrap(true);
        textDescription.setFocusable(false);
        textDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        textDescription.setAlignmentY(Component.CENTER_ALIGNMENT);

        achievementList.stream()
                .filter(achievement -> achievement.getId() == 1) // Применение тернарного оператора
                .forEach(achievement -> {
                    NameAchievement.setText(achievement.getName());
                    textDescription.setText(achievement.getDescription());
                });



        textDescription.setSize(200, 100);
        textDescription.setOpaque(true);
        textDescription.setForeground(Color.black);
        textDescription.setBackground(Color.red);
        textDescription.setEditable(false);
        textDescription.setLineWrap(false);
        textDescription.setWrapStyleWord(false);


        JButton buttonNextNameAchievement = new JButton(">");
        buttonNextNameAchievement.setPreferredSize(new Dimension(70, 50));
        buttonNextNameAchievement.setFont(new Font("asfsafasfasf", Font.BOLD, 30));
        buttonNextNameAchievement.setForeground(Color.red);
        buttonNextNameAchievement.setBackground(Color.black);
        buttonNextNameAchievement.setSize(400, 50);
        buttonNextNameAchievement.setOpaque(true);
        buttonNextNameAchievement.setFocusable(false);

        buttonNextNameAchievement.addActionListener(e -> {
            if (achievementList.size() != ref.indexAchiv){
                ref.indexAchiv +=1;
            }
                achievementList.stream()
                        .filter(achievement -> achievement.getId() == ref.indexAchiv) // Применение тернарного оператора
                        .forEach(achievement -> {
                            NameAchievement.setText(achievement.getName());
                            textDescription.setText(achievement.getDescription());
                        });
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
            if (ref.indexAchiv != 1){
                ref.indexAchiv -=1;
            }
            achievementList.stream()
                    .filter(achievement -> achievement.getId() == ref.indexAchiv) // Применение тернарного оператора
                    .forEach(achievement -> {
                        NameAchievement.setText(achievement.getName());
                        textDescription.setText(achievement.getDescription());
                    });
        });

        panelBlock.add(buttonReversNameAchievement);
        panelBlock.add(NameAchievement);
        panelBlock.add(buttonNextNameAchievement);

        panelForDescription.add(textDescription);


        panelGrid.add(panelBlock);
        panelGrid.add(panelForDescription);
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
        String sql1 = "Select achievement.id as id, achievement.name AS name,idcharacter, description, character.name as namecharacter" +
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
