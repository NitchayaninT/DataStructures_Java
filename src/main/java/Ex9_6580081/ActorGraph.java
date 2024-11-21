//Author : Nitchayanin Thamkunanon 6580081
package Ex9_6580081;

import java.util.*;
import org.jgrapht.*;
import org.jgrapht.alg.shortestpath.BFSShortestPath;
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
    private LinkedHashMap<String, LinkedHashSet<String>> workingMap;

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
    //use Map to keep data from input file
    public void setTreeMap(String actor, String movie)
    {
        LinkedHashSet <String> movies = workingMap.get(actor);
        if(movies==null)
        {
            movies = new LinkedHashSet<>();
            workingMap.put(actor,movies);
        }
        movies.add(movie);
    }
    //getter
    public Graph<String,DefaultEdge> getCoStarGraph() {return costarGraph;}
    public Graph<String,DefaultEdge> getConflictGraph() {return conflictGraph;}
    public Set<String> getConflictVertices() {return conflictGraph.vertexSet();}
    public LinkedHashMap getTreeMap()
    {
        return workingMap;
    }
    public int getCoStarSize(){return costarGraph.vertexSet().size();}
    public int getConflictSize(){return conflictGraph.vertexSet().size();}
    //constructor
    public ActorGraph()
    {
        workingMap = new LinkedHashMap();
        costarGraph = new SimpleGraph<>(DefaultEdge.class);
        conflictGraph = new SimpleGraph<>(DefaultEdge.class);
    }
    //methods
    public void baconDegree()
    { /* Find Bacon degree of given actor */
        String actor = new String();
        while(true) {
            System.out.println("==================== Bacon degrees ====================");
            System.out.println("Enter name or username, or 0 to quit");
            Scanner sc = new Scanner(System.in);
            actor = sc.nextLine();
            if(actor.equals("0")) break;
            else if(actor.isEmpty())
            {
                System.out.println("You haven't inserted any actor's name, please insert!");
                continue;
            }
            Set<String> total_actors = workingMap.keySet(); //get all actors
            Set<String> chosen_actors = new TreeSet<>();
            for (String each_actor : total_actors) {
                if (each_actor.toLowerCase().contains(actor)) {
                    chosen_actors.add(each_actor);
                }
            }

            System.out.println("Valid actors = " + chosen_actors);

            //find shortest path to costarGraph to find bacon degree of THAT actor
            //unweighted, use BFS
            BFSShortestPath<String, DefaultEdge> BFS = new BFSShortestPath<>(costarGraph);
            for (String eachActor : chosen_actors) {
                GraphPath<String, DefaultEdge> pathToBacon = BFS.getPath(eachActor, BACON);
                System.out.println(eachActor + " >> " + "Bacon degree = " + pathToBacon.getLength());

                List<DefaultEdge> list = pathToBacon.getEdgeList();
                String currentActor = eachActor;
                String source, target;
                // Print the edges in the expected order (source -> target)
                for (DefaultEdge eachEdge : list) {

                    if(currentActor.equals(costarGraph.getEdgeSource(eachEdge)))
                    {
                        source = costarGraph.getEdgeSource(eachEdge);
                        target = costarGraph.getEdgeTarget(eachEdge);

                        currentActor = target;
                    }
                    else{
                        target = costarGraph.getEdgeSource(eachEdge);
                        source = costarGraph.getEdgeTarget(eachEdge);

                        currentActor = target;
                    }

                    LinkedHashSet<String> movies = workingMap.get(source); //get list of movies of the source actor
                    movies.retainAll(workingMap.get(target)); //get mutual movie

                    System.out.printf("%20s - %-20s (%s)",source,target, movies.getFirst());
                    System.out.println();
                }
                System.out.println();
            }


        }


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
