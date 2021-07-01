import java.util.Random;

public class GameRandomAi { 
    private Game game;

    public GameRandomAi(Game game) {
        this.game = game;
    }

    /**
     * Zug von RandomAI zurück
     */
    public void getMove() {
        Random random = new Random();

        while(true){
            int rd = random.nextInt(game.getStoneOnBoard().length);
            
            String[] poss = game.setPossibleMove(game.getStoneOnBoard()[rd]);
            game.setLastAction(game.getStoneOnBoard()[rd]);
            game.showPossibleMove(game.getStoneOnBoard()[rd]);
            game.deletePossibleMove();
            if(poss.length != 0){
                String[] poss1 = game.setPossibleMove1(game.getStoneOnBoard()[rd]);
                String[] poss2 = game.setPossibleMove2(game.getStoneOnBoard()[rd]);
                
                int rn = random.nextInt(2);

                if(rn == 0){
                    if(poss1.length != 0){
                        int r = random.nextInt(poss1.length);
                        game.moveStone1(poss1[r]);
                        break;
                    }
                } else if(rn == 1){
                    if(poss2.length != 0){
                        int r = random.nextInt(poss2.length);
                        game.moveStone2(poss2[r]);
                        break;
                    }
                }
            }

        }
    }

    /*
     * public static void main(String[] args) { GameRandomAi g = new GameRandomAi();
     * }
     */

}
