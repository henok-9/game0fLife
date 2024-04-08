import java.util.Scanner;
import java.util.Random;
public class Test{
    // instance fields
    private int size;
    private int seed;
    Random random;
    //defalult constructor
    public Test(){
    }

    // parameterized constructor
    public Test(int size, int seed){
        this.size = size;
        this.seed = seed;
        random = new Random(this.seed);
        
    }
    // generates grid layout
    public void generateGrid(){
        for(int i=0; i < this.size; i++){
            for (int j = 0; j < this.size; j++) {
                System.out.print(getGridState() ? "O" : " ");
            }
        System.out.println();
        }
    }

    // returns a pseudorandom boolean value based on the provided seed;
    public boolean getGridState(){
        return random.nextBoolean();
    }
    
    public static void main(String[] args) {
        System.out.print("> ");
        Scanner scnr = new Scanner(System.in);
        int size = scnr.nextInt();
        int seed = scnr.nextInt();

        Test map = new Test(size, seed);
        map.generateGrid();
       
        scnr.close();
    }

}
