import javax.swing.JFrame;

public class JLabelDemoSteuerung3 {
	private GuiMenu dieOberflaeche;

	public JLabelDemoSteuerung3() {
		dieOberflaeche = new GuiMenu();
		dieOberflaeche.setSize(200,250);
		dieOberflaeche.setLocation(500,200);
		dieOberflaeche.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dieOberflaeche.setResizable(false);
		dieOberflaeche.setVisible(true);
	}

	public static void main(String[] args) {
		JLabelDemoSteuerung3 fenster = new JLabelDemoSteuerung3();
	}
}