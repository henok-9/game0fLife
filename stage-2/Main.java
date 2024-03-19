
public class Main {
    public static void main(String[] args) {
        GridState gridState = new GridState(6,3);
        gridState.generateGrid();
        gridState.printGrid();
        System.out.println("==================================================");
        // gridState.printEvolved();
        gridState.printTest();
        System.out.println(gridState.findNeighbors(gridState.getGrid(), 4, 2));
    }
}
