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
import java.util.Arrays;

public class GuiGame extends JFrame {
    private App app;
    private Game game;

    private Color background = new Color(0XBBADA0);
    private Font font = new Font("Tahoma", Font.BOLD, 15);

    private JPanel[] panel;
    private JLabel[] label;
    public JButton[][] button;

    public GuiGame(App app) {
        super("Attax");

        String[] letter = { 
            "A", "B", "C", "D", "E", "F", "G" 
        };

        game = new Game();
        panel = new JPanel[5];

        this.app = app;
   
        // Panel 1
        panel[0] = letterCoordinate();

        // Panel 2
        panel[1] = numberCoordinate();

        // Panel 3
        panel[2] = board();

        // Panel 4
        panel[3] = numberCoordinate();

        // Panel 5
        panel[4] = letterCoordinate();

        // Hauptfenster
        setLayout(new BorderLayout());
        add(panel[0], BorderLayout.NORTH);
        add(panel[1], BorderLayout.WEST);
        add(panel[2], BorderLayout.CENTER);
        add(panel[3], BorderLayout.EAST);
        add(panel[4], BorderLayout.SOUTH);
        
    }

    private JPanel letterCoordinate() {
        String[] letter = { "A", "B", "C", "D", "E", "F", "G" };

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 7));
        for (int i = 0; i < game.BOARD_ROW; i++) {
            panel.add(new JLabel(letter[i]));

        }

        return panel;
    }

    private JPanel board() {
        /*
        action lister funktioniert nicht wegen funktion
        void oder return benutzen
        */
        JPanel panel = new JPanel();
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

    private JPanel numberCoordinate() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1));
        for (int i = 0; i < game.BOARD_COLUMN; i++) {
            panel.add(new JLabel(String.valueOf(7 - i)));
        }

        return panel;
    }

    private void initGuiBoard() {
        panel = new JPanel[5];

        // Panel 1
        panel[0] = letterCoordinate();

        // Panel 2
        panel[1] = numberCoordinate();

        // Panel 3
        panel[2] = board();

        // Panel 4
        panel[3] = numberCoordinate();

        // Panel 5
        panel[4] = letterCoordinate();
        //panel[4] = matchhistory();

        // Hauptfenster
        setLayout(new BorderLayout());
        add(panel[0], BorderLayout.NORTH);
        add(panel[1], BorderLayout.WEST);
        add(panel[2], BorderLayout.CENTER);
        add(panel[3], BorderLayout.EAST);
        add(panel[4], BorderLayout.SOUTH);
    }

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
            "Rematch", "New Game", "Cancel"
        };

        int selected = JOptionPane.showOptionDialog(null,
                                                    winner,
                                                    "Game Over",
                        JOptionPane.DEFAULT_OPTION, 
                                                    JOptionPane.INFORMATION_MESSAGE, 
                        null, options,null);

        switch(selected){
            case 0:
                new Game();
                break;

            case 1:
                game.initBoard();
                app.showBoard();
                break;
                
            case 2:
                break;
        }
        System.out.println(selected);
	}
/*
    public static void main(String[] args) {

        GuiGame n = new GuiGame(a);

        n.setSize(500, 500);
        n.setLocation(500, 200);
        n.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        n.setResizable(false);
        n.setVisible(true);
    }
    */
}