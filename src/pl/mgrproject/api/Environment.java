package pl.mgrproject.api;

import javax.swing.SwingUtilities;

import pl.mgrproject.api.plugins.Generator;
import pl.mgrproject.api.plugins.PluginManager;
import pl.mgrproject.components.ChartPanel;
import pl.mgrproject.components.GraphPanel;

public class Environment {
    private static PluginManager pluginManager;
    private static boolean stop;
    private static GraphPanel graph;
    private static ChartPanel chart;
    private static int nVert = 1;

    public static PluginManager getPluginManager() {
	if (pluginManager == null) {
	    pluginManager = new PluginManager();
	}

	return pluginManager;
    }

    public static void stopTest() {
	stop = true;
    }

    public static void startTest() {
	stop = false;
    }

    public static boolean testIsStopped() {
	return stop;
    }

    public static GraphPanel getGraphPanel() {
	if (graph == null) {
	    graph = new GraphPanel();
	}
	return graph;
    }

    public static ChartPanel getChartPanel() {
	if (chart == null) {
	    chart = new ChartPanel();
	}
	return chart;
    }

    public static void drawGraph(Generator gen, int vertices) {
	nVert = vertices;
	final Graph<?> g = gen.getGraph(vertices);
	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		graph.draw(g);
	    }
	});
    }
    
    public static void drawStep(Generator gen) {
	drawGraph(gen, ++nVert);
    }
}
