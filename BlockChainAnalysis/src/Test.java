
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

class Graph<T> {

	// We use Hashmap to store the edges in the graph
	private Map<T, List<T> > map = new HashMap<>();
	// This function adds a new vertex to the graph
	public void addVertex(T s)
	{
		map.put(s, new LinkedList<T>());
	}

	// This function adds the edge
	// between source to destination
	public void addEdge(T source, T destination)
	{

		if (!map.containsKey(source))
			addVertex(source);

		if (!map.containsKey(destination))
			addVertex(destination);

		map.get(source).add(destination);
	}

	// This function gives whether
	// a vertex is present or not.
	public void hasVertex(T s)
	{
		if (map.containsKey(s)) {
			System.out.println("The graph contains "
							+ s + " as a vertex.");
		}
		else {
			System.out.println("The graph does not contain "
							+ s + " as a vertex.");
		}
	}

	   public int getVertexCount()
	    {
	        System.out.println("The graph has "
	                           + map.keySet().size()
	                           + " vertex");
	        return map.keySet().size();
	    }
	   
	// This function gives whether an edge is present or not.
	public void hasEdge(T s, T d)
	{
		if (map.get(s).contains(d)) {
			System.out.println("The graph has an edge between "
							+ s + " and " + d + ".");
		}
		else {
			System.out.println("The graph has no edge between "
							+ s + " and " + d + ".");
		}
	}
	
	
	// Prints the adjancency list of each vertex.
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		for (T v : map.keySet()) {
			builder.append(v.toString() + ": ");
			for (T w : map.get(v)) {
				builder.append(w.toString() + " ");
			}
			builder.append("\n");
		}

		return (builder.toString());
	}
}

// Driver Code
public class Test {

	public static void main(String args[]) throws FileNotFoundException, IOException, ParseException
	{

		//C:\Users\rshagan\Documents\DS
		long count = 0;
		FileInputStream inputStream = null;
		Scanner scanner = new Scanner(System.in);
		Graph<String> g = new Graph<String>();
		System.out.println("Enter File Name: ");
		String fileName = scanner.next();
		File file = new File("C:\\Users\\rshagan\\Documents\\DS\\"+fileName);
		System.out.println("Enter range of data to be simulated within a file: ");
		long size = scanner.nextLong();
		try {
	    	
	    	inputStream = new FileInputStream(file);
	    	scanner = new Scanner(inputStream, "UTF-8");
	    	scanner.nextLine();
	    	while(count < size) {
	    		String line = scanner.nextLine();
	    		String[] values = line.split(",");
	    		g.addEdge(values[1], values[2]);
	    		count++;
				 
	        }
	    }
	    catch (OutOfMemoryError oome) {
            //Log the info
            System.err.println("Array size too large");
            System.err.println("Max JVM memory: " + Runtime.getRuntime().maxMemory());
        }
	    catch(Exception e) {
	    	System.out.println(e);
	    }
	    
	    finally {
	    	if(inputStream != null)
	    		inputStream.close();
	    	if(scanner != null)
	    		scanner.close();
	    }
	    System.out.println("Read file of "+size+" size and name is " + file);
		

		// Printing the graph
		System.out.println("Graph:\n" + g.toString());

		g.getVertexCount();
		//g.hasEdge(3, 4);
		//g.hasVertex(4);
	}
}
