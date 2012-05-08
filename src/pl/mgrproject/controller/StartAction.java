package pl.mgrproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import pl.mgrproject.Environment;
import pl.mgrproject.api.Graph;
import pl.mgrproject.components.ChartPanel;
import pl.mgrproject.components.GraphPanel;
import pl.mgrproject.plugins.Generator;

public class StartAction implements ActionListener {

    private GraphPanel graph;
    private ChartPanel chart;
    private JList generators;
    private JList algorithms;

    public StartAction(JPanel graph, JPanel chart, JList generators, JList algorithms) {
	this.graph = (GraphPanel) graph;
	this.chart = (ChartPanel) chart;
	this.generators = generators;
	this.algorithms = algorithms;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
	Environment.startTest();
	String genStr = (String) generators.getSelectedValue();
	final Generator generator = Environment.getPluginManager().getGenerator(genStr);

	ExecutorService exec = Executors.newFixedThreadPool(1);
	exec.execute(new Runnable() {
	    @Override
	    public void run() {
		for (int i = 1; i < 100; ++i) {
		    if (Environment.testIsStopped()) {
			break;
		    }
		    final Graph<?> g = generator.getGraph(i);
		    SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
			    graph.draw(g);
			}
		    });
		    try {
			TimeUnit.MILLISECONDS.sleep(100);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
	    }
	});
	exec.shutdown();
    }

}
