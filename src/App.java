import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JFrame;

public class App implements ActionListener {
    private Game game;
    private GuiGame guiGame;
    private GameRandomAi gameRandomAi;
    private GuiNewGame guiNewGame;
    // private GameRandomAi game1;

    private boolean ai;

    public App() {
        super();
        game = new Game();
        guiGame = new GuiGame(this);
        gameRandomAi = new GameRandomAi(game);
        guiNewGame = new GuiNewGame(this);

        guiGame.setSize(800, 800);
        guiGame.setLocation(500, 200);
        guiGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiGame.setResizable(false);
        guiGame.setVisible(true);
        showBoard();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        	
        System.out.println(action);
        System.out.println(Arrays.deepToString(game.possibleMove1));

        //vsPlayer(action);
        vsRandomAi(action);
    }

    /**
     * <p> Zeigt Board an
     */
    public void showBoard() {
        for (int i = 0; i < game.BOARD_COLUMN; i++) {
            for (int j = 0; j < game.BOARD_ROW; j++) {
                // print number
                guiGame.button[i][j].setText(String.valueOf(game.board[i][j]));

                if (game.board[i][j] == game.EMPTY_FIELD) {
                    guiGame.button[i][j].setBackground(Color.white);
                } else if (game.board[i][j] == game.PLAYER) {
                    guiGame.button[i][j].setBackground(Color.red);
                } else if (game.board[i][j] == -game.PLAYER) {
                    guiGame.button[i][j].setBackground(Color.blue);
                } else if (game.board[i][j] == game.POSSIBLEMOVE1 || game.board[i][j] == -game.POSSIBLEMOVE1
                        || game.board[i][j] == game.POSSIBLEMOVE2 || game.board[i][j] == -game.POSSIBLEMOVE2) {
                    guiGame.button[i][j].setBackground(Color.green);
                }
            }
        }
    }

    /**
     * <p> Spieler vs Spieler Modus
     * @param action Zug
     */
    public void vsPlayer(String action) {
         /*
         -wer ist dran -nur die eigenen steine klicken -mögliche züge -- andere stein
          mögliche züge weg -- 1 oder 2 schrite -andere ist dran
         */
        System.out.println(game.move);
        if (game.getBoardValue(action) == game.PLAYER * game.getCurrentPlayer()) {
            if(!game.noMove()){
                ai = false;
                game.deletePossibleMove();
                game.showPossibleMove(action);
                game.setLastAction(action);
                showBoard();
                gameOver();
            } else {
                ai = true;
            }
        } else if (game.getBoardValue(action) == game.POSSIBLEMOVE1 * game.getCurrentPlayer()) {
            ai = true;
            game.deletePossibleMove();
            game.moveStone1(action);
            showBoard();
            gameOver();
        } else if (game.getBoardValue(action) == game.POSSIBLEMOVE2 * game.getCurrentPlayer()) {
            ai = true;
            game.deletePossibleMove();
            game.moveStone2(action);
            showBoard();
            gameOver();
        } else{
            gameOver();
        }
    }

    private void vsRandomAi(String action){
        if(game.currentPlayer[0] == true){
            vsPlayer(action);
            if(ai){
                game.currentPlayer[0] = false;
                game.currentPlayer[1] = true;

                if(game.currentPlayer[1] == true){
                    ai = false;
                    gameRandomAi.getMove();
                    Arrays.deepToString(game.board);
                    game.currentPlayer[0] = true;
                    game.currentPlayer[1] = false;
                    showBoard();
                    gameOver();
                }
            }

        }
    }

    /**
     * <p> GewinnerFenster
     */
    public void gameOver() {
        if(game.gameOver()){
            int n = guiGame.gameOver(winnerText());
            if(n == 0) {
                game.initBoard();
                showBoard();
            } else if(n == 1){
                game.initBoard();
                showBoard();
                /*
                guiNewGame.setSize(800, 800);
                guiNewGame.setLocation(500, 200);
                guiNewGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                guiNewGame.setResizable(false);
                guiNewGame.setVisible(true);
                */
            } else if(n == 2){
                game.initBoard();  
                showBoard();
            }
        }
    }

    /**
	 * <p> Gibt Gewinner Text zurück
	 * @return Gewinner Text
	 */
	public String winnerText(){
        String a = String.valueOf(game.playerPoints[0]);
        String b = String.valueOf(game.playerPoints[1]);
		if(game.winner[0] && game.winner[1]){
			return a + " - " + b + "\nUnentschieden";
		} else if(game.winner[0]){
			return  a + " - " + b + "\nBlau hat gewonnen";
		} else if(game.winner[1]){
			return  a + " - " + b + "\nRot hat gewonnen";
		}

		//wird nicht eintretten
		return "";
	}

    public static void main(String[] args) {
        App a = new App();
    }
}