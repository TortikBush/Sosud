package Frame;

import DBSourse.AchievementList;
import HelpClasses.CustomFont;
import DBSourse.JDBCPosgreSQLConnection;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static Frame.Main.cachedMainBackground;
import static Frame.Main.parButton;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Achievement {
    private static int FirstIdCharacter = 0;
    public Achievement() throws SQLException, ClassNotFoundException, IOException, FontFormatException {
        var ref = new Object() {
            int indexAchiv = 0;
            int IdCharacter = 1;
            int id = 0;

        };

        JFrame frameAchievement = new JFrame();
        JLabel label = new JLabel(cachedMainBackground);
        int[] centerLocation = Registration.CenterLocationObject(frameAchievement);
        label.setBounds(centerLocation[0], centerLocation[1], 1920, 1080);

        JPanel panelBlockAchievement = new JPanel();
        panelBlockAchievement.setSize(900, 250);

        panelBlockAchievement.setBackground(Color.red);
        panelBlockAchievement.setForeground(Color.red);
        panelBlockAchievement.setOpaque(false);
        panelBlockAchievement.setLayout(new BoxLayout(panelBlockAchievement, BoxLayout.X_AXIS));
        centerLocation = Registration.CenterLocationObject(frameAchievement);
        panelBlockAchievement.setLocation(centerLocation[0] - 670, centerLocation[1] - 100);

        JPanel panelForCharacter = new JPanel();
        panelForCharacter.setSize(500, 70);
        panelForCharacter.setOpaque(false);
        panelForCharacter.setLayout(new BoxLayout(panelForCharacter, BoxLayout.X_AXIS));
        panelForCharacter.setLocation(centerLocation[0] - 250, centerLocation[1] - 400);
        panelForCharacter.setOpaque(false);

        JPanel panelForDescription = new JPanel();
        panelForDescription.setSize(300, 50);
        panelForDescription.setBorder(BorderFactory.createLineBorder(new Color(254, 222, 143), 5, true));
        panelForDescription.setLayout(new BorderLayout());
        panelForDescription.setOpaque(false);

        JPanel panelGrid = new JPanel(new GridLayout(2, 0));
        panelGrid.setSize(1000, 300);
        centerLocation = Registration.CenterLocationObject(frameAchievement);
        panelGrid.setLocation(centerLocation[0] - 500, centerLocation[1] - 100);
        panelGrid.setOpaque(false);


        List<AchievementList> achievementList = SelectAchievementFromDB();

        JTextPane NameAchievement = new JTextPane();
        NameAchievement.setFont(CustomFont.CustomFont1().deriveFont(70f));
        NameAchievement.setForeground(new Color(254, 222, 143));
        NameAchievement.setBorder(BorderFactory.createLineBorder(new Color(254, 222, 143), 2, true));
        NameAchievement.setEditable(false);
        NameAchievement.setFocusable(false);
        NameAchievement.setPreferredSize(new Dimension(300, 60));

        NameAchievement.setOpaque(false);
        NameAchievement.setAlignmentX(Component.CENTER_ALIGNMENT);
        NameAchievement.setAlignmentY(Component.CENTER_ALIGNMENT);

        JButton buttonClose = new JButton();
        File imgAchievement = new File("src/resource/CloseImage.png").getAbsoluteFile();
        Image imgReadAchievement = ImageIO.read(imgAchievement);
        buttonClose.setSize(60, 60);
        Image newImg3 = imgReadAchievement.getScaledInstance((int) buttonClose.getSize().getWidth(), (int) buttonClose.getSize().getHeight(), Image.SCALE_SMOOTH);
        buttonClose.setIcon(new ImageIcon(newImg3));
        Main.parButton(buttonClose);
        int[] centerLocation1 = Registration.CenterLocationObject(frameAchievement);
        buttonClose.setLocation(centerLocation1[0] + 550, centerLocation1[1] - 380);
        buttonClose.addActionListener(e -> {
            try {
                new Main();
                frameAchievement.dispose();
            } catch (IOException | SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });


// Центрирование текста
        StyledDocument docName = NameAchievement.getStyledDocument();
        SimpleAttributeSet centerName = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerName, StyleConstants.ALIGN_CENTER);
        docName.setParagraphAttributes(0, docName.getLength(), centerName, false);

        JTextPane textDescription = new JTextPane();
        textDescription.setFont(CustomFont.CustomFont2().deriveFont(50f));
        textDescription.setForeground(new Color(254, 222, 143));
        textDescription.setEditable(false);
        textDescription.setFocusable(false);
        textDescription.setOpaque(false);
        textDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        textDescription.setAlignmentY(Component.CENTER_ALIGNMENT);

// Устанавливаем выравнивание по центру
        StyledDocument doc = textDescription.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        JTextPane textCharacter = new JTextPane();
        textCharacter.setPreferredSize(new Dimension(300, 100));
        textCharacter.setFont(CustomFont.CustomFont1().deriveFont(60f));
        textCharacter.setForeground(new Color(254, 222, 143));
        textCharacter.setEditable(false);
        textCharacter.setFocusable(false);
        textCharacter.setBorder(BorderFactory.createLineBorder(new Color(254, 222, 143), 2, true));
        textCharacter.setOpaque(false);
        textCharacter.setAlignmentX(Component.CENTER_ALIGNMENT);
        textCharacter.setAlignmentY(Component.CENTER_ALIGNMENT);

// Центрирование текста
        StyledDocument docChar = textCharacter.getStyledDocument();
        SimpleAttributeSet centerChar = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerChar, StyleConstants.ALIGN_CENTER);
        docChar.setParagraphAttributes(0, docChar.getLength(), centerChar, false);

        achievementList.stream()
                .filter(achievement -> achievement.getId() == FirstId(achievementList)) // Применение тернарного оператора
                .forEach(achievement -> {
                    NameAchievement.setText(achievement.getName());
                    textDescription.setText(achievement.getDescription());
                    textCharacter.setText(achievement.getNameCharacter());
                });

        textDescription.setOpaque(false);
        textDescription.setEditable(false);

        JButton buttonNextNameCharacter = new JButton();
        buttonNextNameCharacter.setPreferredSize(new Dimension(60, 40));
        buttonNextNameCharacter.setSize(50, 50);
        buttonNextNameCharacter.setFont(new Font("asfsafasfasf", Font.BOLD, 30));


        File imgButtonArrowRight = new File("src/resource/ButtonArrowRight.png").getAbsoluteFile();
        Image imgReadButtonArrowRight = ImageIO.read(imgButtonArrowRight);
        Image newImg4 = imgReadButtonArrowRight.getScaledInstance((int) buttonNextNameCharacter.getSize().getWidth(), (int) buttonNextNameCharacter.getSize().getHeight(), Image.SCALE_SMOOTH);
        buttonNextNameCharacter.setIcon(new ImageIcon(newImg4));
        parButton(buttonNextNameCharacter);

        buttonNextNameCharacter.addActionListener(e -> {
            ref.indexAchiv = 0;
            ref.id = 0;
            if (FirstIdCharacter == 2){
                ref.IdCharacter = FirstIdCharacter;
                FirstIdCharacter = 0;
            }
            int ID = ref.IdCharacter;
            if (ref.IdCharacter != 3) {
                ref.IdCharacter += 1;
            }
            for (int i = 0; i < 2; i++) {
                achievementList.stream()
                        .filter(achievement -> achievement.getIdCharacter() == ref.IdCharacter) // Применение тернарного оператора
                        .forEach(achievement -> {
                            ref.id = achievement.getId();
                        });
                if (ref.id == 0) {
                    ref.IdCharacter += 1;
                } else if (ref.id != 0) {
                    break;
                }
                if (ref.IdCharacter > 3) {
                    ref.IdCharacter = ID;
                }
            }

            achievementList.stream()
                    .filter(achievement -> achievement.getIdCharacter() == ref.IdCharacter) // Применение тернарного оператора
                    .forEach(achievement -> {
                        textCharacter.setText(achievement.getNameCharacter());
                    });

            List<AchievementList> achievementList1 = new ArrayList<>();
            achievementList.stream()
                    .filter(achievement -> achievement.getIdCharacter() == ref.IdCharacter)// Применение тернарного оператора
                    .forEach(achievement -> {
                        achievementList1.add(achievement);
                    });

            AchievementList previous = achievementList1.get(ref.indexAchiv);
            NameAchievement.setText(previous.getName());
            textDescription.setText(previous.getDescription());
        });


        JButton buttonNextNameAchievement = new JButton();
        buttonNextNameAchievement.setPreferredSize(new Dimension(60, 40));
        buttonNextNameAchievement.setSize(50, 50);
        buttonNextNameAchievement.setFont(new Font("asfsafasfasf", Font.BOLD, 30));

        File imgButtonArrowRight1 = new File("src/resource/ButtonArrowRight.png").getAbsoluteFile();
        Image imgReadButtonArrowRight1 = ImageIO.read(imgButtonArrowRight1);
        Image newImg1 = imgReadButtonArrowRight1.getScaledInstance((int) buttonNextNameAchievement.getSize().getWidth(), (int) buttonNextNameAchievement.getSize().getHeight(), Image.SCALE_SMOOTH);
        buttonNextNameAchievement.setIcon(new ImageIcon(newImg1));
        parButton(buttonNextNameAchievement);


        buttonNextNameAchievement.addActionListener(e -> {
            List<AchievementList> achievementList1 = new ArrayList<>();
            achievementList.stream()
                    .filter(achievement -> achievement.getIdCharacter() == ref.IdCharacter)// Применение тернарного оператора
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

        JButton buttonReversNameAchievement = new JButton();
        buttonReversNameAchievement.setPreferredSize(new Dimension(60, 40));
        buttonReversNameAchievement.setSize(50, 50);
        buttonReversNameAchievement.setFont(new Font("asfsafasfasf", Font.BOLD, 30));


        File imgButtonArrowLeftt1 = new File("src/resource/ButtonArrowLeftt.png").getAbsoluteFile();
        Image imgReadButtonArrowLeftt1 = ImageIO.read(imgButtonArrowLeftt1);
        Image newImg2 = imgReadButtonArrowLeftt1.getScaledInstance((int) buttonReversNameAchievement.getSize().getWidth(), (int) buttonReversNameAchievement.getSize().getHeight(), Image.SCALE_SMOOTH);
        buttonReversNameAchievement.setIcon(new ImageIcon(newImg2));
        parButton(buttonReversNameAchievement);


        buttonReversNameAchievement.addActionListener(e -> {
            List<AchievementList> achievementList1 = new ArrayList<>();
            achievementList.stream()
                    .filter(achievement -> achievement.getIdCharacter() == ref.IdCharacter)// Применение тернарного оператора
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

        JButton buttonReversNameCharacter = new JButton();
        buttonReversNameCharacter.setPreferredSize(new Dimension(60, 40));
        buttonReversNameCharacter.setSize(50, 50);
        buttonReversNameCharacter.setFont(new Font("asfsafasfasf", Font.BOLD, 30));


        File imgButtonArrowLeftt = new File("src/resource/ButtonArrowLeftt.png").getAbsoluteFile();
        Image imgReadButtonArrowLeftt = ImageIO.read(imgButtonArrowLeftt);
        Image newImg = imgReadButtonArrowLeftt.getScaledInstance((int) buttonReversNameCharacter.getSize().getWidth(), (int) buttonReversNameCharacter.getSize().getHeight(), Image.SCALE_SMOOTH);
        buttonReversNameCharacter.setIcon(new ImageIcon(newImg));
        parButton(buttonReversNameCharacter);


        buttonReversNameCharacter.addActionListener(e -> {
            ref.indexAchiv = 0;
            int ID = ref.IdCharacter;
            ref.id = 0;

            if (ref.IdCharacter > 1) {
                ref.IdCharacter -= 1;
            }
            for (int i = 0; i < 2; i++) {
                achievementList.stream()
                        .filter(achievement -> achievement.getIdCharacter() == ref.IdCharacter) // Применение тернарного оператора
                        .forEach(achievement -> {
                            ref.id = achievement.getId();
                        });
                if (ref.IdCharacter < 1) {
                    ref.IdCharacter = ID;
                    return;
                }

                if (ref.id == 0) {
                    if (ref.IdCharacter == 1) {
                        ref.IdCharacter = ID;
                        break;
                    }
                    ref.IdCharacter -= 1;
                }
            }

            achievementList.stream()
                    .filter(achievement -> achievement.getIdCharacter() == ref.IdCharacter) // Применение тернарного оператора
                    .forEach(achievement -> {
                        textCharacter.setText(achievement.getNameCharacter());
                    });

            List<AchievementList> achievementList1 = new ArrayList<>();
            achievementList.stream()
                    .filter(achievement -> achievement.getIdCharacter() == ref.IdCharacter)// Применение тернарного оператора
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
        panelForCharacter.setBorder(BorderFactory.createLineBorder(new Color(254, 222, 143), 2, true));

        panelForDescription.add(textDescription, BorderLayout.CENTER);

        panelGrid.add(panelBlockAchievement);
        panelGrid.add(panelForDescription);
        panelForCharacter.add(buttonReversNameCharacter);
        panelForCharacter.add(textCharacter);
        panelForCharacter.add(buttonNextNameCharacter);

        frameAchievement.add(panelForCharacter);

        frameAchievement.add(buttonClose);
        frameAchievement.add(panelGrid);
        frameAchievement.add(label);
        frameAchievement.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frameAchievement.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameAchievement.setUndecorated(true);
        frameAchievement.setVisible(true);
    }

    public static List<AchievementList> SelectAchievementFromDB() throws SQLException, ClassNotFoundException {
        Connection connection = JDBCPosgreSQLConnection.OpenConnection();
        List<AchievementList> achievementList = new ArrayList<>();
        String sql1 = "SELECT achievement.id AS id, achievement.name AS name, idcharacter, description, \"character\".name AS namecharacter " +
                "FROM achievement " +
                "JOIN \"character\" ON achievement.idcharacter = \"character\".id " +
                "JOIN userachievement ON userachievement.idachievement = achievement.id " +
                "WHERE userachievement.iduser = ?;";

        PreparedStatement stmt = connection.prepareStatement(sql1);
        stmt.setInt(1, Users.GetIdUser());
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

    public static int FirstId(List<AchievementList> achievementList) {
        AchievementList achievement = achievementList.get(0);
        FirstIdCharacter = achievement.getIdCharacter();
        return achievement.getId();
    }
}
