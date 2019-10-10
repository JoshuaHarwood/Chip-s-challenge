package nz.ac.vuw.ecs.swen225.a3.application;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;

import nz.ac.vuw.ecs.swen225.a3.maze.Maze;
import nz.ac.vuw.ecs.swen225.a3.render.BoardCanvas;
import nz.ac.vuw.ecs.swen225.a3.render.InventoryCanvas;
import nz.ac.vuw.ecs.swen225.a3.render.LabelCanvas;

import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;

/**
 * A GUI with a canvas for displaying the game, as well as other
 * information related to the game state, options and more.
 * (Partly generated using Eclipse's WindowBuilder plugin)
 * @author Henry Stoupe - //TODO add ID number
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

		initCanvases();
		addInventoryPanel();
		addLabels();

		showGUI();
	}

	/**
	 * Initializes the canvases.
	 */
	public void initCanvases() {
//		boardCanvas = new BoardCanvas(maze);
//		leftPanel.add(boardCanvas);
	}

	/**
	 * Adds the inventory panel to the GUI.
	 */
	public void addInventoryPanel() {
	}
	
	/**
	 * Adds the labels to the GUI.
	 */
	public void addLabels() {
	}

	/**
	 * Shows the GUI.
	 */
	public void showGUI() {
        frame.setVisible(true);
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
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		leftPanel = new JPanel();
		GridBagConstraints gbc_leftPanel = new GridBagConstraints();
		gbc_leftPanel.insets = new Insets(0, 0, 0, 5);
		gbc_leftPanel.fill = GridBagConstraints.BOTH;
		gbc_leftPanel.gridx = 0;
		gbc_leftPanel.gridy = 0;
		frame.getContentPane().add(leftPanel, gbc_leftPanel);
		leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		boardPanel = new JPanel();
		boardPanel.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		leftPanel.add(boardPanel);
		
		boardCanvas = new BoardCanvas(maze);
		boardPanel.add(boardCanvas);
		
		rightPanel = new JPanel();
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
}
