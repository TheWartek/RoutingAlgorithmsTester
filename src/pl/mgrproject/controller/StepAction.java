package pl.mgrproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JOptionPane;

import pl.mgrproject.Environment;
import pl.mgrproject.api.plugins.Generator;

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
	final Generator generator = Environment.getPluginManager().getGenerator(genStr);
	
	if (generator == null) {
	    JOptionPane.showMessageDialog(null, "Nie wybrano generatora grafu!", "B³¹d", JOptionPane.ERROR_MESSAGE);
	    return;
	}
	
	Environment.drawStep(generator);
    }

}
