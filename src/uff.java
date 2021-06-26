public class uff {
    private Game game;
    private String[][][] uff;
    private int gameRound = 1;
    private String[] lol = new String[1];
    public uff() {
        uff = new String [gameRound][game.BOARD_COLUMN * game.BOARD_ROW][2];
    }

    public void nextRound() {

        String[][][] kek1 = new String [gameRound + 1][game.BOARD_COLUMN * game.BOARD_ROW][2];

        int a = kek1[0].length - 1;
        
        kek[a] = game.playerStone();



    }

    public void add(String action){
        String[] lul = new String[lol.length + 1];

        for(int i = 0; i < lol.length; i++){
            lul[i] = lol[i];
        }

        lul[lol.length] = action;
        this.lol = lul;
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