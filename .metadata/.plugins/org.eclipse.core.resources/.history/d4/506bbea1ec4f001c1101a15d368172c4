import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
public class TopologicalSort {
	 private static Map<Vertex, List<Vertex>> adjVertices = new HashMap<>();
	 
	class Vertex{
		String label;
	    Vertex(String label) {
	        this.label = label;
	    }
	}
	
	class Graph {
		// private Map<Vertex, List<Vertex>> adjVertices = new HashMap<>();
	    
	    void addVertex(String label) {
	        adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
	    }
	    
	    void addEdge(String label1, String label2) {
	        Vertex v1 = new Vertex(label1);
	        Vertex v2 = new Vertex(label2);
	        System.out.println(adjVertices.get(v1));
	        adjVertices.get(v1).add(v2);
	        adjVertices.get(v2).add(v1);
	    }
	    
	    List<Vertex> getAdjVertices(String label) {
		    return adjVertices.get(new Vertex(label));
		}
		
		Set<String> depthFirstTraversal(Graph graph, String root) {
		    Set<String> visited = new LinkedHashSet<String>();
		    Stack<String> stack = new Stack<String>();
		    stack.push(root);
		    while (!stack.isEmpty()) {
		        String vertex = stack.pop();//System.out.println("Vertex: "+ vertex);
		        if (!visited.contains(vertex)) {
		            visited.add(vertex);
		            for (Vertex v : graph.getAdjVertices(vertex)) {              
		                stack.push(v.label);
		            }
		        }
		    }
		    return visited;
		}
	}

	public static void main(String[] args) {
		TopologicalSort t = new TopologicalSort();
		Graph graph = t.new Graph();
	    graph.addVertex("Bob");
	    graph.addVertex("Alice");
	    graph.addVertex("Mark");
	    graph.addVertex("Rob");
	    graph.addVertex("Maria");
		
		  
	    graph.addEdge("Bob", "Alice"); 
	    graph.addEdge("Bob", "Rob");
		  
		  
	    graph.addEdge("Alice", "Mark");
	    graph.addEdge("Rob", "Mark");
		  
		 
	    graph.addEdge("Alice", "Maria");
	    graph.addEdge("Rob", "Maria");
		 
	    Vertex v = t.new Vertex("Bob");
		System.out.println(adjVertices.get(v));
	
		 
	    
	    System.out.println(graph.depthFirstTraversal(graph, "Bob"));

	}
	
	

}
