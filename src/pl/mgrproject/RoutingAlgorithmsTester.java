package pl.mgrproject;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import pl.mgrproject.components.ChartPanel;
import pl.mgrproject.components.GraphPanel;

public class RoutingAlgorithmsTester extends JFrame {
    
    private static final long serialVersionUID = -9006966197122760691L;
    private JPanel graph = new GraphPanel();
    private JPanel chart = new ChartPanel();

    public RoutingAlgorithmsTester() {
	initComponents();
    }
    
    private void initComponents() {
	setLayout(new BorderLayout());
	add(BorderLayout.WEST, leftBar());
	JPanel main = new JPanel();
	main.setLayout(new GridLayout(2, 1));
	main.add(graph);
	main.add(chart);
	add(main);
    }
    
    private JPanel leftBar() {
	JPanel leftBar = new JPanel();
	leftBar.add(new JLabel("Left Bar"));
	leftBar.setBorder(BorderFactory.createEtchedBorder());
	return leftBar;
    }
    
    public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		JFrame f = new RoutingAlgorithmsTester();
		f.setTitle("Tester algorytmów szukania trasy");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
	    }
	});
    }

}
