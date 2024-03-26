import java.util.Arrays;
import java.util.Random;


public class GridState{
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
        
        // cellEvolution(genZero,3);
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
        // for (int i = 0; i < gridSize; i++) {
        //     for (int j = 0; j < gridSize; j++) {
        //         if (getGridState())
        //             grid[i][j] = "O";
        //         else
        //             grid[i][j] = " ";
        //     }
        // }
        // return grid;
    }
    
    public int findNeighbors(String[][] grid, int row, int col) {
        // grid = genZero;
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

    // public String[][] cellEvolution(String[][] grid, int numGen) {
    public String[][] cellEvolution(String[][] evolved, int numGen) {
        // String[][] evolved = new String[gridSize][gridSize];
        evolved = deepCopy(evolved); 
        for (int k = 0; k < numGen; k++) {
            String[][] nextGeneration = deepCopy(evolved); 
            for (int i = 0; i < this.gridSize; i++) {
                for (int j = 0; j < this.gridSize; j++) {
                    // System.out.print(findNeighbors(grid, i, j)+" ");
                    // if (isCellAlive(grid, i, j) && findNeighbors(grid, i, j) < 2 || 
                     
                    if (isCellAlive(evolved, i, j) && findNeighbors(evolved, i, j) < 2 || findNeighbors(evolved, i, j) > 3)  {
                        // System.out.println("Cell Killed at: " + i + ", " + j); 
                        killCell(nextGeneration, i, j);
                        // System.out.print("[K: "+killCell(grid, i, j)+"Neig: "+findNeighbors(grid, i, j)+" Idx: "+i+", "+j+"] ");
                        // System.out.println(Arrays.toString(grid[i]) + Arrays.toString(grid[j])); 
                        // evolved[i][j] = grid[i][j];
                    }
                    // if (isCellAlive(nextGeneration, i, j) && findNeighbors(nextGeneration, i, j) > 3){
                    //     // System.out.println("Cell Killed at: " + i + ", " + j); 
                    // // if (findNeighbors(grid, i, j) > 3){
                    //     killCell(nextGeneration, i, j);
                    //     // killCell(grid, i, j);
                    //     // evolved[i][j] = grid[i][j];
                    //     // System.out.print("[K: "+killCell(grid, i, j)+"Neig: "+findNeighbors(grid, i, j)+" Idx: "+i+", "+j+"] ");
                    // }
                    else if(!isCellAlive(evolved, i, j) && findNeighbors(evolved, i, j) == 3){
                        // System.out.println("Cell Revived at: " + i + ", " + j); 
                        reviveCell(nextGeneration, i, j);
                        // System.out.print("[R: "+reviveCell(grid, i, j)+" Neig: "+findNeighbors(grid, i, j)+" Idx: "+i+", "+j+"] ");
                        // System.out.print("check: "+findNeighbors(grid, i, j)+" ");
                        // evolved[i][j] = grid[i][j];
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
            System.out.println(Arrays.toString(genZero[i]));
        }
    }
    // public void printEvolved() {
    //     for (int i = 0; i < gridSize; i++) {
    //         System.out.println(Arrays.toString(evolved[i]));
    //     }
    // }
    
    public void evolutionPrint(String[][] grid) {
        // grid = cellEvolution(genZero);
        for (int i = 0; i < gridSize; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }
    }
    // simulate the evolution of the grid live using Thread.sleep
    public void simulateEvolution(int numGen) {
        for (int i = 0; i < numGen; i++) {
            try {
                System.out.println("Generation: " + i);
                Thread.sleep(700);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                cellEvolution(numGen);
                printGrid();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
   
    //duplicate 
    // public String[][] _cellEvolution(String[][] grid, String[][] evolved, int numGen) {
    //     for (int i = 0; i < grid.length; i++) {
    //         for (int j = 0; j < grid.length; j++) {
    //         }
    //     }
    //     return evolved;
    // }
    //
    // 
} 


