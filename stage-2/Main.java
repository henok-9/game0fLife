public class Main {
    public static void main(String[] args) {
        String[][] grid = 
                       {{" ","O"," ","O"},
                        {"O","O","O","O"},
                        {" ","O"," ","O"},
                        {" ","O","O","O"}};
        // System.out.println("==================================================");
        // gridState.printEvolved();
        // gridState.printGrid();
        
        GridState gridState = new GridState(grid, grid.length, 1);
        
        gridState.generateGrid();
        
        gridState.printGrid();
        System.out.println(gridState.findNeighbors(grid, 1, 2));
    }
}
