import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class FileReader {

    public static void main(String[] args) throws FileNotFoundException {
        long start = System.currentTimeMillis();

        List<List<String>> map = PuzzleSolver.readFile(new File("maze10_3.txt")); // store each row of the maze as an element in a 2D array
        List<Integer[]> answer = PuzzleSolver.solve(map);
        long now = System.currentTimeMillis();
        double elapsed = now - start;

        PuzzleSolver.displayPath(answer);
        System.out.println();
        System.out.println("Elapsed time = " + elapsed + " milliseconds");
    }
}