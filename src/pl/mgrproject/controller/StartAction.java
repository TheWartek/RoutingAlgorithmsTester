package pl.mgrproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JPanel;

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
	this.graph = (GraphPanel)graph;
	this.chart = (ChartPanel)chart;
	this.generators = generators;
	this.algorithms = algorithms;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
	String genStr = (String)generators.getSelectedValue();
	Generator generator = Environment.getPluginManager().getGenerator(genStr);
	Graph<?> g = generator.getGraph(10);
	//graph.draw(g);
    }

}
