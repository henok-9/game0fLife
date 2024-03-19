import java.util.Arrays;
import java.util.Random;


public class GridState{
    private int gridSize;
    private int seed;
    Random random;
    private String[][] genZero;
    private String[][] evolved;

    public GridState(int gridSize, int seed) {
        this.gridSize = gridSize;
        this.seed = seed;
        random = new Random(this.seed);
        genZero = new String[gridSize][gridSize];
        evolved = Arrays.copyOf(genZero, gridSize);
        
        cellEvolution(genZero,3);
    }

    public void generateGrid() {
        for (int i = 0; i < this.gridSize; i++) {
            for (int j = 0; j < this.gridSize; j++) {
                if (getGridState())
                    genZero[i][j] = "O";
                else
                    genZero[i][j] = " ";
            }
        }
    }
    
    public int findNeighbors(String[][] grid, int row, int col) {
        grid = genZero;
        int maxRows = grid.length;
        int maxCols = grid[0].length;
        String[] neighbors = new String[8];
        int numAliveNieghbors = 0;

        int[] neighborOffsetsRow  = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] neighborOffsetsCol = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            // if (isCellAlive(grid, row, col)) {
                int neighborRow = (row + neighborOffsetsRow[i] + maxRows) % maxRows;
                int neighborCol = (col + neighborOffsetsCol[i] + maxCols) % maxCols;
                neighbors[i] = grid[neighborRow][neighborCol];
            // }
        }
        for (int i = 0; i < neighbors.length; i++) {
            if (isCellAlive(neighbors[i])) {
                numAliveNieghbors++;
            }
        }
        
        return numAliveNieghbors -1;
    }
    
    public String[][] cellEvolution(String[][] grid, int numGen) {
        String[][] evolved = new String[gridSize][gridSize];
        for (int i = 0; i < this.gridSize; i++) {
            for (int j = 0; j < this.gridSize; j++) {
                System.out.print(findNeighbors(grid, i, j)+" ");
                if (findNeighbors(grid, i, j) < 2 || findNeighbors(grid, i, j) > 3) {
                    killCell(grid, i, j);
                    // System.out.println(Arrays.toString(grid[i]) + Arrays.toString(grid[j])); 
                    evolved[i][j] = grid[i][j];
                }
                if (!isCellAlive(grid, i, j) && findNeighbors(grid, i, j) == 3){
                    reviveCell(grid, i, j);
                    System.out.println("check!");
                    evolved[i][j] = grid[i][j];
                }
            }
            System.out.println();
        }
        return evolved;
    }

    public boolean isCellAlive(String cell) {
        return cell == "O" ? true : false;
    }
    
    public boolean isCellAlive(String[][] grid, int row, int col) {
        return grid[row][col] == "O" ? true : false;
    }

    public void reviveCell(String[][] grid, int row, int col) {
        grid[row][col] = "O";
    }

    public void killCell(String[][] grid, int row, int col) {
        grid[row][col] = " ";
    }
    
    public String[][] getGrid() {
        return this.genZero;
    }

    public boolean getGridState() {
        return random.nextBoolean();
    }
    
    public void printGrid() {
        for (int i = 0; i < gridSize; i++) {
            System.out.println(Arrays.toString(genZero[i]));
        }
    }
    public void printEvolved() {
        for (int i = 0; i < gridSize; i++) {
            System.out.println(Arrays.toString(evolved[i]));
        }
    }
    
    public void printTest() {
        String[][] t = cellEvolution(genZero, 2);
        for (int i = 0; i < gridSize; i++) {
            System.out.println(Arrays.toString(t[i]));
        }
    }
    
} 
