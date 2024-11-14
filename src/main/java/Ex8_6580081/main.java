package Ex8_6580081;
import java.util.*;
/**
 *
 * @author Nitchayanin Thamkunanon 6580081
 */
public class main {
    
    String path     = "src/main/java/Ex8_6580081/";
    String fileName = "movies.txt";
    
    ActorMap Map = new ActorMap(); //create Treemap
    int movies_count;//all movies count
    public void initialize()
    {
        Ex8_6580081.MyFileReader myreader = new Ex8_6580081.MyFileReader(path, fileName);
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
                     
                     Map.setTreeMap(actor, movie);//add to the treemap
                }
            
            countLines++;
        }
        System.out.println();
        
    }
    //MAIN
    //menu 0 : union, 1 : intersect, 2 : difference
    public static void main(String args[])
    {
        main Mainprog = new main();
        Mainprog.initialize();
        Mainprog.printAll();
        Mainprog.printSigns();
        Mainprog.userInput();
    }
    
    public void menu()
    {
            System.out.println("Find Movies >> 0 = set initial actors,");
            System.out.printf("%14s 1 = contain actors\n"," ");
            System.out.printf("%14s 2 = without actors\n"," ");
            System.out.printf("%10sothers = quit\n"," ");
    }
    public void printAll()
    {
        Map.printTotalMoviesandActors(movies_count);
        Map.printEntries();
    }
    
    public void userInput()
    {
        boolean quit = false ;
         while(true)
        {
            menu();
            Scanner sc = new Scanner(System.in);
            int user_input = sc.nextInt();
            switch(user_input)
            {
                case 0:
                    Case0();
                    break;
                    
                case 1:
                    Case1();
                    break;
                 
                case 2:
                    Case2();
                    break;
                    
                default:
                    quit = true;
                    break;
            }
            if(quit == true) break;
        }
    }
    public LinkedHashSet<String> readStrings()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Add names or surnames seperated by comma =");
        String input = sc.nextLine();
        String[] names = input.split(",");
        for(int i=0;i<names.length;i++)
        {
            String actor  = names[i].trim();
            names[i]=actor;         
        }
        LinkedHashSet<String> nameslist = new LinkedHashSet<>(Arrays.asList(names));
  
        return nameslist;
    }
   public void printSigns()
   {
        System.out.println("=================================================================================");
   }
   public void Case0()
   {
        int movie_size = 0;
        LinkedHashSet<String> chosen_actors = readStrings();
        LinkedHashSet<String> actorsToAdd = new LinkedHashSet<>(); //temp for chosen_actors
        Set<String> total_actors = Map.getTotalActorsSet(); //get all actors

        for(String chosen_actor : chosen_actors)
        {
             for(String actor:total_actors)
             {
                 if(actor.toLowerCase().contains(chosen_actor))
                 {
                     actorsToAdd.add(actor);
                     LinkedHashSet<String>movies = Map.getKeyValue(actor);
                     for(String movie : movies)
                     {
                        Map.setLinkedHashMap(actor, movie); //set user input to linkedhashmap
                        movie_size++;
                     }

                 }
             }
        }

        System.out.println("Valid input actors = "+actorsToAdd);
        System.out.println();
        Map.printEntriesHashMap();
        Map.initialActors();
        printSigns();      
   }
   public void Case1()
   {
        int movie_size = 0;
        LinkedHashSet<String> chosen_actors = readStrings();
        LinkedHashSet<String> actorsToAdd = new LinkedHashSet<>(); //temp for chosen_actors
        Set<String> total_actors = Map.getTotalActorsSet(); //get all actors

        ActorMap Map1 = new ActorMap();
        for(String chosen_actor : chosen_actors)
        {
             for(String actor:total_actors)
             {
                 if(actor.toLowerCase().contains(chosen_actor))
                 {
                     actorsToAdd.add(actor);
                     LinkedHashSet<String>movies = Map.getKeyValue(actor);
                     for(String movie : movies)
                     {
                        Map1.setLinkedHashMap(actor, movie); 
                        movie_size++;
                     }

                 }
             }
        }
        //got map of inserted actors in map1
        //insert map1 in map??
        System.out.println("Valid input actors = "+actorsToAdd);
        System.out.println();
        Map.insertHashMap(Map1.getLinkedHashMap()); //insert the original map to the submap
        Map.printEntriesHashMap();
        Map.containActors();
        printSigns();
   }
    public void Case2()
   {
        int movie_size = 0;
        LinkedHashSet<String> chosen_actors = readStrings();
        LinkedHashSet<String> actorsToAdd = new LinkedHashSet<>(); //temp for chosen_actors
        Set<String> total_actors1 = Map.getTotalActorsSet(); //get all actors

        ActorMap Map2 = new ActorMap();
        for(String chosen_actor : chosen_actors)
        {
             for(String actor:total_actors1)
             {
                 if(actor.toLowerCase().contains(chosen_actor))
                 {
                     actorsToAdd.add(actor);
                     LinkedHashSet<String>movies = Map.getKeyValue(actor);
                     for(String movie : movies)
                     {
                        Map2.setLinkedHashMap(actor, movie); 
                        movie_size++;
                     }

                 }
             }
        }

        System.out.println("Valid input actors = "+actorsToAdd);
        System.out.println();
        Map.insertHashMap(Map2.getLinkedHashMap()); //insert submap to original map
        Map.printEntriesHashMap();
        Map.withoutActors();
        printSigns();
   }
   
}