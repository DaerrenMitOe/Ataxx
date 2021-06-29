import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import java.awt.GridLayout;
import java.awt.BorderLayout;

public class GuiGame extends JFrame {
    private App app;
    private Game game;

    private JPanel[] panel;
    public JButton[][] button;

    public GuiGame(App app) {
        super("Attax");

        game = new Game();
        panel = new JPanel[5];

        this.app = app;

        initGuiBoard();
    }

    /**
     * Speichert Buchstabenkoordinate im Container
     * @param panel Container
     * @return Buchstabenkoordinate im Container
     */
    private JPanel letterCoordinate(JPanel panel) {
        String[] letter = {
            "A", "B", "C", "D", "E", "F", "G"
        };

        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 7));
        for (int i = 0; i < game.BOARD_ROW; i++) {
            panel.add(new JLabel(letter[i]));

        }

        return panel;
    }

    /**
     * Speichert Spielbrett im Container
     * @param panel Container
     * @return Spielbrett
     */
    private JPanel board(JPanel panel) {
        panel = new JPanel();
        button = new JButton[7][7];
        panel.setLayout(new GridLayout(7, 7));
        for (int i = 0; i < game.BOARD_ROW; i++) {
            for (int j = 0; j < game.BOARD_COLUMN; j++) {
                button[i][j] = new JButton();
                button[i][j].setActionCommand(game.COORDINATE[i][j]);
                button[i][j].addActionListener(app);
                panel.add(button[i][j]);
            }
        }

        return panel;
    }

    /**
     * Speichert Zahlenkoordinate im Container
     * @param panel Container
     * @return Zahlenkoordinate
     */
    private JPanel numberCoordinate(JPanel panel) {
        panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1));
        for (int i = 0; i < game.BOARD_COLUMN; i++) {
            panel.add(new JLabel(String.valueOf(7 - i)));
        }

        return panel;
    }

    /**
     * Initialisiert Spielbrett mit Koordinaten
     */
    private void initGuiBoard() {
        this.panel = new JPanel[5];

        // Panel 1
        panel[0] = letterCoordinate(panel[0]);

        // Panel 2
        panel[1] = numberCoordinate(panel[1]);

        // Panel 3
        panel[2] = board(panel[2]);

        // Panel 4
        panel[3] = numberCoordinate(panel[3]);

        // Panel 5
        panel[4] = letterCoordinate(panel[4]);

        // Hauptfenster
        setLayout(new BorderLayout());
        add(panel[0], BorderLayout.NORTH);
        add(panel[1], BorderLayout.WEST);
        add(panel[2], BorderLayout.CENTER);
        add(panel[3], BorderLayout.EAST);
        add(panel[4], BorderLayout.SOUTH);
    }

    /**
     * Speichert Spielverlauf im Container
     * @param Container
     * @return Spielverlauf im Container
     */
    private JPanel matchhistory(){

        String[] namen = {
            "egon", "frau", "haengen", "maus", "passwort", "post", 
            "roboter", "treiber", "windows", "zaehne"
        };

        JPanel panel = new JPanel();
        JList list1 = new JList(namen);
        JScrollPane scrollPane1 = new JScrollPane(list1);

        JList list2 = new JList(namen);
        JScrollPane scrollPane2 = new JScrollPane(list2);

        JList list3 = new JList(namen);
        JScrollPane scrollPane3 = new JScrollPane(list3);

        list1.setEnabled(false);
        list2.setEnabled(false);
        list3.setEnabled(false);

        list1.setForeground(Color.red);

        panel.setLayout(new GridLayout(1, 3));

        panel.add(list1);
        panel.add(list2);
        panel.add(list3);

        return panel;
    }

    public void gameOver(String winner) {
        // Erstellung Array vom Datentyp Object, Hinzufügen der Optionen		
        Object[] options = {
            "Rematch", "New Game", "Analysis Board"
        };

        int n = JOptionPane.showOptionDialog(this,
                                                    winner,
                                                    "Game Over",
                        JOptionPane.DEFAULT_OPTION, 
                                                    JOptionPane.PLAIN_MESSAGE, 
                        null, options,null);

        switch(n){
            case 0:
                new Game();
                break;

            case 1:
                game.initBoard();
                break;
                
            case 2:
                break;
        }
	}
}