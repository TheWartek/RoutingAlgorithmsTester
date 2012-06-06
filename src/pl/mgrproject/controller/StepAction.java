package pl.mgrproject.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

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
    private int step = 2;

    public StepAction(JList generators, JList algorithms, JTextField start, JTextField stop) {
	this.generators = generators;
	this.algorithms = algorithms;
	this.startVertex = start;
	this.stopVertex = stop;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
	String genStr = (String) generators.getSelectedValue();
	String algStr = (String) algorithms.getSelectedValue();
	final Generator generator = Environment.getPluginManager().getGenerator(genStr);
	final RoutingAlgorithm algorithm = Environment.getPluginManager().getRoutingAlgorithm(algStr);

	if (generator == null) {
	    JOptionPane.showMessageDialog(null, "Nie wybrano generatora grafu!", "B³¹d", JOptionPane.ERROR_MESSAGE);
	    return;
	}

	if (algorithm == null) {
	    JOptionPane.showMessageDialog(null, "Nie wybrano algorytmu routingu!", "B³¹d", JOptionPane.ERROR_MESSAGE);
	    return;
	}
	
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
	do {
	    try {
		algorithm.run(start);
		List<Point> path = algorithm.getPath(stop);
		Environment.setPath(path);
		test = true;
	    } catch (Exception e) {
		generator.generate(step);
		algorithm.setGraph(generator.getGraph());
	    }
	} while(!test);
	
	Environment.drawGraph(g);
	++step;
    }

}
