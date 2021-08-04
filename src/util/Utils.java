package util;

import javax.swing.*;
import java.awt.*;

public interface Utils {
    String APPLICATION_TITLE = "University Management System";
    String APPLICATION_LABEL = "University Application";
    Font font = new Font("SansSerif", Font.BOLD, 14);
    Font fontLabels = new Font("SansSerif", Font.BOLD, 12);
    Font fontButtons = new Font("SansSerif", Font.BOLD, 15);
    Font fontNavigation = new Font("SansSerif", Font.BOLD, 18);

    void setInputField(JTextField textField);
    void setInputLabels(JLabel label);
    void setClickableLabel(JLabel label);
    void setSubmitButton(JButton button);
    void setLogo(JLabel label);
    Image getScaledImage(Image image, int weight, int height);

}
