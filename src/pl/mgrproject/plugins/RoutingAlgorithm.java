package pl.mgrproject.plugins;

import java.util.List;

import pl.mgrproject.api.Graph;

public interface RoutingAlgorithm extends Plugin {
    public void setGraph(Graph g);
    public List<?> getPath();
}
