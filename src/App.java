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

        getBoard();
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        //deletePossibleMoveOnBoard();
        //getBoard();
        switch(game.getBoardValue(action)){
            case 0:
                deletePossibleMoveOnBoard();
                getBoard();
                break;
            case -1,1:
                //deletePossibleMoveOnBoard();
                uff(game.getPossibleMove(action));
                //getBoard();
                break;

                /*
            case 3:
                deletePossibleMoveOnBoard();
                for(int i = 0; i < game.possibleMove1.length; i++){
                    if(game.possibleMove1[i] != null){
                        if(game.possibleMove1[i].equals(action)){
                            game.moveStone1(action);

                        }
                    } 
                }

                for(int i = 0; i < game.possibleMove2.length; i++){
                    if(game.possibleMove2[i] != null){
                        if(game.possibleMove2[i].equals(action)){
                            game.moveStone2(action);
                        }
                    }
                }
                getBoard();
                break;
            case 4:
                deletePossibleMoveOnBoard();
                game.moveStone1(action);
                for(int i = 0; i < game.possibleMove1.length; i++){
                    if(game.possibleMove1[i] != null){
                        if(game.possibleMove1[i].equals(action)){
                            game.moveStone1(action);

                        }
                    } 
                }

                for(int i = 0; i < game.possibleMove2.length; i++){
                    if(game.possibleMove2[i] != null){
                        if(game.possibleMove2[i].equals(action)){
                            game.moveStone2(action);
                        }
                    }
                }
                getBoard();
                break;
            case 5:
                deletePossibleMoveOnBoard();
                game.moveStone1(action);
                for(int i = 0; i < game.possibleMove1.length; i++){
                    if(game.possibleMove1[i] != null){
                        if(game.possibleMove1[i].equals(action)){
                            game.moveStone1(action);

                        }
                    } 
                }

                for(int i = 0; i < game.possibleMove2.length; i++){
                    if(game.possibleMove2[i] != null){
                        if(game.possibleMove2[i].equals(action)){
                            game.moveStone2(action);
                        }
                    }
                }
                getBoard();
                break;
            */
        }

    }

    public void uff(String[] array){
        for(int i = 0; i < array.length; i++){
            gui.button[game.getBoardCoordinate(array[i])[0]][game.getBoardCoordinate(array[i])[1]+1].setBackground(Color.green);
        }
    }
  
    public void getBoard() {
        for(int i = 0; i < game.board.length; i++){
            for(int j = 0; j < game.board.length; j++){
                //gui.button[i-1][j].setText(String.valueOf(game.board[i-1][j-1]));
                gui.button[i][j+1].setText(String.valueOf(game.board[i][j]));
                if(game.board[i][j] == 0){
                    gui.button[i][j+1].setBackground(Color.white);
                } else if (game.board[i][j] == 1){
                    gui.button[i][j+1].setBackground(Color.red);
                } else if (game.board[i][j] == 2){
                    gui.button[i][j+1].setBackground(Color.blue);
                } else if (game.board[i][j] == 3){
                    gui.button[i][j+1].setBackground(Color.green);
                }
            }
        }
    }

    public void deletePossibleMoveOnBoard(){
        for(int i = 0; i < game.board.length; i++){
            for(int j = 0; j < game.board.length; j++){

                switch(game.board[i][j]){
                    case 3:
                        game.board[i][j] = 0;
                        break;
                    case 4:
                        game.board[i][j] -=3;
                        break;
                    case 5:
                        game.board[i][j] -=3;
                        break;
                }
                
            }
        }

    }

	public static void main(String[] args) {
		App app = new App();
	}

}