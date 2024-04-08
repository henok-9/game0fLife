import java.util.Arrays;
import java.util.Random;

public class Map {
    private String East;
    private String West;
    private String North;
    private String South;
    private String NEast;
    private String NWest;
    private String SEast;
    private String SWest;

    private int gridSize;
    private int seed;
    Random random;
    private String[][] genZero;

    public Map(int gridSize, int seed) {
        this.gridSize = gridSize;
        this.seed = seed;
        random = new Random(this.seed);
        genZero = new String[gridSize][gridSize];
    }

    public void generateGrid() {
        for (int i = 0; i < this.gridSize; i++) {
            for (int j = 0; j < this.gridSize; j++) {
                if (getGridState())
                    genZero[i][j] = "O";
                else
                    genZero[i][j] = " ";
                // System.out.print(getGridState() ? "O" : " ");
            }
            // System.out.println();
        }
    }
    
    // ??
    public int findNeighbors(String[][] grid, int row, int col) {
        int maxRows = grid.length;
        int maxCols = grid[0].length;
        String[] neighbors = new String[8];
        int numAliveNieghbors = 0;

        int[] neighborOffsetsRow  = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] neighborOffsetsCol = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            if (isCellAlive(grid, row, col)) {
                int neighborRow = (row + neighborOffsetsRow[i] + maxRows) % maxRows;
                int neighborCol = (col + neighborOffsetsCol[i] + maxCols) % maxCols;
                neighbors[i] = grid[neighborRow][neighborCol];
            }
        }
        for (int i = 0; i < neighbors.length; i++) {
            if (isCellAlive(neighbors[i])) {
                numAliveNieghbors++;
            }
        }
        
        return numAliveNieghbors;
    }

    public boolean getGridState() {
        return random.nextBoolean();
    }

    public boolean isCornerCell(String[][] grid, int row, int col) {
        boolean isCorner = false;
        if (row == 0 && col == 0) {
            isCorner = true;
        }
        if (row == 0 && col == grid.length - 1) {
            isCorner = true;
        }
        if (row == grid.length - 1 && col == 0) {
            isCorner = true;
        }
        if (row == grid.length - 1 && col == grid.length - 1) {
            isCorner = true;
        }
        return isCorner;
    }

    public void getNeighborsCorner(String[][] grid, int row, int col) {
        int gridLen = grid.length;
        // int nTotal = 8;
        if (row == 0 && col == 0) {
            West = grid[row][gridLen - 1];
            SWest = grid[row + 1][gridLen - 1];
            North = grid[gridLen - 1 - row][col];
            NWest = grid[gridLen - 1 - row][gridLen - 1];
            NEast = grid[gridLen - 1 - row][col - 1];
        }

        if (row == gridLen - 1 && col == gridLen - 1) {
            East = grid[row][0];
            SEast = grid[row + 1][0];
            North = grid[gridLen - 1 - row][col];
            NWest = grid[gridLen - 1 - row][col - 1];
            NEast = grid[gridLen - 1 - row][0];
        }

    }

    public int getNeighbors(String[][] grid, int row, int col) {
        int numAliveNieghbors = 0;
        
        if (isCellAlive(grid, row, col) && !isCornerCell(grid, row, col)) {
            East = grid[row][col + 1];
            West = grid[row][col - 1];
            North = grid[row - 1][col];
            South = grid[row + 1][col];
            NEast= grid[row - 1][col + 1];
            NWest= grid[row - 1][col - 1];
            SEast = grid[row + 1][col + 1];
            SWest = grid[row + 1][col - 1];
            
            String[] neighbors = { East, West, North, South, NEast, NWest, SEast, SWest };

            for (int i = 0; i < neighbors.length; i++) {
                if (isCellAlive(neighbors[i])) {
                    numAliveNieghbors++;
                }
            }
        }

        return numAliveNieghbors;
    }

    public boolean isCellAlive(String cell) {
        // System.out.println("ded cell");
        return cell == "O" ? true : false;
    }
    
    public boolean isCellAlive(String[][] grid, int row, int col) {
        return grid[row][col] == "O" ? true : false;
    }

    public void cellEvolution(String[][] intialGrid, int numGen) {
        for (int i = 0; i < this.gridSize; i++) {
            for (int j = 0; j < this.gridSize; j++) {
            }
        }
    }


    
    public void printGrid() {
        for (int i = 0; i < gridSize; i++) {
            System.out.println(Arrays.toString(genZero[i]));
        }

        
    }

    public String[][] getGrid() {
        return this.genZero;
    }
}

/*
 * boolean East = false;
 * boolean West = false;
 * boolean North = false;
 * boolean South = false;
 * boolean NEast = false;
 * boolean NWest = false;
 * boolean SEast = false;
 * boolean SWest = false;
 */
