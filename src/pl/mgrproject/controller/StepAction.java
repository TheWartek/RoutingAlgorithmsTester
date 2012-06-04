package pl.mgrproject.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.swing.JList;
import javax.swing.JOptionPane;

import pl.mgrproject.api.Environment;
import pl.mgrproject.api.Graph;
import pl.mgrproject.api.plugins.Generator;
import pl.mgrproject.api.plugins.RoutingAlgorithm;
import pl.mgrproject.components.GraphPanel;

public class StepAction implements ActionListener {
    
    private JList generators;
    private JList algorithms;
    
    public StepAction(JList generators, JList algorithms) {
	this.generators = generators;
	this.algorithms = algorithms;
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
	
	Environment.drawStep(generator);
	Graph<?> g = generator.getGraph();
	int n = g.getVertices().size();
	algorithm.setGraph(g);
	Random rand = new Random();
	int start = rand.nextInt(n);
	int stop;
	do {
	    stop = rand.nextInt(n);
	} while (stop == start);
	algorithm.run(start);
	List<Point> path = algorithm.getPath(stop);
	Environment.setPath(path);
	GraphPanel gp = Environment.getGraphPanel();
	gp.draw(g);
    }

}
