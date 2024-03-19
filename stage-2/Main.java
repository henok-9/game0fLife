public class Main {
   public static void main(String[] args) {
        int row = 4;
        int col = 4;
        int seed = 4;
        int numGenerations = 1;

        String[][] grid = new String[row][col];
        GridState gridState = new GridState(grid, grid.length, seed);
        gridState.generateGrid();
        
        
        gridState.printGrid();
        System.out.println("=====================================");

        grid = gridState.cellEvolution(grid, numGenerations); 
        gridState.evolutionPrint(grid);  

    }
}


