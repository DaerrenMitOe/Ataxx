import java.util.Arrays;

public class Game {
	final int EMPTY_FIELD = 0;
	final int PLAYER = 1;
	final int BOARD_COLUMN = 7, BOARD_ROW = 7;
	final int POSSIBLEMOVE1 = 8;
	final int POSSIBLEMOVE2 = 16;

	final int[] GAME_MODE = { 
		1, // vs Player
		2, // vs random ai
		3 // vs minmax Ai
	};
	final String[][] COORDINATE;

	protected int move;
	protected int gameRound;
	protected int[] winner = new int[3];
	protected int[] player = new int[2];
	protected int[] points = new int[2];
	protected int[][] board;

	private String lastAction;
	protected String[] possibleMove;
	protected String[] possibleMove1;
	protected String[] possibleMove2;

	public Game() {
		this.move = 1;
		initBoard();
		COORDINATE = initCoordinate();
		setPossibleMove2("G7");
		System.out.println(Arrays.deepToString(COORDINATE));
	}

	public void initBoard() {
		this.board = new int[BOARD_COLUMN][BOARD_ROW];
		for (int i = 0; i < BOARD_COLUMN; i++) {
			for (int j = 0; j < BOARD_ROW; j++) {
				this.board[i][j] = EMPTY_FIELD;
			}
		}

		startingPosition();
	}

	private void startingPosition() {
		/*
		 * 1 = Player1 2 = Player2
		 */

		// player 1 = 1
		// player 2 = -1 verhindert hardcode durch einfaches negieren der zahl
		if (gameRound % 2 == 1) {
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

	private String[][] initCoordinate() {
		String[] letter = { "A", "B", "C", "D", "E", "F", "G" };

		String[][] coordinate = new String[BOARD_COLUMN][BOARD_ROW];

		for (int i = 0; i < BOARD_COLUMN; i++) {
			for (int j = 0; j < BOARD_ROW; j++) {
				coordinate[i][j] = letter[j] + String.valueOf(7 - i);
			}
		}

		return coordinate;
	}

	public void initGame() {

	}

/**
 * <h1>Steine "teilen"</h1>
 * <p> Setzt du den Stein 1 Feld weit auf ein direkt
(maximal 8 Möglichkeiten) angrenzendes Feld,
dann "teilt" er sich. Du hast am Ende deines
Zugs einen Stein mehr: einen auf dem Startfeld
und einen auf dem Zielfeld.
 * @param action
 */
	public void moveStone1(String action) {
		for (int i = 0; i < this.possibleMove1.length; i++) {
			if (this.possibleMove1[i].equals(action)) {
				setStone(action);
				takeStone(action);
				this.move++;
				break;
			}

		}
		// System.out.println(Arrays.deepToString(this.possibleMove1));
	}
/**
 * <h1>Steine bewegen</h1>
 * <p> Du kannst den Stein auch 2 Felder weit versetzen (maximal 16 Möglichkeiten), dann 
bewegt er sich dorthin und teilt sich nicht. Besetze Felder können übersprungen werden.
 * @param action 
*/
	public void moveStone2(String action) {
		for (int i = 0; i < this.possibleMove2.length; i++) {
			if (this.possibleMove2[i].equals(action)) {
				setEmptyField();
				setStone(action);
				takeStone(action);
				this.move++;
				break;
			}
		}
	}
/**
 * <h1>Keine Züge</h1>
 * <p>Bist du an der Reihe und kannst keinen Stein versetzen, so verfällt dein Zug, dein 
Gegner kommt wieder an die Reihe! 
 * @return
 */
	public boolean noMove(){
		String[] uff = getStoneOnBoard();
		for(int i = 0; i < uff.length; i++){
			setPossibleMove(uff[i]);
			if(this.possibleMove.length != 0){
				return false;
			}
		}
		this.move +=2;
		return true;
	}

	private void setStone(String action) {
		board[getBoardCoordinate(action)[0]][getBoardCoordinate(action)[1]] = getCurrentPlayer();
	}
/**
 * <h1>Gegnerische Steine umdrehen</h1>
 * <p>Egal ob du geteilt oder bewegt hast: Ist das
Zielfeld des Zuges benachbart (gerade oder
diagonal) zu ein oder mehreren gegnerischen
Steinen, so werden diese alle in deine Farbe
umgefärbt! 
 * @param action
 */
	private void takeStone(String action) {
		setAllPossibleMove1(getBoardCoordinate(action)[0], getBoardCoordinate(action)[1]);
		for (int i = 0; i < this.possibleMove1.length; i++) {
			if (board[getBoardCoordinate(possibleMove1[i])[0]][getBoardCoordinate(
					possibleMove1[i])[1]] == -getCurrentPlayer()) {
				board[getBoardCoordinate(possibleMove1[i])[0]][getBoardCoordinate(
						possibleMove1[i])[1]] = getCurrentPlayer();
			}
		}
	}

	private void setEmptyField() {
		board[getBoardCoordinate(getLastAction())[0]][getBoardCoordinate(getLastAction())[1]] = EMPTY_FIELD;
	}

	private String getLastAction() {
		return this.lastAction;
	}

	public void setLastAction(String action) {
		this.lastAction = action;
	}

	public void showPossibleMove1() {
		for (int i = 0; i < this.possibleMove1.length; i++) {
			if (board[getBoardCoordinate(possibleMove1[i])[0]][getBoardCoordinate(
					possibleMove1[i])[1]] == EMPTY_FIELD) {
				board[getBoardCoordinate(possibleMove1[i])[0]][getBoardCoordinate(possibleMove1[i])[1]] = POSSIBLEMOVE1
						* getCurrentPlayer();
			}
		}
	}

	private String[] setPossibleMove(String[] array) {
		int i = 0;
		for (int j = 0; j < array.length; j++) {
			if (board[getBoardCoordinate(array[i])[0]][getBoardCoordinate(array[i])[1]] == EMPTY_FIELD) {
				i += 1;
			}
		}

		// anderer name für uff

		String[] uff = new String[i];
		int k = 0;

		for (int j = 0; j < array.length; j++) {
			if (board[getBoardCoordinate(array[j])[0]][getBoardCoordinate(array[j])[1]] == EMPTY_FIELD) {
				uff[k] = array[j];
				k++;
			}
		}

		return uff;
	}

	public void showPossibleMove(String action) {
		setPossibleMove1(action);
		setPossibleMove2(action);

		for (int i = 0; i < this.possibleMove1.length; i++) {
			if (board[getBoardCoordinate(possibleMove1[i])[0]][getBoardCoordinate(
					possibleMove1[i])[1]] == EMPTY_FIELD) {
				board[getBoardCoordinate(possibleMove1[i])[0]][getBoardCoordinate(possibleMove1[i])[1]] = POSSIBLEMOVE1
						* getCurrentPlayer();
			}
		}

		for (int i = 0; i < this.possibleMove2.length; i++) {
			if (board[getBoardCoordinate(possibleMove2[i])[0]][getBoardCoordinate(
					possibleMove2[i])[1]] == EMPTY_FIELD) {
				board[getBoardCoordinate(possibleMove2[i])[0]][getBoardCoordinate(possibleMove2[i])[1]] = POSSIBLEMOVE2
						* getCurrentPlayer();
			}
		}
	}

	public int[] getBoardCoordinate(String coordinate) {
		/**
		 * @DaerrenMitOe uff kek
		 */
		int[] boardCoordinate = new int[2];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (COORDINATE[i][j].equals(coordinate)) {
					boardCoordinate[0] = i;
					boardCoordinate[1] = j;
					return boardCoordinate;
				}
			}
		}
		return boardCoordinate;
	}
/**
 * Gibt Wert von Brettkoordinate aus
 * @param coordinate
 * @return
 */
	public int getBoardValue(String coordinate) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (COORDINATE[i][j].equals(coordinate)) {
					return board[i][j];
				}
			}
		}
		// wird nie eintreten
		return 0;
	}

	/*
	 * public int getBoardValue(int i, int j) { return board[i][j]; }
	 */
/**
 * Speichert mögliche Züge im Umkreis von einem Feld
 * @param action
 */
	public void setPossibleMove1(String action) {
		setAllPossibleMove1(getBoardCoordinate(action)[0], getBoardCoordinate(action)[1]);
		this.possibleMove1 = setPossibleMove(this.possibleMove1);
	}
/**
 * Speichert alle möglichen Züge im Umkreis von einem Feld
 * @param column
 * @param row
 */
	private void setAllPossibleMove1(int column, int row) {
		/*
		 * beginnt links unten gegen den uhrzeigersinn
		 */

		this.possibleMove1 = new String[POSSIBLEMOVE1];

		int i = 0;
		// links unten
		if (column + 1 <= 6 && row - 1 >= 0) {
			possibleMove1[i] = COORDINATE[column + 1][row - 1];
			i += 1;
		}

		// mitte unten
		if (column + 1 <= 6) {
			possibleMove1[i] = COORDINATE[column + 1][row];
			i += 1;
		}

		// rechts unten
		if (column + 1 <= 6 && row + 1 <= 6) {
			possibleMove1[i] = COORDINATE[column + 1][row + 1];
			i += 1;
		}

		// mitte rechts
		if (row + 1 <= 6) {
			possibleMove1[i] = COORDINATE[column][row + 1];
			i += 1;
		}

		// rechts oben
		if (column - 1 >= 0 && row + 1 <= 6) {
			possibleMove1[i] = COORDINATE[column - 1][row + 1];
			i += 1;
		}

		// mitte oben
		if (column - 1 >= 0) {
			possibleMove1[i] = COORDINATE[column - 1][row];
			i += 1;
		}

		// links oben
		if (column - 1 >= 0 && row - 1 >= 0) {
			possibleMove1[i] = COORDINATE[column - 1][row - 1];
			i += 1;
		}

		// mitte links
		if (row - 1 >= 0) {
			possibleMove1[i] = COORDINATE[column][row - 1];
			i += 1;
		}

		this.possibleMove1 = deleteNull(possibleMove1);
	}
/**
 * Speichert mögliche Züge im Umkreis von zwei Felder
 * @param action
 */
	public void setPossibleMove2(String action) {
		setAllPossibleMove2(getBoardCoordinate(action)[0], getBoardCoordinate(action)[1]);
		this.possibleMove2 = setPossibleMove(this.possibleMove2);
	}
/**
 * Speichert alle möglichen Züge im Umkreis von zwei Felder
 * @param column
 * @param row
 */
	private void setAllPossibleMove2(int column, int row) {
		/*
		 * beginnt links unten gegen den uhrzeigersinn
		 */

		this.possibleMove2 = new String[POSSIBLEMOVE2];
		int i = 0;

		// links unten
		if (column + 2 <= 6 && row - 2 >= 0) {
			possibleMove2[i] = COORDINATE[column + 2][row - 2];
			i += 1;
		}

		// mitte unten links
		if (column + 2 <= 6 && row - 1 >= 0) {
			possibleMove2[i] = COORDINATE[column + 2][row - 1];
			i += 1;
		}

		// mitte unten
		if (column + 2 <= 6) {
			possibleMove2[i] = COORDINATE[column + 2][row];
			i += 1;
		}

		// mitte unten rechts
		if (column + 2 <= 6 && row + 1 <= 6) {
			possibleMove2[i] = COORDINATE[column + 2][row + 1];
			i += 1;
		}

		// rechts unten
		if (column + 2 <= 6 && row + 2 <= 6) {
			possibleMove2[i] = COORDINATE[column + 2][row + 2];
			i += 1;
		}

		// mitte rechts unten
		if (column + 1 <= 6 && row + 2 <= 6) {
			possibleMove2[i] = COORDINATE[column + 1][row + 2];
			i += 1;
		}

		// mitte rechts
		if (row + 2 <= 6) {
			possibleMove2[i] = COORDINATE[column][row + 2];
			i += 1;
		}

		// mitte rechts oben
		if (column - 1 >= 0 && row + 2 <= 6) {
			possibleMove2[i] = COORDINATE[column - 1][row + 2];
			i += 1;
		}

		// rechts oben
		if (column - 2 >= 0 && row + 2 <= 6) {
			possibleMove2[i] = COORDINATE[column - 2][row + 2];
			i += 1;
		}

		// mitte oben rechts
		if (column - 2 >= 0 && row + 1 <= 6) {
			possibleMove2[i] = COORDINATE[column - 2][row + 1];
			i += 1;
		}

		// mitte oben
		if (column - 2 >= 0) {
			possibleMove2[i] = COORDINATE[column - 2][row];
			i += 1;
		}

		// mitte oben links
		if (column - 2 >= 0 && row - 1 >= 0) {
			possibleMove2[i] = COORDINATE[column - 2][row - 1];
			i += 1;
		}

		// links oben
		if (column - 2 >= 0 && row - 2 >= 0) {
			possibleMove2[i] = COORDINATE[column - 2][row - 2];
			i += 1;
		}

		// mitte links oben
		if (column - 1 >= 0 && row - 2 >= 0) {
			possibleMove2[i] = COORDINATE[column - 1][row - 2];
			i += 1;
		}

		// mitte links
		if (row - 2 >= 0) {
			possibleMove2[i] = COORDINATE[column][row - 2];
			i += 1;
		}

		// mitte links unten
		if (column + 1 <= 6 && row - 2 >= 0) {
			possibleMove2[i] = COORDINATE[column + 1][row - 2];
			i += 1;
		}

		this.possibleMove2 = deleteNull(this.possibleMove2);
	}
/**
 * Speichert mögliche Züge zurück im Umkreis von einem Feld und zwei Felder
 * @param action
 */
	public void setPossibleMove(String action) {
		setPossibleMove1(action);
		setPossibleMove2(action);
		this.possibleMove = new String[this.possibleMove1.length + this.possibleMove2.length];

		int k = 0;
		for (int i = 0; i < possibleMove1.length; i++) {
			possibleMove[k] = possibleMove1[i];
			k++;
		}

		for (int i = 0; i < possibleMove2.length; i++) {
			possibleMove[k] = possibleMove2[i];
			k++;
		}
	}
	/**
	 * Löscht null im Array
	 * @param array
	 * @return
	 */
	private String[] deleteNull(String[] array) {
		int i = 0;
		for (int j = 0; j < array.length; j++) {
			if (array[j] != null) {
				i += 1;
			}
		}

		// anderer name für uff

		String[] uff = new String[i];

		for (int j = 0; j < uff.length; j++) {
			uff[j] = array[j];
		}

		return uff;
	}

	public String[] getAllStoneCoordinate(int playerStone) {
		String[] allStoneCoordinate = new String[board.length * board.length];
		int k = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == playerStone) {
					allStoneCoordinate[k] = COORDINATE[i][j];
					k += 1;
				}
			}
		}

		allStoneCoordinate = deleteNull(allStoneCoordinate);
		return allStoneCoordinate;
	}

	/*
	 * public String[][][] getAllPosissibelMove(int playerStone) { String[]
	 * allStoneCoordinate = getAllStoneCoordinate(playerStone); String[][][]
	 * allPosissibelMove = new String [2][allStoneCoordinate.length][24];
	 * 
	 * for(int i = 0; i < allPosissibelMove[0].length; i++) {
	 * allPosissibelMove[0][i][0] = allStoneCoordinate[i]; }
	 * 
	 * for(int i = 0; i < allPosissibelMove[0].length; i++) {
	 * allPosissibelMove[0][i] = deleteNull(allPosissibelMove[0][i]); }
	 * 
	 * 
	 * for(int i = 0; i < allPosissibelMove[0].length; i++) { for(int j = 0; j <
	 * setPossibleMove(allStoneCoordinate[i]).length; j++) {
	 * allPosissibelMove[1][i][j] = setPossibleMove(allStoneCoordinate[i])[j]; } }
	 * 
	 * for(int i = 0; i < allPosissibelMove[0].length; i++) { for(int j = 0; j <
	 * allPosissibelMove[0][0].length; j++) { allPosissibelMove[1][i][j] =
	 * setPossibleMove(allStoneCoordinate[i])[j]; allPosissibelMove[1][i] =
	 * deleteNull(allPosissibelMove[1][i]); } }
	 * 
	 * return allPosissibelMove; }
	 */
	public String[] getStoneOnBoard() {
		String[] stoneOnBoard = new String[BOARD_COLUMN * BOARD_ROW];
		int i = 0;
		for (int j = 0; j < board.length; j++) {
			for (int k = 0; k < board.length; k++) {
				if (board[j][k] == getCurrentPlayer()) {
					stoneOnBoard[i] = COORDINATE[j][k];
					i++;
				}
			}
		}

		stoneOnBoard = deleteNull(stoneOnBoard);
		return stoneOnBoard;
	}

	public int getCurrentPlayer() {
		if (this.move % 2 == 0) {
			return 1;
		}
		return -1;
	}

	public void setPoints() {
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < board.length; j++) {
				for (int k = 0; k < board.length; k++) {
					if (board[j][k] == player[i]) {
						points[i]++;
					}
				}
			}
		}
	}

	public void swapPosition() {
		for (int i = 0; i < player.length; i++) {
			player[i] = -player[i];
		}
	}
/**
 * <h1>Spielende</h1>
 * <p>Das Spiel endet,
 * <ul>
 * <li>wenn ein Spieler keine Steine mehr hat,</li>
 * <li>oder wenn beide Spieler keine Züge mehr machen können. </li>
 * </ul>
 */
	public void gameOver() {
		if(gameOver1() || gameOver2()) {
			winner();
		}
	}
/**
 * <h1>Spielende</h1>
 * <p>Das Spiel endet,
 * <ul>
 * <li>wenn ein Spieler keine Steine mehr hat.</li>
 * </ul>
 */
	private boolean gameOver1(){
		for(int i = 0; i < this.points.length; i++){
			if (this.points[i] == 0) {
				return true;
			}
		}
		return false;
	}
/**
 * <h1>Spielende</h1>
 * <p>Das Spiel endet,
 * <ul>
 * <li>wenn beide Spieler keine Züge mehr machen können. </li>
 * </ul>
 */
	private boolean gameOver2(){
		if (BOARD_COLUMN * BOARD_ROW == points[0] + points[1]) {
			return true;
		}
		return false;
	}
/**
 * <h1>Sieger</h1>
 * <p>Der Spieler, der mehr Steine seiner Farbe auf dem Plan hat, gewinnt.
 */
	private void winner(){
		if (points[0] == points[1]) {
			this.winner[1]++;
		} else if (points[0] > points[1]) {
			this.winner[0]++;
		} else if (points[0] < points[1]) {
			this.winner[2]++;
		}
	}

	/*
	 * public static void main(String[] args) { Game game = new Game(); }
	 */
}
/*
 * 
 * for(int i = 0; i < this.possibleMove2.length;i++){
 * if(board[getBoardCoordinate(possibleMove2[i])[0]][getBoardCoordinate(
 * possibleMove2[i])[1]] == EMPTY_FIELD){
 * board[getBoardCoordinate(possibleMove2[i])[0]][getBoardCoordinate(
 * possibleMove2[i])[1]] = POSSIBLEMOVE2 * getCurrentPlayer(); } } return uff;
 */