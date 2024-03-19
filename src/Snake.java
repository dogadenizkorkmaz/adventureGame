import java.util.Random;

public class Snake extends Obstacle {
    private static Random random = new Random();
    public Snake() {

        super(4, "Snake", random.nextInt(7 - 3 + 1) + 3, 12, 0);
    }
}