
package Frame.History;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class StoryManager {
    private final JPanel container;
    private final CardLayout layout;

    public StoryManager(JPanel container, CardLayout layout) {
        this.container = container;
        this.layout = layout;
    }

    public void startStory(String key, Map<String, Object> storyData) throws IOException, FontFormatException {
        showPage(key, storyData);
    }

    public void showPage(String key, Map<String, Object> storyData) throws IOException, FontFormatException {
        JPanel panel = PageFactory.createPage(key, storyData, this);
        if (panel != null) {
            container.add(panel, key);
            layout.show(container, key);
        }
    }
}
