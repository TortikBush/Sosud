package Frame;

import DBSourse.AchievementTable;
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
            int index = 1;
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

        JPanel panelForDescription = new JPanel();
        panelBlock.setSize(1400, 300);
        panelBlock.setBackground(Color.blue);
        panelBlock.setForeground(Color.BLUE);

        JPanel panelGrid = new JPanel(new GridLayout(2, 0));
        panelGrid.setSize(1700, 300);
        centerLocation = Registration.CenterLocationObject(frameAchievement);
        panelGrid.setLocation(centerLocation[0] - 800, centerLocation[1] - 100);


        List<AchievementTable> achievementList = SelectAchievementFromDB();


        JTextArea NameAchievement = new JTextArea();
        NameAchievement.setFont(new Font("a", Font.BOLD, 35));
        NameAchievement.setEditable(false);
        NameAchievement.setLineWrap(true);
        NameAchievement.setFocusable(false);
        NameAchievement.setSize(300, 90);
        NameAchievement.setOpaque(true);
        NameAchievement.setForeground(Color.black);
        NameAchievement.setBackground(Color.red);
        NameAchievement.setEditable(false);
        NameAchievement.setLineWrap(false);
        NameAchievement.setWrapStyleWord(false);
        NameAchievement.setAlignmentX(Component.RIGHT_ALIGNMENT);
        NameAchievement.setAlignmentY(Component.CENTER_ALIGNMENT);


        JTextArea textDescription = new JTextArea("test");
        textDescription.setPreferredSize(new Dimension(300, 100));
        textDescription.setFont(new Font("a", Font.BOLD, 35));
        textDescription.setEditable(false);
        textDescription.setLineWrap(true);
        textDescription.setFocusable(false);
        textDescription.setAlignmentX(Component.RIGHT_ALIGNMENT);
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
            if (achievementList.size() != ref.index){
                ref.index +=1;
            }
                achievementList.stream()
                        .filter(achievement -> achievement.getId() == ref.index) // Применение тернарного оператора
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
            if (ref.index != 1){
                ref.index -=1;
            }
            achievementList.stream()
                    .filter(achievement -> achievement.getId() == ref.index) // Применение тернарного оператора
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

        frameAchievement.add(panelGrid);
        frameAchievement.add(label);
        frameAchievement.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frameAchievement.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameAchievement.setUndecorated(true);

        frameAchievement.show();
    }

    public static List<AchievementTable> SelectAchievementFromDB() throws SQLException, ClassNotFoundException {
        Connection connection = JDBCPosgreSQLConnection.OpenConnection();
        List<AchievementTable> achievementList = new ArrayList<>();
        String sql1 = "Select id,name,idcharacter, description from achievement";
        PreparedStatement stmt = connection.prepareStatement(sql1);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            AchievementTable achievementTable = new AchievementTable();
            achievementTable.setId(rs.getInt("id"));
            achievementTable.setName(rs.getString("name"));
            achievementTable.setIdCharacter(rs.getInt("idcharacter"));
            achievementTable.setDescription(rs.getString("description"));

            achievementList.add(achievementTable);
        }

        return achievementList;
    }
}
