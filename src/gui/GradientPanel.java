package gui;

import javax.swing.*;
import java.awt.*;

class GradientPanel extends JPanel {
    public GradientPanel(FlowLayout flowLayout) {
        super(flowLayout);
    }

    public GradientPanel(BorderLayout borderLayout) {
        super(borderLayout);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        Color color1 = Color.decode("#DD7A7");
        Color color2 = new Color(86, 160, 211);

        GradientPaint gradientPaint = new GradientPaint(0, 0, color1, 180, height, color2);
        graphics2D.setPaint(gradientPaint);
        graphics2D.fillRect(0, 0, width, height);
    }
}
