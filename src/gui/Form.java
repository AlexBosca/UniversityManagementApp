package gui;

import database.Driver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static util.Utils.*;

public abstract class Form extends JFrame {

    protected final Driver driver;


    protected Form() {
        this.driver = Driver.getInstance();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                driver.closeConnection();
            }
        });
    }

    protected void setInputField(JTextField textField) {
        textField.setColumns(25);
        textField.setOpaque(false);
        textField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
        textField.setCaretColor(Color.white);
        textField.setFont(font);
        textField.setForeground(Color.white);
    }

    protected void setInputLabel(JLabel label) {
        label.setFont(fontLabels);
        label.setForeground(Color.white);
    }

    protected void setClickableLabel(JLabel label) {
        label.setFont(fontLabels);
        label.setForeground(Color.white);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    protected void setSubmitButton(JButton button) {
        button.setPreferredSize(new Dimension(250, 40));
        button.setFont(fontButtons);
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setForeground(Color.white);
        button.setBorder(new RoundedBorder(30));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    protected void setRadioButton(JRadioButton radioButton) {
        radioButton.setFont(fontLabels);
        radioButton.setOpaque(false);
        radioButton.setRequestFocusEnabled(false);
        radioButton.setForeground(Color.white);
    }

    protected void setLogo(JLabel logo, int weight, int height) {
        BufferedImage labelImage = null;

        try {
            labelImage = ImageIO.read(new File("src/assets/universityIcon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        logo.setIcon(new ImageIcon(getScaledImage(labelImage, weight, height)));
        logo.setHorizontalTextPosition(JLabel.CENTER);
        logo.setVerticalTextPosition(JLabel.BOTTOM);
        logo.setForeground(Color.white);
    }

    protected Image getScaledImage(Image image, int weight, int height) {
        BufferedImage resizedImage = new BufferedImage(weight, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = resizedImage.createGraphics();

        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(image, 0, 0, weight, height, null);
        graphics2D.dispose();

        return resizedImage;
    }

    protected JPanel setSection(JTextField textField, JLabel label, int verticalGap) {
        JPanel emailPanel = new JPanel(new BorderLayout(0, verticalGap));
        emailPanel.add(label, BorderLayout.NORTH);
        emailPanel.add(textField, BorderLayout.SOUTH);
        emailPanel.setOpaque(false);

        return emailPanel;
    }


    protected abstract void initComponents();
}
