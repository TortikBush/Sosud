
package Frame.History;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class PageFactory {
    public static JPanel createPage(String key, Map<String, Object> data, StoryManager manager) throws IOException, FontFormatException {
        Map<String, Object> node = (Map<String, Object>) data.get(key);
        String type = (String) node.get("type");

        switch (type) {
            case "monologue":
                return new MonologuePage(node, manager, data);
            case "dialogue":
                return new DialoguePage(node, manager, data);
            case "choice":
                return new ChoicePage(node, manager, data);
            default:
                return null;
        }
    }
}
