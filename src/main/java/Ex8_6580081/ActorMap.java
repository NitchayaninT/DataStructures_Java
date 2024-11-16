//author : Nitchayanin Thamkunanon 6580081
package Ex8_6580081;

import java.util.*;
public class ActorMap {
    // Key = actor, Value = set of movies
    // Java's String already has consistent equals & compareTo for equality check
    private TreeMap<String, LinkedHashSet<String>> workingMap;
    private LinkedHashSet<String> resultSet; //set of movies based on given actor
    private LinkedHashMap<String, LinkedHashSet<String>> workingMap2;//input map
    
    //setter
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
    public void setLinkedHashMap(String actor, String movie) {
        
        LinkedHashSet<String> movies = workingMap2.get(actor);
        if (movies == null) {
            movies = new LinkedHashSet<>();
            workingMap2.put(actor, movies);
        }
        movies.add(movie);
    }
    public void insertTreeMap( TreeMap<String, LinkedHashSet<String>> all)
    {
        this.workingMap = all;
    }
    public void insertHashMap(LinkedHashMap<String, LinkedHashSet<String>> all)
    {
        this.workingMap2 = all;
    }
    //getter
    public TreeMap getTreeMap()
    {
        return workingMap;
    }
    public LinkedHashMap getLinkedHashMap()
    {
        return workingMap2;
    }
    //constructor
    public ActorMap()
    {
        workingMap = new TreeMap();
        workingMap2 = new LinkedHashMap();
        resultSet = new LinkedHashSet();
    }
    //methods
    public void initialActors() 
    { 
    /* Initialize resultSet to contain movies of all given actors */
        if (workingMap2.isEmpty()) {
            System.out.println("Please insert actors");
            return;
        }
        Set< Map.Entry<String, LinkedHashSet<String>> > allEntries = workingMap2.entrySet();
        resultSet = new LinkedHashSet< >(allEntries.iterator().next().getValue()); //set1 (movies of first actor1)
        int countEntry = 0;
        for(Map.Entry<String,LinkedHashSet<String>> entry : allEntries)//run through each entry and intersect the movies
        {
            if(countEntry != 0)
            {
                resultSet.addAll(entry.getValue());
            }
            countEntry++;
        }

        System.out.printf("Result = ");
        System.out.println(resultSet);
        
        System.out.printf("Total movies = %d\n",resultSet.size());
        
    }

    public void containActors()
    { 
    /* Find movies in resultSet containing given actors */
        if (workingMap2.isEmpty() || resultSet.isEmpty()) {
            System.out.println("Please insert actors");
            return;
        }

        Set< Map.Entry<String, LinkedHashSet<String>> > allEntries = workingMap2.entrySet();
                
        LinkedHashSet<String> intersect = new LinkedHashSet<>(allEntries.iterator().next().getValue());//set1 (movies of first actor1)

        int countEntry = 0;
        for(Map.Entry<String,LinkedHashSet<String>> entry : allEntries)//run through each entry and intersect the movies
        {
            if(countEntry != 0)
            {
                intersect.retainAll(entry.getValue());
            }
            countEntry++;
        }
         //result is in intersect
        resultSet.retainAll(intersect);
        System.out.printf("Result = ");
        System.out.println(resultSet);
        
        System.out.printf("Total movies = %d\n",resultSet.size());
    }
 
    public void withoutActors()
    {
    /* Find movies in resultSet without given actors */
        if (workingMap2.isEmpty() || resultSet.isEmpty()) {
            System.out.println("Please insert actors");
            return;
        }
        Set< Map.Entry<String, LinkedHashSet<String>> > allEntries = workingMap2.entrySet();
 
        LinkedHashSet<String> MoviesToExclude = new LinkedHashSet<>();//set1 (movies of first actor1)
        
        for(Map.Entry<String,LinkedHashSet<String>> entry : allEntries)//add all movies to exclude
        {
            MoviesToExclude.addAll(entry.getValue());
        }
        resultSet.removeAll(MoviesToExclude);
        System.out.printf("Result = ");
        System.out.println(resultSet);
        
        System.out.printf("Total movies = %d\n",resultSet.size());
    }
    
    public void FullResultSet() 
    { 
    /* Initialize resultSet to contain movies of all given actors */ 
        if(resultSet != null)
        {
            for(LinkedHashSet<String> movie : workingMap.values())
            {
                resultSet.addAll(movie);
            }
        }
        else
        {
            resultSet = new LinkedHashSet<String>();
             for(LinkedHashSet<String> movie : workingMap.values())
            {
                resultSet.addAll(movie);
            }
        }
        //get the full result set
        
    }
    public int getTotalMovies()
    {
        int moviesCount = 0;
        for(LinkedHashSet<String> movieSet : workingMap.values())
        {
            for(String movie : movieSet)
            {
                moviesCount++;
            }
        }
        return moviesCount;
    
    }
    public Set getTotalActorsSet()
    {
        return workingMap.keySet();
    }
    public LinkedHashSet getKeyValue(String actor)
    {
        return workingMap.get(actor);
    }
    //print entries
    public void printEntries()
    {
        Set< Map.Entry<String, LinkedHashSet<String>> > allEntries = workingMap.entrySet();
        for(Map.Entry<String,LinkedHashSet<String>> entry : allEntries)
        {
            System.out.printf("%20s >>",entry.getKey());
            LinkedHashSet<String> movies = entry.getValue();//set of movies
            for(String movie : movies)
            {
                System.out.printf(" %-25s%3s",movie," ");
            }
            System.out.println();
        }
        
    }
    public void printEntriesHashMap()
    {
        Set< Map.Entry<String, LinkedHashSet<String>> > allEntries = workingMap2.entrySet();
        for(Map.Entry<String,LinkedHashSet<String>> entry : allEntries)
        {
            System.out.printf("%20s >>",entry.getKey());
            LinkedHashSet<String> movies = entry.getValue();//set of movies
            for(String movie : movies)
            {
                System.out.printf(" %-25s%3s",movie," ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public void printTotalMoviesandActors(int movie_size)
    {
        int actor_size = workingMap.keySet().size();
        System.out.println("Total movies = " + movie_size);
        System.out.println("Total actors = " + actor_size);
    }
    public void printByEntry(String key)
    {
            System.out.printf("%20s >>",key);
            LinkedHashSet<String> movies = workingMap.get(key);//set of movies
            for(String movie : movies)
            {
                System.out.printf(" %-25s%3s",movie," ");
            }
            System.out.println();
    }
}