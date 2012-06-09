package pl.mgrproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pl.mgrproject.api.Environment;

public class PauseAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
	Environment.pauseTest();
    }

}
