import java.util.Arrays;
import java.util.Random;

public class GameRandomAi {
    private int[][] board;
    private Game game;

    public GameRandomAi(Game game) {
        this.game = game;
    }

    public String uff() {
        Random random = new Random();
        // Game game = new Game();

        int uff = random.nextInt(game.getStoneOnBoard().length);

        String[] a = game.getStoneOnBoard();
        game.setPossibleMove(a[uff]);
        int uff1 = random.nextInt(game.possibleMove.length);
        String c = game.possibleMove[uff1];

        int kek = game.getBoardValue(c)[0];
    }

    /*
     * public static void main(String[] args) { GameRandomAi g = new GameRandomAi();
     * }
     */

}
