
package Frame.History;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

/**
 * Manages navigation between different pages in the story.
 * Uses CardLayout to switch between different panels (pages).
 */
public class StoryManager {
    /** The container panel that holds all story pages */
    private final JPanel container;
    /** The CardLayout used to switch between pages */
    private final CardLayout layout;

    /**
     * Constructor for StoryManager
     * 
     * @param container The panel container that will hold all story pages
     * @param layout The CardLayout used to switch between pages
     */
    public StoryManager(JPanel container, CardLayout layout) {
        this.container = container;
        this.layout = layout;
    }

    /**
     * Starts the story from a specific page
     * 
     * @param key The identifier of the starting page
     * @param storyData Data needed for the story
     * @throws IOException If there is an error loading resources
     * @throws FontFormatException If there is an error loading custom fonts
     */
    public void startStory(String key, Map<String, Object> storyData) throws IOException, FontFormatException {
        showPage(key, storyData);
    }

    /**
     * Shows a specific page in the story
     * 
     * @param key The identifier of the page to show
     * @param storyData Data needed for the story
     * @throws IOException If there is an error loading resources
     * @throws FontFormatException If there is an error loading custom fonts
     */
    public void showPage(String key, Map<String, Object> storyData) throws IOException, FontFormatException {
        JPanel panel = PageFactory.createPage(key, storyData, this);
        if (panel != null) {
            container.add(panel, key);
            layout.show(container, key);
        }
    }
}
