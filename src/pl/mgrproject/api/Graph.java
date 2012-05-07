package pl.mgrproject.api;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Graph<T> {
    private List<Point> vertices = new ArrayList<Point>();
    private List<Edge<T>> edges = new ArrayList<Edge<T>>();

    public List<Point> getVertices() {
	return vertices;
    }

    public List<Edge<T>> getEdges() {
	return edges;
    }

    public void addVertex(Point p) {
	vertices.add(p);
    }
    
    public void addEdge(Edge<T> e) {
	edges.add(e);
    }
}
