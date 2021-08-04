package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static util.Utils.*;

public class ForgotPasswordForm extends Form implements ActionListener {

    private GradientPanel mainPanel;
    private JTextField emailField;
    private JTextField passwordField;
    private JTextField retypedPassField;
    private JButton resetPasswordButton;

    public ForgotPasswordForm(ImageIcon icon) {

        initComponents();

        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(360, 550);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle(APPLICATION_TITLE);

        this.setIconImage(icon.getImage());

        this.setVisible(true);
    }

    @Override
    protected void initComponents() {

        mainPanel = new GradientPanel(new FlowLayout(FlowLayout.CENTER, 0 , 30));

        JLabel logo = new JLabel();
        setLogo(logo, 90, 90);


        emailField = new JTextField();
        setInputField(emailField);

        JLabel emailLabel = new JLabel("Email Address");
        emailLabel.setFont(fontLabels);
        setInputLabel(emailLabel);


        passwordField = new JPasswordField();
        setInputField(passwordField);

        JLabel passwordLabel = new JLabel("Email Address");
        emailLabel.setFont(fontLabels);
        setInputLabel(passwordLabel);


        retypedPassField = new JTextField();
        setInputField(retypedPassField);

        JLabel retypedPassLabel = new JLabel("Email Address");
        emailLabel.setFont(fontLabels);
        setInputLabel(retypedPassLabel);


        resetPasswordButton = new JButton("Reset Password");
        setSubmitButton(resetPasswordButton);


        mainPanel.add(logo);
        mainPanel.add(setSection(emailField, emailLabel, 5));
        mainPanel.add(setSection(passwordField, passwordLabel, 5));
        mainPanel.add(setSection(retypedPassField, retypedPassLabel, 5));
        mainPanel.add(resetPasswordButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
