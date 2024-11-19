//Author : Nitchayanin Thamkunanon 6580081
package Ex9_6580081;

import Ex8_6580081.ActorMap;
import Ex9_6580081.ActorGraph;
import java.util.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class main {
    String path     = "src/main/java/Ex9_6580081/";
    String fileName = "movies.txt";


    ActorGraph G = new ActorGraph(); //create GraphObject
    int movies_count;//all movies count
    public void initialize()
    {
        Ex9_6580081.MyFileReader myreader = new Ex9_6580081.MyFileReader(path, fileName);
        myreader.readFile();
        ArrayList<String> allLines = myreader.getLines();

        int countLines=0;
        movies_count = allLines.size();
        for(String line : allLines)
        {

            String []items  = line.split(";");
            String movie = items[0].trim();

            for(int i=1;i<items.length;i++)
            {
                String actor  = items[i].trim();

                G.setTreeMap(actor, movie);//add to the treemap
            }

            countLines++;
        }
        System.out.println();
        //G.setConflictGraph();

        //use data from treemap to create costarGraph and conflictGraph
        TreeMap<String, TreeSet<String>> M = new TreeMap<>(G.getTreeMap());
        for(String key : M.keySet())
        {
            //find if any other actor has the same movie
            String actorSource = key;
            //compare movie
            for(String movie : M.get(actorSource))
            {
                //movie1
                for(String key2 : M.keySet())
                {
                    if(key.equals(key2)) continue;
                    if(M.get(key2).contains(movie)){
                        String actorDest = key2;
                        if(G.getCoStarGraph().containsEdge(actorSource,actorDest)) continue;
                        else{ G.setCostarGraph(actorSource,actorDest);}
                    }

                }
            }

        }
        G.setConflictGraph();
    }
    //MAIN
    public static void main(String []args)
    {
        main mainProg = new main();
        mainProg.initialize();
        //mainProg.printCoStar();
        //mainProg.printConflict();
        mainProg.printBaconParties();

    }
    public void printCoStar()
    {
        G.printCoStarGraph();
    }
    public void printConflict()
    {
        G.printConflictGraph();
    }
    public void printBaconParties()
    {
        G.baconParties();
    }
}
