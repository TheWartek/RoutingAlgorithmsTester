package pl.mgrproject.components;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChartPanel extends JPanel {
    
    private static final long serialVersionUID = 1L;

    public ChartPanel() {
	setBorder(BorderFactory.createEtchedBorder());
	add(new JLabel("Chart Panel"));
    }
    
}
