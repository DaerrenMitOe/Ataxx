import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.Arrays;


public class GuiGame extends JFrame {
    private App app;

    private Game game;

	private Container c;

	private Color background = new Color (0XBBADA0);
	private Font font = new Font("Tahoma", Font.BOLD, 15);
	
    private JPanel[] panel;
    private JLabel[] label;
    protected JButton[][] button;

/*
    protected JButton[][] button;
    protected JLabel[][] label;
    private JPanel panel;
*/

    public GuiGame(App app) {
        super("Attax");
        game = new Game();

        String[] letter = {
            "A", "B", "C", "D", "E", "F", "G"
        };
        panel = new JPanel[5];
        button = new JButton[7][7];

        // Panel 1
        panel[0] = new JPanel();
        panel[0].setLayout(new GridLayout(1, 9));
        for(int i = 0; i < game.BOARD_ROW + 2; i++){
            if(i == 0 || i == 8){
                panel[0].add(new JLabel());
            } else {
                panel[0].add(new JLabel(letter[i-1]));
            }
        }

        // Panel 2
        panel[1] = new JPanel();
        panel[1].setLayout(new GridLayout(7, 1));
        for(int i = 0; i > game.BOARD_COLUMN; i++){
            panel[1].add(new JLabel(String.valueOf(7 - i)));
        }

        // Panel 3
        panel[2] = new JPanel();
        panel[2].setLayout(new GridLayout(7, 7));
        for(int i = 0; i < game.BOARD_ROW; i++){
            for(int j = 0; j < game.BOARD_COLUMN; j++){
                button[i][j] = new JButton();
                button[i][j].setActionCommand(game.COORDINATE[i][j]);
                button[i][j].addActionListener(app);
                panel[2].add(button[i][j]);
            }
        }

        // Panel 4
        panel[3] = new JPanel();
        panel[3].setLayout(new GridLayout(7, 1));
        for(int i = 0; i > game.BOARD_COLUMN; i++){
            panel[3].add(new JLabel(String.valueOf(7 - i)));
        }

        // Panel 5
        panel[4] = new JPanel();
        panel[4].setLayout(new GridLayout(1, 9));
        for(int i = 0; i < game.BOARD_ROW + 2; i++){
            if(i == 0 || i == 8){
                panel[4].add(new JLabel());
            } else {
                panel[4].add(new JLabel(letter[i-1]));
            }
        }

        // Hauptfenster
        setLayout(new BorderLayout());
        add(panel[0], BorderLayout.NORTH);
        add(panel[1], BorderLayout.WEST);
        add(panel[2], BorderLayout.CENTER);
        add(panel[3], BorderLayout.EAST);
        add(panel[4], BorderLayout.SOUTH);

        //setLayout(new GridLayout(7,1));
        //add(panel[0]);
        //add(panel[1]);
        //add(panel[2]);
        //add(panel[3]);
        //add(panel[4]);

    }

    public void setMove(String text) {
        System.out.println(text);
	}
    public static void main(String[] args) {
        App a = new App();
        GuiGame n = new GuiGame(a);

        n.setSize(500, 500);
        n.setLocation(500, 200);
        n.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        n.setResizable(false);
        n.setVisible(true);
    }
}