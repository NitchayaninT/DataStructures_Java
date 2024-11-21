package examples_ch12;

class ShellSort extends SortBase {

    public ShellSort(int n)     { super(n); }
    public ShellSort(int[] x)   { super(x); }

    public void sort() {
        System.out.println("\n\n=== SHELL SORT ===");
        shellsort();
    }

    private void shellsort() {
        // Shell's increment
        int incr = values.length / 2;

        // Hibbard's increment
        // int k    = (int)(Math.log(values.length)/Math.log(2));
        // int incr = (int)(Math.pow(2,k) - 1);
        for (; incr > 1; incr = incr / 2)                           // Shell's 
        // for (; incr > 1; k--, incr = (int)(Math.pow(2,k) - 1))   // Hibbard's
        {
            System.out.printf("Increment %d : ", incr);
            print();

            for (int s = 0; s < incr; s++)                          // sort each sublist
            {
                System.out.printf("    Sublist : ", s);
                printjump(s, incr);
                insertionsort(s, incr);
                System.out.printf("              ", s);
                printjump(s, incr);
                System.out.println();
            }
        }
        System.out.printf("Increment %d : ", incr);
        print();
        insertionsort(0, 1);
        System.out.printf("Final       : ");
        print();
    }

    // modified from original version
    private void insertionsort(int start, int incr) {
        // 1st member = start, next members = every "incr" positions
        for (int unsorted = start + incr; unsorted < values.length;
                unsorted = unsorted + incr) {
            int i = unsorted;
            int sorted = i - incr;
            while (sorted >= start && values[i] < values[sorted]) {
                swap(i, sorted);
                i = sorted;
                sorted = sorted - incr;
            }
        }
    }
}

class D12_4_ShellSort {

    public static void main(String[] args) {
        int[] X = {6, 8, 1, 4, 10, 5, 3, 9, 22};
        int[] Y = {1, 3, 3, 5, 6, 8, 9, 10, 22};	// already sorted
        int[] Z = {22, 10, 9, 8, 6, 5, 3, 3, 1};        // reverse order
        int[] W = {1, 9, 2, 10, 3, 11, 4, 12, 5, 13, 6, 14, 7, 15, 8, 16};

        ShellSort SS = new ShellSort(W);
        //ShellSort SS = new ShellSort(10);
        System.out.println("Before sorting");
        SS.print();
        SS.sort();
        if (!SS.isAscendingSorted()) {
            System.out.println("Sorting fails !!");
        }
    }
}
