package pl.mgrproject.components;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraphPanel extends JPanel {

    private static final long serialVersionUID = 6502432419743348091L;
    
    public GraphPanel() {
	setBorder(BorderFactory.createEtchedBorder());
	add(new JLabel("Graph Panel"));
    }
}
