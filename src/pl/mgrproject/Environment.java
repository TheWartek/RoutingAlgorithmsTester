package pl.mgrproject;

import pl.mgrproject.plugins.PluginManager;

public class Environment {
    private static PluginManager pluginManager;
    
    public static PluginManager getPluginManager() {
	if (pluginManager == null) {
	    pluginManager = new PluginManager();
	}
	
	return pluginManager;
    }
}
