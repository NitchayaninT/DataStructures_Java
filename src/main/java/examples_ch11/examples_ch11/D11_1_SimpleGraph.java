package examples_ch11.examples_ch11;

import java.util.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.connectivity.*;
import org.jgrapht.alg.color.*;
import org.jgrapht.alg.spanning.*;


class D11_1_SimpleGraph 
{
    ////////////////////////////////////////////////////////////////////////////
    protected HashMap<String, Country>    AllCountries;     // real objects
    protected ArrayList<String>           CountryNames;     // graph nodes

    protected Graph<String, DefaultEdge>            G;      // better approach
    //protected SimpleGraph<String, DefaultEdge>    G; 
    
    ////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) 
    {
	D11_1_SimpleGraph mygraph = new D11_1_SimpleGraph();
	System.out.println("Simple graph (no weight) \n");
	mygraph.printGraph();
	//mygraph.testContent();
        //mygraph.checkType();        

        //mygraph.disconnectGraph();
        //mygraph.testWeakConnectivity();          
        //mygraph.testStrongConnectivity();      // error! for undirected graph
        
        //mygraph.testMST();
        mygraph.testColoring();
    }
    
    public D11_1_SimpleGraph()
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

	G = new SimpleGraph<>(DefaultEdge.class);

	Graphs.addAllVertices(G, CountryNames);
	//G.addEdge("china", "china");		// error!
	//G.addEdge("china", "usa");		// error!

	G.addEdge("china", "korea");		G.addEdge("china", "thailand");
	G.addEdge("china", "malaysia");		G.addEdge("korea", "japan");
	G.addEdge("korea", "australia");	G.addEdge("japan", "thailand");
	G.addEdge("japan", "singapore");	G.addEdge("japan", "australia");
	G.addEdge("thailand", "malaysia");	G.addEdge("thailand", "singapore");
	G.addEdge("malaysia", "singapore");	G.addEdge("singapore", "australia");		
    }

    ////////////////////////////////////////////////////////////////////////////
    public Country searchCountry(String name)
    {
	return AllCountries.get(name);
    }

    public void printGraph()
    {		
	Set<DefaultEdge> allEdges = G.edgeSet();
	printDefaultEdges(allEdges, false);
    }

    public void printDefaultEdges(Collection<DefaultEdge> E, boolean f)
    {
	for (DefaultEdge e : E)
	{
            System.out.println(e);    
			
            /*
            // format our own output
            Country source = searchCountry(G.getEdgeSource(e));
            Country target = searchCountry(G.getEdgeTarget(e));
            
            if (f)  // print object content
		System.out.printf("%20s - %20s \n", source, target);

            else    // print only Country name
		System.out.printf("%s - %s \n", source.getName(), target.getName());
            */
	}
    }

    ////////////////////////////////////////////////////////////////////////////
    public void testContent()
    {
	Scanner scan = new Scanner(System.in);
	System.out.println("\nEnter Country name 1 : "); 
	String key1 = scan.next();
	System.out.println("Enter Country name 2 : "); 
	String key2 = scan.next();

        
	if (G.containsEdge(key1, key2))
            System.out.printf("\n%s-%s exists \n", key1, key2);
	else
            System.out.printf("\n%s-%s does not exist \n", key1, key2);

        
        // Exception if key1 doesn't exist
	if (G.containsVertex(key1))
	{
            System.out.printf("\nDegree of %s = %d \n", key1, G.degreeOf(key1));
            Set<DefaultEdge> edges = G.edgesOf(key1);
            printDefaultEdges(edges, false);
            
            System.out.printf("\nIndegree of %s = %d \n", key1, G.inDegreeOf(key1));
            Set<DefaultEdge> inedges = G.incomingEdgesOf(key1);
            printDefaultEdges(inedges, false);      
            
            System.out.printf("\nOutdegree of %s = %d \n", key1, G.outDegreeOf(key1));
            Set<DefaultEdge> outedges = G.outgoingEdgesOf(key1);
            printDefaultEdges(outedges, false);                 
	}
    }

    public void checkType()
    {
        // (1) use Java's operator
        if (G instanceof SimpleGraph) System.out.println("\nInstance of SimpleGraph");
        else                          System.out.println("\nInstance of other");
        
        // (2) use GraphType
        GraphType type = G.getType();
        System.out.print("GraphType = ");
        if (type.isDirected())      System.out.print("directed  ");
        if (type.isSimple())        System.out.print("simple  ");
        if (type.isUndirected())    System.out.print("undirected  ");
        if (type.isWeighted())      System.out.print("weighted  ");
        System.out.println();
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public boolean testWeakConnectivity()
    {
        ConnectivityInspector<String, DefaultEdge> weakConn = new ConnectivityInspector<>(G);
        
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
        GabowStrongConnectivityInspector<String, DefaultEdge> strongConn = 
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
        KruskalMinimumSpanningTree<String, DefaultEdge> MST = new KruskalMinimumSpanningTree<>(G);
        //PrimMinimumSpanningTree<String, DefaultEdge> MST = new PrimMinimumSpanningTree<>(G);
            
        Set<DefaultEdge> treeEdges = MST.getSpanningTree().getEdges();
        System.out.printf("\nTotal MST cost = %.0f \n", MST.getSpanningTree().getWeight());
        printDefaultEdges(treeEdges, false);
    }

    ////////////////////////////////////////////////////////////////////////////
    public void testColoring()
    {
        GreedyColoring<String, DefaultEdge> color;
        
        color = new GreedyColoring<>(G);
        //color = new LargestDegreeFirstColoring<>(G);      // child class of GreedyColoring
        //color = new SmallestDegreeLastColoring<>(G);      // child class of GreedyColoring
        
        System.out.println("\nGroup vertices by colors");
        List< Set<String> > colorList = color.getColoring().getColorClasses();
        System.out.println("All colors = " + colorList);
        for (int i=0; i<colorList.size(); i++)
        {
            System.out.printf("   Color %d = %s \n", i, colorList.get(i));
        }
        
        System.out.println("\nIndividual vertex colors");
        Map<String, Integer> colorMap = color.getColoring().getColors();
        System.out.println(colorMap);
    }        
}