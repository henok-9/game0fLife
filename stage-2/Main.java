public class Main {
   public static void main(String[] args) {
        // int row = 4;
        // int col = 4;
        String[][] grid = 
                       {{" ","O"," "},
                        {"O"," ","O"},
                        {" "," "," "}};
        // // System.out.println("==================================================");
        // gridState.printGrid();
        // String[][] grid = new String[row][col];
        // String[][] grid2 = new String[row][col];
        
        GridState gridState = new GridState(grid, grid.length, 0);
        
        gridState.generateGrid();
        
        gridState.printGrid();
        System.out.println("=====================================");
        // System.out.println(gridState.findNeighbors(grid, 5, 1));
        // 
        for (int generation = 0; generation < 3; generation++) { // Run for a few generations
            grid = gridState.cellEvolution(grid, grid, 1); 
            System.out.println("Generation: " + generation);
            gridState.evolutionPrint(grid);  
            System.out.println("=====================================");
        }

        // String[][] evo = gridState.cellEvolution(grid, grid, 2);
        // gridState.evolutionPrint(evo);
    }
}
