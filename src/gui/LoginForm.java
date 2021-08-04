package gui;

import users.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static util.Utils.*;

public class LoginForm extends Form implements ActionListener {

    private GradientPanel mainPanel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private final ImageIcon icon;


    public LoginForm() {

        icon = new ImageIcon("src/assets/universityIcon.png");

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
        mainPanel = new GradientPanel(new FlowLayout(FlowLayout.CENTER, 0, 40));


        JLabel logo = new JLabel("Login");
        setLogo(logo, 90, 90);


        emailField = new JTextField();
        setInputField(emailField);

        JLabel emailLabel = new JLabel("Email Address");
        emailLabel.setFont(fontLabels);
        setInputLabel(emailLabel);


        passwordField = new JPasswordField();
        setInputField(passwordField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(fontLabels);
        passwordLabel.setForeground(Color.white);


        loginButton = new JButton("Login");
        setSubmitButton(loginButton);
        loginButton.addActionListener(this);


        JLabel forgotPassLabel = new JLabel("Forgot Password?");
        setClickableLabel(forgotPassLabel);
        forgotPassLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new ForgotPasswordForm(icon);
            }
        });

        JLabel registerLabel = new JLabel("You don't have an account?");
        setClickableLabel(registerLabel);
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new RegisterForm(icon);
            }
        });

        JPanel bottomPanel = new JPanel(new BorderLayout(15, 0));

        bottomPanel.add(forgotPassLabel, BorderLayout.WEST);
        bottomPanel.add(registerLabel, BorderLayout.EAST);
        bottomPanel.setOpaque(false);


        mainPanel.add(logo);
        mainPanel.add(setSection(emailField, emailLabel, 5));
        mainPanel.add(setSection(passwordField, passwordLabel, 5));
        mainPanel.add(loginButton);
        mainPanel.add(bottomPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        String table;

        if(email.contains("student")) {
            table = "students";
        } else {
            table = "teachers";
        }

        if(driver.doLogin(email, password, table)) {
            dispose();
            new Dashboard(
                    icon,
                    driver.getUser(email)
            );
            JOptionPane.showMessageDialog(loginButton, "You have successfully logged in!");
        } else {
            JOptionPane.showMessageDialog(loginButton, "Wrong username or password!");
        }
    }
}
