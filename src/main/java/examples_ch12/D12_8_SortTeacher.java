package examples_ch12;

import java.util.*;

class SortTeacher extends SortBaseTeacher {

    public SortTeacher(Teacher[] x)       { super(x); }
    
    /////////////////////////////////////////////////////////////
    public void bubblesort() {
        System.out.println("\n=== BUBBLE SORT ===");
        for (int i = 0; i < values.length; i++) {
            for (int k = 0; k < values.length - i - 1; k++) {
                if (values[k].compareTo(values[k + 1]) > 0) {
                    swap(k, k + 1);
                }
            }
        }
        if (!isAscendingSorted()) {
            System.out.println("Sorting fails !!");
        } else {
            print();
        }
    }
    /////////////////////////////////////////////////////////////

    public void selectionsort() {
        System.out.println("\n=== SELECTION SORT ===");
        for (int sorted = 0; sorted < values.length; sorted++) {
            int min = sorted;
            for (int i = sorted + 1; i < values.length; i++) {
                if (values[i].compareTo(values[min]) < 0) { 
                    min = i;
                }
            }
            swap(sorted, min);
        }
        if (!isAscendingSorted()) {
            System.out.println("Sorting fails !!");
        } else {
            print();
        }
    }
    /////////////////////////////////////////////////////////////

    public void insertionsort() {
        System.out.println("\n=== INSERTION SORT ===");
        for (int unsorted = 1; unsorted < values.length; unsorted++) {
            int i = unsorted;
            int sorted = i - 1;
            while (sorted >= 0 && values[i].compareTo(values[sorted]) < 0) {
                swap(i, sorted);
                i = sorted;
                sorted--;
            }
        }
        if (!isAscendingSorted()) {
            System.out.println("Sorting fails !!");
        } else {
            print();
        }
    }
    /////////////////////////////////////////////////////////////

    public void shellsort() {
        System.out.println("\n=== SHELL SORT ===");

        int k = (int) (Math.log(values.length) / Math.log(2));
        int incr = (int) (Math.pow(2, k) - 1);
        for (; incr > 1; k--, incr = (int) (Math.pow(2, k) - 1)) {
            for (int s = 0; s < incr; s++) {
                insertionsort(s, incr);
            }
        }
        insertionsort(0, 1);

        if (!isAscendingSorted()) {
            System.out.println("Sorting fails !!");
        } else {
            print();
        }
    }

    private void insertionsort(int start, int incr) {
        for (int unsorted = start + incr; unsorted < values.length;
                unsorted = unsorted + incr) {
            int i = unsorted;
            int sorted = i - incr;
            while (sorted >= start && values[i].compareTo(values[sorted]) < 0) {
                swap(i, sorted);
                i = sorted;
                sorted = sorted - incr;
            }
        }
    }
    /////////////////////////////////////////////////////////////

    public void heapsort() {
        System.out.println("\n=== HEAP SORT ===");

        int size = values.length;
        for (int i = size / 2; i >= 0; i--) {
            maxheapify(i, size);
        }

        for (int i = values.length - 1; i > 0; i--) {
            swap(0, i);
            maxheapify(0, i);
        }
        if (!isAscendingSorted()) {
            System.out.println("Sorting fails !!");
        } else {
            print();
        }
    }

    private void maxheapify(int root, int size) {
        int max = root;
        int left = (2 * root) + 1;	// root's index starts at 0
        int right = left + 1;

        if (left < size && values[left].compareTo(values[root]) >= 0) {       // stable
        //if (left < size && values[left].compareTo(values[root]) > 0) {      // unstable
            max = left;
        }

        if (right < size && values[right].compareTo(values[max]) >= 0) {      // stable
        //if (right < size && values[right].compareTo(values[max]) > 0) {     // unstable
            max = right;
        }

        if (max != root) {
            swap(max, root);
            maxheapify(max, size);
        }
    }
    /////////////////////////////////////////////////////////////

    public void mergesort() {
        System.out.println("\n=== MERGE SORT ===");
        mergesort(0, values.length - 1);
        if (!isAscendingSorted()) {
            System.out.println("Sorting fails !!");
        } else {
            print();
        }
    }

    private void mergesort(int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergesort(left, mid);
        mergesort(mid + 1, right);
        merge(left, mid, mid + 1, right);
    }

    private void merge(int leftFirst, int leftLast, int rightFirst, int rightLast) {
        Teacher[] temp = new Teacher[values.length];
        int i = leftFirst, backupFirst = leftFirst;

        while (leftFirst <= leftLast && rightFirst <= rightLast) {
            if (values[leftFirst].compareTo(values[rightFirst]) <= 0) {       // stable 
            //if (values[leftFirst].compareTo(values[rightFirst]) < 0) {      // unstable 
                temp[i++] = values[leftFirst++];
            } else {
                temp[i++] = values[rightFirst++];
            }
        }

        while (leftFirst <= leftLast) {
            temp[i++] = values[leftFirst++];
        }

        while (rightFirst <= rightLast) {
            temp[i++] = values[rightFirst++];
        }

        for (int j = backupFirst; j <= rightLast; j++) {
            values[j] = temp[j];
        }
    }
    /////////////////////////////////////////////////////////////

    public void quicksort() {
        System.out.println("\n=== QUICK SORT ===");
        quicksort(0, values.length - 1);
        if (!isAscendingSorted()) {
            System.out.println("Sorting fails !!");
        } else {
            print();
        }
    }

    private void quicksort(int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = partition(left, right);
        quicksort(left, mid - 1);
        quicksort(mid + 1, right);
    }

    private int partition(int left, int right) {
        Teacher pivot = values[left];
        int L = left, R = right + 1;

        while (L < R) {
            while (L < right && values[++L].compareTo(pivot) < 0);
            while (R > left && values[--R].compareTo(pivot) > 0);
            if (L < R) {
                swap(L, R);
            }
        }
        swap(left, R);
        return R;
    }
}

////////////////////////////////////////////////////////////////////////////////
public class D12_8_SortTeacher {

    String path     = "src/main/java/examples_ch12/";
    String fileName = "teachers.txt";   
    Teacher     [] T;
    SortTeacher algorithm;

    public void initialize()
    {
        MyFileReader myreader = new MyFileReader(path, fileName);
        myreader.readFile();
        ArrayList<String> allLines = myreader.getLines();
        
        T = new Teacher[ allLines.size() ];
        int index = 0;
        for(String line : allLines)
        {
            String []items  = line.split(",");
            String name     = items[0].trim();
            int    age      = Integer.parseInt( items[1].trim() );
            int    employed = Integer.parseInt( items[2].trim() );
            int    courses  = Integer.parseInt( items[3].trim() );
            
            T[index] = new Teacher(name, age, employed, courses, -1);
            index++;
        }
        System.out.println("=== Before sorting ===");
        for(Teacher te : T) te.print();
        
        algorithm = new SortTeacher(T);
    }
    
    public static void main(String[] args) {
        D12_8_SortTeacher mainprog = new D12_8_SortTeacher();
        mainprog.initialize();
  
        System.out.println("\n=== Sort by age ===");    mainprog.testSorting(1);
        System.out.println("\n=== Sort by employ ==="); mainprog.testSorting(2);
        
        mainprog.testJavaSorting();
    }
    
    public void testSorting(int var)
    {
        for(Teacher te : T) te.setDummy(var);
                
        //algorithm.bubblesort();
        //algorithm.selectionsort();        // unstable
        //algorithm.insertionsort();
        //algorithm.shellsort();	    // unstable
        algorithm.heapsort();             // depend on comparison rule
        //algorithm.mergesort();	    // depend on comparison rule
        //algorithm.quicksort();	    // unstable        
    }
    
    public void testJavaSorting() //stable sorting
    {
        System.out.println("\n=== Java sort by age ===");
        for(Teacher te : T) te.setDummy(1);
        Arrays.sort(T); 
        for(Teacher te : T) te.print();
        System.out.println();

        System.out.println("\n=== Java sort by employ ===");
        for(Teacher te : T) te.setDummy(2);
        Arrays.sort(T); 
        for(Teacher te : T) te.print();
        System.out.println();
    }
}
