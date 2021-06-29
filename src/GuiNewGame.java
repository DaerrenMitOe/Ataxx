import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

import java.awt.GridLayout;
import java.awt.FlowLayout;


public class GuiNewGame extends JFrame {
    private App app;

    private JPanel[] panel;
    private JLabel[] label;
    private JButton[] button;
    private JComboBox[] comboBox;
    private JRadioButton[] radioButton;

    public GuiNewGame(App app) {
        super("New Game");

        panel = new JPanel[5];
        button = new JButton[2];
        comboBox = new JComboBox[2];
        radioButton = new JRadioButton[3];
;
        this.app = app;

        // Panel 1
        panel[0] = new JPanel();
        panel[0].setLayout(new GridLayout(1, 1));
        panel[0].add(new JLabel("Players"));

        // Panel 2
        panel[1] = new JPanel();
        panel[1].setLayout(new GridLayout(2, 2));

        button[0] = new JButton();
        button[0].setBackground(Color.BLUE);
        button[0].setActionCommand("player1");
        button[0].addActionListener(app);

        comboBox[0] = new JComboBox();
        comboBox[0].addItem("Human");
		comboBox[0].addItem("Random AI");
		//comboBox1.addItem("AI");
        comboBox[0].setActionCommand("player1cb");
		comboBox[0].addActionListener(app);

        button[1] = new JButton();
        button[1].setBackground(Color.RED);
        button[1].setActionCommand("player2");
        button[1].addActionListener(app);

        comboBox[1] = new JComboBox();
        comboBox[1].addItem("Human");
		comboBox[1].addItem("Random AI");
		//comboBox2.addItem("AI");
        comboBox[1].setActionCommand("player2cb");
		comboBox[1].addActionListener(app);

        panel[1].add(button[0]);
        panel[1].add(comboBox[0]);
        panel[1].add(button[1]);
        panel[1].add(comboBox[1]);
        
        // Panel 3
        panel[2] = new JPanel();
        panel[2].setLayout(new GridLayout(1, 1));
        panel[2].add(new JLabel("Time Control"));

        // Panel 4
        panel[3] = new JPanel();
        panel[3].setLayout(new GridLayout(3, 1));

        radioButton[0] = new JRadioButton("Untimed");
        radioButton[0].setActionCommand("untimed");
        radioButton[0].addActionListener(app);

        radioButton[1] = new JRadioButton("Blitz 5 min");
        radioButton[1].setActionCommand("blitz");
        radioButton[1].addActionListener(app);

        radioButton[2] = new JRadioButton("Rapid 15 min + 5 sec/move");
        radioButton[2].setActionCommand("rapid");
        radioButton[2].addActionListener(app);

        panel[3].add(radioButton[0]);
        panel[3].add(radioButton[1]);
        panel[3].add(radioButton[2]);

        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(radioButton[0]);
        buttonGroup1.add(radioButton[1]);
        buttonGroup1.add(radioButton[2]);

        // Panel 5
        panel[4] = new JPanel();
        panel[4].setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel[4].add(new JButton("Cancel"));
        panel[4].add(new JButton("Start Game"));
        
        // Hauptfenster
        setLayout(new GridLayout(5,1));
        add(panel[0]);
        add(panel[1]);
        add(panel[2]);
        add(panel[3]);
        add(panel[4]);
    }

    private JPanel gameVariant(JPanel panel){

    }

    private void timeControl(JPanel panel){
        String[] timeControl = {
            "Real Time", "Unlimited"
        };


        panel = new JPanel();
        for(int i = 0; i < timeControl.length; i++){

        }
        comboBox[0] = new JComboBox();
        comboBox[0].addItem("Human");
		comboBox[0].addItem("Random AI");
		//comboBox1.addItem("AI");
        comboBox[0].setActionCommand("timeControl");
		comboBox[0].addActionListener(app);
    }
    public void initNewGame(){
        // Hauptfenster
        setLayout(new GridLayout(5,1));
        add(panel[0]);
        add(panel[1]);
        add(panel[2]);
        add(panel[3]);
        add(panel[4]);
    }

    public static void main(String[] args) {
        App a = new App();
        GuiNewGame n = new GuiNewGame(a);

        n.setSize(500, 500);
        n.setLocation(500, 200);
        n.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        n.setResizable(false);
        n.setVisible(true);
    }
}