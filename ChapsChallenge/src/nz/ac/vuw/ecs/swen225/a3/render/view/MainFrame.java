package nz.ac.vuw.ecs.swen225.a3.render.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    private JPanel mainPanel;
    private JMenu gameMenu;
    private JMenuItem testButton;
    private JMenu optionsMenu;
    private JMenuItem gameNewGame;
    private JMenuItem gameQuitGame;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JMenu levelMenu;
    private JMenu helpMenu;
    private JTextArea testTextArea;
    private JLabel timeLabel;
    private JLabel chipsLeftLabel;
    private JLabel levelLabel;
    private JLabel levelLabelValue;
    private JLabel timeLabelValue;
    private JLabel defaultChipsLeftLabelValue;
    private JPanel infoPanel;


    public MainFrame() {

        setTitle("Chap's Challenge");
//        setSize(WIDTH, HEIGHT);
        setMinimumSize(new Dimension(WIDTH,HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);

    }

    public JMenuItem getTestButton() {
        return testButton;
    }

    public JTextArea getTestTextArea() {
        return testTextArea;
    }

    public JPanel getLeftPanel() {
        return leftPanel;
    }
}
