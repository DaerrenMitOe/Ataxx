import java.util.Arrays;

public class Game {
	final int EMPTY_FIELD = 0;
	final int PLAYER = 1;
	final int POSSIBLEMOVE = 2;
	final int KEK = 3;
	final int BOARD_COLUMN = 7, BOARD_ROW = 7;

	protected int move;
	protected int gameRound;
	protected int[] player = new int[2];
	protected int[] points = new int[2];
	protected int[][] board;
	protected String[][] coordinate;

	private String lastAction;
	protected String[] possibleMove;
	protected String[] possibleMove1;
	protected String[] possibleMove2;
	
	public Game(){
		this.move = 1;
		initBoard();
		initCoordinate();
	}
	
	public void initBoard() {
		this.board = new int[BOARD_COLUMN][BOARD_ROW];
		for(int i = 0; i < BOARD_COLUMN; i++){
			for(int j = 0; j < BOARD_ROW; j++){
				this.board[i][j] = EMPTY_FIELD;
			}
		}
	
		startingPosition();
	}
	
	private void startingPosition() {
		/*
		1 = Player1
		2 = Player2
		*/


		// player 1 = 1
		// player 2 = -1  verhindert hardcode durch einfaches negieren der zahl
		if(gameRound%2 == 1){
			board[0][0] = PLAYER;
			board[6][6] = PLAYER;
	
			board[0][6] = -PLAYER;
			board[6][0] = -PLAYER;
		} else {
			board[0][0] = -PLAYER;
			board[6][6] = -PLAYER;
	
			board[0][6] = PLAYER;
			board[6][0] = PLAYER;
		}
	}

	private void initCoordinate() {
		String[] letter = {
            "A", "B", "C", "D", "E", "F", "G"
        };

		this.coordinate = new String[BOARD_COLUMN][BOARD_ROW];

		for(int i = 0; i < BOARD_COLUMN; i++) {
            for(int j = 0; j < BOARD_ROW; j++) {
				this.coordinate[i][j] = letter[j] + String.valueOf(7 - i);
			}
		}
	}
/*
	public String[][] initGame() {

		return coordinate;
	}
	*/
	public void moveStone1(String action){
		for(int i = 0; i < this.possibleMove1.length; i++) {
			if(this.possibleMove1[i].equals(action)){
				setStone1(action);
				takeStone(getLastAction());
				this.move++;
				break;
			}

		}
		System.out.println(Arrays.deepToString(this.possibleMove1));
	}

	public void moveStone2(String action){
		setEmptyField();
		setStone2(action);
		setPossibleMove2(action);
		for(int i = 0; i < this.possibleMove2.length; i++) {
			if(this.possibleMove2[i].equals(action)){

			}
		}


		/*
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
		*/
	}

	private void setStone1(String action){
		setLastAction(action);
		board[getBoardCoordinate(action)[0]][getBoardCoordinate(action)[1]] = getCurrentPlayer();
	}

	private void setStone2(String action){
		setLastAction(action);
		board[getBoardCoordinate(action)[0]][getBoardCoordinate(action)[1]] = getCurrentPlayer();
	}


	private void takeStone(String action){
		setPossibleMove1(action);
		for(int i = 0; i < this.possibleMove1.length; i++) {
			if(board[getBoardCoordinate(possibleMove1[i])[0]][getBoardCoordinate(possibleMove1[i])[1]] == -getCurrentPlayer()){
				board[getBoardCoordinate(possibleMove1[i])[0]][getBoardCoordinate(possibleMove1[i])[1]] = getCurrentPlayer();
			}
		}
	}


	private void setEmptyField(){
		board[getBoardCoordinate(this.lastAction)[0]][getBoardCoordinate(this.lastAction)[1]] = EMPTY_FIELD;
	}

	private String getLastAction(){
		return this.lastAction;
	}

	private void setLastAction(String action){
		this.lastAction = action;
	}

	public void showPossibleMove(String action){
		setPossibleMove(action);
		for(int i = 0; i < this.possibleMove.length;i++){
			if(board[getBoardCoordinate(possibleMove[i])[0]][getBoardCoordinate(possibleMove[i])[1]] == EMPTY_FIELD){
				board[getBoardCoordinate(possibleMove[i])[0]][getBoardCoordinate(possibleMove[i])[1]] = 2 * getCurrentPlayer();
			}
		}
		//board[getBoardCoordinate(action)[0]][getBoardCoordinate(action)[1]] = 2 * getCurrentPlayer();
	}

	public int[] getBoardCoordinate(String coordinate) {
		/**
		* @DaerrenMitOe uff kek
		*/
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

	public int getBoardValue(int[] coordinate) {
		
		for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
				if(this.coordinate[i][j].equals(coordinate)){
					return board[i][j];
				}
			}
		}
		return 0; // wird nie eintreten
	}

	public void setPossibleMove1(String action){
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
		
		this.possibleMove1 = deleteNull(possibleMove1);
	}

	public void setPossibleMove2(String action){
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

		this.possibleMove2 = deleteNull(possibleMove2);
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
	public void setPossibleMove(String action){
		setPossibleMove1(action);
		setPossibleMove2(action);
		String[] possibleMove = new String[this.possibleMove1.length + this.possibleMove2.length];
		
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

		this.possibleMove = deleteNull(possibleMove);
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
			uff[j] = array[j];
		}

		return uff;
	}


	public String[] getAllStoneCoordinate(int playerStone){
		String[] allStoneCoordinate = new String[board.length * board.length];
		int k = 0;
		for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
				if(board[i][j] == playerStone){
					allStoneCoordinate[k] = coordinate[i][j];
					k += 1;
				}
			}
		}

		allStoneCoordinate = deleteNull(allStoneCoordinate);
		return allStoneCoordinate;
	}

	/*
	public String[][][] getAllPosissibelMove(int playerStone) {
		String[] allStoneCoordinate = getAllStoneCoordinate(playerStone);
		String[][][] allPosissibelMove = new String [2][allStoneCoordinate.length][24];

		for(int i = 0; i < allPosissibelMove[0].length; i++) {
			allPosissibelMove[0][i][0] = allStoneCoordinate[i];
		}

		for(int i = 0; i < allPosissibelMove[0].length; i++) {
			allPosissibelMove[0][i] = deleteNull(allPosissibelMove[0][i]);
		}


		for(int i = 0; i < allPosissibelMove[0].length; i++) {
			for(int j = 0; j < setPossibleMove(allStoneCoordinate[i]).length; j++) {
				allPosissibelMove[1][i][j] = setPossibleMove(allStoneCoordinate[i])[j];
			}
		}

		for(int i = 0; i < allPosissibelMove[0].length; i++) {
			for(int j = 0; j < allPosissibelMove[0][0].length; j++) {
				allPosissibelMove[1][i][j] = setPossibleMove(allStoneCoordinate[i])[j];
				allPosissibelMove[1][i] = deleteNull(allPosissibelMove[1][i]);
			}
		}

		return allPosissibelMove;
	}
*/
	public int getCurrentPlayer() {
		if(this.move% 2 == 0){
			return 1;
		}
		return -1;
	}

	public void setPoints(){
		for(int i = 0; i < points.length; i++){
			for(int j = 0; j < board.length; j++){
				for(int k = 0; k < board.length; k++) {
					if(board[j][k]==player[i]){
						points[i]++;
					}
				}
			}
		}
	}

	public void swapPosition() {
		for(int i = 0; i < player.length; i++){
			player[i] = -player[i];
		}
	}

	public void gameOver(){
		if(BOARD_COLUMN * BOARD_ROW == points[0]+points[1]){
			if(points[0] == points[1]){
				//unentschieden
			} else if(points[0] > points[1]) {
				//player 0 gewiinnnt
			} else if(points[0] < points[1]) {
				//player 1 gewinnt
			}
		}

		if(points[0] == 0){
		//player 1 gewinnt
		}
		
		if(points[1] == 0){
		//player 0 gewinnt
		}
	
	}
	/*
	public static void main(String[] args) {
		Game game = new Game();
	}*/
}
