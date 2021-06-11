import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;
import java.util.Arrays;


public class GuiGame extends JFrame {
    //private App application;

    private Game game;

	private Container c;

	private Color background = new Color (0XBBADA0);
	private Font font = new Font("Tahoma", Font.BOLD, 38);
	

    protected JButton[][] button;
    protected JLabel[][] label;


    public GuiGame(App app) {
        super("Attax");

        game = new Game();

        c = getContentPane();
        c.setBackground(background);
        c.setLayout(null);

        c.setLayout(new GridLayout(game.BOARD_COLUMN + 1, game.BOARD_ROW + 1));

        this.button = new JButton[game.BOARD_COLUMN + 1][game.BOARD_ROW + 1];
        this.label = new JLabel[game.BOARD_COLUMN + 1][game.BOARD_ROW + 1];



        String[] letter = {
            "A", "B", "C", "D", "E", "F", "G"
        };

        for(int i = 0; i < button.length; i++) {
            for(int j = 0; j < button.length; j++) {
                if(i < 7 && j == 0){
                    label[i][j] = new JLabel(String.valueOf(7- i));
                    label[i][j].setHorizontalAlignment(JLabel.CENTER);
                    c.add(label[i][j]);
                } else if(i == 7 && j == 0) {
                    label[i][j] = new JLabel();
                    label[i][j].setHorizontalAlignment(JLabel.CENTER);
                    c.add(label[i][j]);
                } else if(i == 7 && j > 0) { 
                    label[i][j] = new JLabel(letter[j-1]);
                    label[i][j].setHorizontalAlignment(JLabel.CENTER);
                    c.add(label[i][j]);
                } else {
                    button[i][j] = new JButton();
                    button[i][j].setFont(font);
                    button[i][j].setActionCommand(letter[j-1] + String.valueOf(7- i));
                    button[i][j].addActionListener(app);
                    button[i][j].setHorizontalAlignment(JButton.CENTER);
                    button[i][j].setOpaque(true);
                    //label[i][j].setBounds(25 + (100* j),25 + (100* i),75+ (100* j), 75  + (100* i));
                    //button[i][j].setBorder(new LineBorder(background, 25));
                    
                    c.add(button[i][j]);
                }
            }
        }
    }

    public void setMove(String text) {
        System.out.println(text);
	}

    public void board(){
        
    }

    public void menu(){
        
    }
}