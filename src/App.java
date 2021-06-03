import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;

public class App implements ActionListener  {
	private GuiGame gui;
    private Game game;

	public App() {
        super();
        game = new Game();
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
            game.showPossibleMove(action);
            showBoard();
        } else if(game.getBoardValue(action) == game.POSSIBLEMOVE * game.getCurrentPlayer()) {
            deletePossibleMove();
            game.moveStone1(action);

            showBoard();
        } else if(game.getBoardValue(action) == game.KEK * game.getCurrentPlayer()) {

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
  
    public void showBoard() {
        for(int i = 0; i < game.board.length; i++){
            for(int j = 0; j < game.board.length; j++){
                //print
                gui.button[i][j+1].setText(String.valueOf(game.board[i][j]));
                switch(game.board[i][j]){
                    case 0:
                        gui.button[i][j+1].setBackground(Color.white);
                        break;
                    case -1:
                        gui.button[i][j+1].setBackground(Color.blue);
                        break;
                    case 1:
                        gui.button[i][j+1].setBackground(Color.red);
                        break;
                    case -2, 2:
                        gui.button[i][j+1].setBackground(Color.green);
                        break;
                }
            }
        }
    }

    public void PossibleMove() {
        for(int i = 0; i < game.board.length; i++){
            for(int j = 0; j < game.board.length; j++){
                //gui.button[i-1][j].setText(String.valueOf(game.board[i-1][j-1]));
                gui.button[i][j+1].setText(String.valueOf(game.board[i][j]));
                if(game.board[i][j] == 0){
                    gui.button[i][j+1].setBackground(Color.white);
                } else if (game.board[i][j] == 1){
                    gui.button[i][j+1].setBackground(Color.red);
                } else if (game.board[i][j] == -1){
                    gui.button[i][j+1].setBackground(Color.blue);
                }
            }
        }
    }


    public void deletePossibleMove(){
        for(int i = 0; i < game.board.length; i++){
            for(int j = 0; j < game.board.length; j++){
                if(game.board[i][j] == game.POSSIBLEMOVE * game.getCurrentPlayer())
                    game.board[i][j] -= game.POSSIBLEMOVE * game.getCurrentPlayer();
            }
        }

    }

	public static void main(String[] args) {
		App app = new App();
	}

}