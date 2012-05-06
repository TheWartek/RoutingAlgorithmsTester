package pl.mgrproject.plugins;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class PluginManager {
    private List<Plugin> plugins = new ArrayList<Plugin>();

    public PluginManager() {
	findAndInstantiatePlugins();
    }

    public List<String> getPluginNames() {
	List<String> pluginNames = new ArrayList<String>();
	for (Plugin p : plugins) {
	    pluginNames.add(p.getName());
	}
	return pluginNames;
    }

    private void findAndInstantiatePlugins() {
	// looking for *.jar files
	File pluginFolder = new File("./plugin/");
	File[] listOfFiles = pluginFolder.listFiles(new FileFilter() {
	    @Override
	    public boolean accept(File f) {
		if (f.getName().endsWith(".jar")) {
		    return true;
		}
		return false;
	    }
	});
	// ***//

	// opening JAR files and instantiating plugins
	for (File f : listOfFiles) {
	    plugins.add(getPluginInstance(f));
	}
	// ***//
    }

    private Plugin getPluginInstance(File f) {
	Plugin plugin = null;
	try {
	    JarInputStream jarFile = new JarInputStream(new FileInputStream(f.getAbsolutePath()));
	    URLClassLoader ucl = new URLClassLoader(new URL[] { new URL("jar:file:" + f.getCanonicalPath() + "!/") });
	    while (true) {
		JarEntry jarEntry = jarFile.getNextJarEntry();
		if (jarEntry == null) {
		    break;
		}
		String entryName = jarEntry.getName();
		if (entryName.equals(f.getName().replace(".jar", ".class"))) {
		    try {
			Class<?> c = ucl.loadClass(entryName.substring(0, entryName.length() - 6));
			plugin = (Plugin)c.newInstance();
			break;
		    } catch (Exception e) {
			System.out.println("Nie uda³o siê utworzyæ instancji: " + entryName);
			e.printStackTrace();
		    }
		}
	    }
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	
	return plugin;
    }
}
