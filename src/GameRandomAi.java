import java.util.Arrays;
import java.util.Random;

public class GameRandomAi extends Game{
    String stoneOnBoard[] = new String[board.length * board.length];
    public GameRandomAi() {
        System.out.println(Arrays.deepToString(board));
    }

    public String[] getStoneOnBoard(){
        String stoneOnBoard[] = new String[board.length * board.length];
        int k = 0;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                if(board[i][j] == 2) {
                    stoneOnBoard[k] = coordinate[i][j];
                }
			} 
		}

        stoneOnBoard = deleteNull(stoneOnBoard);

        return stoneOnBoard;
    }
    

    public String uff(){
        Random random = new Random();

        int uff = random.nextInt(getStoneOnBoard().length);

        String[] a = getStoneOnBoard();
        String[] b = getPossibleMove(a[uff]);
        int uff1 = random.nextInt(b.length);
        String c = b[uff1];
        
        return c;
    }

/*
    public static void main(String[] args) {
        GameRandomAi g = new GameRandomAi();
    }
*/

}
