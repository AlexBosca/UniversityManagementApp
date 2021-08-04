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

    private static final int NAVIGATION_HORIZONTAL_GAP = 20;

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

//        JLabel logo = new JLabel(APPLICATION_LABEL, JLabel.LEFT);
        JLabel logo = new JLabel(APPLICATION_LABEL, JLabel.CENTER) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(300, 50);
            }
        };
        setupLogo(logo, 40, 40);

        menuBar.add(logo);
        menuBar.add(Box.createRigidArea(new Dimension(0, NAVIGATION_HORIZONTAL_GAP)));
        menuBar.add(createMenuOption(user.getName()));
        menuBar.add(Box.createRigidArea(new Dimension(0, NAVIGATION_HORIZONTAL_GAP)));
        menuBar.add(createMenuOption("Home"));
        menuBar.add(Box.createRigidArea(new Dimension(0, NAVIGATION_HORIZONTAL_GAP)));
        menuBar.add(createMenuOption("Homework"));
        menuBar.add(Box.createRigidArea(new Dimension(0, NAVIGATION_HORIZONTAL_GAP)));
        menuBar.add(createMenuOption("Announcements"));
        menuBar.add(Box.createRigidArea(new Dimension(0, NAVIGATION_HORIZONTAL_GAP)));
        menuBar.add(createMenuOption("Calendar"));

        menuBar.setOpaque(false);

        backgroundPanel.add(pane);
        backgroundPanel.add(menuBar, BorderLayout.LINE_START);

    }

//    @Override
//    protected void setLogo(JLabel logo, int weight, int height) {
//        BufferedImage labelImage = null;
//
//        try {
//            labelImage = ImageIO.read(new File("src/assets/logo.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        logo.setIcon(new ImageIcon(getScaledImage(labelImage, weight, height)));
//        logo.setForeground(Color.white);
//        logo.setVerticalTextPosition(JLabel.CENTER);
//        logo.setHorizontalTextPosition(JLabel.RIGHT);
//        logo.setFont(fontNavigation);
//        logo.setBorder(new EmptyBorder(0, 10, 0, 0));
//        logo.setIconTextGap(20);
//    }

    private void setupLogo(JLabel logo, int weight, int height) {
        logo.setForeground(Color.white);
//        logo.setHorizontalTextPosition(JLabel.CENTER);
        logo.setFont(fontNavigation);
        logo.setBorder(new EmptyBorder(0, 10, 0, 0));
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
                case "Calendar" :
                    image = ImageIO.read(new File("src/assets/calendarIcon.png"));
                    break;
                default :
                    image = ImageIO.read(new File("src/assets/profileIcon.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        menu.setIcon(new ImageIcon(getScaledImage(image, 40, 40)));
        menu.setBorder(new EmptyBorder(0, 10, 0, 0));
        menu.setIconTextGap(20);

        return menu;
    }
}
