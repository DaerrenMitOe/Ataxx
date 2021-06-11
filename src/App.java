import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;

public class App implements ActionListener {
	private GuiGame gui;
    private Game game;
    private GameRandomAi game1;

	public App() {
        super();
        game = new Game();
        game1 = new GameRandomAi();
        
		gui = new GuiGame(this);
        
		gui.setSize(700,700);
		gui.setLocation(500,200);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setResizable(false);
		gui.setVisible(true);

        showBoard();
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        if(game.getBoardValue(action) == game.PLAYER * game.getCurrentPlayer()){
            deletePossibleMove();
            game.setLastAction(action);
            game.showPossibleMove(action);
            showBoard();
        } else if(game.getBoardValue(action) == game.POSSIBLEMOVE1 * game.getCurrentPlayer()) {
            deletePossibleMove();
            game.moveStone1(action);
            showBoard();
        } else if(game.getBoardValue(action) == game.POSSIBLEMOVE2 * game.getCurrentPlayer()) {
            deletePossibleMove();
            game.moveStone2(action);
            showBoard();
        } else {
            showBoard();
        }
            /*
                -wer ist dran
                -nur die eigenen steine klicken 
                -mögliche züge
                -- andere stein mögliche züge weg
                -- 1 oder 2 schrite
                -andere ist dran
           */
    }

    public void showPossibleMove(String action) {
        for(int i = 0; i < this.game.possibleMove.length; i++){
            //gui.button[game.getBoardCoordinate(game.possibleMove1[i])[0]][game.getBoardCoordinate(game.possibleMove1[i])[1]+1].setBackground(Color.green);
        }
    }

    public void uff(String[] array){
        for(int i = 0; i < array.length; i++){
            gui.button[game.getBoardCoordinate(array[i])[0]][game.getBoardCoordinate(array[i])[1]+1].setBackground(Color.green);
        }
    }

    public void deletePossibleMove(){
        deletePossibleMove1();
        deletePossibleMove2();
    }

    public void deletePossibleMove1(){
        for(int i = 0; i < game.board.length; i++){
            for(int j = 0; j < game.board.length; j++){
                if(game.board[i][j] == game.POSSIBLEMOVE1 * game.getCurrentPlayer())
                    game.board[i][j] -= game.POSSIBLEMOVE1 * game.getCurrentPlayer();
            }
        }
    }

    public void deletePossibleMove2(){
        for(int i = 0; i < game.board.length; i++){
            for(int j = 0; j < game.board.length; j++){
                if(game.board[i][j] == game.POSSIBLEMOVE2 * game.getCurrentPlayer())
                    game.board[i][j] -= game.POSSIBLEMOVE2 * game.getCurrentPlayer();
            }
        }
    }

    public void showBoard() {
        for(int i = 0; i < game.board.length; i++){
            for(int j = 0; j < game.board.length; j++){
                //print number
                gui.button[i][j+1].setText(String.valueOf(game.board[i][j]));
                
                if(game.board[i][j] == game.EMPTY_FIELD){
                    gui.button[i][j+1].setBackground(Color.white);
                } else if(game.board[i][j] == game.PLAYER){
                    gui.button[i][j+1].setBackground(Color.red);
                    break;
                } else if(game.board[i][j] == -game.PLAYER){
                    gui.button[i][j+1].setBackground(Color.blue);
                    break;
                } else if(game.board[i][j] == game.POSSIBLEMOVE1 || game.board[i][j] == -game.POSSIBLEMOVE1 || game.board[i][j] == game.POSSIBLEMOVE2 || game.board[i][j] == -game.POSSIBLEMOVE2){
                    gui.button[i][j+1].setBackground(Color.green);
                    break;
                }
            }
        }
    }

	public static void main(String[] args) {
		App app = new App();
	}

}