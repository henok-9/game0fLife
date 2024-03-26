public class Main {
   public static void main(String[] args) {
        int row = 40;
        int col = 40;
        int seed = 2;
        int numGenerations = 53;

        String[][] grid = new String[row][col];
        GridState gridState = new GridState(grid, grid.length, seed);
        gridState.generateGrid();
        
        
        // gridState.printGrid();
        System.out.println("=====================================");

        // grid = gridState.cellEvolution(grid, numGenerations); 
        // gridState.evolutionPrint(grid);  
        gridState.simulateEvolution(numGenerations);

    }
}


