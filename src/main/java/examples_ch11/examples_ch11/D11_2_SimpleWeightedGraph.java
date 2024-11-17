package examples_ch11.examples_ch11;

import java.util.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.connectivity.*;
import org.jgrapht.alg.spanning.*;


class D11_2_SimpleWeightedGraph 
{
    ////////////////////////////////////////////////////////////////////////////
    protected HashMap<String, Country>  AllCountries;           // real objects
    protected ArrayList<String>         CountryNames;           // graph nodes

    protected Graph<String, DefaultWeightedEdge>                       G;
    //protected SimpleWeightedGraph<String, DefaultWeightedEdge>       G;

    ////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) 
    {
	D11_2_SimpleWeightedGraph mygraph = new D11_2_SimpleWeightedGraph();
	System.out.println("Simple weighted graph \n");
	mygraph.printGraph();
        //mygraph.checkType();
	//mygraph.testContent();

        //mygraph.disconnectGraph();        
        //mygraph.testWeakConnectivity();
        //mygraph.testStrongConnectivity();      // error! for undirected graph        
        
	//mygraph.testMST();
    }
    
    public D11_2_SimpleWeightedGraph()
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

	G = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

	//Graphs.addEdgeWithVertices(G, "china", "china", 87);	         // error!

	Graphs.addEdgeWithVertices(G, "china", "korea", 87);
	Graphs.addEdgeWithVertices(G, "china", "thailand",  255);
	Graphs.addEdgeWithVertices(G, "china", "malaysia",  335);
	Graphs.addEdgeWithVertices(G, "korea", "japan",     101);
	Graphs.addEdgeWithVertices(G, "korea", "australia", 649);
	Graphs.addEdgeWithVertices(G, "japan", "thailand",  357);
	Graphs.addEdgeWithVertices(G, "japan", "singapore", 411);
	Graphs.addEdgeWithVertices(G, "japan", "australia", 613);
	Graphs.addEdgeWithVertices(G, "thailand", "malaysia",   104);
	Graphs.addEdgeWithVertices(G, "thailand", "singapore",  117);
	Graphs.addEdgeWithVertices(G, "malaysia", "singapore",  28);
	Graphs.addEdgeWithVertices(G, "singapore", "australia", 482);
    }

    ////////////////////////////////////////////////////////////////////////////
    public Country searchCountry(String name)
    {
	return AllCountries.get(name);
    }

    public void printGraph()
    {
	Set<DefaultWeightedEdge> allEdges = G.edgeSet();
	printDefaultWeightedEdges(allEdges, false);
    }

    public void printDefaultWeightedEdges(Collection<DefaultWeightedEdge> E, boolean f)
    {
	for (DefaultWeightedEdge e : E)
        {
            //System.out.println(e);
	        
            // format our own output
            Country source = searchCountry(G.getEdgeSource(e));
            Country target = searchCountry(G.getEdgeTarget(e));
            double weight = G.getEdgeWeight(e);            
            
            if (f)  // print object content and weight
                System.out.printf("%20s - %20s   weight = %4.0f \n", 
                                  source, target, weight);

            else    // print only Country name and weight
		System.out.printf("%10s - %-10s   weight = %4.0f \n", 
                                  source.getName(), target.getName(), weight);                                  
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    public void checkType()
    {
        // (1) use Java's operator 
        //     - difficult to get exact type because both are true
        if (G instanceof SimpleGraph)           System.out.println("\nInstance of SimpleGraph");
        if (G instanceof SimpleWeightedGraph)   System.out.println("\nInstance of SimpleWeightedGraph");
        else                                    System.out.println("\nInstance of other");
        
        // (2) use GraphType
        GraphType type = G.getType();
        System.out.print("GraphType = ");
        if (type.isDirected())      System.out.print("directed  ");
        if (type.isSimple())        System.out.print("simple  ");
        if (type.isUndirected())    System.out.print("undirected  ");
        if (type.isWeighted())      System.out.print("weighted  ");
        System.out.println();
    }    
    
    public void testContent()
    {
	Scanner scan = new Scanner(System.in);
	System.out.println("\nEnter Country name 1 : "); 
	String key1 = scan.next();
	System.out.println("Enter Country name 2 : "); 
	String key2 = scan.next();

        
	if (G.containsEdge(key1, key2))
	{
            DefaultWeightedEdge edge = G.getEdge(key1, key2);
            System.out.printf("\nWeight of %s-%s = %.0f \n\n", 
			      key1, key2, G.getEdgeWeight(edge));
	}
	else
            System.out.printf("\n%s-%s does not exist \n\n", key1, key2);

        
        // Exception of key1 doesn't exist
	if (G.containsVertex(key1))
	{
            List<String> neighbors = Graphs.neighborListOf(G, key1);
            System.out.printf("Neighbors of %s = %s \n", key1, neighbors);
	}
    }

    ////////////////////////////////////////////////////////////////////////////
    public boolean testWeakConnectivity()
    {
        ConnectivityInspector<String, DefaultWeightedEdge> weakConn = new ConnectivityInspector<>(G);
        
        if (weakConn.isConnected()) System.out.println("\nWeakly connected");
        else                        
        {
            System.out.println("\nNot weakly connected");
            List< Set<String> > subgraphList = weakConn.connectedSets();
            System.out.println("All subgraphs = " + subgraphList);            
            for(int i=0; i<subgraphList.size(); i++)
            {
                System.out.printf("   Subgraph %d = %s \n", i, subgraphList.get(i));
            }
        }
        
        return weakConn.isConnected();
    }

    public boolean testStrongConnectivity()
    {
        GabowStrongConnectivityInspector<String, DefaultWeightedEdge> strongConn = 
                new GabowStrongConnectivityInspector<>(G);
        
        if (strongConn.isStronglyConnected()) System.out.println("\nStrongly connected");
        else                                  System.out.println("\nNot strongly connected");
        
        return strongConn.isStronglyConnected();
    }            
    
    public void disconnectGraph()
    {
        G.removeEdge("china", "korea");
        G.removeEdge("japan", "thailand");
        G.removeEdge("thailand", "singapore");
        G.removeEdge("malaysia", "singapore");
    }
    
    public void testMST()
    {
        KruskalMinimumSpanningTree<String, DefaultWeightedEdge> MST = new KruskalMinimumSpanningTree<>(G);
        //PrimMinimumSpanningTree<String, DefaultWeightedEdge> MST = new PrimMinimumSpanningTree<>(G);
            
        Set<DefaultWeightedEdge> treeEdges = MST.getSpanningTree().getEdges();
        System.out.printf("\nTotal MST cost = %.0f \n", MST.getSpanningTree().getWeight());
        printDefaultWeightedEdges(treeEdges, false);
    }
}
