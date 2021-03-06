package pl.mgrproject.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import pl.mgrproject.api.Environment;
import pl.mgrproject.api.Graph;
import pl.mgrproject.api.plugins.Generator;
import pl.mgrproject.api.plugins.RoutingAlgorithm;

public class StepAction implements ActionListener {

    private JList generators;
    private JList algorithms;
    private JTextField startVertex;
    private JTextField stopVertex;
    private JCheckBox drawGraph;

    public StepAction(JList generators, JList algorithms, JTextField start, JTextField stop, JCheckBox drawGraph) {
	this.generators = generators;
	this.algorithms = algorithms;
	this.startVertex = start;
	this.stopVertex = stop;
	this.drawGraph = drawGraph;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
	String genStr = (String) generators.getSelectedValue();
	String algStr = (String) algorithms.getSelectedValue();
	final Generator generator = Environment.getPluginManager().getGenerator(genStr);
	final RoutingAlgorithm algorithm = Environment.getPluginManager().getRoutingAlgorithm(algStr);

	if (generator == null) {
	    JOptionPane.showMessageDialog(null, "Nie wybrano generatora grafu!", "B��d", JOptionPane.ERROR_MESSAGE);
	    return;
	}

	if (algorithm == null) {
	    JOptionPane.showMessageDialog(null, "Nie wybrano algorytmu routingu!", "B��d", JOptionPane.ERROR_MESSAGE);
	    return;
	}
	
	int step = Environment.getIterationNumber();
	generator.generate(step);
	Graph<?> g = generator.getGraph();
	algorithm.setGraph(g);

	int start = -1;
	int stop = -1;
	int n = g.getVertices().size();
	boolean test = true;
	try {
	    start = Integer.parseInt(startVertex.getText());
	    stop = Integer.parseInt(stopVertex.getText());
	} catch (NumberFormatException e) {
	    test = false;
	}
	
	if (start < 0 || start >= n || stop < 0 || stop >= n ) {
	    test = false;
	}

	if (!test) {
	    Random rand = new Random();
	    start = rand.nextInt(n);
	    do {
		stop = rand.nextInt(n);
	    } while (stop == start);
	}
	
	//jesli graf okaze sie niespojny
	//to zostanie podjeta proba
	//ponownego wygenerowania grafu
	test = false;
	long startTime = 0;
	long stopTime = 0;
	do {
	    try {
		startTime = System.currentTimeMillis();
		algorithm.run(start);
		stopTime = System.currentTimeMillis();
		List<Point> path = algorithm.getPath(stop);
		Environment.setPath(path);
		test = true;
	    } catch (Exception e) {
		generator.generate(step);
		g = generator.getGraph();
		algorithm.setGraph(g);
	    }
	} while(!test);
	
	Environment.addTime(stopTime - startTime);
	if (drawGraph.isSelected()) {
	    Environment.drawGraph(g);
	}
	Environment.drawChart();
	Environment.setIterationNumber(++step);
    }

}
