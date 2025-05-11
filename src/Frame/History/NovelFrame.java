
package Frame.History;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import com.google.gson.Gson;

/**
 * Main frame for displaying the novel/story.
 * Uses CardLayout to switch between different pages of the story.
 */
public class NovelFrame extends JFrame {
    /** CardLayout used to switch between different pages */
    private final CardLayout layout = new CardLayout();
    /** Container panel that holds all story pages */
    private final JPanel container = new JPanel(layout);
    /** Map containing all story data loaded from JSON */
    private Map<String, Object> storyData;
    /** StoryManager instance to handle navigation between pages */
    private final StoryManager storyManager;

    /**
     * Constructor that initializes the frame and loads the story
     * 
     * @param path Path to the JSON file containing the story data
     * @throws IOException If there is an error loading resources
     * @throws FontFormatException If there is an error loading custom fonts
     */
    public NovelFrame(String path, String startData) throws IOException, FontFormatException {
        setTitle("Новелла");
        setSize(1920, 1080);
        setLocationRelativeTo(null);

        add(container);
        storyManager = new StoryManager(container, layout);
        loadStory(path);
        storyManager.startStory(startData, storyData);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);// Убираем границы и заголовок окна
        setVisible(true);
    }

    /**
     * Loads story data from a JSON file
     * 
     * @param path Path to the JSON file containing the story data
     */
    private void loadStory(String path) {
        try (FileReader reader = new FileReader(path)) {
            Gson gson = new Gson();
            storyData = gson.fromJson(reader, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
