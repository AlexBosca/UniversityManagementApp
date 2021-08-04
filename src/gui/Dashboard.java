package gui;

import database.Driver;
import users.User;
import util.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static util.Utils.*;

public class Dashboard extends Form {

    private GradientPanel backgroundPanel;
    private JPanel mainPanel;
    private Driver driver;
    private User user;
//    private ImageIcon icon;

    public Dashboard(ImageIcon icon, User user) {
        this.driver = Driver.getInstance();
//        this.icon = icon;
        this.user = user;

        initComponents();

        this.setContentPane(backgroundPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle(Utils.APPLICATION_TITLE);

        this.setIconImage(icon.getImage());

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                driver.closeConnection();
            }
        });

        this.setVisible(true);
    }

    @Override
    protected void initComponents() {
        backgroundPanel = new GradientPanel(new BorderLayout());
        backgroundPanel.setBorder(new EmptyBorder(40, 0, 0, 0));

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.white);

        JScrollPane pane = new JScrollPane(mainPanel);
        pane.setBorder(BorderFactory.createEmptyBorder());

        JPanel menuBar = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(300, backgroundPanel.getHeight());
            }
        };
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.PAGE_AXIS));
        menuBar.setAlignmentX(SwingConstants.LEFT);

        JLabel logo = new JLabel(APPLICATION_LABEL, JLabel.LEFT);
        setLogo(logo, 40, 40);

        menuBar.add(logo);
        menuBar.add(Box.createRigidArea(new Dimension(0, 5)));
        menuBar.add(createMenuOption(user.getName()));
        menuBar.add(Box.createRigidArea(new Dimension(0, 5)));
        menuBar.add(createMenuOption("Home"));
        menuBar.add(Box.createRigidArea(new Dimension(0, 5)));
        menuBar.add(createMenuOption("Homework"));
        menuBar.add(Box.createRigidArea(new Dimension(0, 5)));
        menuBar.add(createMenuOption("Announcements"));

        menuBar.setOpaque(false);

        backgroundPanel.add(pane);
        backgroundPanel.add(menuBar, BorderLayout.LINE_START);

    }

    @Override
    protected void setLogo(JLabel logo, int weight, int height) {
        super.setLogo(logo, weight, height);

        logo.setVerticalTextPosition(JLabel.CENTER);
        logo.setHorizontalTextPosition(JLabel.RIGHT);
        logo.setFont(fontNavigation);
//        logo.setForeground(Color.black);
        logo.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    private JLabel createMenuOption(String title) {
        JLabel menu = new JLabel(title) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(300, 50);
            }
        };

        menu.setForeground(Color.white);
        menu.setVerticalTextPosition(JLabel.CENTER);
        menu.setFont(fontNavigation);
        menu.setHorizontalTextPosition(JLabel.RIGHT);

        BufferedImage image = null;

        try {
            switch (title) {
                case "Home":
                    image = ImageIO.read(new File("src/assets/homeIcon.png"));
                    break;
                case "Homework" :
                    image = ImageIO.read(new File("src/assets/homeworkIcon.png"));
                    break;
                case "Announcements" :
                    image = ImageIO.read(new File("src/assets/announcementsIcon.png"));
                    break;
                default :
                    image = ImageIO.read(new File("src/assets/profileIcon.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        menu.setIcon(new ImageIcon(getScaledImage(image, 40, 40)));

        return menu;
    }
}
