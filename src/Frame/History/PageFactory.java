
package Frame.History;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class PageFactory {
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
