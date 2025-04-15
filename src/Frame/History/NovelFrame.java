
package Frame.History;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.util.Map;
import com.google.gson.Gson;

public class NovelFrame extends JFrame {
    private final CardLayout layout = new CardLayout();
    private final JPanel container = new JPanel(layout);
    private Map<String, Object> storyData;
    private final StoryManager storyManager;

    public NovelFrame(String path) {
        setTitle("Новелла");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        add(container);
        storyManager = new StoryManager(container, layout);
        loadStory(path);
        storyManager.startStory("start", storyData);
        setVisible(true);
    }

    private void loadStory(String path) {
        try (FileReader reader = new FileReader(path)) {
            Gson gson = new Gson();
            storyData = gson.fromJson(reader, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
