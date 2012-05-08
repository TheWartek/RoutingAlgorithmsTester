package pl.mgrproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JList;
import javax.swing.JOptionPane;

import pl.mgrproject.Environment;
import pl.mgrproject.api.plugins.Generator;

public class StartAction implements ActionListener {

    private JList generators;
    private JList algorithms;

    public StartAction(JList generators, JList algorithms) {
	this.generators = generators;
	this.algorithms = algorithms;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
	Environment.startTest();
	String genStr = (String) generators.getSelectedValue();
	final Generator generator = Environment.getPluginManager().getGenerator(genStr);
	
	if (generator == null) {
	    JOptionPane.showMessageDialog(null, "Nie wybrano generatora grafu!", "B³¹d", JOptionPane.ERROR_MESSAGE);
	    return;
	}
	
	ExecutorService exec = Executors.newFixedThreadPool(1);
	exec.execute(new Runnable() {
	    @Override
	    public void run() {
		for (int i = 1; i < 2; ++i) {
		    if (Environment.testIsStopped()) {
			break;
		    }
		    Environment.drawGraph(generator, i);
		}
	    }
	});
	exec.shutdown();
    }

}
