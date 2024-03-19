public class Main {
    public static void main(String[] args) {
        Map map = new Map(7,64);
        map.generateGrid();
        map.printGrid();
        System.out.println(map.findNeighbors(map.getGrid(), 0, 6));
        // System.out.println(map.isCornerCell(map.getGrid(), 0, 0));
    }
}
