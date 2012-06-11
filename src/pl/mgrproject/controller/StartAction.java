package pl.mgrproject.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import pl.mgrproject.api.Environment;
import pl.mgrproject.api.Graph;
import pl.mgrproject.api.plugins.Generator;
import pl.mgrproject.api.plugins.RoutingAlgorithm;

public class StartAction implements ActionListener {

    private JList generators;
    private JList algorithms;
    private JTextField it;
    private JCheckBox drawGraph;

    public StartAction(JList generators, JList algorithms, JTextField iterations, JCheckBox drawGraph) {
	this.generators = generators;
	this.algorithms = algorithms;
	it = iterations;
	this.drawGraph = drawGraph;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
	if (!Environment.testIsStopped() && !Environment.testIsPaused()) {
	    return;
	}
	if (!Environment.testIsPaused()) {
	    Environment.resetTimes();
	} else {
	    Environment.resumeTest();
	}
	Environment.startTest();
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

	ExecutorService exec = Executors.newFixedThreadPool(1);
	exec.execute(new Runnable() {
	    @Override
	    public void run() {
		int iter = 0;
		int itStart = Environment.getIterationNumber();
		try {
		    iter = Integer.parseInt(it.getText());
		} catch(NumberFormatException e) {
		    iter = -1;
		}
		for (Environment.i = itStart; iter <= 0 ? true : (Environment.i < iter); ++Environment.i) {
		    if (Environment.testIsStopped()) {
			break;
		    }
		    if (Environment.testIsPaused()) {
			Environment.setIterationNumber(Environment.i);
			break;
		    }
		    generator.generate(Environment.i);
		    Graph<?> g = generator.getGraph();
		    algorithm.setGraph(g);

		    int start = -1;
		    int stop = -1;
		    int n = g.getVertices().size();

		    Random rand = new Random();
		    start = rand.nextInt(n);
		    do {
			stop = rand.nextInt(n);
		    } while (stop == start);

		    // jesli graf okaze sie niespojny
		    // to zostanie podjeta proba
		    // ponownego wygenerowania grafu
		    long startTime = 0;
		    long stopTime = 0;
		    boolean test = false;
		    do {
			try {
			    startTime = System.currentTimeMillis();
			    algorithm.run(start);
			    stopTime = System.currentTimeMillis();
			    List<Point> path = algorithm.getPath(stop);
			    Environment.setPath(path);
			    test = true;
			} catch (Exception e) {
			    generator.generate(Environment.i);
			    g = generator.getGraph();
			    algorithm.setGraph(g);
			}
		    } while (!test);

		    Environment.addTime(stopTime - startTime);
		    if (drawGraph.isSelected()) {
			    Environment.drawGraph(g);
			}
		    Environment.drawChart();
		}
	    }
	});
	exec.shutdown();
    }

}
