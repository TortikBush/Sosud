package HelpClasses;
import javax.swing.*;
import java.awt.*;

public class FadeImagePanel extends JPanel {
    private final Image image;
    private float alpha = 0f; // Прозрачность

    public FadeImagePanel(Image image) {
        this.image = image;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            g2.dispose();
        }
    }

    public void fadeIn(int durationMillis) {
        Timer timer = new Timer(40, null);
        long start = System.currentTimeMillis();
        timer.addActionListener(e -> {
            long elapsed = System.currentTimeMillis() - start;
            alpha = Math.min(1f, (float) elapsed / durationMillis);
            repaint();
            if (alpha >= 1f) timer.stop();
        });
        timer.start();
    }

    public void fadeOut(int durationMillis, Runnable onComplete) {
        Timer timer = new Timer(40, null);
        long start = System.currentTimeMillis();
        timer.addActionListener(e -> {
            long elapsed = System.currentTimeMillis() - start;
            alpha = Math.max(0f, 1f - (float) elapsed / durationMillis);
            repaint();
            if (alpha <= 0f) {
                timer.stop();
                if (onComplete != null) onComplete.run();
            }
        });
        timer.start();
    }
}
