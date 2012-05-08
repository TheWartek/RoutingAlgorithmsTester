package pl.mgrproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pl.mgrproject.Environment;

public class StopAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
	Environment.stopTest();
    }

}
