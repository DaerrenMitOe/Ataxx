import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JFrame;

public class App implements ActionListener {
    private GuiGame gui;
    private Game game;
    // private GameRandomAi game1;

    public App() {
        super();
        game = new Game();
        //game1 = new GameRandomAi(game);

        gui = new GuiGame(this);

        gui.setSize(800, 800);
        gui.setLocation(500, 200);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setResizable(false);
        gui.setVisible(true);

        showBoard();
        //gui.board();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        	
        System.out.println(action);
        System.out.println(Arrays.deepToString(game.possibleMove1));

        vsPlayer(action);
        
        /*
         * -wer ist dran -nur die eigenen steine klicken -mögliche züge -- andere stein
         * mögliche züge weg -- 1 oder 2 schrite -andere ist dran
         */
    }
/*
    public void uff(String[] array) {
        for (int i = 0; i < array.length; i++) {
            gui.button[game.getBoardCoordinate(array[i])[0]][game.getBoardCoordinate(array[i])[1] + 1]
                    .setBackground(Color.green);
        }
    }
*/
    public void deletePossibleMove() {
        deletePossibleMove1();
        deletePossibleMove2();
    }

    private void deletePossibleMove1() {
        for (int i = 0; i < game.board.length; i++) {
            for (int j = 0; j < game.board.length; j++) {
                if (game.board[i][j] == game.POSSIBLEMOVE1 * game.getCurrentPlayer())
                    game.board[i][j] -= game.POSSIBLEMOVE1 * game.getCurrentPlayer();
            }
        }
    }

    private void deletePossibleMove2() {
        for (int i = 0; i < game.board.length; i++) {
            for (int j = 0; j < game.board.length; j++) {
                if (game.board[i][j] == game.POSSIBLEMOVE2 * game.getCurrentPlayer())
                    game.board[i][j] -= game.POSSIBLEMOVE2 * game.getCurrentPlayer();
            }
        }
    }

    public void showBoard() {
        for (int i = 0; i < game.BOARD_COLUMN; i++) {
            for (int j = 0; j < game.BOARD_ROW; j++) {
                // print number
                gui.button[i][j].setText(String.valueOf(game.board[i][j]));

                if (game.board[i][j] == game.EMPTY_FIELD) {
                    gui.button[i][j].setBackground(Color.white);
                } else if (game.board[i][j] == game.PLAYER) {
                    gui.button[i][j].setBackground(Color.red);
                } else if (game.board[i][j] == -game.PLAYER) {
                    gui.button[i][j].setBackground(Color.blue);
                } else if (game.board[i][j] == game.POSSIBLEMOVE1 || game.board[i][j] == -game.POSSIBLEMOVE1
                        || game.board[i][j] == game.POSSIBLEMOVE2 || game.board[i][j] == -game.POSSIBLEMOVE2) {
                    gui.button[i][j].setBackground(Color.green);
                }
            }
        }
    }

    public void vsPlayer(String action) {
        if (game.getBoardValue(action) == game.PLAYER * game.getCurrentPlayer()) {
            deletePossibleMove();
            game.setLastAction(action);
            game.showPossibleMove(action);
            showBoard();
            gameOver();
        } else if (game.getBoardValue(action) == game.POSSIBLEMOVE1 * game.getCurrentPlayer()) {
            deletePossibleMove();
            game.moveStone1(action);
            showBoard();
            gameOver();
        } else if (game.getBoardValue(action) == game.POSSIBLEMOVE2 * game.getCurrentPlayer()) {
            deletePossibleMove();
            game.moveStone2(action);
            showBoard();
            gameOver();
        } else {
            showBoard();
            gameOver();
        }
    }

    public void gameOver() {
        if(game.gameOver()){
            gui.gameOver(game.winnerText());
        }
    }


    public static void main(String[] args) {
        App a = new App();
    }

}