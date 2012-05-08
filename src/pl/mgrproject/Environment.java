package pl.mgrproject;

import pl.mgrproject.plugins.PluginManager;

public class Environment {
    private static PluginManager pluginManager;
    private static boolean stop;
    
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
}
