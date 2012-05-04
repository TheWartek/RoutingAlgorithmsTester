package pl.mgrproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import pl.mgrproject.components.ChartPanel;
import pl.mgrproject.components.GraphPanel;

public class RoutingAlgorithmsTester extends JFrame {
    
    private static final long serialVersionUID = -9006966197122760691L;
    private JPanel graph = new GraphPanel();
    private JPanel chart = new ChartPanel();
    private JList generatorsList;

    public RoutingAlgorithmsTester() {
	initComponents();
    }
    
    private void initComponents() {
	setLayout(new BorderLayout());
	add(BorderLayout.WEST, leftBar());
	JPanel main = new JPanel();
	main.setLayout(new BorderLayout());
	JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, graph, chart);
	split.setOneTouchExpandable(true);
	split.setDividerLocation(Toolkit.getDefaultToolkit().getScreenSize().height / 2);
	main.add(split);
	add(main);
    }
    
    private JPanel leftBar() {
	JPanel leftBar = new JPanel();
	leftBar.setBorder(BorderFactory.createEtchedBorder());
	
	//graph egenrator choice
	String[] algorithms = new String[] {"Algorytm1", "Algorytm2", "Algorytm3", "Algorytm4", "Algorytm5", "Algorytm6",};
	JList generatorsList = new JList(algorithms);
	generatorsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	JScrollPane scroll = new JScrollPane(generatorsList);
	scroll.setPreferredSize(new Dimension(150, 75));
	JPanel algPanel = new JPanel();
	algPanel.setLayout(new FlowLayout());
	algPanel.setBorder(BorderFactory.createTitledBorder("Generatory grafu"));
	algPanel.add(scroll);
	leftBar.add(algPanel);
	//***//
	
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
