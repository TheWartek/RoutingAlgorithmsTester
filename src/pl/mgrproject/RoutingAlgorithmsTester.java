package pl.mgrproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import pl.mgrproject.components.ChartPanel;
import pl.mgrproject.components.GraphPanel;
import pl.mgrproject.plugins.PluginManager;

public class RoutingAlgorithmsTester extends JFrame {
    
    private static final long serialVersionUID = -9006966197122760691L;
    private JPanel graph = new GraphPanel();
    private JPanel chart = new ChartPanel();
    private JList generatorsList;
    private JList routingAlgorithmsList;
    private PluginManager pm;

    public RoutingAlgorithmsTester() {
	pm = new PluginManager();
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
	leftBar.setLayout(new FlowLayout());
	leftBar.setPreferredSize(new Dimension(180, leftBar.getHeight()));
	leftBar.setBorder(BorderFactory.createEtchedBorder());
	
	//graph generator choice
	List<String> generatorNames = pm.getGeneratorNames();
	String[] generators = generatorNames.toArray(new String[generatorNames.size()]);
	generatorsList = new JList(generators);
	generatorsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	JScrollPane scroll = new JScrollPane(generatorsList);
	scroll.setPreferredSize(new Dimension(150, 75));
	JPanel genPanel = new JPanel();
	genPanel.setLayout(new FlowLayout());
	genPanel.setBorder(BorderFactory.createTitledBorder("Generatory grafu"));
	genPanel.add(scroll);
	leftBar.add(genPanel);
	//***//
	
	//routing algorithm choice
	List<String> routingAlgorithmNames = pm.getRoutingAlgorithmNames();
	String[] routingAlgorithms = routingAlgorithmNames.toArray(new String[routingAlgorithmNames.size()]);
	routingAlgorithmsList = new JList(routingAlgorithms);
	routingAlgorithmsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	scroll = new JScrollPane(routingAlgorithmsList);
	scroll.setPreferredSize(new Dimension(150, 75));
	JPanel algPanel = new JPanel();
	algPanel.setLayout(new FlowLayout());
	algPanel.setBorder(BorderFactory.createTitledBorder("Algorytmy routingu"));
	algPanel.add(scroll);
	leftBar.add(algPanel);
	//***//
	
	//start button//
	JButton start = new JButton("Start");
	leftBar.add(start);
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
