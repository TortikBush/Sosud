
package Frame.History;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

/**
 * Factory class for creating different types of page panels based on the story data.
 * Uses the "type" field in the node data to determine which page class to instantiate.
 */
public class PageFactory {
    /**
     * Creates a page panel based on the specified key and data
     * 
     * @param key The key identifying the node in the data
     * @param data The map containing all story data
     * @param manager The StoryManager instance to handle navigation between pages
     * @return A JPanel instance of the appropriate page type, or null if the type is not recognized
     * @throws IOException If there is an error loading resources
     * @throws FontFormatException If there is an error loading custom fonts
     */
    public static JPanel createPage(String key, Map<String, Object> data, StoryManager manager) throws IOException, FontFormatException {
        Map<String, Object> node = (Map<String, Object>) data.get(key);
        String type = (String) node.get("type");

        return switch (type) {
            case "monologue" -> new MonologuePage(node, manager, data);
            case "dialogue" -> new DialoguePage(node, manager, data);
            case "choice" -> new ChoicePage(node, manager, data);
            case "first" -> new FirstPages(node, manager, data);
            case "heroView" -> new HeroViewPage(node, manager, data);
            default -> null;
        };
    }
}
