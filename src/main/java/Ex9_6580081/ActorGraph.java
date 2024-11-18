package Ex9_6580081;

import java.util.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.connectivity.*;
import org.jgrapht.alg.color.*;
import org.jgrapht.alg.spanning.*;

import java.util.Set;

import static org.jgrapht.Graphs.addGraph;

public class ActorGraph {
    public static final String BACON = "Kevin Bacon";
    private Graph<String, DefaultEdge> costarGraph;
    private Graph<String, DefaultEdge> conflictGraph;

    //setter
    public void setCostarGraph(String actorSource, String actorTarget)
    {
        if(costarGraph==null)
        {
            costarGraph = new SimpleGraph<>(DefaultEdge.class);
        }
        costarGraph.addVertex(actorSource);
        costarGraph.addVertex(actorTarget);
        costarGraph.addEdge(actorSource,actorTarget);
    }
    //conflictgraph = costargraph - kevin bacon and edges connected to him
    public void setConflictGraph()
    {
        if(conflictGraph==null)
        {
            conflictGraph = new SimpleGraph<>(DefaultEdge.class);
        }
        addGraph(conflictGraph,costarGraph);
        Set<DefaultEdge> baconEdges = costarGraph.edgesOf(BACON);
        for(DefaultEdge edge : baconEdges)
        {
            conflictGraph.removeEdge(edge);
        }
        conflictGraph.removeVertex(BACON);
    }

    //getter
    public Graph<String,DefaultEdge> getConflictGraph()
    {
        return conflictGraph;
    }

    //methods
    public void baconDegree()
    { /* Find Bacon degree of given actor */

    }
    public void baconParties() { /* Arrange parties for Bacon */ }
    public void printDefaultEdges(Collection<DefaultEdge> E, boolean f)
    {
        for (DefaultEdge e : E)
        {
            System.out.println(e);
        }
    }
    public void printCoStarGraph()
    {
        System.out.println("===CoStarGraph===");
        Set<DefaultEdge> allEdges = costarGraph.edgeSet();
        printDefaultEdges(allEdges, false);
    }
    public void printConflictGraph()
    {
        setConflictGraph();
        System.out.println("===ConflictGraph===");
        Set<DefaultEdge> allEdges = conflictGraph.edgeSet();
        printDefaultEdges(allEdges, false);
    }
}
