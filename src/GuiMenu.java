import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.GridLayout;

public class GuiMenu extends JFrame {
    private Container c;

	private JLabel[][] label;
    private JButton[][] button;
    public GuiMenu() {
        //Titelleiste definieren
		super("einfaches JLabel Demo");

		//Referenz auf eigenen Container
		c = getContentPane();

		//Hintergrundfarbe setzen
		c.setBackground(new Color(241,241,241));

		// Layout-Manager des Hauptfensters setzen.
		c.setLayout(new GridLayout(3, 1));

		this.label = new JLabel[3][1];
        this.button = new JButton[3][1];

        label[0][0] = new JLabel("Ataxx");
        c.add(label[0][0]);
        button[1][0] = new JButton("vs Computer");
        c.add(button[1][0]);
        button[2][0] = new JButton("vs Player");
        c.add(button[2][0]);
	}
    
}