import java.util.Random;

public class GridState{
    private int gridSize;
    private int seed;
    private int numGenerations;
    Random random;
    private String[][] genZero;

    public GridState(int gridSize, int seed, int numGenerations) {
        this.genZero = new String[gridSize][gridSize]; 
        this.gridSize = gridSize;
        this.seed = seed;
        this.numGenerations = numGenerations;
        random = new Random(this.seed);
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
        int maxRows = grid.length;
        int maxCols = grid[0].length;
        String[] neighbors = new String[8];
        int numAliveNieghbors = 0;
        int[] neighborOffsetsRow  = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] neighborOffsetsCol = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
                int neighborRow = (row + neighborOffsetsRow[i] + maxRows) % maxRows;
                int neighborCol = (col + neighborOffsetsCol[i] + maxCols) % maxCols;
                neighbors[i] = grid[neighborRow][neighborCol];
        }
        for (int i = 0; i < neighbors.length; i++) {
            if (isCellAlive(neighbors[i])) {
                numAliveNieghbors++;
            }
        }
        return numAliveNieghbors;
    }
    
    public String[][] cellEvolution() {
        String[][] nextGeneration = deepCopy(genZero);
        for (int k = 0; k < numGenerations; k++) {
            for (int i = 0; i < this.gridSize; i++) {
                for (int j = 0; j < this.gridSize; j++) {
                    int neighbors = findNeighbors(genZero, i, j);
                    if (isCellAlive(genZero[i][j]) && (neighbors < 2 || neighbors > 3)) {
                        killCell(nextGeneration, i, j);
                    } else if (!isCellAlive(genZero[i][j]) && neighbors == 3) {
                        reviveCell(nextGeneration, i, j);
                    }
                }
            }
            genZero = deepCopy(nextGeneration);
        }
        return genZero;
    }

    public String[][] cellEvolution(String[][] evolved, int numGen) {
        evolved = deepCopy(evolved); 
        for (int k = 0; k < numGen; k++) {
            String[][] nextGeneration = deepCopy(evolved); 
            for (int i = 0; i < this.gridSize; i++) {
                for (int j = 0; j < this.gridSize; j++) {
                    if (isCellAlive(evolved, i, j) && findNeighbors(evolved, i, j) < 2 || findNeighbors(evolved, i, j) > 3)  {
                        killCell(nextGeneration, i, j);
                    }
                    else if(!isCellAlive(evolved, i, j) && findNeighbors(evolved, i, j) == 3){
                        reviveCell(nextGeneration, i, j);
                    }
                }
            }
            evolved = deepCopy(nextGeneration);
        }   
        return evolved;
    }
    
    public String[][] deepCopy(String[][] grid) {
        String[][] copy = new String[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            copy[i] = grid[i].clone(); 
        }
        return copy;
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
            for (int j = 0; j < gridSize; j++) {
                System.out.print(genZero[i][j]);
            }
            System.out.println();
        }
    }
} 


