package gui;

import users.Student;
import users.Teacher;
import users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static util.Utils.*;

public class RegisterForm extends Form implements ActionListener {

    private GradientPanel mainPanel;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField phoneNumberField;
    private JPasswordField passwordField;
    private JPasswordField retypedPasswordField;
    private JRadioButton userStateRadioBtn;
    private JButton registerButton;
    private JLabel loginLabel;


    public RegisterForm(ImageIcon icon) {

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

        mainPanel = new GradientPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
//        mainPanel.setAlignmentY(JPanel.CENTER_ALIGNMENT);

        JLabel logo = new JLabel();
        setLogo(logo, 80, 80);

        firstNameField = new JTextField();
        setInputField(firstNameField);

        JLabel firstNameLabel = new JLabel("First Name");
        firstNameLabel.setFont(fontLabels);
        setInputLabel(firstNameLabel);


        lastNameField = new JTextField();
        setInputField(lastNameField);

        JLabel lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setFont(fontLabels);
        setInputLabel(lastNameLabel);


        phoneNumberField = new JTextField();
        setInputField(phoneNumberField);

        JLabel phoneLabel = new JLabel("Phone Number");
        phoneLabel.setFont(fontLabels);
        setInputLabel(phoneLabel);


        passwordField = new JPasswordField();
        setInputField(passwordField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(fontLabels);
        setInputLabel(passwordLabel);


        retypedPasswordField = new JPasswordField();
        setInputField(retypedPasswordField);

        JLabel retypedPassLabel = new JLabel("Retype Password");
        retypedPassLabel.setFont(fontLabels);
        setInputLabel(retypedPassLabel);


        userStateRadioBtn = new JRadioButton("Are you a teacher?");
        setRadioButton(userStateRadioBtn);


        registerButton = new JButton("Register");
        setSubmitButton(registerButton);
        registerButton.addActionListener(this);

        loginLabel = new JLabel("Already have an account?");
        setClickableLabel(loginLabel);
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new LoginForm();
            }
        });


        mainPanel.add(logo);
        mainPanel.add(setSection(firstNameField, firstNameLabel,3));
        mainPanel.add(setSection(lastNameField, lastNameLabel, 3));
        mainPanel.add(setSection(phoneNumberField, phoneLabel, 3));
        mainPanel.add(setSection(passwordField, passwordLabel, 3));
        mainPanel.add(setSection(retypedPasswordField, retypedPassLabel, 3));
        mainPanel.add(userStateRadioBtn);
        mainPanel.add(registerButton);
        mainPanel.add(loginLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String phoneNumber = phoneNumberField.getText();
        String password = new String(passwordField.getPassword());
        String retypedPass = new String(retypedPasswordField.getPassword());
        boolean isTeacher = userStateRadioBtn.isSelected();

        User user = null;

        if(isTeacher) {
            user = new Teacher(firstName, lastName, phoneNumber, password);
        } else {
            user = new Student(firstName, lastName, phoneNumber, password);
        }

        if(!password.equals(retypedPass)) {
            JOptionPane.showMessageDialog(registerButton, "Your passwords doesn't match!");
            return;
        }

        if(driver.enrollUser(user, isTeacher)) {
            dispose();
            new LoginForm();
            JOptionPane.showMessageDialog(registerButton, "Your have successfully registered");
        } else {
            JOptionPane.showMessageDialog(registerButton, "Something went wrong during registration!");
        }
    }
}
