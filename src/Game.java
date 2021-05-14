import java.util.Arrays;

public class Game {
	protected int moves;
	protected int[][] board;
	protected String[][] coordinate;
	
	public Game(){
		this.moves = 0;
		this.board = initBoard();
		this.coordinate = initCoordinate();

		System.out.println(Arrays.deepToString(getPossibleMove("B2")));
	}
	
	public int[][] initBoard() {
		//vielleicht forscheife verwenden
		int[][] board = {
				{0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0},
		};
		
		startingPosition(board);

		return board;
	}
	
	public int[][] startingPosition(int[][] board) {
		/*
		1 = Player1
		2 = Player2
		*/


		// player 1 = 1
		// player 2 = -1  verhindert hardcode durch einfaches negieren der zahl

		board[0][0] = 1;
		board[6][6] = 1;

		board[0][6] = -1;
		board[6][0] = -1;
		
		return board;
	}

	public String[][] initCoordinate() {
		String[][] coordinate = new String[board.length][board.length];
		String[] letter = {
            "A", "B", "C", "D", "E", "F", "G"
        };

		for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
				coordinate[i][j] = letter[j] + String.valueOf(7- i);
			}
		}

		return coordinate;
	}

	public void moveStone1(String action){
		
		
		if(getBoardValue(action) == 1){

		}
		setPossibleMove1(coordinateOnBoard(action)[0],coordinateOnBoard(action)[1]);
		for(int i = 0; i < possibleMove1.length; i++) {
			if(possibleMove1[i] != null){
				if(board[coordinateOnBoard(possibleMove1[i])[0]][coordinateOnBoard(possibleMove1[i])[1]] == 2){
					board[coordinateOnBoard(possibleMove1[i])[0]][coordinateOnBoard(possibleMove1[i])[1]] = 1;
				};
			}
		}
	}



	public void moveStone2(String action){
		if(moves%2 == 0){
			board[coordinateOnBoard(getlel())[0]][coordinateOnBoard(getlel())[1]] = 0;
			board[coordinateOnBoard(action)[0]][coordinateOnBoard(action)[1]] = 1;
			setPossibleMove1(coordinateOnBoard(action)[0],coordinateOnBoard(action)[1]);
			for(int i = 0; i < possibleMove1.length; i++) {
				if(possibleMove1[i] != null){
					if(board[coordinateOnBoard(possibleMove1[i])[0]][coordinateOnBoard(possibleMove1[i])[1]] == 2){
						board[coordinateOnBoard(possibleMove1[i])[0]][coordinateOnBoard(possibleMove1[i])[1]] = 1;
					};
				}
			}
			this.moves +=1;
		} else {
			board[coordinateOnBoard(getlel())[0]][coordinateOnBoard(getlel())[1]] = 0;
			board[coordinateOnBoard(action)[0]][coordinateOnBoard(action)[1]] = 2;
			setPossibleMove1(coordinateOnBoard(action)[0],coordinateOnBoard(action)[1]);
			for(int i = 0; i < possibleMove1.length; i++) {
				if(possibleMove1[i] != null){
					if(board[coordinateOnBoard(possibleMove1[i])[0]][coordinateOnBoard(possibleMove1[i])[1]] == 1){
						board[coordinateOnBoard(possibleMove1[i])[0]][coordinateOnBoard(possibleMove1[i])[1]] = 2;
					};
				}
			}
			this.moves +=1;
		}
		

	}

	public int[] getBoardCoordinate(String coordinate) {
		int[] boardCoordinate = new int[2];
		for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
				if(this.coordinate[i][j].equals(coordinate)){
					boardCoordinate[0]= i;
					boardCoordinate[1]= j;
					return boardCoordinate;
				}
			}
		}
		return boardCoordinate;
	}

	public int getBoardValue(String coordinate) {
		for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
				if(this.coordinate[i][j].equals(coordinate)){
					return board[i][j];
				}
			}
		}
		return 0; // wird nie eintreten
	}

	public String[] getPossibleMove1(String action){
		/*
		beginnt links unten gegen den uhrzeigersinn
		*/
		//int[] possibelMOve = new int[10];

		int[] uff = getBoardCoordinate(action);

		int column = uff[0];
		int row = uff[1];




		String[] possibleMove1 = new String[8];

		for(int i = 0; i < possibleMove1.length; i++) {
			possibleMove1[i] = null;
		}

		int i = 0;
		//links unten
		if(column + 1 <= 6 && row - 1 >= 0) {
			possibleMove1[i] = this.coordinate[column + 1][ row - 1];
			i += 1;
		}

		//mitte unten
		if(column + 1 <= 6) {
			possibleMove1[i] = this.coordinate[column + 1][ row];
			i += 1;
		}

		//rechts unten
		if(column + 1 <= 6 && row + 1 <= 6) {
			possibleMove1[i] = this.coordinate[column + 1][ row + 1];
			i += 1;
		}

		//mitte rechts
		if(row + 1 <= 6) {
			possibleMove1[i] = this.coordinate[column][ row + 1];
			i += 1;
		}

		////rechts oben
		if(column - 1 >= 0 && row + 1 <= 6) {
			possibleMove1[i] = this.coordinate[column - 1][ row + 1];
			i += 1;
		}

		//mitte oben
		if(column - 1 >= 0) {
			possibleMove1[i] = this.coordinate[column - 1][ row];
			i += 1;
		}

		////links oben
		if(column - 1 >= 0 && row - 1 >= 0) {
			possibleMove1[i] = this.coordinate[column - 1][ row - 1];
			i += 1;
		}

		////mitte links
		if(row - 1 >= 0) {
			possibleMove1[i] = this.coordinate[column][ row - 1];
			i += 1;
		}
		
		possibleMove1 = deleteNull(possibleMove1);
		
		return possibleMove1;
	}

	public String[] getPossibleMove2(String action){
		/*
		beginnt links unten gegen den uhrzeigersinn
		*/
		
		int[] uff = getBoardCoordinate(action);

		int column = uff[0];
		int row = uff[1];


		//int[] possibelMOve = new int[10];
		String[] possibleMove2 = new String[16];

		for(int i = 0; i < possibleMove2.length; i++) {
			possibleMove2[i] = null;
		}

		int i = 0;

		//links unten
		if(column + 2 <= 6 && row - 2 >= 0) {
			possibleMove2[i] = this.coordinate[column + 2][ row - 2];
			i += 1;
		}
		
		//mitte unten links
		if(column + 2 <= 6 && row - 1 >= 0) {
			possibleMove2[i] = this.coordinate[column + 2][ row - 1];
			i += 1;
		}

		//mitte unten
		if(column + 2 <= 6) {
			possibleMove2[i] = this.coordinate[column + 2][ row];
			i += 1;
		}

		//mitte unten rechts
		if(column + 2 <= 6 && row + 1 <= 6) {
			possibleMove2[i] = this.coordinate[column + 2][ row + 1];
			i += 1;
		}

		//rechts unten
		if(column + 2 <= 6 && row + 2 <= 6) {
			possibleMove2[i] = this.coordinate[column + 2][ row + 2];
			i += 1;
		}

		//mitte rechts unten
		if(column + 1 <= 6 && row + 2 <= 6) {
			possibleMove2[i] = this.coordinate[column + 1][ row + 2];
			i += 1;
		}

		//mitte rechts
		if(row + 2 <= 6) {
			possibleMove2[i] = this.coordinate[column][ row + 2];
			i += 1;
		}

		//mitte rechts oben
		if(column - 1 >= 0 && row + 2 <= 6) {
			possibleMove2[i] = this.coordinate[column - 1][ row + 2];
			i += 1;
		}

		//rechts oben
		if(column - 2 >= 0 && row + 2 <= 6) {
			possibleMove2[i] = this.coordinate[column - 2][ row + 2];
			i += 1;
		}

		//mitte oben rechts
		if(column - 2 >= 0 && row + 1 <= 6) {
			possibleMove2[i] = this.coordinate[column - 2][ row + 1];
			i += 1;
		}

		//mitte oben
		if(column - 2 >= 0) {
			possibleMove2[i] = this.coordinate[column - 2][ row];
			i += 1;
		}

		//mitte oben links
		if(column - 2 >= 0 && row - 1 >= 0) {
			possibleMove2[i] = this.coordinate[column - 2][ row - 1];
			i += 1;
		}

		//links oben
		if(column - 2 >= 0 && row - 2 >= 0) {
			possibleMove2[i] = this.coordinate[column - 2][ row - 2];
			i += 1;
		}

		//mitte links oben
		if(column - 1 >= 0 && row - 2 >= 0) {
			possibleMove2[i] = this.coordinate[column - 1][ row - 2];
			i += 1;
		}

		//mitte links
		if(row - 2 >= 0) {
			possibleMove2[i] = this.coordinate[column][ row - 2];
			i += 1;
		}

		//mitte links unten
		if(column + 1 <= 6 && row - 2 >= 0) {
			possibleMove2[i] = this.coordinate[column + 1][ row - 2];
			i += 1;
		}

		possibleMove2 = deleteNull(possibleMove2);
		return possibleMove2;
	}
/*
	public String[] getPossibleMove4(String[] possibleMove1, String[] possibleMove2){
		String[] possibleMove = new String[16+8];
		
		int k = 0;
		for(int i = 0; i < possibleMove1.length; i++){
			if(getBoardValue(possibleMove1[i]) != -1 && getBoardValue(possibleMove1[i]) != 1) {
				possibleMove[k] = possibleMove1[i];
				k += 1;
			}

		}

		for(int i = 0; i < possibleMove2.length; i++){
			if(getBoardValue(possibleMove2[i]) != -1 && getBoardValue(possibleMove2[i]) != 1) {
				possibleMove[k] = possibleMove2[i];
				k += 1;
			}
		}

		possibleMove = deleteNull(possibleMove);

		return possibleMove;
	}
*/
	public String[] getPossibleMove(String action){
		String[] possibleMove1 = getPossibleMove1(action);
		String[] possibleMove2 = getPossibleMove2(action);
		String[] possibleMove = new String[possibleMove1.length + possibleMove2.length];
		
		int k = 0;
		for(int i = 0; i < possibleMove1.length; i++){
			if(getBoardValue(possibleMove1[i]) != -1 && getBoardValue(possibleMove1[i]) != 1) {
				possibleMove[k] = possibleMove1[i];
				k += 1;
			}

		}

		for(int i = 0; i < possibleMove2.length; i++){
			if(getBoardValue(possibleMove2[i]) != -1 && getBoardValue(possibleMove2[i]) != 1) {
				possibleMove[k] = possibleMove2[i];
				k += 1;
			}
		}

		possibleMove = deleteNull(possibleMove);

		return possibleMove;
	}
	
	public String[] deleteNull(String[] array){
		int i = 0;
		for(int j = 0; j < array.length; j++){
			if(array[j] != null){
				i += 1;
			}
		}

		String[] uff = new String[i];

		for(int j = 0; j < uff.length; j++){
			uff[j]= array[j];
		}

		return uff;
	}


	public String[] getAllAction(int playerStone){
		String[] stone = new String[board.length * board.length];
		int k = 0;
		for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
				if(board[i][j] == playerStone){
					stone[k] = coordinate[i][j];
				}
			}
		}

		stone = deleteNull(stone);
		return stone;
	}
	/*
	public static void main(String[] args) {
		Game game = new Game();
	}*/
}
