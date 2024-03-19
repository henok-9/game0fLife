import java.util.Scanner;

class Main {
   public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in); 
        int gridSize = scnr.nextInt();
        int seed = scnr.nextInt();
        int numGenerations = scnr.nextInt();
        
        GridState gridState = new GridState(gridSize, seed, numGenerations);
        
        gridState.generateGrid();
        gridState.cellEvolution(); 
        
        gridState.printGrid();

    }
}


