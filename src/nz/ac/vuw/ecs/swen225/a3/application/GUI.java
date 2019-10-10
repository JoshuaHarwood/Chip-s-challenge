package nz.ac.vuw.ecs.swen225.a3.application;

import java.awt.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;

import nz.ac.vuw.ecs.swen225.a3.maze.Maze;
import nz.ac.vuw.ecs.swen225.a3.persistence.Persistence;
import nz.ac.vuw.ecs.swen225.a3.render.BoardCanvas;
import nz.ac.vuw.ecs.swen225.a3.render.InventoryCanvas;
import nz.ac.vuw.ecs.swen225.a3.render.LabelCanvas;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowStateListener;
import java.io.*;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

import java.util.stream.Stream;


/**
 * A GUI with a canvas for displaying the game, as well as other
 * information related to the game state, options and more.
 * (Partly generated using Eclipse's WindowBuilder plugin)
 * @author Henry Stoupe  - //TODO add ID number & Joshua Harwood---300439084
 */
public class GUI {

	Maze maze;

	private JFrame frame;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel inventoryPanel;
	private JPanel labelPanel;

	private InventoryCanvas inventoryCanvas;
	private LabelCanvas labelCanvas;
	private BoardCanvas boardCanvas;
	private JPanel boardPanel;

	private Color bgColor = new Color(92, 175, 219);


	private JMenuItem mnPause;
	private JMenuItem mnQuit;
	private final Action action = new SwingAction();


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
	 * @param maze The maze object of the game to draw
	 */
	public GUI(Maze maze) {

		this.maze = maze;

		initialize();

		showGUI();
		
	}


	/**
	 * Shows the GUI.
	 */
	public void showGUI() {
        frame.setVisible(true);
	}
	
	/**
	 * Hide the GUI
	 */
	public void hideGUI() {
		frame.setVisible(false);
	}

	/**
	 * Draws the board.
	 */
	public void drawBoard() {

		boardCanvas.draw(leftPanel.getWidth(), leftPanel.getHeight());
	    inventoryCanvas.draw();
	    labelCanvas.draw();

        showGUI();
        //TODO update the timer, update the treasureleft
        //TODO if getTimeLeft < 0 user loses that level
        //show popup allowing the user to try again or quit
        //System.out.println(maze.getTimeLeft() + " seconds left");
        //System.out.println(maze.getTreasureLeft() + " treasures left");
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Chap's Challenge");
		frame.getContentPane().setBackground(bgColor);
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

		mnPause = new JMenuItem("Pause");
		mnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maze.pause();
			}
		});
		menuGame.add(mnPause);

		mnQuit = new JMenuItem("Quit");
		mnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				maze.pause();
				int input = JOptionPane.showConfirmDialog(frame, "Are you sure you want to QUIT any UNSAVED progress will be LOST", "Quit?", JOptionPane.YES_NO_OPTION);
				if(input==0) {
					System.exit(0);
				}
			}
		});

		menuGame.add(mnQuit);

		JMenu menuOptions = new JMenu("Options");
		menuBar.add(menuOptions);

		JMenuItem menuOptionsRedraw = new JMenuItem("Redraw Board");
		menuOptionsRedraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				drawBoard();
			}
		});

		JMenuItem saveButton = new JMenuItem("Save Game");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = JOptionPane.showInputDialog("Please enter a name for the save");
				Persistence.save(maze, name);
			}
		});

		JMenuItem loadButton = new JMenuItem("Load game");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JsonObject obj = null;

				final JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(frame);
				File f = fc.getSelectedFile();


				try {
					InputStream i = new FileInputStream(f);

					 obj = Json.createReader(new FileInputStream(f))
							.readObject();

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}



				Persistence.load(obj);
			}
		});

		menuOptions.add(menuOptionsRedraw);
		menuOptions.add(saveButton);
		menuOptions.add(loadButton);

		JMenu menuLevel = new JMenu("Level");
		menuBar.add(menuLevel);

		JMenu menuHelp = new JMenu("Help");
		menuHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				maze.helpAlert();
			}
		});

		menuBar.add(menuHelp);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);

		leftPanel = new JPanel();
		leftPanel.setBackground(bgColor);
		GridBagConstraints gbc_leftPanel = new GridBagConstraints();
		gbc_leftPanel.insets = new Insets(0, 0, 0, 5);
		gbc_leftPanel.fill = GridBagConstraints.BOTH;
		gbc_leftPanel.gridx = 0;
		gbc_leftPanel.gridy = 0;
		frame.getContentPane().add(leftPanel, gbc_leftPanel);
		leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		boardPanel = new JPanel();
		boardPanel.setBackground(bgColor);
		boardPanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		leftPanel.add(boardPanel);

		boardCanvas = new BoardCanvas(maze);
		boardPanel.add(boardCanvas);

		rightPanel = new JPanel();
		rightPanel.setBackground(bgColor);
		GridBagConstraints gbc_rightPanel = new GridBagConstraints();
		gbc_rightPanel.fill = GridBagConstraints.BOTH;
		gbc_rightPanel.gridx = 1;
		gbc_rightPanel.gridy = 0;
		frame.getContentPane().add(rightPanel, gbc_rightPanel);
		GridBagLayout gbl_rightPanel = new GridBagLayout();
		gbl_rightPanel.columnWidths = new int[]{202, 0};
		gbl_rightPanel.rowHeights = new int[]{80, 266, 21, 0, 80, 0};
		gbl_rightPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_rightPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		rightPanel.setLayout(gbl_rightPanel);

		inventoryPanel = new JPanel();
		inventoryPanel.setBackground(bgColor);
		GridBagConstraints gbc_inventoryPanel = new GridBagConstraints();
		gbc_inventoryPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_inventoryPanel.anchor = GridBagConstraints.WEST;
		gbc_inventoryPanel.insets = new Insets(0, 0, 5, 0);
		gbc_inventoryPanel.gridx = 0;
		gbc_inventoryPanel.gridy = 1;
		rightPanel.add(inventoryPanel, gbc_inventoryPanel);

		inventoryCanvas = new InventoryCanvas(maze);
		inventoryPanel.add(inventoryCanvas);

		labelPanel = new JPanel();
		labelPanel.setBackground(bgColor);
		GridBagConstraints gbc_labelPanel = new GridBagConstraints();
		gbc_labelPanel.anchor = GridBagConstraints.WEST;
		gbc_labelPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_labelPanel.insets = new Insets(0, 0, 5, 0);
		gbc_labelPanel.gridx = 0;
		gbc_labelPanel.gridy = 3;
		rightPanel.add(labelPanel, gbc_labelPanel);

		labelCanvas = new LabelCanvas(maze);
		labelPanel.add(labelCanvas);
	}

	/**
	 * Gets the left panel.
	 * @return the left panel
	 */
	public JPanel getLeftPanel() {
		return leftPanel;
	}

	/**
	 * Gets the frame.
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Gets the right panel.
	 * @return the right panel
	 */
	public JPanel getRightPanel() {
		return rightPanel;
	}

	/**
	 * Gets the board canvas.
	 * @return the board canvas
	 */
	public BoardCanvas getBoardCanvas() {
		return boardCanvas;
	}

	/**
	 * Gets the inventory canvas.
	 * @return the inventory canvas
	 */
	public InventoryCanvas getInventoryCanvas() {
		return inventoryCanvas;
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
