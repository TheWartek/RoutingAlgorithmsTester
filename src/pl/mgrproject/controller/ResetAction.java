package pl.mgrproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pl.mgrproject.api.Environment;

public class ResetAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
	Environment.setIterationNumber(2);
	Environment.resetTimes();
	Environment.stopTest();
    }

}
