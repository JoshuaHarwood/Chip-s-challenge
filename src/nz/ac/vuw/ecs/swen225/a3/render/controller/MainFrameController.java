package nz.ac.vuw.ecs.swen225.a3.render.controller;

import nz.ac.vuw.ecs.swen225.a3.maze.Tile;
import nz.ac.vuw.ecs.swen225.a3.render.view.BoardCanvas;
import nz.ac.vuw.ecs.swen225.a3.render.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrameController {

    private MainFrame mainFrame;
    private JMenuItem testButton;
    private JTextArea testTextArea;

    private JPanel leftPanel;

    private BoardCanvas canvas;
    private Graphics g;

    private Tile[][] tiles;

    public MainFrameController(Tile[][] tiles) {
        this.tiles = tiles;

        initComponents();
        initListeners();
    }

    private void initComponents() {
        mainFrame = new MainFrame();

        testButton = mainFrame.getTestButton();
        testTextArea = mainFrame.getTestTextArea();
        leftPanel = mainFrame.getLeftPanel();
    }

    private void initListeners()
    {

        initKeys();

        testButton.addActionListener(new TestButtonListener());
    }

    private void initKeys(){

        AbstractAction w = new Waction();
        leftPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"),"WPressed");
        leftPanel.getActionMap().put("WPressed",w);

        AbstractAction a = new Aaction();
        leftPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"),"APressed");
        leftPanel.getActionMap().put("APressed",a);

        AbstractAction s = new Saction();
        leftPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"),"SPressed");
        leftPanel.getActionMap().put("SPressed",s);

        AbstractAction d = new Daction();
        leftPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"),"DPressed");
        leftPanel.getActionMap().put("DPressed",d);

    }

    public void showMainFrameWindow() {
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    /**
     * Temporary method for testing GUI map generation
     */
    public void createBoard() {

        JPanel board = new JPanel();

        canvas = new BoardCanvas(tiles[0].length, tiles.length, tiles);

        board.add(canvas);

        leftPanel.add(board);

        showMainFrameWindow();

    }

    public void redrawBoard() {
        canvas.drawBoard(canvas.getGraphics(), leftPanel.getWidth(), leftPanel.getHeight());
    }

    private class TestButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            testTextArea.append("\nButton Pressed, Drawing Test Grid");
            redrawBoard();
        }
    }

    private class Waction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {

            testTextArea.append("W has been pressed!");
        }
    }

    private class Aaction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            testTextArea.append("A has been pressed!");
        }
    }

    private class Saction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            testTextArea.append("S has been pressed!");
        }
    }

    private class Daction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            testTextArea.append("D has been pressed!");
        }
    }


}
