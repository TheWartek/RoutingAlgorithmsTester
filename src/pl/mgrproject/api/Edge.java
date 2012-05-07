package pl.mgrproject.api;

public class Edge<T> {
    
    public int first;
    public int last;
    public T value;
    
    public Edge(int first, int last, T value) {
	this.first = first;
	this.last = last;
	this.value = value;
    }
    
}
