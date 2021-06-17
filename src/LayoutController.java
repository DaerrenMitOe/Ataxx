import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class LayoutController extends JFrame implements ActionListener {

    JMenuItem flowLayout;
    JMenuItem borderLayout;
    JMenuItem gridLayout;

    JPanel panel;

    JButton button1;
    JButton button2;
    JButton button3;

    public LayoutController() {
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null); // no default layout
        panel.setSize(600, 600);

        flowLayout = new JMenuItem("Flow Layout");
        flowLayout.addActionListener(this);
        borderLayout = new JMenuItem("Border Layout");
        borderLayout.addActionListener(this);
        gridLayout = new JMenuItem("Grid Layout");
        gridLayout.addActionListener(this);

        // menu to change layout dynamically
        JMenu fileMenu = new JMenu("Layout");
        fileMenu.add(flowLayout);
        fileMenu.add(borderLayout);
        fileMenu.add(gridLayout);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Customize your components
        button1 = new JButton("IMAGE1");
        button2 = new JButton("IMAGE2");
        button3 = new JButton("IMAGE3");

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LayoutController().setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Customize your code here
        if (e.getSource() == flowLayout) {
            //flow layout with right alignment
            panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        } else if (e.getSource() == borderLayout) {
            // border layout with constraints
            BorderLayout layout = new BorderLayout();
            layout.addLayoutComponent(button1, BorderLayout.SOUTH);
            layout.addLayoutComponent(button2, BorderLayout.EAST);
            layout.addLayoutComponent(button3, BorderLayout.CENTER);
            panel.setLayout(layout);
        } else if (e.getSource() == gridLayout) {
            // grid layout with 2 rows and 2 columns
            panel.setLayout(new GridLayout(2, 2));
        }
        panel.revalidate();
    }
}