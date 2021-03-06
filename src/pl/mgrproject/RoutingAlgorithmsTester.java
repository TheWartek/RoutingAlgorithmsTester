package pl.mgrproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import pl.mgrproject.api.Environment;
import pl.mgrproject.controller.PauseAction;
import pl.mgrproject.controller.ResetAction;
import pl.mgrproject.controller.StartAction;
import pl.mgrproject.controller.StepAction;
import pl.mgrproject.controller.StopAction;

public class RoutingAlgorithmsTester extends JFrame {
    
    private static final long serialVersionUID = -9006966197122760691L;
    private JList generatorsList;
    private JList routingAlgorithmsList;

    public RoutingAlgorithmsTester() {
	initComponents();
    }
    
    private void initComponents() {
	setLayout(new BorderLayout());
	add(BorderLayout.WEST, leftBar());
	JPanel main = new JPanel();
	main.setLayout(new BorderLayout());
	JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, Environment.getGraphPanel(), Environment.getChartPanel());
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
	List<String> generatorNames = Environment.getPluginManager().getGeneratorNames();
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
	JPanel algPanel = new JPanel();
	List<String> routingAlgorithmNames = Environment.getPluginManager().getRoutingAlgorithmNames();
	String[] routingAlgorithms = routingAlgorithmNames.toArray(new String[routingAlgorithmNames.size()]);
	routingAlgorithmsList = new JList(routingAlgorithms);
	routingAlgorithmsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	scroll = new JScrollPane(routingAlgorithmsList);
	scroll.setPreferredSize(new Dimension(150, 75));
	
	algPanel.setLayout(new FlowLayout());
	algPanel.setBorder(BorderFactory.createTitledBorder("Algorytmy routingu"));
	algPanel.add(scroll);
	leftBar.add(algPanel);
	//***//
	
	JTextField startVertex = new JTextField("-", 3);
	JTextField stopVertex  = new JTextField("-", 3);
	JTextField iteracje    = new JTextField("-", 9);
	JCheckBox drawGraph    = new JCheckBox();
	leftBar.add(new JLabel("Start:"));
	leftBar.add(startVertex);
	leftBar.add(new JLabel("Stop:"));
	leftBar.add(stopVertex);
	leftBar.add(new JLabel("Iteracje:"));
	leftBar.add(iteracje);
	leftBar.add(new JLabel("Rysuj graf:"));
	leftBar.add(drawGraph);
	
	//start button//
	JButton start = new JButton("Start");
	start.addActionListener(new StartAction(generatorsList, routingAlgorithmsList, iteracje, drawGraph));
	leftBar.add(start);
	//***//
	
	//stop button//
	JButton stop = new JButton("Stop");
	stop.addActionListener(new StopAction());
	leftBar.add(stop);
	//***//
	
	//step button//
	JButton step = new JButton("Krok");
	step.addActionListener(new StepAction(generatorsList, routingAlgorithmsList, startVertex, stopVertex, drawGraph));
	leftBar.add(step);
	//***//
	
	//pause button//
	JButton pause = new JButton("Pauza");
	pause.addActionListener(new PauseAction());
	leftBar.add(pause);
	//***//
	
	//reset button//
	JButton reset = new JButton("Reset");
	reset.addActionListener(new ResetAction());
	leftBar.add(reset);
	//***//
	
	return leftBar;
    }
    
    public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		JFrame f = new RoutingAlgorithmsTester();
		f.setTitle("Tester algorytm�w szukania trasy");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setMinimumSize(new Dimension(800, 600));
	    }
	});
    }

}
