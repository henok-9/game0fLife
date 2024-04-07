public class DisplayGrid {
    public static void main(String[] args) {
        int row = 32;
        int col = 32;
        int seed = 43;
        int numGenerations = 50;

        String[][] grid = new String[row][col];
        GridState gridState = new GridState(grid, grid.length, seed);
        gridState.generateGrid();

        // grid = gridState.cellEvolution(grid, numGenerations);
        // gridState.evolutionPrint(grid);
        gridState.simulateEvolution(numGenerations);

    }
}




