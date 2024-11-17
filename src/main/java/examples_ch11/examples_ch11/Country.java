package examples_ch11.examples_ch11;

class Country
{
    private String name;
    private double bigmac;
    public Country(String n, double bm)     { name = n; bigmac = bm; }
    public String getName()                 { return name; }
    public double getBigMac()               { return bigmac; }
    
    @Override
    public String toString()
    {
	String s = String.format("(%s, %.2f $)", name, bigmac);
	return s;
    }
    public void print()
    {
	System.out.println( toString() );
    }
        
    // equality based on name
    public boolean equals(Object o)
    {
	Country other = (Country)o;
	return this.name.equalsIgnoreCase(other.name);
    }
    
    // hashcode based on the hashcode of name
    public int hashCode()
    {
	return name.toLowerCase().hashCode();
    }
}
