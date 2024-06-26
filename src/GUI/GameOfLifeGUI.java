import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.border.Border;


public class GameOfLifeGUI extends JFrame {
    protected static final int CELL_SIZE = 11;
    private final int GRID_SIZE = 70;
    private CustomCellPanel[][] cellPanels;
    private int seed = 23;
    private Timer timer;
    private int delay = 200;  
    private JButton startButton, pauseButton, stepButton, resetButton; 
    private JSlider speedSlider;
    private JLabel generationLabel; 
    private int currentGeneration = 0; 

    public GameOfLifeGUI() {
        super("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(CELL_SIZE * GRID_SIZE, CELL_SIZE * GRID_SIZE);
        GridState game = new GridState(new String[GRID_SIZE][GRID_SIZE], GRID_SIZE, seed);  // Create a new game state
        Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);

        // Create the grid of cell panels
        JPanel gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        cellPanels = new CustomCellPanel[GRID_SIZE][GRID_SIZE];
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cellPanels[row][col] = new CustomCellPanel();
                gridPanel.add(cellPanels[row][col]);
            }
        }
        gridPanel.setBorder(padding);
        // JButton stepButton = new JButton("Step");
        // stepButton.addActionListener(e -> {
        //     game.cellEvolution(1);
        //     updateGridDisplay(game);
        // });
        
        JPanel controlPanel = new JPanel();
        startButton = new JButton("Start");
        pauseButton = new JButton("Pause");
        stepButton = new JButton("Step");
        speedSlider = new JSlider(JSlider.HORIZONTAL, 100, 500, delay); 
        generationLabel = new JLabel("Generation: 0");
        resetButton = new JButton("Reset");
        controlPanel.add(resetButton); 
        controlPanel.add(startButton);
        controlPanel.add(pauseButton);
        controlPanel.add(stepButton);
        controlPanel.add(speedSlider);
        controlPanel.add(generationLabel);

        // Add grid and controls to the layout
        
        setLayout(new GridLayout(1, 2));
        add(gridPanel, BorderLayout.CENTER); 
        add(controlPanel, BorderLayout.NORTH);

        // Timer setup
        timer = new Timer(delay, e -> {
            game.cellEvolution(1);
            updateGridDisplay(game);
            currentGeneration++;  
            generationLabel.setText("Generation: " + currentGeneration);
        });

        // Event Handlers
        startButton.addActionListener(e -> timer.start());
        pauseButton.addActionListener(e -> timer.stop());
        stepButton.addActionListener(e -> { 
            game.cellEvolution(1); 
            updateGridDisplay(game);
            currentGeneration++;
            generationLabel.setText("Generation: " + currentGeneration);
        });
        
        // stop the timer and reset the grid 
        resetButton.addActionListener(e -> {
            timer.stop(); 
            game.generateGrid(); 
            updateGridDisplay(game);
            currentGeneration = 0;
            generationLabel.setText("Generation: 0");
        }); 
        
        speedSlider.addChangeListener(e -> timer.setDelay(speedSlider.getValue()));

        game.generateGrid();
        loadGridFromGameState(game); // Load the initial grid state
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);



        
    gridPanel.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
              int row = e.getY() / CELL_SIZE;
              int col = e.getX() / CELL_SIZE;

              // check if the click is within the grid
              if (row >= 0 && row < GRID_SIZE && col >= 0 && col < GRID_SIZE)  {
                  cellPanels[row][col].setAlive(!cellPanels[row][col].isAlive());
              }
          }
    });
    }
    
    // Load the grid from the game state into the GUI
    private void loadGridFromGameState(GridState game) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cellPanels[row][col].setAlive(game.getGrid()[row][col].equals("O"));
            }
        }
    }

    private void updateGridDisplay(GridState game) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cellPanels[row][col].setAlive(game.getGrid()[row][col].equals("O"));
            }
        }
    }

    public static void main(String[] args) {
        new GameOfLifeGUI();
    }
}
