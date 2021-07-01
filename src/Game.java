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
	private boolean gameOver;
	protected boolean[] currentPlayer = new boolean[2];
	protected boolean[] winner = new boolean[2];
	protected int[] player = new int[2];
	protected double[] playerPoints = new double[2];
	protected double[] stonePoints = new double[2];
	protected int[][] board;

	private String lastAction;
	protected String[] possibleMove;
	protected String[] possibleMove1;
	protected String[] possibleMove2;

	public Game() {
		initBoard();
		initGame();
		COORDINATE = initCoordinate();
	}
	/**
	 * <p> Initialisiert das Spielbrett mit der Startposition
	 */
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

	/**
	 * <p> Initialisiert die Brettkoordinanten
	 * @return Brettkoordinate
	 */
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
	
	/**
	 * <p> Initialister die Startwerte des Spiel
	 */
	public void initGame() {
		this.move = 1;
		this.gameRound = 0;

		this.currentPlayer[0] = true;
		this.currentPlayer[1] = false;

		this.playerPoints[0] = 0;
		this.playerPoints[1] = 0;

		this.stonePoints[0] = 0;
		this.stonePoints[1] = 0;

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
		System.out.println(Arrays.deepToString(uff));

		for(int i = 0; i < uff.length; i++){
			String[] possibleMove = setPossibleMove(uff[i]);
			if(possibleMove.length != 0){
				return false;
			}
		}
			
		this.move++;
		return true;
	}

/**
 * <p> Setzt Stein an 
 * @param action
 */
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
		String[] possibleMove1 = setAllPossibleMove1(getBoardCoordinate(action)[0], getBoardCoordinate(action)[1]);
		for (int i = 0; i < possibleMove1.length; i++) {
			if (board[getBoardCoordinate(possibleMove1[i])[0]][getBoardCoordinate(
					possibleMove1[i])[1]] == -getCurrentPlayer()) {
				board[getBoardCoordinate(possibleMove1[i])[0]][getBoardCoordinate(
						possibleMove1[i])[1]] = getCurrentPlayer();
			}
		}
	}
	
	/**
	 * <p> Setzt ein leeres Feld am vorletzten Zug
	 */
	private void setEmptyField() {
		board[getBoardCoordinate(getLastAction())[0]][getBoardCoordinate(getLastAction())[1]] = EMPTY_FIELD;
	}

	/**
	 * @return Gibt vorletzten Zug zurück
	 */
	private String getLastAction() {
		return this.lastAction;
	}

	/**
	 * <p> Speichert vorletzten Zug ab
	 * @param action vorletzter Zug
	 */
	public void setLastAction(String action) {
		this.lastAction = action;
	}

	/**
	 * <p> Zeige mögliche Züge an, die ein Feld und zwei Felder weiter von den letzten Zug entfernt sind
	 * @param action letzter Zug
	 */
	public void showPossibleMove(String action) {
		showPossibleMove1(action);
		showPossibleMove2(action);
	}

	/**
	 * <p> Zeige mögliche Züge an, die ein Feld weiter von den letzten Zug entfernt sind
	 * @param action letzter Zug
	 */
	private void showPossibleMove1(String action) {
		this.possibleMove1 = setPossibleMove1(action);
		for (int i = 0; i < this.possibleMove1.length; i++) {
			board[getBoardCoordinate(this.possibleMove1[i])[0]][getBoardCoordinate(this.possibleMove1[i])[1]] = POSSIBLEMOVE1
						* getCurrentPlayer();
			}
	}

	/**
	 * <p> Zeige mögliche Züge an, die zwei Felder weiter von den letzten Zug sind entfernt
	 * @param action letzter Zug
	 */
	private void showPossibleMove2(String action) {
		this.possibleMove2 = setPossibleMove2(action);
		for (int i = 0; i < this.possibleMove2.length; i++) {
			board[getBoardCoordinate(this.possibleMove2[i])[0]][getBoardCoordinate(this.possibleMove2[i])[1]] = POSSIBLEMOVE2
						* getCurrentPlayer();
		}
	}

	/**
	 * <p> Löscht mögliche Züge an, die ein Feld und zwei Felder weiter von den letzten Zug entfernt sind
	 */
	public void deletePossibleMove() {
        deletePossibleMove1();
        deletePossibleMove2();
    }

	/**
	 * <p> Löscht mögliche Züge an, die ein Feld weiter von den letzten Zug entfernt sind
	 */
    private void deletePossibleMove1() {
        for (int i = 0; i < BOARD_COLUMN; i++) {
            for (int j = 0; j < BOARD_ROW; j++) {
                if (board[i][j] == POSSIBLEMOVE1 * getCurrentPlayer())
                    board[i][j] -= POSSIBLEMOVE1 * getCurrentPlayer();
            }
        }
    }

	/**
	 * <p> Löscht mögliche Züge an, die zwei Felder weiter von den letzten Zug entfernt sind
	 */
    private void deletePossibleMove2() {
        for (int i = 0; i < BOARD_COLUMN; i++) {
            for (int j = 0; j < BOARD_ROW; j++) {
                if (board[i][j] == POSSIBLEMOVE2 * getCurrentPlayer())
                    board[i][j] -= POSSIBLEMOVE2 * getCurrentPlayer();
            }
        }
    }

	/**
	 * <p> Löscht alle nicht möglichen Züge aus dem Array
	 * @param array alle möglichen Züge
	 * @return mögliche Züge
	 */
	private String[] setPossibleMove(String[] array) {
		int i = 0;
		for (int j = 0; j < array.length; j++) {
			if (board[getBoardCoordinate(array[j])[0]][getBoardCoordinate(array[j])[1]] == EMPTY_FIELD) {
				i++;
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

	/**
	 * <p> Gibt Position der Brettkoordinate auf dem Brett zurück
	 * @param coordinate Brettkoordinate
	 * @return Position der Brettkoordinate
	 */
	private  int[] getBoardCoordinate(String coordinate) {
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
	 * <p> Gibt den Wert der Brettkoordinate auf dem Brett zurück
	 * @param coordinate Brettkoordinate
	 * @return Wert der Brettkoordinate
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

	/**
	 * <p> Gibt alle möglichen Züge zurück, die ein Feld und zwei Felder weiter von der Brettposition entfernt sind
	 * @param column Brettpostion x
	 * @param row Brettpostion y
	 * @return alle möglichen Züge
	 */
	private String[] setAllPossibleMove(int column, int row){
		String[] allPossibelMove1 = setAllPossibleMove1(column, row);
		String[] allPossibelMove2 = setAllPossibleMove2(column, row);
		String[] allPossibelMove = new String[allPossibelMove1.length + allPossibelMove2.length];

		int k = 0;
		for (int i = 0; i < allPossibelMove1.length; i++) {
			allPossibelMove[k] = allPossibelMove1[i];
			k++;
		}

		for (int i = 0; i < allPossibelMove2.length; i++) {
			allPossibelMove[k] = allPossibelMove2[i];
			k++;
		}

		return allPossibelMove;
	}

	/**
	 * <p> Gibt mögliche Züge zurück, die ein Feld von der Brettkoordinate entfernt sind
	 * @param action letzter Zug
	 * @return mögliche Züge
	 */
	public String[] setPossibleMove1(String action) {
		String[] possibleMove1 = setAllPossibleMove1(getBoardCoordinate(action)[0], getBoardCoordinate(action)[1]);
		possibleMove1 = setPossibleMove(possibleMove1);

		return possibleMove1;
	}

	/**
	 * <p> Gibt alle möglichen Züge zurück, die ein Feld von der Brettposition entfernt sind
	 * @param column Brettpostion x
	 * @param row Brettpostion y
	 * @return alle möglichen Züge
	 */
	private String[] setAllPossibleMove1(int column, int row) {
		/*
		 * beginnt links unten gegen den uhrzeigersinn
		 */

		String[] possibleMove1 = new String[POSSIBLEMOVE1];

		int i = 0;
		// links unten
		if (column + 1 <= 6 && row - 1 >= 0) {
			possibleMove1[i] = COORDINATE[column + 1][row - 1];
			i++;
		}

		// mitte unten
		if (column + 1 <= 6) {
			possibleMove1[i] = COORDINATE[column + 1][row];
			i++;
		}

		// rechts unten
		if (column + 1 <= 6 && row + 1 <= 6) {
			possibleMove1[i] = COORDINATE[column + 1][row + 1];
			i++;
		}

		// mitte rechts
		if (row + 1 <= 6) {
			possibleMove1[i] = COORDINATE[column][row + 1];
			i++;
		}

		// rechts oben
		if (column - 1 >= 0 && row + 1 <= 6) {
			possibleMove1[i] = COORDINATE[column - 1][row + 1];
			i++;
		}

		// mitte oben
		if (column - 1 >= 0) {
			possibleMove1[i] = COORDINATE[column - 1][row];
			i++;
		}

		// links oben
		if (column - 1 >= 0 && row - 1 >= 0) {
			possibleMove1[i] = COORDINATE[column - 1][row - 1];
			i++;
		}

		// mitte links
		if (row - 1 >= 0) {
			possibleMove1[i] = COORDINATE[column][row - 1];
			i++;
		}

		return deleteNull(possibleMove1);
	}

	/**
	 * <p> Gibt mögliche Züge zurück, die zwei Felder weiter von der Brettkoordinate entfernt sind
	 * @param action Brettkoordinate
	 * @return mögliche Züge
	 */
	public String[] setPossibleMove2(String action) {
		String[] possibleMove2 = setAllPossibleMove2(getBoardCoordinate(action)[0], getBoardCoordinate(action)[1]);
		possibleMove2 = setPossibleMove(possibleMove2);

		return possibleMove2;
	}

	/**
	 * <p> Gibt alle möglichen Züge zurück, die zwei Felder weiter von der Brettposition entfernt sind
	 * @param column Brettpostion x
	 * @param row Brettpostion y
	 * @return alle möglichen Züge
	 */
	private String[] setAllPossibleMove2(int column, int row) {
		/*
		 * beginnt links unten gegen den uhrzeigersinn
		 */

		String[] possibleMove2 = new String[POSSIBLEMOVE2];
		int i = 0;

		// links unten
		if (column + 2 <= 6 && row - 2 >= 0) {
			possibleMove2[i] = COORDINATE[column + 2][row - 2];
			i++;
		}

		// mitte unten links
		if (column + 2 <= 6 && row - 1 >= 0) {
			possibleMove2[i] = COORDINATE[column + 2][row - 1];
			i++;
		}

		// mitte unten
		if (column + 2 <= 6) {
			possibleMove2[i] = COORDINATE[column + 2][row];
			i++;
		}

		// mitte unten rechts
		if (column + 2 <= 6 && row + 1 <= 6) {
			possibleMove2[i] = COORDINATE[column + 2][row + 1];
			i++;
		}

		// rechts unten
		if (column + 2 <= 6 && row + 2 <= 6) {
			possibleMove2[i] = COORDINATE[column + 2][row + 2];
			i++;
		}

		// mitte rechts unten
		if (column + 1 <= 6 && row + 2 <= 6) {
			possibleMove2[i] = COORDINATE[column + 1][row + 2];
			i++;
		}

		// mitte rechts
		if (row + 2 <= 6) {
			possibleMove2[i] = COORDINATE[column][row + 2];
			i++;
		}

		// mitte rechts oben
		if (column - 1 >= 0 && row + 2 <= 6) {
			possibleMove2[i] = COORDINATE[column - 1][row + 2];
			i++;
		}

		// rechts oben
		if (column - 2 >= 0 && row + 2 <= 6) {
			possibleMove2[i] = COORDINATE[column - 2][row + 2];
			i++;
		}

		// mitte oben rechts
		if (column - 2 >= 0 && row + 1 <= 6) {
			possibleMove2[i] = COORDINATE[column - 2][row + 1];
			i++;
		}

		// mitte oben
		if (column - 2 >= 0) {
			possibleMove2[i] = COORDINATE[column - 2][row];
			i++;
		}

		// mitte oben links
		if (column - 2 >= 0 && row - 1 >= 0) {
			possibleMove2[i] = COORDINATE[column - 2][row - 1];
			i++;
		}

		// links oben
		if (column - 2 >= 0 && row - 2 >= 0) {
			possibleMove2[i] = COORDINATE[column - 2][row - 2];
			i++;
		}

		// mitte links oben
		if (column - 1 >= 0 && row - 2 >= 0) {
			possibleMove2[i] = COORDINATE[column - 1][row - 2];
			i++;
		}

		// mitte links
		if (row - 2 >= 0) {
			possibleMove2[i] = COORDINATE[column][row - 2];
			i++;
		}

		// mitte links unten
		if (column + 1 <= 6 && row - 2 >= 0) {
			possibleMove2[i] = COORDINATE[column + 1][row - 2];
			i++;
		}

		return deleteNull(possibleMove2);
	}

	/**
	 * <p> Gibt mögliche Züge zurück, die ein Feld und zwei Felder weiter von der Brettposition entfernt sind
	 * @param action Brettkoordinate
	 * @return mögliche Züge
	 */
	public String[] setPossibleMove(String action) {
		String[] possibleMove1 = setPossibleMove1(action);
		String[] possibleMove2 = setPossibleMove2(action);
		String[] possibleMove = new String[possibleMove1.length + possibleMove2.length];

		int k = 0;
		for (int i = 0; i < possibleMove1.length; i++) {
			possibleMove[k] = possibleMove1[i];
			k++;
		}

		for (int i = 0; i < possibleMove2.length; i++) {
			possibleMove[k] = possibleMove2[i];
			k++;
		}

		return possibleMove;
	}

	/**
	 * <p> Gibt Array ohne Null zurück
	 * @param array Array mit null
	 * @return Array ohne null
	 */
	private String[] deleteNull(String[] array) {
		int i = 0;
		for (int j = 0; j < array.length; j++) {
			if (array[j] != null) {
				i++;
			}
		}

		// anderer name für uff

		String[] uff = new String[i];

		for (int j = 0; j < uff.length; j++) {
			/*
			array die eingeben werden 
			null im array kommen hintereinader am ende vor
			if (array[j] != null) {
				uff[j] = array[j];
			}
			*/
			uff[j] = array[j];

		}

		return uff;
	}

	/**
	 * <p> Gibt alle Steine auf dem Brett vom Spieler der am Zug ist zurück
	 * @return Brettkoordinate von den Steinen
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

		return deleteNull(stoneOnBoard);
	}

	/**
	 * <p> Gibt den Spieler der am Zug ist zurück
	 * @return Spieler
	 */
	public int getCurrentPlayer() {
		if (this.move % 2 == 0) {
			return PLAYER;
		}
		return -PLAYER;
	}

	/**
	 * <p> Speichert die Punktzahl der Spielern ab
	 */
	public void setStonePoints() {
		resetStonePoints();

		for (int j = 0; j < BOARD_COLUMN; j++) {
			for (int k = 0; k < BOARD_ROW; k++) {
				if (board[j][k] == -PLAYER) {
					this.stonePoints[0]++;
				} else if (board[j][k] == PLAYER) {
					this.stonePoints[1]++;
				}
			}
		}
	}

	/**
	 * <p> Setzt die Punktzahl der Spielern auf 0
	 */
	public void resetStonePoints() {
		for (int i = 0; i < stonePoints.length; i++) {
			stonePoints[i] = 0;
		}
	}

	/**
	 * <p> Seitenwechsel
	 */
	public void swapPosition() {
		for (int i = 0; i < player.length; i++) {
			player[i] = -player[i];
		}
	}

	/**
	 * <h1>Spielende</h1>
	 * <p>Das Spiel endet,
	 * <ul>
	 * <li>wenn ein Spieler keine Steine mehr hat.</li>
	 * <li>wenn beide Spieler keine Züge mehr machen können.</li>
	 * </ul>
	 * @return Spielende
	 */
	public boolean gameOver() {
		setGameOver();
		setStonePoints();
		if(gameOver1() || gameOver2()) {
			winner();
			return true;
			
		}
		return false;
	}

	/**
	 * <h1>Spielende</h1>
	 * <p>Das Spiel endet,
	 * <ul>
	 * <li>wenn ein Spieler keine Steine mehr hat.</li>
	 * </ul>
	 * @return Spielende
	 */
	private boolean gameOver1(){
		for(int i = 0; i < this.stonePoints.length; i++){
			if (this.stonePoints[i] == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <h1>Spielende</h1>
	 * <p>Das Spiel endet,
	 * <ul>
	 * <li>wenn beide Spieler keine Züge mehr machen können.</li>
	 * </ul>
	 * @return Spielende
	 */
	private boolean gameOver2(){
		if (BOARD_COLUMN * BOARD_ROW == stonePoints[0] + stonePoints[1]) {
			return true;
		}
		return false;
	}

	/**
	 * <h1> Sonderfall: Endloses Spiel </h1>
	 * <p> Am Spielende können (selten!) Situationen entstehen, in denen unendlich lange 
	 * weitergespielt wird und immer wieder dieselben Stellungen auftreten. Wenn 3 x dieselbe 
	 * Stellung in einer Partie auftritt, dann endet das Spiel auch. Auch dann gewinnt der 
	 * Spieler mit den meisten Steinen in seiner Farbe.
	 * @return Spielende
	 */
	private boolean gameOver3(){
		return true;
	}

	/**
	 * <h1>Sieger</h1>
	 * <p>Der Spieler, der mehr Steine seiner Farbe auf dem Brett hat, gewinnt.
	 */
	private void winner(){
		if (stonePoints[0] == stonePoints[1]) {
			//Spieler Punkte
			this.playerPoints[0] += 0.5;
			this.playerPoints[1] += 0.5;

			this.winner[0] = true;
			this.winner[1] = true;
		} else if (stonePoints[0] > stonePoints[1]) {
			this.playerPoints[0] += 1;

			this.winner[0] = true;
			this.winner[1] = false;
		} else if (stonePoints[0] < stonePoints[1]) {
			this.playerPoints[1] += 1;

			this.winner[0] = false;
			this.winner[1] = true;
		}
	}

	/**
	 * Vergibt Spielerpunkte
	 */
	private void setPlayerPoints(){
		if(this.winner[0] && this.winner[1]){
			this.playerPoints[0] += 0.5;
			this.playerPoints[1] += 0.5;
		} else if(this.winner[0]){
			this.playerPoints[0] += 1;
		} else if(this.winner[1]){
			this.playerPoints[1] += 1;
		}
	}

	/**
	 * <p> Setzt Spielerpunkte zurück auf 0
	 */
	private void resetPlayerPoints(){
		for(int i = 0; i < playerPoints.length; i++){
			this.playerPoints[i] = 0;
		}
	}

	/**
	 * <p>Setzt die Gewinner zurück
	 */
	private void resetWinner(){
		this.winner[0] = false;
		this.winner[1] = false;
	}

	/**
	 * <p> Setzt this.gameOver auf True, wenn das Spiel vorbei ist
	 */

	private void setGameOver(){
		this.gameOver = true;
	}

	/**
	 * <p> Setzt this.gameOver auf True, wenn das Spiel vorbei ist
	 */

	private void resetGameOver(){
		this.gameOver = false;
	}

	
	public String[][] playerStone(){
		String[][] uff = new String[player.length][BOARD_COLUMN * BOARD_COLUMN];
		int k = 0;
		for (int i = 0; i < BOARD_COLUMN; i++) {
			for (int j = 0; j < BOARD_ROW; j++) {
				if(board[i][j] == -PLAYER){
					uff[0][k] = COORDINATE[i][j];
					k++;
				} else if(board[i][j] == PLAYER){
					uff[1][k] = COORDINATE[i][j];
					k++;
				}
			}
		}

		// uff[0][k] = spiler -1
		// uff[1][k] = spieler 1

		return uff;
	}

	public void nextRound() {
		/*
		3d recheckt [länge][höhe][breite]
		*/
        String[][][] kek = new String [2][1][BOARD_COLUMN * BOARD_ROW];

        int a = kek[0].length - 1;
		for(int j = 0; j < 2; j++){
			for(int i = 0; i < playerStone()[0].length; i++){
				kek[j][0][i] = playerStone()[j][i];
			}
		}
		System.out.println(Arrays.deepToString(kek));
    }
}