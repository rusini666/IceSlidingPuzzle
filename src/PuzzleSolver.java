import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PuzzleSolver {

    private static final int[][] directions = new int[][] {{1, 0},{-1, 0}, {0, 1}, {0, -1}};

    public static List<List<String>> readFile (File file) throws FileNotFoundException {
        List<List<String>> grid = new ArrayList<>();
        // useful when you are trying to read a CSV file, and then you need to handle list of lists to get it in memory,
        // perform some processing and write back to another CSV file
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String[] line = scanner.nextLine().split("");
            grid.add(Arrays.asList(line));
        }
        return grid;
    }

    // the implementation using Dijkstra algorithm
    public static List<Integer[]> solve(List<List<String>> grid) {
        int rows = grid.size();
        int columns = grid.get(0).size();
        int startRow = 0;
        int startCol = 0;
        int endCol = 0;
        int endRow = 0;

        for(int i = 0; i < rows; i++) { // store coordinates(row, column) of 'S' and 'F'
            for (int j = 0; j < columns; j++) {
                if(grid.get(i).get(j).equals("S")) {
                    startRow = i;
                    startCol = j;
                }
                if (grid.get(i).get(j).equals("F")) {
                    endRow = i;
                    endCol = j;
                }
            }
        }

        int[][] distance = new int[rows][columns];

        for (int i = 0; i < distance.length; i++) {
            for(int j = 0; j < columns; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        PriorityQueue<Queue> priorityQueue = new PriorityQueue<>();
        Integer[] arr = new Integer[] {startRow, startCol};
        List<Integer[]> arrayList = new ArrayList<>();
        arrayList.add(arr);
        priorityQueue.add(new Queue(0, startRow, startCol,  arrayList));

        while (!priorityQueue.isEmpty()) {
            Queue queueObj = priorityQueue.remove(); // remove the Queue Object with the shortest distance
            List<Integer[]> path = queueObj.coordinates;
            if (path.get(path.size() - 1)[0] == endRow && path.get(path.size() - 1)[1] == endCol) return path;

            for(int[] direction : directions) {
                int count = 0;
                int currentRow = queueObj.row;
                int currentColumn = queueObj.column;
                int rowDirection = direction[0];
                int columnDirection = direction[1];

                while (((0 <= currentRow + rowDirection) && (currentRow + rowDirection < rows)) && ((0 <=  currentColumn
                        + columnDirection) && (currentColumn + columnDirection < columns)) && !grid.get(currentRow +
                        rowDirection).get(currentColumn + columnDirection).equals("0")) {
                    count += 1;
                    currentRow += rowDirection;
                    currentColumn += columnDirection;
                    if (grid.get(currentRow).get(currentColumn).equals("F")) {
                        path.add(new Integer[] {currentRow, currentColumn});
                        return path ;
                    }
                }

                if (distance[currentRow][currentColumn] == Integer.MAX_VALUE || queueObj.distance + count < distance[currentRow][currentColumn]) {
                    distance[currentRow][currentColumn] = queueObj.distance + count;
                    List<Integer[]> integers = new ArrayList<>(path);
                    integers.add(new Integer[] {currentRow, currentColumn});
                    priorityQueue.add(new Queue(queueObj.distance + count, currentRow, currentColumn, integers));
                }

            }
        }
        return new ArrayList<>();
    }

    public static void displayPath (List<Integer[]> path) {
        if (path.size() > 0) {
            System.out.printf("1. Start at: (%d, %d) \n", path.get(0)[1] + 1, path.get(0)[0] + 1);
            for (int i = 1; i < path.size(); i++) {
                int currentX = path.get(i)[0];
                int currentY = path.get(i)[1];
                int previousX = path.get(i - 1)[0];
                int previousY = path.get(i - 1)[1];
                int pathX = currentX - previousX;
                int pathY = currentY - previousY;
                if (pathX < 0) {
                    System.out.printf((i + 1) + ". Move up to : (%d, %d) \n", path.get(i)[1] + 1, path.get(i)[0] + 1);
                } else if (pathX > 0) {
                    System.out.printf((i + 1) + ". Move Down to : (%d, %d) \n", path.get(i)[1] + 1, path.get(i)[0] + 1);
                } else if (pathY < 0) {
                    System.out.printf((i + 1) + ". Move Left to : (%d, %d) \n", path.get(i)[1] + 1, path.get(i)[0] + 1);
                } else {
                    System.out.printf((i + 1) + ". Move Right to : (%d, %d) \n", path.get(i)[1] + 1, path.get(i)[0] + 1);
                }
            }
            System.out.println((path.size() + 1) + ". Done!");
        }
    }
}
