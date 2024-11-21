package examples_ch12;

class Teacher implements Comparable<Teacher>
{
    private String name;
    private int    age, employed, courses, dummy;
    
    public Teacher(String n, int a, int e, int c, int d)
    { name = n; age = a; employed = e; courses = c; dummy = d; }
    
    public String getName()        { return name; }
    public int    getAge()         { return age; }
    public int    getEmployed()    { return employed; }
    public int    getCourses()     { return courses; }
    public int    getDummy()       { return dummy; }
    
    public void   setDummy(int var)  
    { 
        switch(var)
        {
            case 1 -> dummy = age;
            case 2 -> dummy = employed;
            case 3 -> dummy = courses;
        }
    }
  
    public void   print()
    {
        System.out.printf("%s (dummy = %d), (age = %d, employed = %d, courses = %d) \n",
                           name, dummy, age, employed, courses);
    }
    
    public int compareTo(Teacher other)
    {
        return Integer.compare(dummy, other.dummy);
    }
}
