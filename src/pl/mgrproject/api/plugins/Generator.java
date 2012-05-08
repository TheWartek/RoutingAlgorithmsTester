package pl.mgrproject.api.plugins;

import pl.mgrproject.api.Graph;

public interface Generator extends Plugin {
    public Graph<?> getGraph(int nVertices);
}
