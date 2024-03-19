public class Main {
    public static void main(String[] args) {
        GridState gridState = new GridState(6,3);
        // gridState.printGrid();
        System.out.println("==================================================");
        // gridState.printEvolved();
        gridState.printTest();



        String[][] grid = 
                       {{" ","O"," ","O"},
                        {"O","O","O","O"},
                        {" ","O"," ","O"},
                        {" ","O","O","O"}};
        
        gridState.generateGrid(grid, grid.length);
        
        System.out.println(gridState.findNeighbors(grid, 1, 2));
    }
}
