//Author : Nitchayanin Thamkunanon 6580081
package Ex9_6580081;

import Ex9_6580081.ActorGraph;

import java.util.ArrayList;

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
            String movie = items[0].trim(); //its never used here. but each line = 1 movie

            //connect the nodes together since they are from the same movie
            for(int i=1;i<items.length-1;i++)
            {
                String actorSource  = items[i].trim();
                for(int j=i+1;j<items.length;j++)
                {
                    String actorDest  = items[j].trim();
                    G.setCostarGraph(actorSource,actorDest);
                }
            }

            countLines++;
        }
        System.out.println();
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
