public class DisplayGrid {
    public static void main(String[] args) {
        int row = 25;
        int col = 25;
        int seed = 62;
        int numGenerations = 53;

        String[][] grid = new String[row][col];
        GridState gridState = new GridState(grid, grid.length, seed);
        gridState.generateGrid();

        // grid = gridState.cellEvolution(grid, numGenerations);
        // gridState.evolutionPrint(grid);
        gridState.simulateEvolution(numGenerations);

    }
}
