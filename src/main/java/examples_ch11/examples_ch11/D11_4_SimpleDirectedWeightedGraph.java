package examples_ch11.examples_ch11;

import java.util.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.shortestpath.*;
import org.jgrapht.alg.interfaces.*;


class D11_4_SimpleDirectedWeightedGraph extends D11_2_SimpleWeightedGraph
{
    // Use members inherited from D10_2_SimpleGraph
    //    Variables : AllCountries, CountryNames, G, conn
    //    Methods : searchCountry, printGraph, printDefaultEdges, 
    //              checkType, testContent, 
    //              testWeakConnectivity, testStrongConnectivity

    ////////////////////////////////////////////////////////////////////////////
    //protected SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>  G;
    
    ////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) 
    {
	D11_4_SimpleDirectedWeightedGraph mygraph = new D11_4_SimpleDirectedWeightedGraph();
	System.out.println("Directed weighted graph \n");
	mygraph.printGraph();
        //mygraph.checkType();                  // parent's method
        //mygraph.testContent();                // parent's method
        
        //mygraph.testWeakConnectivity();       // parent's method
        //mygraph.testStrongConnectivity();     // parent's method
        
	//mygraph.testShortestPath();
        //mygraph.testShortestPathNegative();
    }
    
    public D11_4_SimpleDirectedWeightedGraph()
    {
        // real Country objects
	AllCountries = new HashMap<String, Country>();
	AllCountries.put("china", new Country("China", 2.44));
	AllCountries.put("korea", new Country("Korea", 3.19));
	AllCountries.put("japan", new Country("Japan", 4.16));
	AllCountries.put("thailand",  new Country("Thailand",  2.46));
	AllCountries.put("malaysia",  new Country("Malaysia",  2.34));
	AllCountries.put("singapore", new Country("Singapore", 3.75));
	AllCountries.put("australia", new Country("Australia", 4.20));

	// graph nodes are only country names
	CountryNames = new ArrayList<String>();
	CountryNames.add("china");	CountryNames.add("korea"); 
	CountryNames.add("japan");	CountryNames.add("thailand");
	CountryNames.add("malaysia");	CountryNames.add("singapore");
	CountryNames.add("australia");

	G = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

	Graphs.addEdgeWithVertices(G, "china", "korea", 87);
	Graphs.addEdgeWithVertices(G, "china", "thailand",  255);
	Graphs.addEdgeWithVertices(G, "china", "malaysia",  335);
	Graphs.addEdgeWithVertices(G, "korea", "japan",     101);
	Graphs.addEdgeWithVertices(G, "korea", "australia", 649);
        //Graphs.addEdgeWithVertices(G, "korea", "australia", 6490);         // try BFS & Dijkstra
	Graphs.addEdgeWithVertices(G, "japan", "australia", 613);
	Graphs.addEdgeWithVertices(G, "thailand", "japan",  357);
	Graphs.addEdgeWithVertices(G, "thailand", "singapore",  117);
	Graphs.addEdgeWithVertices(G, "malaysia", "thailand",   104);
	Graphs.addEdgeWithVertices(G, "malaysia", "singapore",  28);
	Graphs.addEdgeWithVertices(G, "singapore", "japan",     411);
        Graphs.addEdgeWithVertices(G, "singapore", "australia", 482);
	//Graphs.addEdgeWithVertices(G, "singapore", "australia", -482);     // negative edge
        //Graphs.addEdgeWithVertices(G, "australia", "singapore", -4820);    // negative cycle
    }

    ////////////////////////////////////////////////////////////////////////////
    public void testShortestPath()
    {
	Scanner scan = new Scanner(System.in);
	System.out.println("\nEnter Country name 1 (source) : "); 
	String key1 = scan.next();
	System.out.println("Enter Country name 2 (target) : "); 
	String key2 = scan.next();

	// source and target must exist, otherwise error
	if (G.containsVertex(key1) && G.containsVertex(key2))
	{
            try
            {
                ShortestPathAlgorithm<String, DefaultWeightedEdge> shpath;
                
                //shpath = new BFSShortestPath<>(G);         // wrong result for weighted graph      
                shpath = new DijkstraShortestPath<>(G);
            
                // getPath throws exception if negative edge exists
                GraphPath<String, DefaultWeightedEdge> gpath = shpath.getPath(key1, key2);
                if (gpath != null)            
                    printGraphPath(gpath);
                else
                    System.out.printf("\nPath from %s to %s doesn't exist\n", key1, key2);
            }
            catch(Exception e)
            {
                System.out.println(e);
                System.out.println("Use Bellman-Ford instead");
            }
	}
    }
    
    public void testShortestPathNegative()
    {
	Scanner scan = new Scanner(System.in);
	System.out.println("\nEnter Country name 1 (source) : "); 
	String key1 = scan.next();
	System.out.println("Enter Country name 2 (target) : "); 
	String key2 = scan.next();

	// source and target must exist, otherwise error
	if (G.containsVertex(key1) && G.containsVertex(key2))
	{            
            try 
            {
                ShortestPathAlgorithm<String, DefaultWeightedEdge> shpath = new BellmanFordShortestPath<>(G);
                
                // getPath throws exceptions if negative cycle exists
                GraphPath<String, DefaultWeightedEdge> gpath = shpath.getPath(key1, key2);
                if (gpath != null)
                    printGraphPath(gpath);
                else
                    System.out.printf("\nPath from %s to %s doesn't exist\n", key1, key2);            
            }
            catch(Exception e) 
            { 
                System.out.println(e); 
                System.out.println("No solution");
            }
	}
    } 

    public void printGraphPath(GraphPath<String, DefaultWeightedEdge> gpath)
    {
        System.out.printf("\nTotal edges = %d, total weight = %.2f \n", 
                          gpath.getLength(), gpath.getWeight());
                
        System.out.println("\nEdge list");
        List<DefaultWeightedEdge> allEdges = gpath.getEdgeList();
        printDefaultWeightedEdges(allEdges, false);
                
        List<String> allNodes = gpath.getVertexList();    
        System.out.print("\nNode list = " + allNodes);      
    }
}
