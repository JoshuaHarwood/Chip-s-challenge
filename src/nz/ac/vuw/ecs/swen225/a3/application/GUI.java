package nz.ac.vuw.ecs.swen225.a3.application;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import nz.ac.vuw.ecs.swen225.a3.maze.Maze;
import nz.ac.vuw.ecs.swen225.a3.render.BoardCanvas;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;

/**
 * A GUI with a canvas for displaying the game, as well as other
 * information related to the game state, options and more.
 * (Partly generated using Eclipse's WindowBuilder plugin)
 * @author Henry Stoupe
 */
public class GUI {
	
	BoardCanvas canvas;
	Maze maze;
	private JPanel boardPanel;

	private JFrame frame;
	//private final JTextArea textPanel = new JTextArea();
	private JPanel leftPanel;

	/**
	 * Run GUI on its own.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GUI window = new GUI();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public GUI(Maze maze) {
		
		this.maze = maze;
		this.canvas = new BoardCanvas(maze);
		initialize();

		addBoard();
		
		showGUI();
	}
	
	public void addBoard() {
		
		boardPanel = new JPanel();
		boardPanel.add(canvas);
        leftPanel.add(boardPanel);

		frame.setVisible(true);
	}
	
	public void showGUI() {
        frame.setVisible(true);
	}
	
	public void drawBoard() {
	    canvas.draw(canvas.getGraphics(), leftPanel.getWidth(), leftPanel.getHeight());
        showGUI();
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Chap's Challenge");
		frame.addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent arg0) {
				drawBoard();
			}
		});
		frame.setBounds(100, 100, 812, 636);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu menuGame = new JMenu("Game");
		menuBar.add(menuGame);
		
		JMenu menuOptions = new JMenu("Options");
		menuBar.add(menuOptions);
		
		JMenuItem menuOptionsRedraw = new JMenuItem("Redraw Board");
		menuOptionsRedraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawBoard();
			}
		});
		menuOptions.add(menuOptionsRedraw);
		
		JMenu menuLevel = new JMenu("Level");
		menuBar.add(menuLevel);
		
		JMenu menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		leftPanel = new JPanel();
		leftPanel.setMinimumSize(new Dimension(640, 640));
		leftPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		leftPanel.setForeground(Color.DARK_GRAY);
		frame.getContentPane().add(leftPanel, BorderLayout.CENTER);
		leftPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel rightPanel = new JPanel();
		frame.getContentPane().add(rightPanel, BorderLayout.EAST);
		rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		//textPanel.setText("asdf");
		//rightPanel.add(textPanel);
		
		JButton btnNewButton = new JButton("New button");
		rightPanel.add(btnNewButton);
	}

	public JPanel getLeftPanel() {
		return leftPanel;
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
