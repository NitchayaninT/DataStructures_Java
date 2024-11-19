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
        conflictGraph.removeVertex(BACON);
    }

    //getter
    public Graph<String,DefaultEdge> getCoStarGraph()
    {
        return costarGraph;
    }

    public Graph<String,DefaultEdge> getConflictGraph()
    {
        return conflictGraph;
    }

    public Set<String> getConflictVertices()
    {
        return conflictGraph.vertexSet();
    }
    //methods
    public void baconDegree()
    { /* Find Bacon degree of given actor */

    }
    //apply graph coloring to conflictGraph
    //1.GreedyColoring
    //2.LargestDegreeFirstColoring
    //3.SmallestDegreeLastColoring
    public void baconParties() {
        /* Arrange parties for Bacon */

        for(int i=0;i<3;i++)
        {
            System.out.println("==================== BACON PARTIES ====================");
            switch(i)
            {
                case 0 :
                    greedyAlgo();
                    break;

                case 1:
                    LargestDegreeFirstAlgo();
                    break;

                case 2:
                    SmallestDegreeLastAlgo();
                    break;

            }
            System.out.println();
        }

    }
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
        //setConflictGraph();
        System.out.println("===ConflictGraph===");
        Set<DefaultEdge> allEdges = conflictGraph.edgeSet();
        printDefaultEdges(allEdges, false);
    }

    public void printPartyMembers(Set<String> members)
    {
        TreeSet<String> membersArranged = new TreeSet<>(members);
        int count =0;
        for(String member : membersArranged)
        {
            if(count > 0 && count%6 == 0) System.out.println();
            System.out.printf("%-23s", member);
            count++;

        }
    }

    public void greedyAlgo()
    {
        GreedyColoring<String, DefaultEdge> greedy = new GreedyColoring<>(conflictGraph);

        //group vertices by color
        List< Set<String> > colorList = greedy.getColoring().getColorClasses();
        System.out.println("By GreedyColoring >> total parties = "+colorList.size());

        //System.out.println(colorList);
        for(int i=0; i<colorList.size();i++)
        {
            System.out.println();
            System.out.println("Parties "+ i +" >> guests "+colorList.get(i).size());
            printPartyMembers(colorList.get(i));
            System.out.println();

        }
    }

    public void LargestDegreeFirstAlgo()
    {
        LargestDegreeFirstColoring<String, DefaultEdge> largest = new LargestDegreeFirstColoring<>(conflictGraph);

        //group vertices by color
        List< Set<String> > colorList = largest.getColoring().getColorClasses();
        System.out.println("By LargestDegreeFirstColoring >> total parties = "+colorList.size());

        //System.out.println(colorList);
        for(int i=0; i<colorList.size();i++)
        {
            System.out.println();
            System.out.println("Parties "+ i +" >> guests "+colorList.get(i).size());
            printPartyMembers(colorList.get(i));
            System.out.println();

        }
    }

    public void SmallestDegreeLastAlgo()
    {
        SmallestDegreeLastColoring<String, DefaultEdge> smallest = new SmallestDegreeLastColoring<>(conflictGraph);

        //group vertices by color
        List< Set<String> > colorList = smallest.getColoring().getColorClasses();
        System.out.println("By SmallestDegreeLastColoring >> total parties = "+colorList.size());

        //System.out.println(colorList);
        for(int i=0; i<colorList.size();i++)
        {
            System.out.println();
            System.out.println("Parties "+ i +" >> guests "+colorList.get(i).size());
            printPartyMembers(colorList.get(i));
            System.out.println();

        }
    }
}
