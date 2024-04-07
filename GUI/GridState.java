import java.util.Arrays;
import java.util.Random;

public class GridState {
    private int gridSize;
    private int seed;
    Random random;
    private String[][] genZero;
    // private String[][] evolved;

    public GridState(String[][] grid, int gridSize, int seed) {
        this.genZero = grid;
        this.gridSize = gridSize;
        this.seed = seed;
        random = new Random(this.seed);
        // genZero = new String[gridSize][gridSize];
        // evolved = Arrays.copyOf(genZero, gridSize);
    }

    // public String[][] generateGrid(String[][] grid, int gridSize) {
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
        // grid = genZero;
        int maxRows = grid.length;
        int maxCols = grid[0].length;
        String[] neighbors = new String[8];
        int numAliveNieghbors = 0;

        int[] neighborOffsetsRow = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] neighborOffsetsCol = { -1, 0, 1, -1, 1, -1, 0, 1 };

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

        return numAliveNieghbors;
    }

    public String[][] cellEvolution(int numGen) {
        String[][] nextGeneration = deepCopy(genZero);
        for (int k = 0; k < numGen; k++) {
            for (int i = 0; i < this.gridSize; i++) {
                for (int j = 0; j < this.gridSize; j++) {
                    int neighbors = findNeighbors(genZero, i, j);
                    if (isCellAlive(genZero[i][j]) && (neighbors < 2 || neighbors > 3)) {
                        // nextGeneration[i][j] = " ";
                        killCell(nextGeneration, i, j);
                    } else if (!isCellAlive(genZero[i][j]) && neighbors == 3) {
                        // nextGeneration[i][j] = "O";
                        reviveCell(nextGeneration, i, j);
                    }
                }
            }
            genZero = deepCopy(nextGeneration);
        }
        return genZero;
    }

    // actual copy of the grid and not just a reference to the original grid :D
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
            System.out.println(Arrays.toString(genZero[i]));
        }
    }

    public void evolutionPrint(String[][] grid) {
        // grid = cellEvolution(genZero);
        for (int i = 0; i < gridSize; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }
    }

    // simulate the evolution of the grid to the console 
    public void simulateEvolution(int numGen) {
        for (int i = 0; i < numGen; i++) {
            try {
                System.out.println("Generation: " + i);
                Thread.sleep(800);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                cellEvolution(numGen);
                printGrid();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
