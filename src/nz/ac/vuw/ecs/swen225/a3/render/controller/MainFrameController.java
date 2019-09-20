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

    private void initListeners() {
        testButton.addActionListener(new TestButtonListener());
    }

    public void showMainFrameWindow() {
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    /**
     * Temporary method for testing GUI map generation
     */
    public void createBoard() {

//        GridLayout gl = new GridLayout(10,10);
//        gl.setHgap(0);
//        gl.setVgap(0);
//
//        JPanel grid = new JPanel(gl);
//
//        ImageIcon image = new ImageIcon("src\\nz\\ac\\vuw\\ecs\\swen225\\a3\\maze\\icons\\key_cyan.png");
//        for (int i = 0; i < 100; i++) {
//            grid.add(new JLabel(image));
//        }
//
//        leftPanel.add(grid);

        JPanel board = new JPanel();

        canvas = new BoardCanvas(tiles[0].length, tiles.length, tiles);

        board.add(canvas);

        leftPanel.add(board);

        showMainFrameWindow();

    }

    public void redrawBoard() {
        canvas.drawBoard(canvas.getGraphics());
    }

    private class TestButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

//            testTextArea.append("\nButton Pressed, drawing test line");
//            canvas.drawTestLine(canvas.getGraphics());

            testTextArea.append("\nButton Pressed, Drawing Test Grid");
            redrawBoard();
        }
    }
}
