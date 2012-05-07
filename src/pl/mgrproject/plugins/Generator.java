package pl.mgrproject.plugins;

import pl.mgrproject.api.Graph;

public interface Generator extends Plugin {
    public Graph<?> getGraph();
}
