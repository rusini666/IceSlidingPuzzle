import java.util.List;

/**
 * @author Rusini Thara Gunarathne
 * @author 20200205
 */
class Queue implements Comparable<Queue> {
    int distance;
    int row;
    int column;
    List<Integer[]> coordinates;

    public Queue(int distance, int row, int column, List<Integer[]> coordinates) {
        this.distance = distance;
        this.row = row;
        this.column = column;
        this.coordinates = coordinates;
    }

    public int compareTo(Queue queue) {
        return Integer.compare(distance, queue.distance);
    }
}